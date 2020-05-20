package com.zilinsproject.mybatis.service;

import com.zilinsproject.mybatis.entity.TWarehouseSku;
import com.zilinsproject.mybatis.dao.TWarehouseSkuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zilinsmac
 */
@Service
public class WarehouseProductService implements TWarehouseSkuMapper{

    @Autowired
    private TWarehouseSkuMapper tWarehouseSkuMapper;

    @Override
    public int deleteByPrimaryKey(@Param("warehouse_id") Integer warehouseId, @Param("sku_id") Integer skuId){
        return tWarehouseSkuMapper.deleteByPrimaryKey(warehouseId, skuId);
    }

    @Override
    public int insert(TWarehouseSku record){ return tWarehouseSkuMapper.insert(record); }

    @Override
    public TWarehouseSku selectByPrimaryKey(@Param("warehouse_id") Integer warehouse_id, @Param("sku_id") Integer sku_id){
        return tWarehouseSkuMapper.selectByPrimaryKey(warehouse_id, sku_id);
    }

    @Override
    public List<TWarehouseSku> selectAll(){ return tWarehouseSkuMapper.selectAll(); }

    @Override
    public int updateByPrimaryKey(TWarehouseSku record){ return tWarehouseSkuMapper.updateByPrimaryKey(record); }


}
