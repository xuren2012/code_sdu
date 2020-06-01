package com.sdu.sell.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.sdu.sell.dto.OrderDTO;

/**
 * @Description:
 *              支付接口
 * @Author: YTF
 * @Date: 2020/4/29
 */
public interface PayService {
    //void create(OrderDTO orderDTO);
    PayResponse create(OrderDTO orderDTO);

    PayResponse notify(String notifyData);

    RefundResponse refund(OrderDTO orderDTO);
}
