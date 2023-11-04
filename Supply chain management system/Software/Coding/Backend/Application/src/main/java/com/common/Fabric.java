package com.common;

import com.alibaba.druid.sql.ast.statement.SQLAlterTableDropPrimaryKey;
import com.alibaba.fastjson.JSON;
import com.entity.FabricData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.text.SimpleDateFormat;

/**
 * 数据发送工具
 * 将数据发送到端口8081的fabric sdk接口中
 */
@Service
public class Fabric {
    private String http = "http://localhost:8081/sdk";
    @Autowired
    private RestTemplate restTemplate;

    //将数据发送到hyperledger fabric sdk
    public  void sdk(FabricData fabricData) {
        //获取溯源码
        String onlyKey = fabricData.getOnlyKey();
        String s = null;
        try {
            s = SerializeUtils.serialize(fabricData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //编写完整的url
        String URL_invoke = http + "/invoke?id=" + onlyKey + "&data=" + s;
        //发送数据到sdk
        ResponseEntity<String> forEntity = restTemplate.getForEntity(URL_invoke, String.class);
        }


}