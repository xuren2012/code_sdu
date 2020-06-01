package com.sdu.sell.enums;

import lombok.Getter;

/**
 * @Description: 商品状态
 * @Author: YTF
 * @Date: 2020/4/23
 */
@Getter
public enum ProductStatusEnum implements CodeEnum{

    UP(0, "在架"),
    DOWN(1, "下架");

    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
