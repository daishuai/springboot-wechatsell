package com.daishuai.wechatsell.converter;

import com.daishuai.wechatsell.dto.OrderDTO;
import com.daishuai.wechatsell.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/7/8 20:24
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class OrderMaster2OrderDTOConverter {

    public static OrderDTO  converter(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);

        return orderDTO;
    }

    public static List<OrderDTO> converter(List<OrderMaster> orderMasterList){
        List<OrderDTO> orderDTOList = orderMasterList.stream().map(e -> converter(e)).collect(Collectors.toList());
        return orderDTOList;
    }
}
