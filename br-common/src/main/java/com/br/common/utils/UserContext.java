package com.br.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 当前登录用户ThreadLocal工具类。
 */
public class UserContext {

    /**
     * 登录用户信息
     */
    @Data
    @AllArgsConstructor
    public static class UserInfo {
        private Long userId;
        private Integer role;
        private String jti;
    }

    /**
     * ThreadLocal保存一次请求内的登录用户信息。
     */
    private static final ThreadLocal<UserInfo> TL = new ThreadLocal<>();

    /**
     * 保存当前请求用户信息。
     */
    public static void save(UserInfo userInfo) {
        TL.set(userInfo);
    }

    /**
     * 获取当前请求用户信息。
     */
    public static UserInfo get() {
        return TL.get();
    }

    /**
     * 获取当前请求用户ID
     */
    public static Long getUserId() {
        UserInfo info = TL.get();
        return info == null ? null : info.getUserId();
    }

    /**
     * 获取当前请求用户角色
     */
    public static Integer getRole() {
        UserInfo info = TL.get();
        return info == null ? null : info.getRole();
    }

    /**
     * 获取当前请求用户JWT编号
     */
    public static String getJti() {
        UserInfo info = TL.get();
        return info == null ? null : info.getJti();
    }
    /**
     * 清理当前线程用户信息，避免Tomcat线程复用导致用户信息串请求。
     */
    public static void remove() {
        TL.remove();
    }
}
