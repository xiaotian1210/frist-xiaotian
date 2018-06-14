package com.shareworx.ezfm.app.leave.service;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

/**
 * 休假审批 service
 * @author lingwei.li
 *
 */
public interface AppLeaveApprovalService {

	String ID = "appLeaveApprovalService";
	
	/**
	 * 通过userId获取 审批列表
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject getAllLeaveApprovalByUserId(HttpServletRequest reqParam) throws Exception;
	
	/**
	 * 通过某一个休假审批 
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject saveLeaveApprovalPass(HttpServletRequest reqParam) throws Exception;
	
	/**
	 * 拒绝某一个休假审批
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject saveLeaveApprovalReject(HttpServletRequest reqParam) throws Exception;
}
