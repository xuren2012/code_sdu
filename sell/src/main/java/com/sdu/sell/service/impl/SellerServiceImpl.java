package com.sdu.sell.service.impl;

import com.sdu.sell.dataobject.SellerInfo;
import com.sdu.sell.repository.SellerInfoRepository;
import com.sdu.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: YTF
 * @Date: 2020/5/10
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
