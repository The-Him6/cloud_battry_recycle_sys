package com.br.api.client.fallback;

import com.br.api.client.UserPointsClient;
import com.br.api.dto.UserPointsDTO;
import com.br.common.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

@Slf4j
public class UserPointsFallbackFactory implements FallbackFactory<UserPointsClient> {
    @Override
    public UserPointsClient create(Throwable cause) {
        return new UserPointsClient() {
            @Override
            public Result<Boolean> add(Long userId, Integer points) {
                log.error("用户积分添加失败，userId：{}，points：{}，原因：{}", userId, points, cause.getMessage());
                return Result.error(500, "积分服务不可用，请重试");
            }

            @Override
            public Result<Boolean> deduct(Long userId, Integer points) {
                log.error("用户积分扣除失败，userId：{}，points：{}，原因：{}", userId, points, cause.getMessage());
                return Result.error(500, "积分服务不可用，请重试");
            }

            @Override
            public Result<UserPointsDTO> getByUserId(Long userId) {
                log.error("根据用户ID获取用户积分失败，userId：{}，原因：{}", userId, cause.getMessage());
                return Result.error(500, "积分服务不可用，请重试");
            }
        };
    }
}
