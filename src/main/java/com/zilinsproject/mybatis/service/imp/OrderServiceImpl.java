package com.zilinsproject.mybatis.service.imp;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zilinsproject.mybatis.dao.OrderDetailMapperExtended;
import com.zilinsproject.mybatis.dao.OrderMasterMapperExtended;
import com.zilinsproject.mybatis.entity.CartInfo;
import com.zilinsproject.mybatis.entity.OrderDetail;
import com.zilinsproject.mybatis.entity.OrderMaster;
import com.zilinsproject.mybatis.entity.ProductInfo;
import com.zilinsproject.mybatis.enums.OrderStatusEnum;
import com.zilinsproject.mybatis.enums.PaymentStatusEnum;
import com.zilinsproject.mybatis.enums.ResultEnum;
import com.zilinsproject.mybatis.exceptions.CustomizeException;
import com.zilinsproject.mybatis.service.OrderService;
import com.zilinsproject.mybatis.service.ProductPageService;
import com.zilinsproject.mybatis.utils.KeyUtils;
import com.zilinsproject.mybatis.dto.OrderDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    ProductPageService productService;
    @Autowired
    OrderDetailMapperExtended orderDetailMapper;
    @Autowired
    OrderMasterMapperExtended orderMasterMapper;

    @Override
    public OrderDTO createOrder(Integer user_id, List<CartInfo> items) {
        String orderId = KeyUtils.genUniqueKey();
        BigDecimal totalPrice = new BigDecimal(0);
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (CartInfo item: items){
            //查看商品是否存在
            ProductInfo product = productService.getProductById(item.getProduct_id());
            if (product == null){
                throw new CustomizeException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //计算价格
            totalPrice = product.getProduct_price()
                    .multiply(new BigDecimal(item.getCart_number()))
                    .add(totalPrice);

            //写入order detail数据表
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder_id(orderId);
            orderDetail.setProduct_id(item.getProduct_id());
            orderDetail.setProduct_name(product.getProduct_name());
            orderDetail.setPrice(product.getProduct_price());
            orderDetail.setNum(item.getCart_number());
            orderDetailMapper.insertAutoFill(orderDetail);
            orderDetailList.add(orderDetail);
        }

        //写入order master数据表
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrder_id(orderId);
        orderMaster.setUser_id(user_id);
        orderMaster.setUser_address("");
        orderMaster.setAmount(totalPrice);
        orderMaster.setOrder_status(OrderStatusEnum.INCOMPLETE.getCode());
        orderMaster.setPayment_status(PaymentStatusEnum.INCOMPLETE.getCode());

        //优惠券
        orderMaster.setVoucher_id(null);

        orderMasterMapper.insertAutoFill(orderMaster);

        //减库存？

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }


    @Override
    public OrderDTO selectOrderById(String order_id) {
        OrderDTO orderDTO = new OrderDTO();
        //get order master info
        OrderMaster orderMaster = orderMasterMapper.selectByPrimaryKey(order_id);
        BeanUtils.copyProperties(orderMaster, orderDTO);

        //get order detail info
        List<OrderDetail> orderDetailList = orderDetailMapper.selectByOrderId(order_id);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    @Override
    @Transactional(rollbackFor = CustomizeException.class)
    public void deleteOrderById(String order_id) {
        //check if order exist
        //delete orderMaster
        OrderMaster orderMaster = orderMasterMapper.selectByPrimaryKey(order_id);
        if (orderMaster == null){
            throw new CustomizeException(ResultEnum.ORDER_NOT_EXIST);
        }
        orderMasterMapper.deleteByPrimaryKey(order_id);

        //delete orderDetail
        orderDetailMapper.deleteByOrderId(order_id);
    }


    @Override
    @Transactional(rollbackFor = CustomizeException.class)
    public void completeOrder(String order_id) {
        OrderMaster orderMaster = orderMasterMapper.selectByPrimaryKey(order_id);
        if (orderMaster == null){
            throw new CustomizeException(ResultEnum.ORDER_NOT_EXIST);
        }
        orderMaster.setOrder_status(OrderStatusEnum.COMPLETE.getCode());
        orderMasterMapper.updateByPrimaryKey(orderMaster);
    }


    private List<OrderDTO> getOrdersStatusByUserId(Integer user_id, Integer status){
        List<OrderDTO> orderDTOs = new ArrayList<>();
        List<OrderMaster> orderMasterList = orderMasterMapper.selectByUserId(user_id);
        for (OrderMaster orderMaster: orderMasterList){
            if (orderMaster.getOrder_status().equals(status)){
                OrderDTO orderDTO = new OrderDTO();
                BeanUtils.copyProperties(orderMaster, orderDTO);
                String orderId = orderMaster.getOrder_id();
                orderDTO.setOrderDetailList( orderDetailMapper.selectByOrderId(orderId) );
                orderDTOs.add(orderDTO);
            }
        }
        return orderDTOs;
    }

    @Override
    public PageInfo<OrderDTO> listAllOrdersInProgress(Integer user_id, Integer pageNum, Integer pageSize) {
        List<OrderDTO> orderDTOs = getOrdersStatusByUserId(user_id, OrderStatusEnum.INCOMPLETE.getCode());
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(orderDTOs);
    }

    @Override
    public PageInfo<OrderDTO> listAllOrdersCompleted(Integer user_id, Integer pageNum, Integer pageSize) {
        List<OrderDTO> orderDTOs = getOrdersStatusByUserId(user_id, OrderStatusEnum.COMPLETE.getCode());
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(orderDTOs);
    }

    @Override
    public BigDecimal calcPriceOfProductsByCategoryId(String order_id, Integer category_id) {
        List<OrderDetail> orderDetailList = orderDetailMapper.selectByOrderId(order_id);
        BigDecimal total_amount = new BigDecimal(0);
        for (OrderDetail orderDetail: orderDetailList){
            ProductInfo productInfo = productService.getProductById(orderDetail.getProduct_id());
            if (productInfo.getCategory_type().equals(category_id)){
                total_amount = productInfo.getProduct_price()
                        .multiply(new BigDecimal(orderDetail.getNum()))
                        .add(total_amount);
            }
        }
        return total_amount;
    }

    @Override
    public OrderMaster getOrderMasterById(String order_id) {
        return orderMasterMapper.selectByPrimaryKey(order_id);
    }

    @Override
    @Transactional(rollbackFor = CustomizeException.class)
    public void updateOrderMaster(OrderMaster orderMaster) {
        OrderMaster order = orderMasterMapper.selectByPrimaryKey(orderMaster.getOrder_id());
        if (order == null){
            throw new CustomizeException(ResultEnum.ORDER_NOT_EXIST);
        }
        orderMasterMapper.updateByOrderId(orderMaster);
    }
}
