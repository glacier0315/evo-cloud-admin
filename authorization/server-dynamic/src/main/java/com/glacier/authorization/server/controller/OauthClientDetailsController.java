package com.glacier.authorization.server.controller;

import com.glacier.authorization.server.entity.pojo.OauthClientDetails;
import com.glacier.authorization.server.service.OauthClientDetailsService;
import com.glacier.common.core.entity.vo.Result;
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
    public Result<List<OauthClientDetails>> list() {
        return Result.ok(this.clientDetailsService.findAll());
    }
}
