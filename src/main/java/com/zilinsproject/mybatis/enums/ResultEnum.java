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
    PRODUCT_NOT_FOUND(102, "未找到结果"),
    PRODUCT_OUT_OF_STOCK(103,"商品已售罄"),

    CATEGORY_NOT_EXIST(200, "类目不存在"),
    CATEGORY_STATUS_ERROR(201, "类目状态不存在"),
    CATEGORY_DUPLICATE_ERROR(201, "类目编号已存在"),
    CATEGORY_PRODUCT_EXIST_ERROR(201, "类目存在商品"),

    USERNAME_ALREADY_EXIST(300, "用户名已被注册"),
    EMAIL_ALREADY_EXIST(301, "邮箱已被注册"),
    REGISTER_FAILED_ERROR(302, "用户注册失败"),
    LOGGING_INFO_NOT_CORRECT(303, "用户名或密码错误"),
    LOGIN_STATUS_ERROR(304, "用户没有登陆"),
    USER_NOT_EXIST(305, "用户不存在"),
    TEMPORAL_CODE_FAILED(310, "验证码发送失败"),
    CLOUT_SERVER_STATUS_ERROR(311, "腾讯云服务器故障"),
    CODE_INCORRECT_ERROR(312, "验证码错误"),
    CODE_STATUS_ERROR (313, "验证码无效"),

    PRODUCT_NOT_EXIST_IN_CART(401, "购物车没有该商品"),

    ORDER_NOT_EXIST(500, "订单不存在");
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
