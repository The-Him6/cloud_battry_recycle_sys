package com.br.common.utils;

import com.br.common.constants.SystemConstants;
import com.br.common.exception.ForbiddenException;
import com.br.common.exception.UnauthorizedException;

/**
 * 登录用户工具类
 */
public class AuthUtil {

    /**
     * 从ThreadLocal获取当前用户ID
     */
    public static Long getUserId() {
        getCurrentUser();
        return UserContext.getUserId();
    }

    /**
     * 从ThreadLocal获取当前用户角色
     */
    public static Integer getRole() {
        return UserContext.getRole();
    }

    /**
     * 从ThreadLocal获取当前JWT编号
     */
    public static String getJti() {
        return UserContext.getJti();
    }

    /**
     * 校验当前用户是否是管理员
     */
    public static void requireAdmin() {
        if (!SystemConstants.ROLE_ADMIN.equals(getRole())) {
            throw new ForbiddenException(SystemConstants.ADMIN_ONLY);
        }
    }

    /**
     * 获取当前登录用户上下文
     */
    public static UserContext.UserInfo getCurrentUser() {
        UserContext.UserInfo userInfo = UserContext.get();
        if (userInfo == null) {
            throw new UnauthorizedException(SystemConstants.TOKEN_INVALID);
        }
        return userInfo;
    }

}
