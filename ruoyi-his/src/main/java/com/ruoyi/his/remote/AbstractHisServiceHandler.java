package com.ruoyi.his.remote;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.MapUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.xss.HttpUtils;
import com.ruoyi.his.constant.HisBusinessTypeEnum;
import com.ruoyi.his.domain.HisResponse;
import com.ruoyi.his.remote.request.BaseRequest;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/***
 * his接口抽象类
 */
public abstract class AbstractHisServiceHandler<T extends BaseRequest> implements HisWebServices{

    private static Logger logger = LoggerFactory.getLogger(AbstractHisServiceHandler.class);
    /**
     * 接口apiCode
     * @return
     */
    abstract public HisBusinessTypeEnum getBusinessType();

    /***
     * 数据检查
     * @param id
     * @return
     */
    abstract boolean checkData(Long id);

    /***
     * 构建需要请求的消息体
     * @return
     */
    public abstract T getBusinessData(Long id);

    /***
     * 接口调用完后处理
     * @return
     */
    abstract public String afterInvokeCall(Long id,HisResponse hisResponse);

    /***
     * 发送His接口请求
     * @return
     */
    @Override
    public HisResponse invokeCall(Long id) {
        if(!checkData(id)){
            throw new BusinessException(String.format("数据检查错误，操作失败:"));
        }
        String request = buildRequestData(id);
        HisResponse response = requestHisService(request);
        this.afterInvokeCall(id,response);
        return response;
    }


    /**
     * 封装构造his接口的请求消息体
     * @param id
     * @return
     */
    private String buildRequestData(Long id){
        T t = this.getBusinessData(id);
        Map<String, Object> requestMap = new HashMap<String, Object>();
        requestMap.put("synUserName","");
        requestMap.put("synKey","");
        try {
            requestMap.put(getBusinessType().getCode(), MapUtil.objectToMap(t));
            return JSON.toJSONString(requestMap);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(String.format("数据转换发生异常"));
        }
    }

    /***
     * 构建his请求
     * @param dataParam
     * @return
     */
    public HisResponse requestHisService(String dataParam) {
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("arg0", dataParam);
            logger.info("AbstractHisServiceHandler.api={},dataParam={}",getBusinessType().getApiCode(),dataParam);
            HttpResponse responseInfo = HttpUtils.doPost("http://219.129.12.10:8099/wechat.asmx", getBusinessType().getApiCode(),  new HashMap<>(), null,headers);
            String result = EntityUtils.toString(responseInfo.getEntity(), "UTF-8");
            logger.info("AbstractHisServiceHandler.api={}，result={}",result);
            if(StringUtils.isBlank(result)){
                throw new BusinessException(String.format("无法调用His接口"));
            }
            HisResponse hisResponse = JSON.toJavaObject(JSON.parseObject(result), HisResponse.class);
            return hisResponse;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(String.format("调用His接口发生异常，操作失败:%1$s",e.getMessage()));
        }

    }

}
