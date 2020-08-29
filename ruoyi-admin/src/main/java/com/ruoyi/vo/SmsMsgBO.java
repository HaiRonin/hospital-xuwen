package com.ruoyi.vo;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * 短信发送
 * </p>
 *
 * @author whl
 * @since 2020-08-20
 */

public class SmsMsgBO implements Serializable {

    /***
     * 手机号码
     */
    @NotBlank(message = "手机号码不能为空")
   private String phone;

    /**
     * 短信内容
     */
   private String message;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
