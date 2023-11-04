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
<!--  数据展示  -->
    <h2 style="color: #999999">产品信息</h2>
    <el-divider />
    <el-form status-icon label-width="100px" ref="formRef" :model="allData.form" >
      <el-form-item label="产品名称" prop="productName">
        <el-input v-model="allData.form.productName" placeholder="请输入产品名称"  />
      </el-form-item>
      <el-form-item label="产品数量" prop="productNum">
        <el-input v-model="allData.form.productNum" placeholder="请输入产品数量" maxlength="20"  />
      </el-form-item>
      <el-form-item label="规格单位" prop="unit">
        <el-input v-model="allData.form.unit" placeholder="请输入规格单位" maxlength="50"  />
      </el-form-item>
      <el-form-item label="产地" prop="originAddress">
        <el-input v-model="allData.form.originAddress"  placeholder="请输入产地" maxlength="50" />
      </el-form-item>
      <el-form-item label="产品编码" prop="productNo">
        <el-input v-model="allData.form.productNo"  placeholder="请输入产品编码" maxlength="50" />
      </el-form-item>
    </el-form>
    <h2 style="color: #999999">种养殖过程</h2>
    <el-divider />
    <el-form status-icon label-width="100px" ref="formRef" :model="allData.form" >
      <el-form-item label="种养殖时间" prop="breedTime">
<!--        <el-input v-model="allData.form.breedTime" placeholder="请输入种养殖时间"  />-->
        <el-date-picker
            v-model="allData.form.breedTime"
            type="date"
            placeholder="Pick a date"
            format="YYYY/MM/DD"
            value-format="YYYY-MM-DD"
        />
      </el-form-item>
      <el-form-item label="用药名称" prop="peculiarIllness">
        <el-input v-model="allData.form.medicineName" placeholder="请输入用药名称" maxlength="20"  />
      </el-form-item>
      <el-form-item label="药物生产厂家" prop="medicineManufactor">
        <el-input v-model="allData.form.medicineManufactor" placeholder="请输入药物生产厂家" maxlength="50"  />
      </el-form-item>
      <el-form-item label="药物批号" prop="medicineNo">
        <el-input v-model="allData.form.medicineNo"  placeholder="请输入药物批号" maxlength="50" />
      </el-form-item>
      <el-form-item label="药物使用时间" prop="medicineUseTime">
<!--        <el-input v-model="allData.form.medicineUseTime"  placeholder="请输入使用时间" maxlength="50" />-->
          <el-date-picker
              v-model="allData.form.medicineUseTime"
              type="date"
              placeholder="Pick a date"
              format="YYYY/MM/DD"
              value-format="YYYY-MM-DD"
          />
      </el-form-item>
      <el-form-item label="药物使用量" prop="medicineUnit">
        <el-input v-model="allData.form.medicineUnit"  placeholder="请输入药物使用量" maxlength="50" />
      </el-form-item>
      <el-form-item label="肥料饲料名称" prop="feedName">
        <el-input v-model="allData.form.feedName"  placeholder="请输入肥料饲料名称" maxlength="50" />
      </el-form-item>
      <el-form-item label="肥料饲料厂家" prop="feedManufactor">
        <el-input v-model="allData.form.feedManufactor"  placeholder="请输入肥料饲料厂家" maxlength="50" />
      </el-form-item>
      <el-form-item label="肥料饲料批号" prop="feedNo">
        <el-input v-model="allData.form.feedNo"  placeholder="请输入肥料饲料批号" maxlength="50" />
      </el-form-item>
      <el-form-item label="肥料饲料使用时间" prop="feedDate">
        <el-date-picker
            v-model="allData.form.feedDate"
            type="date"
            placeholder="Pick a date"
            format="YYYY/MM/DD"
            value-format="YYYY-MM-DD"
        />
      </el-form-item>
      <el-form-item label="上市时间" prop="marketTime">
<!--        <el-input v-model="allData.form.marketTime"  placeholder="请输入上市时间" maxlength="50" />-->
        <el-date-picker
            v-model="allData.form.marketTime"
            type="date"
            placeholder="Pick a date"
            format="YYYY/MM/DD"
            value-format="YYYY-MM-DD"
        />
      </el-form-item>

    </el-form>

    <h2 style="color: #999999">自行检测</h2>
    <el-divider />
    <el-form status-icon label-width="100px" ref="formRef" :model="allData.form" >
      <el-form-item label="检测时间" prop="selfTestTime">
        <el-date-picker
            v-model="allData.form.selfTestTime"
            type="date"
            placeholder="Pick a date"
            format="YYYY/MM/DD"
            value-format="YYYY-MM-DD"
        />
      </el-form-item>
      <el-form-item label="检测项目" prop="selfTestProject">
        <el-input v-model="allData.form.selfTestProject"  placeholder="请输入检测项目" maxlength="50" />
      </el-form-item>
      <el-form-item label="检测类型" prop="selfTestType">
        <el-input v-model="allData.form.selfTestType"  placeholder="请输入检测类型" maxlength="50" />
      </el-form-item>
      <el-form-item label="检测结果" prop="selfTestResult">
        <el-input v-model="allData.form.selfTestResult"  placeholder="请输入检测结果" maxlength="50" />
      </el-form-item>
      <el-form-item v-if="allData.form.selfTestGuid != undefined" label="报告上传" prop="selfTestGuid">
        <upload-file @getGuid="getSelfTestGuid"  :fileGuid="allData.form.selfTestGuid">
        </upload-file>
      </el-form-item>
    </el-form>
    <h2 style="color: #999999">委托检测</h2>
    <el-divider />
    <el-form status-icon label-width="100px" ref="formRef" :model="allData.form" >
      <el-form-item label="检测时间" prop="otherTestTime">
        <el-date-picker
          v-model="allData.form.otherTestTime"
          type="date"
          placeholder="Pick a date"
          format="YYYY/MM/DD"
          value-format="YYYY-MM-DD"
      />
      </el-form-item>
      <el-form-item label="检测项目" prop="otherTestProject">
        <el-input v-model="allData.form.otherTestProject"  placeholder="请输入检测项目" maxlength="50" />
      </el-form-item>
      <el-form-item label="检测类型" prop="otherTestType">
        <el-input v-model="allData.form.otherTestType"  placeholder="请输入检测类型" maxlength="50" />
      </el-form-item>
      <el-form-item label="检测结果" prop="otherTestResult">
        <el-input v-model="allData.form.otherTestResult"  placeholder="请输入检测结果" maxlength="50" />
      </el-form-item>
      <el-form-item v-if="allData.form.otherTestGuid != undefined" label="报告上传" prop="otherTestGuid">
        <upload-file @getGuid="getOtherTestGuid" :fileGuid="allData.form.otherTestGuid">
        </upload-file>
      </el-form-item>
    </el-form>

    <div style="height: 10px"></div>
    <div class="login-btn1">
      <el-row>
        <el-col>
          <el-button type="primary" @click="editData">确定修改</el-button>
          <el-button type="primary" @click="back">返回</el-button>
        </el-col>
      </el-row>
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
import { useRouter } from 'vue-router';
import { ElMessageBox } from 'element-plus'
import Pagination from '@components/tables/Pagination.vue';
import UploadFile from '@components/componentUtil/UploadFile.vue';
//路由
const router = useRouter();
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
  if(res == null || res == ''){
    allData.form.otherTestGuid="1"
  }else {
    allData.form.otherTestGuid=res
  }
}

function getSelfTestGuid(res){
  if(res == null || res == ''){
    allData.form.selfTestGuid="1"
  }else {
    allData.form.selfTestGuid=res
  }
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

function timestampToTime(timestamp) {
  var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
  var Y = date.getFullYear() + '-';
  var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
  var D = date.getDate() + ' ';
  return Y+M+D;
}
function getData() {
  let key =  ref(route.query.key);
  baseData
      .viewBreedData({breedOnlyKey:key.value})
      .then((res) => {
        allData.form = res.data.data[0]
        console.log(allData.form)
        allData.form.selfTestGuid = (allData.form.selfTestGuid != null)?allData.form.selfTestGuid:1
        allData.form.otherTestGuid = (allData.form.otherTestGuid != null)?allData.form.otherTestGuid:1
        allData.form.breedTime = (allData.form.breedTime != null)?timestampToTime(allData.form.breedTime):null
        allData.form.medicineUseTime = (allData.form.medicineUseTime != null)?timestampToTime(allData.form.medicineUseTime):null
        allData.form.feedDate = (allData.form.feedDate != null)?timestampToTime(allData.form.feedDate):null
        allData.form.marketTime = (allData.form.marketTime != null)?timestampToTime(allData.form.marketTime):null
        allData.form.selfTestTime = (allData.form.selfTestTime != null)?timestampToTime(allData.form.selfTestTime):null
        allData.form.otherTestTime = (allData.form.otherTestTime != null)?timestampToTime(allData.form.otherTestTime):null
      })
      .catch(() => {
        // ElMessage.error('加载出现小bug!');
      });
}

function back(){
  // router.push('/breedList')
  router.go(-1);
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
      .editBreedData({...allData.form})
      .then((res) => {
        if(res.data.result){
          ElMessage.success("编辑成功")
          allData.form={}
        }else {
          ElMessage.error("编辑失败")
        }
      })
      .catch(() => {
        // ElMessage.error('编辑失败!');
      });
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
  baseData
      .addBreedData(info)
      .then((res) => {
        if(res.data.result){
          ElMessage.success("修改成功")
          back
        }else {
          ElMessage.error("修改失败")
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
    },
    () => allData.form.breedTime,

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
