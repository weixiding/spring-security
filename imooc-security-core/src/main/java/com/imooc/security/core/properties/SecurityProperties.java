/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: SecurityProperties
 * Author:   Administrator
 * Date:     2019/11/20 16:35
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 安全框架的主配置
 *
 * @author Administrator
 * @create 2019/11/20
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "imooc.security")
public class SecurityProperties {
    private BrowserProperties browser;

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
