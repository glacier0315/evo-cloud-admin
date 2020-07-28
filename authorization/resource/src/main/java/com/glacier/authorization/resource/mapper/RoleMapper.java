package com.glacier.authorization.resource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glacier.authorization.resource.entity.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色数据层
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-04 21:53
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根绝用户id 查询用户所拥有的角色
     *
     * @param userId
     * @return
     */
    List<String> findCodeByUserId(@Param("userId") String userId);
}