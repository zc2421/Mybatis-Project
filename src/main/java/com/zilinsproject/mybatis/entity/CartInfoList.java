package com.zilinsproject.mybatis.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zilinsmac
 */

@Data
public class CartInfoList implements Serializable {

    private List<CartInfo> items = new ArrayList<CartInfo>();

    public void add(CartInfo item){
        boolean flag = false;
        //update number
        for (CartInfo c: items){
            if (c.getProduct_id().equals(item.getProduct_id())){
                Integer oldNumber = c.getCart_number();
                c.setCart_number(oldNumber + 1);
                flag = true;
                break;
            }
        }
        //add new item
        if (!flag){
            items.add(item);
        }

    }

    public CartInfo getItem(Integer product_id){
        for (CartInfo item: items){
            if (item.getProduct_id().equals(product_id)){
                return item;
            }
        }
        return null;
    }

    public void remove(CartInfo item){
        items.remove(item);
    }

    public void decrease(Integer product_id){
        for (CartInfo item: items){
            if (item.getProduct_id().equals(product_id)){
                Integer oldNumber = item.getCart_number();
                if (oldNumber > 1){
                    item.setCart_number(oldNumber - 1);
                } else {
                    items.remove(item);
                }

                return;
            }
        }
    }

    public void increase(Integer product_id){
        for (CartInfo item: items){
            if (item.getProduct_id().equals(product_id)){
                item.setCart_number(item.getCart_number() + 1);
                return;
            }
        }
    }

    public boolean isEmpty(){
        return items.size() == 0;
    }
}
