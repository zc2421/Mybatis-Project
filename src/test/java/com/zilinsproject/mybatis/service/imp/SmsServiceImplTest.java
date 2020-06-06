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
        String phoneNumber = "+16468645896";
        Integer userId = 13;
        smsService.SendSmsLoginCode(phoneNumber, userId);
    }
}