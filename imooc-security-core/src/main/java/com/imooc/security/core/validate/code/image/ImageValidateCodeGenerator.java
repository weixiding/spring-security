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
package com.imooc.security.core.validate.code.image;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ValidateCodeGenerator;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.image.BufferedImage;
import java.util.Properties;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2019/11/21
 * @since 1.0.0
 */

public class ImageValidateCodeGenerator implements ValidateCodeGenerator {

    private SecurityProperties securityProperties;

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    public ImageCode generate(ServletWebRequest request) {
        Properties properties = new Properties();
        properties.put("kaptcha.border","yes");
        properties.put("kaptcha.textproducer.char.length",securityProperties.getCode().getImage().getLength());
        properties.put("kaptcha.textproducer.char.string","abcde2345678gfynmnpwx");

        //在此处考虑可变的宽度和高度
        if(request.getParameter("width") != null) {
            properties.put("kaptcha.image.width",request.getParameter("width"));
        }else {
            properties.put("kaptcha.image.width",securityProperties.getCode().getImage().getWidth());
        }
        if(request.getParameter("height") != null) {
            properties.put("kaptcha.image.height",request.getParameter("height"));
        }else {
            properties.put("kaptcha.image.height",securityProperties.getCode().getImage().getHeight());
        }

        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        //生成图形验证码
        String capText = defaultKaptcha.createText();
        // create the image with the text
        BufferedImage bi = defaultKaptcha.createImage(capText);


        ImageCode imagecode = new ImageCode(capText,bi,securityProperties.getCode().getImage().getExprie());
        return imagecode;
    }
}
