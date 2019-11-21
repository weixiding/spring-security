/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ImageCodeGenerator
 * Author:   Administrator
 * Date:     2019/11/21 11:12
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.security.core.validate.code.sms;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ValidateCode;
import com.imooc.security.core.validate.code.ValidateCodeGenerator;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.image.BufferedImage;
import java.util.Properties;


public class SmsValidateCodeGenerator implements ValidateCodeGenerator {

    private SecurityProperties securityProperties;

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        Properties properties = new Properties();
        properties.put("kaptcha.border","yes");
        properties.put("kaptcha.textproducer.char.length",securityProperties.getCode().getSms().getLength());
        properties.put("kaptcha.textproducer.char.string","abcde2345678gfynmnpwx");



        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        //生成图形验证码
        String capText = defaultKaptcha.createText();



        ValidateCode validateCode = new ValidateCode(capText,securityProperties.getCode().getSms().getExprie());
        return validateCode;
    }
}
