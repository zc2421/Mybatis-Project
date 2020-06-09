package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.VoucherUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VoucherUserMapperExtendedTest {
    @Autowired
    private VoucherUserMapperExtended voucherUserMapper;

    @Test
    public void getUserVoucherById() {
        Integer user_id = 13;
        Integer voucher_id = 1;
        VoucherUser voucherUser = voucherUserMapper.getUserVoucherById(user_id, voucher_id);
        System.out.println(voucherUser);
    }
}