package com.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * file_path
 */
@Getter
@Setter
@ToString
public class FilePath implements Serializable {


    private static final long serialVersionUID = 1L;
    /**
     * 文件唯一id标识
     */
    private String guid;

    /**
     * 文件路径（映射本地文件名）
     */
    private String filePath;

    /**
     * 附件大小;
     */
    private String appendixSize;

    /**
     * 文件名称
     */
    private String fileName;
}
