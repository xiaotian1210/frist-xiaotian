package com.shareworx.ezfm.app.patrol.service;

import javax.servlet.http.HttpServletRequest;
import com.alibaba.fastjson.JSONObject;
/**
 * 巡检保养 service
 * @author lingwei.li
 *
 */
public interface AppPatrolService {

	String ID = "appPatrolService";
	
	/**
	 * 通过用户id 获取可以领取的任务
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject getAllPatrolByUserId(HttpServletRequest reqParam) throws Exception;
	
	/**
	 * 领取 巡检的任务
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject getPatrol(HttpServletRequest reqParam) throws Exception;
	
	/**
	 * 我的 巡检任务 列表
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject getMyPatrolList(HttpServletRequest reqParam) throws Exception;
	
	/**
	 * 提交 我的巡检任务
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject saveSubmitMyPatrol(HttpServletRequest reqParam) throws Exception;
	
	/**
	 * 巡检任务详情
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject getPatrolDetails(HttpServletRequest reqParam) throws Exception;
}
