package com.dao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.entity.SaleUser;
import org.springframework.stereotype.Repository;

@Mapper
public interface SaleUserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(SaleUser record);

    int insertSelective(SaleUser record);

    SaleUser selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(SaleUser record);

    int updateByPrimaryKey(SaleUser record);

    List<SaleUser> selectByAll(SaleUser saleUser);

    int updatePasswordByUserId(@Param("updatedPassword")String updatedPassword,@Param("userId")Long userId);

    List<SaleUser> selectByAccount(@Param("account")String account);



}