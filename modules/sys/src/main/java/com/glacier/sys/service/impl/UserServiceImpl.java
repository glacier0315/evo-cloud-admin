package com.glacier.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.glacier.common.core.entity.dto.IdDto;
import com.glacier.common.core.page.PageRequest;
import com.glacier.common.core.page.PageResponse;
import com.glacier.sys.entity.User;
import com.glacier.sys.entity.UserRole;
import com.glacier.sys.mapper.UserMapper;
import com.glacier.sys.mapper.UserRoleMapper;
import com.glacier.sys.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author glacier
 * @version 1.0
 * @description 用户业务类
 * @date 2019-08-04 21:50
 */
@Slf4j
@Transactional(readOnly = true)
@Service(value = "UserService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRoleMapper userRoleMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String username) {
        return userMapper.selectOne(new QueryWrapper<>(User
                .builder()
                .username(username)
                .build()));
    }

    /**
     * 分页查找
     *
     * @param pageRequest
     * @return
     */
    @Override
    public PageResponse<User> findPage(PageRequest<User> pageRequest) {
        Page<User> page = userMapper.selectPage(new Page<>(pageRequest.getCurrent(), pageRequest.getSize()),
                new QueryWrapper<>(pageRequest.getParams()));
        return PageResponse.<User>builder()
                .current(page.getCurrent())
                .size(page.getSize())
                .total(page.getTotal())
                .records(page.getRecords())
                .build();
    }

    @Override
    public int update(User user) {
        int update = 0;
        if (user.getId() != null && !user.getId().isEmpty()) {
            // 对原始密码加密
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                // 加密密码
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            update = userMapper.updateById(user);
        }
        return update;
    }

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int saveUserRole(User user) {
        int update = 0;
        if (user.getId() != null && !user.getId().isEmpty()) {
            update = userMapper.updateById(user);
            // 清空原 用户角色关系
            this.deleteUserRoleByUserId(user.getId());
        } else {
            // 对原始密码加密
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                // 加密密码
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            update = userMapper.insert(user);

        }
        // 保存用户角色关系
        this.saveUserRole(user.getId(), user.getRoleIds());
        return update;
    }

    /**
     * 根据id批量删除
     *
     * @param idDtos
     * @return
     */
    @Transactional(rollbackFor = {})
    @Override
    public int batchDelete(List<IdDto> idDtos) {
        int update = 0;
        if (idDtos != null && !idDtos.isEmpty()) {
            List<String> list = idDtos.stream()
                    .map(IdDto::getId)
                    .collect(Collectors.toList());
            update = userMapper.deleteBatchIds(list);
            // 删除用户角色关系
            for (String userId : list) {
                this.deleteUserRoleByUserId(userId);
            }
        }
        return update;
    }

    /**
     * 保存用户角色关系
     *
     * @param userId
     * @param roleIds
     * @return
     */
    private int saveUserRole(final String userId, List<String> roleIds) {
        int update = 0;
        if (userId != null && !userId.isEmpty()
                && roleIds != null && !roleIds.isEmpty()) {
            // 保存用户角色关系
            for (String roleId : roleIds) {
                update += userRoleMapper.insert(UserRole.builder()
                        .userId(userId)
                        .roleId(roleId)
                        .build());
            }
        }
        return update;
    }

    /**
     * 删除用户角色关系
     *
     * @param userId
     * @return
     */
    private int deleteUserRoleByUserId(final String userId) {
        int update = 0;
        if (userId != null && !userId.isEmpty()) {
            update = userRoleMapper.delete(new UpdateWrapper<>(UserRole.builder().userId(userId).build()));
        }
        return update;
    }
}
