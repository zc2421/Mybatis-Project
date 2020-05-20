package com.zilinsproject.mybatis.service;

import com.zilinsproject.mybatis.entity.TSku;
import com.zilinsproject.mybatis.dao.TSkuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zilinsmac
 */

@Service
public class ProductService implements TSkuMapper{

    @Autowired
    private TSkuMapper tSkuMapper;

    @Override
    public int deleteByPrimaryKey(Integer id){ return tSkuMapper.deleteByPrimaryKey(id); }

    @Override
    public int insert(TSku record){ return tSkuMapper.insert(record); }

    @Override
    public TSku selectByPrimaryKey(Integer id){ return tSkuMapper.selectByPrimaryKey(id); }

    @Override
    public List<TSku> selectAll(){ return tSkuMapper.selectAll(); }

    @Override
    public int updateByPrimaryKey(TSku record){ return tSkuMapper.updateByPrimaryKey(record); }
}
