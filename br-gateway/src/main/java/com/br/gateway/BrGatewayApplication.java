package com.br.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BrGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrGatewayApplication.class, args);
        System.out.println("网关服务启动成功");
        System.out.println("访问地址：http://localhost:8080");
        System.out.println("===============================");
    }

}
