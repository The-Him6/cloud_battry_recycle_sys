package com.br.common.config;

import com.br.common.utils.CacheClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 缓存工具自动装配配置。
 * 只有同时具备 Redisson 与 StringRedisTemplate 依赖的服务才会注册 CacheClient，
 * br-common 将二者声明为 provided，不会透传给依赖方。
 */
@Configuration
@ConditionalOnClass({RedissonClient.class, StringRedisTemplate.class})
public class CacheAutoConfiguration {

    @Bean
    public CacheClient cacheClient(StringRedisTemplate stringRedisTemplate, RedissonClient redissonClient, ObjectMapper objectMapper) {
        return new CacheClient(stringRedisTemplate, redissonClient, objectMapper);
    }
}