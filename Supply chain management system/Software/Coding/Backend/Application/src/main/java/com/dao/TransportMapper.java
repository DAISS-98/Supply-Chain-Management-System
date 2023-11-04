package com.dao;

import com.entity.Transport;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TransportMapper {
    int deleteByPrimaryKey(String transportKey);

    int insert(Transport record);

    int insertSelective(Transport record);

    Transport selectByPrimaryKey(String transportKey);

    int updateByPrimaryKeySelective(Transport record);

    int updateByPrimaryKey(Transport record);

    List<Transport> selectByAll(Transport transport);
}