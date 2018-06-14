package com.shareworx.ezfm.app.problem.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shareworx.ezfm.app.constant.AppConstant;
import com.shareworx.ezfm.app.util.AppEmptyUtils;
import com.shareworx.ezfm.app.util.AppJsonMessage;
import com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsModel;
import com.shareworx.ezfm.problem.file.model.ProblemFileModel;
import com.shareworx.ezfm.problem.file.service.ProblemFileBusinessService;
import com.shareworx.ezfm.problem.record.model.YJWYProblemRecordModel;
/**
 * 报事、报修 附件上传接口实现
 * @author lingwei.li
 *
 */
@Service(AppProblemFileUploadService.ID)
public class AppProblemFileUploadServiceImpl implements AppProblemFileUploadService{

	final static Logger logger = Logger.getLogger(AppProblemFileUploadServiceImpl.class);
	
	@Autowired
	@Qualifier(ProblemFileBusinessService.ID)
	private ProblemFileBusinessService problemFileService;
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject saveUploadInfo(HttpServletRequest reqParam) throws Exception {
		logger.info("AppProblemFileUploadServiceImpl/saveUploadInfo");
		
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
		
		//构建ProblemFileModel 
		JSONArray  jasonArray = JSONArray.parseArray(fileInfo); 
		ProblemFileModel[] problemModels = new ProblemFileModel[jasonArray.size()];
		for (int i = 0; i < jasonArray.size(); i++) {
			Map<String, Object> mapModel = (Map<String, Object>)jasonArray.get(i); 
			ProblemFileModel problemModel = new ProblemFileModel();
			problemModel.setFile_name(String.valueOf(mapModel.get("fileName")));
			problemModel.setFile_path(String.valueOf(mapModel.get("filePath")));
			problemModel.setFile_size(String.valueOf(mapModel.get("fileSize")));
			problemModel.setFile_type(String.valueOf(mapModel.get("fileType")));
//			problemModel.setTable_name(YJWYProblemRecordModel.META_ID);
			//保存到报事
			problemModel.setTable_name(YJWYProblemDetailsModel.META_ID);
			problemModel.setRecord_id(String.valueOf(mapModel.get("problemRecordId")));
			problemModel.setCreate_time(AppConstant.df_YMDHMS.format(new Date()));
			problemModel.setCreate_user(userId);
			problemModel.setPk_crop(crop);
			problemModels[i] = problemModel;
		}
		problemModels = problemFileService.save(problemModels);
		return AppJsonMessage.toJsonMsg(0, "success");
	}

}
