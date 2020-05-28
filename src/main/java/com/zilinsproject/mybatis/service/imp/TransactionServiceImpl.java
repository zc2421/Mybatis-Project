package com.zilinsproject.mybatis.service.imp;

import com.zilinsproject.mybatis.dao.UserInfoMapperExtended;
import com.zilinsproject.mybatis.dao.UserTransactionMapperExtended;
import com.zilinsproject.mybatis.entity.UserInfo;
import com.zilinsproject.mybatis.entity.UserTransaction;
import com.zilinsproject.mybatis.enums.ResultEnum;
import com.zilinsproject.mybatis.exceptions.CustomizeException;
import com.zilinsproject.mybatis.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户历史交易记录
 * @author zilinsmac
 */

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private UserTransactionMapperExtended transactionMapper;
    @Autowired
    private UserInfoMapperExtended userMapper;

    @Override
    public List<UserTransaction> getTransactionsByUserId(Integer user_id) {
        return transactionMapper.getTransactionsByUserId( user_id);
    }

    @Override
    public int insert(UserTransaction userTransaction) {
        return transactionMapper.insertAutoFill(userTransaction);
    }
}
