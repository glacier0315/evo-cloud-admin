package com.glacier.sys.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.io.Serializable;

/**
 * 用户角色关系
 *
 * @author glacier
 * @version 1.0
 * @date 2019-08-11 21:18
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRole implements Serializable {

    private static final long serialVersionUID = -7193974752669679122L;
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 角色id
     */
    private String roleId;
}
