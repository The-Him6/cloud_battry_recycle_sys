package com.br.recycle.controller;

import com.br.common.annotation.OssUpload;
import com.br.common.constants.SystemConstants;
import com.br.common.domain.Result;
import com.br.common.exception.ForbiddenException;
import com.br.common.service.IFileUploadService;
import com.br.common.utils.AuthUtil;
import com.br.recycle.entity.BatteryType;
import com.br.recycle.service.IBatteryTypeService;
import com.br.recycle.vo.BatteryTypeVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * 电池种类控制器
 */
@Tag(name = "电池类型管理", description = "电池类型的增删改查与图标上传")
@RestController
@RequestMapping("/battery-type")
@RequiredArgsConstructor
public class BatteryTypeController {

    private final IBatteryTypeService batteryTypeService;

    private final IFileUploadService fileUploadService;

    /**
     * 根据ID查询电池种类
     */
    @Operation(summary = "根据ID查询电池种类")
    @GetMapping("/{id}")
    public Result<BatteryTypeVO> getById(@PathVariable Long id) {
        BatteryType batteryType = batteryTypeService.getById(id);
        BatteryTypeVO vo = new BatteryTypeVO();
        BeanUtils.copyProperties(batteryType, vo);
        return Result.success(vo);
    }

    /**
     * 查询所有电池种类
     */
    @Operation(summary = "查询所有电池种类")
    @GetMapping("/list")
    public Result<List<BatteryTypeVO>> listAll() {
        List<BatteryType> list = batteryTypeService.listAll();
        List<BatteryTypeVO> voList = new ArrayList<>();
        for (BatteryType item : list) {
            BatteryTypeVO vo = new BatteryTypeVO();
            BeanUtils.copyProperties(item, vo);
            voList.add(vo);
        }
        return Result.success(voList);
    }

    /**
     * 查询启用的电池种类
     */
    @Operation(summary = "查询启用的电池种类")
    @GetMapping("/enabled")
    public Result<List<BatteryTypeVO>> listEnabled() {
        List<BatteryType> list = batteryTypeService.listEnabled();
        List<BatteryTypeVO> voList = new ArrayList<>();
        for (BatteryType item : list) {
            BatteryTypeVO vo = new BatteryTypeVO();
            BeanUtils.copyProperties(item, vo);
            voList.add(vo);
        }
        return Result.success(voList);
    }

    /**
     * 添加电池种类（仅管理员）
     */
    @Operation(summary = "添加电池种类", description = "仅管理员可操作")
    @PostMapping
    public Result<Void> add(@RequestBody BatteryType batteryType) {
        Integer role = AuthUtil.getRole();
        if (!SystemConstants.ROLE_ADMIN.equals(role)) {
            throw new ForbiddenException(SystemConstants.ADMIN_ONLY);
        }
        batteryTypeService.add(batteryType);
        return Result.success(SystemConstants.BATTERY_TYPE_ADD_SUCCESS, null);
    }

    /**
     * 更新电池种类（仅管理员）
     */
    @Operation(summary = "更新电池种类", description = "仅管理员可操作")
    @PutMapping
    public Result<Void> update(@RequestBody BatteryType batteryType) {
        Integer role = AuthUtil.getRole();
        if (!SystemConstants.ROLE_ADMIN.equals(role)) {
            throw new ForbiddenException(SystemConstants.ADMIN_ONLY);
        }
        batteryTypeService.update(batteryType);
        return Result.success(SystemConstants.BATTERY_TYPE_UPDATE_SUCCESS, null);
    }

    /**
     * 删除电池种类（仅管理员）
     */
    @Operation(summary = "删除电池种类", description = "仅管理员可操作")
    @DeleteMapping("/{id}")
    public Result<Void> deleteById(@PathVariable Long id) {
        Integer role = AuthUtil.getRole();
        if (!SystemConstants.ROLE_ADMIN.equals(role)) {
            throw new ForbiddenException(SystemConstants.ADMIN_ONLY);
        }
        batteryTypeService.deleteById(id);
        return Result.success(SystemConstants.BATTERY_TYPE_DELETE_SUCCESS, null);
    }

    /**
     * 上传电池图标（仅管理员）
     */
    @Operation(summary = "上传电池图标", description = "仅管理员可操作，支持 jpeg/png/jpg/gif，最大 2MB")
    @PostMapping("/upload-icon")
    @OssUpload(path = "icon/", allowedTypes = { "image/jpeg", "image/png", "image/jpg", "image/gif" }, maxSize = 2
            * 1024 * 1024)
    public Result<String> uploadIcon(@RequestParam("file") MultipartFile file) {
        Integer role = AuthUtil.getRole();
        if (!SystemConstants.ROLE_ADMIN.equals(role)) {
            throw new ForbiddenException(SystemConstants.ADMIN_ONLY);
        }

        String iconUrl = fileUploadService.uploadBatteryTypeIcon(file);
        return Result.success(SystemConstants.FILE_UPLOAD_SUCCESS, iconUrl);
    }
}
