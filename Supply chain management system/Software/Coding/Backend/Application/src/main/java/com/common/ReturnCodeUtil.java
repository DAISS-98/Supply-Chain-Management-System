package com.common;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 后端返回前端数据的工具类
 */
public class ReturnCodeUtil {
    public static final String falseCode = "0000";//fail
    public static final String successCode = "1111";//success
    private Object data;//返回的数据
    private String message;//返回的信息
    private String code;//返回码
    private Boolean result;//true 获取数据成功  false 获取数据失败
    private int total;//总共有多少条数据
    private Object objectData;
    public ReturnCodeUtil() {
    }




    public void setNotListData(Object objectData){
        this.data = objectData;
    }
    public void setNotListDataAndTrue(Object objectData){
        this.data = objectData;
        this.setResult(true);
    }

    public Object getData() {
        return data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setData(Object data) {
        if(data instanceof List){
            List list = (List)data;
            this.total = list.size();
            this.data = data;
        }else if(null == data){
            this.total = 0;
            this.data = data;
        }else{
            ArrayList arrayList = new ArrayList();
            arrayList.add(data);
            this.data = arrayList;
            this.total = 1;
        }
    }
    /** 解析数据 */
    public void setParseData(Object data){
        if(data instanceof List){
            List list = (List)data;
            list = StringUtil.analysis(list);
            this.total = list.size();
            this.data = list;
        }else if(null == data){
            this.total = 0;
            this.data = data;
        }else{
            List arrayList = new ArrayList();
            arrayList.add(data);
            arrayList = StringUtil.analysis(arrayList);
            this.data = arrayList;
            this.total = 1;
        }
    }

    public void setDataAndTrue(Object data) {
        this.setResult(true);
        setData(data);
    }



    public Object getObjectData() {
        return objectData;
    }

    public void setObjectData(Object objectData) {
        this.objectData = objectData;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
    public void setResultTrue() {
        this.result = true;
    }
    public void setResultTrueAndMessage(String message) {
        this.result = true;
        this.message = message;
    }
    public void setResultFalseAndMessage(String message) {
        this.result = false;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
