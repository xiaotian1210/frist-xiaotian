package com.shareworx.ezfm.app.patrol.service;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
/**
 * 巡检保养 附件上传 接口
 * @author lingwei.li
 *
 */
public interface AppPatrolFileUploadService {
	
	String ID= "appPatrolFileUploadService";
	
	/**
	 * 保存 附件上传的信息
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject saveUploadInfo(HttpServletRequest reqParam) throws Exception;
}
