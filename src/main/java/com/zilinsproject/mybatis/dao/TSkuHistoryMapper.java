package com.zilinsproject.mybatis.dao;

import com.zilinsproject.mybatis.entity.TSkuHistory;
import java.util.List;

public interface TSkuHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TSkuHistory record);

    TSkuHistory selectByPrimaryKey(Integer id);

    List<TSkuHistory> selectAll();

    int updateByPrimaryKey(TSkuHistory record);
}