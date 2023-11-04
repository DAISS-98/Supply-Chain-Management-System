//package com.common.FileUtil;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import com.common.ReturnCodeUtil;
//import com.common.StringUtil;
//import com.entity.FilePath;
//import com.service.FilePathService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.FileCopyUtils;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.math.BigDecimal;
//import java.net.URLEncoder;
//
//@RestController
//public class DownloadController {
//
//    @Autowired
//    private FilePathService filePathService;
//
//    @RequestMapping(value = "/restful/downloadFile")
//    public ReturnCodeUtil downloadFile(HttpServletResponse response,@RequestBody String guid ) throws IOException {
//        guid = guid.replaceAll("guid=","");
//        ReturnCodeUtil res = new ReturnCodeUtil();
//        // 生成数据库文件记录
//        FilePath appendixEntity = new FilePath();
//        appendixEntity.setGuid(guid);
//        if(guid != null){
//             appendixEntity = filePathService.selectByPrimaryKey(guid);
//        }else {
//            res.setMessage("文件不存在");
//            res.setResult(false);
//            return res;
//        }
//
//        //获取到path
//        String filePath = "C:\\codeSource\\code\\application\\src\\main\\resources\\static\\file\\"+appendixEntity.getFilePath();
//        File file = new File(filePath);
//        String showName = appendixEntity.getFileName();
//        InputStream inputStream = null;
//        try {
//            inputStream = new FileInputStream(file);
//            // 设置response头信息
//            response.reset();
//            response.setContentType("application/octet-stream");
//			response.setHeader("Content-Length", String.valueOf(appendixEntity.getAppendixSize()));
//            // 去除掉单位“M”
//            if (StringUtil.isNotNullString(appendixEntity.getAppendixSize())) {
//                String subStringM = appendixEntity.getAppendixSize().toString();
//                if (subStringM.endsWith("M")) {
//                    subStringM = subStringM.substring(0, subStringM.length() - 1);
//                    appendixEntity.setAppendixSize(((new BigDecimal(subStringM)).multiply(new BigDecimal(1024 * 1024))
//                            .setScale(0, BigDecimal.ROUND_HALF_UP)).toString());
//                }
//            }
//            response.setHeader("Content-Length", String.valueOf(appendixEntity.getAppendixSize()));
//            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(showName, "UTF-8"));
//            OutputStream out = response.getOutputStream();
//            // 创建缓冲区
//            FileCopyUtils.copy(new BufferedInputStream(inputStream), new BufferedOutputStream(out));
//        } catch (IOException e) {
//            res.setResult(false);
//            res.setData(showName);
//            return res;
//        } finally {
//            if (inputStream != null) {
//                response.getOutputStream().flush();
//                response.getOutputStream().close();
//                inputStream.close();
//            }
//        }
//
//        return res;
//    }
//
//
//    @RequestMapping("/restful/getFileInfo")
//    public ReturnCodeUtil getFileInfo(@RequestBody FilePath filePath1){
//        ReturnCodeUtil returnCodeUtil = new ReturnCodeUtil();
//        FilePath filePath = filePathService.selectByPrimaryKey(filePath1.getGuid());
//        returnCodeUtil.setData(filePath);
//        returnCodeUtil.setResult(true);
//        return returnCodeUtil;
//    }
//
//
//}
