package com.sdu.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @Description:
 * @Author: YTF
 * @Date: 2020/4/24
 */

@Entity
@Data
public class OrderDetail {

    @Id
    private String detailId;

    //订单id
    private String orderId;

    //商品id
    private String productId;

    //商品名称
    private String productName;

    //商品单价
    private BigDecimal productPrice;

    //商品数量
    private Integer productQuantity;

    //商品图片
    private String productIcon;
}
