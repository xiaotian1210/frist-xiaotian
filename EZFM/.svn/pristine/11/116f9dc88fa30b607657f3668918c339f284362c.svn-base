package com.shareworx.ezfm.app.quality.service;

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
import com.shareworx.ezfm.quality.file.model.QualityFileModel;
import com.shareworx.ezfm.quality.file.service.QualityFileBusinessService;
import com.shareworx.ezfm.quality.proinspect.inspect.insprecord.model.InspectRecordModel;

/**
 * 我的任务 附件上传 serviceImpl
 * @author lingwei.li
 *
 */
@Service(AppInspectFileUploadService.ID)
public class AppInspectFileUploadServiceImpl implements AppInspectFileUploadService{

	final static Logger logger = Logger.getLogger(AppInspectFileUploadServiceImpl.class);
	
	@Autowired
	@Qualifier(QualityFileBusinessService.ID)
	private QualityFileBusinessService qualityFileService;
	
	/**
	 * 保存 附件信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject saveUploadInfo(HttpServletRequest reqParam) throws Exception {
		
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
		QualityFileModel[] qualityFileList = new QualityFileModel[jasonArray.size()];
		for (int i = 0; i < jasonArray.size(); i++) {
			Map<String, Object> mapModel = (Map<String, Object>)jasonArray.get(i); 
			QualityFileModel qualityFileModel = new QualityFileModel();
			qualityFileModel.setFile_name(String.valueOf(mapModel.get("fileName")));
			qualityFileModel.setFile_path(String.valueOf(mapModel.get("filePath")));
			qualityFileModel.setFile_size(String.valueOf(mapModel.get("fileSize")));
			qualityFileModel.setFile_type(String.valueOf(mapModel.get("fileType")));
			qualityFileModel.setTable_name(InspectRecordModel.META_ID);
			qualityFileModel.setRecord_id(String.valueOf(mapModel.get("inspectrecordId")));
			qualityFileModel.setCreate_time(AppConstant.df_YMDHMS.format(new Date()));
			qualityFileModel.setCreate_user(userId);
			qualityFileModel.setPk_crop(crop);
			qualityFileList[i] = qualityFileModel;
		}
		System.out.println(qualityFileList);
		qualityFileList = qualityFileService.save(qualityFileList);
		return AppJsonMessage.toJsonMsg(0, "success");
	}

}
