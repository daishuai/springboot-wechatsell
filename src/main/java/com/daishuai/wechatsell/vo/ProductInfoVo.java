package com.daishuai.wechatsell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/7/7 17:52
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Data
public class ProductInfoVo implements Serializable{
    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;
}
