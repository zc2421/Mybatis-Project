package com.zilinsproject.mybatis.service;

import com.zilinsproject.mybatis.dao.CustomerMapper;
import com.zilinsproject.mybatis.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zilinsmac
 */
@Service
public class CustomerService implements CustomerMapper {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public int deleteByPrimaryKey(String cid){
        return customerMapper.deleteByPrimaryKey(cid);
    }

    @Override
    public int insert(Customer record){
        return customerMapper.insert(record);
    }

    @Override
    public Customer selectByPrimaryKey(String cid){
        return customerMapper.selectByPrimaryKey(cid);
    }

    @Override
    public List<Customer> selectAll(){
        return customerMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Customer record){
        return customerMapper.updateByPrimaryKey(record);
    }
}
