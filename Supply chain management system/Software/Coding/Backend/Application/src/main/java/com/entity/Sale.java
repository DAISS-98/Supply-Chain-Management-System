package com.entity;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** 销售商
    * sale
    */
@Getter
@Setter
@ToString
public class Sale {
    /**
    * 主键
    */
    private String saleKey;

    /**
    * 本级经销商名称
    */
    private String saleName;

    /**
    * 上架时间
    */
    private Date saleOnDate;

    /**
    * 新鲜度
    */
    private String saleFresh;

    /**
    * 环境温度
    */
    private String saleC;

    /**
    * 环境湿度
    */
    private String saleHumidity;

    /**
    * 销售员
    */
    private String salePerson;

    /**
    * 销售单价（元/斤）
    */
    private String salePrice;

    /**
    * 用户id
    */
    private Long userId;


    private List list;

    private String superKey;

}