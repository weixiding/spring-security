/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: FileInfo
 * Author:   Administrator
 * Date:     2019/11/20 12:49
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.dto;


public class FileInfo {
    private String absolutePath;

    public FileInfo() {
    }

    public FileInfo(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }
}
