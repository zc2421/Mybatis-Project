package com.zilinsproject.mybatis.service;

import com.zilinsproject.mybatis.dao.TBrandMapper;
import com.zilinsproject.mybatis.entity.TBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zilinsmac
 */
@Service
public class BrandService implements TBrandMapper{

    @Autowired
    private TBrandMapper tBrandMapper;

    @Override
    public int deleteByPrimaryKey(Integer id){return tBrandMapper.deleteByPrimaryKey(id); }

    @Override
    public int insert(TBrand record){ return tBrandMapper.insert(record); }

    @Override
    public TBrand selectByPrimaryKey(Integer id) { return tBrandMapper.selectByPrimaryKey(id); }

    @Override
    public List<TBrand> selectAll(){return tBrandMapper.selectAll(); }

    @Override
    public int updateByPrimaryKey(TBrand record){return tBrandMapper.updateByPrimaryKey(record); }
}
