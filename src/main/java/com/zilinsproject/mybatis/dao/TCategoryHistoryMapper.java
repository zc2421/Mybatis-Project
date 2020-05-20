package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.TCategoryHistory;
import java.util.List;

public interface TCategoryHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TCategoryHistory record);

    TCategoryHistory selectByPrimaryKey(Integer id);

    List<TCategoryHistory> selectAll();

    int updateByPrimaryKey(TCategoryHistory record);
}