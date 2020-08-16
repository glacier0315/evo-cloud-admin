package com.glacier.common.security.consumer;

import com.glacier.common.core.constant.ServiceNameConstants;
import com.glacier.common.core.entity.Result;
import com.glacier.common.security.consumer.fallback.UserServiceFallbackImpl;
import com.glacier.common.security.entity.dto.UserDetailsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 用户业务层
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-04 21:50
 */
@FeignClient(value = ServiceNameConstants.SYS_SERVICE, path = "/user", fallback = UserServiceFallbackImpl.class)
public interface UserService {

    /**
     * 根据用户唯一标识获取用户信息
     *
     * @param username
     * @return
     */
    @GetMapping(value = "/{username}")
    Result<UserDetailsDto> findByUsername(@PathVariable("username") String username);
}
