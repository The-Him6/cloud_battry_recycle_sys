package com.br.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录响应VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "登录响应VO")
public class LoginVO {
    
    /**
     * JWT Token
     */
    @Schema(description = "JWT Token")
    private String token;
    
    /**
     * 用户信息
     */
    @Schema(description = "用户信息")
    private UserVO userInfo;
}
