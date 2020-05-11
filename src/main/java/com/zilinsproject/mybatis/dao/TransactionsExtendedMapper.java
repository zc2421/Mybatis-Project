package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.Transactions;

import java.util.List;

public interface TransactionsExtendedMapper extends TransactionsMapper{

    List<Transactions> selectAllTransactionsByCid(String cid);
}
