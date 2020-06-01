package com.sdu.sell.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author: YTF
 * @Date: 2020/5/9
 */
@Data
public class ProductForm {

    private String productId;

    //名称
    private String productName;

    //单价
    private BigDecimal productPrice;

    //库存
    private Integer productStock;

    //描述
    private String productDescription;

    //图片
    private String productIcon;

    //类目
    private Integer categoryType;
}
