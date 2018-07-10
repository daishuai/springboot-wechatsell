package com.daishuai.wechatsell.controller;

import com.daishuai.wechatsell.entity.ProductCategory;
import com.daishuai.wechatsell.entity.ProductInfo;
import com.daishuai.wechatsell.service.CategoryService;
import com.daishuai.wechatsell.service.ProductService;
import com.daishuai.wechatsell.vo.ProductInfoVo;
import com.daishuai.wechatsell.vo.ProductVo;
import com.daishuai.wechatsell.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2018/7/7 17:42
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVo list(){
        ResultVo<List<ProductVo>> responseVo = new ResultVo<>();
        //1、查询所有在架商品
        List<ProductInfo> upAll = productService.findUpAll();
        //2、查询所有类目
        List<Integer> categoryTypes = new ArrayList<Integer>();
        for (ProductInfo info : upAll){
            categoryTypes.add(info.getCategoryType());
        }
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypes);
        //3、拼接
        List<ProductVo> productVoList = new ArrayList<>();
        for (ProductCategory category : categoryList){
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(category.getCategoryName());
            productVo.setCategoryType(category.getCategoryType());
            List<ProductInfoVo> infoVos = new ArrayList<ProductInfoVo>();
            for (ProductInfo info : upAll){
                if(info.getCategoryType() == category.getCategoryType()){
                    ProductInfoVo infoVo = new ProductInfoVo();
                    infoVo.setProductId(info.getProductId());
                    infoVo.setProductName(info.getProductName());
                    infoVo.setProductDescription(info.getProductDescription());
                    infoVo.setProductPrice(info.getProductPrice());
                    infoVo.setProductIcon(info.getProductIcon());
                    infoVos.add(infoVo);
                }
            }
            productVo.setProductInfoVos(infoVos);
            productVoList.add(productVo);
        }
        responseVo.setData(productVoList);
        responseVo.setCode(0);
        responseVo.setMsg("处理成功！");
        return responseVo;
    }
}
