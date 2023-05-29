package com.wingliberty.gateway.filter;

import com.wingliberty.gateway.feign.AuthFeign;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author aiLun
 * @date 2023/5/20-16:29
 */

@Component
public class AuthFilter implements GlobalFilter {

    @Resource
    private AuthFeign authFeign;

    /**
     *
     * @param exchange 可以拿到request 和response
     * @param chain 过滤器链
     * @return 是否放行
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        List<String> authorization = exchange.getRequest().getHeaders().get("Authorization");

        if (CollectionUtils.isEmpty(authorization)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        String token = authorization.get(0);
        Object o = authFeign.checkToken(token);
        System.out.println(o);
        return chain.filter(exchange);
    }

}
