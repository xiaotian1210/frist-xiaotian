package com.shareworx.ezfm.app.push.modelVo;

public class IOSModelVo extends PmModelVo{
	
	private String device_token; // 设备号
	/** 当type=customizedcast时, 开发者填写自己的alias。 */
	private String alias; 
	/** 当type=customizedcast时，必填，alias的类型, */
	private String alias_type;
	
	private String alert; // ios 必填项

	private String badge; // ios 必填项

	private String sound; // ios 必填项

	private String content_available; // ios 必填项

	private String customized_field_key; // ios 必填项

	private String customized_field_value;// ios 必填项

	public String getDevice_token() {
		return device_token;
	}

	public void setDevice_token(String device_token) {
		this.device_token = device_token;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAlias_type() {
		return alias_type;
	}

	public void setAlias_type(String alias_type) {
		this.alias_type = alias_type;
	}

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public String getBadge() {
		return badge;
	}

	public void setBadge(String badge) {
		this.badge = badge;
	}

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	public String getContent_available() {
		return content_available;
	}

	public void setContent_available(String content_available) {
		this.content_available = content_available;
	}

	public String getCustomized_field_key() {
		return customized_field_key;
	}

	public void setCustomized_field_key(String customized_field_key) {
		this.customized_field_key = customized_field_key;
	}

	public String getCustomized_field_value() {
		return customized_field_value;
	}

	public void setCustomized_field_value(String customized_field_value) {
		this.customized_field_value = customized_field_value;
	}
	
	
}
