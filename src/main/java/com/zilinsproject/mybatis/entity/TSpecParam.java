package com.zilinsproject.mybatis.entity;

import java.io.Serializable;

public class TSpecParam implements Serializable {
    private Integer id;

    private Integer spg_id;

    private Integer spp_id;

    private String name;

    private Boolean numeric;

    private String unit;

    private String value;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSpg_id() {
        return spg_id;
    }

    public void setSpg_id(Integer spg_id) {
        this.spg_id = spg_id;
    }

    public Integer getSpp_id() {
        return spp_id;
    }

    public void setSpp_id(Integer spp_id) {
        this.spp_id = spp_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Boolean getNumeric() {
        return numeric;
    }

    public void setNumeric(Boolean numeric) {
        this.numeric = numeric;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", spg_id=").append(spg_id);
        sb.append(", spp_id=").append(spp_id);
        sb.append(", name=").append(name);
        sb.append(", numeric=").append(numeric);
        sb.append(", unit=").append(unit);
        sb.append(", value=").append(value);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}