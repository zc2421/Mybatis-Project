package com.zilinsproject.mybatis.entity;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 购物车物品信息
 * @author zilinsmac
 */

@Data
public class CartInfo implements Serializable {

    private Integer product_id;

    private String product_name;

    private BigDecimal product_price;

    private Integer cart_number;

}
