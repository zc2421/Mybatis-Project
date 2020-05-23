package com.zilinsproject.mybatis.service.imp;

import com.zilinsproject.mybatis.dao.ProductCategoryMapperExtended;
import com.zilinsproject.mybatis.entity.ProductCategory;
import com.zilinsproject.mybatis.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zilinsmac
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryMapperExtended categoryMapper;


    @Override
    public int insert(ProductCategory record) {
        return categoryMapper.insert(record);
    }

    @Override
    public ProductCategory selectByPrimaryKey(Integer category_id) {
        return categoryMapper.selectByPrimaryKey(category_id);
    }

    @Override
    public List<ProductCategory> selectAll() {
        return categoryMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(ProductCategory record) {
        return categoryMapper.updateByPrimaryKey(record);
    }
}
