package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.OrderDetail;
import java.util.List;

public interface OrderDetailMapper {
    int deleteByPrimaryKey(Integer detail_id);

    int insert(OrderDetail record);

    OrderDetail selectByPrimaryKey(Integer detail_id);

    List<OrderDetail> selectAll();

    int updateByPrimaryKey(OrderDetail record);
}