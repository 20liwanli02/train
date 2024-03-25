package com.jiawa.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * ComponentScan：启动类只能扫描到同包下的类，不能只写com，会把第三方的jar包也扫描得到，可能会有问题。
 */
@SpringBootApplication
@ComponentScan("com.jiawa")
public class MemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class, args);
    }

}
