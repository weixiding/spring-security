/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TimeAspect
 * Author:   Administrator
 * Date:     2019/11/20 14:21
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class TimeAspect {

    @Around("execution(* com.imooc.controller.UserController.*(..))")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("切面开始");
        System.out.println(pjp.getArgs());
        // start stopwatch
        Object retVal = pjp.proceed();
        // stop stopwatch
        System.out.println("切面结束");
        return retVal;
    }

}
