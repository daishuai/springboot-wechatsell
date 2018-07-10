package com.daishuai.wechatsell.service.impl;

import com.daishuai.wechatsell.entity.ProductInfo;
import com.daishuai.wechatsell.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/7/7 17:15
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceImplTest {

    @Autowired
    private ProductService service;

    @Test
    public void findAll() throws Exception {

        Pageable pageable = new PageRequest(0,1);
        Page<ProductInfo> all = service.findAll(pageable);
        System.out.println(all.getTotalPages());
        System.out.println(all.getTotalElements());
        List<ProductInfo> infos = all.getContent();
        for(ProductInfo productInfo : infos){
            System.out.println(productInfo.getProductName());
        }
    }

    @Test
    public void save() throws Exception {
        ProductInfo productInfo = new ProductInfo();
        String uuid = UUID.randomUUID().toString().replace("-","");
        productInfo.setProductId(uuid);
        productInfo.setCategoryType(101);
        productInfo.setProductDescription("最爱的小龙虾，爽！");
        productInfo.setProductName("麻辣小龙虾");
        productInfo.setProductIcon("c:/");
        productInfo.setProductPrice(BigDecimal.valueOf(53.5));
        productInfo.setProductStatus(0);
        productInfo.setProductStock(100);

        service.save(productInfo);

    }

}