package com.shareworx.ezfm.app.quality.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.shareworx.ezfm.app.quality.service.AppInspectStandardService;
import com.shareworx.ezfm.app.util.AppMessageFormatUtil;

/**
 * 核查标准 action
 * @author yuting.wang
 *
 */
@Controller
@RequestMapping("app/inspectStandard")
public class AppInspectStandardAction {
	final static Logger logger = Logger.getLogger(AppInspectStandardAction.class);
	
	@Autowired
	@Qualifier(AppInspectStandardService.ID)
	private AppInspectStandardService inspectStandardService;
	
	
	/**
	 * 获取标准列表接口
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "standardList", method = RequestMethod.GET)
	public @ResponseBody JSONObject standardList(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis();//当前时间戳
		try {
			JSONObject json = inspectStandardService.getAllStandard(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.info(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
		
	}
	
	/**
	 * 保存标准完成的接口
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "standardFinish", method = RequestMethod.POST)
	public @ResponseBody JSONObject standardFinish(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis();//当前时间戳
		try {
			JSONObject json = inspectStandardService.saveStandardFinish(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.info(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
		
	}
	
	/**
	 * 保存标准整改的接口
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "standardRectification", method = RequestMethod.POST)
	public @ResponseBody JSONObject standardRectification(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis();//当前时间戳
		try {
			JSONObject json = inspectStandardService.saveStandardRectification(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.info(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
		
	}

}
