package com.dao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Date;

import com.entity.Sale;
import org.springframework.stereotype.Repository;

@Mapper
public interface SaleMapper {
    int deleteByPrimaryKey(String saleKey);

    int insert(Sale record);

    int insertSelective(Sale record);

    Sale selectByPrimaryKey(String saleKey);

    int updateByPrimaryKeySelective(Sale record);

    int updateByPrimaryKey(Sale record);

    List<Sale> selectByAll(Sale sale);


}