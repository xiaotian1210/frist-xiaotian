package com.shareworx.ezfm.worktask.areauser.service;

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
import com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsModel;
import com.shareworx.ezfm.worktask.areadetails.service.YJWYWorkTaskAreaDetailsDomainService;
import com.shareworx.ezfm.worktask.areapersonnel.model.YJWYWorkTaskAreaPersonnelModel;
import com.shareworx.ezfm.worktask.areapersonnel.service.YJWYWorkTaskAreaPersonnelDomainService;
import com.shareworx.ezfm.worktask.areauser.model.YJWYWorkTaskAreaUserNexusModel;
import com.shareworx.ezfm.worktask.areauser.vo.AreaUserQueryVo;
import com.shareworx.ezfm.worktask.details.model.YJWYWorkTaskDetailsModel;
import com.shareworx.ezfm.worktask.projectuser.model.YJWYWorkTaskProjectUserModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.StringUtils;

/**
 * 片区与人员关系表业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYWorkTaskAreaUserNexusBusinessService.ID)
public class YJWYWorkTaskAreaUserNexusBusinessServiceImpl implements YJWYWorkTaskAreaUserNexusBusinessService {

	@Autowired
	@Qualifier(YJWYWorkTaskAreaUserNexusDomainService.ID)
	private YJWYWorkTaskAreaUserNexusDomainService domainService;
	
	public void setDomainService(YJWYWorkTaskAreaUserNexusDomainService domainService) {
		this.domainService = domainService;
	}

	@Autowired
	@Qualifier(YJWYUserDomainService.ID)
	private YJWYUserDomainService userDomainService;
	
	public void setUserDomainService(YJWYUserDomainService userDomainService) {
		this.userDomainService = userDomainService;
	}
	@Autowired
	@Qualifier(YJWYWorkTaskAreaPersonnelDomainService.ID)
	private YJWYWorkTaskAreaPersonnelDomainService personnelDomainService;
	
	public void setPersonnelDomainService(YJWYWorkTaskAreaPersonnelDomainService personnelDomainService) {
		this.personnelDomainService = personnelDomainService;
	}
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areauser.service.YJWYWorkTaskAreaUserNexusBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYWorkTaskAreaUserNexusModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYWorkTaskAreaUserNexusModel> list = domainService.queryListByCondition(query);
		for (YJWYWorkTaskAreaUserNexusModel model:list) {
			YJWYUserModel userModel = userDomainService.queryById(model.getUser_id());
			model.put("user_name", userModel.getUser_name());
			model.put("user_code", userModel.getUser_code());
		}
		if(ArrayUtils.isEmpty(list)){
			return new YJWYWorkTaskAreaUserNexusModel[0];
		}
		return list.toArray(new YJWYWorkTaskAreaUserNexusModel[0]);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areauser.service.YJWYWorkTaskAreaUserNexusBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYUserModel[] queryUser(AreaUserQueryVo vo,String whrSql) throws ShareworxServiceException {
		JdbcTemplate read = dao.getReadTemplate();
		String sql = "select pk_user_,user_name_,user_code_ from yjwy_pub_user "+ whrSql;
		//拼接分页
		sql +=" limit "+vo.getStart()+","+vo.getLimit();
		List<YJWYUserModel> list = read.query(sql, new RowMapper<YJWYUserModel>(){
			@Override
			public YJWYUserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				YJWYUserModel model = new YJWYUserModel();
				model.put("pk_personnel_id", rs.getString("pk_user_"));
				model.put("user_name", rs.getString("user_name_"));
				model.put("user_code", rs.getString("user_code_"));
				return model;
			}
		});
		Query query = Query.from(YJWYWorkTaskAreaUserNexusModel.META_ID);
		if (!StringUtils.isEmpty(vo.getArea_id())) {
			query.and(Condition.create("area_id", vo.getArea_id()));
		}
		if (!StringUtils.isEmpty(vo.getUser_type())) {
			query.and(Condition.create("user_type", vo.getUser_type()));
		}
		List<YJWYWorkTaskAreaUserNexusModel> nexusList = domainService.queryListByCondition(query);
		//根据片区ID，和人员类型判断是否选中
				for (YJWYUserModel model:list) {
					for (YJWYWorkTaskAreaUserNexusModel nexusModel:nexusList) {
						if (nexusModel.getUser_id().equals(model.get("pk_personnel_id").toString())) {
							model.put("fk_standardedition", true);
						}
					}
				}
		if(ArrayUtils.isEmpty(list)){
			return new YJWYUserModel[0];
		}
		return list.toArray(new YJWYUserModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areauser.service.YJWYWorkTaskAreaUserNexusBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYWorkTaskAreaPersonnelModel[] queryUserRepair(AreaUserQueryVo vo,String whrSql) throws ShareworxServiceException {
		JdbcTemplate read = dao.getReadTemplate();
		String sql = "select tab1.pk_personnel_id,tab2.user_name_,tab2.user_code_ from yjwy_worktask_area_personnel tab1"
				+" left join yjwy_pub_user tab2 on tab1.user_id=tab2.pk_user_ "
				+ whrSql;
		//拼接分页
		sql +=" limit "+vo.getStart()+","+vo.getLimit();
		List<YJWYWorkTaskAreaPersonnelModel> list = read.query(sql, new RowMapper<YJWYWorkTaskAreaPersonnelModel>(){
			@Override
			public YJWYWorkTaskAreaPersonnelModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				YJWYWorkTaskAreaPersonnelModel model = new YJWYWorkTaskAreaPersonnelModel();
				model.setPk_personnel_id(rs.getString("pk_personnel_id"));
				model.put("user_name", rs.getString("user_name_"));
				model.put("user_code", rs.getString("user_code_"));
				return model;
			}
		});
		Query query = Query.from(YJWYWorkTaskAreaUserNexusModel.META_ID);
		if (!StringUtils.isEmpty(vo.getArea_id())) {
			query.and(Condition.create("area_id", vo.getArea_id()));
		}
		if (!StringUtils.isEmpty(vo.getUser_type())) {
			query.and(Condition.create("user_type", vo.getUser_type()));
		}
		List<YJWYWorkTaskAreaUserNexusModel> nexusList = domainService.queryListByCondition(query);
		//根据片区ID，和人员类型判断是否选中
				for (YJWYWorkTaskAreaPersonnelModel model:list) {
					for (YJWYWorkTaskAreaUserNexusModel nexusModel:nexusList) {
						if (nexusModel.getPersonnel_id().equals(model.getPk_personnel_id())) {
							model.put("fk_standardedition", true);
						}
					}
				}
//		List<YJWYWorkTaskAreaUserNexusModel> list = domainService.queryListByCondition(query);
//		List<YJWYWorkTaskAreaPersonnelModel> personnelList = personnelDomainService.queryListByCondition(queryUser);
//		YJWYUserModel userModel = null;
//		for (YJWYWorkTaskAreaPersonnelModel personnelModel:personnelList) {
//			for (YJWYWorkTaskAreaUserNexusModel model:list) {
//				if (personnelModel.getUser_id().equals(model.getUser_id())) {
//					personnelModel.put("fk_standardedition", true);
//				}
//			}
//			userModel = userDomainService.queryById(personnelModel.getUpdate_user_id());
//			personnelModel.put("user_code", userModel.getUser_code());
//			personnelModel.put("pk_user", personnelModel.getPk_personnel_id());
//		}
		if(ArrayUtils.isEmpty(list)){
			return new YJWYWorkTaskAreaPersonnelModel[0];
		}
		return list.toArray(new YJWYWorkTaskAreaPersonnelModel[0]);
	}
	
	@Override
	public YJWYUserModel[] queryUserRepairAll(String id) throws ShareworxServiceException {
		JdbcTemplate read = dao.getReadTemplate();
		String sql = "select * from yjwy_pub_user where pk_user_ in(select user_id from yjwy_worktask_area_user_nexus where user_type = 2 and area_id in (select pk_area_id from yjwy_worktask_area_details where fk_region_id = '"+id+"'))";
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
	 * @see com.shareworx.ezfm.worktask.areauser.service.YJWYWorkTaskAreaUserNexusBusinessService#load(com.shareworx.ezfm.worktask.areauser.model.YJWYWorkTaskAreaUserNexusModel)
	 */
	@Override
	public YJWYWorkTaskAreaUserNexusModel[] load(YJWYWorkTaskAreaUserNexusModel model) throws ShareworxServiceException {
		List<YJWYWorkTaskAreaUserNexusModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYWorkTaskAreaUserNexusModel[0];
		}
		return list.toArray(new YJWYWorkTaskAreaUserNexusModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areauser.service.YJWYWorkTaskAreaUserNexusBusinessService#save(com.shareworx.ezfm.worktask.areauser.model.YJWYWorkTaskAreaUserNexusModel[])
	 */
	@Override
	public YJWYWorkTaskAreaUserNexusModel[] save(YJWYWorkTaskAreaUserNexusModel[] models) throws ShareworxServiceException {
		for (YJWYWorkTaskAreaUserNexusModel model:models) {
			if (model.getUser_type().equals("2")) {
				YJWYWorkTaskAreaPersonnelModel personnelModel = personnelDomainService.queryById(model.getPersonnel_id());
				if (personnelModel!=null) {
					model.setUser_id(personnelModel.getUser_id());
				}
			}
		}
		List<YJWYWorkTaskAreaUserNexusModel> list = domainService.save(models);
		return list.toArray(new YJWYWorkTaskAreaUserNexusModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areauser.service.YJWYWorkTaskAreaUserNexusBusinessService#update(com.shareworx.ezfm.worktask.areauser.model.YJWYWorkTaskAreaUserNexusModel[])
	 */
	@Override
	public YJWYWorkTaskAreaUserNexusModel[] update(YJWYWorkTaskAreaUserNexusModel[] models) throws ShareworxServiceException {
		List<YJWYWorkTaskAreaUserNexusModel> list = domainService.update(models);
		return list.toArray(new YJWYWorkTaskAreaUserNexusModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areauser.service.YJWYWorkTaskAreaUserNexusBusinessService#delete(com.shareworx.ezfm.worktask.areauser.model.YJWYWorkTaskAreaUserNexusModel[])
	 */
	@Override
	public YJWYWorkTaskAreaUserNexusModel[] delete(YJWYWorkTaskAreaUserNexusModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}
}
