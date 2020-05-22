package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.ProductInfo;

/**
 * @author zilinsmac
 */
public interface ProductInfoMapperExtended extends ProductInfoMapper {

    int insertAutoFill(ProductInfo record);

    int updateSaleable(ProductInfo record);

}
