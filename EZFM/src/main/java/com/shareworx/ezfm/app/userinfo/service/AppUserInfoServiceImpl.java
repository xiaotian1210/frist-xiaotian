package com.shareworx.ezfm.app.userinfo.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.shareworx.platform.persist.service.BaseDomainService;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.app.constant.AppConstant;
import com.shareworx.ezfm.app.util.AppEmptyUtils;
import com.shareworx.ezfm.app.util.AppJsonMessage;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserDomainService;
import com.shareworx.ezfm.pub.feedback.model.YJWYFeedbackModel;
import com.shareworx.ezfm.system.file.model.SystemFileModel;
import com.shareworx.ezfm.system.file.service.SystemFileBusinessService;
/**
 * 用户信息中心 接口实现
 * @author lingwei.li
 *
 */
@Service(AppUserInfoService.ID)
public class AppUserInfoServiceImpl implements AppUserInfoService{

	final static Logger logger = Logger.getLogger(AppUserInfoServiceImpl.class);
	
	@Autowired
	@Qualifier(YJWYUserDomainService.ID)
	private YJWYUserDomainService userService;
	
	@Autowired
	@Qualifier(SystemFileBusinessService.ID)
	private SystemFileBusinessService systemFileBusinessService;
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	/**
	 * 保存用户修改的头像
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@Override
	public JSONObject saveHeadModify(HttpServletRequest reqParam) throws Exception {
		logger.info("AppUserInfoServiceImpl/saveHeadModify");
		
		String userId = reqParam.getParameter("userId");
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String fileInfo = reqParam.getParameter("fileInfo");
		String crop = reqParam.getParameter("crop");
		
		//判断参数是否正常
		if (AppEmptyUtils.isEmpty(userId) 
				|| AppEmptyUtils.isEmpty(mobilePlatform) 
				|| AppEmptyUtils.isEmpty(fileInfo) 
				|| AppEmptyUtils.isEmpty(crop)) {
			logger.info("result:error parameter");
			String msg = "参数有误";
			return AppJsonMessage.toJsonMsgFalse(1,msg);
		}
		
		
		//将string 转 为 json对象
		JSONObject jsonObject = JSONObject.parseObject(fileInfo);
		Map<String, Object> mapModel = (Map<String, Object>)jsonObject;
		String fUserId = String.valueOf(mapModel.get("userId"));
		String filePath = String.valueOf(mapModel.get("filePath"));
		String fileName = String.valueOf(mapModel.get("fileName"));
		String fileSize = String.valueOf(mapModel.get("fileSize"));
		String fileType = String.valueOf(mapModel.get("fileType"));
		//判断参数是否正常
		if (AppEmptyUtils.isEmpty(fUserId) 
				|| AppEmptyUtils.isEmpty(filePath) 
				|| AppEmptyUtils.isEmpty(fileName) 
				|| AppEmptyUtils.isEmpty(fileSize) 
				|| AppEmptyUtils.isEmpty(fileType)) {
			logger.info("result:error parameter");
			String msg = "参数有误";
			return AppJsonMessage.toJsonMsgFalse(1,msg);
		}
		//给用户设置头像path
		YJWYUserModel user = userService.queryById(userId);
		if(null!=user){
			user.setHeader_img(filePath);
		}
		userService.update(new YJWYUserModel[]{user});
		//给附件表插入附件内容
		SystemFileModel systemFileModel = new SystemFileModel();
		systemFileModel.setFile_name(fileName);
		systemFileModel.setFile_path(filePath);
		systemFileModel.setFile_size(fileSize);
		systemFileModel.setFile_type(fileType);
		systemFileModel.setTable_name(YJWYUserModel.META_ID);
		//systemFileModel.setRecord_id(String.valueOf(mapModel.get("leaveId")));
		systemFileModel.setCreate_time(AppConstant.df_YMDHMS.format(new Date()));
		systemFileModel.setCreate_user(userId);
		systemFileModel.setPk_crop(crop);
		
		SystemFileModel[] newSystemFile = systemFileBusinessService.save(new SystemFileModel[]{systemFileModel});
		
		if (AppEmptyUtils.isEmpty(newSystemFile)) {
			logger.info("result:error user modify head");
			String msg = "修改头像错误，请重试！";
			return AppJsonMessage.toJsonMsgFalse(2,msg);
		}
		return AppJsonMessage.toJsonMsg(0, "success");
	}

	/**
	 * 保存 用户修改的名称
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@Override
	public JSONObject saveNameModify(HttpServletRequest reqParam) throws Exception {
		logger.info("AppUserInfoServiceImpl/saveNameModify");
		
		String userId = reqParam.getParameter("userId");
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String userName = reqParam.getParameter("userName");
		
		//判断参数是否正常
		if (AppEmptyUtils.isEmpty(userId) 
				|| AppEmptyUtils.isEmpty(mobilePlatform) 
				|| AppEmptyUtils.isEmpty(userName)) {
			logger.info("result:error parameter");
			String msg = "参数有误";
			return AppJsonMessage.toJsonMsgFalse(1,msg);
		}
		
		//判断用户是否存在 和是否可用
		YJWYUserModel taskUser = userService.queryById(userId);
		
		taskUser.setUser_name(userName);
		
		List<YJWYUserModel> userModels = userService.update(new YJWYUserModel[]{taskUser});
		
		if (AppEmptyUtils.isEmpty(userModels)) {
			logger.info("result:error user modify name");
			String msg = "修改用户名称失败，请重试！";
			return AppJsonMessage.toJsonMsgFalse(2,msg);
		}
		
		return AppJsonMessage.toJsonMsg(0, "success");
	}

	
	/**
	 * 保存用户 修改的 密码
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@Override
	public JSONObject savePasswordModify(HttpServletRequest reqParam) throws Exception {
		logger.info("AppUserInfoServiceImpl/savePasswordModify");
		
		String userId = reqParam.getParameter("userId");
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String oldPwd = reqParam.getParameter("oldPwd");
		String newPwd = reqParam.getParameter("newPwd");
		
		//判断参数是否正常
		if (AppEmptyUtils.isEmpty(userId) 
				|| AppEmptyUtils.isEmpty(mobilePlatform) 
				|| AppEmptyUtils.isEmpty(oldPwd) 
				|| AppEmptyUtils.isEmpty(newPwd)) {
			logger.info("result:error parameter");
			String msg = "参数有误";
			return AppJsonMessage.toJsonMsgFalse(1,msg);
		}
		
		//判断用户是否存在 和是否可用
		YJWYUserModel taskUser = userService.queryById(userId);
		
		//判断旧密码是否正确
		String sqlOldPwd = taskUser.getPassword();//数据库中的密码（加密的）
		oldPwd = StringUtils.md5(oldPwd);//页面上的密码（通过md5加密）
		if (!sqlOldPwd.equals(oldPwd)) {
			logger.info("result:error oldPwd");
			String msg = "旧密码输入错误";
			return AppJsonMessage.toJsonMsgFalse(2,msg);
		}
		
		//进行设置新密码
		newPwd = StringUtils.md5(newPwd);
		taskUser.setPassword(newPwd);
		
		List<YJWYUserModel> userModels = userService.update(new YJWYUserModel[]{taskUser});
		
		if (AppEmptyUtils.isEmpty(userModels)) {
			logger.info("result:error user modify name");
			String msg = "修改用户密码失败，请重试！";
			return AppJsonMessage.toJsonMsgFalse(2,msg);
		}
		
		return AppJsonMessage.toJsonMsg(0, "success");
	}

	/**
	 * 用户问题反馈
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@Override
	public JSONObject saveProblemFeedback(HttpServletRequest reqParam) throws Exception {
		logger.info("AppUserInfoServiceImpl/saveProblemFeedback");
		
		String userId = reqParam.getParameter("userId");
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String crop = reqParam.getParameter("crop");
		String feedback_type = reqParam.getParameter("feedback_type");
		String feedback_phone = reqParam.getParameter("feedback_phone");
		String feedback_desc = reqParam.getParameter("feedback_desc");
		Date nowDate = new Date();
		//判断参数是否正常
		if (AppEmptyUtils.isEmpty(userId) 
				|| AppEmptyUtils.isEmpty(mobilePlatform) 
				|| AppEmptyUtils.isEmpty(crop) 
				|| AppEmptyUtils.isEmpty(feedback_phone)) {
			logger.info("result:error parameter");
			String msg = "参数有误";
			return AppJsonMessage.toJsonMsgFalse(1,msg);
		}
		
		//构建新的 YJWYFeedbackModel
		YJWYFeedbackModel feedBackModel = new YJWYFeedbackModel();
		feedBackModel.setCreat_time(AppConstant.df_YMDHMS.format(nowDate));
		feedBackModel.setCreate_user(userId);
		feedBackModel.setUpdate_time(String.valueOf(new Date().getTime()));
		feedBackModel.setUpdate_user(userId);
		feedBackModel.setFeedback_desc(feedback_desc);
		feedBackModel.setFeedback_phone(feedback_phone);
		feedBackModel.setFeedback_type(feedback_type);
		List<YJWYFeedbackModel> feedback = service.save(YJWYFeedbackModel.META_ID, new YJWYFeedbackModel[]{feedBackModel});
		if (AppEmptyUtils.isEmpty(feedback)) {
			logger.info("result:error add feedBackModel");
			String msg = "问题反馈保存失败，请重试！";
			return AppJsonMessage.toJsonMsgFalse(2,msg);
		}
		feedBackModel.setPk_feedback(feedback.get(0).getPk_feedback());
		return AppJsonMessage.toJsonMsgTrue(feedBackModel);
	}

}
