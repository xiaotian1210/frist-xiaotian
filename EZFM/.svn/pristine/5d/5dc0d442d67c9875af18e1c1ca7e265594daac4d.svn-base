package com.shareworx.ezfm.performance.leave.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.DateTimeUtil;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.performance.leave.dao.YJWYLeaveDao;
import com.shareworx.ezfm.performance.leave.model.YJWYLeaveModel;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectDomainService;
import com.shareworx.ezfm.baseinfo.station.model.YJWYStationModel;
import com.shareworx.ezfm.baseinfo.station.service.YJWYStationDomainService;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserDomainService;
import com.shareworx.ezfm.utils.UserUtil;

/**
 * 休假备案业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYLeaveBusinessService.ID)
public class YJWYLeaveBusinessServiceImpl implements YJWYLeaveBusinessService {

	
	@Autowired
	@Qualifier(YJWYLeaveDao.ID)
	private YJWYLeaveDao leaveDao;

	public void setYJWYLeaveDao(YJWYLeaveDao leaveDao) {
		this.leaveDao = leaveDao;
	}
	@Autowired
	@Qualifier(YJWYLeaveDomainService.ID)
	private YJWYLeaveDomainService domainService;
	
	@Autowired
	@Qualifier(YJWYProjectDomainService.ID)
	private YJWYProjectDomainService projectService;
	
	@Autowired
	@Qualifier(YJWYUserDomainService.ID)
	private YJWYUserDomainService userService;
	
	@Autowired
	@Qualifier(YJWYStationDomainService.ID)
	private YJWYStationDomainService stationService;
	
	public void setDomainService(YJWYLeaveDomainService domainService) {
		this.domainService = domainService;
	}

	public void setProjectService(YJWYProjectDomainService projectService) {
		this.projectService = projectService;
	}
	
	public void setUserDomainService(YJWYUserDomainService userService) {
		this.userService = userService;
	}
	
	public void setStationDomainService(YJWYStationDomainService stationService) {
		this.stationService = stationService;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.Result.leave.service.YJWYLeaveBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYLeaveModel[] query(Query query) throws ShareworxServiceException {
		
		List<YJWYLeaveModel> list = domainService.queryListByCondition(query);
		for (YJWYLeaveModel model:list) {
			//查询项目名称
			if (!StringUtils.isEmpty(model.getFk_project())) {
				YJWYProjectModel projectModels = projectService.queryById(model.getFk_project());
				model.setFk_project(projectModels.getProject_name());
			}
			if (!StringUtils.isEmpty(model.getLv_submitter())) {
				YJWYUserModel userModels = userService.queryById(model.getLv_submitter());
				model.setLv_submitter((userModels.getUser_name()));
			}
			if (!StringUtils.isEmpty(model.getFk_job())) {
				YJWYStationModel stationModels = stationService.queryById(model.getFk_job());
				model.setFk_job(stationModels.getStation_name());
			}
		}
		if(ArrayUtils.isEmpty(list)){
			return new YJWYLeaveModel[0];
		}
		return list.toArray(new YJWYLeaveModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.Result.leave.service.YJWYLeaveBusinessService#load(com.shareworx.ezfm.Result.leave.model.YJWYLeaveModel)
	 */
	@Override
	public YJWYLeaveModel[] load(YJWYLeaveModel model) throws ShareworxServiceException {
		List<YJWYLeaveModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYLeaveModel[0];
		}
		return list.toArray(new YJWYLeaveModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.Result.leave.service.YJWYLeaveBusinessService#save(com.shareworx.ezfm.Result.leave.model.YJWYLeaveModel[])
	 */
	@Override
	public YJWYLeaveModel[] save(YJWYLeaveModel[] models) throws ShareworxServiceException {
		List<YJWYLeaveModel> list = domainService.save(models);
		return list.toArray(new YJWYLeaveModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.Result.leave.service.YJWYLeaveBusinessService#update(com.shareworx.ezfm.Result.leave.model.YJWYLeaveModel[])
	 */
	@Override
	public YJWYLeaveModel[] update(YJWYLeaveModel[] models) throws ShareworxServiceException {
		List<YJWYLeaveModel> modelList = new ArrayList<YJWYLeaveModel>();
		if (models.length>0) {
			for (YJWYLeaveModel model:models) {
				//审批人，修改人
				model.setLv_approval(UserUtil.getCurrentUserPk());	
				model.setLv_confirmTime(DateTimeUtil.getTimestampString(new Date()));
				model.setUpdate_user(UserUtil.getCurrentUserPk());
				model.setUpdate_time(System.currentTimeMillis()+"");
				//String op = model.getLv_operation();
				//model.setLv_operation(model.getLv_operation()+"");
				modelList.add(model);
				
			}
		}
		List<YJWYLeaveModel> list = domainService.update(modelList.toArray(new YJWYLeaveModel[modelList.size()]));
		return list.toArray(new YJWYLeaveModel[0]);
	}

	@Override
	//提交时间超过2天 自动置为撤销
	public YJWYLeaveModel[] updateOperation(YJWYLeaveModel[] models) throws ShareworxServiceException {
		for (YJWYLeaveModel model:models) {
			Query queryp = Query.from(YJWYProjectModel.META_ID);
			queryp.and(Condition.create("project_name_", model.getFk_project()));
			YJWYProjectModel projectModels = projectService.queryOneByCondition(queryp);
			model.setFk_project(projectModels.getPk_project());				
			Query queryu = Query.from(YJWYUserModel.META_ID);
			queryu.and(Condition.create("user_name_", model.getLv_submitter()));
			YJWYUserModel userModels = userService.queryOneByCondition(queryu);
			model.setLv_submitter(userModels.getPk_user());			
			Query querys = Query.from(YJWYStationModel.META_ID);
			querys.and(Condition.create("station_name_", model.getFk_job()));
			YJWYStationModel stationModels = stationService.queryOneByCondition(querys);
			model.setFk_job(stationModels.getPk_station());	
		}
		List<YJWYLeaveModel> list = domainService.update(models);
		return list.toArray(new YJWYLeaveModel[0]);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.Result.leave.service.YJWYLeaveBusinessService#delete(com.shareworx.ezfm.Result.leave.model.YJWYLeaveModel[])
	 */
	@Override
	public YJWYLeaveModel[] delete(YJWYLeaveModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
