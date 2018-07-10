package com.daishuai.wechatsell.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/7/8 16:17
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class KeyUtil {

    public static synchronized String getUniqueKey(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String key = sdf.format(date);
        int random = new Random().nextInt(1000);
        return key + String.format("%04d", random);
    }
}
