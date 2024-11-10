package com.jiawa.train.member.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
// @ComponentScan(basePackages = {"com.jiawa.train.member.controller","com.jiawa.train.member.service"})
// 主要作用是扫描Contorller层和Service层的注解，从而使其能够被Spring容器管理
// 默认扫描当前包及其子包下的所有类，可以通过basePackages属性指定扫描的包路径
@ComponentScan("com.jiawa")
@MapperScan("com.jiawa.train.*.mapper")
public class MemberApplication {

    private static final Logger LOG = LoggerFactory.getLogger(MemberApplication.class);

    /**
     * 主方法，用于启动Spring Boot应用程序。
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MemberApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("启动成功！！");
        LOG.info("测试地址: \thttp://127.0.0.1:{}{}/hello", env.getProperty("server.port"), env.getProperty("server.servlet.context-path"));
    }
}
