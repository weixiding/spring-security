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
import com.imooc.security.core.validate.code.image.ImageValidateCodeGenerator;
import com.imooc.security.core.validate.code.sms.DefaultSmsCodeSender;
import com.imooc.security.core.validate.code.sms.SmsCodeSender;
import com.imooc.security.core.validate.code.sms.SmsValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    //图形验证码的配置
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    @Bean("imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        ImageValidateCodeGenerator imageCodeGenerator = new ImageValidateCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }

    @ConditionalOnMissingBean(name = "smsValidateCodeGenerator")
    @Bean("smsValidateCodeGenerator")
    public ValidateCodeGenerator smsValidateCodeGenerator() {
        SmsValidateCodeGenerator imageCodeGenerator = new SmsValidateCodeGenerator();
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
