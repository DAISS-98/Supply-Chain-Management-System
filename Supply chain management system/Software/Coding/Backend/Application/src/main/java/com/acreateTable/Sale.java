package com.acreateTable;

import java.util.Date;

/**
 * 销售商
 */
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
     * 环境温度
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

}
