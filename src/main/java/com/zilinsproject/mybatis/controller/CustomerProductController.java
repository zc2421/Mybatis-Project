package com.zilinsproject.mybatis.controller;


import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.zilinsproject.mybatis.entity.CartInfo;
import com.zilinsproject.mybatis.entity.CartInfoList;
import com.zilinsproject.mybatis.entity.ProductInfo;
import com.zilinsproject.mybatis.enums.ResultEnum;
import com.zilinsproject.mybatis.exceptions.CustomizeException;
import com.zilinsproject.mybatis.service.imp.CartServiceImpl;
import com.zilinsproject.mybatis.service.imp.ProductPageServiceImpl;
import com.zilinsproject.mybatis.utils.CustomerConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author zilinsmac
 */
@Controller
@RequestMapping("/product")
public class CustomerProductController {

    @Autowired
    private ProductPageServiceImpl productPageService;
    @Autowired
    private Gson gson;
    @Autowired
    private CartServiceImpl cartService;


    @GetMapping("/index")
    public ModelAndView index (Map<String, Object> map,
                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                               HttpSession session){
        PageInfo<ProductInfo> pageInfo = productPageService.listAllProducts(pageNum, pageSize);
        Integer user_id = (Integer) session.getAttribute(CustomerConst.CURRENT_USER);
        if (user_id == null){
            map.put("login", false);
        }else{
            map.put("login", true);
        }
        map.put("pageInfo", pageInfo);
        return new ModelAndView("customerProduct/list-product", map);
    }


    @GetMapping("/addCart")
    public ModelAndView add2Cart (@RequestParam("product_id") Integer product_id,
                                  HttpServletRequest request,
                                  HttpServletResponse response,
                                  Map<String, Object> map) throws UnsupportedEncodingException {


        //获取cookie中原本购物车,如果没有购物车，创建新购物车
        CartInfoList oldCart = getCartList(request);

        //将商品添加到购物车（数量默认加1）
        //查看商品是否存在
        ProductInfo product = productPageService.getProductById(product_id);
        if (product == null){
            map.put("msg", ResultEnum.PRODUCT_NOT_EXIST.getMessage());
            map.put("url", "/product/index");
            return new ModelAndView("common/error", map);
        }

        //查看商品是否剩余库存, 如有库存则库存-1
        product.setProduct_stock(product.getProduct_stock() - 1);
        try{
            productPageService.minusProductStock(product);
        } catch (CustomizeException e){
            map.put("msg", e.getMessage());
            map.put("url", "/product/index");
            return new ModelAndView("common/error", map);
        }

        //商品添加购物车
        CartInfoList newCart = addToCart(oldCart, product);

        //判断是否登陆
        //未登陆追加购物车到cookie
        Integer user_id = (Integer) request.getSession().getAttribute(CustomerConst.CURRENT_USER);
        if (user_id == null ){
            updateCookie(request, response, newCart);
        }
        //如登陆，加到redis
        else{
            cartService.addCartToRedis(user_id, newCart);
            //删除cookie
            deleteCookie(request, response);
        }

        map.put("url", "/product/index");
        return new ModelAndView("common/success", map);
    }


    @GetMapping("/cart")
    public ModelAndView listCart(Map<String, Object> map,
                                 HttpServletResponse response,
                                 HttpServletRequest request) throws UnsupportedEncodingException {


        //先从cookie中拿去购物车信息
        CartInfoList cart = getCartList(request);
        List<CartInfo> items = new ArrayList<>();

        //判断是否登陆
        Integer user_id = (Integer) request.getSession().getAttribute(CustomerConst.CURRENT_USER);
        //未登录,从cookie中拿购物车
        if (user_id == null){
            items = cart.getItems();
        }
        //如登陆，先同步cookie中购物车
        else{
            if (!cart.isEmpty()){cartService.addCartToRedis(user_id,cart);}
            //删除cookie
            deleteCookie(request, response);
            //从redis拿取完整购物车
            items = cartService.getAllItemsFromCart(user_id);

        }

        //具体页面信息
        BigDecimal totalPrice = new BigDecimal("0");
        //结算商品
        for (CartInfo item: items){
            BigDecimal count = new BigDecimal(item.getCart_number());
            totalPrice = totalPrice.add(count.multiply(item.getProduct_price()));
        }
        map.put("items", items);
        map.put("total_price", totalPrice);
        return new ModelAndView("customerProduct/cart", map);
    }



    @GetMapping("/deleteItem")
    public ModelAndView deleteItem(@RequestParam("product_id") Integer product_id,
                                   Map<String, Object> map,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws UnsupportedEncodingException {

        //如未登陆，则从cookie中读取购物车
        Integer user_id = (Integer) request.getSession().getAttribute(CustomerConst.CURRENT_USER);
        if (user_id == null){
            CartInfoList cart = getCartList(request);
            CartInfo item = cart.getItem(product_id);
            Integer number = item.getCart_number();
            cart.remove(item);

            //增加库存
            ProductInfo productInfo = productPageService.getProductById(product_id);
            productInfo.setProduct_stock(productInfo.getProduct_stock() + number);
            try {
                productPageService.plusProductStock(productInfo);
            } catch (CustomizeException e){
                map.put("msg", e.getMessage());
                map.put("url", "/product/cart");
                return new ModelAndView("common/error", map);
            }
            //更新cookie
            updateCookie(request, response, cart);
        }
        //redis中删除记录
        else{
            try {
                cartService.deleteProductById(user_id, product_id);
            } catch (CustomizeException e){
                map.put("msg", e.getMessage());
                map.put("url", "/product/cart");
                return new ModelAndView("common/error", map);
            }
        }

        return listCart(map, response, request);

    }

    @GetMapping("/decrease")
    public ModelAndView decreaseNumber(@RequestParam("product_id") Integer product_id,
                                       Map<String, Object> map,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws UnsupportedEncodingException {

        //未登陆从cookie中拿取购物车信息
        Integer user_id = (Integer) request.getSession().getAttribute(CustomerConst.CURRENT_USER);
        if (user_id == null){
            CartInfoList cart = getCartList(request);
            cart.decrease(product_id);

            //更新库存
            ProductInfo productInfo = productPageService.getProductById(product_id);
            productInfo.setProduct_stock(productInfo.getProduct_stock() + 1);
            try {
                productPageService.plusProductStock(productInfo);
            }catch (CustomizeException e){
                map.put("msg", e.getMessage());
                map.put("url", "/product/cart");
                return new ModelAndView("common/error", map);
            }

            //更新cookie
            updateCookie(request, response, cart);
        }

        //登陆从redis中拿取
        else{
            try {
                cartService.minusItemQuantity(user_id, product_id);
            } catch (CustomizeException e){
                map.put("msg", e.getMessage());
                map.put("url", "/product/cart");
                return new ModelAndView("common/error", map);
            }
        }

        return listCart(map, response, request);
    }



    @GetMapping("/increase")
    public ModelAndView increaseNumber(@RequestParam("product_id") Integer product_id,
                                       Map<String, Object> map,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws UnsupportedEncodingException{
        //未登录更新cookie
        Integer user_id = (Integer) request.getSession().getAttribute(CustomerConst.CURRENT_USER);
        if (user_id == null){
            CartInfoList cart = getCartList(request);

            //查看库存货物是否充足
            ProductInfo productInfo = productPageService.getProductById(product_id);
            productInfo.setProduct_stock(productInfo.getProduct_stock() - 1);
            try {
                productPageService.minusProductStock(productInfo);
            }catch (CustomizeException e){
                map.put("msg", e.getMessage());
                map.put("url", "/product/cart");
                return new ModelAndView("common/error", map);
            }

            //更新购物车
            cart.increase(product_id);

            //更新cookie
            updateCookie(request, response, cart);
        }
        //登陆更新redis
        else{
            try {
                cartService.plusItemQuantity(user_id, product_id);
            } catch (CustomizeException e){
                map.put("msg", e.getMessage());
                map.put("url", "/product/cart");
                return new ModelAndView("common/error", map);
            }
        }

        return listCart(map, response, request);
    }




    private void deleteCookie(HttpServletRequest request, HttpServletResponse response){
        Cookie cookie = new Cookie(CustomerConst.CURRENT_USER_CART, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    private void updateCookie(HttpServletRequest request, HttpServletResponse response, CartInfoList newCart) throws UnsupportedEncodingException {
        String encode = URLEncoder.encode(gson.toJson(newCart), "utf-8");
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0){
            for (Cookie c: cookies){
                if (CustomerConst.CURRENT_USER_CART.equals(c.getName())){
                    c.setValue(encode);
                    c.setPath("/");
                    c.setMaxAge(24*60*60);
                    response.addCookie(c);
                    return;
                }
            }
        }

        Cookie cookie = new Cookie(CustomerConst.CURRENT_USER_CART, encode);
        cookie.setPath("/");
        cookie.setMaxAge(24*60*60);
        response.addCookie(cookie);

    }

    private CartInfoList getCartList(HttpServletRequest request) throws UnsupportedEncodingException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0){
            for (Cookie c: cookies){
                if (CustomerConst.CURRENT_USER_CART.equals(c.getName())){
                    String decode = URLDecoder.decode(c.getValue(), "utf-8");
                    return gson.fromJson(decode, CartInfoList.class);
                }
            }
        }
        return new CartInfoList();
    }

    private CartInfoList addToCart(CartInfoList cart, ProductInfo product){
        //添加商品
        CartInfo item = new CartInfo();
        item.setProduct_id(product.getProduct_id());
        item.setProduct_name(product.getProduct_name());
        item.setCart_number(1);
        item.setProduct_price(product.getProduct_price());
        cart.add(item);
        return cart;
    }
    
}
