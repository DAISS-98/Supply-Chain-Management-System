<template>
  <base-table>
    <div id="div01" align="center">

<!--      <span style="color:#409EFE; font-size: 30px">客户端农产品溯源页面</span><br>-->
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
<!--添加按钮-->
        </el-col>
      </el-row>
    </div>

<!--编辑弹窗-->
    <el-dialog
        v-model="dialogVisible.isShowEdit"
        title="编辑信息"
        width="30%"
        :before-close="handleClose"
    >
            <el-form status-icon label-width="100px" ref="formRef" :model="allData.editData" >
              <el-form-item label="商场名称" prop="mallName">
                <el-input v-model="allData.editData.mallName" placeholder="请输入商场名称"  />
              </el-form-item>
              <el-form-item label="商品价格" prop="price">
                <el-input v-model="allData.editData.price" placeholder="请输入商品价格(元)"   />
              </el-form-item>
              <el-form-item label="上架时间" prop="saleTime">
                <el-date-picker  v-model="allData.editData.saleTime" type="date" placeholder="Pick a day"  value-format="YYYY-MM-DD h:m:s a">
                </el-date-picker>
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
      <el-table-column prop="breedOnlyKey" label="溯源码" width="180" />
      <el-table-column prop="userName" label="操作用户" width="180" />
      <el-table-column prop="productName" label="产品名称" width="160" />
      <el-table-column prop="productNum" label="产品数量" width="160" />
      <el-table-column prop="unit" label="规格单位" width="130" />
      <el-table-column prop="originAddress" label="产地" width="130" />
      <el-table-column prop="productNo" label="产品编码" width="130" />
      <el-table-column prop="breedTime" label="种养殖时间" width="130" />
      <el-table-column prop="medicineName" label="用药名称" width="130"/>
      <el-table-column prop="medicineManufactor" label="药物生产厂家" width="130"/>
      <el-table-column prop="medicineUseTime" label="药物使用时间" width="130"/>
      <el-table-column prop="carNumber" label="车牌号" width="130"/>
      <el-table-column prop="startAddress" label="出发地" width="130"/>
      <el-table-column prop="endAddress" label="目的地" width="130"/>
      <el-table-column prop="transportTime" label="运输时间" width="130"/>
      <el-table-column prop="tranportUserName" label="司机姓名" width="130"/>
      <el-table-column prop="transportUserPhone" label="联系电话" width="130"/>
      <el-table-column prop="saleTime" label="收货时间" width="130"/>
      <el-table-column prop="saleTestTime" label="检测时间" width="130"/>
      <el-table-column prop="saleTestProject" label="检测项目" width="130"/>
      <el-table-column prop="saleTestResult" label="检测结果" width="130"/>
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
