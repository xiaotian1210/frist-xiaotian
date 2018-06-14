package com.shareworx.ezfm.app.problem.service;

import javax.servlet.http.HttpServletRequest;
import com.alibaba.fastjson.JSONObject;
/**
 * 报事处理 service
 * @author lingwei.li
 *
 */
public interface AppProblemService {

	String ID = "appProblemService";
	
	/**
	 * 通过用户id 获取报事列表
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject getAllProblemByUserId(HttpServletRequest reqParam) throws Exception;

	/**
	 * 通过id 获取 报事详情
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject getProblemDetailById(HttpServletRequest reqParam) throws Exception;

	/**
	 * 完成 某个报事
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject saveProblemFinishById(HttpServletRequest reqParam) throws Exception;
}
