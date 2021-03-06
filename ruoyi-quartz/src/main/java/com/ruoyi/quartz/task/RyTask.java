package com.ruoyi.quartz.task;

import com.ruoyi.his.constant.HisBusinessTypeEnum;
import com.ruoyi.his.remote.AbstractHisServiceHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 定时任务调度测试
 * 
 * @author ruoyi
 */
@Component("ryTask")
public class RyTask {

    protected static Logger logger = LoggerFactory.getLogger(RyTask.class);
//    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i)
//    {
//        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
//    }

//    public void ryParams(String params)
//    {
//        System.out.println("执行有参方法：" + params);
//    }

    public void regCancelByDocStopForRefund()
    {
        AbstractHisServiceHandler.servicesInstance(HisBusinessTypeEnum.DOREG).regCancelByDocStopForRefund();
    }

    public void refund()
    {
        for(HisBusinessTypeEnum businessTypeEnum : HisBusinessTypeEnum.values()  ){
            logger.info("task.run.name={}.start",businessTypeEnum.getDesc());
            AbstractHisServiceHandler.servicesInstance(businessTypeEnum).autoScanningRefund();
            logger.info("task.run.name={}.end",businessTypeEnum.getDesc());
        }
    }
}
