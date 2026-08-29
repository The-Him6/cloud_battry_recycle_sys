package com.br.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * 用户注册DTO
 */
@Data
@Schema(description = "用户注册DTO")
public class RegisterDTO {
    
    @NotBlank(message = "用户名不能为空")
    @Schema(description = "用户名")
    private String username;
    
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^[A-Za-z0-9.!]{6,20}$", message = "密码只能包含大小写字母、数字和 . !，长度6-20位")
    @Schema(description = "密码")
    private String password;
    
    @Schema(description = "昵称")
    private String nickname;
    
    @NotBlank(message = "手机号不能为空")
    @Schema(description = "手机号")
    private String phone;
    
    @Schema(description = "邮箱")
    private String email;
}
