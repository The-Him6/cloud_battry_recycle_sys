package com.br.api.client;

import com.br.api.client.fallback.UserPointsFallbackFactory;
import com.br.api.dto.UserPointsDTO;
import com.br.common.domain.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "br-points", fallbackFactory = UserPointsFallbackFactory.class)
public interface UserPointsClient {

    @PostMapping("/points/add")
    Result<Boolean> add(@RequestParam Long userId, @RequestParam Integer points);

    @PostMapping("/points/deduct")
    Result<Boolean> deduct(@RequestParam Long userId, @RequestParam Integer points);

    @GetMapping("/points/{userId}")
    Result<UserPointsDTO> getByUserId(@PathVariable Long userId);
}
