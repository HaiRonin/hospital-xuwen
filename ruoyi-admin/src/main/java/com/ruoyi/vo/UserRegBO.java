package com.ruoyi.vo;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * 用户注册相关
 * </p>
 *
 * @author whl
 * @since 2020-08-20
 */

public class UserRegBO implements Serializable {

    /***
     * 手机号码
     */
    @NotBlank(message = "手机号码不能为空")
   private String phone;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
   private String password;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
   private String verificationCode;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

}
