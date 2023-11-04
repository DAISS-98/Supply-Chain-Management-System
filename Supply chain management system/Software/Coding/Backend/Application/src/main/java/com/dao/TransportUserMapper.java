package com.dao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.entity.TransportUser;
import org.springframework.stereotype.Repository;

@Mapper
public interface TransportUserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(TransportUser record);

    int insertSelective(TransportUser record);

    TransportUser selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(TransportUser record);

    int updateByPrimaryKey(TransportUser record);

    List<TransportUser> selectByAll(TransportUser transportUser);

    int updatePasswordByUserId(@Param("updatedPassword")String updatedPassword,@Param("userId")Long userId);

    List<TransportUser> selectByAccount(@Param("account")String account);



}