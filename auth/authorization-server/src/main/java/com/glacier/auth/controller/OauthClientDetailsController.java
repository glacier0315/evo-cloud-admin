package com.glacier.auth.controller;

import com.glacier.auth.entity.pojo.OauthClientDetails;
import com.glacier.auth.service.OauthClientDetailsService;
import com.glacier.common.core.entity.vo.HttpResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-07-26 16:21
 */
@Slf4j
@RestController
@RequestMapping("/client")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OauthClientDetailsController {
    private final OauthClientDetailsService clientDetailsService;

    @GetMapping(value = "/list")
    public HttpResult<List<OauthClientDetails>> list() {
        return HttpResult.ok(this.clientDetailsService.findAll());
    }
}
