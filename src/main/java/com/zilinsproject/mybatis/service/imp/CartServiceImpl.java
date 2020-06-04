package com.zilinsproject.mybatis.service.imp;


import com.google.gson.Gson;
import com.zilinsproject.mybatis.dao.ProductInfoMapperExtended;
import com.zilinsproject.mybatis.entity.CartInfo;
import com.zilinsproject.mybatis.entity.CartInfoList;
import com.zilinsproject.mybatis.entity.ProductInfo;
import com.zilinsproject.mybatis.enums.ResultEnum;
import com.zilinsproject.mybatis.exceptions.CustomizeException;
import com.zilinsproject.mybatis.service.CartService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * CartService Implementation
 * @author zilinsmac
 */
@Service
public class CartServiceImpl implements CartService {

    private final static String CART_REDIS_KEY_TEMPLATE= "cart_%d";


    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private Gson gson;
    @Autowired
    private ProductInfoMapperExtended productMapper;

    @Override
    public void addCartToRedis(Integer userId, CartInfoList cartList) {
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        List<CartInfo> items = cartList.getItems();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, userId);
        for (CartInfo item: items){
            opsForHash.put(redisKey,
                    String.valueOf(item.getProduct_id()),
                    gson.toJson(item));
        }
    }

    @Override
    public void deleteCartFromRedis(Integer user_id) {
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, user_id);
        redisTemplate.delete(redisKey);
    }

    @Override
    public List<CartInfo> getAllItemsFromCart(Integer userId) {
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, userId);
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        Map<String, String> itemMap = opsForHash.entries(redisKey);
        List<CartInfo> items = new ArrayList<>();
        for (Map.Entry<String, String> map: itemMap.entrySet()){
            CartInfo item = gson.fromJson(map.getValue(), CartInfo.class);
            items.add(item);
        }
        return items;
    }

    @Override
    @Transactional(rollbackFor = CustomizeException.class)
    public void deleteProductById(Integer userId, Integer product_id) {
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, userId);
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String itemString = opsForHash.get(redisKey, String.valueOf(product_id));
        if (itemString == null){
            throw new CustomizeException(ResultEnum.PRODUCT_NOT_EXIST_IN_CART);
        }
        CartInfo item = gson.fromJson(itemString, CartInfo.class);

        //更新数据库库存
        ProductInfo productInfo = productMapper.selectByPrimaryKey(product_id);
        productInfo.setProduct_stock(productInfo.getProduct_stock() + item.getCart_number());
        productMapper.updateProductInfo(productInfo);

        //删除redis中商品记录
        opsForHash.delete(redisKey, String.valueOf(product_id));

    }

    @Override
    @Transactional(rollbackFor = CustomizeException.class)
    public void plusItemQuantity(Integer user_id, Integer product_id) {
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, user_id);
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String itemString = opsForHash.get(redisKey, String.valueOf(product_id));
        if (itemString == null){
            throw new CustomizeException(ResultEnum.PRODUCT_NOT_EXIST_IN_CART);
        }
        //查看商品售罄
        ProductInfo productInfo = productMapper.selectByPrimaryKey(product_id);
        if (productInfo.getProduct_stock() <= 0){
            throw new CustomizeException(ResultEnum.PRODUCT_OUT_OF_STOCK);
        }

        //更新数据库库存
        productInfo.setProduct_stock(productInfo.getProduct_stock() - 1);
        productMapper.updateProductInfo(productInfo);

        //更新redis
        CartInfo item = gson.fromJson(itemString, CartInfo.class);
        item.setCart_number(item.getCart_number() + 1);
        opsForHash.put(redisKey, String.valueOf(product_id), gson.toJson(item));

    }

    @Override
    @Transactional(rollbackFor = CustomizeException.class)
    public void minusItemQuantity(Integer user_id, Integer product_id) {
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, user_id);
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String itemString = opsForHash.get(redisKey, String.valueOf(product_id));
        if (itemString == null){
            throw new CustomizeException(ResultEnum.PRODUCT_NOT_EXIST_IN_CART);
        }
        //更新数据库
        ProductInfo productInfo = productMapper.selectByPrimaryKey(product_id);
        productInfo.setProduct_stock(productInfo.getProduct_stock() + 1);

        //更新redis
        CartInfo item = gson.fromJson(itemString, CartInfo.class);
        //如果只剩下一个就删除商品
        if (item.getCart_number() == 1){
            opsForHash.delete(redisKey, String.valueOf(product_id));
        }else{
            item.setCart_number(item.getCart_number() - 1);
            opsForHash.put(redisKey, String.valueOf(product_id), gson.toJson(item));
        }

    }


}
