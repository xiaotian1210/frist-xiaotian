/*
 * Copyright DMS Jiazht. All rights reserved.
 * 
 * The code was generated by tools.
 * if you want to modify it, regenerate is Deprecated.
 * if you want to regenerate the code strongly, please backup the code first.
 */
package com.shareworx.ezfm.baseinfo.user.model;

import com.shareworx.platform.model.EnumModel;
import java.util.Arrays;

/**
 * 性别参照
 * @author qiang.gu
 *
 */
@SuppressWarnings("serial")
public class YJWYSexEnum extends EnumModel {

	public final static int INDEX_0 = 0;
	
	public final static int INDEX_1 = 1;
	
	/**
	 * 枚举键
	 */
	public final static int[] KEYS = {0, 1};
	
	/**
	 * 枚举值
	 */
	public final static String[] NAMES = {"男", "女"};

	/**
	 * 获取键
	 * @param index
	 * @return
	 */
	public int getKey(int index){
		return KEYS[index];
	}
	
	/**
	 * 获取值
	 * @param index
	 * @return
	 */
	public String getName(int index){
		return NAMES[index];
	}
	
	/**
	 * 根据键获取值
	 * @param key
	 * @return
	 */
	public String getNameByKey(int key){
		int index = Arrays.asList(KEYS).indexOf(key);
		return NAMES[index];
	}
	
	/**
	 * 根据值获取键
	 * @param name
	 * @return
	 */
	public int getKeyByName(String name){
		int index = Arrays.asList(NAMES).indexOf(name);
		return KEYS[index];
	}
}