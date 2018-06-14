package com.shareworx.ezfm.app.basedata.service;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

public interface AppBaseDataDownService {
	String ID = "appBaseDataDownService";
	/**
	 * 基础数据下载
	 * @param json
	 * @return
	 * @throws Exception
	 */
	JSONObject doDownData(HttpServletRequest reqParam) throws Exception;

	/**
	 * 根据用户 获取用户的基础数据
	 * @param reqParam
	 * @return
	 */
	JSONObject doForUserData(HttpServletRequest reqParam) throws Exception;
}
