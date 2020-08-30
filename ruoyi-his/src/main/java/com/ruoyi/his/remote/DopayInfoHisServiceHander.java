package com.ruoyi.his.remote;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.exception.HisException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.his.constant.HisBusinessTypeEnum;
import com.ruoyi.his.constant.PayStatusEnum;
import com.ruoyi.his.domain.DopayInfo;
import com.ruoyi.his.domain.DoregInfo;
import com.ruoyi.his.remote.request.DoPayIn;
import com.ruoyi.his.remote.request.DoRegIn;
import com.ruoyi.his.remote.request.DoRequestInfo;
import com.ruoyi.his.remote.response.DoPayOut;
import com.ruoyi.his.remote.response.DoRegOut;
import com.ruoyi.his.service.IDopayInfoService;
import com.ruoyi.his.service.IDoregInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 缴费Service业务层处理
 * 
 * @author whl
 * @date 2020-08-28
 */
@Service
public class DopayInfoHisServiceHander extends AbstractHisServiceHandler<DoPayIn, DoPayOut> {
    @Autowired
    private IDopayInfoService dopayInfoService;

    @Override
    public HisBusinessTypeEnum getBusinessType() {
        return HisBusinessTypeEnum.DOPAY;
    }

    @Override
    boolean checkData(String outTradeNo) {
        DopayInfo dopayInfo = dopayInfoService.getDetailByOutTradeNo(outTradeNo);
        if(null == dopayInfo){
            throw new HisException(String.format("%1$s记录不存在，不能进行此操作:",outTradeNo));
        }
        if(PayStatusEnum.PAY_SUCCESS.getCode().equals(dopayInfo.getSuccessfulPayment())){
            throw new HisException(String.format("%1$s记录不是支付成功状态，不能进行此操作:",outTradeNo));
        }
        if(StringUtils.isEmpty(dopayInfo.getTransactionId())){
            throw new HisException(String.format("%1$s记录支付流水为空，不能进行此操作:",outTradeNo));
        }
        return true;
    }

    /***
     * {
     *     "socialsecurityNO":"",
     *     "userNo":"",
     *     "overMoney":"",
     *     "medicareReturn":"",
     *     "payCardNo":"",
     *     "synKey":"",
     *     "overRecord":"",
     *     "synUserName":"",
     *     "payNo":"4200000596202008020458500188",
     *     "payType":"5",
     *     "payAmount":211.8,
     *     "bankReturn":"",
     *     "hiFeeNos":"2020073111931",
     *     "medicareType":1,
     *     "terminalCode":""
     * }
     * @param outTradeNo
     * @return
     */
    @Override
    public DoPayIn buildRequestData(String outTradeNo) {
        DopayInfo dopayInfo = dopayInfoService.getDetailByOutTradeNo(outTradeNo);
        if(null == dopayInfo){
            throw new HisException(String.format("%1$s记录已经不存在，不能进行此操作:",outTradeNo));
        }
        DoPayIn doPayIn = new DoPayIn();
        doPayIn.setHiFeeNos(dopayInfo.getHiFeeNos());
        doPayIn.setPayType(dopayInfo.getPayType());
        //支付流水号
        doPayIn.setPayNo(dopayInfo.getTransactionId());
        doPayIn.setPayAmount(dopayInfo.getPayMoney().toString());
        doPayIn.setMedicareType(String.valueOf(dopayInfo.getMedicareType()));
        return doPayIn;
    }

    @Override
    protected DoPayOut transResult(String result) {
        return JSON.toJavaObject(JSON.parseObject(result), DoPayOut.class);
    }


    @Override
    public boolean afterInvokeCallSumbit(String outTradeNo, DoPayOut doPayOut) {
        DopayInfo dopayInfoTemp = dopayInfoService.getDetailByOutTradeNo(outTradeNo);
        if(null == dopayInfoTemp){
            throw new HisException(String.format("%1$s记录已经不存在，不能进行此操作:",outTradeNo));
        }
        DopayInfo dopayInfo = new DopayInfo();
        dopayInfo.setId(dopayInfoTemp.getId());
        dopayInfo.setUpdateTime(DateUtils.getNowDate());
        dopayInfo.setResultCode(doPayOut.getResultCode());
        dopayInfo.setResultMsg(doPayOut.getResultMsg());
        dopayInfo.setSuccessfulPayment(doPayOut.isOk()?PayStatusEnum.ORDER_SUCCESS.getCode():PayStatusEnum.ORDER_FAIL.getCode());
        if(doPayOut.isOk()){
            dopayInfo.setResultMessage(JSON.toJSONString(doPayOut.getResultmessage()));
            dopayInfo.setCheckInfo(doPayOut.getCheckInfo());
        }
        dopayInfoService.updateDopayInfo(dopayInfo);
        return true;
    }

}
