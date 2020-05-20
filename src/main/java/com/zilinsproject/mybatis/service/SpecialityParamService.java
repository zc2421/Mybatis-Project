package com.zilinsproject.mybatis.service;

import com.zilinsproject.mybatis.entity.TSpecParam;
import com.zilinsproject.mybatis.dao.TSpecParamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SpecialityParamService implements TSpecParamMapper{

    @Autowired
    private TSpecParamMapper tSpecParamMapper;

    @Override
    public int deleteByPrimaryKey(Integer id){ return tSpecParamMapper.deleteByPrimaryKey(id); }

    @Override
    public int insert(TSpecParam record){ return tSpecParamMapper.insert(record); }

    @Override
    public TSpecParam selectByPrimaryKey(Integer id){ return tSpecParamMapper.selectByPrimaryKey(id); }

    @Override
    public List<TSpecParam> selectAll() { return tSpecParamMapper.selectAll(); }

    @Override
    public int updateByPrimaryKey(TSpecParam record){ return tSpecParamMapper.updateByPrimaryKey(record);}
}
