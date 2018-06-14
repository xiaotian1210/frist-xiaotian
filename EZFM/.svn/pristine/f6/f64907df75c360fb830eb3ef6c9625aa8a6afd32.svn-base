package com.shareworx.ezfm.app.leave.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.shareworx.ezfm.app.leave.service.AppLeaveService;
import com.shareworx.ezfm.app.util.AppMessageFormatUtil;
/**
 * 休假备案 action
 * @author lingwei.li
 *
 */
@Controller
@RequestMapping("app/leave")
public class AppLeaveAction {

	final static Logger logger = Logger.getLogger(AppLeaveAction.class);
	
	@Autowired
	@Qualifier(AppLeaveService.ID)
	private AppLeaveService leaveService;
	
	/**
	 * 通过用户id 查询用户休假列表
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "leaveList", method = RequestMethod.GET)
	public @ResponseBody JSONObject leaveList(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis();//当前时间戳
		try {
			JSONObject json = leaveService.getAllLeaveByUserId(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.info(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
	
	/**
	 * 添加新的 休假
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addLeave", method = RequestMethod.POST)
	public @ResponseBody JSONObject addLeave(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis();//当前时间戳
		try {
			JSONObject json = leaveService.addLeave(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.info(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
	
}
