package com.sdu.sell.converter;

import com.sdu.sell.dataobject.OrderMaster;
import com.sdu.sell.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 *             类转换器
 * @Author: YTF
 * @Date: 2020/4/25
 */
public class OrderMaster2OrderDTOConverter {

    public static OrderDTO convert(OrderMaster orderMaster){

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);

        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
        return orderMasterList.stream()
                .map(e->convert(e))
                .collect(Collectors.toList());

    }
}
