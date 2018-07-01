package com.daishuai.wechatsell.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
public class ProductCategory {

    @Id
    @GeneratedValue
    private Integer categoryId; //类目Id

    private String categoryName; //类目名称

    private Integer categoryType; //类目编号

    private Date createTime; //创建时间

    private Date updateTime; //更新时间

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
