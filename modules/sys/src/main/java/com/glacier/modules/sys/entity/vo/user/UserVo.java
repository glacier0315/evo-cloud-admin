package com.glacier.modules.sys.entity.vo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-05-24 09:32
 */
@ApiModel(description = "用户列表模型")
@Data
@ToString
public class UserVo implements Serializable {
    private static final long serialVersionUID = 3725415789818769982L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private String id;
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
