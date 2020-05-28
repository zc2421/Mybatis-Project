package com.zilinsproject.mybatis.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * @author zilinsmac
 */
@Data
public class BalanceSubmitForm {


    private Integer user_id;

    private BigDecimal amount;

}
