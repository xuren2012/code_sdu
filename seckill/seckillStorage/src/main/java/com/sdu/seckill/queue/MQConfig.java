package com.sdu.seckill.queue;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: YTF
 * @Date: 2020/5/27
 */
@Configuration
public class MQConfig {

    @Bean
    public Queue queueStorage(){
        return new Queue("storage_queue",true);
    }
}
