package com.zilinsproject.mybatis.service.imp;

import com.google.gson.Gson;
import com.zilinsproject.mybatis.entity.CartInfo;
import com.zilinsproject.mybatis.entity.CartInfoList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceImplTest {
    @Autowired
    private Gson gson;
    @Autowired
    private CartServiceImpl cartService;


    @Test
    public void testGsonToString(){
        CartInfoList cartList = new CartInfoList();

        CartInfo cart = new CartInfo();
        cart.setProduct_id(1);
        cart.setProduct_name("testProduct1");
        BigDecimal price = new BigDecimal("1");
        cart.setProduct_price(price);
        cart.setCart_number(3);

        CartInfo cart1 = new CartInfo();
        cart1.setProduct_id(2);
        cart1.setProduct_name("testProduct");
        cart1.setProduct_price(price);
        cart1.setCart_number(1);

        cartList.add(cart);
        cartList.add(cart1);
        CartInfo test = cartList.getItem(1);
        System.out.println(test.toString());
        cartList.remove(test);

        String json = gson.toJson(cartList);
        System.out.println(json);

//        CartInfoList newCartList = gson.fromJson(json, CartInfoList.class);
//        List<CartInfo> items = newCartList.getItems();
//        for (CartInfo c: items){
//            System.out.println(c.toString());
//        }

    }


    @Test
    public void testAddToRedis(){
        CartInfoList cartList = new CartInfoList();

        CartInfo cart = new CartInfo();
        cart.setProduct_id(1);
        cart.setProduct_name("testProduct1");
        BigDecimal price = new BigDecimal("1");
        cart.setProduct_price(price);
        cart.setCart_number(3);

        CartInfo cart1 = new CartInfo();
        cart1.setProduct_id(2);
        cart1.setProduct_name("testProduct");
        cart1.setProduct_price(price);
        cart1.setCart_number(1);

        cartList.add(cart);
        cartList.add(cart1);

        cartService.addCartToRedis(1, cartList);
    }


    @Test
    public void getAllItemsTest(){
        List<CartInfo> items = cartService.getAllItemsFromCart(13);
        for (CartInfo item: items){
            System.out.println(item.toString());
        }
    }

    @Test
    public void testDelete(){
        cartService.deleteProductById(14, 1);
    }

    @Test
    public void testMins(){
        cartService.minusItemQuantity(14, 4);
    }

    @Test
    public void testPlus(){
        cartService.plusItemQuantity(14, 15);
    }

    @Test
    @Transactional
    public void testDeleteCart(){
        cartService.deleteCartFromRedis(13);
    }
}