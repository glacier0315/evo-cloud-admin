package com.glacier.authorization.server.service.impl;

import com.glacier.authorization.server.entity.OauthClientDetails;
import com.glacier.authorization.server.mapper.OauthClientDetailsMapper;
import com.glacier.authorization.server.service.OauthClientDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-07-26 16:22
 */
@Service(value = "oauthClientDetailsService")
public class OauthClientDetailsServiceImpl implements OauthClientDetailsService {
    private static final Logger log = LoggerFactory.getLogger(OauthClientDetailsServiceImpl.class);
    private final OauthClientDetailsMapper oauthClientDetailsMapper;

    @Autowired
    public OauthClientDetailsServiceImpl(OauthClientDetailsMapper oauthClientDetailsMapper) {
        this.oauthClientDetailsMapper = oauthClientDetailsMapper;
    }

    /**
     * 查询所有客户端
     *
     * @return
     */
    @Override
    public List<OauthClientDetails> findAll() {
        return this.oauthClientDetailsMapper.findAll();
    }

}
