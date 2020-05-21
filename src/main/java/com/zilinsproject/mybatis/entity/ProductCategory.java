package com.zilinsproject.mybatis.entity;

import java.io.Serializable;
import java.util.Date;

public class ProductCategory implements Serializable {
    private Integer category_id;

    private String category_name;

    private Integer category_type;

    private Integer parent_id;

    private Boolean if_parent;

    private Boolean is_deleted;

    private Date create_time;

    private Date update_time;

    private static final long serialVersionUID = 1L;

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name == null ? null : category_name.trim();
    }

    public Integer getCategory_type() {
        return category_type;
    }

    public void setCategory_type(Integer category_type) {
        this.category_type = category_type;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public Boolean getIf_parent() {
        return if_parent;
    }

    public void setIf_parent(Boolean if_parent) {
        this.if_parent = if_parent;
    }

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", category_id=").append(category_id);
        sb.append(", category_name=").append(category_name);
        sb.append(", category_type=").append(category_type);
        sb.append(", parent_id=").append(parent_id);
        sb.append(", if_parent=").append(if_parent);
        sb.append(", is_deleted=").append(is_deleted);
        sb.append(", create_time=").append(create_time);
        sb.append(", update_time=").append(update_time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}