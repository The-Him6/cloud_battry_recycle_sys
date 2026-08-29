package com.br.common.service.impl;

import com.br.common.service.IFileUploadService;
import com.br.common.utils.AliyunOssUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传服务
 * 统一管理所有文件上传操作
 */
@Service("fileUploadService")
@Slf4j
@RequiredArgsConstructor
public class FileUploadServiceImpl implements IFileUploadService {

    private final AliyunOssUtil aliyunOssUtil;

    /**
     * 上传头像 (对应数据库字段: avatar)
     */
    public String uploadAvatar(MultipartFile file) {
        log.info("开始上传头像: {}", file.getOriginalFilename());
        return aliyunOssUtil.uploadFile(file, "avatar/");
    }

    /**
     * 上传商品图片 (对应数据库字段: image_url)
     */
    public String uploadProductImage(MultipartFile file) {
        log.info("开始上传商品图片: {}", file.getOriginalFilename());
        return aliyunOssUtil.uploadFile(file, "image_url/");
    }

    /**
     * 上传电池类型图标 (对应数据库字段: icon)
     */
    public String uploadBatteryTypeIcon(MultipartFile file) {
        log.info("开始上传电池类型图标: {}", file.getOriginalFilename());
        return aliyunOssUtil.uploadFile(file, "icon/");
    }

    /**
     * 删除文件
     */
    public void deleteFile(String fileUrl) {
        log.info("删除文件: {}", fileUrl);
        aliyunOssUtil.deleteFile(fileUrl);
    }
}
