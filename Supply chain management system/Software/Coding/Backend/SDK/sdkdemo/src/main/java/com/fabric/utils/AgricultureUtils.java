package com.fabric.utils;

import com.alibaba.fastjson.JSON;
import com.entity.FabricData;
//import com.example.agriculture.entity.CommonUse;
import com.google.gson.Gson;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AgricultureUtils {

    public  List<com.fabric.po.CommonUse> toListMap(String json){
        List<Object> list = JSON.parseArray(json);
        if(list ==null) return null;
        List<Map<String,Object>> listw = new ArrayList<Map<String,Object>>();
        for (Object object : list){
            Map<String,Object> ageMap = new HashMap<String,Object>();
            Map <String,Object> ret = (Map<String, Object>) object;//取出list里面的值转为map
            listw.add(ret);
        }

        List<com.fabric.po.CommonUse> commonUses = new ArrayList<>();
        for(int i = 0;i<listw.size();i++){
            FabricData commonUse = new FabricData();
            com.fabric.po.CommonUse commonUse1 = new com.fabric.po.CommonUse();
            Map<String, Object> s  = (Map<String, Object>) list.get(i);
            String tid = (String) s.get("TID");
            String ttime = (String) s.get("TTime");
            String IsDelete = (String) s.get("IsDelete");
            Object obj = s.get("THistory");

            if(null !=obj){
                String sss = obj.toString();
                Gson gson = new Gson();
                Map<String, Object> map = new HashMap<String, Object>();
                map = gson.fromJson(sss, map.getClass());
                String data = (String) map.get("data");
                try {
                    commonUse = SerializeUtils.deserialize(data);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                BeanUtils.copyProperties(commonUse,commonUse1);
                commonUse1.setTID(tid);
                commonUse1.setIsDelete(IsDelete);
                commonUse1.setTTime(ttime);
                commonUses.add(commonUse1);
            }else {
//                commonUse.setRole("管理员");
//                commonUse.setOrderstatus("已删除");
                commonUses.add(commonUse1);
            }

        }
        for(int i = 0;i<commonUses.size();i++){
            System.out.println(commonUses.get(i));
        }
        return commonUses;
    }

}
