package com.daishuai.wechatsell.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 商品类目
 * @Author: daishuai
 * @CreateDate: 2018/7/1 14:21
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@Entity
@DynamicUpdate  //动态更新
public class ProductCategory implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId; //类目Id

    private String categoryName; //类目名称

    private Integer categoryType; //类目编号

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

}
