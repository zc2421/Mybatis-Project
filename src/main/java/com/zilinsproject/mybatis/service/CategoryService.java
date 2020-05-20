package com.zilinsproject.mybatis.service;


import com.zilinsproject.mybatis.entity.TCategory;
import com.zilinsproject.mybatis.dao.TCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zilinsmac
 */

@Service
public class CategoryService implements TCategoryMapper{
    @Autowired
    private TCategoryMapper tCategoryMapper;

    @Override
    public int deleteByPrimaryKey(Integer id){ return tCategoryMapper.deleteByPrimaryKey(id); }

    @Override
    public int insert(TCategory record){ return tCategoryMapper.insert(record); }

    @Override
    public TCategory selectByPrimaryKey(Integer id){ return tCategoryMapper.selectByPrimaryKey(id); }

    @Override
    public List<TCategory> selectAll() {return tCategoryMapper.selectAll(); }

    @Override
    public int updateByPrimaryKey(TCategory record) {return tCategoryMapper.updateByPrimaryKey(record); }


}
