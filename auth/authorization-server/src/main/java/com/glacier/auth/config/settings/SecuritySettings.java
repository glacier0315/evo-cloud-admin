package com.glacier.auth.config.settings;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-02-14 22:18
 */
@Data
@ToString
@Configuration
@ConfigurationProperties(prefix = "settings.security")
public class SecuritySettings implements Serializable {
    private static final long serialVersionUID = -2994114879313546449L;

    private List<String> permitAll;

    /**
     * 将permitAll 转换为数组
     *
     * @return
     */
    public String[] permitAll2Array() {
        String[] strings = new String[0];
        if (permitAll != null && !permitAll.isEmpty()) {
            strings = permitAll.toArray(strings);
        }
        return strings;
    }
}
