package com.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.entity.User;
import org.apache.ibatis.annotations.Mapper;


public interface UserMapper {

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> findByAccount(@Param("account")String account);

    List<User> findByAccountAndPasswordAndUserFlagAndCanUse(User user);

    int updatePasswordByUserId(@Param("updatedPassword")String updatedPassword,@Param("userId")Integer userId);

    int updateCanUseByUserId(@Param("updatedCanUse")String updatedCanUse,@Param("userId")Integer userId);

    List<User> selectAllByCanUse(@Param("canUse")String canUse);











}
