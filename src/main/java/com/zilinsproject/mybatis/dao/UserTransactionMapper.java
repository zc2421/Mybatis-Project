package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.UserTransaction;
import java.util.List;

public interface UserTransactionMapper {
    int deleteByPrimaryKey(Integer transaction_id);

    int insert(UserTransaction record);

    UserTransaction selectByPrimaryKey(Integer transaction_id);

    List<UserTransaction> selectAll();

    int updateByPrimaryKey(UserTransaction record);
}