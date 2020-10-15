package com.glacier.common.security.filter;

import com.glacier.common.core.utils.AppContextHolder;
import com.glacier.common.security.utils.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author glacier
 * @version 1.0
 * @date 2020-08-26 12:06
 */
public class CustomContextFilter extends GenericFilterBean {
    private static final Logger log = LoggerFactory.getLogger(CustomContextFilter.class);
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 添加上下文
        AppContextHolder.getInstance()
                .setContext(SecurityUtils.getClaims());
        log.info("添加上下文 {}", AppContextHolder.getInstance().getContext());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
        // 清除上下文
        AppContextHolder.getInstance().clear();
        log.info("清空上下文 {}", AppContextHolder.getInstance().getContext());
    }
}
