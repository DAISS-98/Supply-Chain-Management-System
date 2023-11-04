package com.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
//数据封装集，用来将数据发送到fabric中
public class FabricData implements Serializable {
    private final static long serialVersionUID = 1;
    //溯源码
    private  String onlyKey;
    //设置id
    private List list;

    private Produce produce;

    private Process process;

    private Transport transport;

    private Store store;

    private Sale sale;

}
