package com.zilinsproject.mybatis.dao;


import com.zilinsproject.mybatis.entity.UserTransaction;

import java.util.List;

/**
 * @author zilinsmac
 */
public interface UserTransactionMapperExtended extends UserTransactionMapper{

    List<UserTransaction> getTransactionsByUserId (Integer user_id);

    int insertAutoFill (UserTransaction userTransaction);
}
