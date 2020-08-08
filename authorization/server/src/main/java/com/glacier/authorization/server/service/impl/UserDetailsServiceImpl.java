package com.glacier.authorization.server.service.impl;


import com.glacier.authorization.server.consumer.UserService;
import com.glacier.authorization.server.entity.vo.UserDetailsVo;
import com.glacier.common.core.entity.vo.Result;
import com.glacier.common.core.entity.vo.RoleDetails;
import com.glacier.modules.sys.entity.vo.user.UserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 用户详细业务类
 *
 * @author glacier
 * @version 1.0
 * @date 2019-09-30 10:15
 */
@Slf4j
@Service("userDetailsService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    /**
     * 根据用户名查用户
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetailsVo loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("load user by username :{}", username);
        Result<UserDetails> result = this.userService.findByUsername(username);
        UserDetails details = result.getData();
        if (Result.SUCCUSS.equals(result.getCode())
                && details != null
                && details.getId() != null
                && !details.getId().isEmpty()) {
            UserDetailsVo userDetailsVo = new UserDetailsVo();
            userDetailsVo.setUserId(details.getId());
            userDetailsVo.setUsername(details.getUsername());
            userDetailsVo.setPassword(details.getPassword());
            userDetailsVo.setRoles(
                    Optional.ofNullable(details.getRoleDetails())
                            .orElse(Collections.emptyList())
                            .stream()
                            .map(RoleDetails::getCode)
                            .collect(Collectors.toList())
            );
            userDetailsVo.setEnabled(true);
            userDetailsVo.setAccountNonExpired(true);
            userDetailsVo.setAccountNonLocked(true);
            userDetailsVo.setCredentialsNonExpired(true);
            return userDetailsVo;
        } else {
            throw new UsernameNotFoundException("用户名或者密码不正确！");
        }
    }
}
