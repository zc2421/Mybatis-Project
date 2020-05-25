package com.zilinsproject.mybatis.service;

import com.zilinsproject.mybatis.entity.ProductInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductInfo selectByPrimaryKey(Integer product_id);

    List<ProductInfo> selectAll();

    int saveProductInfo(ProductInfo record);

    List<ProductInfo> getSaleableProducts(Boolean saleable);

    int onSale(Integer product_id);

    int offSale(Integer product_id);

    List<ProductInfo> getProductsByCategory(Integer category_type);

    List<ProductInfo> getProductsByName(String product_name);


}
