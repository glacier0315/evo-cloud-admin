package com.glacier.authorization.server.controller;

import com.glacier.authorization.server.entity.OauthClientDetails;
import com.glacier.authorization.server.service.OauthClientDetailsService;
import com.glacier.common.core.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RestController
@RequestMapping("/client")
public class OauthClientDetailsController {
    private static final Logger log = LoggerFactory.getLogger(OauthClientDetailsController.class);
    private final OauthClientDetailsService clientDetailsService;

    @Autowired
    public OauthClientDetailsController(OauthClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }

    @GetMapping(value = "/list")
    public Result<List<OauthClientDetails>> list() {
        return Result.ok(this.clientDetailsService.findAll());
    }
}
