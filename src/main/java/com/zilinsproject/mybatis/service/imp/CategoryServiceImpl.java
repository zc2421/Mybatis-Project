package com.zilinsproject.mybatis.service.imp;

import com.zilinsproject.mybatis.dao.ProductCategoryMapperExtended;
import com.zilinsproject.mybatis.dao.ProductInfoMapperExtended;
import com.zilinsproject.mybatis.entity.ProductCategory;
import com.zilinsproject.mybatis.entity.ProductInfo;
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
    @Autowired
    private ProductInfoMapperExtended productMapper;


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
        /*插入新类别*/
        if (record.getCategory_id() == null){
            List<ProductCategory> existCategories = categoryMapper.selectAllByType(record.getCategory_type());
            if (existCategories.size() >= 1){
                throw new CustomizeException(ResultEnum.CATEGORY_DUPLICATE_ERROR);
            }
            return categoryMapper.insertAutoFill(record);
        }

        /*更新已有类别*/
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
        List<ProductInfo> existProducts = productMapper.getAllProductsOfCategory(productCategory.getCategory_type());
        if (existProducts.size() > 0){
            throw new CustomizeException(ResultEnum.CATEGORY_PRODUCT_EXIST_ERROR);
        }
        return categoryMapper.deleteCategory(category_id);
    }
}
