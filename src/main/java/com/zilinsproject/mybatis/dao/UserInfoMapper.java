package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.UserInfo;
import java.util.List;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer user_id);

    int insert(UserInfo record);

    UserInfo selectByPrimaryKey(Integer user_id);

    List<UserInfo> selectAll();

    int updateByPrimaryKey(UserInfo record);
}