package com.dao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.entity.StoreUser;
import org.springframework.stereotype.Repository;

@Mapper
public interface StoreUserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(StoreUser record);

    int insertSelective(StoreUser record);

    StoreUser selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(StoreUser record);

    int updateByPrimaryKey(StoreUser record);

    List<StoreUser> selectByAll(StoreUser storeUser);

    int updatePasswordByUserId(@Param("updatedPassword")String updatedPassword,@Param("userId")Long userId);

    List<StoreUser> selectByAccount(@Param("account")String account);


}