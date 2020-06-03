package com.sdu.seckill.service;

import java.util.Map;

/**
 * @Description:
 * @Author: YTF
 * @Date: 2020/5/26
 */
public interface StockService {

    public Map<String, Object> getStockList();

    public Map<String, Object> getStock(String sku_id);

    public Map<String, Object> insertLimitPolicy(Map<String, Object> policyInfo);
}
