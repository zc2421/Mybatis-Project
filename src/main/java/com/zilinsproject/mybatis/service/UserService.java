package com.zilinsproject.mybatis.service;


import com.zilinsproject.mybatis.entity.UserInfo;

/**
 * @author zilinsmac
 */
public interface UserService {

    void register(UserInfo userinfo);

    UserInfo login(String username, String password);

}
