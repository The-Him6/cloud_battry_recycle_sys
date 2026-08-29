package com.br.exchange;

import com.br.api.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.br.api.client", defaultConfiguration = DefaultFeignConfig.class)
@SpringBootApplication
@MapperScan("com.br.exchange.mapper")
public class BrExchangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrExchangeApplication.class, args);
        System.out.println("交易服务启动成功");
        System.out.println("访问地址：http://localhost:8084");
        System.out.println("===============================");
    }

}
