package com.glacier.modules.sys.entity.dto.user;

import com.alibaba.excel.annotation.ExcelProperty;
import com.glacier.common.core.excel.LocalDateConverter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

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
    @ExcelProperty(value = "用户名", index = 0, order = 0)
    @ApiModelProperty(value = "用户名")
    private String username;
    /**
     * 昵称
     */
    @ExcelProperty(value = "昵称", index = 1, order = 1)
    @ApiModelProperty(value = "昵称")
    private String nickname;
    /**
     * 身份证号
     */
    @ExcelProperty(value = "身份证号", index = 2, order = 2)
    @ApiModelProperty(value = "身份证号")
    private String idCard;
    /**
     * 出生日期
     */
    @ExcelProperty(value = "出生日期", index = 3, order = 3, converter = LocalDateConverter.class)
    @ApiModelProperty(value = "出生日期，格式yyyy-MM-dd")
    private LocalDate birthday;
    /**
     * 性别 1=男 2=女 3 其他=保密
     */
    @ExcelProperty(value = "性别", index = 4, order = 4)
    @ApiModelProperty(value = "性别 1=男 2=女 3 其他=保密")
    private Integer sex;
    /**
     * 邮箱
     */
    @ExcelProperty(value = "邮箱", index = 5, order = 5)
    @ApiModelProperty(value = "邮箱")
    private String email;
    /**
     * 手机号
     */
    @ExcelProperty(value = "手机号", index = 6, order = 6)
    @ApiModelProperty(value = "手机号")
    private String mobile;
    /**
     * 岗位id
     */
    @ApiModelProperty(value = "岗位id")
    private String postId;
    /**
     * 单位id
     */
    @ApiModelProperty(value = "单位id")
    private String deptId;
    /**
     * 单位名称
     */
    @ExcelProperty(value = "单位", index = 7, order = 7)
    @ApiModelProperty(value = "单位名称")
    private String deptName;
    /**
     * 简介
     */
    @ApiModelProperty(value = "简介")
    private String introduction;
    /**
     * 角色
     */
    @ApiModelProperty(value = "角色")
    private List<String> roleIds;
}
