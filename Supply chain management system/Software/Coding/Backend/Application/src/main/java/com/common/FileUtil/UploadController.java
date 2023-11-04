//package com.common.FileUtil;
//
//import com.common.ReturnCodeUtil;
//import com.common.StringUtil;
//import com.service.FilePathService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//import com.entity.FilePath;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.UUID;
//
//@RestController
//public class UploadController {
//
//    @Autowired
//    private FilePathService filePathService;
//
//    @RequestMapping("/restful/uploadFile")
//    public ReturnCodeUtil  uploadFile(@RequestParam("file") MultipartFile file){
//        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
//        OutputStream fos = null;
//        InputStream fis = null;
//        try {
//            // 获取文件名
//            String fileName = file.getOriginalFilename();
//            int beginIndex = fileName.lastIndexOf(".");
//            String fileType = fileName.substring(beginIndex);
//            // 是否不允许上传的文件
//            if (!isExistFileType(fileName)) {
//                returnCodeUtil.setResult(false);
//                returnCodeUtil.setMessage("请上传正确格式文件");
//                return returnCodeUtil;
//            }
//            String uuidFileName = StringUtil.getUniqueString() + fileType;
//            FilePath append = new FilePath();
//            append.setGuid(UUID.randomUUID().toString());
//            append.setFileName(fileName);//本来的文件名
//            append.setFilePath(uuidFileName);//改动后的文件名
//            // 文件大小 byte
//            append.setAppendixSize(file.getSize() + "");
//            String filePath = "C:\\codeSource\\code\\application\\src\\main\\resources\\static\\file";
//            // 生成文件
//            File dir = new File(filePath);
//            if (!dir.exists()) {
//                dir.mkdirs();
//            }
//            fos = new FileOutputStream( filePath+ File.separator + uuidFileName);
//            // 以上传文件建立一个文件上传流
//            fis = file.getInputStream();
//            // 将上传文件的内容写入服务器
//            byte[] buffer = new byte[1024];
//            int len = 0;
//            while ((len = fis.read(buffer)) > 0) {
//                fos.write(buffer, 0, len);
//            }
//            // 插入数据库，生成文件数据
//            filePathService.insert(append);
//            returnCodeUtil.setData(append);
//        }catch (Exception e){
//
//        }
//
//        return  returnCodeUtil;
//    }
//
//
//    // 判断是否允许上传类型
//    private boolean isExistFileType(String filename) {
//        int dotIndex = filename.lastIndexOf(".");
//        String fileType = filename.substring(dotIndex + 1).trim();
//        if (StringUtil.isNotNullString(fileType)) {
//            // 不允许上传的类型
//            if (fileType.toLowerCase().equals("jsp") || fileType.toLowerCase().equals("html")
//                    || fileType.toLowerCase().equals("cgi") || fileType.toLowerCase().equals("htm")
//                    || fileType.toLowerCase().equals("js") || fileType.toLowerCase().equals("shtml")
//                    || fileType.toLowerCase().equals("asp") || fileType.toLowerCase().equals("aspx")
//                    || fileType.toLowerCase().equals("php")) {
//                return false;
//            }
//            // 系统配置允许上传的后缀名
//            String fileTypes = "*.pdf;*.doc;*.docx;*.xls;*.xlsx;*.txt;*.ppt;*.pptx;*.zip;*.rar;*.png;*.jpg;*.gif;*.jpeg;*.bmp;";// SystemConfigUtil.getSysConfig().getFileTypes();
//            fileTypes = "*.pdf;*.doc;*.docx;*.xls;*.xlsx;*.txt;*.ppt;*.pptx;*.zip;*.rar;*.png;*.jpg;*.gif;*.jpeg;*.bmp;";
//            String[] urlArray = fileTypes.split(";");
//            for (int i = 0; i < urlArray.length; i++) {
//                String type = urlArray[i];
//                int beginIndex = type.lastIndexOf(".");
//                if (beginIndex >= 0) {
//                    type = type.substring(beginIndex + 1);
//                    if (fileType.toLowerCase().equals(type.toLowerCase())) {
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }
//
//
//}
