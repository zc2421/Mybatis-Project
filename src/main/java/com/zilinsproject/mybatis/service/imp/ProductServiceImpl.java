package com.zilinsproject.mybatis.service.imp;

import com.zilinsproject.mybatis.dao.ProductInfoMapperExtended;
import com.zilinsproject.mybatis.entity.ProductInfo;
import com.zilinsproject.mybatis.enums.ResultEnum;
import com.zilinsproject.mybatis.exceptions.CustomizeException;
import com.zilinsproject.mybatis.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = CustomizeException.class)
    public int saveProductInfo(ProductInfo record) {
        if (record.getProduct_id() == null){
            return productInfoMapper.insertAutoFill(record);
        }

        ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(record.getProduct_id());
        if (productInfo == null){
            throw new CustomizeException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        return productInfoMapper.updateProductInfo(record);
    }


    @Override
    public List<ProductInfo> getSaleableProducts(Boolean saleable) {
        return productInfoMapper.getSaleableProducts(saleable);
    }

    @Override
    @Transactional(rollbackFor = CustomizeException.class)
    public int onSale(Integer product_id) {
        //二判
        ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(product_id);
        if (productInfo == null){
            throw new CustomizeException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getSaleable()){
            throw new CustomizeException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        //三更新
        productInfo.setSaleable(true);
        return productInfoMapper.updateSaleable(productInfo);
    }

    @Override
    @Transactional(rollbackFor = CustomizeException.class)
    public int offSale(Integer product_id) {
        ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(product_id);
        if (productInfo == null){
            throw new CustomizeException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (!productInfo.getSaleable()){
            throw new CustomizeException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setSaleable(false);
        return productInfoMapper.updateSaleable(productInfo);
    }

    @Override
    public List<ProductInfo> getProductsByCategory(Integer category_type) {
        return productInfoMapper.getAllProductsOfCategory(category_type);
    }

    @Override
    public List<ProductInfo> getProductsByName(String product_name) {
        return productInfoMapper.getAllProductsByName(product_name);
    }
}
