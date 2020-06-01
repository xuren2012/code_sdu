package com.sdu.sell.service;

import com.sdu.sell.dataobject.SellerInfo;

/**
 * @Description:
 * @Author: YTF
 * @Date: 2020/5/10
 */
public interface SellerService {

    SellerInfo findSellerInfoByOpenid(String openid);
}
