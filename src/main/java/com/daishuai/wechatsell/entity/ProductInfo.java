package com.daishuai.wechatsell.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/7/1 14:26
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Entity
@DynamicUpdate  //动态更新
public class ProductInfo {

    @Id
    private String productId; //商品Id

    private String productName; //商品名称
    /** setScale(2,BigDecimal.ROUND_HALF_UP) 保留两位小数，向上四舍五入 */
    private BigDecimal productPrice = BigDecimal.valueOf(0L).setScale(2,BigDecimal.ROUND_HALF_UP); //商品价格

    private Integer productStock; //商品库存

    private String productDescription; //商品描述

    private String productIcon; //商品小图

    private Integer productStatus; //商品状态：0正常，1下架

    private Integer categoryId; //类目编号

    private Date createTime; //创建时间

    private Date updateTime; //更新时间

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductIcon() {
        return productIcon;
    }

    public void setProductIcon(String productIcon) {
        this.productIcon = productIcon;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
