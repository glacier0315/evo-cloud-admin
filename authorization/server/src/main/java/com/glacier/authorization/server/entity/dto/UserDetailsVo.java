package com.glacier.authorization.server.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

    @Override
    public void eraseCredentials() {
        this.password = null;
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
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Optional.ofNullable(this.roles)
                .orElseGet(ArrayList::new)
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }
}
