package com.shareworx.ezfm.app.patrol.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.shareworx.ezfm.app.patrol.service.AppPatrolService;
import com.shareworx.ezfm.app.util.AppMessageFormatUtil;

/**
 * 巡检保养 action
 * @author lingwei.li
 *
 */
@Controller
@RequestMapping("app/patrol")
public class AppPatrolAction {

	final static Logger logger = Logger.getLogger(AppPatrolAction.class);
	@Autowired
	@Qualifier(AppPatrolService.ID)
	private AppPatrolService patrolService;
	
	/**
	 * 巡检任务 列表 根据 组 来查看的
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "patrolList", method = RequestMethod.GET)
	public @ResponseBody JSONObject patrolList(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis();//当前时间戳
		try {
			JSONObject json = patrolService.getAllPatrolByUserId(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.info(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
	
	/**
	 * 领取 巡检任务
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getPatrol", method = RequestMethod.POST)
	public @ResponseBody JSONObject getPatrol(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis();//当前时间戳
		try {
			JSONObject json = patrolService.getPatrol(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.info(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
	
	/**
	 * 我的 巡检任务列表
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getMyPatrolList", method = RequestMethod.GET)
	public @ResponseBody JSONObject getMyPatrolList(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis();//当前时间戳
		try {
			JSONObject json = patrolService.getMyPatrolList(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.info(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
	
	/**
	 * 提交我的 巡检任务
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "submitMyPatrol", method = RequestMethod.POST)
	public @ResponseBody JSONObject submitMyPatrol(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis();//当前时间戳
		try {
			JSONObject json = patrolService.saveSubmitMyPatrol(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.info(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
	
	/**
	 * 巡检任务详情
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "patrolDetails", method = RequestMethod.GET)
	public @ResponseBody JSONObject patrolDetails(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis();//当前时间戳
		try {
			JSONObject json = patrolService.getPatrolDetails(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.info(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
}
