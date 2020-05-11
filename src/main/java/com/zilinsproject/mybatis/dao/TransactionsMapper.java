package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.Transactions;
import java.util.List;

public interface TransactionsMapper {
    int deleteByPrimaryKey(String tid);

    int insert(Transactions record);

    Transactions selectByPrimaryKey(String tid);

    List<Transactions> selectAll();

    int updateByPrimaryKey(Transactions record);
}