package com.dao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Date;

import com.entity.Process;
import org.springframework.stereotype.Repository;

@Mapper
public interface ProcessMapper {
    int deleteByPrimaryKey(String processKey);

    int insert(Process record);

    int insertSelective(Process record);

    Process selectByPrimaryKey(String processKey);

    int updateByPrimaryKeySelective(Process record);

    int updateByPrimaryKey(Process record);

    List<Process> selectByAll(Process process);


}