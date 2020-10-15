package com.glacier.gateway.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 请求记录过滤器
 *
 * @author glacier
 * @version 1.0
 * date 2019-12-11 12:38
 */
@Component
public class RequestRecordFilter implements GlobalFilter, Ordered {
    private static final Logger log = LoggerFactory.getLogger(RequestRecordFilter.class);
    public static final String HTTP = "http";
    public static final String HTTPS = "https";
    public static final String WEBSOCKET = "websocket";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String scheme = request.getURI().getScheme();
        if (!HTTP.equals(scheme) && !HTTPS.equals(scheme)) {
            return chain.filter(exchange);
        }
        String upgrade = request.getHeaders().getUpgrade();
        if (WEBSOCKET.equalsIgnoreCase(upgrade)) {
            return chain.filter(exchange);
        }
        HttpHeaders headers = request.getHeaders();
        MultiValueMap<String, HttpCookie> cookies = request.getCookies();
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        Flux<DataBuffer> body = request.getBody();
        log.info("header: {}", headers);
        log.info("cookies: {}", headers);
        log.info("queryParams: {}", headers);
        log.info("body: {}", body);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }

    private Map<String, Object> decodeBody(String body) {
        return Arrays.stream(body.split("&"))
                .map(s -> s.split("="))
                .collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));
    }

    private String encodeBody(Map<String, Object> map) {
        return map.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).collect(Collectors.joining("&"));
    }
}
