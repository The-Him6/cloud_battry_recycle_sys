package com.br.common.utils;

import com.br.common.constants.RedisConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Redis缓存工具类
 */
@Slf4j
public class CacheClient {

    public CacheClient(StringRedisTemplate stringRedisTemplate, RedissonClient redissonClient, ObjectMapper objectMapper) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.redissonClient = redissonClient;
        this.objectMapper = objectMapper;
    }
    private final StringRedisTemplate stringRedisTemplate;
    private final RedissonClient redissonClient;
    private final ObjectMapper objectMapper;

    /**
     * 写入普通缓存
     */
    public void set(String key, Object value, Long time, TimeUnit unit) {
        try {
            stringRedisTemplate.opsForValue().set(key, objectMapper.writeValueAsString(value), time, unit);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("缓存序列化失败", e);
        }
    }

    /**
     * 查询缓存并用空值解决缓存穿透
     */
    public <R, ID> R queryWithPassThrough(String keyPrefix, ID id, Class<R> type,
                                          Function<ID, R> dbFallback, Long time, TimeUnit unit) {
        String key = keyPrefix + id;
        String json = stringRedisTemplate.opsForValue().get(key);
        if (json != null && !json.isEmpty()) {
            return readValue(json, type);
        }
        if (json != null) {
            return null;
        }

        R result = dbFallback.apply(id);
        if (result == null) {
            stringRedisTemplate.opsForValue().set(key, "", RedisConstants.CACHE_NULL_TTL, TimeUnit.MINUTES);
            return null;
        }

        set(key, result, time, unit);
        return result;
    }

    /**
     * 查询缓存并用Redisson互斥锁解决缓存击穿
     */
    public <R, ID> R queryWithMutex(String keyPrefix, ID id, Class<R> type,
                                    Function<ID, R> dbFallback, Long time, TimeUnit unit) {
        String key = keyPrefix + id;
        String json = stringRedisTemplate.opsForValue().get(key);
        if (json != null && !json.isEmpty()) {
            return readValue(json, type);
        }
        if (json != null) {
            return null;
        }

        String lockKey = RedisConstants.LOCK_CACHE_KEY + key;
        RLock lock = redissonClient.getLock(lockKey);
        try {
            if (!lock.tryLock(1, 10, TimeUnit.SECONDS)) {
                Thread.sleep(50);
                return queryWithMutex(keyPrefix, id, type, dbFallback, time, unit);
            }
            String latestJson = stringRedisTemplate.opsForValue().get(key);
            if (latestJson != null && !latestJson.isEmpty()) {
                return readValue(latestJson, type);
            }
            if (latestJson != null) {
                return null;
            }
            R result = dbFallback.apply(id);
            if (result == null) {
                stringRedisTemplate.opsForValue().set(key, "", RedisConstants.CACHE_NULL_TTL, TimeUnit.MINUTES);
                return null;
            }
            set(key, result, time, unit);
            return result;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return dbFallback.apply(id);
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    /**
     * 反序列化缓存数据
     */
    private <R> R readValue(String json, Class<R> type) {
        try {
            return objectMapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("缓存反序列化失败", e);
        }
    }
}
