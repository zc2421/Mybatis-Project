package com.zilinsproject.mybatis.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zilinsmac
 */
@Data
public class ProductForm {

    private Integer product_id;

    private String product_name;

    private BigDecimal product_price;

    private Integer product_stock;

    private String product_description;

    private String product_image;

    private Integer category_type;

    private Boolean saleable;

    private Boolean valid;

}
