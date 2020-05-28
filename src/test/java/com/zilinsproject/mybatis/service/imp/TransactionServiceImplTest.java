package com.zilinsproject.mybatis.service.imp;

import com.zilinsproject.mybatis.entity.UserInfo;
import com.zilinsproject.mybatis.entity.UserTransaction;
import org.apache.catalina.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceImplTest {

    @Autowired
    TransactionServiceImpl transactionService;
    @Autowired
    UserServiceImpl userService;

    @Test
    public void getTransactionsByUserId() {
        List<UserTransaction> trans = transactionService.getTransactionsByUserId(13);
        for (UserTransaction t: trans){
            System.out.println(t.toString());
        }

    }

    @Test
    @Transactional
    public void insert() {
        //update user balance
        UserInfo user = userService.selectByUserId(13);
        BigDecimal amount = new BigDecimal(20);
        user.setUser_balance(user.getUser_balance().add(amount));
        userService.updateUserBalance(user);

        //add transaction history
        UserTransaction transaction = new UserTransaction();
        transaction.setUser_id(user.getUser_id());
        transaction.setAmount(amount);
        transactionService.insert(transaction);

    }
}