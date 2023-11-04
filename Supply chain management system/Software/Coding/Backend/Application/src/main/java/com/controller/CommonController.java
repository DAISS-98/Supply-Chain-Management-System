package com.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.ReturnCodeUtil;
import com.common.StringUtil;
import com.entity.*;
import com.service.CommonService;
//import com.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.alibaba.fastjson.JSON.parseObject;

/**
* (user)表控制层
*/
@RestController
@RequestMapping("/restful/common")
public class CommonController implements Serializable {
/**
* 服务对象
*/

@Resource
private CommonService commonService;

    //用户登录
    @PostMapping(value = "/login")
    public ReturnCodeUtil login(@RequestBody User user){
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        //检查当前用户是否有效
       if(user != null){
           boolean account = StringUtil.isNotNullString(user.getAccount());
           boolean password = StringUtil.isNotNullString(user.getPassword());
           if(account && password){
               Boolean isTure = false;
               Object object = commonService.findUserInfoByAccount(user.getAccount(), user.getUserType());
               if(object != null || "admin".equals(user.getAccount())){
                   if(user.getUserType().equals( "1")){
                       ProduceUser object1 = (ProduceUser) object;
                       isTure = object1.getPassword().equals(user.getPassword());
                       returnCodeUtil.setDataAndTrue(object1);
                   }else if(user.getUserType() .equals( "2")){
                       ProcessUser object1 = (ProcessUser) object;
                       isTure = object1.getPassword().equals(user.getPassword());
                       returnCodeUtil.setDataAndTrue(object1);
                   }else if(user.getUserType() .equals( "3")){
                       TransportUser object1 = (TransportUser) object;
                       isTure = object1.getPassword().equals(user.getPassword());
                       returnCodeUtil.setDataAndTrue(object1);
                   }else if(user.getUserType() .equals( "4")){
                       StoreUser object1 = (StoreUser) object;
                       isTure = object1.getPassword().equals(user.getPassword());
                       returnCodeUtil.setDataAndTrue(object1);
                   }else if(user.getUserType() .equals( "5")){
                       SaleUser object1 = (SaleUser) object;
                       isTure = object1.getPassword().equals(user.getPassword());
                       returnCodeUtil.setDataAndTrue(object1);
                   }else if(user.getUserType() .equals( "6")){
                       boolean b1 = user.getPassword().equals("admin");
                       boolean b2 = user.getAccount().equals("admin");
                       if(b1 && b2) {
                           isTure = true;
                           user.setUserName("admin");
                           user.setUserType("6");
                           user.setUserId(new Long("60221155924"));
                           returnCodeUtil.setDataAndTrue(user);
                       }
                   }
                   if(isTure){
                       return returnCodeUtil;
                   }
               }
               returnCodeUtil.setResultFalseAndMessage("账户或密码错误");
           }else {
                returnCodeUtil.setResultFalseAndMessage("登录失败，请重新填写信息。");
               return returnCodeUtil;
           }
       }
       returnCodeUtil.setResultFalseAndMessage("登录失败");
       return returnCodeUtil;
    }


    //密码修改
    @PostMapping(value = "/updatePassword")
    public ReturnCodeUtil updatePassword(@RequestBody User user) {
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        commonService.updatePasswordByUserId(user.getUserId(),user.getPassword());
        returnCodeUtil.setResultTrue();
        return returnCodeUtil;
    }



    //用户删除
    @PostMapping(value = "/deleteUser")
    public ReturnCodeUtil deleteUser(@RequestBody User user) {
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        commonService.deleteUserByUserId(user.getUserId());
        returnCodeUtil.setResultTrue();
        return returnCodeUtil;
    }




    //用id检索出一览,统一的一览
    @PostMapping(value = "/searchList")
    public ReturnCodeUtil searchList(@RequestBody User user){
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        List list = commonService.searchList(user.getUserId());
        returnCodeUtil.setParseData(list);
        returnCodeUtil.setResult(true);
        return   returnCodeUtil;
    }
    //用id检索出一览,统一的一览
    @PostMapping(value = "/searchByKey")
    public ReturnCodeUtil searchList(@RequestBody Map map){
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        String  userType =(String) map.get("userType");
        String key = (String)map.get("key");
        List list = commonService.searchByKey(userType,key);
        returnCodeUtil.setParseData(list);
        returnCodeUtil.setResult(true);
        return   returnCodeUtil;
    }

    //统一的新增、编辑
    @PostMapping(value = "/insertOrUpdate")
    public ReturnCodeUtil editData(@RequestBody  Map map){
        Long userId = Long.valueOf((String)map.get("userId"));
        String key = (String)map.get("key");
        Object object = map.get("object");
        String supplierName = (String)map.get("supplierName");
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        commonService.insertOrUpdate(userId,key,supplierName,object);
        returnCodeUtil.setResult(true);
        return   returnCodeUtil;
    }
    //统一的删除
    @PostMapping(value = "/deleteOne")
    public ReturnCodeUtil deleteOne(@RequestBody  Map map){
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        commonService.deleteOne((String) map.get("key"));
        returnCodeUtil.setResult(true);
        return   returnCodeUtil;
    }


    //统一的注册
    @PostMapping(value = "/userRegister")
    public ReturnCodeUtil userRegister(@RequestBody  Map map){
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        String userType =(String) map.get("userType");
        Object user = map.get("user");
        Boolean aBoolean = commonService.addUser(user, userType);
        if(aBoolean){
            returnCodeUtil.setResult(true);
        }else {
            returnCodeUtil.setResultFalseAndMessage("用户已经存在");
        }
        return   returnCodeUtil;
    }


    /**
     * 传统方式，非常简单直接通过InetAddress获取，但不准确获取的为虚拟ip
     */
    @PostMapping(value = "/getIp")
    public ReturnCodeUtil getIp() throws Exception {
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        Map map = new HashMap();
        InetAddress localHost = InetAddress.getLocalHost();
        String hostAddress = localHost.getHostAddress();
        map.put("ip",hostAddress);
        returnCodeUtil.setNotListData(map);
        return returnCodeUtil;
    }

    //Map转化为对应的对象



    public  <T> T mapToObject(Object object, Class<T> clazz) {
        return parseObject(JSONObject.toJSONString(object), clazz);
    }

    //检索所有用户
    @PostMapping(value = "/searchAllUser")
    public ReturnCodeUtil searchAllUser(@RequestBody  User user){
        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
        List<User> allUser = commonService.findAllUser();
        returnCodeUtil.setData(allUser);
        returnCodeUtil.setResultTrue();
        return   returnCodeUtil;
    }






}
