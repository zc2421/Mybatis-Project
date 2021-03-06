package com.zilinsproject.mybatis.service.imp;

import com.zilinsproject.mybatis.service.SmsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmsServiceImplTest {
    @Autowired
    private SmsServiceImpl smsService;

    @Test
    public void sendSmsLoginCode() {
        String username = "zc2421";
        smsService.SendSmsLoginCode(username);
    }

    @Test
    public void loginViaCode(){
        String username = "zc2421";
        String code = "8804";
        smsService.loginViaCode(username, code);
    }
}