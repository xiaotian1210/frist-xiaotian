package com.shareworx.ezfm.app.userinfo.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shareworx.ezfm.pub.feedback.model.YJWYFeedbackModel;
import com.shareworx.ezfm.app.constant.AppConstant;
import com.shareworx.ezfm.app.util.AppEmptyUtils;
import com.shareworx.ezfm.app.util.AppJsonMessage;
import com.shareworx.ezfm.system.file.model.SystemFileModel;
import com.shareworx.ezfm.system.file.service.SystemFileBusinessService;

/**
 * 休假备案 附件上传 业务实现
 * @author lingwei.li
 *
 */
@Service(AppFeedbackFileUploadService.ID)
public class AppFeedbackFileUploadServiceImpl implements AppFeedbackFileUploadService{

	final static Logger logger = Logger.getLogger(AppFeedbackFileUploadServiceImpl.class);
	
	@Autowired
	@Qualifier(SystemFileBusinessService.ID)
	private SystemFileBusinessService systemFileBusinessService;
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject saveUploadInfo(HttpServletRequest reqParam) throws Exception {
		logger.info("AppFeedbackFileUploadServiceImpl/saveUploadInfo");
		
		String fileInfo = reqParam.getParameter("fileInfo");//岗位
		String userId = reqParam.getParameter("userId");//项目id
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String crop = reqParam.getParameter("crop");
		
		//判断必填字段
		if (AppEmptyUtils.isEmpty(fileInfo) 
				|| AppEmptyUtils.isEmpty(userId) 
				|| AppEmptyUtils.isEmpty(mobilePlatform) 
				|| AppEmptyUtils.isEmpty(crop)) {
			logger.info("result:error parameter");
			String msg = "参数有误";
			return AppJsonMessage.toJsonMsgFalse(1,msg);
		}
		
		//构建 QualityFileModel 
		JSONArray  jasonArray = JSONArray.parseArray(fileInfo); 
		SystemFileModel[] systemFileModels = new SystemFileModel[jasonArray.size()];
		for (int i = 0; i < jasonArray.size(); i++) {
			Map<String, Object> mapModel = (Map<String, Object>)jasonArray.get(i); 
			SystemFileModel systemFileModel = new SystemFileModel();
			systemFileModel.setFile_name(String.valueOf(mapModel.get("fileName")));
			systemFileModel.setFile_path(String.valueOf(mapModel.get("filePath")));
			systemFileModel.setFile_size(String.valueOf(mapModel.get("fileSize")));
			systemFileModel.setFile_type(String.valueOf(mapModel.get("fileType")));
			systemFileModel.setTable_name(YJWYFeedbackModel.META_ID);
			systemFileModel.setRecord_id(String.valueOf(mapModel.get("feedbackId")));
			systemFileModel.setCreate_time(AppConstant.df_YMDHMS.format(new Date()));
			systemFileModel.setCreate_user(userId);
			systemFileModel.setPk_crop(crop);
			systemFileModels[i] = systemFileModel;
		}
		systemFileModels = systemFileBusinessService.save(systemFileModels);
		return AppJsonMessage.toJsonMsg(0, "success");
	}

}
