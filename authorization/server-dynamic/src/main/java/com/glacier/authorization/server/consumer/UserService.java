package com.glacier.authorization.server.consumer;

import com.glacier.authorization.server.entity.vo.UserDetailsVo;
import com.glacier.common.core.entity.vo.HttpResult;
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
@FeignClient(value = "sys", path = "/user")
public interface UserService {

    /**
     * 根据用户唯一标识获取用户信息
     *
     * @param username
     * @return
     */
    @GetMapping(value = "/{username}")
    HttpResult<UserDetailsVo> findByUsername(@PathVariable("username") String username);
}
