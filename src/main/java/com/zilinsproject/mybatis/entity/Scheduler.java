package com.zilinsproject.mybatis.entity;

import java.io.Serializable;

public class Scheduler implements Serializable {
    private Integer cron_id;

    private String cron_name;

    private String cron;

    private static final long serialVersionUID = 1L;

    public Integer getCron_id() {
        return cron_id;
    }

    public void setCron_id(Integer cron_id) {
        this.cron_id = cron_id;
    }

    public String getCron_name() {
        return cron_name;
    }

    public void setCron_name(String cron_name) {
        this.cron_name = cron_name == null ? null : cron_name.trim();
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron == null ? null : cron.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cron_id=").append(cron_id);
        sb.append(", cron_name=").append(cron_name);
        sb.append(", cron=").append(cron);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}