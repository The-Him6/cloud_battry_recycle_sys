package com.br.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 用户服务启动类
 */
@SpringBootApplication
@MapperScan("com.br.user.mapper")
public class BrUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrUserApplication.class, args);
        System.out.println("用户服务启动成功");
        System.out.println("访问地址：http://localhost:8081");
        System.out.println("===============================");
    }
}
