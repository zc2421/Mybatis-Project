package com.zilinsproject.mybatis.service.imp;

import com.zilinsproject.mybatis.entity.UserInfo;
import org.apache.catalina.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired UserServiceImpl userService;

    @Test
    @Transactional
    public void registerLoginTest() {
        String username = "zichen123";
        String password = "Aber597186";
        UserInfo user = new UserInfo();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail("zichen152@gmail.com");
        user.setRole(1);
        userService.register(user);
        userService.login(username, password);
    }

    @Test
    public void testLogin(){
        String username = "zc2421";
        String password = "Aber455";
        UserInfo user = userService.login(username, password);
        System.out.println(user.toString());
    }

    @Test
    @Transactional
    public void testAddBalance(){
        UserInfo userInfo = userService.selectByUserId(13);
        BigDecimal balance = new BigDecimal(30);
        userInfo.setUser_balance(balance);
        userService.updateUserBalance(userInfo);

        UserInfo userInfoAfter = userService.selectByUserId(13);
        System.out.println(userInfoAfter.getUser_balance());
    }
}