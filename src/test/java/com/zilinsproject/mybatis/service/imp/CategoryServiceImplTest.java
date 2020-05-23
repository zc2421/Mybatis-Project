package com.zilinsproject.mybatis.service.imp;

import com.zilinsproject.mybatis.entity.ProductCategory;
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
public class CategoryServiceImplTest {
    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void selectByPrimaryKey() {
        ProductCategory cat = categoryService.selectByPrimaryKey(3);
        System.out.println(cat.getCategory_name());
    }

    @Test
    public void selectAllValid() {
        List<ProductCategory> clist = categoryService.selectAllValid();
        System.out.println(clist.size());
    }

    @Test
    @Transactional
    public void saveCategory(){
        ProductCategory cat = categoryService.selectByPrimaryKey(3);
        cat.setCategory_name("test name");
        categoryService.saveCategory(cat);
        ProductCategory newCat = categoryService.selectByPrimaryKey(3);
        System.out.println(cat.toString());
    }

    @Test
    @Transactional
    public void deleteCategory() {
        categoryService.deleteCategory(3);
        ProductCategory cat = categoryService.selectByPrimaryKey(3);
        System.out.println(cat.toString());
    }
}