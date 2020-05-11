package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.Customer;
import java.util.List;

public interface CustomerMapper {
    int deleteByPrimaryKey(String cid);

    int insert(Customer record);

    Customer selectByPrimaryKey(String cid);

    List<Customer> selectAll();

    int updateByPrimaryKey(Customer record);
}