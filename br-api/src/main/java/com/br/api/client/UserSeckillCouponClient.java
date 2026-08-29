package com.br.api.client;

import com.br.common.domain.Result;
import com.br.api.dto.UserSeckillCouponDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("br-seckill")
public interface UserSeckillCouponClient {
    @PostMapping("/seckill-coupon/validate")
    Result<UserSeckillCouponDTO> validate(@RequestParam Long couponId, @RequestParam Long userId);

    @PostMapping("/seckill-coupon/markUsed")
    Result<Boolean> markUsed(@RequestParam Long couponId,
                          @RequestParam Long productId,
                          @RequestParam Long exchangeRecordId);
}
