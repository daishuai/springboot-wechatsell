package com.daishuai.wechatsell.dto;

import com.daishuai.wechatsell.constants.OrderStatusEnum;
import com.daishuai.wechatsell.constants.PayStatusEnum;
import com.daishuai.wechatsell.entity.OrderDetail;
import com.daishuai.wechatsell.util.Date2LongSerializer;
import com.daishuai.wechatsell.util.EnumUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/7/8 15:37
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Data
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_NULL)  //值为null的属性不返回
public class OrderDTO implements Serializable{

    private String orderId; //订单Id

    private String buyerName; //买家姓名

    private String buyerPhone; //买家电话

    private String buyerAddress; //买家地址

    private String buyerOpenid; //买家微信Openid

    private BigDecimal orderAmount = BigDecimal.valueOf(0L).setScale(2,BigDecimal.ROUND_HALF_UP); //订单总金额

    private Integer orderStatus; //订单状态：0新下单，1已完结

    private Integer payStatus; //支付状态：0未支付，1已支付

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime; //创建时间

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime; //更新时间

    private List<OrderDetail> orderDetailList;

    @JsonIgnore //对象转JSON时忽略此方法
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }
}
