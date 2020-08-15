package com.glacier.authorization.server.consumer.fallback;

import com.glacier.authorization.server.consumer.UserService;
import com.glacier.common.core.entity.Result;
import com.glacier.common.core.entity.dto.vo.UserDetailsDto;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-07-29 20:46
 */
public class UserServiceFallbackImpl implements UserService {

    @Override
    public Result<UserDetailsDto> findByUsername(String username) {
        return Result.error("服务降级了！");
    }
}
