package com.ruoyi.his.remote.response.healthcard;

import java.io.Serializable;

/**
 * Created by ASUS on 2019/1/25.
 */
public class DynamicQRCodeResponse implements Serializable {

    /**
     * healthCardId : C220AE414CE6EE581037C311AE24518FCFE19C429BECD478C1A13976260FXXXX
     * idType : 01
     * idNumber : 432901198810228888
     * codeType : 0
     */

    private String healthCardId;
    private String idType;
    private String idNumber;
    private String codeType;

    public String getHealthCardId() {
        return healthCardId;
    }

    public void setHealthCardId(String healthCardId) {
        this.healthCardId = healthCardId;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }
}
