package com.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
    * produce_user
    */
@Getter
@Setter
@ToString
public class ProduceUser {
    /**
    * userId
    */
    private Long userId;

    /**
    * account
    */
    private String account;

    /**
    * password
    */
    private String password;

    /**
    * userType
    */
    private String userType;

    /**
    * 用户名或者商家名称
    */
    private String userName;
}