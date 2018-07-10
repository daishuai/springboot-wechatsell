package com.daishuai.wechatsell.exception;

import com.daishuai.wechatsell.constants.ResultEnum;
import lombok.Getter;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/7/8 15:55
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Getter
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message){
        super(message);
        this.code = code;
    }
}
