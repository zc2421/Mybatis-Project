package com.zilinsproject.mybatis.controller;



import com.zilinsproject.mybatis.entity.ProductCategory;
import com.zilinsproject.mybatis.entity.ProductInfo;
import com.zilinsproject.mybatis.enums.ResultEnum;
import com.zilinsproject.mybatis.exceptions.CustomizeException;
import com.zilinsproject.mybatis.form.ProductForm;
import com.zilinsproject.mybatis.form.ProductSearchForm;
import com.zilinsproject.mybatis.service.imp.CategoryServiceImpl;
import com.zilinsproject.mybatis.service.imp.ProductServiceImpl;
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
 * 卖家商品管理页面
 * @author zilinsmac
 */

@Controller
@RequestMapping("/seller/product")
public class SellerProductController {
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    CategoryServiceImpl categoryService;


    @GetMapping("/list")
    public ModelAndView listOnSaleProducts(Map<String, Object> map){
        List<ProductInfo> products = productService.selectAll();
        map.put("products", products);
        return new ModelAndView("sellerProduct/list-product", map);
    }


    @GetMapping("/on_sale")
    public ModelAndView setProductOnSale(@RequestParam("product_id") Integer product_id, Map<String, Object> map){
        try {
            productService.onSale(product_id);
        } catch (CustomizeException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/off_sale")
    public ModelAndView setProductOffSale(@RequestParam("product_id") Integer product_id, Map<String, Object> map){
        try {
            productService.offSale(product_id);
        } catch (CustomizeException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/seller/product/list");
        return new ModelAndView("common/success", map);

    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "product_id", required = false) Integer product_id,
                      Map<String, Object> map){
        if (!StringUtils.isEmpty(product_id)){
            ProductInfo productInfo = productService.selectByPrimaryKey(product_id);
            if (productInfo == null){
                ResultEnum e = ResultEnum.PRODUCT_NOT_EXIST;
                map.put("msg", e.getMessage());
                map.put("url", "/seller/product/list");
                return new ModelAndView("/common/error", map);
            }
            map.put("productInfo", productInfo);
        } else {
            map.put("productInfo", new ProductInfo());
        }

        List<ProductCategory> cats = categoryService.selectAllValid();
        if (cats == null){
            map.put("categoryList", new ArrayList<ProductCategory>());
        }else{
            map.put("categoryList", cats);
        }
        return new ModelAndView("sellerProduct/edit-product");
    }


    @PostMapping("/save")
    public ModelAndView saveProduct(@Valid ProductForm form,
                                    BindingResult bindingResult,
                                    Map<String, Object> map) {
        if (bindingResult.hasErrors()){
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        ProductInfo productInfo = new ProductInfo();
        BeanUtils.copyProperties(form, productInfo);

        try{
            productService.saveProductInfo(productInfo);

        } catch (CustomizeException e){
            map.put("msg", e.getMessage());
            map.put("url", "/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        map.put("url", "/seller/product/list");
        return new ModelAndView("common/success", map);
    }


    @PostMapping("/search")
    public ModelAndView saveProduct(@Valid ProductSearchForm form,
                                    BindingResult bindingResult,
                                    Map<String, Object> map)
    {
        if (bindingResult.hasErrors()){
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/seller/product/list");
            return new ModelAndView("common/error", map);
        }

        String searchType = form.getSearchType();
        String searchValue = form.getSearchValue();
        List<ProductInfo> products = new ArrayList<>();
        String id = "id";
        String category_type = "category_type";

        if (id.equals(searchType)){
            ProductInfo p = productService.selectByPrimaryKey(Integer.parseInt(searchValue));
            if (p != null){ products.add(p); }
        } else if (category_type.equals(searchType)){
            List<ProductInfo> plist = productService.getProductsByCategory(Integer.parseInt(searchValue));
            products.addAll(plist);
        } else {
            List<ProductInfo> plist = productService.getProductsByName("%"+searchValue+"%");
            products.addAll(plist);
        }

        map.put("products", products);
        return new ModelAndView("sellerProduct/list-product", map);



    }





}
