package com.glacier.authorization.server.service.impl;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.glacier.authorization.server.constant.Constant;
import com.glacier.authorization.server.entity.pojo.User;
import com.glacier.authorization.server.service.RoleService;
import com.glacier.authorization.server.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户详细业务类
 *
 * @author glacier
 * @version 1.0
 * @date 2019-09-30 10:15
 */
@Slf4j
@DS(Constant.DATASOURCE_EBOOT_SYS)
@Service("userDetailsService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;
    private final RoleService roleService;

    /**
     * 根据用户名查用户
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userService.getByUsername(username);
        if (user == null || user.getId() == null || user.getId().isEmpty()) {
            throw new UsernameNotFoundException("用户名或者密码不正确！");
        }
        log.info("load user by username :{}", user.toString());
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                this.obtainGrantedAuthorities(user)
        );
    }

    /**
     * 获得登录者所有角色的权限集合.
     *
     * @param user
     * @return
     */
    protected Set<GrantedAuthority> obtainGrantedAuthorities(User user) {
        // 查找角色
        List<String> authorityList = this.roleService.findCodeByUserId(user.getId());
        log.info("user: {}, roles: {}", user.getUsername(), authorityList);
        return authorityList.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }
}