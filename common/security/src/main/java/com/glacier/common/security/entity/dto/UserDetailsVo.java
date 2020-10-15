package com.glacier.common.security.entity.dto;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 用户
 *
 * @author glacier
 * @version 1.0
 * @date 2020-05-24 10:22
 */
public class UserDetailsVo implements UserDetails, CredentialsContainer {

    private static final long serialVersionUID = 7919080375772006733L;
    private String userId;
    private String username;
    private String password;
    private List<String> roles;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return this.roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Optional.ofNullable(this.roles)
                .orElseGet(ArrayList::new)
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        UserDetailsVo that = (UserDetailsVo) o;
        return this.userId.equals(that.userId) &&
                this.username.equals(that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.userId, this.username);
    }

    @Override
    public String toString() {
        return "UserDetailsVo{" +
                "userId='" + this.userId + '\'' +
                ", username='" + this.username + '\'' +
                ", password='" + this.password + '\'' +
                ", roles=" + this.roles +
                ", accountNonExpired=" + this.accountNonExpired +
                ", accountNonLocked=" + this.accountNonLocked +
                ", credentialsNonExpired=" + this.credentialsNonExpired +
                ", enabled=" + this.enabled +
                '}';
    }
}
