package com.ruoyi.common.enums;

/**
 * 业务异常枚举类
 * 
 * @author whl
 * @date 2020年8月8日
 */
public enum HisBusinessType {

	DOREG("doreg","预约挂号"),
	DOPAY("dopay","缴费支付"),
	INPATIENTPAYMENT("inpatientpayment","住院押金补缴"),
	LEAVEHOSPAY("leavehospay","离院结算"),;
	private String key;
	private String desc;

	HisBusinessType(String key, String desc) {
		this.key=key;
		this.desc=desc;
	}

	public String getKey() {
		return key;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static HisBusinessType getTypeByKey(String key){
		HisBusinessType[] values = HisBusinessType.values();
		for(HisBusinessType businessTypeEnum : values  ){
			if(businessTypeEnum.key.equals(key)){
				return businessTypeEnum;
			}
		}
		return null;
	}
}
