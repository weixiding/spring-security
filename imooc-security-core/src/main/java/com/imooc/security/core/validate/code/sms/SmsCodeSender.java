/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: SmsCodeSender
 * Author:   Administrator
 * Date:     2019/11/21 12:40
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.security.core.validate.code.sms;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2019/11/21
 * @since 1.0.0
 */
public interface SmsCodeSender {
    void send(String mobile,String content);

}
