package com.br.common.aspect;

import com.br.common.annotation.OssUpload;
import com.br.common.constants.SystemConstants;
import com.br.common.exception.BadRequestException;
import com.br.common.utils.AliyunOssUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 文件上传AOP切面
 * 统一处理文件上传到阿里云OSS的逻辑
 */
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class OssUploadAspect {

    private final AliyunOssUtil aliyunOssUtil;

    /**
     * 环绕通知：拦截带有@OssUpload注解的方法
     */
    @Around("@annotation(com.br.common.annotation.OssUpload)")
    public Object handleFileUpload(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // 获取注解
        OssUpload ossUpload = method.getAnnotation(OssUpload.class);

        // 获取方法参数
        Object[] args = joinPoint.getArgs();

        // 查找MultipartFile参数
        MultipartFile file = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof MultipartFile) {
                file = (MultipartFile) args[i];
                break;
            }
        }

        if (file == null) {
            log.warn("方法 {} 标记了@OssUpload注解，但没有找到MultipartFile参数", method.getName());
            return joinPoint.proceed();
        }

        // 只验证文件，不上传（上传由Service层处理）
        validateFile(file, ossUpload);

        log.info("文件验证通过: {}", file.getOriginalFilename());

        // 继续执行原方法
        return joinPoint.proceed();
    }

    /**
     * 验证文件
     */
    private void validateFile(MultipartFile file, OssUpload ossUpload) {
        if (file.isEmpty()) {
            throw new BadRequestException(SystemConstants.FILE_EMPTY);
        }

        // 验证文件大小
        if (file.getSize() > ossUpload.maxSize()) {
            long maxSizeMB = ossUpload.maxSize() / 1024 / 1024;
            throw new BadRequestException("文件大小不能超过 " + maxSizeMB + "MB");
        }

        // 验证文件类型
        String[] allowedTypes = ossUpload.allowedTypes();
        if (allowedTypes.length > 0) {
            String contentType = file.getContentType();
            boolean isAllowed = Arrays.asList(allowedTypes).contains(contentType);
            if (!isAllowed) {
                throw new BadRequestException("不支持的文件类型: " + contentType);
            }
        }
    }
}
