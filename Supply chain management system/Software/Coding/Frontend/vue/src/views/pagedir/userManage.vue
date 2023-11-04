<template>
  <base-table>
  <div class="container">
    <!-- 搜索、清除搜索、多选删除、添加 -->
    <div class="handle-box">
      <el-row>

      </el-row>
    </div>
<!--添加弹窗-->

<!--编辑弹窗-->
    <el-dialog
        v-model="dialogVisible.isShowEdit"
        title="密码修改"
        width="30%"
        :before-close="handleClose"
    >
            <el-form status-icon label-width="100px" ref="formRef" :model="allData.editData" >
              <el-form-item label="用户账户" prop="account">
                <el-input v-model="allData.editData.account" disabled="true" />
              </el-form-item>
              <el-form-item label="用户名" prop="userName " >
                <el-input v-model="allData.editData.userName"  disabled="true"/>
              </el-form-item>
              <el-form-item label="用户类型" label-width="80px"  prop="light">
                <el-select v-model="allData.editData.userType" disabled="true"   width="50px" >
                  <el-option key="1" label="生产商" value='1'></el-option>
                  <el-option key="2" label="加工商" value='2'></el-option>
                  <el-option key="3" label="运输商" value='3'></el-option>
                  <el-option key="4" label="仓储商" value='4'></el-option>
                  <el-option key="5" label="销售商" value='5'></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="密码" prop="password">
                <el-input v-model="allData.editData.password" />
              </el-form-item>
            </el-form>

      <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible.isShowEdit = false">取消</el-button>
        <el-button type="primary" @click="editData()"
        >确认</el-button
        >
      </span>
      </template>
    </el-dialog>
<!--数据展示-->
    <el-table
        :data="allData.tableData.slice((page.currentPage-1)*page.pageSize,page.currentPage*page.pageSize)"
        border
        style="width: 100%">
      <el-table-column prop="account" label="账户" />
      <el-table-column prop="userName" label="用户名"  />
      <el-table-column  label="用户类型" >
        <template #default="scope">
        <text>{{getPara(scope.$index, scope.row)}}</text>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" >
        <template #default="scope">
          <el-button type="text" size="small" @click="openEdit(scope.$index, scope.row)"
          >改密</el-button>
          <el-button type="text" size="small" @click="deleteData(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin-top: 10px;text-align: right">
      <pagination :page-size="page.pageSize" :page-total="page.total"
                  :disabled="false" @page-index="pageIndex" />

    </div>
  </div>
  </base-table>


</template>

<script setup>

import { reactive, onMounted  ,watch, ref} from 'vue';
import { useStore } from 'vuex';
import { ElMessage } from 'element-plus';
import * as baseData from '/src/api/baseData';
import { useRoute } from 'vue-router';
import { ElMessageBox } from 'element-plus'
import Pagination from '@components/tables/Pagination.vue';
import {insertOrUpdate, updatePassword} from "/src/api/baseData";

const dialogVisible = reactive({
  isShowEdit:false,
  isShowAdd:false
})
//所有的数据存放
const allData=reactive({
  searchDate:null,
  tableData:[],
  editData:{},
  addData:{},
  otherData:{
    produceAddress:""
  }
})

function getPara(index,row){
  if(row.userType == '1'){return "生产商"}
  if(row.userType == '2'){return "加工商"}
  if(row.userType == '3'){return "运输商"}
  if(row.userType == '4'){return "仓储商"}
  if(row.userType == '5'){return "销售商"}
  return "";
}

function getProduceAddress(index){
  if(index != undefined && index != null && index != "" ){
    return "https://map.baidu.com/search/"+index+"/@12058530.39363388,3521654.274999952,7.35z?querytype=s&wd="+index+"&c=315&provider=pc-aladin&pn=0&device_ratio=1&da_src=shareurl"
  }
  return "https://www.amap.com/"
}

// 状态管理
const store = useStore();

// 页面配置
const page = reactive({
  total:0,
  pageSize:10,
  currentPage:1,
  iconName: 'cascades', // 页面icon名字
  pageName: '种植商', // 页面名字
});

/**
 * 页面改变(子组件传值)
 */
function pageIndex(res) {
  page.currentPage = res;
  // props.getData();
}
/**
 * 获取展示数据
 */
function getData() {
  baseData
    .searchAllUser({})
    .then((res) => {
      console.log(res)
      allData.tableData = res.data.data
      console.log(allData.tableData)
      page.total = res.data.total
    })
    .catch(() => {
      // ElMessage.error('加载出现小bug!');
    });
}

// 转换时间戳，获取年月日-时分秒
function getTime(index,row,name) {


  let timestamp= row[name];
  if(timestamp == undefined || timestamp == null) return "";
  var date = new Date(Number(timestamp));//时间戳为10位需*1000，时间戳为13位的话不需乘1000
  var Y = date.getFullYear() + '-';
  var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
  var D = (date.getDate() < 10 ? '0'+date.getDate() : date.getDate()) + ' ';
  var h = (date.getHours() < 10 ? '0'+date.getHours() : date.getHours()) + ':';
  var m = (date.getMinutes() < 10 ? '0'+date.getMinutes() : date.getMinutes()) + ':';
  var s = (date.getSeconds() < 10 ? '0'+date.getSeconds() : date.getSeconds());

  var strDate = Y+M+D+h+m+s;
  return strDate;//2020-07-30 01:05:54

}

//打开添加弹窗
function openAdd(index,row){
  allData.addData = {}
  dialogVisible.isShowAdd = true
}
//打开编辑弹窗
function openEdit(index,row){
  Object.keys(row).forEach((item) => {
          console.log(item,row[item])
            allData.editData[item] = row[item]
          });
  dialogVisible.isShowEdit = true
}
//编辑处理
function editData(){
  let key = allData.editData.userId;
  baseData
      .updatePassword({userId:allData.editData.userId,password:allData.editData.password})
      .then((res) => {
        debugger
        getData();

        if(res.data.result){
          ElMessage.success("修改成功")
        }else {
          ElMessage.error("修改失败")
        }
      })
      .catch(() => {
        // ElMessage.error('添加失败!');
      });
  dialogVisible.isShowEdit = false
  getData();
}
//删除处理
function deleteData(index,row){
  ElMessageBox.confirm(
      '是否删除',
      '提示',
      {
        confirmButtonText: '是',
        cancelButtonText: '否',
        type: 'warning',
      }
  )
      .then( ()=> {
        let key = row.userId;
        debugger
            baseData
                .deleteUser({userId:key})
                .then((res) => {
                  getData();
                  if (res.data.result) {
                    ElMessage.success("删除成功")
                  } else {
                    ElMessage.error("删除失败")
                  }
                })
                .catch(() => {
                  ElMessage.error('删除失败!');
                }),
                baseData
                    .viewData({userId:localStorage.getItem("userId"),userType:localStorage.getItem("userType")})
                    .then((res) => {
                      console.log(res)
                      allData.tableData = res.data.data
                      console.log(allData.tableData)
                      page.total = res.data.total
                    })
                    .catch(() => {
                      // ElMessage.error('加载出现小bug!');
                    });
          }
      )
  getData()
}


//搜素处理
function handleSearch(){

}
// 页面加载后调用函数
onMounted(() => {
  getData();
});

//弹窗关闭提醒
const handleClose = () => {
  ElMessageBox.confirm('确定关闭?')
      .then(() => {
        dialogVisible.isShowEdit = false
        dialogVisible.isShowAdd = false

        // done()
      })
      .catch(() => {
        // catch error
      })
}

// 路由
const route = useRoute();
watch(
    () => route.path,
    (oldValue, newValue) => {
    }
);



</script>

<style scoped>
.table {
  width: 100%;
  font-size: 14px;
  max-height: 578px;
}

.red {
  color: #ff0000;
}

.table-td-thumb {
  display: block;
  margin: auto;
  width: 40px;
  height: 40px;
}

.handle-box {
  margin-bottom: 20px;
}

.handle-input {
  width: 180px;
  display: inline-block;
}

.mr10 {
  margin-right: 10px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.float-right {
  float: right;
}
</style>
