package com.br.common.annotation;

import java.lang.annotation.*;

/**
 * 文件上传注解
 * 标记在需要上传文件到阿里云OSS的方法上
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OssUpload {

    /**
     * 文件存储路径前缀
     */
    String path() default "";

    /**
     * 是否允许覆盖同名文件
     */
    boolean allowOverwrite() default false;

    /**
     * 允许的文件类型
     * 例如: {"image/jpeg", "image/png"}
     * 空数组表示不限制
     */
    String[] allowedTypes() default {};

    /**
     * 最大文件大小
     * 默认2MB
     */
    long maxSize() default 2 * 1024 * 1024;
}
