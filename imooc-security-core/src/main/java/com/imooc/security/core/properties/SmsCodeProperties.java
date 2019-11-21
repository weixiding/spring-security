/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: SmsCodeProperties
 * Author:   Administrator
 * Date:     2019/11/21 12:58
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
public class SmsCodeProperties {

    private int length = 6;
    private int exprie = 60;
    private String url = "";


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExprie() {
        return exprie;
    }

    public void setExprie(int exprie) {
        this.exprie = exprie;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
