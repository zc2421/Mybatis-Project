package com.zilinsproject.mybatis.service;


import com.github.pagehelper.PageInfo;
import com.zilinsproject.mybatis.entity.ProductInfo;

/**
 * @author zilinsmac
 */
public interface ProductPageService {

    PageInfo<ProductInfo> listAllProducts(Integer pageNum, Integer pageSize);

    ProductInfo getProductById(Integer product_id);

    int minusProductStock(ProductInfo product);

    int plusProductStock(ProductInfo product);
}
