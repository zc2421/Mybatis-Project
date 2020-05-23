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
    PRODUCT_STATUS_ERROR(101, "商品状态不存在")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
