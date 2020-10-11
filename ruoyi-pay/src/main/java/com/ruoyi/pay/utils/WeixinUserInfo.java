package com.ruoyi.pay.utils;

/**
 * 存储微信用户信息的 对象
 * 
 * @author yuanhualiang
 *
 */
public class WeixinUserInfo {

	/**
     * subscribe 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
	 */
	private String subscribe;

	/**
	 * openid 用户的标识，对当前公众号唯一
	 */
	private String openid;

	/**
	 * nickname 用户的昵称
	 */
	private String nickname;

	/**
	 * sex 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	 */
	private String sex;

	/**
	 * city 用户所在城市
	 */
	private String city;

	/**
	 * country 用户所在国家
	 */
	private String country;

	/**
	 * province 用户所在省份
	 */
	private String province;

	/**
	 * language 用户的语言，简体中文为zh_CN
	 */
	private String language;

	/**
	 * headimgurl
	 * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
	 * 。若用户更换头像，原有头像URL将失效。
	 */
	private String headimgurl;

	/**
	 * subscribe_time 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
	 */
	private String subscribe_time;

	/**
	 * unionid 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）
	 */
	private String unionid;

	/**
	 * @return the subscribe
	 */
	public String getSubscribe() {
		return subscribe;
	}

	/**
	 * @param subscribe
	 *            the subscribe to set
	 */
	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}

	/**
	 * @return the openid
	 */
	public String getOpenid() {
		return openid;
	}

	/**
	 * @param openid
	 *            the openid to set
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return EmojiFilterUtils.filterEmoji(nickname);
	}

	/**
	 * @param nickname
	 *            the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province
	 *            the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language
	 *            the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the headimgurl
	 */
	public String getHeadimgurl() {
		return headimgurl;
	}

	/**
	 * @param headimgurl
	 *            the headimgurl to set
	 */
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	/**
	 * @return the subscribe_time
	 */
	public String getSubscribe_time() {
		return subscribe_time;
	}

	/**
	 * @param subscribe_time
	 *            the subscribe_time to set
	 */
	public void setSubscribe_time(String subscribe_time) {
		this.subscribe_time = subscribe_time;
	}

	/**
	 * @return the unionid
	 */
	public String getUnionid() {
		return unionid;
	}

	/**
	 * @param unionid
	 *            the unionid to set
	 */
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

}
