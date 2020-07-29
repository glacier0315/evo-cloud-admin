package com.glacier.modules.sys.entity.vo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

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
public class UserDetailsVo implements Serializable {

    private static final long serialVersionUID = -4433713600535288510L;
    private String userId;
    private String username;
    private String password;
    private List<String> roles;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

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
