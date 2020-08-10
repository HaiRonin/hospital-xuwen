package com.ruoyi.his.constant;

import com.ruoyi.his.remote.DoregInfoHisServiceHander;

/**
 * 业务异常枚举类
 * 
 * @author whl
 * @date 2020年8月8日
 */
public enum HisBusinessTypeEnum {

	DOREG("doRegIn", "/doreg","预约挂号", DoregInfoHisServiceHander.class),
	DOPAY("dopay", "/dopay","缴费支付",DoregInfoHisServiceHander.class),
	INPATIENTPAYMENT("inpatientpayment", "/inpatientpayment","住院押金补缴",DoregInfoHisServiceHander.class),;
	private String code;
	private String apiUrl;
	private String desc;
	private Class clazz;

	HisBusinessTypeEnum(String code, String apiUrl,String desc,  Class clazz) {
		this.code=code;
		this.apiUrl=apiUrl;
		this.desc=desc;
		this.clazz=clazz;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiCode) {
		this.apiUrl = apiCode;
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

	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}

	public static HisBusinessTypeEnum getCodeByName(String name){
		HisBusinessTypeEnum[] values = HisBusinessTypeEnum.values();
		for(HisBusinessTypeEnum businessTypeEnum : values  ){
			if(businessTypeEnum.code.equals(name)){
				return businessTypeEnum;
			}
		}
		return null;
	}
}
