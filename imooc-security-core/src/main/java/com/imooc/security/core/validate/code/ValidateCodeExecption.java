/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ValidateCodeExecption
 * Author:   Administrator
 * Date:     2019/11/20 22:43
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.security.core.validate.code;


import org.springframework.security.core.AuthenticationException;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2019/11/20
 * @since 1.0.0
 */
public class ValidateCodeExecption extends AuthenticationException {


    public ValidateCodeExecption(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateCodeExecption(String msg) {
        super(msg);
    }
}
