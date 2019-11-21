/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ValidateCodeFilter
 * Author:   Administrator
 * Date:     2019/11/20 22:23
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.security.core.validate.code;

import com.imooc.security.core.properties.SecurityProperties;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.PathMatcher;
import java.util.HashSet;
import java.util.Set;

/**
 * 图形验证码验证的过滤器
 */
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {
    SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    private AuthenticationFailureHandler authenticationFailureHandler;
    private SecurityProperties securityProperties;
    private Set<String> url = new HashSet<>();
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }



    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        //进行url路径的拆分工作
        String urlFromProperties = securityProperties.getCode().getImage().getUrl();
        String[] urls = StringUtils.split(urlFromProperties, ",");
        for (String u : urls) {
            url.add(u);
        }
        //添加我们必须拦截的路径
        url.add("/authentication/form");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean isFilter = false;
        for (String s : url) {
            if(antPathMatcher.match(s,request.getRequestURI())) {
                isFilter = true;
            }
        }
        if(isFilter) {
            //进行校验逻辑
            try {
                validate(new ServletWebRequest(request));
            } catch (AuthenticationException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request,response);
    }
    //图形验证码的校验功能
    private void validate(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {

        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(servletWebRequest, ValidateCodeController.SESSION_KEY);
        String imageCode = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "imageCode");
        if(StringUtils.isBlank(imageCode)) {
            throw new ValidateCodeExecption("验证码不能为空");
        }
        //验证码不存在
        if(codeInSession == null) {
            throw new ValidateCodeExecption("验证码不存在");
        }
        //验证码过期
        if(codeInSession.ifExpire()) {
            throw new ValidateCodeExecption("验证码过期");
        }
        //验证码不匹配
        if(!StringUtils.endsWithIgnoreCase(codeInSession.getCode(),imageCode)) {
            throw new ValidateCodeExecption("验证码不匹配");
        }
        sessionStrategy.removeAttribute(servletWebRequest, ValidateCodeController.SESSION_KEY);

    }
}
