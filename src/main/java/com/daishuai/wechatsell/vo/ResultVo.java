package com.daishuai.wechatsell.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/7/7 17:42
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Getter
@Setter
public class ResultVo<T> {

    private Integer code;

    private String msg;

    private T data;

    public ResultVo(){}

    public ResultVo(Integer code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResultVo success(Object data){
        return new ResultVo(0, "处理成功！", data);
    }

    public static ResultVo success(){
        return ResultVo.success(null);
    }

    public static ResultVo error(Integer code, String msg){
        return new ResultVo(code, msg, null);
    }
}
