package com.daishuai.wechatsell.repository;

import com.daishuai.wechatsell.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/7/1 16:01
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    List<OrderDetail> findByOrOrderId(String orderId);
}
