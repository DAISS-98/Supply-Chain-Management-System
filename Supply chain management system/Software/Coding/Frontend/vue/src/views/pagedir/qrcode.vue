<template>
  <base-table>
    <div class="container">
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
    <el-table-column fixed="right" label="操作" width="150">
      <template #default="scope">
        <el-button type="text" size="small" @click="detailMakeQRcode(scope.$index, scope.row)"
        >二维码生成</el-button>
      </template>
    </el-table-column>
  </el-table>
  <div style="margin-top: 10px;text-align: right">
    <pagination :page-size="page.pageSize" :page-total="page.total"
                :disabled="false" @page-index="pageIndex" />
  </div>
<!--      <template >-->

  <div  class="div"  v-if="text != undefined && text.value != '' && isShow ">

<!--      <button @click="makeQRcode()"></button>-->
<!--      <br/>-->
    <span class="spanx"></span>
    <br/>
<!--    <el-divider />-->
    <img :src="qrImage" class="qqq" /><br/>
    <el-button type="text"  size="xlarge" @click="downLoadCode()">下载</el-button>
    <el-button type="text"  size="xlarge" @click="close()">不显示</el-button>

  </div>

<!--      </template>-->
    </div>

  </base-table>
</template>

<script setup>
import { reactive, onMounted  ,watch, ref} from 'vue';
import { useStore } from 'vuex';
import { ElMessage } from 'element-plus';
import { useRoute } from 'vue-router';
import { ElMessageBox } from 'element-plus';
import QRCode from 'qrcode';
import * as baseData from '/src/api/baseData';


import Pagination from '@components/tables/Pagination.vue';

// 路由
const route = useRoute();
// const text = ref('xxx')
// const text = ref(route.query.id);
const text = ref("http://www.bilibili.com");
const qrImage = ref('')
const isShow = ref(false)//二维码是否显示

async function makeQRcode(key) {
  console.log("http://"+allData.ip+":3000/#/trace?key="+key)
  isShow.value = true
  qrImage.value = await QRCode.toDataURL("http://"+allData.ip+":3000/#/trace?key="+key+"&userType="+localStorage.getItem("userType"))
}

//获取用户本地ip的方法
function getIp(){
  baseData
      .getIp()
      .then((res) => {
        console.log(res)
        allData.ip = res.data.data.ip
      })
}

//明细生成二维码
function detailMakeQRcode(index,row){
  makeQRcode(row.produceKey);
}

const dialogVisible = reactive({
  isShowEdit:false,
  isShowAdd:false
})

const logoSrc=reactive("");
const appSrc=reactive("SDSDSD");
//所有的数据存放
const allData=reactive({
  searchData:null,
  tableData:[],
  editData:{},
  addData:{},
  routerParam:"",
  ip:null
})

// 状态管理
const store = useStore();

// 页面配置
const page = reactive({
  total:0,
  pageSize:5,
  currentPage:1,
  iconName: 'cascades',
  pageName: '种植商',
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
    .selectAllList()
    .then((res) => {
      console.log(res)
      allData.tableData = res.data.data
      console.log(allData.tableData)
      page.total = res.data.total
      for (let index in allData.tableData) {
        allData.tableData[index].breedTime = (allData.tableData[index].breedTime != null) ? timestampToTime(allData.tableData[index].breedTime) : null
        allData.tableData[index].medicineUseTime = (allData.tableData[index].medicineUseTime != null) ? timestampToTime(allData.tableData[index].medicineUseTime) : null
        allData.tableData[index].feedDate = (allData.tableData[index].feedDate != null) ? timestampToTime(allData.tableData[index].feedDate) : null
        allData.tableData[index].marketTime = (allData.tableData[index].marketTime != null) ? timestampToTime(allData.tableData[index].marketTime) : null
        allData.tableData[index].selfTestTime = (allData.tableData[index].selfTestTime != null) ? timestampToTime(allData.tableData[index].selfTestTime) : null
        allData.tableData[index].otherTestTime = (allData.tableData[index].otherTestTime != null) ? timestampToTime(allData.tableData[index].otherTestTime) : null
        allData.tableData[index].saleTime = (allData.tableData[index].saleTime != null) ? timestampToTime(allData.tableData[index].saleTime) : null
        allData.tableData[index].saleTestTime = (allData.tableData[index].saleTestTime != null) ? timestampToTime(allData.tableData[index].saleTestTime) : null
      }
    })
    .catch(() => {
      ElMessage.error('加载出现小bug!');
    });
}

function timestampToTime(timestamp) {
  var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
  var Y = date.getFullYear() + '-';
  var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
  var D = date.getDate() + ' ';
  return Y+M+D;
}
// 下载二维码
function downLoadCode(){
  var alink = document.createElement("a");
  alink.href = qrImage.value;
  alink.download = "二维码.png";
  alink.click();
}
//不显示
function close(){
  isShow.value = false //不展示
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
  // console.log("router")
  // console.log(route.query)
  // console.log(route.params)
  // this.text = router;
    getData();
  getIp();

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

.span{
  color: #00a854;
  font-size: x-large;
}
.spanx{
  color: #3EA0FC;
  font-size: large;
  text-align: left;
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
.qqq{
  background-color: #111;
  width:200px;
  height:200px;
  margin: 0 auto; /*水平居中*/
  position: relative;
}

.div{

  text-align: center;
  position: center;
}

.qrCode {

  display: inline-block;
  margin: 0 auto; /*水平居中*/
  position: relative;
  top: 15%;
}
  .img {
    width: 200px;
    height: 200px;
    background-color: #fff;
     padding: 6px;

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
