package com.lousland.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Hanzhi
 * @description：测试启动
 * @date ：2021/1/21 20:17
 */
@RestController
public class TestStartUp {

    @GetMapping("/testStartUp")
    public String TestStartUp() {
        return "Hello Lousland";
    }

}
