package com.shareworx.ezfm.app.push.modelVo;

import java.util.Map;

import org.json.JSONObject;

public class AndroidModelVo extends PmModelVo{
	/** 设备号 */
	private String device_token; // 设备号
	/** 当type=customizedcast时, 开发者填写自己的alias。 */
	private String alias; // 当type=customizedcast时, 开发者填写自己的alias。
	/** 当type=customizedcast时，必填，alias的类型, */
	private String alias_type;// Android 当type=customizedcast时，必填，alias的类型,
	/** 通知栏提示文字 */
	private String ticker; // 通知栏提示文字
	/** 通知标题 */
	private String title; // Android 通知标题
	/** 通知文字描述 */
	private String text; // Android 通知文字描述
	/** 为安卓的通知设置额外的键/值 */
	private String extra_field_key;// Android 为安卓的通知设置额外的键/值
	/** 为安卓的通知设置额外的键/值 */
	private String extra_field_value;// Android 为安卓的通知设置额外的键/值
	/***/
	private JSONObject filterJson;

	private Map<String, Object> extra_field_map;// Android 为安卓的通知设置额外的键/值
	
	public Map<String, Object> getExtra_field_map() {
		return extra_field_map;
	}

	public void setExtra_field_map(Map<String, Object> extra_field_map) {
		this.extra_field_map = extra_field_map;
	}

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

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getExtra_field_key() {
		return extra_field_key;
	}

	public void setExtra_field_key(String extra_field_key) {
		this.extra_field_key = extra_field_key;
	}

	public String getExtra_field_value() {
		return extra_field_value;
	}

	public void setExtra_field_value(String extra_field_value) {
		this.extra_field_value = extra_field_value;
	}

	public JSONObject getFilterJson() {
		return filterJson;
	}

	public void setFilterJson(JSONObject filterJson) {
		this.filterJson = filterJson;
	}

}

/*
 * TODO Construct the filter condition: "where": { "and": [ {"tag":"test"},
 * {"tag":"Test"} ] } filterJson 设置说明 JSONObject filterJson = new JSONObject();
 * JSONObject whereJson = new JSONObject(); JSONArray tagArray = new
 * JSONArray(); JSONObject testTag = new JSONObject(); JSONObject TestTag = new
 * JSONObject(); testTag.put("tag", "test"); TestTag.put("tag", "Test");
 * tagArray.put(testTag); tagArray.put(TestTag); whereJson.put("and", tagArray);
 * filterJson.put("where", whereJson);
 * System.out.println(filterJson.toString());
 */