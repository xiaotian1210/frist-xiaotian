package com.shareworx.ezfm.problem.nexus.proandclass.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.problem.classadmin.service.ProblemClassBusinessService;
import com.shareworx.ezfm.problem.nexus.proanduser.service.YJWYProjectInfoUserNexusBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.area.dao.YJWYAreaDao;
import com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel;
import com.shareworx.ezfm.baseinfo.area.service.YJWYAreaBusinessService;
import com.shareworx.ezfm.baseinfo.project.dao.YJWYProjectDao;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.problem.classadmin.model.ProblemClassModel;
import com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusModel;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.platform.util.StringUtils;

/**
 * 项目与报事类型关系表业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYProjectInfoClassNexusBusinessService.ID)
public class YJWYProjectInfoClassNexusBusinessServiceImpl implements YJWYProjectInfoClassNexusBusinessService {

	@Autowired
	@Qualifier(YJWYProjectInfoClassNexusDomainService.ID)
	private YJWYProjectInfoClassNexusDomainService domainService;
	
	public void setDomainService(YJWYProjectInfoClassNexusDomainService domainService) {
		this.domainService = domainService;
	}
	@Autowired
	@Qualifier(YJWYAreaBusinessService.ID)
	private YJWYAreaBusinessService areaService;

	public void setAreaService(YJWYAreaBusinessService areaService) {
		this.areaService = areaService;
	}
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;

	@Autowired
	@Qualifier(ProblemClassBusinessService.ID)
	private ProblemClassBusinessService service;



    @Autowired
    @Qualifier(YJWYProjectInfoUserNexusBusinessService.ID)
    private YJWYProjectInfoUserNexusBusinessService userNexusBusinessService;

    public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proandclass.service.YJWYProjectInfoClassNexusBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYProjectInfoClassNexusModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYProjectInfoClassNexusModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYProjectInfoClassNexusModel[0];
		}
		return list.toArray(new YJWYProjectInfoClassNexusModel[0]);
	}
	@Override
	public YJWYProjectInfoClassNexusModel[] queryAreaAndProject(String class_id) throws ShareworxServiceException {
		List<YJWYProjectInfoClassNexusModel> list = new ArrayList<YJWYProjectInfoClassNexusModel>();
		if (StringUtils.isEmpty(class_id)) {
			//拿到区域
			YJWYAreaModel[] areaModels =this.queryArea();
			YJWYProjectInfoClassNexusModel probjectInfoModel;
			for (int i = 0; i < areaModels.length; i++) {
				probjectInfoModel = new YJWYProjectInfoClassNexusModel();
				probjectInfoModel.put("id",  areaModels[i].get("pk_area"));
				probjectInfoModel.put("name", areaModels[i].get("area_name"));
				probjectInfoModel.put("pk_area", areaModels[i].get("pk_area"));
				probjectInfoModel.put("type", 1);
				probjectInfoModel.put("isParent", true);
				list.add(probjectInfoModel);
			}
		}else{
			//拿到所有项目
			YJWYProjectModel[] projectModels = queryProjectByArea(class_id);
			YJWYProjectInfoClassNexusModel probjectInfoModel;
			for (YJWYProjectModel model:projectModels) {
					probjectInfoModel = new YJWYProjectInfoClassNexusModel();
					probjectInfoModel.put("id", model.get("pk_project"));
					probjectInfoModel.put("name", model.get("project_name"));
					probjectInfoModel.put("pId",  class_id);
					probjectInfoModel.put("type", 2);
                    ProblemClassModel[] problemClassModels = service.projectClassquery(null, model.getPk_project());
                    String classnames  = "";
                    for(ProblemClassModel item:problemClassModels){
                        Object selected = item.get("selected");
                        if(selected != null){
                            classnames+= item.get("name")+"、 ";
                        }
                    }
                String pk = model.getPk_project();
                YJWYUserModel[] userByProjectId = userNexusBusinessService.findUserByProjectId(pk, "", 0, 5);
                String usr = "";
                for(YJWYUserModel item:userByProjectId){
                    if(item.get("fk_standardedition") != null){
                        usr +=item.getUser_name()+"、 ";
                    }

                }
                probjectInfoModel.put("classnames",classnames);
                probjectInfoModel.put("usr",usr);
                    list.add(probjectInfoModel);
			}
		}
		
		/*YJWYProjectInfoClassNexusModel probjectInfoModel;
		List<YJWYProjectInfoClassNexusModel> list = new ArrayList<YJWYProjectInfoClassNexusModel>();
		for (int i =0;i<areaModels.length;i++) {
			probjectInfoModel = new YJWYProjectInfoClassNexusModel();
			probjectInfoModel.put("id",  areaModels[i].get("pk_area"));
			probjectInfoModel.put("name", areaModels[i].get("area_name"));
			probjectInfoModel.put("pk_area", areaModels[i].get("pk_area"));
			probjectInfoModel.put("type", 1);
			list.add(probjectInfoModel);
			for (YJWYProjectModel model:projectModels) {
				if (areaModels[i].get("pk_area").equals(model.getPk_area())) {
					probjectInfoModel = new YJWYProjectInfoClassNexusModel();
					probjectInfoModel.put("id", model.get("pk_project"));
					probjectInfoModel.put("name", model.get("project_name"));
//					probjectInfoModel.put("classnames", this.getProjectNexusClass(model.get("pk_project").toString()));
					probjectInfoModel.put("pId",  areaModels[i].get("pk_area"));
					probjectInfoModel.put("type", 2);
					list.add(probjectInfoModel);
				}
			}
		}*/
		if(ArrayUtils.isEmpty(list)){
			return new YJWYProjectInfoClassNexusModel[0];
		}
		return list.toArray(new YJWYProjectInfoClassNexusModel[0]);
	}
	
	/**
	 * 根据项目，查询关联分类名称
	 * @param pk_project
	 * @return
	 */
	public String getProjectNexusClass(String pk_project){
		JdbcTemplate read = dao.getReadTemplate();
		String classNames = "";
		String sql = "select tab2.class_name from yjwy_proinfo_class_nexus tab1 "
				+ "left join yjwy_problem_class tab2 on tab1.class_id=tab2.pk_class_id";
		if (!StringUtils.isEmpty(pk_project)) {
			sql +=" where tab1.project_id ='"+pk_project+"'";
		}else{
			sql +=" where 1!=1";
		}
		List<String> list = read.query(sql, new RowMapper<String>(){
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				String class_name = "";
				if (!StringUtils.isEmpty(rs.getString("class_name"))) {
					class_name = rs.getString("class_name");
				}
				return class_name;
			}
		});
		for (String name:list) {
			classNames += name+",";
		}
		return classNames;
	}
	/**
	 * 查找区域
	 * @param areaModels
	 * @return
	 */
	public YJWYAreaModel[] queryArea(){
		Query query = Query.from(YJWYAreaModel.META_ID);
		//添加公司查询条件。
		query.and(Condition.create("pk_crop_", UserUtil.getCurrentUser().getPk_crop()));
		query.and(Condition.create("delete_flag_",0));
		YJWYAreaDao domainDao = SpringUtils.getBean(YJWYAreaDao.ID);
		List<YJWYAreaModel> list = domainDao.queryListByCondition(query);;
		if(ArrayUtils.isEmpty(list)){
			return new YJWYAreaModel[0];
		}
		return list.toArray(new YJWYAreaModel[0]);
	}
	/**
	 * 根据区域查找项目
	 * @param areaModels
	 * @return
	 */
	public YJWYProjectModel[] queryProjectByArea(String class_id){
		Query query = Query.from(YJWYProjectModel.META_ID);
		//添加公司查询条件。
		query.and(Condition.create("pk_crop_", UserUtil.getCurrentUser().getPk_crop()));
		query.and(Condition.create("delete_flag_",0));
		query.and(Condition.create("pk_area_",class_id));
		YJWYProjectDao domainDao = SpringUtils.getBean(YJWYProjectDao.ID);
		List<YJWYProjectModel> list = domainDao.queryListByCondition(query);;
		if(ArrayUtils.isEmpty(list)){
			return new YJWYProjectModel[0];
		}
		return list.toArray(new YJWYProjectModel[0]);
	}
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proandclass.service.YJWYProjectInfoClassNexusBusinessService#load(com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusModel)
	 */
	@Override
	public YJWYProjectInfoClassNexusModel[] load(YJWYProjectInfoClassNexusModel model) throws ShareworxServiceException {
		List<YJWYProjectInfoClassNexusModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYProjectInfoClassNexusModel[0];
		}
		return list.toArray(new YJWYProjectInfoClassNexusModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proandclass.service.YJWYProjectInfoClassNexusBusinessService#save(com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusModel[])
	 */
	@Override
	public YJWYProjectInfoClassNexusModel[] save(YJWYProjectInfoClassNexusModel[] models) throws ShareworxServiceException {
		List<YJWYProjectInfoClassNexusModel> list = domainService.save(models);
		return list.toArray(new YJWYProjectInfoClassNexusModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proandclass.service.YJWYProjectInfoClassNexusBusinessService#update(com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusModel[])
	 */
	@Override
	public YJWYProjectInfoClassNexusModel[] update(YJWYProjectInfoClassNexusModel[] models) throws ShareworxServiceException {
		List<YJWYProjectInfoClassNexusModel> list = domainService.update(models);
		return list.toArray(new YJWYProjectInfoClassNexusModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.nexus.proandclass.service.YJWYProjectInfoClassNexusBusinessService#delete(com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusModel[])
	 */
	@Override
	public YJWYProjectInfoClassNexusModel[] delete(YJWYProjectInfoClassNexusModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
