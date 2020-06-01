package com.sdu.sell.repository;

import com.sdu.sell.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description:
 * @Author: YTF
 * @Date: 2020/4/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("100012");
        productInfo.setProductName("甜瓜");
        productInfo.setProductPrice(new BigDecimal(6.0));
        productInfo.setProductDescription("甜瓜财务自由啦...");
        productInfo.setProductStock(100);
        productInfo.setProductIcon("http://f.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(10);
        ProductInfo result = repository.save(productInfo);
        Assert.assertNotNull(result);

    }

    @Test
    public void findByProductStatus(){
        List<ProductInfo> productInfoList = repository.findByProductStatus(0);
        Assert.assertNotEquals(0,productInfoList.size());
    }

}