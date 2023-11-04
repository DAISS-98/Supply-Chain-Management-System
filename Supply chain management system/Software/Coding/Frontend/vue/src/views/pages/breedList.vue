<template>
  <base-table>
  <div class="container">
    <div class="handle-box">
      <el-row>
        <el-col :span="16">
        </el-col>
      </el-row>
    </div>
<!--数据展示-->
    <el-table
        :data="allData.tableData.slice((page.currentPage-1)*page.pageSize,page.currentPage*page.pageSize)"
        border
        style="width: 100%">
      <el-table-column prop="breedOnlyKey" label="溯源码" width="180" />
      <el-table-column prop="productName" label="产品名称" width="160" />
      <el-table-column prop="productNum" label="产品数量" width="160" />
      <el-table-column prop="unit" label="规格单位" width="130" />
      <el-table-column prop="originAddress" label="产地" width="130" />
      <el-table-column prop="productNo" label="产品编码" width="130" />
      <el-table-column prop="breedTime" label="种养殖时间" width="130" />
      <el-table-column prop="medicineName" label="用药名称" width="130"/>
      <el-table-column prop="medicineManufactor" label="药物生产厂家" width="130"/>
      <el-table-column prop="medicineUseTime" label="药物使用时间" width="130"/>
      <el-table-column fixed="right" label="操作" width="150">
        <template #default="scope">
          <el-button type="text" size="small" @click="openEdit(scope.$index, scope.row)"
          >查看编辑</el-button>
          <el-button type="text" size="small" @click="deleteData(scope.$index, scope.row)"
          >删除</el-button>
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
import * as baseData from '@api/baseData';
import { useRoute } from 'vue-router';
import { useRouter } from 'vue-router';
import { ElMessageBox } from 'element-plus'
import Pagination from '@components/tables/Pagination.vue';

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
})

// 状态管理
const store = useStore();
//路由
const router = useRouter();

// 页面配置
const page = reactive({
  total:0,
  pageSize:7,
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
function timestampToTime(timestamp) {
  var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
  var Y = date.getFullYear() + '-';
  var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
  var D = date.getDate() + ' ';
  return Y+M+D;
}
/**
 * 获取展示数据
 */
function getData() {
  baseData
    .viewBreedList({userId:localStorage.getItem("userId")})
    .then((res) => {
      allData.tableData = res.data.data
      for (let index in allData.tableData){
        debugger
        allData.tableData[index].breedTime = (allData.tableData[index].breedTime != null)?timestampToTime(allData.tableData[index].breedTime):null
        allData.tableData[index].medicineUseTime = (allData.tableData[index].medicineUseTime != null)?timestampToTime(allData.tableData[index].medicineUseTime):null
        allData.tableData[index].feedDate = (allData.tableData[index].feedDate != null)?timestampToTime(allData.tableData[index].feedDate):null
        allData.tableData[index].marketTime = (allData.tableData[index].marketTime != null)?timestampToTime(allData.tableData[index].marketTime):null
        allData.tableData[index].selfTestTime = (allData.tableData[index].selfTestTime != null)?timestampToTime(allData.tableData[index].selfTestTime):null
        allData.tableData[index].otherTestTime = (allData.tableData[index].otherTestTime != null)?timestampToTime(allData.tableData[index].otherTestTime):null
      }
      page.total = res.data.total
    })
    .catch(() => {
      // ElMessage.error('加载出现小bug!');
    });
}

//打开添加弹窗
function openAdd(index,row){
  allData.addData = {}
  dialogVisible.isShowAdd = true
}
//打开编辑弹窗
function openEdit(index,row){
  // router.push({ name: 'user', params: { userId: 123 }})
  router.push({
    path:'/breedEdit',
    query: {key: row.breedOnlyKey}
  })
}
//编辑处理
function editData(){
  baseData
      .editData({...allData.editData,userId:localStorage.getItem("userId"),userFlag:localStorage.getItem("userFlag")})
      .then((res) => {
        this.getData();
        if(res.data.result){
          ElMessage.success("编辑成功")
        }else {
          ElMessage.error("编辑失败")
        }
      })
      .catch(() => {
        // ElMessage.error('编辑失败!');
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
            baseData
                .deleteBreed(row)
                .then((res) => {
                  debugger
                  if (res.data.result) {
                    ElMessage.success("删除成功")
                  } else {
                    ElMessage.error("删除失败")
                  }
                  getData()

                })
                .catch(() => {
                  ElMessage.error('删除失败!');
                })
          }
      )
  getData()
}
//添加处理
function addData(){
  baseData
      .addData({...allData.addData,userId:localStorage.getItem("userId"),userFlag:localStorage.getItem("userFlag")})
      .then((res) => {
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
