package com.sdu.seckill.queue;

import com.alibaba.fastjson.JSONObject;
import com.sdu.seckill.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description:
 * @Author: YTF
 * @Date: 2020/5/27
 */
@Component
public class OrderQueue {

    @Autowired
    private OrderService orderService;

    @RabbitListener(queues = "order_queue")
    public void insertOrder(String msg){
        //1、接收消息并输出
        System.out.println("order_queue接收消息："+msg);

        //2、调用 service insertOrder方法
        Map<String, Object> orderInfo = JSONObject.parseObject(msg, Map.class);
        Map<String, Object> resultMap = orderService.insertOrder(orderInfo);

        //3、失败，返回错误信息
        if (!(Boolean) resultMap.get("result")){
            System.out.println("order_queue处理消息失败："+resultMap.get("msg"));
        }else {
            //4、成功，返回成功信息
            System.out.println("order_queue处理消息成功！");
        }
    }

//    @RabbitListener(queues = "order_status_queue")
//    public void updateOrderStatus(String msg){
//        //1、接收消息
//        System.out.println("order_status_queue接收消息："+msg);
//
//        //2、调用 service updateOrderStatus方法
//        Map<String, Object> resultMap = orderService.updateOrderStatus(msg);
//
//        //3、执行失败，返回错误信息
//        if (!(Boolean) resultMap.get("result")){
//            System.out.println("order_status_queue处理消息失败："+resultMap.get("msg"));
//        }else {
//            //4、成功，返回成功信息
//            System.out.println("order_status_queue处理消息成功！"+msg);
//        }
//
//    }
}
