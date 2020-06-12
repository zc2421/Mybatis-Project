package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.Scheduler;
import java.util.List;

public interface SchedulerMapper {
    int deleteByPrimaryKey(Integer cron_id);

    int insert(Scheduler record);

    Scheduler selectByPrimaryKey(Integer cron_id);

    List<Scheduler> selectAll();

    int updateByPrimaryKey(Scheduler record);
}