package com.sdu.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: YTF
 * @Date: 2020/5/28
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue itemQueue(){
        return new Queue("item_queue",true);
    }
}
