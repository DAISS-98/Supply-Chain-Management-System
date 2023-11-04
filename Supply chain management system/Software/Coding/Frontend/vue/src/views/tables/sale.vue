<template>
  <base-table>
  <div class="container">
    <!-- 搜索、清除搜索、多选删除、添加 -->
    <div class="handle-box">
      <el-row>
        <el-col :span="16">
          <!-- 搜索框 -->
<!--          <el-input v-model="allData.searchContent"  placeholder="搜素内容"-->
<!--                    class="grid-content handle-input mr10" />-->


<!-- 搜索按钮 -->
<!--          <el-button type="primary" :icon="Search"-->
<!--                     @click="handleSearch">搜索</el-button>-->
<!--添加按钮-->
<!--          <el-button type="primary" :icon="Search"-->
<!--                     @click="openAdd">添加</el-button>-->
        </el-col>
      </el-row>
    </div>
<!--添加弹窗-->
<!--    <el-dialog-->
<!--        v-model="dialogVisible.isShowAdd"-->
<!--        title="添加信息"-->
<!--        width="30%"-->
<!--        :before-close="handleClose"-->
<!--    >-->
<!--      <el-form status-icon label-width="100px" ref="formRef" :model="allData.addData" >-->
<!--        <el-form-item label="加工厂名称" prop="factoryName">-->
<!--          <el-input v-model="allData.addData.factoryName" placeholder="请输入加工厂名称"  />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="质检人员" prop="qualityInspector">-->
<!--          <el-input v-model="allData.addData.qualityInspector" placeholder="请输入质检人员"   />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="加工人员" prop="processPerson">-->
<!--          <el-input v-model="allData.addData.processPerson" placeholder="请输入加工人员"   />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="加工时间" prop="date">-->
<!--          <el-input v-model="allData.addData.date"  placeholder="请输入加工时间"  />-->
<!--        </el-form-item>-->

<!--      </el-form>-->

<!--      <template #footer>-->
<!--      <span class="dialog-footer">-->
<!--        <el-button @click="dialogVisible.isShowAdd = false">取消</el-button>-->
<!--        <el-button type="primary" @click="addData">确认</el-button>-->
<!--      </span>-->
<!--      </template>-->
<!--    </el-dialog>-->
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
      <el-table-column prop="planterOnlyKey" label="溯源码" width="180" />
      <el-table-column prop="planterName" label="农产品名称" width="160" />
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
      <el-table-column fixed="right" label="操作" width="150">
        <template #default="scope">
          <el-button type="text" size="small" @click="openEdit(scope.$index, scope.row)"
          >编辑</el-button>
<!--          <el-button type="text" size="small" @click="deleteData(scope.$index, scope.row)">删除</el-button>-->
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
  searchDate:null,
  tableData:[],
  editData:{},
  addData:{},
})

// 状态管理
const store = useStore();

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
//编辑处理
function editData(){
  console.log(allData.editData)
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
                .deleteData(row)
                .then((res) => {
                  this.getData();
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
      )
  getData()
}
//添加处理
function addData(){
  baseData
      .addData({...allData.addData,userId:localStorage.getItem("userId"),userFlag:localStorage.getItem("userFlag")})
      .then((res) => {
        if(res.data.result){
          this.getData();
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
