package com.fabric.po;

import com.entity.*;
import com.entity.Process;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@ToString
public class CommonUse implements Serializable {
    private String TID;
    private String TTime;
    private String IsDelete;
    private String role;


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
