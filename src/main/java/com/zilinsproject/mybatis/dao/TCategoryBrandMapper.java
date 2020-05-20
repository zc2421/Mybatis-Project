package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.TCategoryBrand;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TCategoryBrandMapper {
    int deleteByPrimaryKey(@Param("category_id") Integer category_id, @Param("brand_id") Integer brand_id);

    int insert(TCategoryBrand record);

    List<TCategoryBrand> selectAll();
}