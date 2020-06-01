package com.sdu.sell.dto;

import lombok.Data;

/**
 * @Description:
 * @Author: YTF
 * @Date: 2020/4/24
 */
@Data
public class CartDTO {

    private String productId;
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
