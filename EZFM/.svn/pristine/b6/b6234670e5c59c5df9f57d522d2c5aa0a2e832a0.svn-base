package com.shareworx.ezfm.worktask.projectuser.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserDomainService;
import com.shareworx.ezfm.worktask.projectuser.model.YJWYWorkTaskProjectUserModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.StringUtils;

/**
 * 项目接口人员业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYWorkTaskProjectUserBusinessService.ID)
public class YJWYWorkTaskProjectUserBusinessServiceImpl implements YJWYWorkTaskProjectUserBusinessService {

	@Autowired
	@Qualifier(YJWYWorkTaskProjectUserDomainService.ID)
	private YJWYWorkTaskProjectUserDomainService domainService;
	
	public void setDomainService(YJWYWorkTaskProjectUserDomainService domainService) {
		this.domainService = domainService;
	}

	@Autowired
	@Qualifier(YJWYUserDomainService.ID)
	private YJWYUserDomainService userDomainService;
	
	public void setUserDomainService(YJWYUserDomainService userDomainService) {
		this.userDomainService = userDomainService;
	}
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.projectuser.service.YJWYWorkTaskProjectUserBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYWorkTaskProjectUserModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYWorkTaskProjectUserModel> list = domainService.queryListByCondition(query);
		for (YJWYWorkTaskProjectUserModel model:list) {
			YJWYUserModel userModel = userDomainService.queryById(model.getUser_id());
			model.put("user_name", userModel.getUser_name());
			model.put("user_code", userModel.getUser_code());
		}
		if(ArrayUtils.isEmpty(list)){
			return new YJWYWorkTaskProjectUserModel[0];
		}
		return list.toArray(new YJWYWorkTaskProjectUserModel[0]);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.projectuser.service.YJWYWorkTaskProjectUserBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYUserModel[] findUserByProjectId(String projectId,String userName,int start,int limit) throws ShareworxServiceException {
		Query query = Query.from(YJWYWorkTaskProjectUserModel.META_ID);
		query = query.where(new Condition("project_id", QueryContents.TYPE_EQ, projectId));
		List<YJWYWorkTaskProjectUserModel> projectUserlist = domainService.queryListByCondition(query);
		JdbcTemplate read = dao.getReadTemplate();
		String sql = " select * from yjwy_pub_user where pk_user_ in(select pk_user_ from view_yjwy_project_user where pk_project_ = '"+projectId+"') ";
		if (!StringUtils.isEmpty(userName)) {
			sql +=" and user_name_ like '%"+userName+"%'";
		}
		sql += "limit "+start+","+limit;		
		List<YJWYUserModel> list = read.query(sql, new RowMapper<YJWYUserModel>(){

			@Override
			public YJWYUserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				YJWYUserModel model = new YJWYUserModel();
				model.setPk_user(rs.getString("pk_user_"));
				model.setUser_name(rs.getString("user_name_"));
				model.setUser_code(rs.getString("user_code_"));
				return model;
				}
				
			});
		if(ArrayUtils.isEmpty(list)){
			return new YJWYUserModel[0];
		}
		for (YJWYUserModel userModel:list) {
			for (YJWYWorkTaskProjectUserModel projectUserModel:projectUserlist) {
				if (userModel.getPk_user().equals(projectUserModel.getUser_id())) {
					userModel.put("fk_standardedition", true);
				}
			}
		}
		return list.toArray(new YJWYUserModel[0]);
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.projectuser.service.YJWYWorkTaskProjectUserBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYUserModel[] findUserAllByProjectId(String projectId) throws ShareworxServiceException {
		JdbcTemplate read = dao.getReadTemplate();
		String sql = "select * from yjwy_pub_user where pk_user_ in(select user_id from yjwy_worktask_project_user where project_id = '"+projectId+"') ";
		List<YJWYUserModel> list = read.query(sql, new RowMapper<YJWYUserModel>(){
			@Override
			public YJWYUserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				YJWYUserModel model = new YJWYUserModel();
				model.setPk_user(rs.getString("pk_user_"));
				model.setUser_name(rs.getString("user_name_"));
				model.setEm_code(rs.getString("em_code_"));
				model.setEmail(rs.getString("email_"));
				model.setPhone(rs.getString("phone_"));
				return model;
				}
			});
		
		if(ArrayUtils.isEmpty(list)){
			return new YJWYUserModel[0];
		}
		return list.toArray(new YJWYUserModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.projectuser.service.YJWYWorkTaskProjectUserBusinessService#load(com.shareworx.ezfm.worktask.projectuser.model.YJWYWorkTaskProjectUserModel)
	 */
	@Override
	public YJWYWorkTaskProjectUserModel[] load(YJWYWorkTaskProjectUserModel model) throws ShareworxServiceException {
		List<YJWYWorkTaskProjectUserModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYWorkTaskProjectUserModel[0];
		}
		return list.toArray(new YJWYWorkTaskProjectUserModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.projectuser.service.YJWYWorkTaskProjectUserBusinessService#save(com.shareworx.ezfm.worktask.projectuser.model.YJWYWorkTaskProjectUserModel[])
	 */
	@Override
	public YJWYWorkTaskProjectUserModel[] save(YJWYWorkTaskProjectUserModel[] models) throws ShareworxServiceException {
		List<YJWYWorkTaskProjectUserModel> list = domainService.save(models);
		return list.toArray(new YJWYWorkTaskProjectUserModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.projectuser.service.YJWYWorkTaskProjectUserBusinessService#update(com.shareworx.ezfm.worktask.projectuser.model.YJWYWorkTaskProjectUserModel[])
	 */
	@Override
	public YJWYWorkTaskProjectUserModel[] update(YJWYWorkTaskProjectUserModel[] models) throws ShareworxServiceException {
		List<YJWYWorkTaskProjectUserModel> list = domainService.update(models);
		return list.toArray(new YJWYWorkTaskProjectUserModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.projectuser.service.YJWYWorkTaskProjectUserBusinessService#delete(com.shareworx.ezfm.worktask.projectuser.model.YJWYWorkTaskProjectUserModel[])
	 */
	@Override
	public YJWYWorkTaskProjectUserModel[] delete(YJWYWorkTaskProjectUserModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
