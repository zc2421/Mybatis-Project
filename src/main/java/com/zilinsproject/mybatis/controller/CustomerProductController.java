package com.zilinsproject.mybatis.controller;


import com.github.pagehelper.PageInfo;
import com.zilinsproject.mybatis.entity.ProductInfo;
import com.zilinsproject.mybatis.service.imp.ProductPageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;


/**
 * @author zilinsmac
 */
@Controller
@RequestMapping("/product")
public class CustomerProductController {

    @Autowired
    private ProductPageServiceImpl productService;


    @GetMapping("/index")
    public ModelAndView index (Map<String, Object> map,
                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize){
        PageInfo<ProductInfo> pageInfo = productService.listAllProducts(pageNum, pageSize);
        map.put("pageInfo", pageInfo);
        return new ModelAndView("customerProduct/list-product", map);
    }

    
}
