package com.zilinsproject.mybatis.service;

import com.zilinsproject.mybatis.entity.TCategoryHistory;
import com.zilinsproject.mybatis.dao.TCategoryHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zilinsmac
 */
@Service
public class CategoryHistoryMapper implements TCategoryHistoryMapper{

    @Autowired
    private TCategoryHistoryMapper tCategoryHistoryMapper;

    @Override
    public int deleteByPrimaryKey(Integer id){ return tCategoryHistoryMapper.deleteByPrimaryKey(id); }

    @Override
    public int insert(TCategoryHistory record){ return tCategoryHistoryMapper.insert(record); }

    @Override
    public TCategoryHistory selectByPrimaryKey(Integer id){return tCategoryHistoryMapper.selectByPrimaryKey(id); }

    @Override
    public List<TCategoryHistory> selectAll(){return tCategoryHistoryMapper.selectAll(); }

    @Override
    public int updateByPrimaryKey(TCategoryHistory record){return tCategoryHistoryMapper.updateByPrimaryKey(record); }
}
