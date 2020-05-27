package com.zilinsproject.mybatis.service;


import com.zilinsproject.mybatis.entity.UserInfo;
import org.apache.catalina.User;

/**
 * @author zilinsmac
 */
public interface UserService {

    UserInfo selectByUserId(Integer id);

    void register(UserInfo userinfo);

    UserInfo login(String username, String password);

    int updateUserBalance(UserInfo userInfo);

}
