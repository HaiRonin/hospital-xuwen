package com.ruoyi.his.remote;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.MapUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.xss.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Configuration
public class HisBaseServices {

    /**
     * His接口地址
     */
    @Value("${his.service.url}")
    private String hisUrl;


    private static Logger logger = LoggerFactory.getLogger(HisBaseServices.class);
    /***
     * 构建his请求
     * @param dataParam
     * @return
     */
    public String requestHisService(String apiUrl, String dataParam) {
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("doRegIn", dataParam);
            logger.info("调用His接口HisBaseServices.apiUrl={},dataParam={}",apiUrl,dataParam);
            HttpResponse responseInfo = HttpUtils.doPost(hisUrl, apiUrl,  new HashMap<>(), null,headers);
            String result = EntityUtils.toString(responseInfo.getEntity(), "UTF-8");
            logger.info("调用His接口HisBaseServices.apiUrl={}，result={}",apiUrl,result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(String.format("调用His接口发生异常，操作失败:%1$s",e.getMessage()));
        }
    }

    /**
     * 封装构造his接口的请求消息体
     * @return
     */
    protected Map<String, Object> buildBaseRequestData(String key,Object obj){
        Map<String, Object> requestMap = new HashMap<String, Object>();
        requestMap.put("synUserName","");
        requestMap.put("synKey","");
        //无入参
        if(StringUtils.isEmpty(key)){
            return requestMap;
        }
        try {
            if(obj instanceof String){
                requestMap.put(key, obj);
            }
            else if(obj instanceof Integer){
                requestMap.put(key, obj);
            }
            else{
                requestMap.put(key, MapUtil.objectToMap(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(String.format("数据转换发生异常"));
        }
        return requestMap;
    }
}
