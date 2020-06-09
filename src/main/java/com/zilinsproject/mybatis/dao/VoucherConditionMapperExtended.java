package com.zilinsproject.mybatis.dao;


import com.zilinsproject.mybatis.entity.VoucherCondition;

public interface VoucherConditionMapperExtended extends VoucherConditionMapper{

    VoucherCondition selectConditionById(Integer condition_id);
}
