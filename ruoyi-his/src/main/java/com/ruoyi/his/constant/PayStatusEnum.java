package com.ruoyi.his.constant;

/**
 * 支付状态枚举类
 * 
 * @author whl
 * @date 2020年8月28日
 */
public enum PayStatusEnum {

	INIT("1","待支付"),
	PAY_SUCCESS("2","支付成功"),
	PAY_FAIL("3","支付失败"),
	ORDER_SUCCESS("4","下单成功"),
	ORDER_FAIL("5","下单失败"),
	REFUND_SUCCESS("6","退款成功"),
	REFUND_FAIL("7","退款失败"),
	REFUND_TODO("8","待退款"),
	;
	private String code;
	private String desc;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	PayStatusEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static PayStatusEnum getPayStatusEnumByCode(String code){
		PayStatusEnum[] values = PayStatusEnum.values();
		for(PayStatusEnum payStatusEnum : values  ){
			if(payStatusEnum.getCode().equals(code)){
				return payStatusEnum;
			}
		}
		return null;
	}

}
