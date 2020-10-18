package com.ruoyi.his.callservice;

import com.ruoyi.his.domain.DopayInfo;
import com.ruoyi.his.remote.request.ToPayRecipeInfoIn;
import com.ruoyi.his.remote.response.ToPayRecipeInfoOut;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
@Configuration
public interface HisPayOrderServices {

    ToPayRecipeInfoOut querytopayrecipeinfolist(ToPayRecipeInfoIn toPayRecipeInfoIn);

    /***
     * 获取所有待缴费金额
     * @param toPayRecipeInfoIn
     * @return
     */
    DopayInfo getToPayRecipeTotalAmount(ToPayRecipeInfoIn toPayRecipeInfoIn);
}
