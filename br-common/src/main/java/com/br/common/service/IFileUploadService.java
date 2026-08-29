package com.br.common.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传服务接口。
 */
public interface IFileUploadService {

    String uploadAvatar(MultipartFile file);

    String uploadProductImage(MultipartFile file);

    String uploadBatteryTypeIcon(MultipartFile file);

    void deleteFile(String fileUrl);
}
