package com.br.user.service;

import com.br.user.dto.ForgotPasswordDTO;
import com.br.user.dto.LoginDTO;
import com.br.user.dto.RegisterDTO;
import com.br.user.vo.LoginVO;

/**
 * 认证服务接口。
 */
public interface IAuthService {

    void register(RegisterDTO dto);

    LoginVO login(LoginDTO dto);

    void logout(String jti);

    void forgotPassword(ForgotPasswordDTO dto);
}
