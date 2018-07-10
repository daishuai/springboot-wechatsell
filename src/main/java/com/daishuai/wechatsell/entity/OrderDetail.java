package com.daishuai.wechatsell.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description: 订单商品
 * @Author: daishuai
 * @CreateDate: 2018/7/1 15:46
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Entity
@Data
public class OrderDetail implements Serializable {

    @Id
    private String detailId;
    private String orderId; //订单Id
    private String productId; //商品Id
    private String productName; //商品名称
    private BigDecimal productPrice = BigDecimal.valueOf(0L).setScale(2,4); //商品价格
    private Integer productQuantity; //商品数量
    private String productIcon; //商品图标

}
