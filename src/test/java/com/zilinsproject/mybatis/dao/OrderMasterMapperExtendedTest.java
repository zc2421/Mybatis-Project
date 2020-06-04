package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.OrderMaster;
import com.zilinsproject.mybatis.enums.OrderStatusEnum;
import com.zilinsproject.mybatis.enums.PaymentStatusEnum;
import com.zilinsproject.mybatis.utils.KeyUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterMapperExtendedTest {

    @Autowired
    OrderMasterMapperExtended orderMasterMapper;

    @Test
    public void insertAutoFill() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrder_id(KeyUtils.genUniqueKey());
        orderMaster.setUser_id(1);
        orderMaster.setUser_address("this is an address");
        orderMaster.setAmount(new BigDecimal(0));
        orderMaster.setVoucher_id(1);
        orderMaster.setOrder_status(OrderStatusEnum.INCOMPLETE.getCode());
        orderMaster.setPayment_status(PaymentStatusEnum.INCOMPLETE.getCode());
        orderMasterMapper.insertAutoFill(orderMaster);
    }

    @Test
    public void testSelectAllInProgress(){
        List<OrderMaster> orderMasterList = orderMasterMapper.selectByUserId(13);
        for (OrderMaster orderMaster: orderMasterList){
            System.out.println(orderMaster.toString());
        }
    }
}