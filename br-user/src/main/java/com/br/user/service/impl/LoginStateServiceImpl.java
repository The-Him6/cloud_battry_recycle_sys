package com.br.user.service.impl;

import cn.hutool.core.util.BooleanUtil;
import com.br.common.constants.RedisConstants;
import com.br.user.service.ILoginStateService;
import com.br.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 登录态服务类
 */
@Service("loginStateService")
@RequiredArgsConstructor
public class LoginStateServiceImpl implements ILoginStateService {

    private final StringRedisTemplate stringRedisTemplate;

    @Value("${login.state-expiration:3600000}")
    private Long stateExpiration;

    /**
     * 登录成功后把JWT的jti保存到Redis，支持主动退出、强制失效和1小时滑动过期
     */
    public void saveLoginState(String jti, UserVO userVO) {
        String key = RedisConstants.LOGIN_TOKEN_KEY + jti;
        Map<String, String> userMap = new HashMap<>();
        userMap.put("id", String.valueOf(userVO.getId()));
        userMap.put("username", userVO.getUsername());
        userMap.put("nickname", userVO.getNickname() == null ? "" : userVO.getNickname());
        userMap.put("role", String.valueOf(userVO.getRole()));
        stringRedisTemplate.opsForHash().putAll(key, userMap);
        stringRedisTemplate.expire(key, stateExpiration, TimeUnit.MILLISECONDS);
    }

    /**
     * 每次请求刷新登录态TTL；如果Redis中不存在jti，说明该Token已经失效
     */
    public boolean refreshLoginState(String jti) {
        String key = RedisConstants.LOGIN_TOKEN_KEY + jti;
        Boolean exists = stringRedisTemplate.hasKey(key);
        if (!BooleanUtil.isTrue(exists)) {
            return false;
        }
        stringRedisTemplate.expire(key, stateExpiration, TimeUnit.MILLISECONDS);
        return true;
    }

    /**
     * 退出登录时删除Redis登录态
     */
    public void removeLoginState(String jti) {
        stringRedisTemplate.delete(RedisConstants.LOGIN_TOKEN_KEY + jti);
    }
}
