package com.zilinsproject.mybatis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TSkuHistory implements Serializable {
    private Integer id;

    private String title;

    private BigDecimal price;

    private Integer category_id;

    private Integer brand_id;

    private Integer spg_id;

    private Boolean saleable;

    private Boolean valid;

    private Date create_time;

    private Date last_update_time;

    private String images;

    private String param;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

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

    public Integer getSpg_id() {
        return spg_id;
    }

    public void setSpg_id(Integer spg_id) {
        this.spg_id = spg_id;
    }

    public Boolean getSaleable() {
        return saleable;
    }

    public void setSaleable(Boolean saleable) {
        this.saleable = saleable;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getLast_update_time() {
        return last_update_time;
    }

    public void setLast_update_time(Date last_update_time) {
        this.last_update_time = last_update_time;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param == null ? null : param.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", price=").append(price);
        sb.append(", category_id=").append(category_id);
        sb.append(", brand_id=").append(brand_id);
        sb.append(", spg_id=").append(spg_id);
        sb.append(", saleable=").append(saleable);
        sb.append(", valid=").append(valid);
        sb.append(", create_time=").append(create_time);
        sb.append(", last_update_time=").append(last_update_time);
        sb.append(", images=").append(images);
        sb.append(", param=").append(param);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}