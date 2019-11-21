/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ValidateCodeBeanConfig
 * Author:   Administrator
 * Date:     2019/11/21 11:19
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.security.core.validate.code;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.image.ImageCodeGenerator;
import com.imooc.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.imooc.security.core.validate.code.sms.SmsCodeGenerator;
import com.imooc.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    //图形验证码的配置
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    @Bean("imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator() {
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }

    @ConditionalOnMissingBean(name = "smsCodeGenerator")
    @Bean("smsCodeGenerator")
    public ValidateCodeGenerator smsCodeGenerator() {
        SmsCodeGenerator imageCodeGenerator = new SmsCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }


    //手机短信发送服务
    @ConditionalOnMissingBean(SmsCodeSender.class)
    @Bean
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();

    }
}
