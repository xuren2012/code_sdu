package com.sdu.seckill.dao;

import java.util.ArrayList;
import java.util.Map;

/**
 * @Description:
 * @Author: YTF
 * @Date: 2020/5/26
 */
public interface StockDao {
    public ArrayList<Map<String, Object>> getStockList();

    public ArrayList<Map<String, Object>> getStock(String sku_id);

    public boolean insertLimitPolicy(Map<String, Object> policyInfo);
}
