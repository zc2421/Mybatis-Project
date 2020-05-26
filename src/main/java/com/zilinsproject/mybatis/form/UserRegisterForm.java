package com.zilinsproject.mybatis.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 用户注册表格
 * @author zilinsmac
 */

@Data
public class UserRegisterForm {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String email;
}
