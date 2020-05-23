package com.zilinsproject.mybatis.service;

import com.zilinsproject.mybatis.entity.ProductCategory;

import java.util.List;

/**
 * @author zilinsmac
 */

public interface CategoryService {


    ProductCategory selectByPrimaryKey(Integer category_id);

    List<ProductCategory> selectAllValid();

    int insert(ProductCategory record);

    int updateCategory(ProductCategory record);

    int deleteCategory(Integer category_id);

}
