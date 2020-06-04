package com.zilinsproject.mybatis.service;


import com.zilinsproject.mybatis.entity.CartInfo;
import com.zilinsproject.mybatis.entity.CartInfoList;

import java.util.List;

/**
 * 购物车
 * @author zilinsmac
 */

public interface CartService {


    void addCartToRedis(Integer userId, CartInfoList cartList);

    void deleteCartFromRedis(Integer user_id);

    List<CartInfo> getAllItemsFromCart(Integer userId);

    void deleteProductById(Integer user_id, Integer product_id);

    void plusItemQuantity(Integer user_id, Integer product_id);

    void minusItemQuantity(Integer user_id, Integer product_id);


}
