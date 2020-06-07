package com.zilinsproject.mybatis.service;

import com.zilinsproject.mybatis.entity.UserInfo;

public interface SmsService {

    void SendSmsLoginCode(String username);

    UserInfo loginViaCode(String username, String code);
}
