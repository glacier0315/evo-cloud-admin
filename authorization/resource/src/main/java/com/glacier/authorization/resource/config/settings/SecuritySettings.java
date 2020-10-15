package com.glacier.authorization.resource.config.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;
import java.util.List;

/**
 * @author glacier
 * @version 1.0
 * date 2020-02-14 22:18
 */
@Configuration
@ConfigurationProperties(prefix = "settings.security")
public class SecuritySettings implements Serializable {
    private static final long serialVersionUID = -2994114879313546449L;

    private List<String> permitAll;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<String> getPermitAll() {
        return this.permitAll;
    }

    public void setPermitAll(List<String> permitAll) {
        this.permitAll = permitAll;
    }

    /**
     * 将permitAll 转换为数组
     *
     * @return
     */
    public String[] permitAll2Array() {
        String[] strings = new String[0];
        if (this.permitAll != null && !this.permitAll.isEmpty()) {
            strings = this.permitAll.toArray(strings);
        }
        return strings;
    }

    @Override
    public String toString() {
        return "SecuritySettings{" +
                "permitAll=" + this.permitAll +
                '}';
    }
}
