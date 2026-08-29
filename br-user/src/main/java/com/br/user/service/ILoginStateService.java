package com.br.user.service;


import com.br.user.vo.UserVO;

/**
 * Redis 登录态服务接口。
 */
public interface ILoginStateService {

    void saveLoginState(String jti, UserVO userVO);

    boolean refreshLoginState(String jti);

    void removeLoginState(String jti);
}
