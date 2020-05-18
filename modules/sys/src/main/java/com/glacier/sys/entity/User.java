package com.glacier.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-04 13:45
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName(excludeProperty = {"deptName", "roleIds"})
public class User implements Serializable {
    private static final long serialVersionUID = -3083387263445135811L;
    /**
     * 主键
     */
    @TableId
    private String id;
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
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateDate;
    /**
     * 删除标记
     */
    private String delFlag;

    /*非数据库字段*/
    /**
     * 单位名称
     */
    private String deptName;
    /**
     * 角色Id集合
     */
    private List<String> roleIds;
}
