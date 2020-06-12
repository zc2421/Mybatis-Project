package com.zilinsproject.mybatis.service;

import com.zilinsproject.mybatis.entity.Voucher;

import java.util.List;

/**
 * 优惠券服务
 * @author zilinsmac
 */

public interface VoucherService {

    int validateVoucher(Integer user_id, Integer voucher_id, String order_id);

    List<Voucher> getValidVouchersByUserId(Integer user_id);

    List<Voucher> getInvalidVouchersByUserId(Integer user_id);
}
