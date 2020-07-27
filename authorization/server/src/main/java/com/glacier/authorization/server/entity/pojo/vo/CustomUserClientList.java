package com.glacier.authorization.server.entity.pojo.vo;

import com.glacier.authorization.server.entity.pojo.OauthClientDetails;
import com.glacier.authorization.server.entity.pojo.User;
import lombok.*;

import java.util.List;

/**
 * 动态数据源切换测试
 *
 * @author glacier
 * @version 1.0
 * @date 2020-07-26 21:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CustomUserClientList {
    private List<User> userList;
    private List<OauthClientDetails> oauthClientDetailsList;
}
