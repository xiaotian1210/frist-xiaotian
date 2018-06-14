package com.shareworx.ezfm.app.userinfo.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.shareworx.ezfm.app.userinfo.service.AppUserInfoService;
import com.shareworx.ezfm.app.util.AppMessageFormatUtil;
/**
 * 用户信息 中心 action
 * @author lingwei.li
 *
 */
@Controller
@RequestMapping("app/userinfo")
public class AppUserInfoAction {

	final static Logger logger = Logger.getLogger(AppUserInfoAction.class);
	
	@Autowired
	@Qualifier(AppUserInfoService.ID)
	private AppUserInfoService userInfoService;
	
	/**
	 * 用户修改头像
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "headModify", method = RequestMethod.POST)
	public @ResponseBody JSONObject headModify(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis();//当前时间戳
		try {
			JSONObject json = userInfoService.saveHeadModify(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.info(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
	
	/**
	 * 用户修改名称
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "nameModify", method = RequestMethod.POST)
	public @ResponseBody JSONObject nameModify(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis();//当前时间戳
		try {
			JSONObject json = userInfoService.saveNameModify(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.info(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
	
	/**
	 * 用户修改密码
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "passwordModify", method = RequestMethod.POST)
	public @ResponseBody JSONObject passwordModify(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis();//当前时间戳
		try {
			JSONObject json = userInfoService.savePasswordModify(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.info(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
	
	
	/**
	 * 问题反馈
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "problemFeedback", method = RequestMethod.POST)
	public @ResponseBody JSONObject saveProblemFeedback(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis();//当前时间戳
		try {
			JSONObject json = userInfoService.saveProblemFeedback(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.info(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
	
	
}
