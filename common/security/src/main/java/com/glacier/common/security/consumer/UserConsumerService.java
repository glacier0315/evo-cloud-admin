package com.glacier.common.security.consumer;

import com.glacier.common.core.constant.ServiceNameConstants;
import com.glacier.common.core.entity.Result;
import com.glacier.common.core.entity.dto.vo.UserDetailsDto;
import com.glacier.common.security.consumer.fallback.UserConsumerServiceFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户业务层
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-04 21:50
 */
@FeignClient(value = ServiceNameConstants.SYS_SERVICE, path = "/oauth", fallback = UserConsumerServiceFallbackImpl.class)
public interface UserConsumerService {

    /**
     * 根据用户唯一标识获取用户信息
     *
     * @param username
     * @return
     */
    @GetMapping(value = "/user")
    Result<UserDetailsDto> findByUsername(@RequestParam String username);
}
