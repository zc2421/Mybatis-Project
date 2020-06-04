package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.OrderDetail;
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
public class OrderDetailMapperExtendedTest {

    @Autowired
    private OrderDetailMapperExtended orderMapper;

    @Test
    public void insertAutoFill() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder_id("thisisatest");
        orderDetail.setProduct_id(1);
        orderDetail.setProduct_name("test");
        orderDetail.setPrice(new BigDecimal(1));
        orderDetail.setNum(1);
        orderMapper.insertAutoFill(orderDetail);
    }

    @Test
    public void testSelectInProgress(){
        List<OrderDetail> orderDetailList = orderMapper.selectByOrderId("1591294951724797524");
        for (OrderDetail orderDetail: orderDetailList){
            System.out.println(orderDetail);
        }
    }
}