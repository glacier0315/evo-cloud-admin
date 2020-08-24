package com.glacier.common.security.annotation;


import com.glacier.common.security.config.CustomWebSecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


/**
 * @author glacier
 * @version 1.0
 * @date 2020-08-17 12:50
 */

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE})
@Documented
@Inherited
@Configuration
@Import({CustomWebSecurityConfig.class})
public @interface EnableCustomSecurity {
}
