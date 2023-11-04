<template>
<!--  action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"-->
  <el-upload
      class="avatar-uploader"
       action="/restful/uploadFile"
      :show-file-list="false"
      :on-success="handleAvatarSuccess"
      :before-upload="beforeAvatarUpload"
  >
    <img v-if="imageUrl" :src="imageUrl" class="avatar" />
    <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
  </el-upload>
<!--  <div class="demo-image__placeholder" >-->
<!--    <div class="block">-->
<!--      <span class="demonstration">头像</span>-->
<!--      <el-image :src="src" />-->
<!--    </div>-->

<!--  </div>-->

  <el-upload
      v-model:file-list="fileList"
      class="upload-demo"
      action="/restful/uploadFile"
      multiple
      style="width: 300px"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :before-remove="beforeRemove"
      :limit="1"
      :on-exceed="handleExceed"
      :on-success="handleFileSuccess"

  >
    <el-button type="primary">Click to upload</el-button>
    <template #tip>
      <div class="el-upload__tip">
        doc files with a size less than 5M.
      </div>
    </template>
  </el-upload>

</template>

<script lang="ts" setup>

import { reactive, onMounted  ,watch, ref} from 'vue';
import { useStore } from 'vuex';
import { ElMessage } from 'element-plus';
import * as baseData from '/src/api/baseData';
import { useRoute } from 'vue-router';
import { ElMessageBox } from 'element-plus';
import * as http from '/src/utils/request'
import xxx from '/src/api/baseData'
import Pagination from '/src/components/tables/Pagination.vue';


// import { ref } from 'vue'
// import { ElMessage } from 'element-plus'
import { Plus } from '@element-plus/icons-vue'

import type { UploadProps } from 'element-plus'
import type {  UploadUserFile } from 'element-plus'
import axios from 'axios';

const imgGuid = ref('1')
const fileGuid = ref('55f16aee-f68d-40ec-a3f4-87107613e823')
//所有的数据存放
const allData=reactive({
  searchData:null,
  tableData:[],
  editData:{},
  addData:{},
  routerParam:"",
})


//图片上传BEGIN=========================================
const imageUrl = ref('')

const handleAvatarSuccess: UploadProps['onSuccess'] = (
    response,
    uploadFile
) => {
  imgGuid.value = response.data[0].guid;
  if(uploadFile.raw != null && uploadFile.raw != undefined){
    imageUrl.value = URL.createObjectURL(uploadFile.raw)
  }
}

const beforeAvatarUpload: UploadProps['beforeUpload'] = (rawFile) => {
  if (rawFile.type !== 'image/jpeg') {
    ElMessage.error('Avatar picture must be JPG format!')
    return false
  } else if (rawFile.size / 1024 / 1024 > 2) {
    ElMessage.error('Avatar picture size can not exceed 2MB!')
    return false
  }
  return true
}
const imgFileInfo = reactive({
  url: '/restful/downloadFile?guid='+imgGuid,
  name:"hello"
})

function handleImgDownload(file){  //图片下载  参数imgFile

  if(imgGuid.value != '1'){
    http.post(file.url,{},{responseType: 'blob'}).then(res=>{
      // let blob = new Blob([res.data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"})
      let blob = new Blob([res.data], {type: "application/octet-stream"})
      let fileName = file.name;
      if('msSaveOrOpenBlob' in navigator){
        //ie使用的下载方式
        window.navigator.msSaveOrOpenBlob(blob, fileName);
      }else{
        let link = document.createElement('a')
        // link.href = window.URL.createObjectURL(blob)
        link.href = URL.createObjectURL(blob)
        imageUrl.value = URL.createObjectURL(blob)
        // link.download = fileName;
        // link.click();
      }
    });
  }else {
    console.log("1");
  }
}
//图片上传END============================================




//文件BEGIN======================================
const fileList = ref<UploadUserFile[]>([
  // {
  //   name: 'element-plus-logo.svg',
  //   url: 'https://element-plus.org/images/element-plus-logo.svg',
  // },
  // {
  //   name: 'element-plus-logo2.svg',
  //   url: 'https://element-plus.org/images/element-plus-logo.svg',
  // },
])


const handleRemove: UploadProps['onRemove'] = (file, uploadFiles) => {
  this.fileGuid = '1'
}

const handlePreview: UploadProps['onPreview'] = (uploadFile) => {
  console.log(uploadFile)
}

const handleExceed: UploadProps['onExceed'] = (files, uploadFiles) => {
  ElMessage.warning(
      `The limit is 1, you selected ${files.length} files this time, add up to ${
          files.length + uploadFiles.length
      } totally`
  )
}

const beforeRemove: UploadProps['beforeRemove'] = (uploadFile, uploadFiles) => {
  return ElMessageBox.confirm(
      `Cancel the transfert of ${uploadFile.name} ?`
  ).then(
      () => true,
      () => false
  )
}

//file
const handleFileSuccess: UploadProps['onSuccess'] = (
    response1,
    uploadFile
) => {
  fileGuid.value = response1.data[0].guid
  if(uploadFile.raw != null && uploadFile.raw != undefined){
    // imageUrl.value = URL.createObjectURL(uploadFile.raw)
    console.log('hello');
  }
}



const file = reactive({
  url: '/restful/downloadFile?guid='+fileGuid,
  name:"hello"
})

function handleFileDownload(file){  //文件下载  file
  if(fileGuid.value !=  '1'){
    http.post(file.url,{},{responseType: 'blob'}).then(res=>{
      // let blob = new Blob([res.data], {type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"})
      let blob = new Blob([res.data], {type: "application/octet-stream"})
      let fileName = file.name;
      if('msSaveOrOpenBlob' in navigator){
        //ie使用的下载方式
        window.navigator.msSaveOrOpenBlob(blob, fileName);
      }else{
        let link = document.createElement('a')
        // link.href = window.URL.createObjectURL(blob)
        link.href = URL.createObjectURL(blob)
        link.download = fileName;
        link.click();
      }
    });
  }else {
    console.log("1");
  }
}

function getFileInfo(fileGuid){//文件加载显示
  baseData.getFileInfo({guid:fileGuid.value})
      .then((res) => {

        allData.tableData = res.data.data
        let filePath = res.data.data[0].filePath
        let fileName = res.data.data[0].fileName
        fileList.value.push({name: fileName,
          url: 'http://localhost:9999/file/'+filePath,})
        page.total = res.data.total
      })
      .catch(() => {
        ElMessage.error('加载出现小bug!');
      });
}
//文件END=================================


// 页面加载后调用函数
onMounted(() => {
  //加载图片显示
  handleImgDownload(imgFileInfo)
  //加载文件显示
  getFileInfo(fileGuid);

});




// 路由
const route = useRoute();
// const text = ref('xxx')
const text = ref(route.query.id);
const qrImage = ref('')



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
  baseData.viewData({userId:localStorage.getItem("userId"),userFlag:localStorage.getItem("userFlag")})
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

/*图片*/
.demo-image__placeholder .block {
  padding: 30px 0;
  text-align: center;
  border-right: solid 1px var(--el-border-color);
  display: inline-block;
  width: 49%;
  box-sizing: border-box;
  vertical-align: top;
}
.demo-image__placeholder .demonstration {
  display: block;
  color: var(--el-text-color-secondary);
  font-size: 14px;
  margin-bottom: 20px;
}
.demo-image__placeholder .el-image {
  padding: 0 5px;
  max-width: 300px;
  max-height: 200px;
}

.demo-image__placeholder.image-slot {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  background: var(--el-fill-color-light);
  color: var(--el-text-color-secondary);
  font-size: 14px;
}
.demo-image__placeholder .dot {
  animation: dot 2s infinite steps(3, start);
  overflow: hidden;
}


</style>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}

.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
