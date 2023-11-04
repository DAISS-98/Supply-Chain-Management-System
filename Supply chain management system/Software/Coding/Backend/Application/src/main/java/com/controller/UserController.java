//package com.controller;
//import com.common.ReturnCodeUtil;
//import com.common.StringUtil;
//import com.entity.User;
//import com.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import javax.annotation.Resource;
//import java.io.Serializable;
//import java.util.List;
//
///**
//* (user)表控制层
//*/
//@RestController
//@RequestMapping("/restful/user")
//public class UserController implements Serializable {
///**
//* 服务对象
//*/
//@Resource
//private UserService userService;
//
//    //用户登录
//    @PostMapping(value = "/login")
//    public ReturnCodeUtil login(@RequestBody User user){
//        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
//        //检查当前用户是否有效
//       if(user != null){
//           boolean b1 = StringUtil.isNotNullString(user.getUserType());
//           boolean b2 = StringUtil.isNotNullString(user.getAccount());
//           boolean b3 = StringUtil.isNotNullString(user.getPassword());
//           if(b1 && b2 && b3){
//               List<User> res = userService.findByAccountAndPasswordAndUserFlagAndCanUse(user);
//               //正确
//               if(res != null && res.size() == 1){
//                    returnCodeUtil.setDataAndTrue(res);
//                    return returnCodeUtil;
//               }
//           }else {
//                returnCodeUtil.setResultFalseAndMessage("登录失败，请重新填写信息。");
//               return returnCodeUtil;
//           }
//       }
//       returnCodeUtil.setResultFalseAndMessage("登录失败");
//       return returnCodeUtil;
//    }
//
//
//    //密码修改
//    @PostMapping(value = "/updatePassword")
//    public ReturnCodeUtil updatePassword(@RequestBody User user) {
//        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
//        returnCodeUtil.setResultTrue();
//        return returnCodeUtil;
//    }
//
//
//
//    //用户删除
//    @PostMapping(value = "/deleteUser")
//    public ReturnCodeUtil deleteUser(@RequestBody User user) {
//        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
//        returnCodeUtil.setResultTrue();
//        return returnCodeUtil;
//    }
//
//    //用户新增
//    @PostMapping(value = "/userInsert")
//    public ReturnCodeUtil userInsert(@RequestBody Object user , @RequestBody String userType) {
//        //根据userType 转object
//
//        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
//        if ( StringUtil.isNotNullString(userType) && user instanceof User ) {
//            Long id = StringUtil.getId(userType);
//
////            userService.insert();
//
//
//        }
//
//        return returnCodeUtil;
//    }
//
//
//
//
//
//
//
//
//
//}
