package com.dao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Date;

import com.entity.Produce;
import org.springframework.stereotype.Repository;

@Mapper
public interface ProduceMapper {
    int deleteByPrimaryKey(String produceKey);

    int insert(Produce record);

    int insertSelective(Produce record);

    Produce selectByPrimaryKey(String produceKey);

    int updateByPrimaryKeySelective(Produce record);

    int updateByPrimaryKey(Produce record);

    List<Produce> selectByAll(Produce produce);


}