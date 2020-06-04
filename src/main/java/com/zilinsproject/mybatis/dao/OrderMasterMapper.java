package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.OrderMaster;
import java.util.List;

public interface OrderMasterMapper {
    int deleteByPrimaryKey(String order_id);

    int insert(OrderMaster record);

    OrderMaster selectByPrimaryKey(String order_id);

    List<OrderMaster> selectAll();

    int updateByPrimaryKey(OrderMaster record);
}