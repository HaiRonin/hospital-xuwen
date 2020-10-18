package com.ruoyi.his.remote.request;


/***
 * 待交费列表数据
 */
public class ToPayRecipeInfoIn extends BaseRequest{

    /**
     * UserName :
     * id : 1
     * patientNo : 00414474
     * medicareType : 1
     */

    private String UserName;
    private String patientNo;
    private Long medicareType=1L;

    //用户OPENID
    private String openId;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPatientNo() {
        return patientNo;
    }

    public void setPatientNo(String patientNo) {
        this.patientNo = patientNo;
    }

    public Long getMedicareType() {
        return medicareType;
    }

    public void setMedicareType(Long medicareType) {
        this.medicareType = medicareType;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
