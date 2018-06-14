package com.shareworx.ezfm.problem.classadmin.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserDomainService;
import com.shareworx.ezfm.problem.classadmin.dao.ProblemClassDao;
import com.shareworx.ezfm.problem.classadmin.model.ProblemClassModel;
import com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusModel;
import com.shareworx.ezfm.problem.nexus.proandclass.service.YJWYProjectInfoClassNexusDomainService;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.platform.util.StringUtils;

/**
 * 报事分类管理业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(ProblemClassBusinessService.ID)
public class ProblemClassBusinessServiceImpl implements ProblemClassBusinessService {

	@Autowired
	@Qualifier(ProblemClassDomainService.ID)
	private ProblemClassDomainService domainService;
	
	@Autowired
	@Qualifier(YJWYUserDomainService.ID)
	private YJWYUserDomainService userService;
	
	@Autowired
	@Qualifier(YJWYProjectInfoClassNexusDomainService.ID)
	private YJWYProjectInfoClassNexusDomainService projectService;
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	public void setDomainService(ProblemClassDomainService domainService) {
		this.domainService = domainService;
	}
	public void setProjectService(YJWYProjectInfoClassNexusDomainService projectService) {
		this.projectService = projectService;
	}

	public void setUserService(YJWYUserDomainService userService) {
		this.userService = userService;
	}
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.service.ProblemClassBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public ProblemClassModel[] query(Query query) throws ShareworxServiceException {
		List<ProblemClassModel> list = domainService.queryListByCondition(query);
		for (ProblemClassModel model:list) {
			if (!StringUtils.isEmpty(model.getCreate_user_id())) {
				YJWYUserModel userModel = userService.queryById(model.getCreate_user_id());
				if (userModel!=null) {
					model.put("create_user_name", userModel.getUser_name());
				}
			}
		}
		if(ArrayUtils.isEmpty(list)){
			return new ProblemClassModel[0];
		}
		return list.toArray(new ProblemClassModel[0]);
	}
	@Override
	public ProblemClassModel[] queryTree(String class_id) throws ShareworxServiceException { 
		JdbcTemplate read = dao.getReadTemplate();
		String sql = "select tab1.pk_class_id,tab1.class_name,tab1.class_code,tab1.parent_id,"
					+"tab1.whether_visit,tab1.project_attribute,tab1.sort,tab1.create_time,tab2.user_name_ as create_user_name "
					+"from yjwy_problem_class tab1 left join yjwy_pub_user tab2 on tab1.create_user_id= tab2.pk_user_ ";
		sql +=" where 1=1 and pk_crop='"+UserUtil.getCurrentUser().getPk_crop()+"'"; 
		if (StringUtils.isEmpty(class_id)) {
			sql += " and (parent_id is null or parent_id='')";
		}else{
			sql += " and parent_id='"+class_id+"'";
		}
		
		List<ProblemClassModel> list = read.query(sql, new RowMapper<ProblemClassModel>(){
			@Override
			public ProblemClassModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProblemClassModel model = new ProblemClassModel();
				//ID
				model.setPk_class_id(rs.getString("pk_class_id"));
				//名称
				model.setClass_name(rs.getString("class_name"));
				//编码
				model.setClass_code(rs.getString("class_code"));
				//PID
				model.setParent_id(rs.getString("parent_id"));
				//是否回访
				model.setWhether_visit(rs.getInt("whether_visit"));
				//项目属性
				model.setProject_attribute(rs.getString("project_attribute"));
				//排序
				model.setSort(rs.getInt("sort"));
				//创建时间
				model.setCreate_time(rs.getString("create_time"));
				//创建人姓名
				model.put("create_user_name", rs.getString("create_user_name"));
				
				
				
				Query query = Query.from(ProblemClassModel.META_ID);
				query.and(Condition.create("parent_id",rs.getString("pk_class_id")));
				ProblemClassDao domainDao = SpringUtils.getBean(ProblemClassDao.ID);
				long count = domainDao.countListByCondition(query);
				if (count>0) {
					model.put("isParent", true);
				}
				return model;
			}
			
		});
		if(ArrayUtils.isEmpty(list)){
			return new ProblemClassModel[0];
		}
		return list.toArray(new ProblemClassModel[0]);
	}
	@Override
	public ProblemClassModel[] projectClassquery(String class_id,String project_id) throws ShareworxServiceException {
		JdbcTemplate read = dao.getReadTemplate();
		String sql = "select tab1.pk_class_id,tab1.class_name,tab1.class_code,tab1.parent_id,"
					+"tab1.whether_visit,tab1.project_attribute,tab1.sort,tab1.create_time,tab2.user_name_ as create_user_name "
					+"from yjwy_problem_class tab1 left join yjwy_pub_user tab2 on tab1.create_user_id= tab2.pk_user_ ";
		sql +=" where 1=1 and pk_crop='"+UserUtil.getCurrentUser().getPk_crop()+"'"; 
		if (StringUtils.isEmpty(class_id)) {
			sql += " and (parent_id is null or parent_id='')";
		}else{
			sql += " and parent_id='"+class_id+"'";
		}
		
		List<ProblemClassModel> list = read.query(sql, new RowMapper<ProblemClassModel>(){
			@Override
			public ProblemClassModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProblemClassModel model = new ProblemClassModel();
				//ID
				model.setPk_class_id(rs.getString("pk_class_id"));
				//名称
				model.setClass_name(rs.getString("class_name"));
				//编码
				model.setClass_code(rs.getString("class_code"));
				//PID
				model.setParent_id(rs.getString("parent_id"));
				//是否回访
				model.setWhether_visit(rs.getInt("whether_visit"));
				//项目属性
				model.setProject_attribute(rs.getString("project_attribute"));
				//排序
				model.setSort(rs.getInt("sort"));
				//创建时间
				model.setCreate_time(rs.getString("create_time"));
				//创建人姓名
				model.put("create_user_name", rs.getString("create_user_name"));
				Query query = Query.from(ProblemClassModel.META_ID);
				query.and(Condition.create("parent_id",rs.getString("pk_class_id")));
				ProblemClassDao domainDao = SpringUtils.getBean(ProblemClassDao.ID);
				long count = domainDao.countListByCondition(query);
				if (count>0) {
					model.put("isParent", true);
				}
				return model;
			}
			
		});
		Query querys = Query.from(YJWYProjectInfoClassNexusModel.META_ID);
		querys.and(new Condition("project_id", QueryContents.TYPE_EQ, project_id));
		List<YJWYProjectInfoClassNexusModel> projectClassList= projectService.queryListByCondition(querys);
		for (ProblemClassModel classModel:list) {
			classModel.put("id", classModel.getPk_class_id());
			classModel.put("name",classModel.getClass_name());
			classModel.put("project_id",project_id);
			for (YJWYProjectInfoClassNexusModel model:projectClassList) {
				if (model.getClass_id()!=null&&model.getClass_id().equals(classModel.getPk_class_id())) {
					classModel.put("selected",true);
				}
			}
		}
		if(ArrayUtils.isEmpty(list)){
			return new ProblemClassModel[0];
		}
		return list.toArray(new ProblemClassModel[0]);
	}
	
	@Override
	public YJWYWorkTaskRepairClassModel[] queryClassByProject(String projectId,String pk_details_id) throws ShareworxServiceException {
		//通过项目ID查询人员
		JdbcTemplate read = dao.getReadTemplate();
		List<YJWYWorkTaskRepairClassModel> list = new ArrayList<YJWYWorkTaskRepairClassModel>();
		String sql = " select pk_class_id,class_name from yjwy_problem_class where pk_class_id in("
				+ "select class_id from yjwy_proinfo_class_nexus where project_id = '"+projectId+"') ";
		list.addAll(read.query(sql, new RowMapper<YJWYWorkTaskRepairClassModel>(){
			@Override
			public YJWYWorkTaskRepairClassModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				YJWYWorkTaskRepairClassModel model = new YJWYWorkTaskRepairClassModel();
				model.setPk_class_id(rs.getString("pk_class_id"));
				model.setClass_name(rs.getString("class_name"));
				return model;
			}
			
		}));
		if(!ArrayUtils.isEmpty(list)){
			return list.toArray(new YJWYWorkTaskRepairClassModel[0]);
		}else{
			return new YJWYWorkTaskRepairClassModel[0];
		}
	}
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.service.ProblemClassBusinessService#load(com.shareworx.ezfm.problem.classadmin.model.ProblemClassModel)
	 */
	@Override
	public ProblemClassModel[] load(ProblemClassModel model) throws ShareworxServiceException {
		List<ProblemClassModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new ProblemClassModel[0];
		}
		return list.toArray(new ProblemClassModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.service.ProblemClassBusinessService#save(com.shareworx.ezfm.problem.classadmin.model.ProblemClassModel[])
	 */
	@Override
	public ProblemClassModel[] save(ProblemClassModel[] models) throws ShareworxServiceException {
		//获取当前登录人信息
		String time = this.obtainTime();
		for (ProblemClassModel model:models) {
			//注入创建时间
			model.setCreate_time(time);
			model.setUpdate_time(System.currentTimeMillis()+"");
			model.setCreate_user_id(UserUtil.getCurrentUserPk());
			model.setUpdate_user_id(UserUtil.getCurrentUserPk());
			//当没选择父ID的时候，默认一级目录
			if (StringUtils.isEmpty(model.getParent_id())) {
				model.setRoot(1);
			}
			//转换项目属性，已逗号分隔
			if (model.get("project_attribute")!=null&&!StringUtils.isEmpty(model.get("project_attribute").toString())) {
				String attr = model.get("project_attribute").toString();
				if (attr.length()>1) {
					attr = attr.substring(1,attr.length()-1);
					attr = attr.replace(" ", ""); 
				}
				model.put("project_attribute", attr);
			}
		}
		List<ProblemClassModel> list = domainService.save(models);
		return list.toArray(new ProblemClassModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.service.ProblemClassBusinessService#update(com.shareworx.ezfm.problem.classadmin.model.ProblemClassModel[])
	 */
	@Override
	public ProblemClassModel[] update(ProblemClassModel[] models) throws ShareworxServiceException {
		
		String time = this.obtainTime();
		//获取当前登录人信息
		YJWYUserModel user = userService.queryById(UserUtil.getCurrentUserPk());
		for (ProblemClassModel model:models) {
			//注入修改时间
			model.setUpdate_time(System.currentTimeMillis()+"");
			model.setUpdate_user_id(UserUtil.getCurrentUserPk());
			model.setPk_crop(user.getPk_crop());
			//转换String格式
			String attr="";
			if (model.get("project_attribute")!=null) {
				attr = model.get("project_attribute").toString();
			}
			if (attr.length()>1) {
				attr = attr.substring(1,attr.length()-1);
				attr = attr.replace(" ", ""); 
			}
			model.put("project_attribute", attr);
		}
		List<String> editFields = new ArrayList<String>();
		editFields.add("class_name");
		editFields.add("whether_repair");
		editFields.add("whether_visit");
		editFields.add("project_attribute");
		editFields.add("time_limit");
		editFields.add("sort");
		editFields.add("update_time");
//		List<ProblemClassModel> list = domainService.update(models);
		List<ProblemClassModel> list = domainService.update(editFields, models);
		return list.toArray(new ProblemClassModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.service.ProblemClassBusinessService#delete(com.shareworx.ezfm.problem.classadmin.model.ProblemClassModel[])
	 */
	@Override
	public ProblemClassModel[] delete(ProblemClassModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}
	//公共方法，获取当前时间
	public String obtainTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(new Date());
	}
}
