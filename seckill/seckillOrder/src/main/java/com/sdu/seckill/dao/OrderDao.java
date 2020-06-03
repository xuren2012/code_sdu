package com.sdu.seckill.dao;

import java.util.Map;

/**
 * @Description:
 * @Author: YTF
 * @Date: 2020/5/27
 */
public interface OrderDao {

    public Map<String,Object> insertOrder(Map<String,Object> orderInfo);

    public boolean updateOrderStatus(String order_id);
}
