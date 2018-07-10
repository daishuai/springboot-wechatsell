package com.daishuai.wechatsell.service;

import com.daishuai.wechatsell.dto.CarDTO;
import com.daishuai.wechatsell.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/7/1 20:28
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public interface ProductService {

    ProductInfo findOne(String productId);

    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 查询所有在架商品
     * @return
     */
    List<ProductInfo> findUpAll();

    ProductInfo save(ProductInfo productInfo);

    void increaseStock(List<CarDTO> carDTOList);

    void decreaseStock(List<CarDTO> carDTOList);
}
