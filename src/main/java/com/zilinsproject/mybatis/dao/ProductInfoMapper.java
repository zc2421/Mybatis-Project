package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.ProductInfo;
import java.util.List;

public interface ProductInfoMapper {
    int deleteByPrimaryKey(Integer product_id);

    int insert(ProductInfo record);

    ProductInfo selectByPrimaryKey(Integer product_id);

    List<ProductInfo> selectAll();

    int updateByPrimaryKey(ProductInfo record);
}