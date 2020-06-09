package com.zilinsproject.mybatis.service;


import com.github.pagehelper.PageInfo;
import com.zilinsproject.mybatis.entity.CartInfo;
import com.zilinsproject.mybatis.dto.OrderDTO;
import com.zilinsproject.mybatis.entity.OrderMaster;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单服务
 * @author zilinsmac
 */
public interface OrderService {

    OrderDTO createOrder(Integer user_id, List<CartInfo> items);

    OrderDTO selectOrderById(String order_id);

    void deleteOrderById(String order_id);

    void completeOrder(String order_id);

    PageInfo<OrderDTO> listAllOrdersInProgress(Integer user_id, Integer pageNum, Integer pageSize);

    PageInfo<OrderDTO> listAllOrdersCompleted(Integer user_id, Integer pageNum, Integer pageSize);

    BigDecimal calcPriceOfProductsByCategoryId(String order_id, Integer category_id);

    OrderMaster getOrderMasterById(String order_id);

    void updateOrderMaster(OrderMaster orderMaster);


}
