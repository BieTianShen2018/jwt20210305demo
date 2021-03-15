package com.jjwt.demo20210305.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {


    @RequestMapping("/authentication")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {

        return String.format("Hello 222 333 %s!", name);
    }


    @RequestMapping("/failed")
    public Map<String, String> requestFailed(){

        Map<String, String> map = new HashMap<>();
        map.put("code", "-1");
        map.put("msg", "请求错误");
        return map;
    }

    @Value(value = "${test-key}")
    private String testKey;


    @RequestMapping("testApi")
    public String testHotDeployment() {

        return "Wonderful :"+testKey;


    }
}
