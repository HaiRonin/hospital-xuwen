package com.ruoyi.his.remote;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.xss.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
@Configuration
public class HisCallXuWenServices implements HisCallServices{

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

    private static Logger logger = LoggerFactory.getLogger(HisCallXuWenServices.class);

    /***
     * 徐闻his服务接口
     * @param apiUrl
     * @param dataParam
     * @return
     */
    public String requestHisService(String apiUrl, String dataParam) {
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
}
