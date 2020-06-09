package com.zilinsproject.mybatis.entity;

import java.io.Serializable;

public class VoucherCondition implements Serializable {
    private Integer id;

    private Integer condition_id;

    private String condition;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCondition_id() {
        return condition_id;
    }

    public void setCondition_id(Integer condition_id) {
        this.condition_id = condition_id;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition == null ? null : condition.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", condition_id=").append(condition_id);
        sb.append(", condition=").append(condition);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}