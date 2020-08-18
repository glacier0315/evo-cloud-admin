package com.glacier.common.security.config.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-02-14 22:18
 */
@ConfigurationProperties(prefix = "settings.security")
public class SecuritySettings implements Serializable {
    private static final long serialVersionUID = -2994114879313546449L;

    private List<String> permitAll;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<String> getPermitAll() {
        return permitAll;
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
        return Optional.ofNullable(this.permitAll)
                .map(list -> list.toArray(new String[0]))
                .orElseGet(() -> new String[0]);
    }

    @Override
    public String toString() {
        return "SecuritySettings{" +
                "permitAll=" + permitAll +
                '}';
    }
}
