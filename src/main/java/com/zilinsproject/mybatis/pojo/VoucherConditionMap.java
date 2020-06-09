package com.zilinsproject.mybatis.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class VoucherConditionMap {

    private BigDecimal amount;

    private Integer category_id;

    private Integer shop_id;
}
