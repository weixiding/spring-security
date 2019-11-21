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

import com.imooc.security.core.properties.SecurityConstants;
import com.imooc.security.core.validate.code.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;
import java.util.Map;

import static com.mysql.jdbc.interceptors.SessionAssociationInterceptor.getSessionKey;

/**

 */
@Component
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

    private SessionStrategy sessionStrategy =  new HttpSessionSessionStrategy();
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
        ValidateCodeGenerator validateCodeGenerator = this.validateCodeGenerator.get(type.toLowerCase() + ValidateCodeGenerator.class.getSimpleName());
        return (C) validateCodeGenerator.generate(request);

    }

    private String getProcessorType(ServletWebRequest request) {
        return StringUtils.substringAfter(request.getRequest().getRequestURI(), SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX);
    }

    private void save(ServletWebRequest request,C validateCode) {
        HttpSessionSessionStrategy httpSessionSessionStrategy = new HttpSessionSessionStrategy();
        httpSessionSessionStrategy.setAttribute(request,SecurityConstants.SESSION_KEY_PREFIX + getProcessorType(request).toUpperCase(),validateCode);
    }

    protected abstract void send(ServletWebRequest request, C validateCode) throws IOException, ServletRequestBindingException;

    private String getSessionKey() {
        return SecurityConstants.SESSION_KEY_PREFIX + getValidateCodeType().toString().toUpperCase();
    }
    /**
     * 图片和短信验证码的逻辑一致，提取成公共的
     * @param request
     */
    @Override
    public void validate(ServletWebRequest request) throws ServletRequestBindingException {

        // 拿到自己的类型
        ValidateCodeType type = getValidateCodeType();

        String sessionKey = getSessionKey();
        // 拿到创建 create() 存储到session的code验证码对象
        C codeInSession = (C) sessionStrategy.getAttribute(request, sessionKey);

        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), type.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeExecption("获取验证码的值失败");
        }

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeExecption("验证码的值不能为空");
        }
        if (codeInSession == null) {
            throw new ValidateCodeExecption("验证码不存在");
        }
        if (codeInSession.isExpire()) {
            sessionStrategy.removeAttribute(request, sessionKey);
            throw new ValidateCodeExecption("验证码已过期");
        }
        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeExecption("验证码不匹配");
        }
        sessionStrategy.removeAttribute(request, sessionKey);
    }
    /**
     * 根据请求的url获取校验码的类型:
     * ValidateCodeProcessorHolder : 中持有所有本类的子类型，获取getClass能拿到具体的实例类名
     * @return
     * @see ValidateCodeProcessorHolder
     */
    private ValidateCodeType getValidateCodeType() {
        // 处理器 命名规则：ImageValidateCodeProcessor，拿到前缀即可
        String type = StringUtils.substringBefore(getClass().getSimpleName(), ValidateCodeProcessor.class.getSimpleName());
        return ValidateCodeType.valueOf(type.toUpperCase());
    }
}
