package com.glacier.authorization.resource.entity.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.glacier.common.core.entity.pojo.AbstractDataPojo;
import lombok.*;

import java.util.Date;

/**
 * 用户
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-04 13:45
 */
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class User extends AbstractDataPojo {
    private static final long serialVersionUID = -2411301606042619439L;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;
    /**
     * 性别 1=男 2=女 其他=保密
     */
    private Integer sex;
    /**
     * 状态  1 正常  2 锁定
     */
    private String status;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 单位id
     */
    private String deptId;
    /**
     * 单位名称
     */
    private String deptName;
}
