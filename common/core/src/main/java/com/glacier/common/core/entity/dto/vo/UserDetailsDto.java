package com.glacier.common.core.entity.dto.vo;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * 用户详情模型
 *
 * @author glacier
 * @version 1.0
 * date 2020-05-24 10:22
 */
public class UserDetailsDto implements Serializable {

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
    private LocalDate birthday;
    /**
     * 性别 1=男 2=女 3 其他=保密
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
     * 角色集合
     */
    private List<RoleDetailsDto> roleDetailDtos;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdCard() {
        return this.idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public LocalDate getBirthday() {
        return this.birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Integer getSex() {
        return this.sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDeptId() {
        return this.deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return this.deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<RoleDetailsDto> getRoleDetailDtos() {
        return this.roleDetailDtos;
    }

    public void setRoleDetailDtos(List<RoleDetailsDto> roleDetailDtos) {
        this.roleDetailDtos = roleDetailDtos;
    }

    @Override
    public String toString() {
        return "UserDetailsDto{" +
                "id='" + this.id + '\'' +
                ", username='" + this.username + '\'' +
                ", nickname='" + this.nickname + '\'' +
                ", password='" + this.password + '\'' +
                ", idCard='" + this.idCard + '\'' +
                ", birthday=" + this.birthday +
                ", sex=" + this.sex +
                ", status='" + this.status + '\'' +
                ", email='" + this.email + '\'' +
                ", mobile='" + this.mobile + '\'' +
                ", deptId='" + this.deptId + '\'' +
                ", deptName='" + this.deptName + '\'' +
                ", roleDetailDtos=" + this.roleDetailDtos +
                '}';
    }
}
