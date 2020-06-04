package com.zilinsproject.mybatis.enums;

import lombok.Getter;

/**
 * 订单状态
 * @author zilinsmac
 */

@Getter
public enum OrderStatusEnum {

    /**
     * 0 - 订单未完成
     * 1 - 订单交易进行中
     * 2 - 订单已完成
     */

    INCOMPLETE(0),
    COMPLETE(1),

    ;


    private Integer code;

    OrderStatusEnum(Integer code) {
        this.code = code;
    }
}
