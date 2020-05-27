package com.zilinsproject.mybatis.dao;


import com.zilinsproject.mybatis.entity.UserInfo;

/**
 * @author zilinsmac
 */

public interface UserInfoMapperExtended extends UserInfoMapper{

    int countByUsername (String username);

    int countByEmail (String email);

    int insertAutoFill (UserInfo userInfo);

    UserInfo selectByUsername (String username);

    int updateUserbalance (UserInfo userInfo);
}
