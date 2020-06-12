package com.zilinsproject.mybatis.service.imp;

import com.google.gson.Gson;
import com.zilinsproject.mybatis.dao.VoucherConditionMapperExtended;
import com.zilinsproject.mybatis.dao.VoucherMapperExtended;
import com.zilinsproject.mybatis.dao.VoucherUserMapperExtended;
import com.zilinsproject.mybatis.entity.*;
import com.zilinsproject.mybatis.enums.ResultEnum;
import com.zilinsproject.mybatis.exceptions.CustomizeException;
import com.zilinsproject.mybatis.pojo.VoucherConditionMap;
import com.zilinsproject.mybatis.service.OrderService;
import com.zilinsproject.mybatis.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 优惠券服务
 * @author zilinsmac
 */

@Service
public class VoucherServiceImpl implements VoucherService {

    @Autowired
    private VoucherUserMapperExtended voucherUserMapper;
    @Autowired
    private VoucherMapperExtended voucherMapper;
    @Autowired
    private VoucherConditionMapperExtended voucherConditionMapper;
    @Autowired
    private Gson gson;
    @Autowired
    private OrderService orderService;


    @Override
    @Transactional(rollbackFor = CustomizeException.class)
    public int validateVoucher(Integer user_id, Integer voucher_id, String order_id) {
        //1.查看用户是否拥有优惠券
        VoucherUser voucherUser = voucherUserMapper.getUserVoucherById(user_id, voucher_id);
        if (voucherUser == null){
            throw new CustomizeException(ResultEnum.VOUCHER_NOT_EXIST);
        }

        //2. 查看优惠券是否可用
        //查看用户优惠券是否有效
        if (!voucherUser.getValid()){
            throw new CustomizeException(ResultEnum.VOUCHER_NOT_VALID);
        }

        //查看优惠券本身是否过期
        Voucher voucher = voucherMapper.selectByPrimaryKey(voucher_id);
        if (voucher == null){
            throw new CustomizeException(ResultEnum.VOUCHER_NOT_VALID);
        }
        Date current = new Date();
        if (current.compareTo(voucher.getStart_date()) < 0 || voucher.getEnd_date().compareTo(current) < 0) {
            //to add here: 将用户优惠券设为失效
            //..
            throw new CustomizeException(ResultEnum.VOUCHER_DATE_STATS_ERROR);
        }

        //查看订单是否满足优惠券要求

        VoucherCondition voucherCondition = voucherConditionMapper.selectConditionById(voucher.getCondition_id());
        String condition = voucherCondition.getCondition();
        VoucherConditionMap conditionMap = gson.fromJson(condition, VoucherConditionMap.class);
        int valid = validateOrder(order_id, conditionMap);
        if (valid == 0){
            throw new CustomizeException(ResultEnum.VOUCHER_NOT_QUALIFIED);
        }

        //3.更新优惠券状态
        voucherUser.setValid(false);
        voucherUserMapper.updateByPrimaryKey(voucherUser);

        //4.更新订单总价及优惠券状态
        OrderMaster orderMaster = orderService.getOrderMasterById(order_id);
        orderMaster.setVoucher_id(voucher_id);
        BigDecimal productAmount = orderService.calcPriceOfProductsByCategoryId(order_id, conditionMap.getCategory_id());
        BigDecimal reduction = productAmount.subtract(conditionMap.getAmount());
        BigDecimal newPrice = orderMaster.getAmount().subtract(productAmount)
                                .add(reduction);

        orderMaster.setAmount(newPrice);
        orderService.updateOrderMaster(orderMaster);

        return 1;
    }

    @Override
    public List<Voucher> getValidVouchersByUserId(Integer user_id) {
        List<Voucher> validVouchers = new ArrayList<>();
        List<VoucherUser> voucherUserList = voucherUserMapper.getVoucherUserByUserId(user_id);
        voucherUserList.forEach(voucherUser -> {
            if (voucherUser.getValid()){
                validVouchers.add(voucherMapper.selectByPrimaryKey(voucherUser.getVoucher_id()));
            }
        });
        return validVouchers;
    }

    @Override
    public List<Voucher> getInvalidVouchersByUserId(Integer user_id) {
        List<Voucher> validVouchers = new ArrayList<>();
        List<VoucherUser> voucherUserList = voucherUserMapper.getVoucherUserByUserId(user_id);
        voucherUserList.forEach(voucherUser -> {
            if (!voucherUser.getValid()){
                validVouchers.add(voucherMapper.selectByPrimaryKey(voucherUser.getVoucher_id()));
            }
        });
        return validVouchers;
    }


    private int validateOrder(String order_id, VoucherConditionMap conditionMap){
        //查看商品类型总价是否满足
        Integer category_id = conditionMap.getCategory_id();

        if (category_id != null){
            BigDecimal qualifiedProductAmount = orderService.calcPriceOfProductsByCategoryId(order_id, conditionMap.getCategory_id());
            //总金额不满足要求
            if (qualifiedProductAmount.compareTo(conditionMap.getAmount()) < 0){
                return 0;
            }
        }

        //查看其他....

        return 1;
    }
}
