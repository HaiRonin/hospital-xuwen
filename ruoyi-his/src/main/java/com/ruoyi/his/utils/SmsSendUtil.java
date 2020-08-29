package com.ruoyi.his.utils;

import com.ruoyi.common.exception.HisException;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class SmsSendUtil {

    private static Logger logger= LoggerFactory.getLogger(SmsSendUtil.class);

    public static String sendMsg(String mobile,String message)  {

        String url = "http://api.qirui.com:7891/mt";
        //apiKey和APISecret
        String apiKey  = "1777570010";
        String apiSecret = "1276711630a5ff155db8";
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+message);
        String respStr = null;
        StringBuilder sb = new StringBuilder(2000);
        sb.append(url);
        sb.append("?dc=15");
        try {
            sb.append("&sm=").append(URLEncoder.encode(message, "utf8"));
            sb.append("&da=").append(mobile);
            sb.append("&un=").append(apiKey);
            sb.append("&pw=").append(apiSecret);
            sb.append("&tf=3&rd=1&rf=2");   //短信内容编码为 urlencode+utf8
            String request = sb.toString();
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(request);
            CloseableHttpResponse response = client.execute(httpGet);

            HttpEntity entity = response.getEntity();
            if(entity != null) {
                respStr = EntityUtils.toString(entity, "UTF-8");
                logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>【短信请求成功】>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+respStr);
                return respStr;
            }
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>【短信请求失败】>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+respStr);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new HisException(String.format("%1$s号码发送短信失败:",mobile));
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            throw new HisException(String.format("%1$s号码发送短信失败:",mobile));
        }
        return respStr;

    }

}