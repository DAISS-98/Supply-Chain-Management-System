package com.dao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.entity.ProduceUser;
import org.springframework.stereotype.Repository;

@Mapper
public interface ProduceUserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(ProduceUser record);

    int insertSelective(ProduceUser record);

    ProduceUser selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(ProduceUser record);

    int updateByPrimaryKey(ProduceUser record);

    List<ProduceUser> selectByAll(ProduceUser produceUser);

    int updatePasswordByUserId(@Param("updatedPassword")String updatedPassword,@Param("userId")Long userId);

    List<ProduceUser> selectByAccount(@Param("account")String account);








}