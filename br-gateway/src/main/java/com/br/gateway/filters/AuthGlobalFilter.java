package com.br.gateway.filters;

import lombok.RequiredArgsConstructor;

import cn.hutool.core.util.BooleanUtil;
import com.br.common.constants.RedisConstants;
import com.br.common.exception.UnauthorizedException;
import com.br.gateway.config.AuthProperties;
import com.br.gateway.util.JwtTool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.PathContainer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.pattern.PathPatternParser;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private final AuthProperties authProperties;
    private final JwtTool jwtTool;
    private final StringRedisTemplate stringRedisTemplate;
    @Value("${login.state-expiration:3600000}")
    private Long stateExpiration;

    private final PathPatternParser pathPatternParser = new PathPatternParser();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1.获取用户信息
        ServerHttpRequest request = exchange.getRequest();

        // 2.判断是否需要做登录拦截
        if (isExclude(request.getPath().toString())){
            // 放行
            return chain.filter(exchange);
        }

        // 3.获取token
        String token = null;
        List<String> authorization = request.getHeaders().get("Authorization");
        if (authorization != null && !authorization.isEmpty()) {
            token = authorization.get(0);
            // 去除Bearer前缀
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }
        }

        // 4.校验并解析token（一次解析，避免重复验签）
        JwtTool.Payload payload;
        try {
            payload = jwtTool.parseToken(token);
        } catch (UnauthorizedException e) {
            // 拦截，设置响应状态码
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        Long userId = payload.userId();
        Integer role = payload.role();
        String jti = payload.jti();

        // Redis中没有登录态时，说明该JWT已退出登录或被强制失效
        if (jti == null || !refreshLoginState(jti)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }


        // 5.传递用户信息
        ServerHttpRequest shr = exchange.getRequest().mutate()
                .header("user-id", userId.toString())
                .header("user-role", String.valueOf(role))
                .header("jti", jti)
                .build();

        ServerWebExchange swe = exchange.mutate().request(shr).build();

        // 6.放行
        return chain.filter(swe);
    }

    private boolean refreshLoginState(String jti) {
        String key = RedisConstants.LOGIN_TOKEN_KEY + jti;
        Boolean exists = stringRedisTemplate.hasKey(key);
        if (!BooleanUtil.isTrue(exists)) {
            return false;
        }
        stringRedisTemplate.expire(key, stateExpiration, TimeUnit.MILLISECONDS);
        return true;
    }

    private boolean isExclude(String path) {
        PathContainer pathContainer = PathContainer.parsePath(path);
        for (String pattern : authProperties.getExcludePaths()) {
            if (pathPatternParser.parse(pattern).matches(pathContainer)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
