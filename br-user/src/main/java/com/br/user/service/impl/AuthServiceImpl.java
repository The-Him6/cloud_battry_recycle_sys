package com.br.user.service.impl;

import cn.hutool.core.lang.UUID;
import com.br.common.constants.SystemConstants;
import com.br.common.exception.BadRequestException;
import com.br.common.exception.DbException;
import com.br.common.exception.UnauthorizedException;
import com.br.user.dto.ForgotPasswordDTO;
import com.br.user.dto.LoginDTO;
import com.br.user.dto.RegisterDTO;
import com.br.user.entity.User;
import com.br.user.mapper.UserMapper;
import com.br.user.service.IAuthService;
import com.br.user.service.ILoginStateService;
import com.br.user.util.JwtUtil;
import com.br.user.vo.LoginVO;
import com.br.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;


/**
 * 认证服务类
 */
@Service("authService")
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    
    private final UserMapper userMapper;

    private final JwtUtil jwtUtil;

    private final ILoginStateService loginStateService;
    
    /**
     * 用户注册
     */
    public void register(RegisterDTO dto) {
        // 检查用户名是否已存在
        User existUser = userMapper.getByUsername(dto.getUsername());
        if (existUser != null) {
            throw new BadRequestException(SystemConstants.USER_ALREADY_EXISTS);
        }
        
        // 创建用户
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(DigestUtils.md5DigestAsHex(dto.getPassword().getBytes(StandardCharsets.UTF_8)));
        user.setNickname(dto.getNickname() != null ? dto.getNickname() : dto.getUsername());
        user.setPhone(dto.getPhone());
        user.setEmail(dto.getEmail());
        user.setRole(SystemConstants.ROLE_USER);
        user.setStatus(SystemConstants.STATUS_NORMAL);
        
        userMapper.insert(user);
    }
    
    /**
     * 用户登录
     */
    public LoginVO login(LoginDTO dto) {
        // 查询用户
        User user = userMapper.getByUsername(dto.getUsername());
        if (user == null) {
            throw new UnauthorizedException(SystemConstants.USER_NOT_FOUND);
        }
        
        // 验证密码
        String encryptedPassword = DigestUtils.md5DigestAsHex(dto.getPassword().getBytes(StandardCharsets.UTF_8));
        if (!encryptedPassword.equals(user.getPassword())) {
            throw new UnauthorizedException(SystemConstants.USER_PASSWORD_ERROR);
        }
        
        // 检查用户状态
        if (user.getStatus().equals(SystemConstants.STATUS_DISABLED)) {
            throw new UnauthorizedException(SystemConstants.USER_DISABLED);
        }
        
        // 构建用户信息VO
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);

        // 生成带jti的Token，并把jti写入Redis登录态
        String jti = UUID.randomUUID().toString();
        String token = jwtUtil.generateToken(user.getId(), user.getRole(), jti);
        loginStateService.saveLoginState(jti, userVO);
        
        return new LoginVO(token, userVO);
    }

    /**
     * 用户退出登录
     */
    public void logout(String jti) {
        loginStateService.removeLoginState(jti);
    }

    /**
     * 忘记密码
     */
    public void forgotPassword(ForgotPasswordDTO dto) {
        User user = userMapper.getByUsername(dto.getUsername());
        if (user == null) {
            throw new BadRequestException(SystemConstants.USER_NOT_FOUND);
        }
        if (user.getPhone() == null || !user.getPhone().equals(dto.getPhone())) {
            throw new BadRequestException(SystemConstants.USER_PHONE_MISMATCH);
        }

        int updated = userMapper.resetPasswordByUsernameAndPhone(
                dto.getUsername(),
                dto.getPhone(),
                DigestUtils.md5DigestAsHex("123456".getBytes(StandardCharsets.UTF_8))
        );
        if (updated == 0) {
            throw new DbException(SystemConstants.OPERATION_FAILED);
        }
    }
}
