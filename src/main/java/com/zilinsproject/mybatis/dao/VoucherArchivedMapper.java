package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.VoucherArchived;
import java.util.List;

public interface VoucherArchivedMapper {
    int deleteByPrimaryKey(Integer voucher_id);

    int insert(VoucherArchived record);

    VoucherArchived selectByPrimaryKey(Integer voucher_id);

    List<VoucherArchived> selectAll();

    int updateByPrimaryKey(VoucherArchived record);
}