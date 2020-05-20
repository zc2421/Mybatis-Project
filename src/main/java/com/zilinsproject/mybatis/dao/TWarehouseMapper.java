package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.TWarehouse;
import java.util.List;

public interface TWarehouseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TWarehouse record);

    TWarehouse selectByPrimaryKey(Integer id);

    List<TWarehouse> selectAll();

    int updateByPrimaryKey(TWarehouse record);
}