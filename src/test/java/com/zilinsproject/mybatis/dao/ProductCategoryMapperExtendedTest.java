package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.ProductCategory;
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
public class ProductCategoryMapperExtendedTest {

    @Autowired ProductCategoryMapperExtended categoryMapper;


    @Test
    public void selectAllTest(){
        List<ProductCategory> cats = categoryMapper.selectAll();
        System.out.println(cats.size());
    }

    @Test
    public void insertAutoFillTest(){
        ProductCategory pcat = new ProductCategory();
        pcat.setCategory_name("图书");
        pcat.setCategory_type(6);
        pcat.setIf_parent(false);
        pcat.setIs_deleted(false);
        categoryMapper.insertAutoFill(pcat);
    }

    @Test
    public void selectAllValidTest(){
        List<ProductCategory> validList = categoryMapper.selectAllValid();
        System.out.println(validList.size());
    }

    @Test
    @Transactional
    public void updateCategoryTest(){
        ProductCategory cat = categoryMapper.selectByPrimaryKey(5);
        cat.setCategory_type(10);
        categoryMapper.updateCategory(cat);
        ProductCategory newCat = categoryMapper.selectByPrimaryKey(5);
        Assert.assertEquals(new Integer(10), newCat.getCategory_type());
    }

    @Test
    @Transactional
    public void deleteCategoryTest(){
        categoryMapper.deleteCategory(3);
        ProductCategory cat = categoryMapper.selectByPrimaryKey(3);
        Assert.assertEquals(true,cat.getIs_deleted());
    }
}