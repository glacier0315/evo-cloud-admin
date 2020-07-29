package com.glacier.authorization.server.consumer.fallback;

import com.glacier.authorization.server.consumer.UserService;
import com.glacier.authorization.server.entity.vo.UserDetailsVo;
import com.glacier.common.core.entity.vo.HttpResult;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-07-29 20:46
 */
public class UserServiceFallbackImpl implements UserService {

    @Override
    public HttpResult<UserDetailsVo> findByUsername(String username) {
        return HttpResult.error("服务降级了！");
    }
}
