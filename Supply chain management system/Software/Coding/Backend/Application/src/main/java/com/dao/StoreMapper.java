package com.dao;

import com.entity.Store;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface StoreMapper {
    int deleteByPrimaryKey(String storeKey);

    int insert(Store record);

    int insertSelective(Store record);

    Store selectByPrimaryKey(String storeKey);

    int updateByPrimaryKeySelective(Store record);

    int updateByPrimaryKey(Store record);

    List<Store> selectByAll(Store store);
}