package com.sdu.sell.service.impl;

import com.sdu.sell.dto.OrderDTO;
import com.sdu.sell.service.OrderService;
import com.sdu.sell.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author: YTF
 * @Date: 2020/4/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {

    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;

    @Test
    public void create() {
        //测试获取四大参数是否成功
        //OrderDTO orderDTO = new OrderDTO();
        //payService.create(orderDTO);

        OrderDTO orderDTO = new OrderDTO();
        orderDTO = orderService.findOne("1587881439817359557");
        payService.create(orderDTO);
    }

    //测试退款
    @Test
    public void refund(){
        OrderDTO orderDTO = orderService.findOne("");
        payService.refund(orderDTO);
    }

}