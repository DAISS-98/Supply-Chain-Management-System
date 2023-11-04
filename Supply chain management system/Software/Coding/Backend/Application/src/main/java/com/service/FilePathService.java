//package com.service;
//
//import org.springframework.stereotype.Service;
//import javax.annotation.Resource;
//import com.entity.FilePath;
//import com.dao.FilePathMapper;
//
//@Service
//public class FilePathService {
//
//    @Resource
//    private FilePathMapper filePathMapper;
//
//
//    public int deleteByPrimaryKey(String guid) {
//        return filePathMapper.deleteByPrimaryKey(guid);
//    }
//
//
//    public int insert(FilePath record) {
//        return filePathMapper.insert(record);
//    }
//
//
//    public int insertSelective(FilePath record) {
//        return filePathMapper.insertSelective(record);
//    }
//
//
//    public FilePath selectByPrimaryKey(String guid) {
//        return filePathMapper.selectByPrimaryKey(guid);
//    }
//
//
//    public int updateByPrimaryKeySelective(FilePath record) {
//        return filePathMapper.updateByPrimaryKeySelective(record);
//    }
//
//
//    public int updateByPrimaryKey(FilePath record) {
//        return filePathMapper.updateByPrimaryKey(record);
//    }
//
//}
//
//
//
