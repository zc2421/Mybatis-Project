package com.zilinsproject.mybatis.service.imp;

import com.zilinsproject.mybatis.dao.ProductCategoryMapperExtended;
import com.zilinsproject.mybatis.entity.ProductCategory;
import com.zilinsproject.mybatis.enums.ResultEnum;
import com.zilinsproject.mybatis.exceptions.CustomizeException;
import com.zilinsproject.mybatis.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zilinsmac
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryMapperExtended categoryMapper;


    @Override
    public ProductCategory selectByPrimaryKey(Integer category_id) {
        ProductCategory cat = categoryMapper.selectByPrimaryKey(category_id);
        if (cat == null){
            throw new CustomizeException(ResultEnum.CATEGORY_NOT_EXIST);
        }
        return categoryMapper.selectByPrimaryKey(category_id);
    }

    @Override
    public List<ProductCategory> selectAllValid() {
        return categoryMapper.selectAllValid();
    }

    @Override
    @Transactional(rollbackFor = CustomizeException.class)
    public int saveCategory(ProductCategory record) {
        if (record.getCategory_id() == null){
            return categoryMapper.insertAutoFill(record);
        }
        ProductCategory productCategory = categoryMapper.selectByPrimaryKey(record.getCategory_id());
        if (productCategory == null){
            throw new CustomizeException(ResultEnum.CATEGORY_NOT_EXIST);
        }
        return categoryMapper.updateCategory(record);
    }

    @Override
    @Transactional(rollbackFor = CustomizeException.class)
    public int deleteCategory(Integer category_id) {
        ProductCategory productCategory = categoryMapper.selectByPrimaryKey(category_id);
        if (productCategory == null){
            throw new CustomizeException(ResultEnum.CATEGORY_NOT_EXIST);
        }
        if (productCategory.getIs_deleted()){
            throw new CustomizeException(ResultEnum.CATEGORY_STATUS_ERROR);
        }
        return categoryMapper.deleteCategory(category_id);
    }
}
