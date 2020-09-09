package com.ruoyi.his.remote.response.healthcard;

/**
 * Created by ASUS on 2019/1/25.
 */
public class HealthCardRegisterResponse extends HealthCardBaseResponse {


    /**
     * qrCodeText : C220AE414CE6EE581037C311AE24518FCFE19C429BECD478C1A13976260FXXXX:1
     * healthCardId : C220AE414CE6EE581037C311AE24518FCFE19C429BECD478C1A13976260FXXXX
     * phid : 584AF4B1D110B8BB7CBAC978C03B657191DF84863144E436B5CA38C0F0E2XXXX
     * adminExt : {"qr_code":"C220AE414CE6EE581037C311AE24518FCFE19C429BECD478C1A13976260FXXXX:1","ecardId":"C220AE414CE6EE581037C311AE24518FCFE19C429BECD478C1A13976260FXXXX","main_index":"584AF4B1D110B8BB7CBAC978C03B657191DF84863144E436B5CA38C0F0E2XXXX","id_type":"居民身份证","id_number":"身份证号码","name":"姓名","sex":"性别","birthday":"1998-09-08","telephone":"手机号码","nation":"民族","unit":"null","address":"地址"}
     */

    private String qrCodeText;
    private String healthCardId;
    private String phid;
    private String adminExt;

    public String getQrCodeText() {
        return qrCodeText;
    }

    public void setQrCodeText(String qrCodeText) {
        this.qrCodeText = qrCodeText;
    }

    public String getHealthCardId() {
        return healthCardId;
    }

    public void setHealthCardId(String healthCardId) {
        this.healthCardId = healthCardId;
    }

    public String getPhid() {
        return phid;
    }

    public void setPhid(String phid) {
        this.phid = phid;
    }

    public String getAdminExt() {
        return adminExt;
    }

    public void setAdminExt(String adminExt) {
        this.adminExt = adminExt;
    }
}
