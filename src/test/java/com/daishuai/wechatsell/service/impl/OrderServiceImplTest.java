package com.daishuai.wechatsell.service.impl;

import com.daishuai.wechatsell.dto.OrderDTO;
import com.daishuai.wechatsell.entity.OrderDetail;
import com.daishuai.wechatsell.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/7/8 18:51
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void create() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("Jack Ma");
        orderDTO.setBuyerAddress("江苏省苏州市工业园区");
        orderDTO.setBuyerOpenid("abc321");
        orderDTO.setBuyerPhone("15848962588");
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail order1 = new OrderDetail();
        order1.setProductId("bdabc829879a4f068136377709eb5d31");
        order1.setProductQuantity(2);
        orderDetailList.add(order1);
        OrderDetail order2 = new OrderDetail();
        order2.setProductId("440b0cdb3b68408488b4260dae09c6f4");
        order2.setProductQuantity(3);
        orderDetailList.add(order2);
        orderDTO.setOrderDetailList(orderDetailList);
        orderService.create(orderDTO);
    }

    @Test
    public void findOne() throws Exception {
    }

    @Test
    public void findList() throws Exception {
    }

    @Test
    public void cancel() throws Exception {
    }

    @Test
    public void finish() throws Exception {
    }

    @Test
    public void pay() throws Exception {
    }

}