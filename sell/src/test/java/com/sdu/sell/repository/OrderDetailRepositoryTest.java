package com.sdu.sell.repository;

import com.sdu.sell.dataobject.OrderDetail;
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
 * @Date: 2020/4/24
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123456002");
        orderDetail.setOrderId("158851002");
        orderDetail.setProductIcon("http://a.jpg");
        orderDetail.setProductId("1234560001");
        orderDetail.setProductName("ProductName");
        orderDetail.setProductPrice(new BigDecimal(43.0));
        orderDetail.setProductQuantity(100);

        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderId(){
        List<OrderDetail> orderDetailList = repository.findByOrderId("158851001");
        Assert.assertNotEquals(0,orderDetailList.size());
    }

}