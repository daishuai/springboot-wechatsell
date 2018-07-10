package com.daishuai.wechatsell.service.impl;

import com.daishuai.wechatsell.constants.OrderStatusEnum;
import com.daishuai.wechatsell.constants.PayStatusEnum;
import com.daishuai.wechatsell.constants.ResultEnum;
import com.daishuai.wechatsell.converter.OrderMaster2OrderDTOConverter;
import com.daishuai.wechatsell.dto.CarDTO;
import com.daishuai.wechatsell.dto.OrderDTO;
import com.daishuai.wechatsell.entity.OrderDetail;
import com.daishuai.wechatsell.entity.OrderMaster;
import com.daishuai.wechatsell.entity.ProductInfo;
import com.daishuai.wechatsell.exception.SellException;
import com.daishuai.wechatsell.repository.OrderDetailRepository;
import com.daishuai.wechatsell.repository.OrderMasterRepository;
import com.daishuai.wechatsell.repository.ProductInfoRepository;
import com.daishuai.wechatsell.service.OrderService;
import com.daishuai.wechatsell.service.ProductService;
import com.daishuai.wechatsell.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/7/1 20:34
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Service
@Slf4j  //日志
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Autowired
    private ProductService productService;

    @Override
    @Transactional
    @CachePut(value = "orderDTO", key = "#orderDTO.orderId")
    public OrderDTO create(OrderDTO orderDTO) {

        //String orderId = UUID.randomUUID().toString().replace("-", "");
        String orderId = KeyUtil.getUniqueKey();

        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        //1.查询商品（数量，价格）
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        for(OrderDetail orderDetail : orderDetailList){
            ProductInfo productInfo = productInfoRepository.findOne(orderDetail.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //订单详情入库
            //属性拷贝
            //BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetail.setProductPrice(productInfo.getProductPrice());
            orderDetail.setProductIcon(productInfo.getProductIcon());
            orderDetail.setProductName(productInfo.getProductName());
            orderDetail.setOrderId(orderId);
            //orderDetail.setDetailId(UUID.randomUUID().toString().replace("-", ""));
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetailRepository.save(orderDetail);
        }

        //3.写入订单数据库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        orderDTO.setOrderAmount(orderAmount);
        orderDTO.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderDTO.setPayStatus(PayStatusEnum.WAIT.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);    //注意copy的顺序，属性为null也会被copy
        orderMasterRepository.save(orderMaster);
        //4.下单成功，扣库存
        //lambda表达式
        List<CarDTO> carDTOList = orderDTO.getOrderDetailList().stream().map(e -> new CarDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productService.decreaseStock(carDTOList);
        return orderDTO;
    }

    @Override
    @Cacheable(value = "orderDTO", key = "#orderId")
    public OrderDTO findOne(String orderId) {

        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if(orderMaster == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasters = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderMaster> orderMasterList = orderMasters.getContent();
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.converter(orderMasterList);
        Page<OrderDTO> orderDTOS = new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasters.getTotalElements());

        return orderDTOS;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        //1.判断订单状态：已完成的订单不能取消
        if(orderDTO.getOrderStatus() != OrderStatusEnum.NEW.getCode()){
            log.info("【取消订单】订单状态不正确，orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2.修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if(updateResult == null){
            log.error("【取消订单】更新失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //3.返回库存
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【取消订单】订单中无商品详情，orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CarDTO> carDTOList = orderDTO.getOrderDetailList().stream().map(e -> new CarDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
        productService.increaseStock(carDTOList);
        //4.如果已支付，需要退款
        if(orderMaster.getPayStatus() == PayStatusEnum.SUCCESS.getCode()){
            //TODO
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {

        OrderMaster orderMaster = new OrderMaster();
        //判断订单状态
        if(orderDTO.getOrderStatus() != OrderStatusEnum.NEW.getCode()){
            log.error("【完结订单】订单状态不正确，orderId={}，orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if(updateResult == null){
            log.error("【完结订单】更新失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO pay(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        //判断订单状态
        if(orderDTO.getOrderStatus() != OrderStatusEnum.NEW.getCode()){
            log.error("【订单支付】订单状态不正确，orderId={}，orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if(orderDTO.getPayStatus() != PayStatusEnum.WAIT.getCode()){
            log.error("【订单支付】支付状态不正确，orderId={}，payStatus={}", orderDTO.getOrderId(), orderDTO.getPayStatus());
            throw new SellException(ResultEnum.PAY_STATUS_ERROR);
        }
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if(updateResult == null){
            log.error("【订单支付】更新失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findAll(Pageable pageable) {

        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.converter(orderMasterPage.getContent());
        return new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterPage.getTotalElements());
    }
}
