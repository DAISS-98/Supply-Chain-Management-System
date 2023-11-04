<template>
  <el-upload
      class="avatar-uploader"
      action="/restful/uploadFile"
      :show-file-list="false"
      :on-success="handleAvatarSuccess"
      :before-upload="beforeAvatarUpload"
      :disabled="disabled"
  >
    <img v-if="imageUrl" :src="imageUrl" class="avatar" />
    <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
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
import { Plus } from '@element-plus/icons-vue'
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
  imgGuid: String, //guid
  disabled: {
    type:Boolean,
    default:false
  },
});

const {imgGuid,disabled} = toRefs(props);

const emit = defineEmits(['getGuid']);

/**
 * 返回guid
 */
function returnImgGuid(guid) {
  emit('getGuid', guid);
}

defineExpose({
  returnImgGuid,disabled
});

watch(
    //侦测Guid是否改变
    () =>imgGuid ,
    (oldValue, newValue) => {
      handleImgDownload(imgFileInfo);
    },
);

onMounted(
    () => {
      handleImgDownload(imgFileInfo)
    }
)
//图片上传BEGIN=========================================
const imageUrl = ref('')
//图片上传成功guid处理
const handleAvatarSuccess: UploadProps['onSuccess'] = (
    response,
    uploadFile
) => {
  // imgGuid.value = response.data[0].guid;
  //imgGuid向上抛

  returnImgGuid(response.data[0].guid)
  if(uploadFile.raw != null && uploadFile.raw != undefined){
    imageUrl.value = URL.createObjectURL(uploadFile.raw)
  }
}

//检查imgGuid是否为空
function checkGuid(){

  if(imgGuid.value == '' || imgGuid.value == null || imgGuid.value == undefined ){
    return false;
  }
  return true;
}


const beforeAvatarUpload: UploadProps['beforeUpload'] = (rawFile) => {
  if (rawFile.type !== 'image/jpeg') {
    ElMessage.error('picture must be JPG format!')
    return false
  } else if (rawFile.size / 1024 / 1024 > 2) {
    ElMessage.error('picture size can not exceed 2MB!')
    return false
  }
  return true
}

const imgFileInfo = reactive({
  url: '/restful/downloadFile?guid='+imgGuid.value,
  name:"picture.gpg"
})

//图片下载(图片显示，获取图片的信息)  参数imgFile
function handleImgDownload(file){

  console.log(imgGuid)
    if(!checkGuid()) return ;
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
}
//图片上传END============================================
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
