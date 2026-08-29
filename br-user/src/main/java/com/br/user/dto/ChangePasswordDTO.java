package com.br.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * 修改密码DTO
 */
@Data
@Schema(description = "修改密码DTO")
public class ChangePasswordDTO {

    @NotBlank(message = "原密码不能为空")
    @Schema(description = "原密码")
    private String oldPassword;

    @NotBlank(message = "新密码不能为空")
    @Pattern(regexp = "^[A-Za-z0-9.!]{6,20}$", message = "密码只能包含大小写字母、数字和 . !，长度6-20位")
    @Schema(description = "新密码")
    private String newPassword;
}




