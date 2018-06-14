package com.shareworx.ezfm.app.worktask.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.shareworx.ezfm.app.util.AppMessageFormatUtil;
import com.shareworx.ezfm.app.worktask.service.AppWorkTaskService;

/**
 * 工单管理 action
 * @author lingwei.li
 *
 */
@Controller
@RequestMapping("app/workTask")
public class AppWorkTaskAction {

	final static Logger logger = Logger.getLogger(AppWorkTaskAction.class);
	@Autowired
	@Qualifier(AppWorkTaskService.ID)
	private AppWorkTaskService service;
	
	/**
	 * 抢单池
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "workTaskPool", method = RequestMethod.GET)
	public @ResponseBody JSONObject workTaskPool(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis(); //当前时间戳
		try {
			JSONObject json = service.getAllWorkTask(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.error(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
	
	/**
	 * 抢 工单
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getWorkTask", method = RequestMethod.POST)
	public @ResponseBody JSONObject getWorkTask(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis(); //当前时间戳
		try {
			JSONObject json = service.getWorkTask(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.error(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
	
	/**
	 * 新增 工单
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addWorkTask", method = RequestMethod.POST)
	public @ResponseBody JSONObject addWorkTask(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis(); //当前时间戳
		try {
			JSONObject json = service.addWorkTask(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.error(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
	
	/**
	 * 我发出的 工单
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getSendWorkTask", method = RequestMethod.GET)
	public @ResponseBody JSONObject getSendWorkTask(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis(); //当前时间戳
		try {
			JSONObject json = service.getSendWorkTask(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.error(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
	
	/**
	 * 工单详情
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "workTaskDetails", method = RequestMethod.GET)
	public @ResponseBody JSONObject workTaskDetails(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis(); //当前时间戳
		try {
			JSONObject json = service.getWorkTaskDetails(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.error(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
	
	/**
	 * 我的 工单 列表
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "myWorkTaskList", method = RequestMethod.GET)
	public @ResponseBody JSONObject myWorkTaskList(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis(); //当前时间戳
		try {
			JSONObject json = service.getMyWorkTaskList(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.error(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
	
	/**
	 * 接受我的 工单
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "acceptMyWorkTask", method = RequestMethod.POST)
	public @ResponseBody JSONObject acceptMyWorkTask(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis(); //当前时间戳
		try {
			JSONObject json = service.saveAcceptMyWorkTask(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.error(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
	
	/**
	 * 拒绝我的 工单
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "rejectMyWorkTask", method = RequestMethod.POST)
	public @ResponseBody JSONObject rejectMyWorkTask(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis(); //当前时间戳
		try {
			JSONObject json = service.saveRejectMyWorkTask(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.error(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
	
	/**
	 * 派发我的 工单
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "distributeMyWorkTask", method = RequestMethod.POST)
	public @ResponseBody JSONObject distributeMyWorkTask(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis(); //当前时间戳
		try {
			JSONObject json = service.saveDistributeMyWorkTask(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.error(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
	
	/**
	 * 完成我的 工单
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "finishMyWorkTask", method = RequestMethod.POST)
	public @ResponseBody JSONObject finishMyWorkTask(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis(); //当前时间戳
		try {
			JSONObject json = service.saveFinishMyWorkTask(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.error(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
	
	/**
	 * 取消我的 工单
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "cancelMyWorkTask", method = RequestMethod.POST)
	public @ResponseBody JSONObject cancelMyWorkTask(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis(); //当前时间戳
		try {
			JSONObject json = service.saveCancelMyWorkTask(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.error(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
	/**
	 * 片区主管获取的超时工单列表
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getTimeOutWorkTask", method = RequestMethod.GET)
	public @ResponseBody JSONObject getTimeOutWorkTask(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis(); //当前时间戳
		try {
			JSONObject json = service.getTimeOutWorkTask(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.error(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
}
