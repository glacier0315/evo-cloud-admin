package com.glacier.sys.entity.dto;

import lombok.*;

import java.io.Serializable;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-05-03 17:47
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginUserDto implements Serializable {
    private static final long serialVersionUID = -2787163127766987740L;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}
