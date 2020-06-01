package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.ProductInfo;

import java.util.List;

/**
 * @author zilinsmac
 */
public interface ProductInfoMapperExtended extends ProductInfoMapper {

    int insertAutoFill(ProductInfo record);

    int updateSaleable(ProductInfo record);

    int updateProductInfo(ProductInfo record);

    List<ProductInfo> getSaleableProducts(Boolean saleable);

    List<ProductInfo> getAllProductsOfCategory(Integer category_type);

    List<ProductInfo> getAllProductsByName(String product_name);


}
