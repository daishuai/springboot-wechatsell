package com.daishuai.wechatsell.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Description: 卖家信息
 * @Author: daishuai
 * @CreateDate: 2018/7/1 15:55
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Entity
@Data
public class SellerInfo implements Serializable {

    @Id
    private String id; //卖家Id
    private String username; //用户名
    private String password; //密码
    private String openid;  //微信openid
}
