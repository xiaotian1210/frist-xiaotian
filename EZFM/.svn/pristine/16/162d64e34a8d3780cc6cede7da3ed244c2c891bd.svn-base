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
import com.shareworx.ezfm.app.leave.service.AppLeaveApprovalService;
import com.shareworx.ezfm.app.util.AppMessageFormatUtil;

/**
 * 休假 审批 action
 * @author lingwei.li
 *
 */
@Controller
@RequestMapping("app/leaveApproval")
public class AppLeaveApprovalAction {

	final static Logger logger = Logger.getLogger(AppLeaveApprovalAction.class);
	
	@Autowired
	@Qualifier(AppLeaveApprovalService.ID)
	private AppLeaveApprovalService leaveApprovalService;
	
	/**
	 * 休假审批 列表
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "leaveApprovalList", method = RequestMethod.GET)
	public @ResponseBody JSONObject leaveApproveList(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis();//当前时间戳
		try {
			JSONObject json = leaveApprovalService.getAllLeaveApprovalByUserId(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.info(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
	
	/**
	 * 休假审批 通过
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "leaveApprovalPass", method = RequestMethod.POST)
	public @ResponseBody JSONObject leaveApprovePass(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis();//当前时间戳
		try {
			JSONObject json = leaveApprovalService.saveLeaveApprovalPass(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.info(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
	
	/**
	 * 休假审批 拒绝
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "leaveApprovalReject", method = RequestMethod.POST)
	public @ResponseBody JSONObject leaveApproveReject(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis();//当前时间戳
		try {
			JSONObject json = leaveApprovalService.saveLeaveApprovalReject(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.info(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
}
