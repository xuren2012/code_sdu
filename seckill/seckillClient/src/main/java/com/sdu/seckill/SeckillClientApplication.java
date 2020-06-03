package com.sdu.seckill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SeckillClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeckillClientApplication.class, args);
    }

}
