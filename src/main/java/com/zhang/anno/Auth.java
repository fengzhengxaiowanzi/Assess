package com.zhang.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: zhang
 * @Description: com.zhang.anno 是否检查登录 添加注解默认检查
 * @Date：Created in 17:58 2021/1/6
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
    boolean auth() default true;
}
