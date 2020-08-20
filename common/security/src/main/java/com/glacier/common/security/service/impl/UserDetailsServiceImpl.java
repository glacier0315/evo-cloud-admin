package com.glacier.common.security.service.impl;


import com.glacier.common.core.entity.Result;
import com.glacier.common.core.entity.dto.vo.RoleDetailsDto;
import com.glacier.common.core.entity.dto.vo.UserDetailsDto;
import com.glacier.common.security.consumer.UserConsumerService;
import com.glacier.common.security.entity.dto.UserDetailsVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
 * @date 2019-09-30 10:15
 */
@Slf4j
@Service("userDetailsService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserConsumerService userService;

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
        Result<UserDetailsDto> result = this.userService.findByUsername(username);
        UserDetailsDto details = result.getData();
        if (Result.SUCCUSS.equals(result.getCode())
                && details != null
                && details.getId() != null
                && !details.getId().isEmpty()) {
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
        } else {
            throw new UsernameNotFoundException("用户名或者密码不正确！");
        }
    }
}
