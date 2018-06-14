package com.shareworx.ezfm.app.worktask.service;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

/**
 * 报事、报修 附件 接口
 * @author lingwei.li
 *
 */
public interface AppWorkTaskSignedFileUploadService {

	String ID = "appWorkTaskSignedFileUploadService";
	
	/**
	 * 保存 附件上传的信息
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	JSONObject saveUploadInfo(HttpServletRequest reqParam) throws Exception;
}
