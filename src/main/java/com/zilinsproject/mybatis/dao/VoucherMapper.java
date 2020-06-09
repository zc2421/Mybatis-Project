package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.Voucher;
import java.util.List;

public interface VoucherMapper {
    int deleteByPrimaryKey(Integer voucher_id);

    int insert(Voucher record);

    Voucher selectByPrimaryKey(Integer voucher_id);

    List<Voucher> selectAll();

    int updateByPrimaryKey(Voucher record);
}