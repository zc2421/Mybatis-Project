package com.zilinsproject.mybatis.entity;

import java.io.Serializable;

public class TCategoryBrand implements Serializable {
    private Integer category_id;

    private Integer brand_id;

    private static final long serialVersionUID = 1L;

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public Integer getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Integer brand_id) {
        this.brand_id = brand_id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", category_id=").append(category_id);
        sb.append(", brand_id=").append(brand_id);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}