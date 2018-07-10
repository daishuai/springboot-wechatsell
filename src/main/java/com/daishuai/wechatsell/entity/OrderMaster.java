package com.daishuai.wechatsell.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/7/1 15:41
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Entity
@Data
@DynamicUpdate //自动更新
@DynamicInsert //加此注解，数据中设置了默认值，model中可以不赋值，否则保存会报错
public class OrderMaster implements Serializable {

    @Id
    private String orderId; //订单Id
    private String buyerName; //买家姓名
    private String buyerPhone; //买家电话
    private String buyerAddress; //买家地址
    private String buyerOpenid; //买家微信Openid
    private BigDecimal orderAmount = BigDecimal.valueOf(0L).setScale(2,BigDecimal.ROUND_HALF_UP); //订单总金额
    private Integer orderStatus; //订单状态：0新下单，1已完结
    private Integer payStatus; //支付状态：0未支付，1已支付
    private Date createTime; //创建时间
    private Date updateTime; //更新时间



}