package com.ruoyi.his.constant;

import com.ruoyi.his.remote.DopayInfoHisServiceHander;
import com.ruoyi.his.remote.DoregInfoHisServiceHander;
import com.ruoyi.his.remote.InPatientPaymentHisServiceHander;
import com.ruoyi.his.remote.LeaveHosPayHisServiceHander;

/**
 * 业务异常枚举类
 * 
 * @author whl
 * @date 2020年8月8日
 */
public enum HisBusinessTypeEnum {

	DOREG("doreg","doRegIn", "/doReg","预约挂号",true, DoregInfoHisServiceHander.class),
	DOPAY("dopay","doRegIn", "/doPay","缴费支付",true, DopayInfoHisServiceHander.class),
	INPATIENTPAYMENT("inpatientpayment","doRegIn", "/inPatientPayment","住院押金补缴",false, InPatientPaymentHisServiceHander.class),
	LEAVEHOSPAY("leavehospay","doRegIn", "/leaveHosPay","离院结算",true, LeaveHosPayHisServiceHander.class),;
	private String key;
	private String code;
	private String apiUrl;
	private String desc;
	private boolean autoRefund;
	private Class clazz;

	HisBusinessTypeEnum(String key, String code, String apiUrl,String desc, boolean autoRefund, Class clazz) {
		this.key=key;
		this.code=code;
		this.apiUrl=apiUrl;
		this.desc=desc;
		this.autoRefund=autoRefund;
		this.clazz=clazz;
	}

	public String getKey() {
		return key;
	}

	public String getCode() {
		return code;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Class getClazz() {
		return clazz;
	}

	public boolean isAutoRefund() {
		return autoRefund;
	}

	public static HisBusinessTypeEnum getTypeByKey(String key){
		HisBusinessTypeEnum[] values = HisBusinessTypeEnum.values();
		for(HisBusinessTypeEnum businessTypeEnum : values  ){
			if(businessTypeEnum.key.equals(key)){
				return businessTypeEnum;
			}
		}
		return null;
	}
}
