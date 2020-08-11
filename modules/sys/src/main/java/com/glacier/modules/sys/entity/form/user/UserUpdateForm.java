package com.glacier.modules.sys.entity.form.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.glacier.common.core.entity.form.IdForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * 用户信息修改封装类
 *
 * @author glacier
 * @version 1.0
 * @date 2020-05-24 08:57
 */
@ApiModel(description = "用户更新模型")
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class UserUpdateForm extends IdForm {
    private static final long serialVersionUID = -3135681003079034721L;
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
    @ApiModelProperty(value = "出生日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;
    /**
     * 性别 1=男 2=女 3 其他=保密
     */
    @ApiModelProperty(value = "性别 1=男 2=女 3 其他=保密")
    private Integer sex;
    /**
     * 状态  1 正常  0 锁定
     */
    @ApiModelProperty(value = "状态  1 正常  0 锁定")
    private String status;
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
}
