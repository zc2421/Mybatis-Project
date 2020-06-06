package com.zilinsproject.mybatis.service;

public interface SmsService {

    void SendSmsLoginCode(String phoneNumber, Integer userId);
}
