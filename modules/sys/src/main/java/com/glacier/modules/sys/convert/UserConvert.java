package com.glacier.modules.sys.convert;

import com.glacier.common.core.entity.dto.UserDetailsDto;
import com.glacier.modules.sys.entity.User;
import com.glacier.modules.sys.entity.dto.user.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * date 2021-05-06 15:19
 * 用户类型转换
 * @author glacier
 * @version 1.0
 */
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserConvert {
    /**
     * 转换
     *
     * @param user
     * @return
     */
    UserProfile toUserProfile(User user);
    
    /**
     * 转换
     *
     * @param user
     * @return
     */
    UserDetailsDto toUserDetailsDto(User user);
    
    /**
     * @param user
     * @return
     */
    UserDto toUserDto(User user);
    
    /**
     * 转换
     *
     * @param userList
     * @return
     */
    List<UserDto> toUserDto(List<User> userList);
    
    /**
     * 转换
     *
     * @param userProfileForm
     * @return
     */
    User map(UserProfileForm userProfileForm);
    
    /**
     * 转换
     *
     * @param userAvatarForm
     * @return
     */
    User map(UserAvatarForm userAvatarForm);
    
    
    /**
     * 转换
     *
     * @param userAddForm
     * @return
     */
    User map(UserAddForm userAddForm);
    
    /**
     * 转换
     *
     * @param userDto
     * @return
     */
    User map(UserDto userDto);
}
