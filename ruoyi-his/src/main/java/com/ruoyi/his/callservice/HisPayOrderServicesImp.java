package com.ruoyi.his.callservice;

import com.alibaba.fastjson.JSON;
import com.ruoyi.his.domain.DopayInfo;
import com.ruoyi.his.remote.request.ToPayRecipeInfoIn;
import com.ruoyi.his.remote.response.DoPayOut;
import com.ruoyi.his.remote.response.ToPayRecipeInfoOut;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Configuration
public class HisPayOrderServicesImp implements HisPayOrderServices{

    @Autowired
    private HisBaseServices hisBaseServices;
    @Override
    public ToPayRecipeInfoOut querytopayrecipeinfolist(ToPayRecipeInfoIn toPayRecipeInfoIn) {
        try {
            String response = hisBaseServices.requestHisService("/queryToPayRecipeInfoList", JSON.toJSONString(toPayRecipeInfoIn));
            ToPayRecipeInfoOut toPayRecipeInfoOut = JSON.parseObject(response, ToPayRecipeInfoOut.class);
            return toPayRecipeInfoOut;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    /***
     * 获取所有待缴费金额
     * @param toPayRecipeInfoIn
     * @return
     */
    @Override
    public DopayInfo getToPayRecipeTotalAmount(ToPayRecipeInfoIn toPayRecipeInfoIn) {
        ToPayRecipeInfoOut toPayRecipeInfoOut =  querytopayrecipeinfolist(toPayRecipeInfoIn);
        if(null == toPayRecipeInfoOut || CollectionUtils.isEmpty(toPayRecipeInfoOut.getHiFee())){
            return null;
        }
        DopayInfo dopayInfo = new DopayInfo();
        double amount = toPayRecipeInfoOut.getHiFee().stream().mapToDouble(ToPayRecipeInfoOut.HiFeeBean::getSettleAmount).sum();
        dopayInfo.setPayMoney(BigDecimal.valueOf(amount));
        List<String> hiFeeNos = toPayRecipeInfoOut.getHiFee().stream().map(ToPayRecipeInfoOut.HiFeeBean::getHiFeeNo).collect(Collectors.toList());
        dopayInfo.setHiFeeNos(String.join(",", hiFeeNos));
        return dopayInfo;
    }
}
