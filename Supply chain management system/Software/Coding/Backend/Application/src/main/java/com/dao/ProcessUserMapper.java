package com.dao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.entity.ProcessUser;
import org.springframework.stereotype.Repository;

@Mapper
public interface ProcessUserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(ProcessUser record);

    int insertSelective(ProcessUser record);

    ProcessUser selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(ProcessUser record);

    int updateByPrimaryKey(ProcessUser record);

    List<ProcessUser> selectByAll(ProcessUser processUser);

    int updatePasswordByUserId(@Param("updatedPassword")String updatedPassword,@Param("userId")Long userId);

    List<ProcessUser> selectByAccount(@Param("account")String account);





}