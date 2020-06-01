package com.zilinsproject.mybatis.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zilinsproject.mybatis.dao.ProductInfoMapperExtended;
import com.zilinsproject.mybatis.entity.ProductInfo;
import com.zilinsproject.mybatis.enums.ResultEnum;
import com.zilinsproject.mybatis.exceptions.CustomizeException;
import com.zilinsproject.mybatis.service.ProductPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品分页
 * @author zilinsmac
 */
@Service
public class ProductPageServiceImpl implements ProductPageService {

    @Autowired
    ProductInfoMapperExtended productMapper;

    @Override
    public PageInfo<ProductInfo> listAllProducts(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProductInfo> products = productMapper.selectAll();
        return new PageInfo<>(products);
    }

    @Override
    public ProductInfo getProductById(Integer product_id) {
        return productMapper.selectByPrimaryKey(product_id);
    }



    @Override
    @Transactional(rollbackFor = CustomizeException.class)
    public int minusProductStock(ProductInfo record) {
        //商品不存在
        ProductInfo productInfo = productMapper.selectByPrimaryKey(record.getProduct_id());
        if (productInfo == null){
            throw new CustomizeException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        //商品已售罄
        if (productInfo.getProduct_stock() <= 0) {
            throw new CustomizeException(ResultEnum.PRODUCT_OUT_OF_STOCK);
        }
        //减少库存
        productInfo.setProduct_stock(record.getProduct_stock());
        return productMapper.updateProductInfo(productInfo);
    }

    @Override
    @Transactional(rollbackFor = CustomizeException.class)
    public int plusProductStock(ProductInfo record) {
        //商品不存在
        ProductInfo productInfo = productMapper.selectByPrimaryKey(record.getProduct_id());
        if (productInfo == null){
            throw new CustomizeException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        productInfo.setProduct_stock(record.getProduct_stock());
        return productMapper.updateProductInfo(productInfo);
    }
}
