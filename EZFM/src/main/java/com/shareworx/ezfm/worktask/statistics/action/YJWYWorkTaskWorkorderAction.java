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
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.device.util.service.YJWYDeviceService;
import com.shareworx.ezfm.device.util.vo.ParamEntity;
import com.shareworx.ezfm.utils.ImpAndExpExcel;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.ezfm.worktask.statistics.service.YJWYWorkTaskStatisticsService;

/**
 * 工单明细表控制器
 * 
 * @author jin.li
 *
 */
@Controller
@RequestMapping("ezfm/worktask/workorder")
public class YJWYWorkTaskWorkorderAction {

	final static Logger log = Logger.getLogger(YJWYWorkTaskWorkorderAction.class);

	/** 跳转页面 */
	public final static String PAGE_FORWARD = "ezfm/workTask/statistics/worktask_workorder_list";

	@Autowired
	@Qualifier(YJWYWorkTaskStatisticsService.ID)
	private YJWYWorkTaskStatisticsService workTaskStatisticsService;

	public void setWorkTaskStatisticsService(YJWYWorkTaskStatisticsService workTaskStatisticsService) {
		this.workTaskStatisticsService = workTaskStatisticsService;
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
		String start_time = params.getStart_time();
		String end_time = params.getEnd_time();
		StringBuilder projectSql = new StringBuilder("");
		StringBuilder areaSql = new StringBuilder("");
		StringBuilder dateSql = new StringBuilder("");
		if (!DeviceUtil.stringIsEmpty(pk_project) && !"default".equals(pk_project)) {
			projectSql.append(" tt.fk_project_id = '" + pk_project + "' ");
		} else if (!DeviceUtil.stringIsEmpty(pk_area) && !"default".equals(pk_area)) {
			areaSql.append(" tt.pk_area = '" + pk_area + "' ");
		} else {
			Set<String> set = deviceService.queryProjectIdsByPkUser(UserUtil.getCurrentPk());
			if (!ArrayUtils.isEmpty(set)) {
				projectSql.append(" " + DeviceUtil.getInNotInSql("tt.fk_project_id", QueryContents.TYPE_IN, set.toArray(new String[] {})) + " ");
			}else {
				projectSql.append(" 1=2 ");
			}
		}
		if (DeviceUtil.stringIsEmpty(start_time) && DeviceUtil.stringIsEmpty(end_time)) {
			start_time = DeviceUtil.CurrentMonthFirstDay(new Date(), DeviceUtil.YMDHMS);
			end_time = DeviceUtil.date2String(new Date(), DeviceUtil.YMDHMS);
		}
		dateSql.append(" tt.create_time >= '" + start_time + "' and tt.create_time <= '" + end_time + "' ");
		// 查询协助人姓名拼接
		StringBuilder helpUsers = new StringBuilder();
		helpUsers.append("select t1.pk_details_id,GROUP_CONCAT(t3.user_name_) help_users from yjwy_worktask_details t1 ");
		helpUsers.append("left join yjwy_worktask_assist_user_record t2 on t2.fk_details_id = t1.pk_details_id ");
		helpUsers.append("LEFT JOIN yjwy_pub_user t3 ON t2.fk_user_id = t3.pk_user_  ");
		helpUsers.append("where t1.pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		helpUsers.append("group by t1.pk_details_id ");
		// 查询报事满意程度
		StringBuilder evaluate = new StringBuilder();
		evaluate.append("select t1.pk_details_id pk_details_id,t2.pk_details_id problem_pk_details_id,");
		evaluate.append("t3.pk_record_id,t3.operate_type,t3.evaluate_type,");
		evaluate.append("t4.dict_name_ dict_name from yjwy_worktask_details t1 ");
		evaluate.append("LEFT JOIN yjwy_problem_details t2 ON t1.pk_details_id = t2.fk_details_id ");
		evaluate.append("LEFT JOIN yjwy_problem_record t3 ON t2.pk_details_id = t3.fk_details_id ");
		evaluate.append("LEFT JOIN (");
		evaluate.append("select * from yjwy_pub_dict where dict_parent_ = 'satisfyLevel' ");
		evaluate.append("AND pk_crop_ = '" + DeviceUtil.getPkCrop() + "' ");
		evaluate.append(") t4 ON t3.evaluate_type = t4.dict_sort_  WHERE t3.operate_type = 'operationExpress6' ");
		evaluate.append("and t1.pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		// 查询任务回访状态
		StringBuilder visit = new StringBuilder();
		visit.append("select t1.pk_details_id pk_details_id,t2.pk_details_id problem_pk_details_id,t2.state from yjwy_worktask_details t1 ");
		visit.append("LEFT JOIN yjwy_problem_details t2 ON t1.pk_details_id = t2.fk_details_id ");
		visit.append("where t1.pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		// 查询完成记录
		StringBuilder operationRemarks = new StringBuilder();
		operationRemarks.append("select t1.pk_details_id,t2.operation_express,t2.operation_remarks from yjwy_worktask_details t1 ");
		operationRemarks.append("LEFT JOIN yjwy_worktask_details_record t2 ON t1.pk_details_id = t2.fk_details_id ");
		operationRemarks.append("WHERE t2.operation_express = 'operationExpress6' ");
		operationRemarks.append("and t1.pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		// 查询二级三级维修分类
		StringBuilder className = new StringBuilder();
		className.append("SELECT t1.pk_details_id,GROUP_CONCAT(DISTINCT t3.class_name) three_class,GROUP_CONCAT(DISTINCT t4.class_name) two_class ");
		className.append("from yjwy_worktask_details t1 ");
		className.append("LEFT JOIN yjwy_worktask_son_class_record t2 ON t1.pk_details_id = t2.fk_details_id ");
		className.append("LEFT JOIN yjwy_worktask_repair_class t3 ON t3.pk_class_id = t2.fk_class_id ");
		className.append("LEFT JOIN yjwy_worktask_repair_class t4 ON t2.fk_two_levelclass_id = t4.pk_class_id ");
		className.append("where t1.pk_crop = '" + DeviceUtil.getPkCrop() + "' ");

		// 查询语句
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t12.dict_name_ service_name,t11.three_class,t11.two_class,t10.operation_remarks,t9.state,");
		sql.append("t8.problem_pk_details_id,t8.pk_record_id,t8.operate_type,t8.evaluate_type,t8.dict_name satisfaction,");
		sql.append("t7.help_users,t5.pk_area_ pk_area,t5.area_name_ area_name,");
		sql.append("t4.project_name_ project_name,t3.user_name_ repair_user_name,t2.class_name one_class,");
		sql.append("t1.* from yjwy_worktask_details t1 ");
		sql.append("left join yjwy_worktask_repair_class t2 on t1.repair_class_id = t2.pk_class_id ");
		sql.append("left join yjwy_pub_user t3 on t1.repair_user_id = t3.pk_user_ ");
		sql.append("left join yjwy_pub_project t4 on t1.fk_project_id = t4.pk_project_ ");
		sql.append("left join yjwy_pub_area t5 on t4.pk_area_ = t5.pk_area_ ");
		sql.append("left join ( ");
		sql.append(helpUsers);
		sql.append(") t7 on t1.pk_details_id = t7.pk_details_id ");
		sql.append("left join ( ");
		sql.append(evaluate);
		sql.append(") t8 on t1.pk_details_id = t8.pk_details_id ");
		sql.append("left join ( ");
		sql.append(visit);
		sql.append(") t9 on t1.pk_details_id = t9.pk_details_id ");
		sql.append("left join ( ");
		sql.append(operationRemarks);
		sql.append(") t10 on t1.pk_details_id = t10.pk_details_id ");
		sql.append("left join ( ");
		sql.append(className);
		sql.append(") t11 on t11.pk_details_id = t1.pk_details_id ");
		sql.append("left join yjwy_pub_dict t12 on t12.dict_code_ = t1.service_type ");
		sql.append("where t1.pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		sql.append(") tt ");
		sql.append("where " + dateSql);
		if (!StringUtils.isEmpty(projectSql.toString()) || !StringUtils.isEmpty(areaSql.toString())) {
			sql.append("and " + projectSql + areaSql);
		}
		sql.append(" order by tt.pk_details_id ");
		long count = workTaskStatisticsService.queryCount(sql.toString());
		if (null != page && null != rows) {
			sql.append(" limit " + (page - 1) * rows + "," + rows);
		}
		// 执行查询
		List<Map<String, Object>> models = workTaskStatisticsService.queryList(sql.toString());
		// 计算
		Object obj = null;
		Object obj1 = null;
		String str = null;
		for (Map<String, Object> map : models) {
			// 接单方式(抢单/派单)
			obj = map.get("dispatch_type");
			if ((Integer) obj == 1) {
				str = "抢单";

			} else if ((Integer) obj == 2) {
				str = "派单";
			} else {
				str = "待抢待派";
			}
			map.put("dispatch_type", str);
			// 任务状态
			obj = map.get("state");
			if (obj != null) {
				switch (Integer.parseInt(obj.toString())) {
				case 3:
					str = "已完成待回访";
					break;
				case 4:
					str = "已回访";
					break;
				}
			} else {
				obj = map.get("task_state");
				switch ((Integer) obj) {
				case 0:
					str = "未派单";
					break;
				case 1:
					str = "待接单";
					break;
				case 2:
					str = "维修中";
					break;
				case 3:
					str = "完成";
					break;
				case 4:
					str = "已拒单";
					break;
				case 5:
					str = "已取消";
					break;
				}
			}
			map.put("state", str);
			// 总定额工时(分钟)
			obj = map.get("ration_duration");
			obj1 = map.get("work_number");
			if (obj == null) {
				map.put("finished_duration", "");
			} else {
				map.put("finished_duration", Integer.parseInt(obj.toString()) * Integer.parseInt(obj1.toString()) + "");
			}

			// 维修耗时(小时)
			setBetweenTime(map, "orders_time", "finish_time", "repair_hours", "3600000");
			// 维修耗时(分钟)
			setBetweenTime(map, "orders_time", "finish_time", "repair_mins", "60000");
			// 是否24小时内完成维修单
			if (!map.get("repair_hours").equals("")) {
				if (Float.parseFloat(map.get("repair_hours").toString()) <= 24) {
					map.put("twentyfour", "是");
				} else {
					map.put("twentyfour", "否");
				}
			} else {
				map.put("twentyfour", "");
			}
			// 金额合计
			obj = map.get("artificial_cost");
			obj1 = map.get("material_cost");
			map.put("count_cost", Float.parseFloat(obj.toString()) + Float.parseFloat(obj1.toString()));
			// 抢/派单耗时(分钟)
			setBetweenTime(map, "create_time", "dispatch_time", "dispatch_mins", "60000");
			// 接单耗时(分钟)
			setBetweenTime(map, "dispatch_time", "orders_time", "order_mins", "60000");
			// 完成耗时(分钟)
			setBetweenTime(map, "create_time", "finish_time", "finish_mins", "60000");

		}

		ModelAndResult mar = new ModelAndResult();
		mar.setTotal(count);
		mar.setAttribute("rows", models);
		return mar;
	}

	/**
	 * 设置时间差
	 * 
	 * @param map
	 * @param sdateName
	 *            较小日期
	 * @param bdateName
	 *            较大日期
	 * @param key
	 *            键名
	 * @param divisor
	 *            除数
	 */
	private void setBetweenTime(Map<String, Object> map, String sdateName, String bdateName, String key, String divisor) {
		Object sdate = map.get(sdateName);
		Object bdate = map.get(bdateName);
		if (sdate != null && bdate != null) {
			Long time = DeviceUtil.millsBetween(sdate.toString(), bdate.toString(), DeviceUtil.YMDHMS);
			map.put(key, DeviceUtil.forDivision(time.toString(), divisor));
		} else {
			map.put(key, "");
		}
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
		return ImpAndExpExcel.doExpExcel(mr.get("rows"), new String[] { "datails_code", "one_class", "two_class", "three_class", "project_name", "service_name", "repair_user", "repair_details", "repair_content", "dispatch_type", "operation_remarks", "state", "ration_duration", "work_number", "finished_duration", "repair_hours", "repair_mins", "repair_user_name", "help_users", "artificial_cost", "material_cost", "count_cost", "create_time", "dispatch_time", "orders_time", "finish_time", "satisfaction", "twentyfour", "dispatch_mins", "order_mins", "finish_mins" }, "templates/templates/worktask/工单管理工单明细表.xls", 2);
	}
}
