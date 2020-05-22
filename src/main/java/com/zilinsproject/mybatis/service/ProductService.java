package com.zilinsproject.mybatis.service;

import com.zilinsproject.mybatis.entity.ProductInfo;

import java.util.List;

public interface ProductService {

    ProductInfo selectByPrimaryKey(Integer product_id);

    List<ProductInfo> selectAll();

    int updateByPrimaryKey(ProductInfo record);

    int insert(ProductInfo record);

    int updateSaleable(ProductInfo record);
}
