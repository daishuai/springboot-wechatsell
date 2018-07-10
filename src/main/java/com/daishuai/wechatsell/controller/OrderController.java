package com.daishuai.wechatsell.controller;

import com.daishuai.wechatsell.entity.OrderMaster;
import com.daishuai.wechatsell.service.OrderService;
import com.daishuai.wechatsell.service.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/7/4 18:40
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @Autowired
    private WebSocket webSocket;

    @RequestMapping("/list")
    public String list(Map<String, Object> map){
        //注意：使用 freemarker作为模板引擎，map要作为方法参数传入，如果在方法体中定义无法传到页面，会报错
        //Map map = new HashMap();
        map.put("springboot","freemarker");
        List<OrderMaster> orders = new ArrayList<>();
        for(int i = 0; i< 4; i++){
            OrderMaster orderMaster = new OrderMaster();
            orderMaster.setOrderId(UUID.randomUUID().toString());
            orderMaster.setBuyerName("张三" + i + 1);
            orders.add(orderMaster);
        }
        map.put("orders", orders);
        //return new ModelAndView("order/list",map);
        return "order/list";
    }

    @GetMapping("/test")
    public void test(){
        webSocket.sendMessage("你有新的订单，请及时查收！！");
    }
}
