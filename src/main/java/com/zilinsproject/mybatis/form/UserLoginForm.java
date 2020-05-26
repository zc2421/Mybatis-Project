package com.zilinsproject.mybatis.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 用户登陆表
 * @author zilinsmac
 */

@Data
public class UserLoginForm {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
