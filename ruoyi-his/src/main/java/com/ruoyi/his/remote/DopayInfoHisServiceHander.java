package com.ruoyi.his.remote;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.his.constant.HisBusinessTypeEnum;
import com.ruoyi.his.constant.PayStatusEnum;
import com.ruoyi.his.domain.DopayInfo;
import com.ruoyi.his.domain.DoregInfo;
import com.ruoyi.his.remote.request.DoPayIn;
import com.ruoyi.his.remote.response.BaseResponse;
import com.ruoyi.his.remote.response.DoPayOut;
import com.ruoyi.his.remote.response.DoRegOut;
import com.ruoyi.his.service.IDopayInfoService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 缴费Service业务层处理
 * 
 * @author whl
 * @date 2020-08-28
 */
@Service
public class DopayInfoHisServiceHander extends AbstractHisServiceHandler<DoPayIn,DopayInfo, DoPayOut> {
    @Autowired
    private IDopayInfoService dopayInfoService;

    @Override
    public HisBusinessTypeEnum getBusinessType() {
        return HisBusinessTypeEnum.DOPAY;
    }


    @Override
    DopayInfo getOrderDetail(String outTradeNo) {
        DopayInfo dopayInfo = dopayInfoService.getDetailByOutTradeNo(outTradeNo);
        dopayInfo.setAmount(dopayInfo.getPayMoney());
        return dopayInfo;
    }

    @Override
    int updateOrder(DopayInfo dopayInfo) {
        return dopayInfoService.updateDopayInfo(dopayInfo);
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
        DopayInfo dopayInfo = getOrderDetail(outTradeNo);
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
        return JSON.parseObject(result, DoPayOut.class);
    }


    @Override
    public BaseResponse afterInvokeCallSumbit(String outTradeNo, DoPayOut doPayOut) {
        DopayInfo dopayInfoTemp = getOrderDetail(outTradeNo);
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
        return doPayOut.isOk()?BaseResponse.success():BaseResponse.fail("操作失败，支付金额稍后将会原路返回");
    }

    @Override
    protected List<DopayInfo> getRefundOrderList() {
        List<DopayInfo> refundList = new ArrayList<>();
        DopayInfo query = new DopayInfo();
        query.setSuccessfulPayment(PayStatusEnum.ORDER_FAIL.getCode());
        List<DopayInfo> lstDopayInfo =dopayInfoService.selectDopayInfoList(query);
        if(CollectionUtils.isNotEmpty(lstDopayInfo)){
            refundList.addAll(lstDopayInfo);
        }
        query = new DopayInfo();
        query.setSuccessfulPayment(PayStatusEnum.REFUND_FAIL.getCode());
        List<DopayInfo> refundTodo =dopayInfoService.selectDopayInfoList(query);
        if(CollectionUtils.isNotEmpty(refundTodo)){
            refundList.addAll(refundTodo);
        }
        return lstDopayInfo;
    }


}
