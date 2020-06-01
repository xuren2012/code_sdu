package com.sdu.sell.exception;

import com.sdu.sell.enums.ResultEnum;

/**
 * @Description:
 * @Author: YTF
 * @Date: 2020/4/24
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code,String message) {
        super(message);
        this.code = code;
    }
}
