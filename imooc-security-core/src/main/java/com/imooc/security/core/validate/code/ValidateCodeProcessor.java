/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ValidateCodeProcessor
 * Author:   Administrator
 * Date:     2019/11/21 13:28
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.security.core.validate.code;


import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeProcessor {
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE";

    void create(ServletWebRequest request) throws Exception;

}
