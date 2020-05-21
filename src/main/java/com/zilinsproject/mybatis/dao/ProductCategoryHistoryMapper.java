package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.ProductCategoryHistory;
import java.util.List;

public interface ProductCategoryHistoryMapper {
    int deleteByPrimaryKey(Integer category_id);

    int insert(ProductCategoryHistory record);

    ProductCategoryHistory selectByPrimaryKey(Integer category_id);

    List<ProductCategoryHistory> selectAll();

    int updateByPrimaryKey(ProductCategoryHistory record);
}