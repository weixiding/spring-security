/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: AbstractValidateCodeProcessor
 * Author:   Administrator
 * Date:     2019/11/21 13:36
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.security.core.validate.code.impl;

import com.imooc.security.core.validate.code.ValidateCode;
import com.imooc.security.core.validate.code.ValidateCodeGenerator;
import com.imooc.security.core.validate.code.ValidateCodeProcessor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;
import java.util.Map;

/**

 */
public abstract class AbstractValidateCodeProcessor<C> implements ValidateCodeProcessor {

    //依赖查找
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerator;

    @Override
    public void create(ServletWebRequest request) throws Exception {
        C validateCode = generate(request);
        save(request,validateCode);
        send(request,validateCode);
    }
    private C generate(ServletWebRequest request) {
        String type = getProcessorType(request);
        ValidateCodeGenerator validateCodeGenerator = this.validateCodeGenerator.get(type.toLowerCase() + "CodeGenerator");
        return (C) validateCodeGenerator.generate(request);

    }

    private String getProcessorType(ServletWebRequest request) {
        return StringUtils.substringAfter(request.getRequest().getRequestURI(),"/code/");
    }

    private void save(ServletWebRequest request,C validateCode) {
        HttpSessionSessionStrategy httpSessionSessionStrategy = new HttpSessionSessionStrategy();
        httpSessionSessionStrategy.setAttribute(request,SESSION_KEY_PREFIX + getProcessorType(request).toUpperCase(),validateCode);
    }

    protected abstract void send(ServletWebRequest request, C validateCode) throws IOException, ServletRequestBindingException;
}
