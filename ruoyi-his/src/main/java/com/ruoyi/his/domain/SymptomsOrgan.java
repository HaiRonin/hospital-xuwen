package com.ruoyi.his.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 身体分类与科室对象 his_symptoms_organ
 * 
 * @author whl
 * @date 2020-08-16
 */
public class SymptomsOrgan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Integer id;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 身体部位 */
    @Excel(name = "身体部位")
    private String bodyPart;

    /** 病症 */
    @Excel(name = "病症")
    private String symptoms;

    /** 科室名称 */
    @Excel(name = "科室名称")
    private String organName;

    /** 科室id */
    @Excel(name = "科室id")
    private Integer organId;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setBodyPart(String bodyPart) 
    {
        this.bodyPart = bodyPart;
    }

    public String getBodyPart() 
    {
        return bodyPart;
    }
    public void setSymptoms(String symptoms) 
    {
        this.symptoms = symptoms;
    }

    public String getSymptoms() 
    {
        return symptoms;
    }
    public void setOrganName(String organName) 
    {
        this.organName = organName;
    }

    public String getOrganName() 
    {
        return organName;
    }
    public void setOrganId(Integer organId) 
    {
        this.organId = organId;
    }

    public Integer getOrganId() 
    {
        return organId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sex", getSex())
            .append("bodyPart", getBodyPart())
            .append("symptoms", getSymptoms())
            .append("organName", getOrganName())
            .append("organId", getOrganId())
            .toString();
    }
}
