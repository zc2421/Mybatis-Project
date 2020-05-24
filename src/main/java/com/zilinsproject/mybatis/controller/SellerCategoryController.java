package com.zilinsproject.mybatis.controller;


import com.zilinsproject.mybatis.entity.ProductCategory;
import com.zilinsproject.mybatis.entity.ProductInfo;
import com.zilinsproject.mybatis.exceptions.CustomizeException;
import com.zilinsproject.mybatis.form.CategoryForm;
import com.zilinsproject.mybatis.form.ProductForm;
import com.zilinsproject.mybatis.service.imp.CategoryServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zilinsmac
 */
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;


    @GetMapping("/list")
    public ModelAndView getAllCategory(Map<String, Object> map){
        List<ProductCategory> catList = categoryService.selectAllValid();
        map.put("categoryList", catList);
        return new ModelAndView("category/list-category", map);
    }

    @GetMapping("/delete")
    public ModelAndView deleteCategory(@RequestParam("category_id") Integer category_id,
                                       Map<String, Object>map)
    {
        try{
            categoryService.deleteCategory(category_id);
        } catch (CustomizeException e){
            map.put("msg", e.getMessage());
            map.put("url", "/seller/category/list");
            return new ModelAndView("/common/error", map);
        }
        map.put("url", "/seller/category/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "category_id", required = false) Integer category_id,
                              Map<String, Object> map){
        if (!StringUtils.isEmpty(category_id)){
            try{
                ProductCategory category = categoryService.selectByPrimaryKey(category_id);
                map.put("category", category);
            } catch (CustomizeException e){
                map.put("msg", e.getMessage());
                map.put("url", "/seller/category/list");
                return new ModelAndView("/common/error",map);
            }
        } else {
            map.put("category", new ProductCategory());
        }

        List<ProductCategory> cats = categoryService.selectAllValid();
        if (cats == null){
            map.put("categoryList", new ArrayList<ProductCategory>());
        }else {
            map.put("categoryList", cats);
        }
        return new ModelAndView("category/edit-category", map);
    }

    @PostMapping("/save")
    public ModelAndView saveCategory(@Valid CategoryForm form,
                                    BindingResult bindingResult,
                                    Map<String, Object> map) {
        if (bindingResult.hasErrors()){
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/seller/category/list");
            return new ModelAndView("common/error", map);
        }

        ProductCategory productCategory = new ProductCategory();
        BeanUtils.copyProperties(form, productCategory);
        productCategory.setIs_deleted(false);

        try{
            categoryService.saveCategory(productCategory);

        } catch (CustomizeException e){
            map.put("msg", e.getMessage());
            map.put("url", "/seller/category/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/seller/category/list");
        return new ModelAndView("common/success", map);
    }



}
