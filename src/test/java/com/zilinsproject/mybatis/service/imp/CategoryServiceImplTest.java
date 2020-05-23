package com.zilinsproject.mybatis.service.imp;

import com.zilinsproject.mybatis.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryMapper;

    @Test
    public void insert() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void selectAll() {
        List<ProductCategory> cats = categoryMapper.selectAllValid();
        Assert.assertNotEquals(0, cats.size());
    }

    @Test
    public void updateByPrimaryKey() {
    }
}