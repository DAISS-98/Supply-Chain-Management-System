//package com.service;
//
//import org.springframework.stereotype.Service;
//import javax.annotation.Resource;
//import com.entity.User;
//import com.dao.UserMapper;
//
//import java.util.List;
//
//
//
//@Service
//public class UserService{
//
//    @Resource
//    private UserMapper userMapper;
//
//
//    public int deleteByPrimaryKey(Integer userId) {
//        return userMapper.deleteByPrimaryKey(userId);
//    }
//
//    /**  */
//    public int insert(User record) {
//        return userMapper.insert(record);
//    }
//
//
//    public int insertSelective(User record) {
//        return userMapper.insertSelective(record);
//    }
//
//
//    public User selectByPrimaryKey(Integer userId) {
//        return userMapper.selectByPrimaryKey(userId);
//    }
//
//
//    public int updateByPrimaryKeySelective(User record) {
//        return userMapper.updateByPrimaryKeySelective(record);
//    }
//
//
//    public int updateByPrimaryKey(User record) {
//        return userMapper.updateByPrimaryKey(record);
//    }
//
//    public  void setUserInfo(User user){
//    }
//
//    public List setUserListInfo(List<? extends User> users){
//        if(users != null && users.size()>0){
//            for(int i = 0;i<users.size();i++){
//                User user = users.get(i);
//                this.setUserInfo(user);
//            }
//        }
//        return users;
//
//    }
//
//	public Boolean isAccountCanRegister(User user){
//        String account = user.getAccount();
//        List<User> byAccount = userMapper.findByAccount(account);
//        if(byAccount == null || byAccount.size() <1){
//            return true;
//        }else if (byAccount.size()>0){
//            return false;
//        }
//        return true;
//    }
//
//	public List<User> findByAccountAndPasswordAndUserFlagAndCanUse(User user){
//		 return userMapper.findByAccountAndPasswordAndUserFlagAndCanUse(user);
//	}
//
//	public int updatePasswordByUserId(String updatedPassword,Integer userId){
//		 return userMapper.updatePasswordByUserId(updatedPassword,userId);
//	}
//
//	public List<User> getUserApproveList(){
////        userMapper
//        return null;
//    }
//
//	public int updateCanUseByUserId(String updatedCanUse,Integer userId){
//		 return userMapper.updateCanUseByUserId(updatedCanUse,userId);
//	}
//
//	public List<User> selectAllByCanUse(String canUse){
//		 return userMapper.selectAllByCanUse(canUse);
//	}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
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
