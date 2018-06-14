package com.shareworx.ezfm.device.list.service;

import java.util.*;

import com.shareworx.platform.persist.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.device.fmdata.model.YJWYEqModel;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.device.list.dao.YJWYListDao;
import com.shareworx.ezfm.device.list.model.YJWYListModel;
import com.shareworx.ezfm.device.siteproject.service.YJWYSiteProjectService;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.device.util.service.YJWYDeviceService;
import com.shareworx.ezfm.device.util.vo.ParamEntity;
import com.shareworx.ezfm.utils.UserUtil;

/**
 * 设备列表业务操作实现
 * 
 * @author jin.li
 *
 */
@Service(YJWYListService.ID)
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class YJWYListServiceImpl implements YJWYListService {

	@Autowired
	@Qualifier(YJWYListDao.ID)
	private YJWYListDao yjwyListDao;

	public void setYjwyListDao(YJWYListDao yjwyListDao) {
		this.yjwyListDao = yjwyListDao;
	}

	@Autowired
	@Qualifier(YJWYSiteProjectService.ID)
	private YJWYSiteProjectService siteProjectService;

	private JdbcTemplate readTemplate = DatabaseConnections.getReadTemplate();

	public void setSiteProjectService(YJWYSiteProjectService siteProjectService) {
		this.siteProjectService = siteProjectService;
	}

	@Autowired
	@Qualifier(YJWYDeviceService.ID)
	private YJWYDeviceService deviceService;

	public void setDeviceService(YJWYDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	/**
	 * 设备列表查询
	 */
	public List<YJWYListModel> queryList(ParamEntity params) {
		String sqlString = this.getSqlString(params, true);
		return yjwyListDao.queryList(sqlString);
	}
	
	public List<YJWYListModel> queryList2(ParamEntity params) {
		String sqlString = this.getSqlString2(params, true);
		return yjwyListDao.queryList(sqlString);
	}

	private String getSqlString2(ParamEntity params, boolean delete_flag) {
		Integer page = params.getPage();
		Integer rows = params.getRows();
		String eqKey = params.getEqKey();
		
		String area_name = params.getArea_name();
		String project_name = params.getProject_name();
		String rm_id = params.getRm_id();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("eq.eq_id eq_id,eq.csi_id csi_id,eq.fm_code fm_code,");
		sql.append("eq.parameter1 parameter1,eq.parameter2 parameter2,");
		sql.append("eq.name eq_name,eq.power power,eq.brand brand,");
		sql.append("eq.model model,eq.factory factory,eq.use_dept use_dept,");
		sql.append("eq.service_dept service_dept,eq.site_id site_id,");
		sql.append("eq.use_name use_name,eq.status status,eq.rm_id rm_id,");
		sql.append("eq.active active,eq.eq_description eq_description,");
		sql.append("eq.last_modify_user last_modify_user,eq.last_modify_time last_modify_time,");
		sql.append("eq.create_by create_by,eq.create_date create_date,");
		sql.append("dict.dict_name_ csi_name,room.name rm_name,");
		sql.append("eq.eq_code_ as eq_code,");
		sql.append("eq.eq_sys_name,");
		sql.append("eq.fk_eq_sys_id,");
		sql.append("eq.fk_sys_all_ids");
		sql.append(" FROM yjwy_fmdata_eq eq ");
		sql.append("  JOIN yjwy_pub_dict dict ");
		sql.append(" ON eq.csi_id = dict.pk_dict_ ");
		sql.append("  JOIN yjwy_fmdata_room room ");
		sql.append(" ON eq.rm_id = room.rm_id ");
		sql.append(" WHERE eq.delete_flag <> 1 AND eq.pk_crop = '" + DeviceUtil.getPkCrop() + "' ");

		if (!StringUtils.isEmpty(rm_id) && !"default".equals(rm_id)) {
			sql.append(" AND eq.rm_id = '" + rm_id + "' ");
		} else {
			Set<String> site_ids = params.getSite_ids();
			String siteids = "";
			if(site_ids!=null) {
				String[] ids = site_ids.toArray(new String[]{});
				for(int i=0;i<ids.length;i++) {
					siteids += ("'"+ids[i]+"'");
					if(i!=ids.length-1) {
						siteids+=",";
					}
				}
			}
			if (site_ids != null && site_ids.size() > 0) {
				sql.append(" AND eq.site_id in ("+siteids+")");
			} else {
				sql.append(" AND 1=2");
			}
		}
		if(params.getActive() != null){
			sql.append(" and active = "+params.getActive()+" " );
		}
		if (!StringUtils.isEmpty(eqKey)) {
			sql.append(" AND ( eq.fm_code LIKE '%" + eqKey + "%' ");
			sql.append(" OR eq.name LIKE '%" + eqKey + "%') ");
		}
		if(StringUtils.isEmpty(params.getSort())) {
			sql.append(" ORDER BY eq.dms_update_time desc ");
		} else {
			sql.append(" ORDER BY "+params.getSort()+" "+ params.getOrder());
		}
		
		if (delete_flag && null != page && null != rows) {
			sql.append(" LIMIT " + (page - 1) * rows + "," + rows);
		}
		return sql.toString();
	}
	/**
	 * 查询总数
	 */
	public Long queryCount(ParamEntity params) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(eq.eq_id) from (" + this.getSqlString(params, false) + ") eq");
		return yjwyListDao.queryCount(sql.toString());
	}

	/**
	 * 获取sql语句
	 * 
	 * @return
	 */
	private String getSqlString(ParamEntity params, boolean delete_flag) {
		Integer page = params.getPage();
		Integer rows = params.getRows();
		String eqKey = params.getEqKey();
		
		String area_name = params.getArea_name();
		String project_name = params.getProject_name();
		String rm_id = params.getRm_id();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("eq.eq_id eq_id,eq.csi_id csi_id,eq.fm_code fm_code,");
		sql.append("eq.parameter1 parameter1,eq.parameter2 parameter2,");
		sql.append("eq.name eq_name,eq.power power,eq.brand brand,");
		sql.append("eq.model model,eq.factory factory,eq.use_dept use_dept,");
		sql.append("eq.service_dept service_dept,eq.site_id site_id,");
		sql.append("eq.use_name use_name,eq.status status,eq.rm_id rm_id,");
		sql.append("eq.active active,eq.eq_description eq_description,");
		sql.append("eq.last_modify_user last_modify_user,eq.last_modify_time last_modify_time,");
		sql.append("eq.create_by create_by,eq.create_date create_date,");
		sql.append("dict.dict_name_ csi_name,room.name rm_name,");
		sql.append("eq.eq_code_ as eq_code,");
		sql.append("eq.eq_sys_name,");
		sql.append("eq.fk_eq_sys_id,");
		sql.append("eq.fk_sys_all_ids");
		sql.append(" FROM yjwy_fmdata_eq eq ");
		sql.append("  JOIN yjwy_pub_dict dict ");
		sql.append(" ON eq.csi_id = dict.pk_dict_ ");
//		sql.append(" JOIN yjwy_fmdata_csi csi ");
//		sql.append(" ON eq.csi_id = csi.csi_id ");
		sql.append("  JOIN yjwy_fmdata_room room ");
		sql.append(" ON eq.rm_id = room.rm_id ");
		sql.append(" WHERE eq.delete_flag <>  1 AND eq.pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		if (!StringUtils.isEmpty(rm_id) && !"default".equals(rm_id)) {
			sql.append(" AND eq.rm_id = '" + rm_id + "' ");
		}
		//区域
		if(!StringUtils.isEmpty(area_name) && !"区域选择".equals(area_name)){
			sql.append(" AND room.name like '" + area_name + "%' ");
		}
		//项目
		if(!StringUtils.isEmpty(project_name) && !"项目选择".equals(project_name)){

			//保留之前名称查询
			if(params.getList() == null){
				if(area_name != null){
					sql.append(" AND room.name like '" + area_name+"|"+project_name + "%' ");
				}else{
					sql.append(" AND room.name like '"+project_name + "%' ");
				}
			}else if(params.getList() != null && params.getList().size()>0){//roomID 查询


				sql.append(" and room.rm_id in (");
				List<String> list = params.getList();
				for(int i = 0 ;i < list.size();i++){
					if(list.size()-1 == i){
						sql.append("'"+list.get(i)+"'");
					}else {
						sql.append("'"+list.get(i)+"',");
					}

				}
				sql.append(")");
			}else if(params.getList() != null && params.getList().size()==0){
				sql.append(" and room.rm_id in ('')");
			}

		}

		//默认查询当前人所属项目所有数据
		Set<String> projectIds = deviceService.queryProjectIdsByPkUser(UserUtil.getCurrentUserPk());
		if (projectIds != null && projectIds.size() > 0) {
			sql.append(" AND "+ DeviceUtil.getInNotInSql("eq.site_id", QueryContents.TYPE_IN, projectIds.toArray(new String[] {})));
		} else {
			sql.append("AND 1=2");
		}
		
		
		
		if(params.getActive() != null){
			sql.append("and active = "+params.getActive()+" " );
		}

		if(!StringUtils.isEmpty(params.getFk_eq_sys_id())){
			sql.append(" and fk_sys_all_ids like '%"+params.getFk_eq_sys_id()+"%'" );
		}
//		} else if (!StringUtils.isEmpty(pk_project) && !"default".equals(pk_project)) {
//			String[] siteIds = siteProjectService.queryIds(new String[] { pk_project });
//			if (siteIds.length != 0) {
//				sql.append(" AND " + DeviceUtil.getInNotInSql("eq.site_id", QueryContents.TYPE_IN, siteIds));
//			} else {
//				sql.append(" AND 1=2");
//			}
//		} else if (!StringUtils.isEmpty(pk_area) && !"default".equals(pk_area)) {
//			String[] projectIds = DeviceUtil.getPrimaryKeyValue(
//					(DomainModel[]) deviceService.getProjectModelsByPkArea(pk_area).toArray(new YJWYProjectModel[] {}));
//			String[] siteIds = siteProjectService.queryIds(projectIds);
//			if (siteIds.length != 0) {
//				sql.append(" AND " + DeviceUtil.getInNotInSql("eq.site_id", QueryContents.TYPE_IN, siteIds));
//			} else {
//				sql.append(" AND 1=2");
//			}
//		} else {
//			Set<String> projectIds = deviceService.queryProjectIdsByPkUser(UserUtil.getCurrentUserPk());
//			String[] siteIds = siteProjectService.queryIds(projectIds.toArray(new String[] {}));
//			if (siteIds.length != 0) {
//				sql.append(" AND " + DeviceUtil.getInNotInSql("eq.site_id", QueryContents.TYPE_IN, siteIds));
//			} else {
//				sql.append(" AND 1=2");
//			}
//		}
		if (!StringUtils.isEmpty(eqKey)) {
			sql.append(" AND ( eq.fm_code LIKE '%" + eqKey + "%' ");
			sql.append(" OR eq.name LIKE '%" + eqKey + "%') ");
		}
		if(StringUtils.isEmpty(params.getSort())) {
			sql.append(" ORDER BY eq.dms_update_time desc ");
		} else {
			sql.append(" ORDER BY "+params.getSort()+" "+ params.getOrder());
		}
		
		if (delete_flag && null != page && null != rows) {
			sql.append(" LIMIT " + (page - 1) * rows + "," + rows);
		}
		return sql.toString();
	}

	@SuppressWarnings("static-access")
	public String queryPk_area(String pk_project) {
		Query query = Query.select(YJWYProjectModel.PK_AREA).from(YJWYProjectModel.META_ID)
				.where(Condition.create("PK_PROJECT_", pk_project));
		return yjwyListDao.queryPk_area(query);
	}

	@Override
	public List<Map<String, Object>> queryEqWorkTask(String project_name) {
		List<Map<String,Object>> list = new ArrayList<>();
		String sql = "SELECT * " +
				"FROM yjwy_worktask_details " +
				"LEFT JOIN  yjwy_fmdata_eq  eq on eq.eq_id = yjwy_worktask_details.fk_repair_equipment " +
				"JOIN yjwy_fmdata_room room  ON eq.rm_id = room.rm_id " +
				"where room.name like '%"+project_name+"%' " +
				"LIMIT 0,5";
		list = readTemplate.queryForList(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> queryEqPatrolTask(String pk_project) {
		List<Map<String,Object>> list = new ArrayList<>();
//		String sql = "SELECT * " +
//				"FROM yjwy_patrol_taskeq " +
//				"LEFT JOIN  yjwy_fmdata_eq  eq on eq.eq_id = yjwy_patrol_taskeq.eq_id " +
//				"LEFT JOIN yjwy_patrol_task on yjwy_patrol_task.task_id = yjwy_patrol_taskeq.task_id  " +
//				"JOIN yjwy_fmdata_room room  ON eq.rm_id = room.rm_id " +
//				"where room.name like '%"+project_name+"%' " +
//				"LIMIT 0,5 ";


		String sql = "SELECT DISTINCT eq.site_id, eq.eq_id,eq.eq_code_,(select count(pk_id) from yjwy_patrol_taskeq WHERE yjwy_patrol_taskeq.eq_id =eq.eq_id ) as count,eq.name,room.name as place,yjwy_pub_dict.dict_name_ FROM yjwy_fmdata_eq eq\n" +
				"LEFT JOIN yjwy_fmdata_room room  ON eq.rm_id = room.rm_id " +
				"LEFT JOIN yjwy_pub_dict  ON pk_dict_ = eq.csi_id " +
				"Right JOIN yjwy_patrol_taskeq ON yjwy_patrol_taskeq.eq_id =eq.eq_id " +
				"where eq_code_ != 'test' and eq.site_id = '"+pk_project+"' and " +
				"(select count(pk_id) from yjwy_patrol_taskeq WHERE yjwy_patrol_taskeq.eq_id =eq.eq_id ) >0 ";
		list = readTemplate.queryForList(sql);
		return list;
	}

	public List<Map<String, Object>> queryEqPatrolTaskByEqId(String eq_id){
		List<Map<String,Object>> list = new ArrayList<>();
		String sql = "SELECT\n" +
				"\tyjwy_patrol_task.plan_name,yjwy_patrol_task.begin_time,yjwy_patrol_task.end_time,yjwy_patrol_task.task_type,eq.eq_id\n" +
				"FROM\n" +
				"\tyjwy_patrol_taskeq\n" +
				"LEFT JOIN yjwy_fmdata_eq eq ON eq.eq_id = yjwy_patrol_taskeq.eq_id\n" +
				"LEFT JOIN yjwy_patrol_task ON yjwy_patrol_task.task_id = yjwy_patrol_taskeq.task_id\n" +
				"\n" +
				"WHERE eq.eq_id = '"+eq_id+"'\n" +
				"\t\n" +
				"LIMIT 0,\n" +
				" 5\n";
		list = readTemplate.queryForList(sql);
		return list;
	}
	@Override
	public int deleteByCondition(String eq_id) throws ShareworxServiceException {
		String sql = "delete from " + YJWYEqModel.META_ID + " where " + YJWYEqModel.EQ_ID + " = '" + eq_id + "'";
		return yjwyListDao.deleteByCondition(sql);
	}
}
