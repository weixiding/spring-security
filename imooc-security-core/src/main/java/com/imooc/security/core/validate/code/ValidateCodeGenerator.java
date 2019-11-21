/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ValidateCodeGenerator
 * Author:   Administrator
 * Date:     2019/11/21 11:05
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2019/11/21
 * @since 1.0.0
 */
public interface ValidateCodeGenerator {
    public ImageCode generate(ServletWebRequest request);
}
