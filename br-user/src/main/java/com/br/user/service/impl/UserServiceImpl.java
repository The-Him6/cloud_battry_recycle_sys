package com.br.user.service.impl;

import com.br.common.constants.SystemConstants;
import com.br.common.domain.PageRequest;
import com.br.common.domain.PageResult;
import com.br.common.exception.BadRequestException;
import com.br.common.exception.DbException;
import com.br.user.dto.ChangePasswordDTO;
import com.br.user.entity.User;
import com.br.user.mapper.UserMapper;
import com.br.user.service.IUserService;
import com.br.user.vo.UserVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


/**
 * 用户服务类
 */
@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserMapper userMapper;

    /**
     * 统计用户总数
     */
    public Long count() {
        Integer total = userMapper.count();
        return total == null ? 0L : total.longValue();
    }

    /**
     * 根据ID查询用户
     */
    public UserVO getById(Long id) {
        User user = userMapper.getById(id);
        if (user == null) {
            throw new DbException(SystemConstants.USER_NOT_FOUND);
        }
        UserVO vo = new UserVO();
        BeanUtils.copyProperties(user, vo);
        return vo;
    }

    /**
     * 查询所有用户
     */
    public List<UserVO> listAll() {
        List<User> users = userMapper.listAll();
        List<UserVO> voList = new ArrayList<>();
        for (User user : users) {
            UserVO vo = new UserVO();
            BeanUtils.copyProperties(user, vo);
            voList.add(vo);
        }
        return voList;
    }

    /**
     * 分页查询用户列表
     */
    public PageResult<UserVO> getUserPage(PageRequest pageRequest) {
        // 开启分页
        PageHelper.startPage(pageRequest.getValidPageNum(), pageRequest.getValidPageSize());

        // 查询用户列表
        List<User> users = userMapper.listAll();

        // 转换为 VO
        List<UserVO> voList = new ArrayList<>();
        for (User user : users) {
            UserVO vo = new UserVO();
            BeanUtils.copyProperties(user, vo);
            voList.add(vo);
        }

        // 封装分页结果
        PageInfo<User> pageInfo = new PageInfo<>(users);
        PageResult<UserVO> result = new PageResult<>();
        result.setTotal(pageInfo.getTotal());
        result.setList(voList);
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setPages(pageInfo.getPages());

        return result;
    }

    /**
     * 添加用户
     */
    public void addUser(User user) {
        // 检查用户名是否已存在
        User existUser = userMapper.getByUsername(user.getUsername());
        if (existUser != null) {
            throw new BadRequestException(SystemConstants.USER_ALREADY_EXISTS);
        }

        // 校验密码格式（仅限大小写字母、数字和 . !，长度6-20）
        if (user.getPassword() == null || !user.getPassword().matches("^[A-Za-z0-9.!]{6,20}$")) {
            throw new BadRequestException(SystemConstants.USER_PASSWORD_FORMAT_ERROR);
        }

        // 密码加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8)));

        // 设置默认值
        if (user.getRole() == null) {
            user.setRole(SystemConstants.ROLE_USER);
        }
        if (user.getStatus() == null) {
            user.setStatus(SystemConstants.STATUS_NORMAL);
        }
        if (user.getNickname() == null || user.getNickname().isEmpty()) {
            user.setNickname(user.getUsername());
        }

        userMapper.insert(user);
    }

    /**
     * 更新用户信息
     */
    public void update(User user) {
        User existUser = userMapper.getById(user.getId());
        if (existUser == null) {
            throw new DbException(SystemConstants.USER_NOT_FOUND);
        }

        // 如果修改了密码，需要加密
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8)));
        }

        userMapper.update(user);
    }

    /**
     * 修改当前用户密码
     */
    public void changePassword(Long userId, ChangePasswordDTO dto) {
        String encryptedOldPassword = DigestUtils.md5DigestAsHex(dto.getOldPassword().getBytes(StandardCharsets.UTF_8));
        User user = userMapper.getByIdAndPassword(userId, encryptedOldPassword);
        if (user == null) {
            throw new BadRequestException(SystemConstants.USER_OLD_PASSWORD_ERROR);
        }
        if (dto.getOldPassword().equals(dto.getNewPassword())) {
            throw new BadRequestException(SystemConstants.USER_NEW_PASSWORD_SAME_AS_OLD);
        }

        User updateUser = new User();
        updateUser.setId(userId);
        updateUser.setPassword(dto.getNewPassword());
        update(updateUser);
    }

    /**
     * 删除用户
     */
    public void deleteById(Long id) {
        User user = userMapper.getById(id);
        if (user == null) {
            throw new DbException(SystemConstants.USER_NOT_FOUND);
        }
        userMapper.deleteById(id);
    }

    /**
     * 搜索用户
     */
    public PageResult<UserVO> searchUsers(Long userId, String username, PageRequest pageRequest) {
        int offset = (pageRequest.getValidPageNum() - 1) * pageRequest.getValidPageSize();

        List<User> users = userMapper.searchUsers(userId, username, offset, pageRequest.getValidPageSize());
        Integer total = userMapper.countBySearch(userId, username);

        List<UserVO> voList = new ArrayList<>();
        for (User user : users) {
            UserVO vo = new UserVO();
            BeanUtils.copyProperties(user, vo);
            voList.add(vo);
        }

        PageResult<UserVO> result = new PageResult<>();
        result.setTotal((long) total);
        result.setList(voList);
        result.setPageNum(pageRequest.getValidPageNum());
        result.setPageSize(pageRequest.getValidPageSize());
        result.setPages((int) Math.ceil((double) total / pageRequest.getValidPageSize()));

        return result;
    }
}
