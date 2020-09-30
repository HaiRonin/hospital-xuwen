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
    /**
     * His接口地址
     */
    @Value("${his.service.name}")
    private String hisName;

    private static Logger logger = LoggerFactory.getLogger(HisBaseServices.class);
    /***
     * 构建his请求
     * @param dataParam
     * @return
     */
    public String requestHisService(String apiUrl, String dataParam) {
        String result = "";
        try {
            switch (hisName){
                case "xuwen":
                    result=requestHisServiceForXuwen(apiUrl,dataParam);
                    break;
                case "nongken":
                    result=requestHisServiceForNk(apiUrl,dataParam);
                    break;
                default:
                    result=requestHisServiceForNk(apiUrl,dataParam);
                    break;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(String.format("调用His接口发生异常，操作失败:%1$s",e.getMessage()));
        }
    }

    /***
     * 徐闻his服务接口
     * @param apiUrl
     * @param dataParam
     * @return
     */
    public String requestHisServiceForXuwen(String apiUrl, String dataParam) {
        try {
            logger.info("调用His接口HisBaseServices.apiUrl={},dataParam={}",apiUrl,dataParam);
            String result = HttpUtils.post(hisUrl+apiUrl, dataParam);
            logger.info("调用His接口HisBaseServices.apiUrl={}，result={}",apiUrl,result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(String.format("调用His接口发生异常，操作失败:%1$s",e.getMessage()));
        }
    }

    /***
     * 农垦his请求
     * @param dataParam
     * @return
     */
    public String requestHisServiceForNk(String apiUrl, String dataParam) {
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
