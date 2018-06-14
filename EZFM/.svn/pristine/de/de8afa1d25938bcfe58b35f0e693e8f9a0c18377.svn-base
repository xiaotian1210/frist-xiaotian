package com.shareworx.ezfm.problem.statistics.action;

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
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.device.util.service.YJWYDeviceService;
import com.shareworx.ezfm.device.util.vo.ParamEntity;
import com.shareworx.ezfm.problem.statistics.service.YJWYProblemStatisticsService;
import com.shareworx.ezfm.utils.ImpAndExpExcel;
import com.shareworx.ezfm.utils.UserUtil;

/**
 * 报事类型数据及时率统计表控制器
 * 
 * @author jin.li
 *
 */
@Controller
@RequestMapping("ezfm/problem/regularly")
public class YJWYProblemRegularlyAction {

	final static Logger log = Logger.getLogger(YJWYProblemRegularlyAction.class);

	/** 跳转页面 */
	public final static String PAGE_FORWARD = "ezfm/problem/statistics/problem_regularly_list";

	@Autowired
	@Qualifier(YJWYProblemStatisticsService.ID)
	private YJWYProblemStatisticsService problemStatisticsService;

	public void setProblemStatisticsService(YJWYProblemStatisticsService problemStatisticsService) {
		this.problemStatisticsService = problemStatisticsService;
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
			projectSql.append(" where t4.pk_project = '" + pk_project + "' ");
		} else if (!DeviceUtil.stringIsEmpty(pk_area) && !"default".equals(pk_area)) {
			areaSql.append(" where t5.pk_area_ = '" + pk_area + "' ");
		} else {
			Set<String> set = deviceService.queryProjectIdsByPkUser(UserUtil.getCurrentPk());
			if (!ArrayUtils.isEmpty(set)) {
				projectSql.append(" where " + DeviceUtil.getInNotInSql("t4.pk_project", QueryContents.TYPE_IN, set.toArray(new String[] {})) + " ");
			}else {
				projectSql.append(" where 1=2 ");
			}
		}
		if (DeviceUtil.stringIsEmpty(start_time) && DeviceUtil.stringIsEmpty(end_time)) {
			start_time = DeviceUtil.CurrentMonthFirstDay(new Date(), DeviceUtil.YMDHMS);
			end_time = DeviceUtil.date2String(new Date(), DeviceUtil.YMDHMS);
		}
		dateSql.append(" and create_time >= '" + start_time + "' and create_time <= '" + end_time + "' ");

		// 查询报事任务总数
		StringBuilder all = new StringBuilder();
		all.append("select fk_project_id,count(pk_details_id) all_total from yjwy_problem_details ");
		all.append("where fk_details_id is null ");
		all.append(dateSql);
		all.append(" and pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		all.append(" group by fk_project_id ");

		// 查询报事任务完成总数
		StringBuilder completion = new StringBuilder();
		completion.append("select d1.fk_project_id,(case when d2.completion_total is null then 0 else d2.completion_total end ) as completion_total from ");
		completion.append("(select distinct fk_project_id from yjwy_problem_details where fk_details_id is null " + " and pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		completion.append(dateSql);
		completion.append(" ) d1 ");
		completion.append("left join (select fk_project_id,count(pk_details_id) completion_total ");
		completion.append("from yjwy_problem_details where state in (3,4) and fk_details_id is null " + " and pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		completion.append(dateSql);
		completion.append("group by fk_project_id ) d2 on d1.fk_project_id = d2.fk_project_id ");

		// 查询报事任务及时完成总数
		StringBuilder timely = new StringBuilder();
		timely.append("select d1.fk_project_id,(case when t2.timely_total is null then 0 else t2.timely_total end ) as timely_total from ");
		timely.append("(select distinct fk_project_id from yjwy_problem_details where fk_details_id is null " + " and pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		timely.append(dateSql);
		timely.append(" ) d1 ");
		timely.append("left join (select t1.fk_project_id fk_project_id,count(t1.pk_details_id) timely_total ");
		timely.append("from ( select d.*,(case when c.time_limit=1 then date_add(d.create_time,interval 24 hour) ");
		timely.append("when c.time_limit=2 then date_add(d.create_time,interval 40 minute) ");
		timely.append("when c.time_limit=3 then date_add(d.create_time,interval 30 minute) end ) as temp_time ");
		timely.append("from (select * from yjwy_problem_details where state in (3,4) and fk_details_id is null " + " and pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		timely.append(dateSql);
		timely.append(") d left join yjwy_problem_class c on d.fk_class_id = c.pk_class_id ");
		timely.append(") t1 where t1.complete_time <= t1.temp_time ");
		timely.append("group by t1.fk_project_id ) t2 on d1.fk_project_id = t2.fk_project_id ");

		// 查询项目和区域信息
		StringBuilder proarea = new StringBuilder();
		proarea.append("select t1.pk_project_,t1.project_name_,t1.pk_area_,t2.area_name_ ");
		proarea.append("from yjwy_pub_project t1 left join yjwy_pub_area t2 on t1.pk_area_ = t2.pk_area_ ");

		// 查询语句
		StringBuilder sql = new StringBuilder();
		sql.append("select t4.pk_project,t4.all_total,t4.completion_total,t4.timely_total,");
		sql.append("t5.pk_area_ pk_area,t5.project_name_ project_name,t5.area_name_ area_name from ");
		sql.append("(select t1.fk_project_id pk_project,t1.all_total,t2.completion_total,t3.timely_total ");
		// 拼接总数sql
		sql.append("from (" + all + ") t1 ");
		// 拼接完成总数sql
		sql.append("left join (" + completion + ") t2 on t1.fk_project_id = t2.fk_project_id ");
		// 拼接及时完成总数sql
		sql.append("left join (" + timely + ") t3 on t1.fk_project_id = t3.fk_project_id ) t4 ");
		sql.append("left join (" + proarea + ") t5 on t4.pk_project = t5.pk_project_ ");
		sql.append(projectSql);
		sql.append(areaSql);
		// 排序
		sql.append(" order by t4.pk_project ");
		long count = problemStatisticsService.queryCount(sql.toString());
		// 分页
		if (null != page && null != rows) {
			sql.append(" limit " + (page - 1) * rows + "," + rows);
		}
		List<Map<String, Object>> models = problemStatisticsService.queryList(sql.toString());
		for (Map<String, Object> map : models) {
			// 完成率、及时完成率
			map.put("completion_rate", DeviceUtil.getPercent(map.get("completion_total").toString(), map.get("all_total").toString()));
			map.put("timely_rate", DeviceUtil.getPercent(map.get("timely_total").toString(), map.get("completion_total").toString()));
		}
		ModelAndResult mar = new ModelAndResult();
		mar.setTotal(count);
		mar.setAttribute("rows", models);
		return mar;
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
		return ImpAndExpExcel.doExpExcel(mr.get("rows"), new String[] { "area_name", "project_name", "all_total", "completion_total", "completion_rate", "timely_total", "timely_rate" }, "templates/templates/problem/报事管理报事类型数据及时率统计表.xls", 2);
	}

}
