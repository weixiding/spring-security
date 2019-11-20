/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ControllerExecptionHandler
 * Author:   Administrator
 * Date:     2019/11/20 13:54
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.execption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExecptionHandler {
    @ExceptionHandler(UserNotExistExecption.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> handUserNotExistExecption(UserNotExistExecption e) {
        String message = e.getMessage();
        String id = e.getId();
        Map<String,Object> ret = new HashMap<>();
        ret.put("message",message);
        ret.put("id",id);
        return ret;
    }


}
