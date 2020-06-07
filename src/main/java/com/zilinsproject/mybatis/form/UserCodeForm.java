package com.zilinsproject.mybatis.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * 获取验证码页面
 * @author zilinsmac
 */

@Data
public class UserCodeForm {

    @NotBlank
    private String username;
}
