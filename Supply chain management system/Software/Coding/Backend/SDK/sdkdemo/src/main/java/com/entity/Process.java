package com.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/** 加工商
    * process
    */
@Getter
@Setter
@ToString
public class Process {
    /**
    * 主键
    */
    private String processKey;

    /**
    * 加工商名称
    */
    private String processName;

    /**
    * 进货时间
    */
    private Date processGetDate;

    /**
    * 加工日期
    */
    private Date processTime;

    /**
    * 加工地点
    */
    private String processAddress;

    /**
    * 加工人员
    */
    private String processPerson;

    /**
    * 新鲜度
    */
    private String processFresh;

    /**
    * 环境温度
    */
    private String processC;

    /**
    * 环境湿度
    */
    private String processHumidity;

    /**
    * 用户id
    */
    private Long processUserId;

    private List list;

    private String superKey;
}