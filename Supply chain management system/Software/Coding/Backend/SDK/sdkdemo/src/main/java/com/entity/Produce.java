package com.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**   生产商
    * produce
    */
@Getter
@Setter
@ToString
public class Produce {
    /**
    * 主键
    */
    private String produceKey;

    /**
    * 农产品名称
    */
    private String produceName;

    /**
    * 批次号
    */
    private String produceNum;

    /**
    * 品种
    */
    private String produceType;

    /**
    * 生产日期
    */
    private Date produceDate;

    /**
    * 生产地点
    */
    private String produceAddress;

    /**
    * 生产周期
    */
    private String produceCycle;

    /**
    * 生产人员
    */
    private String producePerson;

    /**
    * 新鲜度
    */
    private String produceFresh;

    /**
    * 环境温度
    */
    private String produceC;

    /**
    * 环境湿度
    */
    private String produceHumidity;

    /**
    * 生产商名称
    */
    private String produceSupplierName;

    /**
    * 下级商家名称
    */
    private String produceSubName;

    /**
    * 用户id
    */
    private Long produceUserId;

    private List list;
}