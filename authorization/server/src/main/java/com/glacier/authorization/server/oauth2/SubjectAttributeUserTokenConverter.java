package com.glacier.authorization.server.oauth2;

import com.glacier.common.core.constant.CommonConstant;
import com.glacier.common.security.entity.dto.UserDetailsVo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 扩展jwt
 *
 * @author glacier
 * @version 1.0
 * @date 2020-08-20 09:47
 */
public class SubjectAttributeUserTokenConverter extends DefaultUserAuthenticationConverter {

    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put(CommonConstant.OAUTH_USERNAME, authentication.getName());
        // 添加用户id
        if (authentication.getPrincipal() instanceof UserDetailsVo) {
            UserDetailsVo userDetailsVo = (UserDetailsVo) authentication.getPrincipal();
            response.put(CommonConstant.OAUTH_USER_ID, userDetailsVo.getUserId());
        }
        if (authentication.getAuthorities() != null
                && !authentication.getAuthorities().isEmpty()) {
            response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
        }
        return response;
    }
}
