package com.glacier.authorization.resource.mapper;

import com.glacier.authorization.resource.entity.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户数据层
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-04 21:53
 */
public interface UserMapper {

    /**
     * 根据id 查询用户
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     * 保存用户
     *
     * @param record
     * @return
     */
    int insert(User record);

    /**
     * 根据id 查找用户
     *
     * @param id
     * @return
     */
    User selectByPrimaryKey(@Param("id") String id);

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    User selectOneByUsername(@Param("username") String username);

    /**
     * 查询用户
     *
     * @param user
     * @return
     */
    List<User> selectList(User user);

    /**
     * 查询所有用户
     *
     * @return
     */
    List<User> selectAll();

    /**
     * 根据id 更新用户
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(User record);
}
