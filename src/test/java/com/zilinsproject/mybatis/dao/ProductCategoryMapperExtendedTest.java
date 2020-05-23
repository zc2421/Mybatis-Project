package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryMapperExtendedTest {

    @Autowired ProductCategoryMapperExtended categoryMapper;


    @Test
    public void selectAllTest(){
        List<ProductCategory> cats = categoryMapper.selectAll();
        System.out.println(cats.size());
    }

}