/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ImageCode
 * Author:   Administrator
 * Date:     2019/11/20 21:42
 * Description: \
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.security.core.validate.code;

import java.time.LocalDateTime;

/**
 * 〈一句话功能简述〉<br> 
 * 〈\〉
 *
 * @author Administrator
 * @create 2019/11/20
 * @since 1.0.0
 */
public class ImageCode {
    private String code;
    private LocalDateTime expireTime;

    public ImageCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public ImageCode(String code, int expireIn) {
        this.code = code;
        this.expireTime =  LocalDateTime.now().plusSeconds(expireIn);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean ifExpire() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}
