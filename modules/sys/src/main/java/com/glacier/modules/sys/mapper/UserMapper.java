package com.glacier.modules.sys.mapper;

import com.glacier.common.core.mapper.BaseMapper;
import com.glacier.modules.sys.entity.User;
import com.glacier.modules.sys.entity.dto.user.UserDto;
import com.glacier.modules.sys.entity.dto.user.UserQuery;

import java.util.List;

/**
 * 用户数据层
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-04 21:53
 */
public interface UserMapper extends BaseMapper<User, String> {

    /**
     * 根据条件查询
     * @param params
     * @return
     */
    List<User> selectList(UserQuery params);

    /**
     * 根据组织机构id 更新组织机构名称
     *
     * @param record
     * @return
     */
    int updateDeptByDeptId(User record);

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return
     */
    User selectOneByUsername(String username);

    /**
     * 更新个人信息
     *
     * @param record
     * @return
     */
    int updateProfileByPrimaryKey(User record);

    /**
     * 更新头像
     *
     * @param record
     * @return
     */
    int updateAvatarByPrimaryKey(User record);

    /**
     * 更新密码
     *
     * @param record
     * @return
     */
    int updatePasswordByPrimaryKey(User record);

    /**
     * 查询用户名是否已使用
     * id 不为空时 排除该id的角色
     *
     * @param userDto 角色
     * @return 编码数量（排除指定id）
     */
    int checkUsernameExist(UserDto userDto);

    /**
     * 查询用户身份证号是否已使用
     * id 不为空时 排除该id的角色
     *
     * @param userDto 角色
     * @return 编码数量（排除指定id）
     */
    int checkIdCardExist(UserDto userDto);
}
