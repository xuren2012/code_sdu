package com.sdu.seckill.queue;

import com.sdu.seckill.service.StorageService;
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
public class StorageQueue {

    @Autowired
    private StorageService storageService;

    @RabbitListener(queues = "storage_queue")
    public void insertStorage(String msg){

        //1、接收消息并输出
        System.out.println("storage_queue接收消息："+msg);

        //2、获取数据
        Map<String, Object> resultMap = storageService.insertStorage(msg, 0, 1);

        //3、执行失败，输出失败消息
        if (!(boolean)resultMap.get("result")){
            System.out.println("storage_queue处理消息失败："+resultMap.get("msg"));
        }else {
        //4、输出成功信息
            System.out.println("storage_queue处理消息成功！"+msg);
        }

    }
}
