package com.zhang.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: zhang
 * @Description: PACKAGE_NAME
 * @Date：Created in 13:47 2021/1/6
 */
@SpringBootApplication(scanBasePackages = "com.zhang")
@MapperScan("com.zhang.mapper")
@PropertySource(value = "classpath:config.properties")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
