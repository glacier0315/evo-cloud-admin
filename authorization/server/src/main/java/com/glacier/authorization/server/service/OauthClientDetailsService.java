package com.glacier.authorization.server.service;

import com.glacier.authorization.server.entity.OauthClientDetails;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * date 2020-07-26 16:21
 */
public interface OauthClientDetailsService {

    /**
     * 查询所有客户端
     *
     * @return 所有客户端
     */
    List<OauthClientDetails> findAll();
}
