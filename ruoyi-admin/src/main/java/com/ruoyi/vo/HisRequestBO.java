package com.ruoyi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wu123
 * @since 2019-01-20
 */

@ApiModel("his接口请求对象")
public class HisRequestBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 接口名称
     */
    @ApiModelProperty("接口名称")
    private String api;

    /***
     * 接口参数
     */
    @ApiModelProperty("接口请求参数消息体")
    private String dataParam;

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getDataParam() {
        return dataParam;
    }

    public void setDataParam(String dataParam) {
        this.dataParam = dataParam;
    }
}
