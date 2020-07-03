package com.glacier.sys.entity.pojo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.glacier.common.core.entity.pojo.BasePojo;
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
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Builder
@TableName(excludeProperty = {"deptName"})
public class User extends BasePojo {
    private static final long serialVersionUID = -3083387263445135811L;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
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
     * 单位id
     */
    private String deptId;
    /**
     * 删除标记
     */
    @TableLogic
    private String delFlag;


    /*非数据库字段*/
    /**
     * 单位名称
     */
    private String deptName;
}
