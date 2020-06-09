package com.zilinsproject.mybatis.dao;


import com.zilinsproject.mybatis.entity.OrderMaster;
import org.hibernate.criterion.Order;

import java.util.List;

public interface OrderMasterMapperExtended extends OrderMasterMapper{

    int insertAutoFill(OrderMaster orderMaster);

    List<OrderMaster> selectByUserId(Integer user_id);

    void updateByOrderId(OrderMaster orderMaster);

}
