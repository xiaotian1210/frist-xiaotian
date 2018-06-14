package com.shareworx.ezfm.device.patrol.plan.service;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.DomainModel;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.persist.service.BaseDomainService;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.device.patrol.plan.dao.YJWYPlanDao;
import com.shareworx.ezfm.device.patrol.plan.dao.YJWYPlanEqDao;
import com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanEqModel;
import com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanModel;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.device.util.service.YJWYDeviceService;
import com.shareworx.ezfm.device.util.vo.ParamEntity;
import com.shareworx.ezfm.utils.UserUtil;

/**
 * 巡检维保计划业务操作实现
 * 
 * @author jin.li
 *
 */
@Service(YJWYPlanService.ID)
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class YJWYPlanServiceImpl implements YJWYPlanService {

	@Autowired
	@Qualifier(YJWYPlanDao.ID)
	private YJWYPlanDao planDao;

	public void setPlanDao(YJWYPlanDao planDao) {
		this.planDao = planDao;
	}

	@Autowired
	@Qualifier(YJWYPlanEqDao.ID)
	private YJWYPlanEqDao planEqDao;

	public void setPlanEqDao(YJWYPlanEqDao planEqDao) {
		this.planEqDao = planEqDao;
	}

	@Autowired
	@Qualifier(YJWYDeviceService.ID)
	private YJWYDeviceService deviceService;

	public void setDeviceService(YJWYDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;

	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/**
	 * 查询设备列表
	 */
	public List<Map<String, Object>> queryEqMap(String plan_id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("pe.plan_id plan_id,pe.eq_id eq_id,pe.rm_id rm_id,pe.csi_id csi_id, ");
		sql.append("eq.fm_code fm_code,eq.name eq_name,eq.power power,eq.brand brand, ");
		sql.append("eq.model model,eq.factory factory,eq.use_dept use_dept, ");
		sql.append("eq.service_dept service_dept,eq.site_id site_id,eq.use_name use_name, ");
		sql.append("eq.status status,eq.active active,eq.eq_description eq_description, ");
		sql.append("csi.dict_name_ csi_name,rm.name rm_name ");
		sql.append("FROM yjwy_patrol_plan_eq pe ");
		sql.append("JOIN yjwy_fmdata_eq eq ");
		sql.append("on pe.eq_id = eq.eq_id ");
		sql.append("JOIN yjwy_fmdata_room rm ");
		sql.append("on pe.rm_id = rm.rm_id  ");
		sql.append("JOIN yjwy_pub_dict csi  ");
		sql.append("on pe.csi_id = csi.pk_dict_  ");
		if (null != plan_id) {
			sql.append(" where pe.plan_id='" + plan_id + "'");
		}
		return planDao.queryMap(sql.toString());
	}

	/**
	 * 计划联合查询
	 */
	@Override
	public List<Map<String, Object>> queryMap(ParamEntity params) {
		return planDao.queryMap(this.getSqlString(params, true));
	}

	/**
	 * 联合数量查询
	 */
	@Override
	public Long queryCount(ParamEntity params) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(p.plan_id) from (" + this.getSqlString(params, false) + ") p");
		return planDao.queryCount(sql.toString());
	}

	/**
	 * 获取sql语句
	 * 
	 * @return
	 */
	private String getSqlString(ParamEntity params, boolean flag) {
		Integer page = params.getPage();
		Integer rows = params.getRows();
		String plan_type = params.getPlan_type();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append(" p.plan_id plan_id,p.plan_name plan_name,p.pk_area pk_area, ");
		sql.append(" p.pk_project pk_project,p.start_time start_time,p.end_time end_time, ");
		sql.append(" p.group_id pk_group,p.frequency frequency,p.plan_type plan_type, ");
		sql.append(" p.is_enable is_enable,p.next_time next_time,p.pk_crop pk_crop, ");
		sql.append(" p.create_time create_time,p.create_user create_user, ");
		sql.append(" p.update_time update_time,p.update_user update_user, ");
		sql.append(" a.area_name_ area_name,pro.project_name_ project_name,g.group_name group_name ");
		sql.append(" from yjwy_patrol_plan p  ");
		sql.append(" LEFT JOIN yjwy_pub_area a  ");
		sql.append(" ON p.pk_area = a.pk_area_  ");
		sql.append(" LEFT JOIN yjwy_pub_project pro  ");
		sql.append(" ON p.pk_project = pro.pk_project_  ");
		sql.append(" LEFT JOIN yjwy_executor_group g ");
		sql.append(" ON p.group_id = g.pk_group ");
		// 计划类型
		if (!DeviceUtil.stringIsEmpty(plan_type)) {
			sql.append(" where p.plan_type = '" + plan_type + "'");
		}
		// 查询当前用户的项目
		Set<String> project_ids = params.getProject_ids();
		String projectids = "";
		if (project_ids != null) {
			String[] ids = project_ids.toArray(new String[] {});
			for (int i = 0; i < ids.length; i++) {
				projectids += ("'" + ids[i] + "'");
				if (i != ids.length - 1) {
					projectids += ",";
				}
			}
		}
		if (project_ids != null && project_ids.size() > 0) {
			sql.append(" AND p.pk_project in (" + projectids + ")");
		} else {
			sql.append(" AND 1=2");
		}
		
		 
		sql.append(" AND p.pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		sql.append(" ORDER BY p.CREATE_TIME DESC ");
		if (flag && null != page && null != rows) {
			sql.append(" LIMIT " + (page - 1) * rows + "," + rows);
		}
		return sql.toString();
	}

	public YJWYPlanModel queryOne(Serializable id) {
		return planDao.queryOneByCondition(Query.from(YJWYPlanModel.META_ID).where(new Condition("plan_id", QueryContents.TYPE_EQ, id)));
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
	public YJWYPlanModel[] saveModels(YJWYPlanModel[] models) {
		YJWYPlanModel yjwyPlanModel = models[0];
		// 设置初始任务生成日期
		yjwyPlanModel.setNext_time(this.getInitNext_time(yjwyPlanModel));
		// 频率
		String frequency_num = yjwyPlanModel.getFrequency_num();
		String frequency_unit = yjwyPlanModel.getFrequency_unit();
		String frequency = frequency_num + "/" + frequency_unit;
		yjwyPlanModel.setFrequency(frequency);
		service.save(YJWYPlanModel.META_ID, yjwyPlanModel);
		planEqDao.saveModels(this.savePlanEqModels(yjwyPlanModel));
		return new YJWYPlanModel[] { yjwyPlanModel };
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
	public YJWYPlanModel[] updateModels(YJWYPlanModel[] models) {
		YJWYPlanModel yjwyPlanModel = models[0];
		// 设置初始任务生成日期
		yjwyPlanModel.setNext_time(this.getInitNext_time(yjwyPlanModel));
		// 频率
		String frequency_num = yjwyPlanModel.getFrequency_num();
		String frequency_unit = yjwyPlanModel.getFrequency_unit();
		String frequency = frequency_num + "/" + frequency_unit;
		yjwyPlanModel.setFrequency(frequency);
		planDao.updateModels(new YJWYPlanModel[] { yjwyPlanModel });
		planEqDao.deleteByCondition(new Delete(YJWYPlanEqModel.META_ID).where(new Condition("plan_id", QueryContents.TYPE_EQ, yjwyPlanModel.getPlan_id())));
		planEqDao.saveModels(this.savePlanEqModels(yjwyPlanModel));
		return new YJWYPlanModel[] { yjwyPlanModel };
	}

	/**
	 * 获取初始任务生成日期
	 * 
	 * @param model
	 * @return
	 */
	public String getInitNext_time(YJWYPlanModel model) {
		// 获取参数
		String now_time = DeviceUtil.date2String(new Date(), "yyyy-MM-dd");
		String start_time = model.getStart_time();
		String end_time = model.getEnd_time();
		Date now_date = DeviceUtil.string2Date(now_time, "yyyy-MM-dd");
		Date start_date = DeviceUtil.string2Date(start_time, "yyyy-MM-dd");
		Date end_date = DeviceUtil.string2Date(end_time, "yyyy-MM-dd");
		// 下次生成时间
		Date next_date = null;
		// 开始日期小于当前日期，并且结束日期大于当前日期。下次生成日期为当前日期的下一天。
		// 开始日期小于当前日期，并且结束日期小于等于当前日期。下次生成日期为结束日期。
		// 开始日期等于当前日期，下次生成日期为当前日期的下一天。
		if (now_date.compareTo(start_date) >= 0) {
			if (now_date.compareTo(end_date) < 0) {
				next_date = DeviceUtil.getNextDate(now_date, 1);
			} else {
				// 下次生成日期为结束日期。
				next_date = end_date;
			}
		} else {
			// 开始日期大于当前日期，下次生成日期为开始日期。
			next_date = start_date;
		}
		String next_time = null;
		if (null != next_date) {
			next_time = DeviceUtil.date2String(next_date, "yyyy-MM-dd");
		}
		return next_time;
	}

	/**
	 * 对中间表进行数据保存
	 * 
	 * @param groupModel
	 * @return
	 */
	private YJWYPlanEqModel[] savePlanEqModels(YJWYPlanModel model) {
		Map<String, Object> map = null;
		String plan_id = model.getPlan_id();
		// 获取设备数组
		JSONArray eqs = JSONArray.parseArray(model.getEqs());
		// 获取分类数组
		JSONArray csis = JSONArray.parseArray(model.getCsis());
		// 分类与工艺关联map
		Map<String, Object> csisMap = new HashMap<>();
		Map<String, Object> csiTemp = null;
		for (int i = 0; i < csis.size(); i++) {
			csiTemp = (Map<String, Object>) csis.get(i);
			csisMap.put((String) csiTemp.get("csi_id"), csiTemp.get("pmp_id"));
		}
		YJWYPlanEqModel[] planEqModels = null;
		// 遍历设备
		if (eqs.size() > 0 && eqs != null) {
			planEqModels = new YJWYPlanEqModel[eqs.size()];
			YJWYPlanEqModel planEqModel = null;

			for (int i = 0; i < eqs.size(); i++) {
				map = (Map<String, Object>) eqs.get(i);
				planEqModel = new YJWYPlanEqModel();
				// 设置参数
				planEqModel.setPlan_id(plan_id);
				planEqModel.setEq_id((String) map.get("eq_id"));
				planEqModel.setRm_id((String) map.get("rm_id"));
				planEqModel.setCsi_id((String) map.get("csi_id"));
				planEqModel.setPmp_id(csisMap.get(map.get("csi_id")).toString());
				DeviceUtil.setPkCrop(planEqModel);
				planEqModels[i] = planEqModel;
			}
		}
		return planEqModels;
	}

	/**
	 * 删除model
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
	public YJWYPlanModel[] deleteModels(YJWYPlanModel[] models, String[] plan_ids) {
		planEqDao.deleteByCondition(new Delete(YJWYPlanEqModel.META_ID).where(new Condition("plan_id", QueryContents.TYPE_IN, plan_ids)));
		planDao.deleteModels(models);
		return models;
	}

	@Override
	public YJWYPlanModel[] queryModelsByNextTime(String now_time) {
		StringBuilder sql = new StringBuilder();
		sql.append("select ");
		sql.append("plan_id,plan_name,pk_area,pk_project,start_time,end_time,group_id,frequency,plan_type,");
		sql.append("is_enable,next_time,pk_crop,create_time,create_user,update_time,update_user ");
		sql.append("from yjwy_patrol_plan ");
		sql.append("where is_enable = 1 ");
		sql.append("and next_time = '" + now_time + "' ");
		// 结束日期比下次生成日期小，不生成任务
		sql.append("and end_time >= next_time ");
		List<YJWYPlanModel> planList = planDao.queryModels(sql.toString());
		YJWYPlanModel[] planModels = new YJWYPlanModel[planList.size()];
		for (int i = 0; i < planList.size(); i++) {
			planModels[i] = planList.get(i);
		}
		return planModels;
	}

}
