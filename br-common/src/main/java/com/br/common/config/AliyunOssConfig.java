package com.br.common.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里云OSS配置类
 * 只有配置了 aliyun.oss 四项才启用，未配置的服务（如纯积分服务）自动跳过，不影响启动。
 */
@Configuration
@ConditionalOnProperty(prefix = "aliyun.oss",
        name = {"endpoint", "access-key-id", "access-key-secret", "bucket-name"})
public class AliyunOssConfig {
    
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    
    @Value("${aliyun.oss.access-key-id}")
    private String accessKeyId;
    
    @Value("${aliyun.oss.access-key-secret}")
    private String accessKeySecret;
    
    @Bean
    public OSS ossClient() {
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }
}
