<template>
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
      :disabled="disabled"
  >
    <el-button type="primary">Click to upload</el-button>
    <template #tip>
      <div class="el-upload__tip">
        doc files with a size less than 5M.
      </div>
    </template>
  </el-upload>

</template>

<script lang="ts"  setup>
import { ElMessageBox } from 'element-plus'
import { ElMessage } from 'element-plus';
import type { UploadProps } from 'element-plus'
import type {  UploadUserFile } from 'element-plus'
import * as http from '/src/utils/request'
import { reactive, onMounted  ,watch, ref,toRefs} from 'vue';
import * as baseData from '/src/api/baseData';
import {create} from "domain";

//所有的数据存放
const allData=reactive({
  searchData:null,
  tableData:[],
  editData:{},
  addData:{},
  routerParam:"",
})

//给我一个guid,我需要展示，没有给我guid，给我文件，我就返回一个guid给他
const props = defineProps({
  fileGuid: String, //guid
  disabled: {
    type:Boolean,
    default:false
  },
});

const {fileGuid,disabled} = toRefs(props);

const emit = defineEmits(['getGuid']);

/**
 * 返回guid
 */
function returnFileGuid(guid) {
  emit('getGuid', guid);
}

defineExpose({
  returnFileGuid,disabled
});

watch(
    //侦测fileGuid是否改变
    () =>fileGuid ,
    (oldValue, newValue) => {
        getFileInfo(newValue);
    },
);

onMounted(
    () => {
      getFileInfo(fileGuid)
    }
)
//文件BEGIN=========================================================================
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

//删除文件
const handleRemove: UploadProps['onRemove'] = (file, uploadFiles) => {
  fileGuid.value = ''
  returnFileGuid(null)
}

//点击文件列表，应该下载文件
const handlePreview: UploadProps['onPreview'] = (uploadFile) => {
  debugger
  handleFileDownload()
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

//文件成功上传后返回guid
const handleFileSuccess: UploadProps['onSuccess'] = (
    response1,
    uploadFile
) => {

  fileGuid.value = response1.data[0].guid
  //将guid向上抛
  returnFileGuid(response1.data[0].guid)
  if(uploadFile.raw != null && uploadFile.raw != undefined){
    // imageUrl.value = URL.createObjectURL(uploadFile.raw)
    console.log('hello');
  }
}

const file = reactive({
  url: '/restful/downloadFile?guid='+fileGuid.value,
  name:"hello"
})

//文件下载  file
function handleFileDownload(){
  debugger
  if(fileGuid.value !=  '1'   ){
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
//检查guid是否存在
function checkFileGuid(){
  if(fileGuid == '' || fileGuid == null || fileGuid == undefined ){
    return false;
  }
  return true;
}
//文件加载显示
function getFileInfo(fileGuid){
  if(!checkFileGuid) return;
debugger
  baseData.getFileInfo({guid:fileGuid.value})
      .then((res) => {
        allData.tableData = res.data.data
        let filePath = res.data.data[0].filePath
        let fileName = res.data.data[0].fileName
        file.name = fileName
        fileList.value.push({name: fileName,
          url: 'http://localhost:9999/file/'+filePath,})
      })
      .catch(() => {
        // ElMessage.error('加载出现小bug!');
      });
}
//文件END=====================================================================
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
