package com.ruoyi.vo;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wu123
 * @since 2019-01-26
 */
public class SymptomsOrganExd implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 男或女：W-女；M-男
     */
    private String sex;
    /**
     * 身体部位
     */
    private String bodyPart;
    /***
     * 病症
     */
    private String symptoms;


    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
    }
}
