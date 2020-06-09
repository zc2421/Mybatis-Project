package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.VoucherUser;
import java.util.List;

public interface VoucherUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VoucherUser record);

    VoucherUser selectByPrimaryKey(Integer id);

    List<VoucherUser> selectAll();

    int updateByPrimaryKey(VoucherUser record);
}