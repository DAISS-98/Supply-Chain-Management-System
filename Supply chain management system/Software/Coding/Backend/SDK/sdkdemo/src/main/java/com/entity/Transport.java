package com.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * transport
 */
@Getter
@Setter
@ToString
public class Transport {
    /**
     * 主键
     */
    private String transportKey;

    /**
     * 运输商名称
     */
    private String transportName;

    /**
     * 运输人
     */
    private String transportPerson;

    /**
     * 出发地
     */
    private String transportBeginAddress;

    /**
     * 出发时间
     */
    private Date transportBeginDate;

    /**
     * 出发时新鲜度
     */
    private String transportBeginFresh;

    /**
     * 目的地
     */
    private String transportEndAddress;

    /**
     * 到达时间
     */
    private Date transportEndDate;

    /**
     * 到达时新鲜度
     */
    private String transportEndFresh;

    /**
     * 运输温度
     */
    private String transportC;

    /**
     * 运输湿度
     */
    private String transportHumidity;

    /**
     * 运输行为信息
     */
    private String transportInfo;

    /**
     * 用户id
     */
    private Long transportUserId;

    private List list;

    private String superKey;
}