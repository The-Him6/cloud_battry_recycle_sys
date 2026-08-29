package com.br.points;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.br.points.mapper")
public class BrPointsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrPointsApplication.class, args);
        System.out.println("积分服务启动成功");
        System.out.println("访问地址：http://localhost:8082");
        System.out.println("===============================");
    }

}
