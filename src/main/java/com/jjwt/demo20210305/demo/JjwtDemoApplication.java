package com.jjwt.demo20210305.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class JjwtDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JjwtDemoApplication.class, args);
    }

}
