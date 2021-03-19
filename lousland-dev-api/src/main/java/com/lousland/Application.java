package com.lousland;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author ：Hanzhi
 * @description：启动类
 * @date ：2021/1/21 20:15
 */
@SpringBootApplication
@MapperScan(basePackages = "com.lousland.mapper")
@ComponentScan(basePackages = "com.lousland")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
