<template>
  <base-table>
  <div class="login-wrap">
    <div class="ms-login">
      <div class="ms-title">用户个人信息</div>
<!--      <el-form :model="param" :rules="rules" ref="login" label-width="0px" class="ms-content">-->
      <el-form :model="param" ref="login" label-width="0px" class="ms-content">
<!--       <div style="height: 5px">-->
<!--       </div>-->

        <el-form-item  label="账户" label-width="80px" prop="account">
          <el-input v-model="param.data.account" disabled="true"  minlength="5" maxlength="25" placeholder="请输入账户" >
          </el-input>
        </el-form-item>
<!--        <el-form-item label="密码" label-width="80px" prop="password">-->
<!--          <el-input  placeholder="请输入密码" type="password"  minlength="6" maxlength="24"  v-model="param.data.password">-->
<!--          </el-input>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="确认密码" label-width="80px" prop="password2">-->
<!--          <el-input  placeholder="请再次输入密码"   type="password"  minlength="6" maxlength="24"  v-model="param.data.password2">-->
<!--          </el-input>-->
<!--        </el-form-item>-->
        <el-form-item label="用户名" label-width="80px" prop="userName">
          <el-input  placeholder="请输入用户名" disabled="true"  v-model="param.data.userName">
          </el-input>
        </el-form-item>
        <el-form-item label="联系号码" label-width="80px" prop="phone">
          <el-input  placeholder="请输入联系号码" disabled="true"  v-model="param.data.phone">
          </el-input>
        </el-form-item>
<!--   种养殖用户  -->
        <el-form-item  label="企业名称" label-width="80px" prop="breedName">
          <el-input   placeholder="请输入企业名称" disabled="true"  v-model="param.data.breedName">
          </el-input>
        </el-form-item>
        <el-form-item  label="企业地址" label-width="80px" prop="breedAddress">
          <el-input   placeholder="请输入企业地址" disabled="true"  v-model="param.data.breedAddress">
          </el-input>
        </el-form-item>
        <el-form-item  label="营业证号" label-width="80px" prop="breedNum">
          <el-input   placeholder="请输入营业证号码" disabled="true"  v-model="param.data.breedNum">
          </el-input>
        </el-form-item>
        <el-form-item  label="企业法人" label-width="80px" prop="breedLegalPerson">
          <el-input   placeholder="请输入企业法人" disabled="true"  v-model="param.data.breedLegalPerson">
          </el-input>
        </el-form-item>
        <el-form-item v-if="param.data.pictureGuid != null"  label="资格证书" label-width="80px" prop="pictureGuid">
              <upload-img @getGuid="getGuid" :imgGuid="param.data.pictureGuid" disabled="true" >
              </upload-img>
        </el-form-item>
<!--        <p class="login-tips">Tips : 用户名和密码随便填。</p>-->
      </el-form>
    </div>
  </div>
    </base-table>
</template>


<script setup>
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
import { User, Lock } from '@element-plus/icons'; // 图标
import userApi from '@api/user';
import * as baseData from '/src/api/baseData';
import UploadImg from '@components/componentUtil/UploadImg.vue';
import { useRoute } from 'vue-router';
import { Plus } from '@element-plus/icons-vue'
import axios from 'axios';
import { reactive, onMounted  ,watch, ref} from 'vue';

const param = reactive({
  data:{
    account: null,
    password: null,
    password2:null,
    userName: null,
    userFlag: '1',
    phone:null,
    breedName:null,
    breedAddress:null,
    breedNum:null,
    breedLegalPerson:null,
    pictureGuid:null
  }
});

// };
const login = ref(null);

// 状态管理
const store = useStore();
const back = () => {
  router.push('/login');
}



function getData() {
  baseData
      .viewBreedUser({userId:localStorage.getItem("userId")})
      .then((res) => {
        param.data = res.data.data[0]
      })
      .catch(() => {
        // ElMessage.error('加载出现小bug!');
      });
}

onMounted(() => {
  getData();
});

// 路由
const route = useRoute();
watch(
    () => route.path,
    (oldValue, newValue) => {
    }
);

// store.commit('clearTags'); // 清空标签

// defineExpose 可以省略
// defineExpose({
//   submitForm,
// });
</script>

<style scoped>
.login-wrap {
  position: relative;
  width: 100%;
  height: 100%;
  /*background-image: url(../assets/img/login-bg.jpg);*/
  background-color: white;
  background-size: 100%;
  overflow: auto;
}
.ms-title {
  width: 100%;
  line-height: 50px;
  text-align: center;
  font-size: 20px;
  color: #409EFF;
  border-bottom: 1px solid #ddd;
}
.ms-login {
  position: absolute;
  left: 35%;
  /*top: 45%;*/
  width: 500px;
  /*margin: -190px 0 0 -175px;*/
  border-radius: 5px;
  /*background: rgba(255, 255, 255, 0.3);*/
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
  /*color: #fff;*/
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
