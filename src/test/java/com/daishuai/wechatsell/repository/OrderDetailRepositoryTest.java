package com.daishuai.wechatsell.repository;

import com.daishuai.wechatsell.entity.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/7/8 15:19
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void save() throws Exception {

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId(UUID.randomUUID().toString().replace("-", ""));
        orderDetail.setOrderId("6b0982fac43c40e3b372cee17053f5ce");
        orderDetail.setProductId("174b44ea1f6645d4ab7bde0517a956e8");
        orderDetail.setProductName("美汁源");
        orderDetail.setProductIcon("c:/");
        orderDetail.setProductPrice(new BigDecimal(8.5));
        orderDetail.setProductQuantity(1);

        repository.save(orderDetail);

    }

    @Test
    public void findByOrOrderId() throws Exception {
    }

}