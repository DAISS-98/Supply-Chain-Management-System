<template>
  <base-table>
    <div class="container">
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
    <!--      <el-table-column prop="transportBeginFresh" label="出发时新鲜度(%)" width="150"/>-->
    <el-table-column prop="transportEndAddress" label="目的地" width="150"/>
    <el-table-column   label="到达时间" width="160" >
      <template #default="scope">
        <text  >{{getTime(scope.$index, scope.row,"transportEndDate")}}</text>
      </template>
    </el-table-column>
    <!--      <el-table-column prop="transportEndFresh" label="到达时新鲜度(%)" width="150"/>-->
    <!--      <el-table-column prop="transportC" label="运输温度" width="150"/>-->
    <!--      <el-table-column prop="transportHumidity" label="运输湿度" width="150"/>-->
    <el-table-column prop="storeName" label="仓储商名称" width="150"/>
    <el-table-column prop="storeAddress" label="仓储地点" width="150"/>
    <el-table-column prop="storePerson" label="管理员" width="150"/>
    <el-table-column   label="入库时间" width="160" >
      <template #default="scope">
        <text  >{{getTime(scope.$index, scope.row,"storeIntime")}}</text>
      </template>
    </el-table-column>
    <!--      <el-table-column prop="storeIntime" label="入库时间" width="150"/>-->
    <el-table-column prop="storeFresh" label="入库新鲜度" width="150"/>
    <el-table-column prop="storeC" label="仓储温度" width="150"/>
    <el-table-column prop="storeInfo" label="仓储其他信息" width="150"/>
    <el-table-column prop="storeOther" label="其他" width="150"/>
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
