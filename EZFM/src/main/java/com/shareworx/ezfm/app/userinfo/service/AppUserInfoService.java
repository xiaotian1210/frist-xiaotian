package com.shareworx.ezfm.app.userinfo.service;

import javax.servlet.http.HttpServletRequest;
import com.alibaba.fastjson.JSONObject;

/**
 * 用户信息中心 接口
 * @author lingwei.li
 *
 */
public interface AppUserInfoService {

	String ID = "appUserInfoService";
	
	/**
	 * 保存 头像修改
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject saveHeadModify(HttpServletRequest reqParam) throws Exception;
	
	/**
	 * 保存 名字修改
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject saveNameModify(HttpServletRequest reqParam) throws Exception;
	
	/**
	 * 保存 密码修改
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject savePasswordModify(HttpServletRequest reqParam) throws Exception;
	
	/**
	 * 保存 用户问题反馈
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject saveProblemFeedback(HttpServletRequest reqParam) throws Exception;
	
	
}

