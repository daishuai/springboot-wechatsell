package com.daishuai.wechatsell.constants;

import lombok.Getter;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/7/7 17:09
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Getter
public enum ProductStatusEnum {
    UP(0, "在架"),
    DOWN(1, "下架");
    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
