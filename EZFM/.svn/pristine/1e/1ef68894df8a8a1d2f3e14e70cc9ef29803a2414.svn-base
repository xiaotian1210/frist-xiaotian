package com.shareworx.ezfm.worktask.statistics.action;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserBusinessService;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.device.util.service.YJWYDeviceService;
import com.shareworx.ezfm.device.util.vo.ParamEntity;
import com.shareworx.ezfm.utils.ImpAndExpExcel;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.ezfm.worktask.areaproject.model.YJWYWorkTaskAreaProjectNexusModel;
import com.shareworx.ezfm.worktask.areaproject.service.YJWYWorkTaskAreaProjectNexusDomainService;
import com.shareworx.ezfm.worktask.areauser.model.YJWYWorkTaskAreaUserNexusModel;
import com.shareworx.ezfm.worktask.areauser.service.YJWYWorkTaskAreaUserNexusBusinessService;
import com.shareworx.ezfm.worktask.statistics.service.YJWYWorkTaskStatisticsService;

/**
 * 绩效考核统计表控制器
 * 
 * @author jin.li
 *
 */
@Controller
@RequestMapping("ezfm/worktask/performance")
public class YJWYWorkTaskPerformanceAction {

	final static Logger log = Logger.getLogger(YJWYWorkTaskPerformanceAction.class);

	/** 跳转页面 */
	public final static String PAGE_FORWARD = "ezfm/workTask/statistics/worktask_performance_list";

	@Autowired
	@Qualifier(YJWYWorkTaskStatisticsService.ID)
	private YJWYWorkTaskStatisticsService statisticsService;

	public void setStatisticsService(YJWYWorkTaskStatisticsService statisticsService) {
		this.statisticsService = statisticsService;
	}

	@Autowired
	@Qualifier(YJWYWorkTaskAreaProjectNexusDomainService.ID)
	private YJWYWorkTaskAreaProjectNexusDomainService areaProjectNexusDomainService;

	public void setAreaProjectNexusDomainService(YJWYWorkTaskAreaProjectNexusDomainService areaProjectNexusDomainService) {
		this.areaProjectNexusDomainService = areaProjectNexusDomainService;
	}

	@Autowired
	@Qualifier(YJWYWorkTaskAreaUserNexusBusinessService.ID)
	private YJWYWorkTaskAreaUserNexusBusinessService areaUserNexusBusinessService;

	public void setAreaUserNexusBusinessService(YJWYWorkTaskAreaUserNexusBusinessService areaUserNexusBusinessService) {
		this.areaUserNexusBusinessService = areaUserNexusBusinessService;
	}

	@Autowired
	@Qualifier(YJWYUserBusinessService.ID)
	private YJWYUserBusinessService userBusinessService;

	public void setUserBusinessService(YJWYUserBusinessService userBusinessService) {
		this.userBusinessService = userBusinessService;
	}

	@Autowired
	@Qualifier(YJWYDeviceService.ID)
	private YJWYDeviceService deviceService;

	public void setDeviceService(YJWYDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	/**
	 * 转向列表页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView listForward() {
		return new ModelAndView(PAGE_FORWARD);
	}

	/**
	 * 查询数据集合
	 * 
	 * @return
	 */
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult query(ParamEntity params) {
		// 获取参数
		Integer page = params.getPage();
		Integer rows = params.getRows();
		String pk_area = params.getPk_area();
		String pk_project = params.getPk_project();
		String pk_user = params.getPk_user();
		String start_time = params.getStart_time();
		String end_time = params.getEnd_time();
		StringBuilder projectSql = new StringBuilder("");
		StringBuilder areaSql = new StringBuilder("");
		StringBuilder userSql = new StringBuilder("");
		StringBuilder dateSql = new StringBuilder("");
		if (!DeviceUtil.stringIsEmpty(pk_user) && !"default".equals(pk_user)) {
			userSql.append(" tt.repair_user_id = '" + pk_user + "' ");
		} else if (!DeviceUtil.stringIsEmpty(pk_project) && !"default".equals(pk_project)) {
			projectSql.append(" tt.pk_project = '" + pk_project + "' ");
		} else if (!DeviceUtil.stringIsEmpty(pk_area) && !"default".equals(pk_area)) {
			areaSql.append(" tt.pk_area = '" + pk_area + "' ");
		} else {
			Set<String> set = deviceService.queryProjectIdsByPkUser(UserUtil.getCurrentPk());
			if (!ArrayUtils.isEmpty(set)) {
				projectSql.append(" " + DeviceUtil.getInNotInSql("tt.pk_project", QueryContents.TYPE_IN, set.toArray(new String[] {})) + " ");
			}else {
				projectSql.append(" 1=2 ");
			}
		}
		if (DeviceUtil.stringIsEmpty(start_time) && DeviceUtil.stringIsEmpty(end_time)) {
			start_time = DeviceUtil.CurrentMonthFirstDay(new Date(), DeviceUtil.YMDHMS);
			end_time = DeviceUtil.date2String(new Date(), DeviceUtil.YMDHMS);
		}
		dateSql.append(" d.create_time >= '" + start_time + "' and d.create_time <= '" + end_time + "' ");

		// 查询总单量
		StringBuilder orders_total = new StringBuilder();
		orders_total.append("select fk_project_id,repair_user_id,count(pk_details_id) orders_total from yjwy_worktask_details d ");
		orders_total.append("where repair_user_id is not null ");
		orders_total.append("and " + dateSql);
		orders_total.append("and d.pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		orders_total.append("group by repair_user_id,fk_project_id ");
		// 完成工时（分钟）
		StringBuilder all_duration = new StringBuilder();
		all_duration.append("select fk_project_id,repair_user_id,sum(finished_duration) all_duration from yjwy_worktask_details d ");
		all_duration.append("where task_state = 3 and" + dateSql);
		all_duration.append("and pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		all_duration.append("group by fk_project_id,repair_user_id ");
		// 协助工时（分钟）
		StringBuilder help_duration = new StringBuilder();
		help_duration.append("select t1.fk_user_id,d.fk_project_id,");
		help_duration.append("cast(sum(t1.work_proportion/100*d.finished_duration) as DECIMAL(10,2)) help_duration ");
		help_duration.append("from yjwy_worktask_assist_user_record t1 ");
		help_duration.append("LEFT JOIN yjwy_worktask_details d ON t1.fk_details_id = d.pk_details_id ");
		help_duration.append("where " + dateSql);
		help_duration.append("group by t1.fk_user_id,d.fk_project_id ");
		// 查询完成总单量
		StringBuilder complete_total = new StringBuilder();
		complete_total.append("select fk_project_id,repair_user_id,count(pk_details_id) complete_total ");
		complete_total.append("from yjwy_worktask_details d where task_state = 3 ");
		complete_total.append("and " + dateSql);
		complete_total.append("and pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		complete_total.append("group by repair_user_id,fk_project_id ");
		// 维修及时完成数量
		StringBuilder timely_total = new StringBuilder();
		timely_total.append("select fk_project_id,repair_user_id,count(pk_details_id) timely_total ");
		timely_total.append("from yjwy_worktask_details d ");
		timely_total.append("where task_state = 3 and (finished_duration-ration_duration)<=0 ");
		timely_total.append("and " + dateSql);
		timely_total.append("and pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		timely_total.append("group by fk_project_id,repair_user_id ");
		// 协助单量
		StringBuilder help_total = new StringBuilder();
		help_total.append("select d.fk_project_id,t1.fk_user_id,count(t1.fk_details_id) help_total ");
		help_total.append("from yjwy_worktask_assist_user_record t1 ");
		help_total.append("LEFT JOIN yjwy_worktask_details d on t1.fk_details_id = d.pk_details_id ");
		help_total.append("where " + dateSql);
		help_total.append("group by d.fk_project_id,t1.fk_user_id ");
		// 拒单量
		StringBuilder refuse_total = new StringBuilder();
		refuse_total.append("select d.fk_project_id,t1.operation_user_id,COUNT(pk_record_id) refuse_total ");
		refuse_total.append("from yjwy_worktask_details_record t1 ");
		refuse_total.append("LEFT JOIN yjwy_worktask_details d ON t1.fk_details_id = d.pk_details_id ");
		refuse_total.append("where operation_express = 'operationExpress4' ");
		refuse_total.append("and " + dateSql);
		refuse_total.append("group by d.fk_project_id,t1.operation_user_id ");
		// 户内维修单量
		StringBuilder indoors_total = new StringBuilder();
		indoors_total.append("select fk_project_id,repair_user_id,count(*) indoors_total from yjwy_worktask_details d ");
		indoors_total.append("where repair_user_id is not null and service_type = 'serviceCateOne' ");
		indoors_total.append("and " + dateSql);
		indoors_total.append("and pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		indoors_total.append("group by fk_project_id,repair_user_id ");
		// 户内维修工时（分钟）
		StringBuilder indoors_duraction = new StringBuilder();
		indoors_duraction.append("select fk_project_id,repair_user_id,sum(finished_duration) indoors_duraction from yjwy_worktask_details d ");
		indoors_duraction.append("where repair_user_id is not null and service_type = 'serviceCateOne' ");
		indoors_duraction.append("and " + dateSql);
		indoors_duraction.append("and pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		indoors_duraction.append("group by fk_project_id,repair_user_id ");
		// 公共区域单量
		StringBuilder public_total = new StringBuilder();
		public_total.append("select fk_project_id,repair_user_id,count(*) public_total from yjwy_worktask_details d ");
		public_total.append("where repair_user_id is not null and service_type = 'serviceCateTwo' ");
		public_total.append("and " + dateSql);
		public_total.append("and pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		public_total.append("group by fk_project_id,repair_user_id ");
		// 公共区域工时(分钟)
		StringBuilder public_duraction = new StringBuilder();
		public_duraction.append("select fk_project_id,repair_user_id,sum(finished_duration) public_duraction from yjwy_worktask_details d ");
		public_duraction.append("where repair_user_id is not null and service_type = 'serviceCateTwo' ");
		public_duraction.append("and " + dateSql);
		public_duraction.append("and pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		public_duraction.append("group by fk_project_id,repair_user_id ");
		// 维修满意单量
		StringBuilder satis_total = new StringBuilder();
		satis_total.append("select d.fk_project_id,d.repair_user_id,count(d.pk_details_id) satis_total from yjwy_worktask_details d  ");
		satis_total.append("left join yjwy_problem_details t2 on d.pk_details_id = t2.fk_details_id ");
		satis_total.append("left join yjwy_problem_record t3 on t2.pk_details_id = t3.fk_details_id ");
		satis_total.append("where d.service_type = 'serviceCateOne' and t3.evaluate_type in (1,2) ");
		satis_total.append("and " + dateSql);
		satis_total.append("and d.pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		satis_total.append("group by d.fk_project_id,d.repair_user_id ");
		// 维修回访单量
		StringBuilder visit_total = new StringBuilder();
		visit_total.append("select d.fk_project_id,d.repair_user_id,count(d.pk_details_id) visit_total from yjwy_worktask_details d ");
		visit_total.append("left join yjwy_problem_details t2 on d.pk_details_id = t2.fk_details_id ");
		visit_total.append("left join yjwy_problem_record t3 on t2.pk_details_id = t3.fk_details_id ");
		visit_total.append("where d.service_type = 'serviceCateOne' and t3.evaluate_type <> 0 ");
		visit_total.append("and " + dateSql);
		visit_total.append("and d.pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		visit_total.append("group by d.fk_project_id,d.repair_user_id ");

		// 抢单完成量
		StringBuilder grab_total = new StringBuilder();
		grab_total.append("select fk_project_id,repair_user_id,count(*) grab_total from yjwy_worktask_details d ");
		grab_total.append("where dispatch_type = 1 and task_state = 3 ");
		grab_total.append("and " + dateSql);
		grab_total.append("and d.pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		grab_total.append("group by fk_project_id,repair_user_id ");

		// 查询sql
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.fk_project_id pk_project,t1.repair_user_id,t14.project_name_ project_name,");
		sql.append("t14.pk_area_ pk_area,t15.area_name_ area_name,t16.user_name_ repair_user_name,");
		sql.append("(case when t1.orders_total is null then 0 else t1.orders_total end ) orders_total,");
		sql.append("(case when t2.all_duration is null then 0 else t2.all_duration end ) all_duration,");
		sql.append("(case when t3.help_duration is null then 0 else t3.help_duration end ) help_duration,");
		sql.append("(case when t4.complete_total is null then 0 else t4.complete_total end ) complete_total,");
		sql.append("(case when t5.timely_total is null then 0 else t5.timely_total end ) timely_total,");
		sql.append("(case when t6.help_total is null then 0 else t6.help_total end ) help_total,");
		sql.append("(case when t7.refuse_total is null then 0 else t7.refuse_total end ) refuse_total,");
		sql.append("(case when t8.indoors_total is null then 0 else t8.indoors_total end ) indoors_total,");
		sql.append("(case when t9.indoors_duraction is null then 0 else t9.indoors_duraction end ) indoors_duraction,");
		sql.append("(case when t10.public_total is null then 0 else t10.public_total end ) public_total,");
		sql.append("(case when t11.public_duraction is null then 0 else t11.public_duraction end ) public_duraction,");
		sql.append("(case when t12.satis_total is null then 0 else t12.satis_total end ) satis_total,");
		sql.append("(case when t13.grab_total is null then 0 else t13.grab_total end ) grab_total ");
		sql.append("from (");
		sql.append(orders_total);
		sql.append(") t1 LEFT JOIN (");
		sql.append(all_duration);
		sql.append(") t2 ON t1.fk_project_id = t2.fk_project_id AND t1.repair_user_id = t2.repair_user_id ");
		sql.append("LEFT JOIN (");
		sql.append(help_duration);
		sql.append(") t3 ON t1.fk_project_id = t3.fk_project_id AND t1.repair_user_id = t3.fk_user_id ");
		sql.append("LEFT JOIN (");
		sql.append(complete_total);
		sql.append(") t4 ON t1.fk_project_id = t4.fk_project_id AND t1.repair_user_id = t4.repair_user_id ");
		sql.append("LEFT JOIN (");
		sql.append(timely_total);
		sql.append(") t5 ON t1.fk_project_id = t5.fk_project_id AND t1.repair_user_id = t5.repair_user_id ");
		sql.append("LEFT JOIN (");
		sql.append(help_total);
		sql.append(") t6 ON t1.fk_project_id = t6.fk_project_id AND t1.repair_user_id = t6.fk_user_id ");
		sql.append("LEFT JOIN (");
		sql.append(refuse_total);
		sql.append(") t7 ON t1.fk_project_id = t7.fk_project_id AND t1.repair_user_id = t7.operation_user_id ");
		sql.append("LEFT JOIN (");
		sql.append(indoors_total);
		sql.append(") t8 ON t1.fk_project_id = t8.fk_project_id AND t1.repair_user_id = t8.repair_user_id ");
		sql.append("LEFT JOIN (");
		sql.append(indoors_duraction);
		sql.append(") t9 ON t1.fk_project_id = t9.fk_project_id AND t1.repair_user_id = t9.repair_user_id ");
		sql.append("LEFT JOIN (");
		sql.append(public_total);
		sql.append(") t10 ON t1.fk_project_id = t10.fk_project_id AND t1.repair_user_id = t10.repair_user_id ");
		sql.append("LEFT JOIN (");
		sql.append(public_duraction);
		sql.append(") t11 ON t1.fk_project_id = t11.fk_project_id AND t1.repair_user_id = t11.repair_user_id ");
		sql.append("LEFT JOIN (");
		sql.append(satis_total);
		sql.append(") t12 ON t1.fk_project_id = t12.fk_project_id AND t1.repair_user_id = t12.repair_user_id ");
		sql.append("LEFT JOIN (");
		sql.append(visit_total);
		sql.append(") t17 ON t1.fk_project_id = t17.fk_project_id AND t1.repair_user_id = t17.repair_user_id ");
		sql.append("LEFT JOIN (");
		sql.append(grab_total);
		sql.append(") t13 ON t1.fk_project_id = t13.fk_project_id AND t1.repair_user_id = t13.repair_user_id ");
		sql.append("LEFT JOIN yjwy_pub_project t14 ON t1.fk_project_id = t14.pk_project_ ");
		sql.append("LEFT JOIN yjwy_pub_area t15 ON t14.pk_area_ = t15.pk_area_ ");
		sql.append("LEFT JOIN yjwy_pub_user t16 ON t16.pk_user_ = t1.repair_user_id ");
		sql.append(") tt ");

		// 条件拼接
		if (!StringUtils.isEmpty(projectSql.toString()) || !StringUtils.isEmpty(areaSql.toString()) || !StringUtils.isEmpty(userSql.toString())) {
			sql.append("where " + userSql + projectSql + areaSql);
		}
		// 排序
		sql.append(" order by tt.repair_user_id ");
		long count = statisticsService.queryCount(sql.toString());
		// 分页
		if (null != page && null != rows) {
			sql.append(" limit " + (page - 1) * rows + "," + rows);
		}

		List<Map<String, Object>> models = statisticsService.queryList(sql.toString());
		// 计算
		for (Map<String, Object> map : models) {
			// 完成工时(小时)
			setDivisor(map, "all_duration", "60", "all_duration");
			// 协助工时(小时)
			setDivisor(map, "help_duration", "60", "help_duration");
			// 维修完成率
			setRate(map, "complete_total", "orders_total", "complete_rate");
			// 维修及时率
			setRate(map, "timely_total", "complete_total", "timely_rate");
			// 户内维修工时(小时)
			setDivisor(map, "indoors_duraction", "60", "indoors_duraction");
			// 公共区域工时(小时)
			setDivisor(map, "public_duraction", "60", "public_duraction");
			// 维修满意率
			setRate(map, "satis_total", "visit_total", "satis_rate");
		}
		ModelAndResult mar = new ModelAndResult();
		mar.setTotal(count);
		mar.setAttribute("rows", models);
		return mar;
	}

	/**
	 * 除数设值
	 * 
	 * @param map
	 * @param up
	 * @param down
	 * @param key
	 */
	private void setDivisor(Map<String, Object> map, String up, String down, String key) {
		Object upObject = map.get(up);
		Object downObject = map.get(down);
		if (upObject != null && downObject != null) {
			map.put(key, DeviceUtil.doubleForDivision(upObject.toString(), downObject.toString()));
		} else {
			map.put(key, "0.0");
		}
	}

	/**
	 * 概率设值
	 * 
	 * @param map
	 * @param up
	 * @param down
	 * @param key
	 */
	private void setRate(Map<String, Object> map, String up, String down, String key) {
		Object upObject = map.get(up);
		Object downObject = map.get(down);
		if (upObject != null && downObject != null) {
			map.put(key, DeviceUtil.getPercent(upObject.toString(), downObject.toString()));
		} else {
			map.put(key, "0.0%");
		}
	}

	/**
	 * 根据项目查询维修人员列表
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "queryUser", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult queryProject(ParamEntity params) {
		// 获取区域主键
		String pk_project = params.getPk_project();
		ModelAndResult mr = null;
		// 设置默认选中项
		ParamEntity paramEntity = new ParamEntity();
		paramEntity.setPk_user("default");
		paramEntity.setUser_name("人员选择");
		paramEntity.setSelected(true);
		// 声明返回data数组
		Object[] modelsRes = new Object[] { paramEntity };
		if (null != pk_project) {
			// 项目查片区
			YJWYWorkTaskAreaProjectNexusModel model = areaProjectNexusDomainService.queryOneByCondition(Query.from(YJWYWorkTaskAreaProjectNexusModel.META_ID).where(new Condition("project_id", QueryContents.TYPE_EQ, pk_project)));
			if (null != model) {
				Query areaUserQuery = Query.from(YJWYWorkTaskAreaUserNexusModel.META_ID);
				areaUserQuery.where(new Condition("user_type", QueryContents.TYPE_EQ, "2"));
				areaUserQuery.and(new Condition("area_id", QueryContents.TYPE_EQ, model.getArea_id()));
				// 片区查人员
				YJWYWorkTaskAreaUserNexusModel[] areaUserNexusModels = areaUserNexusBusinessService.query(areaUserQuery);
				if (DeviceUtil.arrayIsNotEmpty(areaUserNexusModels)) {
					String[] userIds = new String[areaUserNexusModels.length];
					for (int i = 0; i < areaUserNexusModels.length; i++) {
						userIds[i] = areaUserNexusModels[i].getUser_id();
					}
					Query userQuery = Query.from(YJWYUserModel.META_ID);
					userQuery.where(new Condition("pk_user_", QueryContents.TYPE_IN, userIds));
					// 人员信息
					YJWYUserModel[] userModels = userBusinessService.query(userQuery);
					// 数据总数
					int len = userModels.length;
					// 实例化data数组在数据总数基础上长度加1
					modelsRes = new Object[len + 1];
					// 将默认选中的项目添加进去
					modelsRes[0] = paramEntity;
					// 将数据遍历转存到data数组中
					for (int i = 0; i < len; i++) {
						modelsRes[i + 1] = userModels[i];
					}
				}
			}
		}
		mr = new ModelAndResult(modelsRes);
		return mr;
	}

	/**
	 * 导出报表
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "export", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult doExpDataStructureByTable(@RequestParam(value = "param", required = false) String param) {
		ParamEntity params = JSONObject.parseObject(param, ParamEntity.class);
		params.setPage(null);
		params.setRows(null);
		ModelAndResult mr = this.query(params);
		return ImpAndExpExcel.doExpExcel(mr.get("rows"), new String[] { "project_name", "repair_user_name", "all_duration", "help_duration", "complete_rate", "timely_rate", "orders_total", "help_total", "refuse_total", "indoors_total", "indoors_duraction", "public_total", "public_duraction", "satis_total", "satis_rate", "grab_total" }, "templates/templates/worktask/工单管理绩效考核统计表.xls", 2);
	}

}
