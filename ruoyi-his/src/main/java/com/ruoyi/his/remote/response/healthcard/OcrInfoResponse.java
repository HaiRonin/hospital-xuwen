package com.ruoyi.his.remote.response.healthcard;

import java.io.Serializable;

/**
 * @Description : 身份证照片OCR接口
 * @Author : yuanhualiang
 * @Date: 2020-09-26 21:15
 */
public class OcrInfoResponse implements Serializable {

    private String name;

    private String id;

    private String gender;

    private String nation;

    private String birth;

    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
