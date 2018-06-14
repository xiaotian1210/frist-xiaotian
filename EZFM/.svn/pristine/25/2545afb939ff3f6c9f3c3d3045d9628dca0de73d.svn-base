package com.shareworx.ezfm.app.worktask.service;

import javax.servlet.http.HttpServletRequest;
import com.alibaba.fastjson.JSONObject;

/**
 * 工单处理 service 层
 * @author lingwei.li
 *
 */
public interface AppWorkTaskService {

	String ID = "AppWorkTaskService";
	
	/**
	 * 更新 抢单池
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject getAllWorkTask(HttpServletRequest reqParam) throws Exception;

	/**
	 * 抢 工单
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject getWorkTask(HttpServletRequest reqParam) throws Exception;
	
	/**
	 * 工单 详情
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject getWorkTaskDetails(HttpServletRequest reqParam) throws Exception;

	/**
	 * 我的 工单 列表
	 * @param reqParam
	 * @return
	 */
	JSONObject getMyWorkTaskList(HttpServletRequest reqParam) throws Exception;
	
	/**
	 * 接受我的工单
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject saveAcceptMyWorkTask(HttpServletRequest reqParam) throws Exception;
	
	/**
	 * 拒绝我的工单
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject saveRejectMyWorkTask(HttpServletRequest reqParam) throws Exception;
	
	/**
	 * 派发我的工单
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject saveDistributeMyWorkTask(HttpServletRequest reqParam) throws Exception;
	
	/**
	 * 完成我的工单
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject saveFinishMyWorkTask(HttpServletRequest reqParam) throws Exception;
	
	/**
	 * 取消我的工单
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject saveCancelMyWorkTask(HttpServletRequest reqParam) throws Exception;

	/**
	 * 新增工单
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject addWorkTask(HttpServletRequest reqParam) throws Exception;
	
	/**
	 * 获取发出 的工单
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject getSendWorkTask(HttpServletRequest reqParam) throws Exception;
	
	/**
	 * 获取超时工单
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject getTimeOutWorkTask(HttpServletRequest reqParam) throws Exception;
	
}
