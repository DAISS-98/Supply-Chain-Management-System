package com.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * store
 */
@Getter
@Setter
@ToString
public class Store {
    /**
     * 主键
     */
    private String storeKey;

    /**
     * 仓储商名称
     */
    private String storeName;

    /**
     * 仓储地点
     */
    private String storeAddress;

    /**
     * 管理员
     */
    private String storePerson;

    /**
     * 入库时间
     */
    private Date storeIntime;

    /**
     * 入库新鲜度
     */
    private String storeFresh;

    /**
     * 仓储温度
     */
    private String storeC;

    /**
     * 仓储湿度
     */
    private String storeHumidity;

    /**
     * 仓储其他信息
     */
    private String storeInfo;

    /**
     * 其他
     */
    private String storeOther;

    /**
     * 用户id
     */
    private Long storeUserId;

    private List list;

    private String superKey;
}