package com.zilinsproject.mybatis.enums;

import lombok.Getter;

@Getter
public enum PaymentStatusEnum {
    /**
     *
     */
    INCOMPLETE(0),
    COMPLETE(1),

    ;

    private Integer code;


    PaymentStatusEnum(Integer code) {
        this.code = code;
    }
}
