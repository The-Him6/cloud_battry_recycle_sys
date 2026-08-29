package com.br.recycle;

import com.br.api.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.br.api.client",defaultConfiguration = DefaultFeignConfig.class)
@SpringBootApplication
@MapperScan("com.br.recycle.mapper")
public class BrRecycleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrRecycleApplication.class, args);
        System.out.println("回收服务启动成功");
        System.out.println("访问地址：http://localhost:8083");
        System.out.println("===============================");
    }

}
