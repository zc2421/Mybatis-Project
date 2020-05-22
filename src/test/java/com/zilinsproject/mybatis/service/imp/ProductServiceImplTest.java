package com.zilinsproject.mybatis.service.imp;

import com.zilinsproject.mybatis.entity.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void updateSaleable() {
    }
}