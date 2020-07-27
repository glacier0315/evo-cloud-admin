package com.glacier.authorization.server.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glacier.authorization.server.constant.Constant;
import com.glacier.authorization.server.entity.pojo.Role;
import com.glacier.authorization.server.mapper.RoleMapper;
import com.glacier.authorization.server.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色业务层
 *
 * @author glacier
 * @version 1.0
 * @date 2020-05-21 17:10
 */
@Slf4j
@DS(Constant.DATASOURCE_EBOOT_SYS)
@Service(value = "roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<String> findCodeByUserId(String userId) {
        return this.baseMapper.findCodeByUserId(userId);
    }
}
