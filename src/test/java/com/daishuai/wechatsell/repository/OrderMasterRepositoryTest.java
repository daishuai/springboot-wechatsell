package com.daishuai.wechatsell.repository;

import com.daishuai.wechatsell.entity.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/7/8 14:54
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void findByBuyerOpenid() throws Exception {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(UUID.randomUUID().toString().replace("-",""));
        orderMaster.setBuyerName("TOM");
        orderMaster.setBuyerAddress("武汉市武昌区湖北省国税局");
        orderMaster.setBuyerOpenid("12321");
        orderMaster.setBuyerPhone("434234143134");
        orderMaster.setOrderStatus(0);
        orderMaster.setPayStatus(1);
        orderMaster.setOrderAmount(new BigDecimal(30.5));

        repository.save(orderMaster);
    }

}