package com.sdu.sell.VO;

import lombok.Data;

/**
 * @Description:
 *              http请求返回数据的最外层对象
 * @Author: YTF
 * @Date: 2020/4/23
 */
@Data
public class ResultVO<T> {

    //错误码
    private Integer code;

    //提示信息
    private String msg;

    //具体数据
    private T data;

}
