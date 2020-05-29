package com.zilinsproject.mybatis.service.imp;

import com.github.pagehelper.PageInfo;
import com.zilinsproject.mybatis.entity.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductPageServiceImplTest {

    @Autowired
    ProductPageServiceImpl productService;

    @Test
    public void listAllProducts() {
        PageInfo<ProductInfo> page = productService.listAllProducts(1, 5);
//        for (ProductInfo p: list){
//            System.out.println(p.toString());
//        }
        System.out.println(page.getPageNum());
    }
}