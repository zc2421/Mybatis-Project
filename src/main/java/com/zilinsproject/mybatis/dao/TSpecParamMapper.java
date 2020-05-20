package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.TSpecParam;
import java.util.List;

public interface TSpecParamMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TSpecParam record);

    TSpecParam selectByPrimaryKey(Integer id);

    List<TSpecParam> selectAll();

    int updateByPrimaryKey(TSpecParam record);
}