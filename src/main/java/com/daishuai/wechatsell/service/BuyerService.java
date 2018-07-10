package com.daishuai.wechatsell.service;

import com.daishuai.wechatsell.dto.OrderDTO;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/7/8 23:04
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public interface BuyerService {

    OrderDTO findOrderOne(String openid, String orderId);

    OrderDTO cancelOrder(String openid, String orderId);


}
