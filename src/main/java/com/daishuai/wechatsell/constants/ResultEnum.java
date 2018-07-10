package com.daishuai.wechatsell.constants;

import lombok.Getter;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/7/8 15:56
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_EOOR(11, "库存不够"),
    ORDER_NOT_EXIST(12, "订单不存在"),
    ORDERDETAIL_NOT_EXIST(13, "订单详情不存在"),
    ORDER_STATUS_ERROR(14, "订单状态不正确"),
    ORDER_UPDATE_FAIL(15, "订单更新失败"),
    ORDER_DETAIL_EMPTY(16, "订单详情为空"),
    PAY_STATUS_ERROR(17, "支付状态不正确"),
    PARAM_ERROR(1, "参数不正确"),
    CAR_EMPTY(18, "购物车为空"),
    ORDER_OWNER_ERROR(19, "该订单不属于当前用户"),
    SUCCESS(0, "成功"),
    ORDER_CANCEL_SUCCESS(19, "订单取消成功"),
    ORDER_FINISH_SUCCESS(20, "订单完结成功");

    private Integer code;

    private String message;

    ResultEnum (Integer code, String message){
        this.code = code;
        this.message = message;
    }

}
