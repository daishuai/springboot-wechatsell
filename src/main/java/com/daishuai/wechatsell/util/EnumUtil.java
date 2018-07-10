package com.daishuai.wechatsell.util;

import com.daishuai.wechatsell.constants.CodeEnum;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/7/9 14:17
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){
        for(T each : enumClass.getEnumConstants()){
            if (each.getCode() == code){
                return each;
            }
        }
        return null;
    }
}
