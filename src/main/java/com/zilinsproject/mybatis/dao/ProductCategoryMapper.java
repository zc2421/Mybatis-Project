package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.ProductCategory;
import java.util.List;

public interface ProductCategoryMapper {
    int deleteByPrimaryKey(Integer category_id);

    int insert(ProductCategory record);

    ProductCategory selectByPrimaryKey(Integer category_id);

    List<ProductCategory> selectAll();

    int updateByPrimaryKey(ProductCategory record);
}