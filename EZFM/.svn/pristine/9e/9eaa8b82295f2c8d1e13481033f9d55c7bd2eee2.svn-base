package com.shareworx.ezfm.app.login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.shareworx.ezfm.app.login.service.AppLoginService_BAK;
import com.shareworx.ezfm.app.util.AppMessageFormatUtil;

/**
 * app端  登入登出
 * @author lingwei.li
 *
 */
@Controller
@RequestMapping("app/login/bak")
public class AppLoginAction_BAK {
	
	final static Logger logger = Logger.getLogger(AppLoginAction_BAK.class);
	
	@Autowired
	@Qualifier(AppLoginService_BAK.ID)
	private AppLoginService_BAK service;
	
	
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public @ResponseBody JSONObject appLogin(HttpServletRequest reqParam, HttpServletResponse response) throws Exception{
		long start = System.currentTimeMillis();
		try {
			JSONObject json = service.getLoginInfo(reqParam,response);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.info(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
	
	/**
	 * 系统注销
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public @ResponseBody JSONObject appLogout(String sessionId) throws Exception{
		try {
			JSONObject json = service.logout(sessionId);
			logger.info("logout success");
			return json;
		} catch (Exception e) {
			logger.info("logout error");
			throw(e);
		}
	}
}
