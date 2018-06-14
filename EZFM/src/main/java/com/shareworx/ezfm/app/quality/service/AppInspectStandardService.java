package com.shareworx.ezfm.app.quality.service;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
/**
 * 标准 service
 * @author lingwei.li
 *
 */
public interface AppInspectStandardService {
	String ID="appInspectStandardService";
	/**
	 * 根据条件，获取所有标准列表
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject getAllStandard(HttpServletRequest reqParam) throws Exception;

	/**
	 * 根据条件 保存标准完成
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject saveStandardFinish(HttpServletRequest reqParam) throws Exception;

	/**
	 * 根据条件 保存标准整改
	 * @param reqParam
	 * @return
	 */
	JSONObject saveStandardRectification(HttpServletRequest reqParam) throws Exception;
	
}
