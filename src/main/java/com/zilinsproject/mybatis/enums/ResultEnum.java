package com.zilinsproject.mybatis.enums;


import lombok.Getter;

/**
 * @author zilinsmac
 */

@Getter
public enum ResultEnum {
    /**
     * reject messages
     */
    PRODUCT_NOT_EXIST(100, "商品不存在"),
    PRODUCT_STATUS_ERROR(101, "商品状态不存在"),

    CATEGORY_NOT_EXIST(200, "类目不存在"),
    CATEGORY_STATUS_ERROR(201, "类目状态不存在"),
    CATEGORY_DUPLICATE_ERROR(201, "类目编号已存在")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
