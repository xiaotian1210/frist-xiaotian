package com.shareworx.ezfm.app.problem.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.shareworx.ezfm.app.problem.service.AppProblemService;
import com.shareworx.ezfm.app.util.AppMessageFormatUtil;

/**
 * 报事管理 action
 * @author lingwei.li
 *
 */
@Controller
@RequestMapping("app/problem")
public class AppProblemAction {

	final static Logger logger = Logger.getLogger(AppProblemAction.class);
	@Autowired
	@Qualifier(AppProblemService.ID)
	private AppProblemService problemService;
	
	/**
	 * 获取 报事列表
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "problemList", method = RequestMethod.GET)
	public @ResponseBody JSONObject problemList(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis();//当前时间戳
		try {
			JSONObject json = problemService.getAllProblemByUserId(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.info(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
	
	/**
	 * 根据id 获取报事详情
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "problemDetail", method = RequestMethod.GET)
	public @ResponseBody JSONObject problemDetail(HttpServletRequest reqParam) throws Exception{
		
		long start = System.currentTimeMillis();//当前时间戳
		try {
			JSONObject json = problemService.getProblemDetailById(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.info(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
	
	/**
	 * 完成某个 报事
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "problemFinish", method = RequestMethod.POST)
	public @ResponseBody JSONObject problemFinish(HttpServletRequest reqParam) throws Exception{
		
		long start = System.currentTimeMillis();//当前时间戳
		try {
			JSONObject json = problemService.saveProblemFinishById(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.info(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
}
