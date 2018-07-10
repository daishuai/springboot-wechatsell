package com.daishuai.wechatsell;

import com.daishuai.wechatsell.entity.ProductInfo;
import com.daishuai.wechatsell.repository.ProductInfoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/7/1 18:27
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;


    @Test
    public void save(){
        ProductInfo productInfo = new ProductInfo();
        String uuid = UUID.randomUUID().toString().replace("-","");
        productInfo.setProductId(uuid);
        productInfo.setCategoryType(103);
        productInfo.setProductDescription("汤底可以喝的麻辣烫！！");
        productInfo.setProductName("麻辣烫");
        productInfo.setProductIcon("c:/");
        productInfo.setProductPrice(BigDecimal.valueOf(20.5));
        productInfo.setProductStatus(0);
        productInfo.setProductStock(100);
        ProductInfo save = repository.save(productInfo);
    }

    @Test
    public void findByProductStatus(){

        List<ProductInfo> productInfos = repository.findByProductStatus(0);
        System.out.println(productInfos.size());
        for (ProductInfo productInfo : productInfos){
            System.out.println(productInfo.getProductDescription());
        }
    }

}
