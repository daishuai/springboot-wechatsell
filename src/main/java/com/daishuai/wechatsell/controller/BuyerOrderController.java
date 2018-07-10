package com.daishuai.wechatsell.controller;

import com.daishuai.wechatsell.constants.ResultEnum;
import com.daishuai.wechatsell.converter.OrderForm2OrderDTOConverter;
import com.daishuai.wechatsell.dto.OrderDTO;
import com.daishuai.wechatsell.entity.OrderMaster;
import com.daishuai.wechatsell.exception.SellException;
import com.daishuai.wechatsell.form.OrderForm;
import com.daishuai.wechatsell.service.BuyerService;
import com.daishuai.wechatsell.service.OrderService;
import com.daishuai.wechatsell.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/7/8 21:16
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    @PostMapping("/create")
    public ResultVo<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确");
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.converter(orderForm);

        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CAR_EMPTY);
        }
        OrderDTO createResult = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        if(createResult == null){

        }
        map.put("orderId",createResult.getOrderId());
        return ResultVo.success(map);
    }


    @GetMapping("/list")
    public ResultVo<List<OrderDTO>> list(@RequestParam(value = "openid") String openid,
                                         @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", required = false, defaultValue = "10") Integer size){
        if(StringUtils.isEmpty(openid)){
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        Pageable pageable = new PageRequest(page,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, pageable);

        return ResultVo.success(orderDTOPage.getContent());

    }

    @GetMapping("/detail")
    public ResultVo<OrderDTO> orderDetail(@RequestParam("openid") String openid,
                                          @RequestParam("orderId") String orderId){
        //TODO 不安全的做法，已改进
        //OrderDTO orderDTO = orderService.findOne(orderId);
        //改进后
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return ResultVo.success(orderDTO);
    }

    @PostMapping("/cancel")
    public ResultVo cancelOrder(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId){
        //TODO 不安全的做法 已改进
        /*OrderDTO orderDTO = orderService.findOne(orderId);
        orderService.cancel(orderDTO);*/
        //改进后
        buyerService.cancelOrder(openid, orderId);
        return ResultVo.success();
    }

}
