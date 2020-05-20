package com.zilinsproject.mybatis.service;

import com.zilinsproject.mybatis.dao.TSkuHistoryMapper;
import com.zilinsproject.mybatis.entity.TSkuHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zilinsmac
 */
@Service
public class ProductHistoryService implements TSkuHistoryMapper{

    @Autowired
    private TSkuHistoryMapper tSkuHistoryMapper;

    @Override
    public int deleteByPrimaryKey(Integer id){return tSkuHistoryMapper.deleteByPrimaryKey(id); }

    @Override
    public int insert(TSkuHistory record){ return tSkuHistoryMapper.insert(record); }

    @Override
    public TSkuHistory selectByPrimaryKey(Integer id){ return tSkuHistoryMapper.selectByPrimaryKey(id); }

    @Override
    public List<TSkuHistory> selectAll(){ return tSkuHistoryMapper.selectAll();}

    @Override
    public int updateByPrimaryKey(TSkuHistory record){ return tSkuHistoryMapper.updateByPrimaryKey(record); }



}
