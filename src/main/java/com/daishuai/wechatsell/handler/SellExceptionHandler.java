package com.daishuai.wechatsell.handler;

import com.daishuai.wechatsell.exception.SellException;
import com.daishuai.wechatsell.vo.ResultVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/7/10 0:25
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@ControllerAdvice
public class SellExceptionHandler {

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVo handleSellException(SellException e){
        return ResultVo.error(e.getCode(), e.getMessage());
    }
}
