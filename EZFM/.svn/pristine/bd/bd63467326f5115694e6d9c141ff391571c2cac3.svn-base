package com.shareworx.ezfm.quality.proinspect.datastatistics.rectification.action;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.device.util.service.YJWYDeviceService;
import com.shareworx.ezfm.quality.proinspect.datastatistics.rectification.vo.RectificationRateQueryVo;
import com.shareworx.ezfm.utils.ImpAndExpExcel;
import com.shareworx.ezfm.utils.UserUtil;

/**
 * 品质管理 数据统计--整改率 action
 * 
 * @author lingwei.li
 *
 */
@Controller
@RequestMapping("ezfm/quality/datastatistics/rectification")
public class RectificationRateAction {

	final static Logger log = Logger.getLogger(RectificationRateAction.class);

	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;

	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	
	
	@Autowired
	@Qualifier(YJWYDeviceService.ID)
	private YJWYDeviceService deviceService;

	public void setDeviceService(YJWYDeviceService deviceService) {
		this.deviceService = deviceService;
	}
	

	/** 跳转页面 */
	public final static String PAGE_FORWARD = "ezfm/quality/datastatistics/rectification/rectification_rate_list";

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
	 * 查询操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult queryTaskForContion(@RequestBody RectificationRateQueryVo queryVo) {
		JdbcTemplate read = dao.getReadTemplate();
		System.out.println(queryVo.toString());
		// 获取 查询条件的 sql
		String sql = getTaskForContionSql(queryVo);

		// 查询条数
		String countSql = getCountSql(queryVo);
		int count = read.queryForObject(countSql, Integer.class);

		// 设置一个默认 率
		List<Map<String, Object>> list = getPageList(read.queryForList(sql));
		
		ModelAndResult mar = new ModelAndResult(list);
		mar.setTotal(count);
		return mar;
	}

	/**
	 * 导出excel
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "export", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult doExpDataStructureByTable(@RequestParam(value="param", required=false) String param) {
		JdbcTemplate read = dao.getReadTemplate();
		RectificationRateQueryVo queryVo = JSONObject.parseObject(param,RectificationRateQueryVo.class);
		// 查询条数
		String countSql = getCountSql(queryVo);
		int count = read.queryForObject(countSql, Integer.class);
		queryVo.setStart(0);
		queryVo.setLimit(count);
		
		// 获取 查询条件的 sql
		String sql = getTaskForContionSql(queryVo);
		
		List<Map<String, Object>> list = getPageList(read.queryForList(sql));
		
		return ImpAndExpExcel.doExpExcel(list, new String[]{"project_name","taskuser_name",
				"check_task_num","active_task_num","finish_rate","timely_rate","total_rate"}, 
				"templates/templates/quality/品质核查整改率统计.xls", 2);

	}

	/**
	 * 补充页面上 缺少的 值
	 * @param list
	 * @return
	 */
	public static List<Map<String, Object>> getPageList(List<Map<String, Object>> list) {
		
		// 设置一个默认 率
		final String default_rate = "0.00%";
		
		for (Map<String, Object> map : list) {
			
			int rec_task_num = Integer.valueOf(String.valueOf(map.get("check_task_num"))) 
					+ Integer.valueOf(String.valueOf(map.get("active_task_num")));
			
			int finished_task_num = Integer.valueOf(String.valueOf(map.get("finished_task_num")));
			
			int timely_num = finished_task_num - Integer.valueOf(String.valueOf(map.get("overdue_task_num")));
			
			int totalNum = Integer.valueOf(String.valueOf(map.get("total_task_num")));
			
			// 计算整改完成率（整改完成数/整改任务总数）
			if (rec_task_num == 0 || finished_task_num == 0) {
				map.put("finish_rate", default_rate);
			} else {
				String finish_rate = getPercentage(finished_task_num, rec_task_num);
				map.put("finish_rate", finish_rate);
			}
			
			// 计算整改及时率（及时整改数（整改完成数-超时整改数）/整改完成总数）
			if (finished_task_num == 0 || timely_num == 0) {
				map.put("timely_rate", default_rate);
			} else {
				String timelyRate = getPercentage(timely_num,finished_task_num);
				map.put("timely_rate", timelyRate);
			}
			
			// 整改任务占比 （整改任务总数/总任务数）
			if (totalNum == 0 || rec_task_num == 0) {
				map.put("total_rate", default_rate);
			} else {
				String totalRate = getPercentage(rec_task_num, totalNum);
				map.put("total_rate", totalRate);
			}
		}
		
		return list;
	}
	
	
	/**
	 * 计算百分比
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static String getPercentage(int v1, int v2) {
		double val_1 = (float) v1 / v2 * 100;
		java.math.BigDecimal b1 = new java.math.BigDecimal(val_1);
		double val_2 = b1.setScale(2, java.math.BigDecimal.ROUND_HALF_UP).doubleValue();
		String result = String.valueOf(new DecimalFormat("######0.00").format(val_2))+"%";
		return result;

	}

	/**
	 * 获取根据 queryVo 获取需要执行的 sql语句
	 * 
	 * @param queryVo
	 * @return
	 */
	public static String getTaskForContionSql(RectificationRateQueryVo queryVo) {
		YJWYUserModel user = UserUtil.getCurrentUser();
		String whrAllSql = " ";
		String whrSql = "where 1 = 1 ";
		// 区域
		if (!StringUtils.isEmpty(queryVo.getArea_id())) {
			whrSql = whrSql + " and yqit.fk_area = '" + queryVo.getArea_id() + "' ";
		}
		// 项目id
		if (!StringUtils.isEmpty(queryVo.getProject_id())) {
			whrSql = whrSql + " and yqit.fk_project = '" + queryVo.getProject_id() + "' ";
		}else{
			YJWYDeviceService	deviceService = SpringUtils.getBean(YJWYDeviceService.ID);
			Set<String> projectIds = deviceService.queryProjectIdsByPkUser(user.getPk_user());
			String ids = "";
			for(String id : projectIds){
				ids = ids+",'"+id+"'";
			}
			//ids非空再substring(1)，不然报空指针
			if(!StringUtils.isEmpty(ids)){
				whrSql = whrSql+" and yqit.fk_project in ("+ids.substring(1)+") ";
			}else{
				whrSql = whrSql+" and yqit.fk_project in ('') ";
			}
		}
		// 部门
		if (!StringUtils.isEmpty(queryVo.getDept_id())) {
			whrSql = whrSql + " and yqit.fk_dept = '" + queryVo.getDept_id() + "' ";
		}
		// 岗位
		if (!StringUtils.isEmpty(queryVo.getStation_id())) {
			whrSql = whrSql + " and yqit.fk_job = '" + queryVo.getStation_id() + "' ";
		}
		// 项目分类（项目属性）
		if (!StringUtils.isEmpty(queryVo.getProject_attribute())) {
			whrAllSql = whrAllSql + " and yqis.project_category = '" + queryVo.getProject_attribute() + "' ";
		}
		// 人员
		if (!StringUtils.isEmpty(queryVo.getUser_id())) {
			whrSql = whrSql + " and yqit.fk_taskuser = '" + queryVo.getUser_id() + "' ";
		}
		// 提交时间
		if (!StringUtils.isEmpty(queryVo.getStart_time()) && !StringUtils.isEmpty(queryVo.getEnd_time())) {
			whrSql = whrSql + " and yqit.create_time >= '" + queryVo.getStart_time() + " 00:00:00' " 
					+ "and yqit.create_time <= '"+ queryVo.getEnd_time() + " 23:59:59' ";
		}
		// 任务开始时间  和  任务结束时间
		if (!StringUtils.isEmpty(queryVo.getTask_start_time()) && !StringUtils.isEmpty(queryVo.getTask_end_time())) {
			whrSql = whrSql + " and yqit.task_start_time >= '" + queryVo.getTask_start_time() + "' " 
					+ "and yqit.task_deadline_date <= '"+ queryVo.getTask_end_time() + "' ";
		}else{
			if (!StringUtils.isEmpty(queryVo.getTask_start_time())) {
				whrSql = whrSql + " and yqit.task_start_time = '" + queryVo.getTask_start_time() + "' ";
			}else if (!StringUtils.isEmpty(queryVo.getTask_end_time())) {
				whrSql = whrSql + " and yqit.task_deadline_date = '" + queryVo.getTask_end_time() + "' ";
			}
		}

		whrSql = whrSql + " and yqit.pk_crop='"+UserUtil.getCurrentUser().getPk_crop()+"' ";
		
		String sql = "select " 
				+ "ypp.project_name_ as project_name, " 
				+ "ypu.user_name_ as taskuser_name, " 
				+ "IFNULL(a.check_task_num, 0) as check_task_num,"
				+ "IFNULL(b.active_task_num, 0) as active_task_num," 
				+ "IFNULL(c.finished_task_num, 0) as finished_task_num,"
				+ "IFNULL(d.overdue_task_num, 0) as overdue_task_num, "
				+ "IFNULL(e.total_task_num, 0) as total_task_num "
				+ "from "
					+ "yjwy_quality_inspecttask yqit "
					+ "LEFT JOIN yjwy_pub_project ypp "
						+ "ON yqit.fk_project = ypp.pk_project_ "
					+ "LEFT JOIN yjwy_pub_user ypu "
						+ "ON yqit.fk_taskuser = ypu.pk_user_ "
					+ "LEFT JOIN yjwy_quality_inspectstandard yqis "
						+ "ON yqit.fk_standard = yqis.pk_inspstan "
					//考核内整改项
					+ "LEFT JOIN "
						+ "(select yqit.fk_project, yqit.fk_taskuser, "
						+ "COUNT(yqit.pk_task) AS check_task_num "
						+ "FROM "
							+ "yjwy_quality_inspecttask yqit "
							+ whrSql
							+ "AND yqit.is_rectify = '1' "
							+ "AND yqit.task_generate_mode = '0' "
							+ "GROUP BY yqit.fk_project, yqit.fk_taskuser) a "
						+ "ON a.fk_project = yqit.fk_project "
						+ "AND a.fk_taskuser = yqit.fk_taskuser " 
					//主动发起整改项
					+ "LEFT JOIN "
						+ "(select yqit.fk_project, yqit.fk_taskuser, "
						+ "COUNT(yqit.pk_task) AS active_task_num "
						+ "FROM "
							+ "yjwy_quality_inspecttask yqit "
							+ whrSql
							+ "AND yqit.is_rectify = '1' "
							+ "AND yqit.task_generate_mode = '1' "
							+ "GROUP BY yqit.fk_project, yqit.fk_taskuser) b "
						+ "ON b.fk_project = yqit.fk_project "
						+ "AND b.fk_taskuser = yqit.fk_taskuser " 
					//整改完成数
					+ "LEFT JOIN "
						+ "(select yqit.fk_project, yqit.fk_taskuser, "
						+ "COUNT(yqit.pk_task) AS finished_task_num "
						+ "FROM "
							+ "yjwy_quality_inspecttask yqit "
							+ whrSql
							+ "AND yqit.is_rectify = '1' "
							+ "AND yqit.task_state = '30' "
							+ "GROUP BY yqit.fk_project, yqit.fk_taskuser) c "
						+ "ON c.fk_project = yqit.fk_project "
						+ "AND c.fk_taskuser = yqit.fk_taskuser " 
					//整改超期完成数	
					+ "LEFT JOIN "
						+ "(select yqit.fk_project, yqit.fk_taskuser, "
						+ "COUNT(yqit.pk_task) AS overdue_task_num "
						+ "FROM "
							+ "yjwy_quality_inspecttask yqit "
							+ whrSql
							+ "AND yqit.is_rectify = '1' "
							+ "AND yqit.task_state = '30' "
							+ "AND yqit.task_iscq = '1' "
							+ "GROUP BY yqit.fk_project, yqit.fk_taskuser) d "
						+ "ON d.fk_project = yqit.fk_project "
						+ "AND d.fk_taskuser = yqit.fk_taskuser "
					
					//任务总数	
					+ "LEFT JOIN "
						+ "(select yqit.fk_project, yqit.fk_taskuser, "
						+ "COUNT(yqit.pk_task) AS total_task_num "
						+ "FROM "
							+ "yjwy_quality_inspecttask yqit "
							+ whrSql
							+ "GROUP BY yqit.fk_project, yqit.fk_taskuser) e "
						+ "ON e.fk_project = yqit.fk_project "
						+ "AND e.fk_taskuser = yqit.fk_taskuser "
				+ whrSql +" AND yqit.is_rectify = '1' "+ whrAllSql
				+ "GROUP BY yqit.fk_project, yqit.fk_taskuser "
				+ "limit " + queryVo.getStart() + "," + queryVo.getLimit();
		return sql;
	}

	/**
	 * 获取总条数的 sql
	 * 
	 * @return
	 */
	public static String getCountSql(RectificationRateQueryVo queryVo) {
		String whrSql = "where yqit.is_rectify = '1' ";
		// 区域
		if (!StringUtils.isEmpty(queryVo.getArea_id())) {
			whrSql = whrSql + " and yqit.fk_area = '" + queryVo.getArea_id() + "' ";
		}
		// 项目id
		if (!StringUtils.isEmpty(queryVo.getProject_id())) {
			whrSql = whrSql + " and yqit.fk_project = '" + queryVo.getProject_id() + "' ";
		}
		// 部门
		if (!StringUtils.isEmpty(queryVo.getDept_id())) {
			whrSql = whrSql + " and yqit.fk_dept = '" + queryVo.getDept_id() + "' ";
		}
		// 岗位
		if (!StringUtils.isEmpty(queryVo.getStation_id())) {
			whrSql = whrSql + " and yqit.fk_job = '" + queryVo.getStation_id() + "' ";
		}
		// 项目分类（项目属性）
		if (!StringUtils.isEmpty(queryVo.getProject_attribute())) {
			whrSql = whrSql + " and yqis.project_category = '" + queryVo.getProject_attribute() + "' ";
		}
		// 人员
		if (!StringUtils.isEmpty(queryVo.getUser_id())) {
			whrSql = whrSql + " and yqit.fk_taskuser = '" + queryVo.getUser_id() + "' ";
		}
		// 提交时间
		if (!StringUtils.isEmpty(queryVo.getStart_time()) && !StringUtils.isEmpty(queryVo.getEnd_time())) {
			whrSql = whrSql + " and yqit.create_time >= '" + queryVo.getStart_time() + " 00:00:00' " 
					+ "and yqit.create_time <= '"+ queryVo.getEnd_time() + " 23:59:59' ";
		}
		
		// 任务开始时间  和  任务结束时间
		if (!StringUtils.isEmpty(queryVo.getTask_start_time()) && !StringUtils.isEmpty(queryVo.getTask_end_time())) {
			whrSql = whrSql + " and yqit.task_start_time >= '" + queryVo.getTask_start_time() + "' " 
					+ "and yqit.task_deadline_date <= '"+ queryVo.getTask_end_time() + "' ";
		}else{
			if (!StringUtils.isEmpty(queryVo.getTask_start_time())) {
				whrSql = whrSql + " and yqit.task_start_time = '" + queryVo.getTask_start_time() + "' ";
			}else if (!StringUtils.isEmpty(queryVo.getTask_end_time())) {
				whrSql = whrSql + " and yqit.task_deadline_date = '" + queryVo.getTask_end_time() + "' ";
			}
		}
		
		whrSql = whrSql + " and yqit.pk_crop='"+UserUtil.getCurrentUser().getPk_crop()+"' ";
		
		String countSql = "select count(*) " 
				+ "from " 
					+ "("
					+ "select " 
						+ "yqit.fk_project as project_id, "
						+ "yqit.fk_taskuser as taskuser_id, " 
						+ "yqit.fk_job as job, " 
						+ "yqit.fk_dept as dept, " 
						+ "yqit.fk_area as area, "
						+ "yqis.project_category as project_category, " 
						+ "yqit.create_time " 
						+ "from " 
							+ "yjwy_quality_inspecttask yqit " // 任务表
							+ "left join yjwy_quality_inspectstandard yqis " // 标准表
								+ "on yqit.fk_standard = yqis.pk_inspstan " 
						+ whrSql
						+ "group by yqit.fk_project, yqit.fk_taskuser) m "; 
		
		return countSql;

	}

}
