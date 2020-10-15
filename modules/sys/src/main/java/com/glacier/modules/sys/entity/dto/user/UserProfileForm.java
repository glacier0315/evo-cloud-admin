package com.glacier.modules.sys.entity.dto.user;

import com.glacier.common.core.entity.dto.IdDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户信息修改封装类
 *
 * @author glacier
 * @version 1.0
 * date 2020-07-23 10:31
 */
@ApiModel(description = "用户个人中心信息模型")
public class UserProfileForm extends IdDto {

    private static final long serialVersionUID = -4572380926802939142L;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickname;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;
    /**
     * 性别 1=男 2=女 3 其他=保密
     */
    @ApiModelProperty(value = "性别 1=男 2=女 3 其他=保密")
    private Integer sex;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return this.sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "UserProfileForm{" +
                "nickname='" + this.nickname + '\'' +
                ", mobile='" + this.mobile + '\'' +
                ", email='" + this.email + '\'' +
                ", sex=" + this.sex +
                ", id='" + this.id + '\'' +
                '}';
    }
}

