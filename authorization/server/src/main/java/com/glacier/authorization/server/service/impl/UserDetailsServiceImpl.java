package com.glacier.authorization.server.service.impl;


import com.glacier.authorization.server.consumer.UserService;
import com.glacier.authorization.server.entity.vo.UserDetailsVo;
import com.glacier.common.core.entity.vo.HttpResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("load user by username :{}", username);
        HttpResult<UserDetailsVo> result = this.userService.findByUsername(username);
        if (HttpResult.SUCCUSS.equals(result.getCode())
                && result.getData() != null
                && result.getData().getUserId() != null
                && !result.getData().getUserId().isEmpty()) {
            return result.getData();
        } else {
            throw new UsernameNotFoundException("用户名或者密码不正确！");
        }
    }
}
