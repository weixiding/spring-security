/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: SmsCodeAuthenticationProvider
 * Author:   Administrator
 * Date:     2019/11/21 14:41
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.security.core.authentation.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 提供手机号认证功能
 */

public class SmsCodeAuthenticationProvider implements AuthenticationProvider {
    private UserDetailsService userDetailsService;

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsAuthentationToken smsAuthentication= (SmsAuthentationToken) authentication;
        UserDetails userDetails = userDetailsService.loadUserByUsername((String) smsAuthentication.getPrincipal());
        if(userDetails == null) {
            throw new UsernameNotFoundException("无法获取用信息");
        }
        SmsAuthentationToken smsAuthentationToken = new SmsAuthentationToken(userDetails.getUsername(),userDetails,userDetails.getAuthorities());
        return  smsAuthentationToken;

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return SmsAuthentationToken.class.isAssignableFrom(aClass);
    }
}
