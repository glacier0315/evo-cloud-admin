package com.glacier.authorization.server.entity;

import java.io.Serializable;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-07-26 16:15
 */
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
    private String autoApprove;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getResourceIds() {
        return this.resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public String getClientSecret() {
        return this.clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getScope() {
        return this.scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getAuthorizedGrantTypes() {
        return this.authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public String getWebServerRedirectUri() {
        return this.webServerRedirectUri;
    }

    public void setWebServerRedirectUri(String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri;
    }

    public String getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public Integer getAccessTokenValidity() {
        return this.accessTokenValidity;
    }

    public void setAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    public Integer getRefreshTokenValidity() {
        return this.refreshTokenValidity;
    }

    public void setRefreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    public String getAdditionalInformation() {
        return this.additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getAutoApprove() {
        return this.autoApprove;
    }

    public void setAutoApprove(String autoApprove) {
        this.autoApprove = autoApprove;
    }

    @Override
    public String toString() {
        return "OauthClientDetails{" +
                "clientId='" + this.clientId + '\'' +
                ", resourceIds='" + this.resourceIds + '\'' +
                ", clientSecret='" + this.clientSecret + '\'' +
                ", scope='" + this.scope + '\'' +
                ", authorizedGrantTypes='" + this.authorizedGrantTypes + '\'' +
                ", webServerRedirectUri='" + this.webServerRedirectUri + '\'' +
                ", authorities='" + this.authorities + '\'' +
                ", accessTokenValidity=" + this.accessTokenValidity +
                ", refreshTokenValidity=" + this.refreshTokenValidity +
                ", additionalInformation='" + this.additionalInformation + '\'' +
                ", autoApprove='" + this.autoApprove + '\'' +
                '}';
    }
}
