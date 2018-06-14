package com.shareworx.ezfm.app.sign.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.shareworx.ezfm.app.sign.service.AppSignService;
import com.shareworx.ezfm.app.util.AppMessageFormatUtil;

/**
 * 签到、签退 action
 * @author lingwei.li
 *
 */
@Controller
@RequestMapping("app/sign")
public class AppSignAction {

	final static Logger logger = Logger.getLogger(AppSignAction.class);
	
	@Autowired
	@Qualifier(AppSignService.ID)
	private AppSignService service;
	
	/**
	 * 签到
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "sign", method = RequestMethod.POST)
	public @ResponseBody JSONObject sign(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis(); //当前时间戳
		try {
			JSONObject json = service.saveSign(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.error(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
	
	/**
	 * 签退
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value = "signOut", method = RequestMethod.POST)
	public @ResponseBody JSONObject signOut(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis(); //当前时间戳
		try {
			JSONObject json = service.saveSignOut(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.error(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
}
