package com.zilinsproject.mybatis.service;

/**
 * 优惠券服务
 * @author zilinsmac
 */

public interface VoucherService {

    int validateVoucher(Integer user_id, Integer voucher_id, String order_id);
}
