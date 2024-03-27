package com.jiawa.train.member.config;

//import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

/**
 * ComponentScan：扫描Controller，启动类只能扫描到同包下的类，不能只写com，会把第三方的jar包也扫描得到，可能会有问题。
 * MapperScan：扫描Mapper
 */
@SpringBootApplication
@ComponentScan("com.jiawa")
@MapperScan("com.jiawa.train.member.mapper")
public class MemberApplication {
    private static final Logger LOG = LoggerFactory.getLogger(MemberApplication.class);

        public static void main(String[] args){
            SpringApplication app = new SpringApplication(MemberApplication.class);
            Environment env = app.run(args).getEnvironment();
            LOG.info("启动成功！！");
            LOG.info("测试地址: \thttp://127.0.0.1:{}{}/hello", env.getProperty("server.port"), env.getProperty("server.servlet.context-path"));
        }
}

