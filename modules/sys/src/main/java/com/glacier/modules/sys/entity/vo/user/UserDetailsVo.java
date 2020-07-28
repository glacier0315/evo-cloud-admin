package com.glacier.modules.sys.entity.vo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Objects;
import java.util.Set;

/**
 * 用户
 *
 * @author glacier
 * @version 1.0
 * @date 2020-05-24 10:22
 */
@Data
@AllArgsConstructor
@ToString
public class UserDetailsVo implements UserDetails, CredentialsContainer {

    private static final long serialVersionUID = -4433713600535288510L;
    private final String userId;
    private final String username;
    private String password;
    private final Set<GrantedAuthority> authorities;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;

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
}
