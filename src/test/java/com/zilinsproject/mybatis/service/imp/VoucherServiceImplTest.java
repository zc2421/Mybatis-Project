package com.zilinsproject.mybatis.service.imp;

import com.zilinsproject.mybatis.entity.OrderMaster;
import com.zilinsproject.mybatis.entity.Voucher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class VoucherServiceImplTest {
    @Autowired
    private VoucherServiceImpl voucherService;
    @Autowired
    private OrderServiceImpl orderService;

    @Test
    @Transactional
    public void validateVoucher() {
        Integer user_id = 13;
        Integer voucher_id = 1;
        String order_id = "1591658890917245612";
        voucherService.validateVoucher(user_id, voucher_id, order_id);
        OrderMaster orderMaster = orderService.getOrderMasterById(order_id);
        System.out.println(orderMaster);
    }

    @Test
    public void selectValidVouchers(){
        Integer user_id = 13;
        List<Voucher> voucherList = voucherService.getValidVouchersByUserId(user_id);
        voucherList.forEach(voucher -> {
            System.out.println(voucher.toString());
        });
    }

    @Test
    public void selectInvalidVouchers(){
        Integer user_id = 13;
        List<Voucher> voucherList = voucherService.getInvalidVouchersByUserId(user_id);
        voucherList.forEach(voucher -> {
            System.out.println(voucher.toString());
        });
    }
}