package com.glacier.auth.service.impl;

import com.glacier.auth.mapper.RoleMapper;
import com.glacier.auth.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-05-21 17:10
 */
@Slf4j
@Transactional(readOnly = true)
@Service(value = "roleService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleServiceImpl implements RoleService {
    private final RoleMapper roleMapper;

    @Override
    public List<String> findCodeByUserId(String userId) {
        return roleMapper.findCodeByUserId(userId);
    }
}
