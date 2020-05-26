package com.zilinsproject.mybatis.enums;

import lombok.Getter;

/**
 * @author zilinsmac
 */

@Getter
public enum UserEnum {

    /**
     * 用户身份编码
     */

    CUSTOMER(0),
    ADMIN(1)
    ;

    private Integer code;

    UserEnum(Integer code) {
        this.code = code;
    }
}
