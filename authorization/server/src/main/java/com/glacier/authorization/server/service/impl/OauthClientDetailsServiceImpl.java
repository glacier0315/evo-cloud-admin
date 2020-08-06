package com.glacier.authorization.server.service.impl;

import com.glacier.authorization.server.entity.pojo.OauthClientDetails;
import com.glacier.authorization.server.mapper.OauthClientDetailsMapper;
import com.glacier.authorization.server.service.OauthClientDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-07-26 16:22
 */
@Slf4j
@Service(value = "oauthClientDetailsService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OauthClientDetailsServiceImpl implements OauthClientDetailsService {

    private final OauthClientDetailsMapper oauthClientDetailsMapper;

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
