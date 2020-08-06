package com.glacier.authorization.server.entity.pojo;

import lombok.*;

import java.io.Serializable;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-07-26 16:15
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class OauthClientDetails implements Serializable {
    private static final long serialVersionUID = 3719213115388663432L;
    /**
     * 主键
     */
    private String clientId;
    private String resourceIds;
    private String clientSecret;
    private String scope;
    private String authorizedGrantTypes;
    private String webServerRedirectUri;
    private String authorities;
    private Integer accessTokenValidity;
    private Integer refreshTokenValidity;
    private String additionalInformation;
    private String autoapprove;
}
