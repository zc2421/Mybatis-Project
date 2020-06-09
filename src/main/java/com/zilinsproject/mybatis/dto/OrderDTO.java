package com.zilinsproject.mybatis.dto;

import com.zilinsproject.mybatis.entity.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author zilinsmac
 */
@Data
public class OrderDTO {

    private String order_id;

    private Integer user_id;

    private String user_address;

    private BigDecimal amount;

    private Integer order_status;

    private Integer payment_status;

    private Integer voucher_id;

    private Date create_time;

    private List<OrderDetail> orderDetailList;

}
