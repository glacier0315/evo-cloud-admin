package com.glacier.modules.sys.entity.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-08-15 10:02
 */
@Data
@ToString
public abstract class AbstractUserDto implements Serializable {
    private static final long serialVersionUID = -8546936668021702992L;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickname;
    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String idCard;
    /**
     * 出生日期
     */
    @ApiModelProperty(value = "出生日期，格式yyyy-MM-dd")
    private LocalDate birthday;
    /**
     * 性别 1=男 2=女 3 其他=保密
     */
    @ApiModelProperty(value = "性别 1=男 2=女 3 其他=保密")
    private Integer sex;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;
    /**
     * 单位id
     */
    @ApiModelProperty(value = "单位id")
    private String deptId;
    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称")
    private String deptName;
    /**
     * 简介
     */
    @ApiModelProperty(value = "简介")
    private String introduction;
}
