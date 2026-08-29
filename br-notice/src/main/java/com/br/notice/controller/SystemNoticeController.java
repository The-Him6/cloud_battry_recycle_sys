package com.br.notice.controller;
import lombok.RequiredArgsConstructor;

import com.br.common.constants.SystemConstants;
import com.br.common.domain.Result;
import com.br.common.utils.AuthUtil;
import com.br.notice.entity.SystemNotice;
import com.br.notice.service.ISystemNoticeService;
import com.br.notice.vo.SystemNoticeVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统弹窗公告控制器
 */
@Tag(name = "系统公告", description = "系统弹窗公告的增删改查与已读标记")
@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class SystemNoticeController {

    private final ISystemNoticeService systemNoticeService;

    /**
     * 管理员查询全部公告
     */
    @Operation(summary = "查询全部公告", description = "仅管理员可操作")
    @GetMapping("/list")
    public Result<List<SystemNoticeVO>> listAll() {
        AuthUtil.requireAdmin();
        List<SystemNotice> list = systemNoticeService.listAll();
        List<SystemNoticeVO> voList = new ArrayList<>();
        for (SystemNotice item : list) {
            SystemNoticeVO vo = new SystemNoticeVO();
            BeanUtils.copyProperties(item, vo);
            voList.add(vo);
        }
        return Result.success(voList);
    }

    /**
     * 用户查询未读有效弹窗
     */
    @Operation(summary = "查询未读有效弹窗", description = "返回当前用户未读且在有效期内的公告")
    @GetMapping("/active")
    public Result<List<SystemNoticeVO>> listActiveUnread() {
        List<SystemNotice> list = systemNoticeService.listActiveUnread(AuthUtil.getUserId());
        List<SystemNoticeVO> voList = new ArrayList<>();
        for (SystemNotice item : list) {
            SystemNoticeVO vo = new SystemNoticeVO();
            BeanUtils.copyProperties(item, vo);
            voList.add(vo);
        }
        return Result.success(voList);
    }

    /**
     * 管理员新增公告
     */
    @Operation(summary = "新增公告", description = "仅管理员可操作")
    @PostMapping
    public Result<Void> add(@RequestBody SystemNotice notice) {
        AuthUtil.requireAdmin();
        systemNoticeService.add(notice, AuthUtil.getUserId());
        return Result.success(SystemConstants.NOTICE_ADD_SUCCESS, null);
    }

    /**
     * 管理员更新公告
     */
    @Operation(summary = "更新公告", description = "仅管理员可操作")
    @PutMapping
    public Result<Void> update(@RequestBody SystemNotice notice) {
        AuthUtil.requireAdmin();
        systemNoticeService.update(notice);
        return Result.success(SystemConstants.NOTICE_UPDATE_SUCCESS, null);
    }

    /**
     * 用户标记公告已读
     */
    @Operation(summary = "标记公告已读")
    @PostMapping("/{id}/read")
    public Result<Void> markRead(@PathVariable Long id) {
        systemNoticeService.markRead(id, AuthUtil.getUserId());
        return Result.success(SystemConstants.NOTICE_READ_SUCCESS, null);
    }
}