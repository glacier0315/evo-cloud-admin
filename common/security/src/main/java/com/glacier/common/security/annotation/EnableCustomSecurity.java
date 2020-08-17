package com.glacier.common.security.annotation;


import com.glacier.common.security.config.CustomWebSecurityConfig;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

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
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@Import({CustomWebSecurityConfig.class})
public @interface EnableCustomSecurity {
}
