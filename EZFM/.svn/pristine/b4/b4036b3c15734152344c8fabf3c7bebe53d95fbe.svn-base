package com.shareworx.ezfm.energyloss.tabledefinition.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel;
import com.shareworx.ezfm.baseinfo.area.service.YJWYAreaDomainService;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectDomainService;
import com.shareworx.ezfm.baseinfo.resources.dao.YJWYResourcesDao;
import com.shareworx.ezfm.device.fmdata.service.YJWYRoomBusinessService;
import com.shareworx.ezfm.device.util.service.YJWYDeviceService;
import com.shareworx.ezfm.energyloss.tabledefinition.dao.YJWYEnergyElectricDao;
import com.shareworx.ezfm.energyloss.tabledefinition.model.YJWYEnergyElectricModel;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.model.IObjectModel;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.platform.util.StringUtils;

/**
 * 电表业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYEnergyElectricBusinessService.ID)
public class YJWYEnergyElectricBusinessServiceImpl implements YJWYEnergyElectricBusinessService {

	@Autowired
	@Qualifier(YJWYEnergyElectricDomainService.ID)
	private YJWYEnergyElectricDomainService domainService;
	
	public void setDomainService(YJWYEnergyElectricDomainService domainService) {
		this.domainService = domainService;
	}
	
	
	@Autowired
	@Qualifier(YJWYRoomBusinessService.ID)
	private YJWYRoomBusinessService areaservice;
	public void setAreaservice(YJWYRoomBusinessService areaservice) {
		this.areaservice = areaservice;
	}
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;

	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	@Autowired
	@Qualifier(YJWYProjectDomainService.ID)
	private YJWYProjectDomainService projectService;

	public void setProjectService(YJWYProjectDomainService projectService) {
		this.projectService = projectService;
	}
	@Autowired
	@Qualifier(YJWYAreaDomainService.ID)
	private YJWYAreaDomainService areaService;

	public void setAreaService(YJWYAreaDomainService areaService) {
		this.areaService = areaService;
	}
	
	@Autowired
	@Qualifier(YJWYDeviceService.ID)
	private YJWYDeviceService deviceService;
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.service.YJWYEnergyElectricBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYEnergyElectricModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYEnergyElectricModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYEnergyElectricModel[0];
		}
		return list.toArray(new YJWYEnergyElectricModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.service.YJWYEnergyElectricBusinessService#load(com.shareworx.ezfm.energyloss.tabledefinition.model.YJWYEnergyElectricModel)
	 */
	@Override
	public YJWYEnergyElectricModel[] load(YJWYEnergyElectricModel model) throws ShareworxServiceException {
		List<YJWYEnergyElectricModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYEnergyElectricModel[0];
		}
		return list.toArray(new YJWYEnergyElectricModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.service.YJWYEnergyElectricBusinessService#save(com.shareworx.ezfm.energyloss.tabledefinition.model.YJWYEnergyElectricModel[])
	 */
	@Override
	public YJWYEnergyElectricModel[] save(YJWYEnergyElectricModel[] models) throws ShareworxServiceException {
		List<YJWYEnergyElectricModel> list = domainService.save(models);
		return list.toArray(new YJWYEnergyElectricModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.service.YJWYEnergyElectricBusinessService#update(com.shareworx.ezfm.energyloss.tabledefinition.model.YJWYEnergyElectricModel[])
	 */
	@Override
	public YJWYEnergyElectricModel[] update(YJWYEnergyElectricModel[] models) throws ShareworxServiceException {
		List<YJWYEnergyElectricModel> list = domainService.update(models);
		return list.toArray(new YJWYEnergyElectricModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.tabledefinition.service.YJWYEnergyElectricBusinessService#delete(com.shareworx.ezfm.energyloss.tabledefinition.model.YJWYEnergyElectricModel[])
	 */
	@Override
	public YJWYEnergyElectricModel[] delete(YJWYEnergyElectricModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

	@Override
	public YJWYEnergyElectricModel[] queryTree(String pk_crop, String id, int type,String energy, String getsomeArea)
			throws ShareworxServiceException {
		// 返回树集合
		List<YJWYEnergyElectricModel> list = new ArrayList<YJWYEnergyElectricModel>();
		if (type == 1) {
			YJWYEnergyElectricModel model = null;
				YJWYAreaModel[] area=UserUtil.getUserArea();
//				拼接区域
				for (YJWYAreaModel yjwyAreaModel : area) {
					model = new YJWYEnergyElectricModel();
					model.put("id", yjwyAreaModel.getPk_area());
					model.put("name", yjwyAreaModel.getArea_name());
					model.put("pk_area", yjwyAreaModel.getPk_area());
					model.put("isParent", true);
					model.put("isSilent", false);
					model.put("nocheck", false);
					model.put("type", 2);
					list.add(model);
				}
		} else if (type == 2) {
			YJWYProjectModel[] projectIds=UserUtil.getUserProject(id);
			YJWYEnergyElectricModel model = null;
//			拼接项目
			for (YJWYProjectModel yjwyProjectModel : projectIds) {
				model = new YJWYEnergyElectricModel();
				model.put("id", yjwyProjectModel.getPk_project());
				model.put("name", yjwyProjectModel.getProject_name());
				model.put("pId", yjwyProjectModel.getPk_area());
				model.put("pk_project", yjwyProjectModel.getPk_project());
				model.put("pk_area" , yjwyProjectModel.getPk_area());
				model.put("isParent", true);
				model.put("type", 3);
				model.put("bim_url",yjwyProjectModel.getBim_url());
				list.add(model);
			}
		}else if(type == 3){
			String sql="select pk_project,eq_id,star_num,eq_name,pk_area,parent_id,enable_time,install_time,eq_code,"
					+ "version,surface_num,expect_use_time,purpose,pk_resource,"
					+ "rate,metering_range,notice_num,hydropower from yjwy_energy_electric"
					+ " where parent_id is null  and pk_project='"+ id+ "' and delete_flag='0'";
			if(energy!=null&&energy!=""){
				sql = sql+" and hydropower = '"+ energy+"'";
			}
			JdbcTemplate read = dao.getReadTemplate();
			list=read.query(sql, new RowMapper<YJWYEnergyElectricModel>() {

				@Override
				public YJWYEnergyElectricModel mapRow(ResultSet rs, int rowNum) throws SQLException {
					YJWYEnergyElectricModel eelmodel=new YJWYEnergyElectricModel();
					String install_area="";
						 install_area=areaservice.getRoomPlaceByResourceId(rs.getString("pk_resource"));
					
					eelmodel.put("pk_area", rs.getString("pk_area"));
					eelmodel.put("id", rs.getString("eq_id"));
					eelmodel.put("eq_id", rs.getString("eq_id"));
					eelmodel.put("name", rs.getString("eq_name"));
					eelmodel.put("eq_name", rs.getString("eq_name"));
					eelmodel.put("pId",rs.getString("parent_id") );
					eelmodel.put("eq_code", rs.getString("eq_code"));
					eelmodel.put("version", rs.getString("version"));
					eelmodel.put("surface_num", rs.getString("surface_num"));
					eelmodel.put("install_time", rs.getString("install_time"));
					eelmodel.put("enable_time", rs.getString("enable_time"));
					eelmodel.put("expect_use_time", rs.getString("expect_use_time"));
					
					eelmodel.put("install_area", install_area);
					
					eelmodel.put("star_num", rs.getString("star_num"));
					eelmodel.put("fk_resource_id", rs.getString("pk_resource"));
					eelmodel.put("rate", rs.getString("rate"));
					eelmodel.put("pk_project", rs.getString("pk_project"));
					eelmodel.put("metering_range", rs.getString("metering_range"));
					eelmodel.put("notice_num", rs.getString("notice_num"));
					eelmodel.put("hydropower", rs.getString("hydropower"));
					eelmodel.put("purpose", rs.getString("purpose"));
					if(!StringUtils.isEmpty(rs.getString("parent_id"))){
						eelmodel.put("hasParent", "1");
					}else{
						eelmodel.put("hasParent", "0");
					}

					Query eelQuery = Query.from(YJWYEnergyElectricModel.META_ID);
					eelQuery.and(Condition.create("parent_id", rs.getString("eq_id")));
					YJWYEnergyElectricDao eeldao = SpringUtils.getBean(YJWYEnergyElectricDao.ID);
					long count = eeldao.countListByCondition(eelQuery);
					if (count > 0) {
						eelmodel.put("isParent", true);
					}
					eelmodel.put("type", 4);
					return eelmodel;
					
				}
			});
			
		}else{
			String sql="select parent_id,pk_project,pk_area,star_num,eq_id,eq_name,parent_id,enable_time,install_time,pk_resource,"
					+ "eq_code,version,surface_num,expect_use_time,rate,metering_range,purpose,"
					+ "notice_num,hydropower from yjwy_energy_electric where parent_id ='"+id+ "' and delete_flag='0'";
			if(energy!=null&&energy!=""){
				sql = sql+" and hydropower = '"+ energy+"'";
			}
			JdbcTemplate read = dao.getReadTemplate();
			list=read.query(sql, new RowMapper<YJWYEnergyElectricModel>(){

				@Override
				public YJWYEnergyElectricModel mapRow(ResultSet rs, int rowNum) throws SQLException {
					YJWYEnergyElectricModel eelmodel=new YJWYEnergyElectricModel();
					String install_area=areaservice.getRoomPlaceByResourceId(rs.getString("pk_resource"));
					eelmodel.put("id", rs.getString("eq_id"));
					eelmodel.put("eq_id", rs.getString("eq_id"));
					eelmodel.put("name", rs.getString("eq_name"));
					eelmodel.put("eq_name", rs.getString("eq_name"));
					eelmodel.put("pId",rs.getString("parent_id") );
					eelmodel.put("eq_code", rs.getString("eq_code"));
					eelmodel.put("version", rs.getString("version"));
					eelmodel.put("fk_resource_id", rs.getString("pk_resource"));
					eelmodel.put("star_num", rs.getString("star_num"));
					eelmodel.put("surface_num", rs.getString("surface_num"));
					eelmodel.put("install_time", rs.getString("install_time"));
					eelmodel.put("enable_time", rs.getString("enable_time"));
					eelmodel.put("expect_use_time", rs.getString("expect_use_time"));
					
					eelmodel.put("install_area",install_area);
					
					eelmodel.put("rate", rs.getString("rate"));
					eelmodel.put("pk_project", rs.getString("pk_project"));
					eelmodel.put("metering_range", rs.getString("metering_range"));
					eelmodel.put("notice_num", rs.getString("notice_num"));
					eelmodel.put("hydropower", rs.getString("hydropower"));
					eelmodel.put("purpose", rs.getString("purpose"));
					if(!StringUtils.isEmpty(rs.getString("parent_id"))){
						eelmodel.put("hasParent", "1");
					}else{
						eelmodel.put("hasParent", "0");
					}

					Query eelQuery = Query.from(YJWYEnergyElectricModel.META_ID);
					eelQuery.and(Condition.create("parent_id", rs.getString("eq_id")));
					YJWYEnergyElectricDao eeldao = SpringUtils.getBean(YJWYEnergyElectricDao.ID);
					long count = eeldao.countListByCondition(eelQuery);
					if (count > 0) {
						eelmodel.put("isParent", true);
					}
					eelmodel.put("type", 4);
					return eelmodel;
				}
				});
			if (ArrayUtils.isEmpty(list)) {
				return new YJWYEnergyElectricModel[0];
			}
		}
		return list.toArray(new YJWYEnergyElectricModel[0]);
	}

}
