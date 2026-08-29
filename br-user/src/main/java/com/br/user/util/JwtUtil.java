package com.br.user.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT 工具类（仅用于签发 token，解析校验在网关）。
 */
@Component
public class JwtUtil {
    
    @Value("${jwt.secret}")
    private String secret;
    
    @Value("${jwt.expiration}")
    private Long expiration;
    
    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
    
    /**
     * 签发带 jti 的 token
     */
    public String generateToken(Long userId, Integer role, String jti) {
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
                .signWith(getSecretKey())
                .compact();
    }
}
