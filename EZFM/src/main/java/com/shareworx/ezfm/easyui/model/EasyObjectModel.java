package com.shareworx.ezfm.easyui.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.shareworx.platform.model.IJsonModel;

@SuppressWarnings("serial")
public class EasyObjectModel extends HashMap<String, Object> implements IJsonModel {
	
	public EasyObjectModel() {
		super();
	}

	public EasyObjectModel(Map<? extends String, ? extends Object> m) {
		super(m);
	}
	
	public Set<String> getProperties() {
		return keySet();
	}

	/**
	 * 根据键获取对象值
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String key){
		return (T) get(key);
	}
	
	/**
	 * 设置键值
	 * @param key
	 * @param value
	 */
	public void setAttribute(String key, Object value) {
		put(key, value);
	}
	
	/**
	 * 根据键获取集合对象值
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends EasyObjectModel> List<T> getArrayAttribute(String key) {
		return (List<T>) get(key);
	}
	
	/**
	 * 设置集合对象键值
	 * @param key
	 * @param value
	 */
	public <T> void setArrayAttribute(String key, List<T> value){
		put(key, value);
	}
	
	/**
	 * 对集合对象增加值
	 * @param key
	 * @param value
	 */
	public <T extends EasyObjectModel> void addArrayAttribute(String key, T value){
		if(!containsKey(key) || get(key) == null){
			setArrayAttribute(key, new ArrayList<T>());
		}
		getArrayAttribute(key).add(value);
	}

	@Override
	public String toJSONString() {
		return JSON.toJSONString(this);
	}
}
