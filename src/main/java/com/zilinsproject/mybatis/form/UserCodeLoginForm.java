package com.zilinsproject.mybatis.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 用户输入验证码
 * @author zilinsmac
 */
@Data
public class UserCodeLoginForm {

    @NotBlank
    private String username;

    @NotBlank
    private String code;
}
