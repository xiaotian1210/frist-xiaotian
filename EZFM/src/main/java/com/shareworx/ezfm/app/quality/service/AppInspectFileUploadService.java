package com.shareworx.ezfm.app.quality.service;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

/**
 * 我的任务 附件上传 service
 * @author lingwei.li
 *
 */

public interface AppInspectFileUploadService {
	
	String ID = "appInspectFileUploadService";
	
	/**
	 * 保存 附件上传的信息
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject saveUploadInfo(HttpServletRequest reqParam) throws Exception;
}
