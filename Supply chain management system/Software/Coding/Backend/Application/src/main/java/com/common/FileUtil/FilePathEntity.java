package com.common.FileUtil;

public class FilePathEntity implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 文件路径（映射本地文件名）
     */
    private String filePath;

    // 附件大小;
    private String appendixSize;

    /**
     * 业务类型
     */
//    private Integer fileId;
    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件唯一id标识
     */
    private String guid;


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

//    public Integer getFileId() {
//        return fileId;
//    }
//
//    public void setFileId(Integer fileId) {
//        this.fileId = fileId;
//    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
