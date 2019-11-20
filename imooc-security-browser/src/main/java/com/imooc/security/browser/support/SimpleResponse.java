/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: SimpleResponse
 * Author:   Administrator
 * Date:     2019/11/20 16:31
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.security.browser.support;


public class SimpleResponse {
    private Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
