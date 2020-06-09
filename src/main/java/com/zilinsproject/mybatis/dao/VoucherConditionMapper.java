package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.VoucherCondition;
import java.util.List;

public interface VoucherConditionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VoucherCondition record);

    VoucherCondition selectByPrimaryKey(Integer id);

    List<VoucherCondition> selectAll();

    int updateByPrimaryKey(VoucherCondition record);
}