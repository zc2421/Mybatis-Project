package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.ProductCategory;

import java.util.List;

/**
 * @author zilinsmac
 */

public interface ProductCategoryMapperExtended extends ProductCategoryMapper{

    int insertAutoFill(ProductCategory record);

    List<ProductCategory> selectAllValid();

    int updateCategory(ProductCategory record);

    int deleteCategory(Integer category_id);

    List<ProductCategory> selectAllByType(Integer category_type);

}
