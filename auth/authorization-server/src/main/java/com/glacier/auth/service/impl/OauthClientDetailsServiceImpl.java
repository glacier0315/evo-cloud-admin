package com.glacier.auth.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glacier.auth.constant.Constant;
import com.glacier.auth.entity.pojo.OauthClientDetails;
import com.glacier.auth.mapper.OauthClientDetailsMapper;
import com.glacier.auth.service.OauthClientDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-07-26 16:22
 */
@Slf4j
@DS(Constant.DATASOURCE_AUTH)
@Service(value = "oauthClientDetailsService")
public class OauthClientDetailsServiceImpl extends ServiceImpl<OauthClientDetailsMapper, OauthClientDetails> implements OauthClientDetailsService {

    /**
     * 查询所有客户端
     *
     * @return
     */
    @Override
    public List<OauthClientDetails> findAll() {
        return this.baseMapper.selectList(new QueryWrapper<>(null));
    }

}
