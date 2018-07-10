package com.daishuai.wechatsell.service;

import com.daishuai.wechatsell.entity.ProductCategory;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/7/1 20:24
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
