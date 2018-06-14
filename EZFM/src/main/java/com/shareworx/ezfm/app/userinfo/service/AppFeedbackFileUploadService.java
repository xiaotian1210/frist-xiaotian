package com.shareworx.ezfm.app.userinfo.service;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

/**
 * 休假备案 附件上传 接口
 * @author lingwei.li
 *
 */
public interface AppFeedbackFileUploadService {

	String ID = "appFeedbackFileUploadService";
	
	/**
	 * 保存 附件上传的信息
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject saveUploadInfo(HttpServletRequest reqParam) throws Exception;
}
