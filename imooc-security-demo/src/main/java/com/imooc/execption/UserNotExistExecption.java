/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: UserNotExistExecption
 * Author:   Administrator
 * Date:     2019/11/20 13:55
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.execption;


public class UserNotExistExecption extends Exception {
    String id;

    public UserNotExistExecption(String message, String id) {
        super(message);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
