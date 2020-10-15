package com.glacier.authorization.resource.service.impl;

import com.glacier.authorization.resource.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色业务层
 *
 * @author glacier
 * @version 1.0
 * @date 2020-05-21 17:44
 */
@Transactional(readOnly = true)
@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {
    private static final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);
}
