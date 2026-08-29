package com.br.common.config;

import com.br.common.aspect.OssUploadAspect;
import com.br.common.service.impl.FileUploadServiceImpl;
import com.br.common.utils.AliyunOssUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Import;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传自动装配配置
 * 通过 @Import 注册 OSS 相关组件，写进 AutoConfiguration.imports，
 * 让所有依赖 br-common 的服务自动具备上传能力（无需各自改扫描包）。
 * 没有 SpringMVC（MultipartFile）的模块自动跳过。
 */
@Import({
        AliyunOssConfig.class,
        AliyunOssUtil.class,
        FileUploadServiceImpl.class,
        OssUploadAspect.class
})
@ConditionalOnClass(MultipartFile.class)
@ConditionalOnProperty(prefix = "aliyun.oss",
        name = {"endpoint", "access-key-id", "access-key-secret", "bucket-name"})
public class FileUploadAutoConfiguration {
}
