package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.TWarehouseSku;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TWarehouseSkuMapper {
    int deleteByPrimaryKey(@Param("warehouse_id") Integer warehouse_id, @Param("sku_id") Integer sku_id);

    int insert(TWarehouseSku record);

    TWarehouseSku selectByPrimaryKey(@Param("warehouse_id") Integer warehouse_id, @Param("sku_id") Integer sku_id);

    List<TWarehouseSku> selectAll();

    int updateByPrimaryKey(TWarehouseSku record);
}