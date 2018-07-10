package com.daishuai.wechatsell.constants;

import lombok.Getter;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/7/8 14:44
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Getter
public enum PayStatusEnum implements CodeEnum{

    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功");

    private Integer code;

    private String message;

    PayStatusEnum (Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
