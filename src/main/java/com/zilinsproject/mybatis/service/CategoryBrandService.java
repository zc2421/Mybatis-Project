package com.zilinsproject.mybatis.service;

import com.zilinsproject.mybatis.dao.TCategoryBrandMapper;
import com.zilinsproject.mybatis.entity.TCategoryBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zilinsmac
 */
@Service
public class CategoryBrandService implements TCategoryBrandMapper {

    @Autowired
    private TCategoryBrandMapper tCategoryBrandMapper;

    @Override
    public int deleteByPrimaryKey(@Param("category_id") Integer category_id, @Param("brand_id") Integer brand_id) {
        return tCategoryBrandMapper.deleteByPrimaryKey(category_id, brand_id);
    }

    @Override
    public int insert(TCategoryBrand record){return tCategoryBrandMapper.insert(record); }

    @Override
    public List<TCategoryBrand> selectAll(){ return tCategoryBrandMapper.selectAll();}
}


