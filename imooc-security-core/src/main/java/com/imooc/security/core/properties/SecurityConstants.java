/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: SecurityConstants
 * Author:   Administrator
 * Date:     2019/11/21 20:54
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.security.core.properties;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2019/11/21
 * @since 1.0.0
 */
public interface SecurityConstants {
    String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code/";

    String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";

    String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/form";

    String DEFAULT_LOGIN_PROCESSING_URL_MOBILE = "/authentication/mobile";

    String DEFAULT_LOGIN_PAGE_URL = "/imocc-signIn.html";

    String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";

    String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";

    public static final String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";

    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE";

}
