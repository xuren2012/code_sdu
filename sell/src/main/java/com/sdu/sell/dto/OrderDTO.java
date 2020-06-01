package com.sdu.sell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.sdu.sell.dataobject.OrderDetail;
import com.sdu.sell.enums.OrderStatusEnum;
import com.sdu.sell.enums.PayStatusEnum;
import com.sdu.sell.utils.EnumUtil;
import com.sdu.sell.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 *              各层间传输的数据对象--data transfer object
 * @Author: YTF
 * @Date: 2020/4/24
 */

@Data
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    //订单id
    private String orderId;

    //买家姓名
    private String buyerName;

    //买家电话
    private String buyerPhone;

    //买家地址
    private String buyerAddress;

    //买家微信Openid
    private String buyerOpenid;

    //订单总额
    private BigDecimal orderAmount;

    //订单状态，默认为0--新下单
    private Integer orderStatus;

    //支付状态，默认为0--未支付
    private Integer payStatus;

    //创建时间
    @JsonSerialize(using= Date2LongSerializer.class)
    private Date createTime;

    //更新时间
    @JsonSerialize(using= Date2LongSerializer.class)
    private Date updateTime;

    //订单详情列表
    List<OrderDetail> orderDetailList;

//    public OrderStatusEnum getOrderStatusEnum(){
//        return OrderStatusEnum.getOrderStatusEnum(orderStatus);
//    }
//
//    public PayStatusEnum getPayStatusEnum(){
//        payStatus
//    }

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus,OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }

}
