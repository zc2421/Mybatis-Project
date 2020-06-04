package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.OrderDetail;

import java.util.List;

/**
 * @author zilinsmac
 */

public interface OrderDetailMapperExtended extends OrderDetailMapper {

    int insertAutoFill(OrderDetail record);

    List<OrderDetail> selectByOrderId(String order_id);

    int deleteByOrderId(String order_id);
}
