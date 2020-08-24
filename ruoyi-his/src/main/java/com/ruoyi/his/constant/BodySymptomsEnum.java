package com.ruoyi.his.constant;

/**
 * 业务异常枚举类
 * 
 * @author 刘涛
 * @date 2018年8月8日
 */
public enum BodySymptomsEnum {

	ERROR("male_adult", "m","男-大人"),
	TRADE_ERROR("female_adult", "w","女-大人"),
	TRADE_FREQUENTLY("male_children", "h","男-小孩"),
	DATA_CENTER_ERROR("female_children", "s","女-小孩");
	private String name;
	private String code;
	private String desc;

	BodySymptomsEnum(String name, String code, String desc) {
		this.name = name;
		this.code = code;
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return this.code;
	}

	public String getDesc() {
		return this.desc;
	}

	public static String getCodeByName(String name){
		BodySymptomsEnum[] values = BodySymptomsEnum.values();
		for(BodySymptomsEnum bodySymptomsEnum : values  ){
			if(bodySymptomsEnum.getName().equals(name)){
				return bodySymptomsEnum.getCode();
			}
		}
		return null;
	}
}
