package com.shareworx.ezfm.app.login.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

/**
 * app端 登录认证 接口
 * @author lingwei.li
 *
 */
public interface AppLoginService_BAK {
	String ID="appLoginServiceBAK";
	
	/**
	 * 获取用户登录信息
	 * @param username
	 * @return
	 */
	JSONObject getLoginInfo(HttpServletRequest reqParam, HttpServletResponse response) throws Exception;
	
	/**
	 * 登出
	 * @param username
	 * @return
	 */
	JSONObject logout(String sessionId) throws Exception;
}
