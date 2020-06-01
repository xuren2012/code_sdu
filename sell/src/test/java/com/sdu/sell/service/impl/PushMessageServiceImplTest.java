package com.sdu.sell.service.impl;

import com.sdu.sell.dto.OrderDTO;
import com.sdu.sell.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @Description:
 * @Author: YTF
 * @Date: 2020/5/11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PushMessageServiceImplTest {

    @Autowired
    private PushMessageServiceImpl pushMessageService;

    @Autowired
    private OrderService orderService;

    @Test
    public void orderStatus(){
        OrderDTO orderDTO = orderService.findOne("1588900958663718399");
        pushMessageService.orderStatus(orderDTO);
    }

}