package com.ruoyi.his.remote;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.his.constant.HisBusinessTypeEnum;
import com.ruoyi.his.constant.PayStatusEnum;
import com.ruoyi.his.domain.CovOrder;
import com.ruoyi.his.domain.DopayInfo;
import com.ruoyi.his.domain.DoregInfo;
import com.ruoyi.his.remote.request.DoCovIn;
import com.ruoyi.his.remote.request.DoPayIn;
import com.ruoyi.his.remote.response.BaseResponse;
import com.ruoyi.his.remote.response.DoCovOut;
import com.ruoyi.his.remote.response.DoPayOut;
import com.ruoyi.his.service.ICovOrderService;
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
public class CovOrderHisServiceHander extends AbstractHisServiceHandler<DoCovIn, CovOrder, DoCovOut> {
    @Autowired
    private ICovOrderService iCovOrderService;


    @Override
    public HisBusinessTypeEnum getBusinessType() {
        return HisBusinessTypeEnum.COV;
    }

    @Override
    CovOrder getOrderDetail(String outTradeNo) {
        CovOrder covOrder = iCovOrderService.getDetailByOutTradeNo(outTradeNo);
        covOrder.setAmount(covOrder.getPayAmount());
        return covOrder;
    }

    @Override
    int updateOrder(CovOrder covOrder) {
        return iCovOrderService.updateCovOrder(covOrder);
    }

    @Override
    public DoCovIn buildRequestData(String outTradeNo) {
        CovOrder covOrder = iCovOrderService.getDetailByOutTradeNo(outTradeNo);
        DoCovIn covIn = new DoCovIn();
        covIn.setCardNo(covOrder.getCardNo());
        covIn.setName(covOrder.getName());
        covIn.setPayType(covOrder.getPayType());
        covIn.setPayNo(covOrder.getTransactionId());
        covIn.setPayAmount(covOrder.getPayAmount().toString());
        return covIn;
    }

    @Override
    protected DoCovOut transResult(String result) {
        return JSON.parseObject(result, DoCovOut.class);
    }

    @Override
    public BaseResponse afterInvokeCallSumbit(String outTradeNo, DoCovOut doCovOut) {
        CovOrder covOrderTemp = iCovOrderService.getDetailByOutTradeNo(outTradeNo);
        CovOrder covOrder = new CovOrder();
        covOrder.setId(covOrderTemp.getId());
        covOrder.setUpdateTime(DateUtils.getNowDate());
        covOrder.setResultCode(doCovOut.getResultCode());
        covOrder.setResultMsg(doCovOut.getResultMsg());
        covOrder.setSuccessfulPayment(doCovOut.isOk()?PayStatusEnum.ORDER_SUCCESS.getCode():PayStatusEnum.ORDER_FAIL.getCode());
//        if(doCovOut.isOk()){
////            covOrder.setResultMsg(JSON.toJSONString(doCovOut.getResultMsg()));
//            System.out.println(JSON.toJSONString(doCovOut));
//        }
        iCovOrderService.updateCovOrder(covOrder);
        return doCovOut.isOk()?BaseResponse.success():BaseResponse.fail("操作失败，支付金额稍后将会原路返回");
    }

    @Override
    protected List<CovOrder> getRefundOrderList() {
        return null;
    }
}
