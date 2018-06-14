package com.shareworx.ezfm.worktask.repairclass.service;

import java.math.BigDecimal;
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

import com.shareworx.ezfm.baseinfo.user.service.YJWYUserDomainService;
import com.shareworx.ezfm.problem.classadmin.dao.ProblemClassDao;
import com.shareworx.ezfm.problem.classadmin.model.ProblemClassModel;
import com.shareworx.ezfm.system.dict.model.YJWYDictModel;
import com.shareworx.ezfm.system.dict.service.YJWYDictDomainService;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.ezfm.worktask.details.service.YJWYWorkTaskDetailsDomainService;
import com.shareworx.ezfm.worktask.repairclass.dao.YJWYWorkTaskRepairClassDao;
import com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.platform.util.StringUtils;

/**
 * 维修种类业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYWorkTaskRepairClassBusinessService.ID)
public class YJWYWorkTaskRepairClassBusinessServiceImpl implements YJWYWorkTaskRepairClassBusinessService {

	@Autowired
	@Qualifier(YJWYWorkTaskRepairClassDomainService.ID)
	private YJWYWorkTaskRepairClassDomainService domainService;
	
	@Autowired
	@Qualifier(YJWYDictDomainService.ID)
	private YJWYDictDomainService dictService;
	
	@Autowired
	@Qualifier(YJWYUserDomainService.ID)
	private YJWYUserDomainService userService;
	
	
	@Autowired
	@Qualifier(YJWYWorkTaskDetailsDomainService.ID)
	private YJWYWorkTaskDetailsDomainService workTaskDomainService;
	
	public void setDomainService(YJWYWorkTaskRepairClassDomainService domainService) {
		this.domainService = domainService;
	}

	public void setDictService(YJWYDictDomainService dictService) {
		this.dictService = dictService;
	}
	
	public void setUserService(YJWYUserDomainService userService) {
		this.userService = userService;
	}
	public void setWorkTaskDomainService(YJWYWorkTaskDetailsDomainService workTaskDomainService) {
		this.workTaskDomainService = workTaskDomainService;
	}
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.repairclass.service.YJWYWorkTaskRepairClassBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYWorkTaskRepairClassModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYWorkTaskRepairClassModel> list = domainService.queryListByCondition(query);
		//查询关联字段
		for (YJWYWorkTaskRepairClassModel model:list) {
			//获取创建人姓名
			if (!StringUtils.isEmpty(model.getCreate_user_id())) {
				model.put("create_user_name", userService.queryById(model.getCreate_user_id()).getUser_name());
			}
			if (!StringUtils.isEmpty(model.getCompany())) {
				model.put("company_name",dictName(model.getCompany()));
			}
			if (!StringUtils.isEmpty(model.getProject_class())) {
				model.put("project_class_name",dictName(model.getProject_class()));
			}
			if (!StringUtils.isEmpty(model.getRepair_class())) {
				model.put("repair_class_name",dictName(model.getRepair_class()));
			}
			if (!StringUtils.isEmpty(model.getService_major())) {
				model.put("service_major_name",dictName(model.getService_major()));
			}
			//位根目录是，sure_choice设置为true,可以关联项目。不是根目录不可以关联项目
			if (!StringUtils.isEmpty(model.getParent_id())) {
				if (model.getParent_id().equals("0")) {
					model.put("sure_choice",true);
				}else{
					model.put("sure_choice",false);
				}
			}
		}
		
		if(!ArrayUtils.isEmpty(list)){
			return this.mosaicTree(list.toArray(new YJWYWorkTaskRepairClassModel[0]));
		}else{
			return new YJWYWorkTaskRepairClassModel[0];
		}
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.repairclass.service.YJWYWorkTaskRepairClassBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYWorkTaskRepairClassModel[] queryTree(String class_id) throws ShareworxServiceException {
		JdbcTemplate read = dao.getReadTemplate();
		String sql = "select tab1.pk_class_id,tab1.class_name,tab1.class_code,tab1.parent_id,"
				+ " tab1.specifications,tab1.rated_worktime,tab1.labor_cost,tab1.material_cost,tab1.mechanics_cost"
				+ " ,tab1.repair_class,tab1.project_class,tab1.service_major,tab1.create_time,tab1.sort"
				+ " ,tab2.user_name_ as create_user_name,"
				+ "tab3.dict_name_ as project_class_name,tab4.dict_name_ as repair_class_name,"
				+ "tab5.dict_name_ as service_major_name,tab6.dict_name_ as company_name"
				+ " from ((((yjwy_worktask_repair_class tab1 left join yjwy_pub_user tab2 on tab1.create_user_id= tab2.pk_user_) "
				+ " left join yjwy_pub_dict tab3 on tab1.project_class=tab3.dict_code_)"
				+ " left join yjwy_pub_dict tab4 on tab1.repair_class=tab4.dict_code_)"
				+ " left join yjwy_pub_dict tab5 on tab1.repair_class=tab5.dict_code_)"
				+ " left join yjwy_pub_dict tab6 on tab1.company=tab6.dict_code_";
		sql +=" where 1=1 and pk_crop='"+UserUtil.getCurrentUser().getPk_crop()+"'"; 
		if (StringUtils.isEmpty(class_id)) {
			sql += " and (parent_id is null or parent_id='')";
		}else{
			sql += " and parent_id='"+class_id+"'";
		}
		sql +=" ORDER BY sort";
		List<YJWYWorkTaskRepairClassModel> list = read.query(sql, new RowMapper<YJWYWorkTaskRepairClassModel>(){
			@Override
			public YJWYWorkTaskRepairClassModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				YJWYWorkTaskRepairClassModel model = new YJWYWorkTaskRepairClassModel();
				//ID
				model.setPk_class_id(rs.getString("pk_class_id"));
				model.put("id", rs.getString("pk_class_id"));
				//名称
				model.setClass_name(rs.getString("class_name"));
				model.put("name", rs.getString("class_name"));
				//编码
				model.setClass_code(rs.getString("class_code"));
				//PID
				model.setParent_id(rs.getString("parent_id"));
				//规格
				model.setSpecifications(StringUtils.isEmpty(rs.getString("specifications"))?"":rs.getString("specifications"));
				//额定工时
				model.setRated_worktime(rs.getInt("rated_worktime"));
				//人工费用
				model.setLabor_cost(rs.getBigDecimal("labor_cost")==null?new BigDecimal("0"):rs.getBigDecimal("labor_cost"));
				//材料费用
				model.setMaterial_cost(rs.getBigDecimal("material_cost")==null?new BigDecimal("0"):rs.getBigDecimal("material_cost"));
				//机械费用
				model.setMechanics_cost(rs.getBigDecimal("mechanics_cost")==null?new BigDecimal("0"):rs.getBigDecimal("mechanics_cost"));
				//维修工种
				model.setRepair_class(rs.getString("repair_class"));
				model.put("repair_class_name", StringUtils.isEmpty(rs.getString("repair_class_name"))?"":rs.getString("repair_class_name"));
				//项目属性
				model.setProject_class(rs.getString("project_class"));
				//单位
				model.put("company_name",StringUtils.isEmpty(rs.getString("company_name"))?"":rs.getString("company_name"));
				//项目名称
				model.put("project_class_name", StringUtils.isEmpty(rs.getString("project_class_name"))?"":rs.getString("project_class_name"));
				//服务专业
				model.setService_major(rs.getString("service_major"));
				model.put("service_major_name", StringUtils.isEmpty(rs.getString("service_major_name"))?"":rs.getString("service_major_name"));
				//创建时间
				model.setCreate_time(rs.getString("create_time"));
				
				//创建人姓名
				model.put("create_user_name", rs.getString("create_user_name"));

				model.setSort(rs.getInt("sort"));
				
				//获取资源Query
				Query query = Query.from(YJWYWorkTaskRepairClassModel.META_ID);
				query.and(Condition.create("parent_id",rs.getString("pk_class_id")));
				YJWYWorkTaskRepairClassDao domainDao = SpringUtils.getBean(YJWYWorkTaskRepairClassDao.ID);
				long count = domainDao.countListByCondition(query);
				if (count>0) {
					model.put("isParent", true);
				}
				return model;
			}
			
		});
		if(ArrayUtils.isEmpty(list)){
			return new YJWYWorkTaskRepairClassModel[0];
		}
		return list.toArray(new YJWYWorkTaskRepairClassModel[0]);
	}
	@Override
	public YJWYWorkTaskRepairClassModel[] queryClassByProject(String projectId,String pk_details_id) throws ShareworxServiceException {
		//通过项目ID查询
		JdbcTemplate read = dao.getReadTemplate();
		List<YJWYWorkTaskRepairClassModel> list = new ArrayList<YJWYWorkTaskRepairClassModel>();
		String sql = "select  tab1.pk_class_id,tab1.class_name from yjwy_worktask_repair_class tab1 "
					+" left join yjwy_worktask_repair_class_project tab2 on tab1.pk_class_id=tab2.class_id "
					+" where (tab1.parent_id is null or tab1.parent_id='') and tab2.project_id = '"+projectId+"' ";
		list.addAll(read.query(sql, new RowMapper<YJWYWorkTaskRepairClassModel>(){
			@Override
			public YJWYWorkTaskRepairClassModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				YJWYWorkTaskRepairClassModel model = new YJWYWorkTaskRepairClassModel();
				model.setPk_class_id(rs.getString("pk_class_id"));
				model.setClass_name(rs.getString("class_name"));
				return model;
			}
			
		}));
		/*if (!StringUtils.isEmpty(pk_details_id)) {
			YJWYWorkTaskDetailsModel detailsModel = workTaskDomainService.queryById(pk_details_id);
			if (detailsModel!=null) {
				for (YJWYWorkTaskRepairClassModel model:list) {
					if (model.getPk_class_id().equals(detailsModel.getRepair_class_id())) {
						model.put("selected", true);
					}
				}
			}
		}*/
		if(!ArrayUtils.isEmpty(list)){
			return this.mosaicTree(list.toArray(new YJWYWorkTaskRepairClassModel[0]));
		}else{
			return new YJWYWorkTaskRepairClassModel[0];
		}
	}


	@Override
	public YJWYWorkTaskRepairClassModel[] queryClassByTree(String pk_details_id) throws ShareworxServiceException {
		JdbcTemplate read = dao.getReadTemplate();
		String sql = " select pk_class_id,class_name from yjwy_worktask_repair_class where parent_id ='"+pk_details_id+"'";
		List<YJWYWorkTaskRepairClassModel> list = read.query(sql, new RowMapper<YJWYWorkTaskRepairClassModel>(){
			@Override
			public YJWYWorkTaskRepairClassModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				YJWYWorkTaskRepairClassModel model = new YJWYWorkTaskRepairClassModel();
//				model.setPk_class_id(rs.getString("pk_class_id"));
//				model.setClass_name(rs.getString("class_name"));
				model.put("id", rs.getString("pk_class_id"));
				model.put("text", rs.getString("class_name"));
				model.setPk_class_id(rs.getString("pk_class_id"));
				model.put("sure_choice", false);
				return model;
			}
			
		});
		String sonSql = "select pk_class_id,class_name,parent_id,parent_id from yjwy_worktask_repair_class where parent_id in ("
				+ "select pk_class_id from yjwy_worktask_repair_class where parent_id ='"+pk_details_id+"')";
		List<YJWYWorkTaskRepairClassModel> sonList = read.query(sonSql, new RowMapper<YJWYWorkTaskRepairClassModel>(){
			@Override
			public YJWYWorkTaskRepairClassModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				YJWYWorkTaskRepairClassModel model = new YJWYWorkTaskRepairClassModel();
//				model.setPk_class_id(rs.getString("pk_class_id"));
//				model.setClass_name(rs.getString("class_name"));
				model.put("id", rs.getString("pk_class_id"));
				model.put("text", rs.getString("class_name"));
				model.put("sure_choice", true);
				model.setParent_id(rs.getString("parent_id"));
				return model;
			}
			
		});
		List<YJWYWorkTaskRepairClassModel> sonChildrenList;
		for (YJWYWorkTaskRepairClassModel model:list) {
			sonChildrenList = new ArrayList<YJWYWorkTaskRepairClassModel>();
			for (YJWYWorkTaskRepairClassModel sonModel:sonList) {
				if (!StringUtils.isEmpty(sonModel.getParent_id())&&!StringUtils.isEmpty(model.getPk_class_id())) {
					if (sonModel.getParent_id().equals(model.getPk_class_id())) {
						sonChildrenList.add(sonModel);
					}
				}
			}
			model.put("children", sonChildrenList);
		}
		if(!ArrayUtils.isEmpty(list)){
			return this.mosaicTree(list.toArray(new YJWYWorkTaskRepairClassModel[0]));
		}else{
			return new YJWYWorkTaskRepairClassModel[0];
		}
	}
	/**
	 * 拼接树数据
	 * @param models
	 * @return
	 */
	public YJWYWorkTaskRepairClassModel[] mosaicTree(YJWYWorkTaskRepairClassModel[] models){
		for (YJWYWorkTaskRepairClassModel model:models) {
			
//			dictService.
			model.put("id", model.get("pk_class_id"));
			model.put("name", model.get("class_name"));
			model.put("pId", model.get("parent_id"));
			//判断是否为叶子节点。
			Query query = Query.from(ProblemClassModel.META_ID);
			ProblemClassDao domainDao = SpringUtils.getBean(ProblemClassDao.ID);
			query.and(Condition.create("parent_id", model.get("pk_class_id")));
			if (domainDao.countListByCondition(query)>0) {
				model.put("isParent", true);
				model.put("open", true);
				model.put("nocheck",false);
			}else{
				model.put("nocheck",true);
			}
		}
		return models;
		
		
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.repairclass.service.YJWYWorkTaskRepairClassBusinessService#load(com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassModel)
	 */
	@Override
	public YJWYWorkTaskRepairClassModel[] load(YJWYWorkTaskRepairClassModel model) throws ShareworxServiceException {
		List<YJWYWorkTaskRepairClassModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYWorkTaskRepairClassModel[0];
		}
		return list.toArray(new YJWYWorkTaskRepairClassModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.repairclass.service.YJWYWorkTaskRepairClassBusinessService#save(com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassModel[])
	 */
	@Override
	public YJWYWorkTaskRepairClassModel[] save(YJWYWorkTaskRepairClassModel[] models) throws ShareworxServiceException {
		//获取当前操作时间字符串

		String time = this.obtainTime();
		for (YJWYWorkTaskRepairClassModel model:models) {
			if (StringUtils.isEmpty(model.getParent_id())) {
				model.setParent_id("");
				model.setWhether_root(1);
			}else{
				model.setWhether_root(2);
			}
			model.setCreate_time(time);
			//注入创建人ID
			model.setCreate_user_id(UserUtil.getCurrentUserPk());
			//注入最后修改时间戳
			model.setUpdate_time(System.currentTimeMillis()+"");
			//注入最后修改人ID
			model.setUpdate_user_id(UserUtil.getCurrentUserPk());
		}
		List<YJWYWorkTaskRepairClassModel> list = domainService.save(models);
		return list.toArray(new YJWYWorkTaskRepairClassModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.repairclass.service.YJWYWorkTaskRepairClassBusinessService#update(com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassModel[])
	 */
	@Override
	public YJWYWorkTaskRepairClassModel[] update(YJWYWorkTaskRepairClassModel[] models) throws ShareworxServiceException {
		YJWYWorkTaskRepairClassModel[] updateModels = new YJWYWorkTaskRepairClassModel[models.length];
		YJWYWorkTaskRepairClassModel updateModel;
		for (int i = 0; i < models.length; i++) {
			updateModel = domainService.queryById(models[i].getPk_class_id());
			//修改数据
			if (!StringUtils.isEmpty(models[i].getClass_code())) {
				updateModel.setClass_code(models[i].getClass_code());
			}
			if (!StringUtils.isEmpty(models[i].getClass_name())) {
				updateModel.setClass_name(models[i].getClass_name());
			}
			updateModel.setService_major(models[i].getService_major());
			updateModel.setRepair_class(models[i].getRepair_class());
			updateModel.setProject_class(models[i].getProject_class());
			updateModel.setSpecifications(models[i].getSpecifications());
			updateModel.setCompany(models[i].getCompany());
			updateModel.setRated_worktime(Integer.parseInt(models[i].get("rated_worktime").toString()));
			BigDecimal labor_cost=new BigDecimal(models[i].get("labor_cost").toString());
			BigDecimal material_cost=new BigDecimal(models[i].get("material_cost").toString());
			BigDecimal mechanics_cost=new BigDecimal(models[i].get("mechanics_cost").toString());
			updateModel.setLabor_cost(labor_cost);
			updateModel.setMaterial_cost(material_cost);
			updateModel.setMechanics_cost(mechanics_cost);
			updateModel.setSort(Integer.parseInt(models[i].get("sort").toString()));
			updateModel.setSupport_category(models[i].getSupport_category());
			updateModel.setPk_crop(UserUtil.getCurrentUser().getPk_crop());
//			updateModel.setPk_crop(models[i].getPk_crop());
			//注入最后修改时间戳
			updateModel.setUpdate_time(System.currentTimeMillis()+"");
			//注入最后修改人ID
			updateModel.setUpdate_user_id(UserUtil.getCurrentUserPk());
			updateModel.setUpdate_user_id(UserUtil.getCurrentUserPk());
			updateModels[i] = updateModel;
		}
		List<YJWYWorkTaskRepairClassModel> list = domainService.update(updateModels);
		return list.toArray(new YJWYWorkTaskRepairClassModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.repairclass.service.YJWYWorkTaskRepairClassBusinessService#delete(com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassModel[])
	 */
	@Override
	public YJWYWorkTaskRepairClassModel[] delete(YJWYWorkTaskRepairClassModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

	
	//公共方法，获取当前时间
	public String obtainTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(new Date());
	}
	
	public String dictName(String dictCode){
		YJWYDictModel dictModel = dictService.getDict(dictCode);
		if (dictModel!=null) {
			return dictModel.getDict_name();
		}
		return "";
	}
}
