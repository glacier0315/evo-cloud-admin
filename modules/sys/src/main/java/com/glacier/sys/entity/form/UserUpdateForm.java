package com.glacier.sys.entity.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-05-24 08:57
 */
@Data
@ToString
public class UserUpdateForm implements Serializable {
    private static final long serialVersionUID = -3135681003079034721L;
    /**
     * 主键
     */
    private String id;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    /**
     * 性别 1=男 2=女 其他=保密
     */
    private Integer sex;
    /**
     * 状态  1 正常  0 锁定
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
     * 单位id
     */
    private String deptId;
}
