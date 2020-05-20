package com.zilinsproject.mybatis.controller;

import com.zilinsproject.mybatis.entity.*;
import com.zilinsproject.mybatis.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import java.util.List;
import java.util.Locale;


/**
 * @author zilinsmac
 */


@Controller
@RequestMapping("/product-manager")
public class ProductManagerController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping
    public String getAllProducts(Model model){
        List<TSku> products = productService.selectAll();
        model.addAttribute("products", products);
        return "list-products";
    }

    @RequestMapping("edit-product")
    public String editProduct(Model model){
        TSku newProduct = new TSku();
        model.addAttribute("newProduct", newProduct);
        return "edit-product";
    }

    @RequestMapping("list-category")
    public String editCategory(Model model){
        List<TCategory> categories = categoryService.selectAll();
        model.addAttribute("categories", categories);
        return "list-category";
    }

}
