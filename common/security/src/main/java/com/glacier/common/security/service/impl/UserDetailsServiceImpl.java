package com.glacier.common.security.service.impl;


import com.glacier.common.core.entity.Result;
import com.glacier.common.core.entity.dto.vo.RoleDetailsDto;
import com.glacier.common.core.entity.dto.vo.UserDetailsDto;
import com.glacier.common.security.consumer.UserConsumerService;
import com.glacier.common.security.entity.dto.UserDetailsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 用户详细业务类
 *
 * @author glacier
 * @version 1.0
 * date 2019-09-30 10:15
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    private final UserConsumerService userService;

    @Autowired
    public UserDetailsServiceImpl(UserConsumerService userService) {
        this.userService = userService;
    }

    /**
     * 根据用户名查用户
     *
     * @param username 用户名
     * @return 用户信息
     * @throws UsernameNotFoundException 用户不存在异常
     */
    @Override
    public UserDetailsVo loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("load user by username :{}", username);
        Result<UserDetailsDto> result = this.userService.findByUsername(username);
        UserDetailsDto details = result.getData();
        if (Result.SUCCUSS.equals(result.getCode())) {
            UserDetailsVo userDetailsDto = new UserDetailsVo();
            userDetailsDto.setUserId(details.getId());
            userDetailsDto.setUsername(details.getUsername());
            userDetailsDto.setPassword(details.getPassword());
            userDetailsDto.setRoles(
                    Optional.ofNullable(details.getRoleDetailDtos())
                            .orElseGet(ArrayList::new)
                            .stream()
                            .map(RoleDetailsDto::getCode)
                            .collect(Collectors.toList()));
            userDetailsDto.setEnabled(true);
            userDetailsDto.setAccountNonExpired(true);
            userDetailsDto.setAccountNonLocked(true);
            userDetailsDto.setCredentialsNonExpired(true);
            return userDetailsDto;
        }
        throw new UsernameNotFoundException(result.getMsg());
    }
}
