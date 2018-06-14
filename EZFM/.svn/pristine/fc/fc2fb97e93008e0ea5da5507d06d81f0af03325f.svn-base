package com.shareworx.ezfm.worktask.areaproject.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel;
import com.shareworx.ezfm.baseinfo.area.service.YJWYAreaDomainService;
import com.shareworx.ezfm.baseinfo.project.dao.YJWYProjectDao;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectBusinessService;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectDomainService;
import com.shareworx.ezfm.problem.record.model.YJWYProblemRecordModel;
import com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsModel;
import com.shareworx.ezfm.worktask.areadetails.service.YJWYWorkTaskAreaDetailsDomainService;
import com.shareworx.ezfm.worktask.areaproject.model.YJWYWorkTaskAreaProjectNexusModel;
import com.shareworx.ezfm.worktask.areaproject.vo.WorkTaskAreaProVo;
import com.alibaba.fastjson.JSON;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.SpringUtils;

/**
 * 片区项目关联业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYWorkTaskAreaProjectNexusBusinessService.ID)
public class YJWYWorkTaskAreaProjectNexusBusinessServiceImpl implements YJWYWorkTaskAreaProjectNexusBusinessService {

	@Autowired
	@Qualifier(YJWYWorkTaskAreaProjectNexusDomainService.ID)
	private YJWYWorkTaskAreaProjectNexusDomainService domainService;
	
	public void setDomainService(YJWYWorkTaskAreaProjectNexusDomainService domainService) {
		this.domainService = domainService;
	}
	@Autowired
	@Qualifier(YJWYAreaDomainService.ID)
	private YJWYAreaDomainService areaService;

	public void setAreaService(YJWYAreaDomainService areaService) {
		this.areaService = areaService;
	}
	@Autowired
	@Qualifier(YJWYWorkTaskAreaDetailsDomainService.ID)
	private YJWYWorkTaskAreaDetailsDomainService detailsService;

	public void setDetailsService(YJWYWorkTaskAreaDetailsDomainService detailsService) {
		this.detailsService = detailsService;
	}
	@Autowired
	@Qualifier(YJWYProjectDomainService.ID)
	private YJWYProjectDomainService projectService;

	public void setProjectService(YJWYProjectDomainService projectService) {
		this.projectService = projectService;
	}
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areaproject.service.YJWYWorkTaskAreaProjectNexusBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYWorkTaskAreaProjectNexusModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYWorkTaskAreaProjectNexusModel> list = domainService.queryListByCondition(query);
		for (YJWYWorkTaskAreaProjectNexusModel model:list) {
			YJWYWorkTaskAreaDetailsModel detailsModel = detailsService.queryById(model.getArea_id());
			YJWYProjectModel projectModel = projectService.queryById(model.getProject_id());
			model.put("project_name", projectModel.getProject_name());
			model.put("details_name", detailsModel.getArea_name());
		}
		if(ArrayUtils.isEmpty(list)){
			return new YJWYWorkTaskAreaProjectNexusModel[0];
		}
		return list.toArray(new YJWYWorkTaskAreaProjectNexusModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areaproject.service.YJWYWorkTaskAreaProjectNexusBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYProjectModel[] queryProject(String wheSql,WorkTaskAreaProVo queryvo) throws ShareworxServiceException {
		JdbcTemplate read = dao.getReadTemplate();
		
		String sql = "select pk_project_,project_name_ "+wheSql + " limit "+queryvo.getStart()+","+queryvo.getLimit();
		List<YJWYProjectModel> list = read.query(sql, new RowMapper<YJWYProjectModel>(){
			@Override
			public YJWYProjectModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				YJWYProjectModel vo = new YJWYProjectModel();
				//记录ID
				vo.setPk_project(rs.getString("pk_project_"));
				//操作时间
				vo.setProject_name(rs.getString("project_name_"));
				return vo;
			}			
		});
		if(ArrayUtils.isEmpty(list)){
			return new YJWYProjectModel[0];
		}
		return list.toArray(new YJWYProjectModel[0]);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areaproject.service.YJWYWorkTaskAreaProjectNexusBusinessService#load(com.shareworx.ezfm.worktask.areaproject.model.YJWYWorkTaskAreaProjectNexusModel)
	 */
	@Override
	public YJWYWorkTaskAreaProjectNexusModel[] load(YJWYWorkTaskAreaProjectNexusModel model) throws ShareworxServiceException {
		List<YJWYWorkTaskAreaProjectNexusModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYWorkTaskAreaProjectNexusModel[0];
		}
		return list.toArray(new YJWYWorkTaskAreaProjectNexusModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areaproject.service.YJWYWorkTaskAreaProjectNexusBusinessService#save(com.shareworx.ezfm.worktask.areaproject.model.YJWYWorkTaskAreaProjectNexusModel[])
	 */
	@Override
	public YJWYWorkTaskAreaProjectNexusModel[] save(YJWYWorkTaskAreaProjectNexusModel[] models) throws ShareworxServiceException {
		List<YJWYWorkTaskAreaProjectNexusModel> list = domainService.save(models);
		return list.toArray(new YJWYWorkTaskAreaProjectNexusModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areaproject.service.YJWYWorkTaskAreaProjectNexusBusinessService#update(com.shareworx.ezfm.worktask.areaproject.model.YJWYWorkTaskAreaProjectNexusModel[])
	 */
	@Override
	public YJWYWorkTaskAreaProjectNexusModel[] update(YJWYWorkTaskAreaProjectNexusModel[] models) throws ShareworxServiceException {
		List<YJWYWorkTaskAreaProjectNexusModel> list = domainService.update(models);
		return list.toArray(new YJWYWorkTaskAreaProjectNexusModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areaproject.service.YJWYWorkTaskAreaProjectNexusBusinessService#delete(com.shareworx.ezfm.worktask.areaproject.model.YJWYWorkTaskAreaProjectNexusModel[])
	 */
	@Override
	public YJWYWorkTaskAreaProjectNexusModel[] delete(YJWYWorkTaskAreaProjectNexusModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
