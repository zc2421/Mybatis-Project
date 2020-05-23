package com.zilinsproject.mybatis.exceptions;

import com.zilinsproject.mybatis.enums.ResultEnum;

/**
 * 界面异常
 * @author zilinsmac
 */
public class CustomizeException extends RuntimeException{

    private Integer code;

    public CustomizeException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
