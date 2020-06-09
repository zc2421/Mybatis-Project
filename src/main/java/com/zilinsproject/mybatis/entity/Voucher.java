package com.zilinsproject.mybatis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Voucher implements Serializable {
    private Integer voucher_id;

    private String code;

    private String description;

    private BigDecimal deno;

    private String currency;

    private Integer condition_id;

    private Date start_date;

    private Date end_date;

    private Integer max_num;

    private static final long serialVersionUID = 1L;

    public Integer getVoucher_id() {
        return voucher_id;
    }

    public void setVoucher_id(Integer voucher_id) {
        this.voucher_id = voucher_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public BigDecimal getDeno() {
        return deno;
    }

    public void setDeno(BigDecimal deno) {
        this.deno = deno;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public Integer getCondition_id() {
        return condition_id;
    }

    public void setCondition_id(Integer condition_id) {
        this.condition_id = condition_id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Integer getMax_num() {
        return max_num;
    }

    public void setMax_num(Integer max_num) {
        this.max_num = max_num;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", voucher_id=").append(voucher_id);
        sb.append(", code=").append(code);
        sb.append(", description=").append(description);
        sb.append(", deno=").append(deno);
        sb.append(", currency=").append(currency);
        sb.append(", condition_id=").append(condition_id);
        sb.append(", start_date=").append(start_date);
        sb.append(", end_date=").append(end_date);
        sb.append(", max_num=").append(max_num);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}