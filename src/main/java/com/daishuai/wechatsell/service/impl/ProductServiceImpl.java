package com.daishuai.wechatsell.service.impl;

import com.daishuai.wechatsell.constants.ProductStatusEnum;
import com.daishuai.wechatsell.constants.ResultEnum;
import com.daishuai.wechatsell.dto.CarDTO;
import com.daishuai.wechatsell.entity.ProductInfo;
import com.daishuai.wechatsell.exception.SellException;
import com.daishuai.wechatsell.repository.ProductInfoRepository;
import com.daishuai.wechatsell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/7/1 20:30
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.getOne(productId);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {

        return repository.findAll(pageable);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CarDTO> carDTOList) {
        for(CarDTO carDTO : carDTOList){
            ProductInfo productInfo = repository.findOne(carDTO.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + carDTO.getProductQuantity();
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CarDTO> carDTOList) {
        for (CarDTO carDTO : carDTOList){
            ProductInfo productInfo = repository.findOne(carDTO.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() - carDTO.getProductQuantity();
            if(result < 0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_EOOR);
            }
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

}
