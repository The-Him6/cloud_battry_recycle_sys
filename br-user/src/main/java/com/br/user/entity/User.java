package com.br.user.entity;

import io.swagger.v3.oas.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户实体类
 */
@Data
@Schema(description = "用户实体")
public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private Long id;
    
    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String username;
    
    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;
    
    /**
     * 昵称
     */
    @Schema(description = "昵称")
    private String nickname;
    
    /**
     * 手机号
     */
    @Schema(description = "手机号")
    private String phone;
    
    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;
    
    /**
     * 头像地址
     */
    @Schema(description = "头像地址")
    private String avatar;
    
    /**
     * 角色：0-普通用户，1-管理员
     */
    @Schema(description = "角色：0-普通用户，1-管理员")
    private Integer role;
    
    /**
     * 状态：0-禁用，1-正常
     */
    @Schema(description = "状态：0-禁用，1-正常")
    private Integer status;
    
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
