package com.daishuai.wechatsell.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/7/8 16:41
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Data
public class CarDTO implements Serializable {

    private String productId;

    private Integer productQuantity;

    public CarDTO(){};

    public CarDTO(String productId, Integer productQuantity){
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
