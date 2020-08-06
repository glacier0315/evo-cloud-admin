package com.glacier.authorization.server.mapper;

import com.glacier.authorization.server.entity.pojo.OauthClientDetails;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-07-26 16:19
 */
public interface OauthClientDetailsMapper {

    /**
     * 查询所有客户端
     *
     * @return
     */
    List<OauthClientDetails> findAll();
}
