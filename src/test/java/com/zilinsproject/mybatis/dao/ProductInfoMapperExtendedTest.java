package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoMapperExtendedTest {

    @Autowired
    private ProductInfoMapperExtended productInfoMapper;


    @Test
    public void selectAllTest (){
        List<ProductInfo> products = productInfoMapper.selectAll();
        Assert.assertNotEquals(0, products.size());
    }

    @Test
    public void insertAutoTest(){
        ProductInfo product = new ProductInfo();
        product.setProduct_name("火龙果");
        BigDecimal price = new BigDecimal(12.45);
        product.setProduct_price(price);
        product.setProduct_stock(30);
        product.setCategory_type(5);
        product.setProduct_description("新鲜火龙果，不甜不要钱");
        product.setProduct_image("fruit.jpg");
        product.setSaleable(true);
        product.setValid(true);
        productInfoMapper.insertAutoFill(product);
    }

    @Test
    @Transactional
    public void updateSaleableTest(){
        ProductInfo product = productInfoMapper.selectByPrimaryKey(1);
        product.setSaleable(false);
        productInfoMapper.updateSaleable(product);
        Boolean check = productInfoMapper.selectByPrimaryKey(1).getSaleable();
        System.out.println(check);
    }

    @Test
    @Transactional
    public void updateByPKTest(){
        ProductInfo product = productInfoMapper.selectByPrimaryKey(1);
        product.setProduct_image("newImageFor1222.jpg");
        productInfoMapper.updateByPrimaryKey(product);
        ProductInfo updatedProduct = productInfoMapper.selectByPrimaryKey(1);
        System.out.println(updatedProduct.toString());
    }

    @Test
    public void selectByPrimaryKeyTest(){
        ProductInfo product = productInfoMapper.selectByPrimaryKey(1);
        Assert.assertEquals(new Integer(1), product.getProduct_id());
    }



}