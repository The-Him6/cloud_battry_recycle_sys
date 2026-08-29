package com.br.recycle.service.impl;

import com.br.api.client.UserClient;
import com.br.api.client.UserPointsClient;
import com.br.common.constants.SystemConstants;
import com.br.common.domain.PageRequest;
import com.br.common.domain.PageResult;
import com.br.common.domain.Result;
import com.br.common.exception.BadRequestException;
import com.br.common.exception.DbException;
import com.br.common.exception.ForbiddenException;
import com.br.recycle.entity.BatteryType;
import com.br.recycle.entity.RecycleDetail;
import com.br.recycle.entity.RecycleOrder;
import com.br.recycle.mapper.BatteryTypeMapper;
import com.br.recycle.mapper.RecycleDetailMapper;
import com.br.recycle.mapper.RecycleOrderMapper;
import com.br.recycle.service.IRecycleOrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 回收订单服务类
 */
@Service("recycleOrderService")
@RequiredArgsConstructor
public class RecycleOrderServiceImpl implements IRecycleOrderService {

    private final RecycleOrderMapper recycleOrderMapper;

    private final RecycleDetailMapper recycleDetailMapper;

    private final BatteryTypeMapper batteryTypeMapper;

    private final UserClient userClient;

    private final UserPointsClient userPointsClient;

    private final ObjectMapper objectMapper;

    /**
     * 根据ID查询订单
     */
    public RecycleOrder getById(Long id) {
        RecycleOrder order = recycleOrderMapper.getById(id);
        if (order == null) {
            throw new DbException(SystemConstants.ORDER_NOT_FOUND);
        }
        return order;
    }

    /**
     * 查询所有订单
     */
    public List<RecycleOrder> listAll() {
        return recycleOrderMapper.listAll();
    }

    /**
     * 分页查询订单列表
     */
    public PageResult<RecycleOrder> getOrderPage(PageRequest pageRequest) {
        // 开启分页
        PageHelper.startPage(pageRequest.getValidPageNum(), pageRequest.getValidPageSize());

        // 查询订单列表
        List<RecycleOrder> list = recycleOrderMapper.listAll();

        // 封装分页结果
        PageInfo<RecycleOrder> pageInfo = new PageInfo<>(list);
        return PageResult.of(pageInfo);
    }

    /**
     * 根据用户ID查询订单
     */
    public List<RecycleOrder> listByUserId(Long userId) {
        return recycleOrderMapper.listByUserId(userId);
    }

    /**
     * 查询订单明细
     * 已完成订单的明细从 recycle_detail 表查询，未完成订单的明细从 detailJson 解析
     */
    public List<RecycleDetail> getOrderDetails(Long orderId) {
        RecycleOrder order = recycleOrderMapper.getById(orderId);
        if (order == null) {
            return new ArrayList<>();
        }
        // 已完成订单的明细已写入明细表，直接查表
        if (SystemConstants.ORDER_STATUS_COMPLETED.equals(order.getOrderStatus())) {
            return recycleDetailMapper.listByOrderId(orderId);
        }
        // 未完成订单的明细暂存在 detailJson 中
        if (order.getDetailJson() == null || order.getDetailJson().isEmpty()) {
            return new ArrayList<>();
        }
        try {
            return objectMapper.readValue(order.getDetailJson(), new TypeReference<List<RecycleDetail>>() {});
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("订单明细反序列化失败", e);
        }
    }

    /**
     * 创建订单
     */
    @Transactional(rollbackFor = Exception.class)
    public void createOrder(RecycleOrder order, List<RecycleDetail> details) {
        // 生成订单编号
        String orderNumber = generateOrderNumber(order.getUserId());
        order.setOrderNumber(orderNumber);

        // 计算总数量和总积分
        int totalCount = 0;
        int totalPoints = 0;

        for (RecycleDetail detail : details) {
            BatteryType batteryType = batteryTypeMapper.getById(detail.getBatteryTypeId());
            if (batteryType == null) {
                throw new DbException(SystemConstants.BATTERY_TYPE_NOT_FOUND);
            }

            int points = batteryType.getPoints() * detail.getBatteryCount();
            detail.setPoints(points);

            totalCount += detail.getBatteryCount();
            totalPoints += points;
        }

        order.setTotalCount(totalCount);
        order.setTotalPoints(totalPoints);
        order.setOrderStatus(SystemConstants.ORDER_STATUS_PENDING); // 设置为待处理状态

        // 明细暂存到订单的 detailJson 字段，待订单完成后再写入 recycle_detail
        try {
            order.setDetailJson(objectMapper.writeValueAsString(details));
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("订单明细序列化失败", e);
        }

        // 插入订单
        recycleOrderMapper.insert(order);
    }

    /**
     * 更新订单状态
     */
    @GlobalTransactional(rollbackFor = Exception.class)
    public void updateStatus(Long id, Integer status) {
        RecycleOrder order = recycleOrderMapper.getById(id);
        if (order == null) {
            throw new DbException(SystemConstants.ORDER_NOT_FOUND);
        }

        Integer oldStatus = order.getOrderStatus();
        // 状态流转校验：已完成/已取消为终态，不可再变更；新状态必须在合法集合内
        if (status == null
                || SystemConstants.ORDER_STATUS_COMPLETED.equals(oldStatus)
                || SystemConstants.ORDER_STATUS_CANCELLED.equals(oldStatus)
                || (status != 0 && status != 1 && status != 2 && status != 3)) {
            throw new BadRequestException(SystemConstants.ORDER_STATUS_ILLEGAL);
        }
        order.setOrderStatus(status);
        recycleOrderMapper.update(order);

        // 订单变为已完成时，才把明细写入 recycle_detail（确保明细表只有已完成订单的数据）
        if (SystemConstants.ORDER_STATUS_COMPLETED.equals(status)) {
            insertDetailsOnComplete(order);

            // 给用户增加积分
            Result<Boolean> r = userPointsClient.add(order.getUserId(), order.getTotalPoints());
            if (r == null || !Boolean.TRUE.equals(r.getData())) {
                throw new DbException(SystemConstants.POINTS_GRANT_FAILED);
            }
        }
    }

    /**
     * 订单完成时，将暂存的明细写入 recycle_detail
     */
    private void insertDetailsOnComplete(RecycleOrder order) {
        if (order.getDetailJson() == null || order.getDetailJson().isEmpty()) {
            return;
        }
        try {
            List<RecycleDetail> details = objectMapper.readValue(order.getDetailJson(),
                    new TypeReference<List<RecycleDetail>>() {});
            if (details == null || details.isEmpty()) {
                return;
            }
            for (RecycleDetail detail : details) {
                detail.setOrderId(order.getId());
            }
            recycleDetailMapper.batchInsert(details);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("订单明细反序列化失败", e);
        }
    }

    /**
     * 取消订单
     */
    public void cancelOrder(Long id, Long userId) {
        RecycleOrder order = recycleOrderMapper.getById(id);
        if (order == null) {
            throw new DbException(SystemConstants.ORDER_NOT_FOUND);
        }

        // 检查是否是订单所有者
        if (!order.getUserId().equals(userId)) {
            throw new ForbiddenException(SystemConstants.PERMISSION_DENIED);
        }

        // 只有待处理状态的订单可以取消
        if (!order.getOrderStatus().equals(SystemConstants.ORDER_STATUS_PENDING)) {
            throw new BadRequestException(SystemConstants.ORDER_CANNOT_CANCEL);
        }

        order.setOrderStatus(SystemConstants.ORDER_STATUS_CANCELLED);
        recycleOrderMapper.update(order);
    }

    /**
     * 生成订单编号
     */
    private String generateOrderNumber(Long userId) {
        LocalDateTime now = LocalDateTime.now();
        String datePart = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String userPart = buildUserIdentifier(userId);
        int currentCount = recycleOrderMapper.countUserOrdersByDate(userId, now.toLocalDate().toString());

        return "BR" + datePart + userPart + String.format("%04d", currentCount + 1);
    }

    /**
     * 构建用户标识
     */
    private String buildUserIdentifier(Long userId) {
        // 使用用户ID的模运算生成短标识
        if (userId != null) {
            return "U" + String.format("%03d", userId % 1000);
        }
        return "U000";
    }

    /**
     * 搜索订单（管理员）
     */
    public PageResult<RecycleOrder> searchOrders(String address, String startDate, String endDate,
            Integer orderStatus, PageRequest pageRequest) {
        int offset = (pageRequest.getValidPageNum() - 1) * pageRequest.getValidPageSize();

        List<RecycleOrder> list = recycleOrderMapper.searchOrders(address, startDate, endDate, orderStatus, null, offset,
                pageRequest.getValidPageSize());
        int total = recycleOrderMapper.countBySearch(address, startDate, endDate, orderStatus, null);

        PageResult<RecycleOrder> result = new PageResult<>(list, (long) total, pageRequest.getValidPageNum(),
                pageRequest.getValidPageSize());
        result.setPages((int) Math.ceil((double) total / pageRequest.getValidPageSize()));

        return result;
    }

    /**
     * 搜索我的订单（用户）
     */
    public List<RecycleOrder> searchMyOrders(Long userId, String address, String startDate, String endDate) {
        return recycleOrderMapper.searchOrders(address, startDate, endDate, null, userId, 0, 10000);
    }
}
