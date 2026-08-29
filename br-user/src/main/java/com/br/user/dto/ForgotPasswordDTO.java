package com.br.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 忘记密码DTO
 */
@Data
@Schema(description = "忘记密码DTO")
public class ForgotPasswordDTO {

    @NotBlank(message = "用户名不能为空")
    @Schema(description = "用户名")
    private String username;

    @NotBlank(message = "手机号不能为空")
    @Schema(description = "手机号")
    private String phone;
}




