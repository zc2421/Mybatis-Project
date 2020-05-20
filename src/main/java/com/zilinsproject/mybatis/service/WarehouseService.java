package com.zilinsproject.mybatis.service;

import com.zilinsproject.mybatis.dao.TWarehouseMapper;
import com.zilinsproject.mybatis.entity.TWarehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zilinsmac
 */
@Service
public class WarehouseService implements TWarehouseMapper{

    @Autowired
    private TWarehouseMapper tWarehouseMapper;

    @Override
    public int deleteByPrimaryKey(Integer id){ return tWarehouseMapper.deleteByPrimaryKey(id); }

    @Override
    public int insert(TWarehouse record){ return tWarehouseMapper.insert(record); }

    @Override
    public TWarehouse selectByPrimaryKey(Integer id){ return tWarehouseMapper.selectByPrimaryKey(id); }

    @Override
    public List<TWarehouse> selectAll(){ return tWarehouseMapper.selectAll(); }

    @Override
    public int updateByPrimaryKey(TWarehouse record) { return tWarehouseMapper.updateByPrimaryKey(record); }
}
