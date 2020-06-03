package com.sdu.seckill.service;

import java.util.Map;

/**
 * @Description:
 * @Author: YTF
 * @Date: 2020/5/27
 */
public interface OrderService {

    public Map<String,Object> insertOrder(Map<String, Object> orderInfo);

    public Map<String, Object> createOrder(String sku_id, String user_id);

    public Map<String, Object> getOrder(String order_id);

    public Map<String, Object> payOrder(String order_id, String sku_id);

    public Map<String,Object> updateOrderStatus(String order_id);
}
