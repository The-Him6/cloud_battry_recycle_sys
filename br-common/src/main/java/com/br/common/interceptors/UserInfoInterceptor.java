package com.br.common.interceptors;

import cn.hutool.core.util.StrUtil;
import com.br.common.utils.UserContext;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserInfoInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 1.获取登录用户信息
        String userId = request.getHeader("user-id");
        String role = request.getHeader("user-role");
        String jti = request.getHeader("jti");

        // 2.判断是否获取了用户，如果有，存入ThreadLocal
        if (StrUtil.isNotBlank(userId)) {
            UserContext.save(new UserContext.UserInfo(
                    Long.valueOf(userId),
                    StrUtil.isNotBlank(role) ? Integer.valueOf(role) : null,
                    StrUtil.isNotBlank(jti) ? jti : null
            ));
        }
        // 3.放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        UserContext.remove();
    }
}