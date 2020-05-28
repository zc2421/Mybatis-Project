package com.zilinsproject.mybatis.service;


import com.zilinsproject.mybatis.entity.UserTransaction;

import java.util.List;

/**
 * @author zilinsmac
 */
public interface TransactionService {

    List<UserTransaction> getTransactionsByUserId (Integer user_id);

    int insert (UserTransaction userTransaction);
}
