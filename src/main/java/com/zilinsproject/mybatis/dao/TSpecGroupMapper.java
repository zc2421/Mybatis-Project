package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.TSpecGroup;
import java.util.List;

public interface TSpecGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TSpecGroup record);

    TSpecGroup selectByPrimaryKey(Integer id);

    List<TSpecGroup> selectAll();

    int updateByPrimaryKey(TSpecGroup record);
}