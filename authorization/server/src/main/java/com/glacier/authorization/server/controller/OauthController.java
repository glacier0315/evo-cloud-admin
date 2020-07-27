package com.glacier.authorization.server.controller;

import com.glacier.common.core.entity.vo.HttpResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

/**
 * 定制token
 *
 * @author glacier
 * @version 1.0
 * @date 2020-07-24 12:47
 */
@Slf4j
@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OauthController {

    private final TokenEndpoint tokenEndpoint;

    @GetMapping("/token")
    public HttpResult<OAuth2AccessToken> getAccessToken(
            Principal principal,
            @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        return HttpResult.ok(
                this.tokenEndpoint.getAccessToken(principal, parameters)
                        .getBody());
    }

    @PostMapping("/token")
    public HttpResult<OAuth2AccessToken> postAccessToken(
            Principal principal,
            @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        return HttpResult.ok(
                this.tokenEndpoint.postAccessToken(principal, parameters)
                        .getBody());
    }
}
