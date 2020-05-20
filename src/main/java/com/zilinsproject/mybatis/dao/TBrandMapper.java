package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.TBrand;
import java.util.List;

public interface TBrandMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TBrand record);

    TBrand selectByPrimaryKey(Integer id);

    List<TBrand> selectAll();

    int updateByPrimaryKey(TBrand record);
}