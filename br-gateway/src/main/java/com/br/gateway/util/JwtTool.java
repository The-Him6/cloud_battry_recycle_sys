package com.br.gateway.util;

import com.br.common.exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * JWT 工具类（校验失败直接抛异常，不做返回值判断）。
 */
@Component
public class JwtTool {

    private final SecretKey key;
    private final Long expiration;

    public JwtTool(@Value("${jwt.secret}") String secret,
                   @Value("${jwt.expiration}") Long expiration) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expiration = expiration;
    }

    /**
     * 创建带 jti 的 token（与 br-user 签发格式一致）
     */
    public String createToken(Long userId, Integer role) {
        return createToken(userId, role, UUID.randomUUID().toString());
    }

    /**
     * 创建带 jti 的 token，jti 用于把 JWT 与 Redis 登录态绑定
     */
    public String createToken(Long userId, Integer role, String jti) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("role", role);
        claims.put("jti", jti);
        Date now = new Date();
        return Jwts.builder()
                .claims(claims)
                .id(jti)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + expiration))
                .signWith(key)
                .compact();
    }

    /**
     * 解析 token，返回登录用户信息；token 为空、无效或过期直接抛 UnauthorizedException。
     */
    public Payload parseToken(String token) {
        // 1. 校验 token 是否为空
        if (token == null || token.isBlank()) {
            throw new UnauthorizedException("未登录");
        }
        // 2. 去除 Bearer 前缀
        String t = token.startsWith("Bearer ") ? token.substring(7) : token;
        // 3. 解析并验签（无效/过期直接抛异常）
        Claims claims;
        try {
            claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(t).getPayload();
        } catch (ExpiredJwtException e) {
            throw new UnauthorizedException("token已经过期");
        } catch (Exception e) {
            throw new UnauthorizedException("无效的token");
        }
        // 4. 校验必要字段
        Long userId = claims.get("userId", Long.class);
        Integer role = claims.get("role", Integer.class);
        String jti = claims.get("jti", String.class);
        if (jti == null) {
            jti = claims.getId();
        }
        if (userId == null || jti == null) {
            throw new UnauthorizedException("无效的token");
        }
        // 5. 返回登录用户信息
        return new Payload(userId, role, jti);
    }

    // ====== 兼容方法（委托 parseToken，旧调用无需改） ======

    public Long getUserId(String token) {
        return parseToken(token).userId();
    }

    public Integer getRole(String token) {
        return parseToken(token).role();
    }

    public String getJti(String token) {
        return parseToken(token).jti();
    }

    /**
     * 登录用户信息（userId/role/jti）
     */
    public record Payload(Long userId, Integer role, String jti) {
    }
}
