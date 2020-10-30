package com.ruoyi.his.remote.request.healthcard;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by ASUS on 2019/1/25.
 */
@ApiModel(value="上报的健康卡使用数据对象",description="上报的健康卡使用数据对象")
public class ReportDataResquest implements Serializable {

    @ApiModelProperty(value="健康卡ID",name="healthCardId",example="B5A28173356BB199962ABE0493ED0416CC17A9DF801CBE416B08CAB5A97D4AB6")
    private String healthCardId;

    @ApiModelProperty(value = "用卡环节:0101011 预约挂号\\n\" +\n" +
            "            \"0101012 当天挂号\\n\" +\n" +
            "            \"0101013 预约挂号查询\\n\" +\n" +
            "            \"0101051 门诊缴费待缴费\\n\" +\n" +
            "            \"0101052 门诊缴费已缴费\\n\" +\n" +
            "            \"0101081查检验报告\\n\" +\n" +
            "            \"0101082 查检查报告", name = "scene", example = "0101011")
    private String scene;


    @ApiModelProperty(value = "用卡科室,如果预约挂号（0101011）、当日挂号（0101012）、" +
            "则用卡科室数据为必填，上报医院科室中文名称即可", name = "department", example = "科室名称")
    private String department;

    public String getHealthCardId() {
        return healthCardId;
    }

    public void setHealthCardId(String healthCardId) {
        this.healthCardId = healthCardId;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
