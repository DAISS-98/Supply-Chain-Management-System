package com.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

//通用类
@Getter
@Setter
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
    * 用户id 唯一主键
    */
    private Long userId;

    /**
    * 用户账户
    */
    private String account;

    /**
    * 用户密码
    */
    private String password;

    /**
    * 用户类型   1 种养殖用户  2 运输用户   3销售用户  4监管用户 5管理员
    */
    private String userType;

    /**
     * 用户名
     */
    private String userName;

}
