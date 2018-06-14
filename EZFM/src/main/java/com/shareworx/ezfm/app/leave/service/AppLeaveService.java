package com.shareworx.ezfm.app.leave.service;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

/**
 * 休假备案 service
 * @author lingwei.li
 *
 */
public interface AppLeaveService {

	String ID = "appLeaveService";
	
	/**
	 * 根据userID 获取休假列表
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject getAllLeaveByUserId(HttpServletRequest reqParam) throws Exception;
	
	/**
	 * 新增休假
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject addLeave(HttpServletRequest reqParam) throws Exception;
}
