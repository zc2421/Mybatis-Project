package com.zilinsproject.mybatis.service;

import com.zilinsproject.mybatis.dao.TransactionsExtendedMapper;
import com.zilinsproject.mybatis.entity.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService implements TransactionsExtendedMapper {

    @Autowired
    private TransactionsExtendedMapper transactionsMapper;

    @Override
    public int deleteByPrimaryKey(String tid){
        return transactionsMapper.deleteByPrimaryKey(tid);
    }

    @Override
    public int insert(Transactions record){
        return transactionsMapper.insert(record);
    }

    @Override
    public Transactions selectByPrimaryKey(String tid){
        return transactionsMapper.selectByPrimaryKey(tid);
    }

    @Override
    public List<Transactions> selectAll(){
        return transactionsMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Transactions record){
        return transactionsMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Transactions> selectAllTransactionsByCid(String cid) {  return transactionsMapper.selectAllTransactionsByCid(cid); }
}
