package com.emall.controller.common;

import com.emall.pojo.User;
import com.emall.utils.CookieUtil;
import com.emall.utils.JsonUtil;
import com.emall.utils.RedisPoolUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SessionExpireFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
        if (StringUtils.isNotEmpty(loginToken)) {
            String userStr = RedisPoolUtil.get(loginToken);
            User user = JsonUtil.string2Obj(userStr, User.class);
            if (user != null) {
                RedisPoolUtil.expire(loginToken, 60 * 30);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
