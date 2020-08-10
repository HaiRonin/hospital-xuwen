package com.ruoyi.his.remote;

import com.alibaba.fastjson.JSON;
import com.ruoyi.his.domain.HisResponse;

import java.util.Map;

public class Test {
    public static void main(String[] args) {
          AbstractHisServiceHandler handler = new DoregInfoHisServiceHander();
        HisResponse hisResponse = null;
        String par = "01";
        Map<String, Object> requestMap = handler.buildBaseRequestData(null,par);
         handler.requestHisService("/querydepartmentlist", "{\"synUserName\":\"\",\"synKey\":\"\"}");

         requestMap = handler.buildBaseRequestData("organId",1342);
//         handler.requestHisService("/querydepartmentbyid", JSON.toJSONString(requestMap));

//        handler.invokeCall(1l);

          requestMap = handler.buildBaseRequestData("departmentorganId",par);
//          hisResponse = handler.requestHisService("/querydoctorlist", JSON.toJSONString(requestMap));

    }
}
