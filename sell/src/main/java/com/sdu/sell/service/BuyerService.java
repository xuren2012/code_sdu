package com.sdu.sell.service;

import com.sdu.sell.dto.OrderDTO;

/**
 * @Description:
 * @Author: YTF
 * @Date: 2020/4/26
 */
public interface BuyerService {

    //查询一个订单
    OrderDTO findOrderOne(String openid,String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid,String orderId);
}
