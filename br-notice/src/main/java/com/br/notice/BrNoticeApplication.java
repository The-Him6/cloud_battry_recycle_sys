package com.br.notice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.br.notice.mapper")
public class BrNoticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrNoticeApplication.class, args);
        System.out.println("通知服务启动成功");
        System.out.println("访问地址：http://localhost:8086");
        System.out.println("===============================");
    }

}
