package com.sdu.seckill.dao;

import java.util.Map;

/**
 * @Description:
 * @Author: YTF
 * @Date: 2020/5/27
 */
public interface StorageDao {

    public Map<String,Object> insertStorage(String sku_id,double in_quanty,double out_quanty);
}
