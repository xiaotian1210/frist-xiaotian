package com.shareworx.ezfm.app.patrol.service;
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
import com.shareworx.ezfm.device.patrol.record.model.YJWYTaskFileModel;
import com.shareworx.ezfm.device.patrol.record.service.YJWYTaskFileBusinessService;
import com.shareworx.ezfm.device.patrol.task.model.YJWYTaskEqModel;
import com.shareworx.ezfm.device.patrol.task.service.YJWYTaskEqBusinessService;

/**
 * 巡检保养 附件上传 接口实现
 * @author lingwei.li
 *
 */
@Service(AppPatrolFileUploadService.ID)
public class AppPatrolFileUploadServiceImpl implements AppPatrolFileUploadService{

	final static Logger logger = Logger.getLogger(AppPatrolFileUploadServiceImpl.class);
	
	@Autowired
	@Qualifier(YJWYTaskFileBusinessService.ID)
	private YJWYTaskFileBusinessService patrolFileService;
	@Autowired
	@Qualifier(YJWYTaskEqBusinessService.ID)
	YJWYTaskEqBusinessService taskeqService;
	
	public void setTaskeqService(YJWYTaskEqBusinessService taskeqService) {
		this.taskeqService = taskeqService;
	}
	
	@SuppressWarnings({ "unchecked" })
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
		
		//构建LeaveFileModel 
		JSONArray  jasonArray = JSONArray.parseArray(fileInfo); 
		YJWYTaskFileModel[] patroFileModels = new YJWYTaskFileModel[jasonArray.size()];
		for (int i = 0; i < jasonArray.size(); i++) {
			Map<String, Object> mapModel = (Map<String, Object>)jasonArray.get(i); 
			YJWYTaskFileModel patroFileModel = new YJWYTaskFileModel();
			patroFileModel.setFile_name(String.valueOf(mapModel.get("fileName")));
			patroFileModel.setFile_path(String.valueOf(mapModel.get("filePath")));
			patroFileModel.setFile_size(String.valueOf(mapModel.get("fileSize")));
			patroFileModel.setFile_type(String.valueOf(mapModel.get("fileType")));
			patroFileModel.setTable_name(YJWYTaskEqModel.META_ID);
			/*String task_id = String.valueOf(mapModel.get("taskId"));
			String eq_id = String.valueOf(mapModel.get("eqId"));
			Query query = Query.from(YJWYTaskEqModel.META_ID);
			query.where(new Condition("task_id", QueryContents.TYPE_EQ, task_id));
			query.and(new Condition("eq_id", QueryContents.TYPE_EQ, eq_id));
			YJWYTaskEqModel[] taskEqModels = taskeqService.query(query);
			String pk_id = taskEqModels[0].getPk_id();
			patroFileModel.setRecord_id(pk_id);*/
			
			patroFileModel.setRecord_id(String.valueOf(mapModel.get("taskEqRecordId")));
			patroFileModel.setFile_stage(Integer.valueOf(mapModel.get("fileStage").toString()));
			patroFileModel.setCreate_time(AppConstant.df_YMDHMS.format(new Date()));
			patroFileModel.setCreate_user(userId);
			patroFileModel.setPk_crop(crop);
			patroFileModels[i] = patroFileModel;
		}
		patroFileModels = patrolFileService.save(patroFileModels);
		return AppJsonMessage.toJsonMsg(0, "success");
	}

}
