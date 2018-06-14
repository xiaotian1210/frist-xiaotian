package com.shareworx.ezfm.app.quality.service;
import javax.servlet.http.HttpServletRequest;

/**
 * 核查管理 service
 */
import com.alibaba.fastjson.JSONObject;
/**
 * 任务 接口
 * @author lingwei.li
 *
 */
public interface AppInspectTaskService {
	String ID = "appInspectTaskService";
	
	/**
	 * 获取所有核查任务(包括：待办任务、待整改、已办任务、问题跟踪)
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject getAllTask(HttpServletRequest reqParam) throws Exception;
	
	/**
	 * 获取任务详情（包含历史记录）
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject getInspectTaskDetail(HttpServletRequest reqParam) throws Exception;

	/**
	 * 保存完成的任务
	 * @param parseObject
	 * @return
	 */
	JSONObject saveTaskFinish(HttpServletRequest parseObject) throws Exception;

	/**
	 * 保存整改的任务
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject saveTaskRectification(HttpServletRequest reqParam) throws Exception;

	/**
	 * 保存整改完成的任务
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject saveTaskRecFinish(HttpServletRequest reqParam) throws Exception;
	
	/**
	 * 保存整改确认的任务
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject saveTaskRecConfirm(HttpServletRequest reqParam) throws Exception;

	
}
