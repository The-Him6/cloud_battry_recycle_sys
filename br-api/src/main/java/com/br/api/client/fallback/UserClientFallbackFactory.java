package com.br.api.client.fallback;

import com.br.api.client.UserClient;
import com.br.api.dto.UserInfoDTO;
import com.br.common.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

@Slf4j
public class UserClientFallbackFactory implements FallbackFactory<UserClient> {
    @Override
    public UserClient create(Throwable cause) {
        return new UserClient() {
            @Override
            public Result<Long> count() {
                log.error("用户服务不可用，获取用户总数失败，原因：{}", cause.getMessage());
                return Result.error(500, "用户服务不可用");
            }

            @Override
            public Result<UserInfoDTO> getById(Long id) {
                log.error("用户服务不可用，获取用户信息失败，userId：{}，原因：{}", id, cause.getMessage());
                return Result.error(500, "用户服务不可用");
            }
        };
    }
}
