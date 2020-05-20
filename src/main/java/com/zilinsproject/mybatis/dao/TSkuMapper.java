package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.TSku;
import java.util.List;

public interface TSkuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TSku record);

    TSku selectByPrimaryKey(Integer id);

    List<TSku> selectAll();

    int updateByPrimaryKey(TSku record);
}