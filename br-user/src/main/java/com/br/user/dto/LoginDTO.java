package com.br.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 用户登录DTO
 */
@Data
@Schema(description = "用户登录DTO")
public class LoginDTO {
    
    @NotBlank(message = "用户名不能为空")
    @Schema(description = "用户名")
    private String username;
    
    @NotBlank(message = "密码不能为空")
    @Schema(description = "密码")
    private String password;
}
