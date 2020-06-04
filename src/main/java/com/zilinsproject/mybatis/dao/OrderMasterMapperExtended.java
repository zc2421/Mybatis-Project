package com.zilinsproject.mybatis.dao;


import com.zilinsproject.mybatis.entity.OrderMaster;

import java.util.List;

public interface OrderMasterMapperExtended extends OrderMasterMapper{

    int insertAutoFill(OrderMaster orderMaster);

    List<OrderMaster> selectByUserId(Integer user_id);

}
