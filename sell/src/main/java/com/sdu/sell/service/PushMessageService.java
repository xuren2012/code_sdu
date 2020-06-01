package com.sdu.sell.service;

import com.sdu.sell.dto.OrderDTO;

/**
 * @Description:
 *              推送消息
 * @Author: YTF
 * @Date: 2020/5/11
 */
public interface PushMessageService {

    /**
     * 订单状态变量消息
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}
