package com.glacier.authorization.server.consumer.fallback;

import com.glacier.authorization.server.consumer.UserService;
import com.glacier.common.core.entity.vo.Result;
import com.glacier.common.core.entity.vo.UserDetails;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-07-29 20:46
 */
public class UserServiceFallbackImpl implements UserService {

    @Override
    public Result<UserDetails> findByUsername(String username) {
        return Result.error("服务降级了！");
    }
}
