package com.br.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 网关鉴权配置：无需登录即可访问的白名单。
 */
@Data
@Component
@ConfigurationProperties(prefix = "br.auth")
public class AuthProperties {

    /**
     * 白名单路径，默认登录/注册/忘记密码
     */
    private List<String> excludePaths;
}
