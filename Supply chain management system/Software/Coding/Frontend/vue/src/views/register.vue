<template>
  <base-table>
  <div class="login-wrap">
    <div class="ms-login">
      <div class="ms-title">用户注册</div>
<!--      <el-form :model="param" :rules="rules" ref="login" label-width="0px" class="ms-content">-->
      <el-form :model="param" ref="login" label-width="0px" class="ms-content">
<!--       <div style="height: 5px">-->
<!--       </div>-->
        <el-form-item label="用户类型" label-width="80px"  prop="light">
          <el-select v-model="this.param.userType" placeholder="请选择用户类型"  width="50px" >
            <el-option key="1" label="生产商" value='1'></el-option>
            <el-option key="2" label="加工商" value='2'></el-option>
            <el-option key="3" label="运输商" value='3'></el-option>
            <el-option key="4" label="仓储商" value='4'></el-option>
            <el-option key="5" label="销售商" value='5'></el-option>
          </el-select>
        </el-form-item>
        <el-form-item  label="账户" label-width="80px" prop="account">
          <el-input v-model="param.account" minlength="5" maxlength="25" placeholder="请输入账户(手机号)" >
          </el-input>
        </el-form-item>

        <el-form-item label="商家名称" label-width="80px" prop="userName">
          <el-input  placeholder="请输入商家名称"  v-model="param.userName">
          </el-input>
        </el-form-item>
        <el-form-item label="密码" label-width="80px" prop="password">
          <el-input  placeholder="请输入密码" type="password"  minlength="6" maxlength="24"  v-model="param.password">
          </el-input>
        </el-form-item>
        <el-form-item label="确认密码" label-width="80px" prop="password2">
          <el-input  placeholder="请再次输入密码"   type="password"  minlength="6" maxlength="24"  v-model="param.password2">
          </el-input>
        </el-form-item>
        <div class="login-btn">
          <el-button type="primary" @click="submitForm()">注册</el-button>
          <el-button type="primary" @click="back()">返回</el-button>
        </div>
<!--        <p class="login-tips">Tips : 用户名和密码随便填。</p>-->
      </el-form>
    </div>
  </div>
    </base-table>
</template>


<script setup>
import { ref, reactive,watch } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
import { User, Lock } from '@element-plus/icons'; // 图标
import userApi from '@api/user';
import * as baseData from '/src/api/baseData';
import UploadImg from '@components/componentUtil/UploadImg.vue';

import { Plus } from '@element-plus/icons-vue'
import axios from 'axios';
import {userRegister} from "/src/api/baseData";

const router = useRouter();
const param = reactive({
  account: null,
  password: null,
  password2:null,
  userName: null,
  userType: '1'
  // phone:null,
  // breedName:null,
  // breedAddress:null,
  // breedNum:null,
  // breedLegalPerson:null,
  // pictureGuid:null
});

//校验信息是否填写完整BEGIN-------------------------------
function checkUserInfo() {
  for (let s in param){
    let item = param[s]
    if(item == '' || item == null || item == undefined ){
      ElMessage.error('用户注册信息填写不完整，请重写填写!');
      return false;
    }
  }
  return true;
}
//校验信息是否填写完整END---------------------------------
// 用户校验
// const rules = {
//   username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
//   password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
// };
const login = ref(null);
// 状态管理
const store = useStore();
const back = () => {
  router.push('/login');
}
watch(

    // () =>param.userType ,
    // (oldValue, newValue) => {
    //   onReset()
    //   addFlagContribute()
    // },
);

//选择其他类型时，重置页面字段
function onReset(){

  for(let item in param){
    if(checkIsDelete(item)){
      delete param[item]
    }
  }
}

//判断字段是否需要重置
function checkIsDelete(name){
  if(name == 'userType'){
    return false;
  }
  return true;
}
//添加对应的字段
// function addFlagContribute(){
//   if(param.userType == '1'){
//     param.breedName=null,
//     param.breedAddress=null,
//     param.breedNum=null,
//     param.breedLegalPerson=null,
//     param.pictureGuid=null
//   }else if(param.userType == '2'){
//     param.transportName=null,
//     param.transportAddress=null,
//     param.transportLegalPerson=null
//   }else if(param.userType == '3'){
//     param.saleName=null,
//     param.saleAddress=null,
//     param.saleLegalPerson=null
//   }else if(param.userType == '4'){
//     param.superviseName=null
//   }
// }


const submitForm = () => {
  // login.value.validate((valid) => {
  // store.commit('setAllNull');
   if(!checkUserInfo()){
     return ;
   }
   if(param.password != param.password2 ){
     ElMessage.error('两次密码不一样，请重新填写!');
     return ;
   }

    baseData.userRegister({user:{...param},userType:param.userType})
        .then((res) => {
          debugger
          if(res.data.result){
              router.push('/login')
            ElMessage.success('注册成功!');
          }else {
            ElMessage.error(res.data.message);
          }
        })
        .catch(() => {
          ElMessage.error('注册失败!');
        });

  // });
};

function getGuid(res){
  param.pictureGuid=res
}

store.commit('clearTags'); // 清空标签

// defineExpose 可以省略
// defineExpose({
//   submitForm,
// });
</script>

<style scoped>
.login-wrap {
  /*position: relative;*/
  /*width: 100%;*/
  /*height: 100%;*/
  /*!*background-image: url(../assets/img/login-bg.jpg);*!*/
  /*background-image: url(../assets/img/login05.jpg);*/
  /*background-size: 100%;*/
  /*overflow: auto;*/
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
  left: 40%;
  top: 30%;
  width: 500px;
  /*margin: -190px 0 0 -175px;*/
  border-radius: 5px;
  background: rgba(255, 255, 255, 0.3);
  overflow: auto;
}
.ms-content {
  top:10%;
  padding: 30px 30px;
  text-align: center;
  font-size: 20px;
  color: #409EFF;
  border-bottom: 1px solid #ddd;
}
.login-btn {
  text-align: center;
}
.login-btn button {
  width: 40%;
  height: 36px;
  margin-bottom: 10px;
}
.login-tips {
  font-size: 12px;
  line-height: 30px;
  color: #fff;
}
</style>
<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}

.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
