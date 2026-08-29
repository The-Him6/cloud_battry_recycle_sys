package com.br.api.client;

import com.br.api.dto.UserInfoDTO;
import com.br.common.domain.Result;
import com.br.api.client.fallback.UserClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "br-user", fallbackFactory = UserClientFallbackFactory.class)
public interface UserClient {

    @GetMapping("/user/count")
    Result<Long> count();

    @GetMapping("/user/{id}")
    Result<UserInfoDTO> getById(@PathVariable Long id);

}
