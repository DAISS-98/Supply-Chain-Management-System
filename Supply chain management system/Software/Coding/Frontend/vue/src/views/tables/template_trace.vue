<template>
  <base-table>
    <div id="div01" align="center">

      <span style="color:#409EFE; font-size: 30px">客户端农产品溯源页面</span><br>
<!--      <input v-model="id"><button  v-on:click="restful" style="color:#121502;">查询</button><br>-->
<!--      <textarea   v-model="content"  style="width: 60%;height: 300px; font-size: larger"  > </textarea>-->

    </div>
  <div class="container">
    <!-- 搜索、清除搜索、多选删除、添加 -->
    <div class="handle-box">
      <el-row>
        <el-col :span="22">
          <!-- 搜索框 -->
          <el-input v-model="allData.searchData"  placeholder="请输入溯源码"
                    class="grid-content handle-input mr10"  />

<!-- 搜索按钮 -->
          <el-button type="primary" :icon="Search"
                     @click="handleSearch">追溯</el-button>
        </el-col>
      </el-row>
    </div>

<!--数据展示-->
    <el-table
        :data="allData.tableData.slice((page.currentPage-1)*page.pageSize,page.currentPage*page.pageSize)"
        border
        style="width: 100%">
      <el-table-column prop="tid" label="Fabric交易序列号" width="180" />
      <el-table-column prop="ttime" label="交易时间" width="180" />
      <el-table-column prop="role" label="交易用户" width="180" />
      <el-table-column prop="planterName" label="农产品" width="130" />
      <el-table-column prop="temperature" label="温度" width="130" />
      <el-table-column prop="envHumidity" label="环境湿度" width="130" />
      <el-table-column prop="ph" label="ph值" width="130" />
      <el-table-column prop="light" label="光照强度" width="130" />
      <el-table-column prop="soilHumidity" label="土壤湿度" width="130"/>
      <el-table-column prop="factoryName" label="加工厂名称" width="130"/>
      <el-table-column prop="qualityInspector" label="质检人员" width="130"/>
      <el-table-column prop="processPerson" label="加工人员" width="130"/>
      <el-table-column prop="date" label="加工时间" width="130"/>
      <el-table-column prop="location" label="仓储地址" width="130"/>
      <el-table-column prop="inTime" label="进入时间" width="130"/>
      <el-table-column prop="outTime" label="离开时间" width="130"/>
      <el-table-column prop="transportPerson" label="运输人" width="130"/>
      <el-table-column prop="leaveAddress" label="出发地" width="130"/>
      <el-table-column prop="arriveAddress" label="目的地" width="130"/>
      <el-table-column prop="mallName" label="商场名称" width="130"/>
      <el-table-column prop="price" label="商品价格(元)" width="130"/>
      <el-table-column prop="saleTime" label="上架时间" width="130"/>
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
import baseData from '@api/baseData';
import { useRoute } from 'vue-router';
import { ElMessageBox } from 'element-plus'
import Pagination from '@components/tables/Pagination.vue';

const dialogVisible = reactive({
  isShowEdit:false,
  isShowAdd:false
})
//所有的数据存放
const allData=reactive({
  searchData:null,
  tableData:[],
  editData:{},
  addData:{},
})

// 状态管理
const store = useStore();

// 页面配置
const page = reactive({
  total:0,
  pageSize:5,
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
    .viewData({userId:localStorage.getItem("userId"),userFlag:localStorage.getItem("userFlag")})
    .then((res) => {
      console.log(res)
      allData.tableData = res.data.data
      console.log(allData.tableData)
      page.total = res.data.total
    })
    .catch(() => {
      ElMessage.error('加载出现小bug!');
    });
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

//搜素处理
function handleSearch(){
  if(allData.searchData == "" || allData.searchData == null || allData.searchData == undefined ){
    ElMessage.error("请输入溯源码")
    return false;
  }
            baseData
                .queryAll({saleOnlyKey:allData.searchData})
                .then((res) => {
                  console.log(res)
                  allData.tableData = res.data.data
                  page.total = res.data.total
                })
                .catch(() => {
                })

}

// 页面加载后调用函数
onMounted(() => {
  // getData();
});

//弹窗关闭提醒
const handleClose = () => {
  ElMessageBox.confirm('确定关闭?')
      .then(() => {
        dialogVisible.isShowEdit = false
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
  width: 400px;
}

.float-right {
  float: right;
}
.demo-date-picker {
  display: flex;
  width: 100%;
  padding: 0;
  flex-wrap: wrap;
}
.demo-date-picker .block {
  padding: 30px 0;
  text-align: center;
  border-right: solid 1px var(--el-border-color-base);
  flex: 1;
}
.demo-date-picker .block:last-child {
  border-right: none;
}
.demo-date-picker .demonstration {
  display: block;
  color: var(--el-text-color-secondary);
  font-size: 14px;
  margin-bottom: 20px;
}
</style>
