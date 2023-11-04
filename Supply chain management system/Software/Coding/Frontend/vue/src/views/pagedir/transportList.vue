<template>
  <base-table>
  <div class="container">

<!--编辑弹窗-->
    <el-dialog
        v-model="dialogVisible.isShowEdit"
        title="编辑信息"
        width="30%"
        :before-close="handleClose"
    >
            <el-form status-icon label-width="100px" ref="formRef" :model="allData.editData" >
              <el-form-item label="运输人" prop="transportPerson">
                <el-input v-model="allData.editData.transportPerson" placeholder="请输入运输人"  />
              </el-form-item>
              <el-form-item label="出发地" prop="transportBeginAddress">
                <el-input v-model="allData.editData.transportBeginAddress" placeholder="请输入出发地"  />
                <el-link :href="getProduceAddress(allData.editData.transportBeginAddress)" target="_blank">检索详细地址</el-link>
              </el-form-item>
              <el-form-item label="出发时间" prop="transportBeginDate">
                <el-date-picker
                    v-model="allData.editData.transportBeginDate"
                    type="datetime"
                    placeholder="Select date and time"
                />
              </el-form-item>
              <el-form-item label="出发时新鲜度" prop="transportBeginFresh">
                <el-input v-model="allData.editData.transportBeginFresh" placeholder="请输入出发时新鲜度"  />
              </el-form-item>
              <el-form-item label="目的地" prop="transportEndAddress">
                <el-input v-model="allData.editData.transportEndAddress" placeholder="请输入目的地"  />
                <el-link :href="getProduceAddress(allData.editData.transportEndAddress)" target="_blank">检索详细地址</el-link>
              </el-form-item>
              <el-form-item label="到达时间" prop="transportEndDate">
                <el-date-picker
                    v-model="allData.editData.transportEndDate"
                    type="datetime"
                    placeholder="Select date and time"
                />
              </el-form-item>
              <el-form-item label="到达时新鲜度" prop="transportEndFresh">
                <el-input v-model="allData.editData.transportEndFresh" placeholder="请输入到达时新鲜度"  />
              </el-form-item>
              <el-form-item label="运输温度" prop="transportC">
                <el-input v-model="allData.editData.transportC" placeholder="请输入运输温度"  />
              </el-form-item>
              <el-form-item label="运输湿度(%)" prop="transportHumidity">
                <el-input v-model="allData.editData.transportHumidity" placeholder="请输入运输湿度(%)"  />
              </el-form-item>
              <el-form-item label="运输行为信息" prop="transportInfo">
                <el-input v-model="allData.editData.transportInfo" placeholder="请输入运输行为信息"  />
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
      <el-table-column prop="produceName" label="农产品名称" width="180" />
      <el-table-column prop="produceNum" label="批次号" width="160" />
      <el-table-column prop="produceType" label="品种" width="150" />
      <el-table-column   label="生产日期" width="160" >
        <template #default="scope">
          <text  >{{getTime(scope.$index, scope.row,"produceDate")}}</text>
        </template>
      </el-table-column>
      <el-table-column prop="produceAddress" label="生产地点" width="150" />
      <el-table-column prop="produceCycle" label="生产周期" width="150" />
      <el-table-column prop="producePerson" label="生产人员" width="150" />
      <el-table-column prop="produceSubName" label="生产商下级商家名称" width="150" />
      <el-table-column prop="produceSupplierName" label="生产商名称" width="150" />
      <el-table-column prop="processName" label="加工商名称"  width="150"/>
      <el-table-column   label="进货时间" width="160" >
        <template #default="scope">
          <text  >{{getTime(scope.$index, scope.row,"processGetDate")}}</text>
        </template>
      </el-table-column>
      <el-table-column   label="加工日期" width="160" >
        <template #default="scope">
          <text  >{{getTime(scope.$index, scope.row,"processTime")}}</text>
        </template>
      </el-table-column>
      <el-table-column prop="processAddress" label="加工地点" width="150" />
      <el-table-column prop="processPerson" label="加工人员" width="150"/>
<!--      <el-table-column prop="processFresh" label="新鲜度(%)" width="150"/>-->
<!--      <el-table-column prop="processC" label="环境温度" width="150"/>-->
<!--      <el-table-column prop="processHumidity" label="环境湿度(%)" width="150"/>-->
      <el-table-column prop="transportName" label="运输商名称" width="150"/>
      <el-table-column prop="transportPerson" label="运输人" width="150"/>
      <el-table-column prop="transportBeginAddress" label="出发地" width="150"/>
      <el-table-column   label="出发时间" width="160" >
        <template #default="scope">
          <text  >{{getTime(scope.$index, scope.row,"transportBeginDate")}}</text>
        </template>
      </el-table-column>
      <el-table-column prop="transportBeginFresh" label="出发时新鲜度(%)" width="150"/>
      <el-table-column prop="transportEndAddress" label="目的地" width="150"/>
      <el-table-column   label="到达时间" width="160" >
        <template #default="scope">
          <text  >{{getTime(scope.$index, scope.row,"transportEndDate")}}</text>
        </template>
      </el-table-column>
      <el-table-column prop="transportEndFresh" label="到达时新鲜度(%)" width="150"/>
      <el-table-column prop="transportC" label="运输温度" width="150"/>
      <el-table-column prop="transportHumidity" label="运输湿度" width="150"/>
      <el-table-column prop="transportInfo" label="运输行为信息" width="150"/>
      <el-table-column fixed="right" label="操作" width="150">
        <template #default="scope">
          <el-button type="text" size="small" @click="openEdit(scope.$index, scope.row)">编辑</el-button>
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
import {insertOrUpdate} from "/src/api/baseData";

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
  allData.editData = {}
  debugger
  Object.keys(row).forEach((item) => {
          console.log(item,row[item])
            allData.editData[item] = row[item]
          });
  dialogVisible.isShowEdit = true
}
//编辑处理
function editData(){
  let key = allData.editData.superKey;
  baseData
      .insertOrUpdate({userId:localStorage.getItem("userId"),key:key,supplierName: localStorage.getItem("userName") ,object:{...allData.editData}})
      .then((res) => {
        debugger
        getData();

        if(res.data.result){
          ElMessage.success("编辑成功")
        }else {
          ElMessage.error("编辑失败")
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
        let key = row.produceKey;
        debugger
            baseData
                .deleteData({key:key})
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

//添加处理
function addData(){
  baseData
      .insertOrUpdate({userId:localStorage.getItem("userId"),userType:localStorage.getItem("userType"),supplierName: localStorage.getItem("userName") ,object:{...allData.addData}})
      .then((res) => {
        debugger
        getData();

        if(res.data.result){
          ElMessage.success("添加成功")
        }else {
          ElMessage.error("添加失败")
        }
      })
      .catch(() => {
        // ElMessage.error('添加失败!');
      });
  dialogVisible.isShowAdd = false
  getData();
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
