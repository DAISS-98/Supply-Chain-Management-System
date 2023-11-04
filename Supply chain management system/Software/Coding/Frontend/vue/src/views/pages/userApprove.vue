<template>
  <base-table>
  <div class="container">
    <!-- 搜索、清除搜索、多选删除、添加 -->
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
      <el-table-column prop="userName" label="用户名" width="200" />
      <el-table-column prop="account" label="账号" width="200" />
      <el-table-column prop="userFlagName" label="账户职位" width="200" />
      <el-table-column prop="canUse" label="状态" width="200" />
      <el-table-column  label="操作" width="230">
        <template #default="scope">
          <el-button type="text" size="small" @click="approveUser(scope.$index, scope.row,'2')"
          >审批通过</el-button>
          <el-button type="text" size="small" @click="approveUser(scope.$index, scope.row,'0')"
          >审批不通过</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin-top: 10px;text-align: right">
      <pagination :page-size="page.pageSize" :page-total="page.total"
                  :disabled="false" @page-index="pageIndex" />
    </div>


<!--    <template>-->
<!--      <span >-->
<!--        <el-button @click="dialogVisible.isShowAdd = false">取消</el-button>-->
<!--        <el-button type="primary" @click="addData">确认</el-button>-->
<!--      </span>-->
<!--    </template>-->
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
import UploadFile from '@components/componentUtil/UploadFile.vue';

const dialogVisible = reactive({
  isShowEdit:false,
  isShowAdd:false
})
//所有的数据存放
const allData=reactive({
  form:{},
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
  pageSize:10,
  currentPage:1,
  iconName: 'cascades', // 页面icon名字
  pageName: '种植商', // 页面名字
});

function getOtherTestGuid(res){

  allData.form.otherTestGuid=res
}

function getSelfTestGuid(res){
  allData.form.selfTestGuid=res
}
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
    .userApproveList()
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


// function approveOk(index,row){
//   ElMessageBox.confirm(
//       '是否确认',
//       '提示',
//       {
//         confirmButtonText: '是',
//         cancelButtonText: '否',
//         type: 'warning',
//       }
//   )
//   console.log(row)
// }

function approveUser(index,row,canuse){
  let userId= row.userId;
  // ElMessageBox.confirm(
  //     '是否确认',
  //     '提示',
  //     {
  //       confirmButtonText: '是',
  //       cancelButtonText: '否',
  //       type: 'warning',
  //     }
  // ).then(
  //     baseData.userApprove({
  //       userId:userId,
  //       canUse:canuse
  //     })
  // )


  // ElMessageBox.confirm(
  //     'proxy will permanently delete the file. Continue?',
  //     'Warning',
  //     {
  //       confirmButtonText: 'OK',
  //       cancelButtonText: 'Cancel',
  //       type: 'warning',
  //     }
  // )
  //     .then(() => {
  //       ElMessage({
  //         type: 'success',
  //         message: 'Delete completed',
  //       })
  //     })
  //     .catch(() => {
  //       ElMessage({
  //         type: 'info',
  //         message: 'Delete canceled',
  //       })
  //     })

  ElMessageBox.confirm(
      '是否确认?',
      '提示',
      {
        confirmButtonText: '是',
        cancelButtonText: '否',
        type: 'warning',
      }
  )
      .then(
          () => {
            baseData.userApprove({
              userId: userId,
              canUse: canuse
            })
            ElMessage({
                      type: 'success',
                      message: '操作成功',
                    })
            getData()
          }
      )
      .catch(() => {
        ElMessage({
          type: 'info',
          message: '已取消',
        })
      })

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
      .editData({...allData.editData})
      .then((res) => {
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
  allData.form.userId = localStorage.getItem("userId")
  let info = {
    ...allData.form
  }
  console.log("-----------------")
  console.log(allData.form)
  console.log("-----------------")
  debugger
  baseData
      .addBreedData(info)
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
.login-btn1 {
  text-align: center;
}
.login-btn1 button {
  width: 10%;
  height: 36px;
  margin-bottom: 10px;
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
  border-right: solid 1px var(--el-border-color);
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
