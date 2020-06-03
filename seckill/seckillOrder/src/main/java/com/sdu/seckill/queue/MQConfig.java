package com.sdu.seckill.queue;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: YTF
 * @Date: 2020/5/27
 */
@Component
public class MQConfig {

    @Bean
    public Queue queueOrder(){
        return new Queue("order_queue",true);
    }

//    public Queue queueOrderStatus(){
//        return new Queue("order_status_queue",true);
//    }

}
