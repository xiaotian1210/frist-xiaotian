package com.shareworx.ezfm.app.sign.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.ezfm.app.constant.AppConstant;
import com.shareworx.ezfm.app.util.AppEmptyUtils;
import com.shareworx.ezfm.app.util.AppJsonMessage;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectDomainService;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserDomainService;
import com.shareworx.ezfm.performance.sign.model.YJWYSignModel;
import com.shareworx.ezfm.performance.sign.service.YJWYSignDomainService;

/**
 * 签到、签退 接口实现
 * @author lingwei.li
 *
 */
@Service(AppSignService.ID)
public class AppSignServiceImpl implements AppSignService{

	final static Logger logger = Logger.getLogger(AppSignServiceImpl.class);
	
	@Autowired
	@Qualifier(YJWYProjectDomainService.ID)
	private YJWYProjectDomainService projectService;
	@Autowired
	@Qualifier(YJWYSignDomainService.ID)
	private YJWYSignDomainService signService;
	@Autowired
	@Qualifier(YJWYUserDomainService.ID)
	private YJWYUserDomainService userService;
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	
	/**
	 * 签到
	 */
	@Override
	public JSONObject saveSign(HttpServletRequest reqParam) throws Exception {
		logger.info("appSignService/saveSign");
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String crop = reqParam.getParameter("crop");
		String userId = reqParam.getParameter("userId");
		String projectId = reqParam.getParameter("projectId");
		String signTime = reqParam.getParameter("signTime");
		String lng = reqParam.getParameter("lng");
		String lat = reqParam.getParameter("lat");
		Date currentDate = new Date();
		//先判断必填参数
		if(AppEmptyUtils.isEmpty(userId) 
				|| AppEmptyUtils.isEmpty(crop)
				|| AppEmptyUtils.isEmpty(mobilePlatform) 
				|| AppEmptyUtils.isEmpty(signTime) 
				|| AppEmptyUtils.isEmpty(lng) 
				|| AppEmptyUtils.isEmpty(lat)){
			logger.info("result:error parameter");
			String msg = "参数错误！";
			return AppJsonMessage.toJsonMsgFalse(1,msg); //参数错误！
		}
		
		YJWYSignModel signModel = new YJWYSignModel();
		
		if (!AppEmptyUtils.isEmpty(projectId)) {
			//检查项目id
			YJWYProjectModel projectModel = projectService.queryById(projectId);
			if(AppEmptyUtils.isEmpty(projectModel)){
				logger.info("result:error parameter");
				String msg = "项目不存在！";
				return AppJsonMessage.toJsonMsgFalse(2,msg); //参数错误！
			}
			signModel.setFk_project(projectId);
			//设置用户的 岗位、部门、区域信息
			String area_dept_sta = getSql(userId, projectId);
			List<Map<String, Object>> list = readJdbcTemplate.queryForList(area_dept_sta);
			if (!AppEmptyUtils.isEmpty(list)) {
				signModel.setFk_region(String.valueOf(list.get(0).get("pk_area_")));
				signModel.setFk_job(String.valueOf(list.get(0).get("pk_org_")));
				signModel.setFk_department(String.valueOf(list.get(0).get("pk_station_")));
			}
		}
		
		signModel.setSign_action(AppConstant.SIGN_STATE_1);
		signModel.setSign_commitTime(signTime);
		signModel.setSign_person(userId);
		signModel.setSign_location(lng+","+lat);
		signModel.setCreate_time(AppConstant.df_YMDHMS.format(currentDate));
		signModel.setCreate_user(userId);
		signModel.setUpdate_user(userId);
		signModel.setUpdate_time(String.valueOf(currentDate.getTime()));
		signModel.setPk_crop(crop);
		
		List<YJWYSignModel> signModels = signService.save(new YJWYSignModel[]{signModel});
		
		if(AppEmptyUtils.isEmpty(signModels)){
			String msg = "签到保存失败！";
			return AppJsonMessage.toJsonMsg(2, msg);
		}
		//签到成功更改用户签到状态为1；
		updateUserIsSign(userId,"1");
		return AppJsonMessage.toJsonMsg(0, "签到成功！");
		
	}

	/**
	 * 签退
	 */
	@Override
	public JSONObject saveSignOut(HttpServletRequest reqParam) throws Exception {
		logger.info("appSignService/saveSignOut");
		
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String crop = reqParam.getParameter("crop");
		String userId = reqParam.getParameter("userId");
		String projectId = reqParam.getParameter("projectId");
		String signTime = reqParam.getParameter("signTime");
		String lng = reqParam.getParameter("lng");
		String lat = reqParam.getParameter("lat");
		Date currentDate = new Date();
		//先判断必填参数
		if(AppEmptyUtils.isEmpty(userId) 
				|| AppEmptyUtils.isEmpty(crop)
				|| AppEmptyUtils.isEmpty(mobilePlatform) 
				|| AppEmptyUtils.isEmpty(signTime) 
				|| AppEmptyUtils.isEmpty(lng) 
				|| AppEmptyUtils.isEmpty(lat)){
			logger.info("result:error parameter");
			String msg = "参数错误！";
			return AppJsonMessage.toJsonMsgFalse(1,msg); //参数错误！
		}
		YJWYSignModel signModel = new YJWYSignModel();
		if (!AppEmptyUtils.isEmpty(projectId)) {
			//检查项目id
			YJWYProjectModel projectModel = projectService.queryById(projectId);
			if(AppEmptyUtils.isEmpty(projectModel)){
				logger.info("result:error parameter");
				String msg = "项目不存在！";
				return AppJsonMessage.toJsonMsgFalse(2,msg); //参数错误！
			}	
			signModel.setFk_project(projectId);
		}
		signModel.setSign_action(AppConstant.SIGN_STATE_0);
		signModel.setSign_commitTime(signTime);
		signModel.setSign_person(userId);
		signModel.setSign_location(lng+","+lat);
		signModel.setCreate_user(userId);
		signModel.setCreate_time(AppConstant.df_YMDHMS.format(currentDate));
		signModel.setUpdate_user(userId);
		signModel.setUpdate_time(String.valueOf(currentDate.getTime()));
		signModel.setPk_crop(crop);
		
		List<YJWYSignModel> signModels = signService.save(new YJWYSignModel[]{signModel});
		if(AppEmptyUtils.isEmpty(signModels)){
			String msg = "签到保存失败！";
			return AppJsonMessage.toJsonMsg(2, msg);
		}
		//签退成功，更改用户状态为0
		updateUserIsSign(userId,"0");
		return AppJsonMessage.toJsonMsg(0, "签退成功！");
	}

	public void updateUserIsSign(String userId,String isSign){
		YJWYUserModel user = userService.queryById(userId);
		if(null!=user){
			user.setIs_sign(isSign);
			userService.update(new YJWYUserModel[]{user});
		}
	}
	
	/**
	 * 根据userid和projectid 获取用户的岗位、部门、区域
	 * @param userId
	 * @param projectId
	 * @return
	 */
	public String getSql(String userId,String projectId){
		String whr = "where 1 = 1 ";
		if (!AppEmptyUtils.isEmpty(userId)) {
			whr = whr + "and pk_user_ = '"+userId+"' ";
		}
		if (!AppEmptyUtils.isEmpty(projectId)) {
			whr = whr + "and pk_project_ = '"+projectId+"' ";
		}
		
		String sql = "select pk_station_, pk_org_, pk_area_ "
				+ "from view_yjwy_user "
				+ whr;
		
		return sql;
	}
	
}