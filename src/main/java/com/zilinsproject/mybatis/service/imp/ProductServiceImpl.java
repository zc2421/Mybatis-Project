package com.zilinsproject.mybatis.service.imp;

import com.zilinsproject.mybatis.dao.ProductInfoMapperExtended;
import com.zilinsproject.mybatis.entity.ProductInfo;
import com.zilinsproject.mybatis.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zilinsmac
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoMapperExtended productInfoMapper;

    @Override
    public ProductInfo selectByPrimaryKey(Integer product_id) {
        return productInfoMapper.selectByPrimaryKey(product_id);
    }

    @Override
    public List<ProductInfo> selectAll() {
        return productInfoMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(ProductInfo record) {
        return productInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public int insert(ProductInfo record) {
        return productInfoMapper.insertAutoFill(record);
    }

    @Override
    public int updateSaleable(ProductInfo record) {
        return productInfoMapper.updateSaleable(record);
    }
}
