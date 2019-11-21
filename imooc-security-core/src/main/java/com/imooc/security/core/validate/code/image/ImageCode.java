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
package com.imooc.security.core.validate.code.image;

import com.imooc.security.core.validate.code.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 〈一句话功能简述〉<br> 
 * 〈\〉
 *
 * @author Administrator
 * @create 2019/11/20
 * @since 1.0.0
 */
public class ImageCode  extends ValidateCode {

    private BufferedImage bufferedImage;


    public ImageCode(String code,BufferedImage bufferedImage,LocalDateTime expireTime) {
        super(code, expireTime);
    }

    public ImageCode(String code,BufferedImage bufferedImage,int expireIn) {
        super(code, expireIn);
        this.bufferedImage = bufferedImage;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }
}
