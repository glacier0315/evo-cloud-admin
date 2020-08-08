package com.glacier.modules.sys.entity.vo.user;

import com.glacier.common.core.entity.vo.RoleDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
public class UserDetails implements Serializable {

    private static final long serialVersionUID = -4433713600535288510L;
    /**
     * 主键
     */
    private String id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 密码
     */
    private String password;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 出生日期
     */
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
    /**
     * 单位名称
     */
    private String deptName;

    /**
     * 角色编码集合
     */
    private List<RoleDetails> roleDetails;
}
