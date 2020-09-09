package com.ruoyi.his.remote.response.healthcard;

/**
 * Created by ASUS on 2019/1/25.
 */
public class HealthCardTokenResponse extends HealthCardBaseResponse {


    /**
     * appToken : 9A60D3D561E4ADBA89ACD9FBF2FB8D2C
     * expiresIn : 7200
     */

    private String appToken;
    private int expiresIn;

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
