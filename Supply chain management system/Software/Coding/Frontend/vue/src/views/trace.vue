<template>
  <base-table>
    <div class="login-wrap">
    <div  align="center"     >

      <span style="color:#409EFE; font-size: 30px">农产品供应链溯源</span><br>
    </div>
  <div class="container1" >
    <!-- 搜索、清除搜索、多选删除、添加 -->
    <div v-if="allData.show">


    <span>生产记录</span>
    <el-divider></el-divider>
<!--数据展示-->
    <el-form status-icon label-width="100px"  :model="allData.breed" >
      <el-row>
        <el-col :span="12">
          <el-form-item label="农产品名称" prop="produceName" >
            <el-input v-model="allData.breed.produceName" disabled="true"   />
          </el-form-item>
        </el-col>
        <el-col :span="12">
        <el-form-item label="批次号" prop="produceNum">
          <el-input v-model="allData.breed.produceNum"  maxlength="20" disabled="true"  />
        </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="品种" prop="produceType">
            <el-input v-model="allData.breed.produceType"  maxlength="50" disabled="true"  />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="生产日期" prop="produceDate">
            <el-input v-model="allData.breed.produceDate" disabled="true"   maxlength="50" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="生产地点" prop="produceAddress">
            <el-input v-model="allData.breed.produceAddress" disabled="true"   maxlength="50" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="生产人员" prop="producePerson">
            <el-input v-model="allData.breed.producePerson" disabled="true"   maxlength="50" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="新鲜度(%)" prop="produceFresh">
            <el-input v-model="allData.breed.produceFresh"  disabled="true"  maxlength="20"  />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="环境温度(C)" prop="produceC">
            <el-input v-model="allData.breed.produceC"  disabled="true"  maxlength="50"  />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="环境湿度(%)" prop="produceHumidity">
            <el-input v-model="allData.breed.produceHumidity" disabled="true"   maxlength="50" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="生产周期" prop="produceCycle">
            <el-input v-model="allData.breed.produceCycle" disabled="true"   maxlength="50" />
          </el-form-item>
        </el-col>
      </el-row>




    <span>加工记录</span>
    <el-divider></el-divider>
      <el-row>
        <el-col :span="12">
          <el-form-item label="加工商名称" prop="processName">
            <el-input v-model="allData.breed.processName" disabled="true"  />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="进货时间" prop="processGetDate">
            <el-input v-model="allData.breed.processGetDate" disabled="true"   />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="加工地点" prop="processAddress">
            <el-input v-model="allData.breed.processAddress"  disabled="true"  />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="加工人员" prop="processPerson">
            <el-input v-model="allData.breed.processPerson"  disabled="true"  />
          </el-form-item>
        </el-col>
      </el-row>


    <span>运输记录</span>
    <el-divider></el-divider>
      <el-row>
        <el-col :span="12">
          <el-form-item label="出发地" prop="transportBeginAddress">
            <el-input v-model="allData.breed.transportBeginAddress" disabled="true"  />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="出发时间" prop="transportBeginDate">
            <el-input v-model="allData.breed.transportBeginDate" disabled="true"   />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="目的地" prop="transportEndAddress">
            <el-input v-model="allData.breed.transportEndAddress"  disabled="true"  />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="到达时间" prop="transportEndDate">
            <el-input v-model="allData.breed.transportEndDate"  disabled="true"  />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="运输温度(C)" prop="transportC">
            <el-input v-model="allData.breed.transportC"  disabled="true"  />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="运输湿度(%)" prop="transportHumidity">
            <el-input v-model="allData.breed.transportHumidity"  disabled="true"  />
          </el-form-item>
        </el-col>
      </el-row>

    <span>仓储记录</span>
    <el-divider></el-divider>
      <el-row>
        <el-col :span="12">
          <el-form-item label="仓储商名称" prop="storeName">
            <el-input v-model="allData.breed.storeName" disabled="true"  />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="仓储地点" prop="storeAddress">
            <el-input v-model="allData.breed.storeAddress" disabled="true"   />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="管理员" prop="storePerson">
            <el-input v-model="allData.breed.storePerson"  disabled="true"  />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="入库时间" prop="storeIntime">
            <el-input v-model="allData.breed.storeIntime"  disabled="true"  />
          </el-form-item>
        </el-col>
      </el-row>

    <span>销售记录</span>
    <el-divider></el-divider>
      <el-row>
        <el-col :span="12">
          <el-form-item label="经销商名称" prop="saleName">
            <el-input v-model="allData.breed.saleName" placeholder="请输入检测项目" disabled="true"  />
          </el-form-item>
        </el-col>
        <el-col :span="12">
            <el-form-item label="上架时间" prop="saleOnDate">
              <el-input v-model="allData.breed.saleOnDate" placeholder="请输入检测项目" disabled="true"  />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="销售员" prop="salePerson">
            <el-input v-model="allData.breed.salePerson" placeholder="请输入检测结果" disabled="true"   />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="销售单价（元/斤）" prop="salePrice">
              <el-input v-model="allData.breed.salePrice" placeholder="请输入检测结果" disabled="true"   />
          </el-form-item>
        </el-col>
      </el-row>
      <el-divider></el-divider>
<!--      <span>区块地址:</span><span>{{allData.hashCode}}</span>-->

    </el-form>
    </div>
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
  breed:{},
  transport:{},
  sale:{},
  show:false,
  hashCode:null
})


// 转换时间戳，获取年月日-时分秒
function getTime(name) {


  let timestamp= allData.breed[name];
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
// 状态管理
const store = useStore();
const userType =  route.query.userType;
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
  let key =  route.query.key;
  let userType =  route.query.userType;
  if(key == "" || key == null || key == undefined ){
    ElMessage.error("Error")
    return false;
  }
  if(userType == "" || userType == null || userType == undefined ){
    ElMessage.error("Error")
    return false;
  }
            baseData
                .searchByKey({key:key,userType:userType})
                .then((res) => {
                  console.log(res)
                  // debugger
                  // allData.tableData = res.data.data
                  if(res.data.result){
                    allData.breed = res.data.data[0];
                    allData.breed.produceDate = getTime('produceDate')
                    allData.breed.processGetDate = getTime('processGetDate')
                    allData.breed.transportBeginDate = getTime('transportBeginDate')
                    allData.breed.transportEndDate = getTime('transportEndDate')
                    allData.breed.storeIntime = getTime('storeIntime')
                    allData.breed.saleOnDate = getTime('saleOnDate')

                    page.total = res.data.total
                    allData.show = true;
                  }else {
                    allData.show = false;
                  }


                })
                .catch(() => {
                })


}



// 页面加载后调用函数
onMounted(() => {
  handleSearch();

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
.container1 {
  padding: 30px;
  background: #fff;
  border: 0px solid #ddd;
  border-radius: 5px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
  width: 400px;
}
.login-wrap {
  position: relative;
  width: 100%;
  height: 100%;
  /*background-image: url(../assets/img/login-bg.jpg);*/
  /*background-image: url(../assets/img/login05.jpg);*/
  background-size: 100%;
  background-color: white;
  overflow: auto;
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
