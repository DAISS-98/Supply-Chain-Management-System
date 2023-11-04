<template>
  <div class="login-wrap">
    <div class="ms-login">
      <div class="ms-title">农产品供应链管理系统</div>
<!--      <el-form :model="param" :rules="rules" ref="login" label-width="0px" class="ms-content">-->
      <el-form :model="param" ref="login" label-width="0px" class="ms-content">
        <el-radio-group v-model="param.userType" size="large">
        <el-radio-button label="1">生产商</el-radio-button>
        <el-radio-button label="2">加工商</el-radio-button>
        <el-radio-button label="3">运输商</el-radio-button>
        <el-radio-button label="4">仓储商</el-radio-button>
        <el-radio-button label="5">销售商</el-radio-button>
        <el-radio-button label="6">管理员</el-radio-button>
      </el-radio-group>
       <div style="height: 20px">
       </div>
        <el-form-item prop="username">
          <el-input v-model="param.account" placeholder="account">
            <template #prepend>
              <el-button :icon="User" />
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input type="password" placeholder="password" v-model="param.password"
            @keyup.enter="submitForm()">
            <template #prepend>
              <el-button :icon="Lock" />
            </template>
          </el-input>
        </el-form-item>
        <div class="login-btn">
          <el-button type="primary" @click="submitForm()">登录</el-button>
          <el-button type="primary" @click="register()">注册</el-button>
        </div>
<!--        <p class="login-tips">Tips : 用户名和密码随便填。</p>-->
      </el-form>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
import { User, Lock } from '@element-plus/icons'; // 图标
import userApi from '@api/user';
import * as baseData from '/src/api/baseData';
const router = useRouter();
const param = reactive({
  account: null,
  password: null,
  userType: "1"
});

// 用户校验
const rules = {
  username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
};
const login = ref(null);
// 状态管理
const store = useStore();
const register = () => {
  router.push('/register')
}

//设置用户信息
function setUserInfo(res) {
  debugger
  res.data.data=res.data.data[0]
  store.commit('setUser',res.data.data);
  store.commit('setUserType',res.data.data.userType);
  store.commit('setUserId',res.data.data.userId);
  store.commit('setAccount',res.data.data.account);
  localStorage.setItem("ms_username",res.data.data.userName);
  localStorage.setItem("userName",res.data.data.userName);
  localStorage.setItem("userType",res.data.data.userType);
  localStorage.setItem("userId",res.data.data.userId);
  localStorage.setItem("token","true");
}

//用户类型和home设置
const userInfoMap=reactive({
  userInfo:[
    {userType:1,userHomePath:'/produceHome'},
    {userType:2,userHomePath:'/processHome'},
    {userType:3,userHomePath:'/transportHome'},
    {userType:4,userHomePath:'/storeHome'},
    {userType:5,userHomePath:'/saleHome'},
    {userType:6,userHomePath:'/adminHome'}
  ]
})

const submitForm = () => {
  baseData
        .login(param)
        .then((res) => {
          localStorage.setItem("token","false");
          console.log(res)
          if(res.data.result){
            //保存用户信息
            setUserInfo(res)
            //循环判断要跳转哪个主页
            for(let item of userInfoMap.userInfo){
              if(param.userType == item.userType){
                router.push(item.userHomePath)
              }
            }
            ElMessage.success('登录成功');
          }else {
            ElMessage.error('账户或密码不正确!');
          }
        })

  // });
};


store.commit('clearTags'); // 清空标签

// defineExpose 可以省略
defineExpose({
  rules,
  submitForm,
});
</script>

<style scoped>
.login-wrap {
  position: relative;
  width: 100%;
  height: 100%;
  /*background-image: url(../assets/img/login-bg.jpg);*/
  background-image: url(../assets/img/login05.jpg);
  background-size: 100%;
  background-repeat: no-repeat;
  /*background-attachment:fixed;*/
  background-size: cover;
}
.ms-title {
  width: 100%;
  line-height: 50px;
  text-align: center;
  font-size: 25px;
  color: #0e4d8c;
  border-bottom: 1px solid #ddd;
}
.ms-login {
  position: absolute;
  left: 46%;
  top: 50%;
  width: 580px;
  margin: -190px 0 0 -175px;
  border-radius: 5px;
  background: rgba(255, 255, 255, 0.3);
  overflow: hidden;
}
.ms-content {
  padding: 30px 30px;
}
.login-btn {
  text-align: center;
}
.login-btn button {
  width: 47%;
  height: 36px;
  margin-bottom: 10px;
}
.login-tips {
  font-size: 12px;
  line-height: 30px;
  color: #fff;
}
</style>
