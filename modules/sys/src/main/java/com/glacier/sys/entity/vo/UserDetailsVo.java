package com.glacier.sys.entity.vo;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Set;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-05-24 10:22
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailsVo implements Serializable {
    private static final long serialVersionUID = -2993294506370864935L;
    private String password;
    private String username;
    private Set<GrantedAuthority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
}
