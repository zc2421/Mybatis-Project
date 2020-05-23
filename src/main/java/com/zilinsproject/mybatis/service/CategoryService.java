package com.zilinsproject.mybatis.service;

import com.zilinsproject.mybatis.entity.ProductCategory;

import java.util.List;

/**
 * @author zilinsmac
 */

public interface CategoryService {

    int insert(ProductCategory record);

    ProductCategory selectByPrimaryKey(Integer category_id);

    List<ProductCategory> selectAll();

    int updateByPrimaryKey(ProductCategory record);
}
