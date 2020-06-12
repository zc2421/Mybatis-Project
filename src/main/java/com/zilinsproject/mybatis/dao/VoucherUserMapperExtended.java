package com.zilinsproject.mybatis.dao;



import com.zilinsproject.mybatis.entity.VoucherUser;

import java.util.List;

public interface VoucherUserMapperExtended extends VoucherUserMapper{

    VoucherUser getUserVoucherById(Integer user_id, Integer voucher_id);

    List<VoucherUser> getVoucherUserByUserId(Integer user_id);

}
