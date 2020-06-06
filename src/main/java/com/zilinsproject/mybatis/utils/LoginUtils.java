package com.zilinsproject.mybatis.utils;

import java.util.Random;

/**
 * 生成登陆验证码
 * @author zilinsmac
 */
public class LoginUtils {

    public static synchronized String generateCode(){
        Integer rand = new Random().nextInt(9000) + 1000;
        return String.valueOf(rand);
    }
}
