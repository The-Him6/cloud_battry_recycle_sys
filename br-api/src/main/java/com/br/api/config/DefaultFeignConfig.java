package com.br.api.config;

import com.br.api.client.fallback.UserClientFallbackFactory;
import com.br.api.client.fallback.UserPointsFallbackFactory;
import com.br.common.utils.UserContext;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class DefaultFeignConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor userInfoRequestInterceptor() {
        return template -> {
            Long userId = UserContext.getUserId();
            Integer role = UserContext.getRole();
            String jti = UserContext.getJti();
            if (userId != null) {
                template.header("user-id", userId.toString());
                if (role != null) template.header("user-role", role.toString());
                if (jti != null) template.header("jti", jti);
            }
        };
    }

    @Bean
    public UserPointsFallbackFactory userPointsFallbackFactory(){
        return new UserPointsFallbackFactory();
    }

    @Bean
    public UserClientFallbackFactory userClientFallbackFactory(){
        return new UserClientFallbackFactory();
    }
}