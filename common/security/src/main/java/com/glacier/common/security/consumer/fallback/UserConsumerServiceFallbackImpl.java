package com.glacier.common.security.consumer.fallback;

import com.glacier.common.core.entity.Result;
import com.glacier.common.core.entity.dto.vo.UserDetailsDto;
import com.glacier.common.security.consumer.UserConsumerService;

/**
 * @author glacier
 * @version 1.0
 * date 2020-07-29 20:46
 */
public class UserConsumerServiceFallbackImpl implements UserConsumerService {

    @Override
    public Result<UserDetailsDto> findByUsername(String username) {
        return Result.error("服务降级了！");
    }
}
