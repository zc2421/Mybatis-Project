package com.zilinsproject.mybatis.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zilinsproject.mybatis.dao.ProductInfoMapperExtended;
import com.zilinsproject.mybatis.entity.ProductInfo;
import com.zilinsproject.mybatis.service.ProductPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
