package com.zilinsproject.mybatis.service.imp;

import com.zilinsproject.mybatis.entity.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void selectByPrimaryKeyTest() {
        ProductInfo product = productService.selectByPrimaryKey(1);
        Assert.assertEquals(new Integer(1), product.getProduct_id());
    }

    @Test
    public void selectAll() {
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void getSaleable(){
        List<ProductInfo> notForSale = productService.getSaleableProducts(false);
        System.out.println(notForSale.size());
    }

    @Test
    @Transactional
    public void onSaleTest(){
        productService.onSale(6);
        ProductInfo product = productService.selectByPrimaryKey(6);
        Assert.assertEquals(true, product.getSaleable());
    }

    @Test
    @Transactional
    public void offSaleTest(){
        productService.offSale(1);
        ProductInfo productInfo = productService.selectByPrimaryKey(1);
        Assert.assertEquals(false, productInfo.getSaleable());
    }

}