package com.daishuai.wechatsell;

import com.daishuai.wechatsell.entity.ProductCategory;
import com.daishuai.wechatsell.repository.ProductCategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/7/6 10:55
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(4);
        productCategory.setCategoryName("打折促销");
        productCategory.setCategoryType(104);
        repository.save(productCategory);
    }

    @Test
    public void findByProductCategoryTypeInTest(){
        List<Integer> categoryTypeList = Arrays.asList(102,103,104);
        List<ProductCategory> list = repository.findByCategoryTypeIn(categoryTypeList);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getCategoryName());
        }
    }
}
