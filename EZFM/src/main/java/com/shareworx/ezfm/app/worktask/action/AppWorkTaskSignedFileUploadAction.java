package com.shareworx.ezfm.app.worktask.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.shareworx.ezfm.app.util.AppMessageFormatUtil;
import com.shareworx.ezfm.app.worktask.service.AppWorkTaskSignedFileUploadService;

/**
 * 报修 客户签名 附件上传 action
 * @author lingwei.li
 *
 */
@Controller
@RequestMapping("app/workTaskSignedFileUpload")
public class AppWorkTaskSignedFileUploadAction {

	final static Logger logger = Logger.getLogger(AppWorkTaskSignedFileUploadAction.class);
	@Autowired
	@Qualifier( AppWorkTaskSignedFileUploadService.ID)
	private AppWorkTaskSignedFileUploadService signedFileUploadService;
	
	/**
	 * 保存文件上传信息
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saveUploadInfo", method = RequestMethod.POST)
	public @ResponseBody JSONObject saveUploadInfo(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis();//当前时间戳
		try {
			JSONObject json = signedFileUploadService.saveUploadInfo(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.info(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
		
	}
}
