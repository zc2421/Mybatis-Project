package com.zilinsproject.mybatis.service;

import com.zilinsproject.mybatis.dao.TSpecGroupMapper;
import com.zilinsproject.mybatis.entity.TSpecGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author zilinsmac
 */

@Service
public class SpecialityGroupService implements TSpecGroupMapper{

    @Autowired
    private TSpecGroupMapper tSpecGroupMapper;

    @Override
    public int deleteByPrimaryKey(Integer id){ return tSpecGroupMapper.deleteByPrimaryKey(id); }

    @Override
    public int insert(TSpecGroup record){ return tSpecGroupMapper.insert(record); }

    @Override
    public TSpecGroup selectByPrimaryKey(Integer id){ return tSpecGroupMapper.selectByPrimaryKey(id); }

    @Override
    public List<TSpecGroup> selectAll(){ return tSpecGroupMapper.selectAll(); }

    @Override
    public int updateByPrimaryKey(TSpecGroup record){ return tSpecGroupMapper.updateByPrimaryKey(record); }
}
