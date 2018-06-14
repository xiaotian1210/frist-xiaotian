package com.shareworx.ezfm.worktask.classpro.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectDomainService;
import com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusModel;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.ezfm.worktask.classpro.model.YJWYWorkTaskRepairClassProjectModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.util.ArrayUtils;

/**
 * 种类项目关联业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYWorkTaskRepairClassProjectBusinessService.ID)
public class YJWYWorkTaskRepairClassProjectBusinessServiceImpl implements YJWYWorkTaskRepairClassProjectBusinessService {

	@Autowired
	@Qualifier(YJWYWorkTaskRepairClassProjectDomainService.ID)
	private YJWYWorkTaskRepairClassProjectDomainService domainService;
	
	@Autowired
	@Qualifier(YJWYProjectDomainService.ID)
	private YJWYProjectDomainService projectService;
	
	public void setDomainService(YJWYWorkTaskRepairClassProjectDomainService domainService) {
		this.domainService = domainService;
	}

	public void setProjectService(YJWYProjectDomainService projectService) {
		this.projectService = projectService;
	}
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.classpro.service.YJWYWorkTaskRepairClassProjectBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYWorkTaskRepairClassProjectModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYWorkTaskRepairClassProjectModel> list = domainService.queryListByCondition(query);
		for (YJWYWorkTaskRepairClassProjectModel model:list) {
			YJWYProjectModel projectModel = projectService.queryById(model.getProject_id());
			model.put("project_name", projectModel.getProject_name());
		}
		if(ArrayUtils.isEmpty(list)){
			return new YJWYWorkTaskRepairClassProjectModel[0];
		}
		return list.toArray(new YJWYWorkTaskRepairClassProjectModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.classpro.service.YJWYWorkTaskRepairClassProjectBusinessService#load(com.shareworx.ezfm.worktask.classpro.model.YJWYWorkTaskRepairClassProjectModel)
	 */
	@Override
	public YJWYWorkTaskRepairClassProjectModel[] load(YJWYWorkTaskRepairClassProjectModel model) throws ShareworxServiceException {
		List<YJWYWorkTaskRepairClassProjectModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYWorkTaskRepairClassProjectModel[0];
		}
		return list.toArray(new YJWYWorkTaskRepairClassProjectModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.classpro.service.YJWYWorkTaskRepairClassProjectBusinessService#save(com.shareworx.ezfm.worktask.classpro.model.YJWYWorkTaskRepairClassProjectModel[])
	 */
	@Override
	public YJWYWorkTaskRepairClassProjectModel[] save(YJWYWorkTaskRepairClassProjectModel[] models) throws ShareworxServiceException {
		String pk_class_id = models[0].get("pk_class_id").toString();
		List<Object> classids = models[0].getProjectids();
		YJWYWorkTaskRepairClassProjectModel[] rs = null;
		
		List<YJWYWorkTaskRepairClassProjectModel> list = new ArrayList<YJWYWorkTaskRepairClassProjectModel>();
		if (classids != null&&classids.size() > 0) {
			//新增前，删除已保存关系；
			this.deleteProjectClassModels(pk_class_id);
			YJWYWorkTaskRepairClassProjectModel[] nexusModels = new YJWYWorkTaskRepairClassProjectModel[classids.size()];
			Map<String, String> map;
			YJWYWorkTaskRepairClassProjectModel nexusModel;
			for (int i = 0; i < classids.size(); i++) {
				nexusModel = new YJWYWorkTaskRepairClassProjectModel();
				map = (Map<String, String>) classids.get(i);
				nexusModel.setProject_id(map.get("id"));
				nexusModel.setClass_id(pk_class_id);
				nexusModel.setCreate_time(this.obtainTime());
				nexusModel.setUpdate_time(System.currentTimeMillis()+"");
				nexusModel.setCreate_user_id(UserUtil.getCurrentUserPk());
				nexusModel.setUpdate_user_id(UserUtil.getCurrentUserPk());
				nexusModels[i] = nexusModel;
			}
			
			list = domainService.save(nexusModels);
		}
		return list.toArray(new YJWYWorkTaskRepairClassProjectModel[0]);
	}

	
	/**
	 * 根据分组主键id对关系表进行删除
	 * 
	 * @param pk_group
	 * @return
	 */
	public void deleteProjectClassModels(String pk_class_id) {
		// 生成query
		Query query = Query.from(YJWYWorkTaskRepairClassProjectModel.META_ID);
		query = query.where(new Condition("class_id", QueryContents.TYPE_EQ, pk_class_id));
		// 查询数据
		List<YJWYWorkTaskRepairClassProjectModel> models = domainService.queryListByCondition(query);
		// 判断是否有数据
		if (models.size() > 0) {
			for (YJWYWorkTaskRepairClassProjectModel model:models) {
				domainService.delete(model);
			}
		}
	}
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.classpro.service.YJWYWorkTaskRepairClassProjectBusinessService#update(com.shareworx.ezfm.worktask.classpro.model.YJWYWorkTaskRepairClassProjectModel[])
	 */
	@Override
	public YJWYWorkTaskRepairClassProjectModel[] update(YJWYWorkTaskRepairClassProjectModel[] models) throws ShareworxServiceException {
		List<YJWYWorkTaskRepairClassProjectModel> list = domainService.update(models);
		return list.toArray(new YJWYWorkTaskRepairClassProjectModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.classpro.service.YJWYWorkTaskRepairClassProjectBusinessService#delete(com.shareworx.ezfm.worktask.classpro.model.YJWYWorkTaskRepairClassProjectModel[])
	 */
	@Override
	public YJWYWorkTaskRepairClassProjectModel[] delete(YJWYWorkTaskRepairClassProjectModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}
	//公共方法，获取当前时间
		public String obtainTime(){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			return df.format(new Date());
		}
}
