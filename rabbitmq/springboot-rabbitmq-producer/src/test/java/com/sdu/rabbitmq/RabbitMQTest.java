package com.sdu.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: YTF
 * @Date: 2020/5/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class RabbitMQTest {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testCreateMessage(){
        rabbitTemplate.convertAndSend( "item.insert", "商品新增，routing key 为item.insert");
        rabbitTemplate.convertAndSend( "item.update", "商品修改，routing key 为item.update");
        rabbitTemplate.convertAndSend( "item.delete", "商品删除，routing key 为item.delete");
    }

}
