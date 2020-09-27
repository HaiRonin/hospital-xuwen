package com.ruoyi.his.remote;

import com.ruoyi.common.exception.BusinessException;
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
public class HisCallNongKenServices implements HisCallServices{

    /**
     * His接口地址
     */
    @Value("${his.service.url}")
    private String hisUrl;
    /**
     * His接口地址
     */
    @Value("${his.service.name}")
    private String hisName;

    private static Logger logger = LoggerFactory.getLogger(HisCallNongKenServices.class);

    /***
     * 农垦his请求
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
}
