package com.shareworx.ezfm.quality.proinspect.datastatistics.personnel.action;

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
import com.shareworx.ezfm.quality.proinspect.datastatistics.personnel.vo.PersonnelQueryVo;
import com.shareworx.ezfm.utils.ImpAndExpExcel;
import com.shareworx.ezfm.utils.UserUtil;
/**
 * 人员数据统计 
 * @author lingwei.li
 *
 */
@Controller
@RequestMapping("ezfm/quality/datastatistics/personnel")
public class PersonnelAction {

	final static Logger log = Logger.getLogger(PersonnelAction.class);
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;

	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	@Autowired
	@Qualifier(YJWYDeviceService.ID)
	private  YJWYDeviceService deviceService;

	public void setDeviceService(YJWYDeviceService deviceService) {
		this.deviceService = deviceService;
	}
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "ezfm/quality/datastatistics/personnel/personnel_list";
	public final static String PAGE_SON = "ezfm/quality/datastatistics/personnel/personnel_sonpage_list";
	
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
	 * 条件查询
	 * @param queryVo
	 * @return
	 */
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult queryTaskForContion(@RequestBody PersonnelQueryVo queryVo) {
		JdbcTemplate read = dao.getReadTemplate();
		
		// 获取 查询条件的 sql
		String sql = getTaskForContionSql(queryVo);

		// 查询条数
		String countSql = getCountSql(queryVo);
		int count = read.queryForObject(countSql, Integer.class);
		
		List<Map<String, Object>> list = getPageList(read.queryForList(sql));
		
		ModelAndResult mar = new ModelAndResult(list);
		mar.setTotal(count);
		
		return mar;
	}
	
	/**
	 * 导出excel
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "export", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult doExpDataStructureByTable(@RequestParam(value="param", required=false) String param) {
		
		JdbcTemplate read = dao.getReadTemplate();
		PersonnelQueryVo queryVo = JSONObject.parseObject(param,PersonnelQueryVo.class);
		// 查询条数
		String countSql = getCountSql(queryVo);
		int count = read.queryForObject(countSql, Integer.class);
		queryVo.setStart(0);
		queryVo.setLimit(count);
		
		// 获取 查询条件的 sql
		String sql = getTaskForContionSql(queryVo);
		
		List<Map<String, Object>> list = getPageList(read.queryForList(sql));
		
		return ImpAndExpExcel.doExpExcel(list, new String[]{"project_name","station_name",
				"fk_taskuser_name","total_task_num","check_task_num",
				"wait_task_num","finished_task_num","rec_finished_num",
				"unfinished_task_num","finished_rate"}, 
				"templates/templates/quality/品质核查人员数据统计表.xls", 2);
		
	}
	
	/**
	 * 补充  页面上缺少的 字段
	 * @param list
	 * @return
	 */
	public static List<Map<String, Object>> getPageList(List<Map<String, Object>> list) {
		
		// 设置一个默认 率
		final String default_rate = "0.00%";
		
		for (Map<String, Object> map : list) {
			
			int total_task_num = Integer.valueOf(String.valueOf(map.get("total_task_num")));
			
			int finished_num = Integer.valueOf(String.valueOf(map.get("finished_task_num")))
					+Integer.valueOf(String.valueOf(map.get("rec_finished_num")));
			
			//任务完成率（整改完成数/总任务）先判断除数是否为0
			if (total_task_num == 0 || finished_num == 0) {
				map.put("finished_rate", default_rate);
			}else{
				String finish_rate = getPercentage(finished_num, total_task_num);
				map.put("finished_rate", finish_rate);
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
	public static String getTaskForContionSql(PersonnelQueryVo queryVo) {
		YJWYUserModel user = UserUtil.getCurrentUser();
		String whrSql = "where 1=1 ";				
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
		// 岗位
		if (!StringUtils.isEmpty(queryVo.getStation_id())) {
			whrSql = whrSql + " and yqit.fk_job = '" + queryVo.getStation_id() + "' ";
		}
		// 提交时间
		if (!StringUtils.isEmpty(queryVo.getStart_time()) && !StringUtils.isEmpty(queryVo.getEnd_time())) {
			whrSql = whrSql + " and yqit.create_time >= '" + queryVo.getStart_time() + " 00:00:00' " 
						+ "and yqit.create_time <= '"+ queryVo.getEnd_time() + " 23:59:59' ";
		}
		// 任务开始时间  和  任务结束时间
		if (!StringUtils.isEmpty(queryVo.getTask_start_time()) && !StringUtils.isEmpty(queryVo.getTask_end_time())) {
			whrSql =whrSql + " and yqit.task_start_time >= '" + queryVo.getTask_start_time() + "' " 
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
				//+ "yqit.fk_taskuser, "
				+ "ypu.user_name_ as fk_taskuser_name, "
				//+ "yqit.fk_project as project_id, "
				+ "ypp.project_name_ as project_name, "
				//+ "yqit.fk_area as area_id, "
				//+ "yqit.fk_job as station_id, "
				+ "yps.station_name_ as station_name,"
				//+ "yqit.create_time, "
				+ "IFNULL(a.total_task_num, 0) as total_task_num, "
				+ "IFNULL(b.check_task_num, 0) as check_task_num, "
				+ "IFNULL(c.wait_task_num, 0) as wait_task_num, "
				+ "IFNULL(d.finished_task_num, 0) as finished_task_num, "
				+ "IFNULL(e.rec_finished_num, 0) as rec_finished_num, "
				+ "IFNULL(f.unfinished_task_num, 0) as unfinished_task_num "
				+ "from "
				+ "yjwy_quality_inspecttask yqit "
				+ "left join yjwy_pub_user ypu on yqit.fk_taskuser = ypu.pk_user_ "
				+ "left join yjwy_pub_project ypp on yqit.fk_project = ypp.pk_project_ "
				+ "left join yjwy_pub_station yps on yqit.fk_job = yps.pk_station_ "
				//总任务数
				+ "left join "
					+ "(select yqit.fk_taskuser, yqit.fk_project, "
					+ "IFNULL(COUNT(yqit.pk_task), 0) as total_task_num "
					+ "from "
					+ "yjwy_quality_inspecttask yqit "
					+ whrSql
					+ "group by yqit.fk_project, yqit.fk_taskuser) a "
					+ "on a.fk_project = yqit.fk_project "
					+ "and a.fk_taskuser = yqit.fk_taskuser "
				//考核总任务数
				+ "left join "
					+ "(select yqit.fk_taskuser, yqit.fk_project, "
					+ "IFNULL(COUNT(yqit.pk_task), 0) as check_task_num "
					+ "from "
					+ "yjwy_quality_inspecttask yqit "
					+ whrSql
					+ "and yqit.task_generate_mode = '0' "
					+ "group by yqit.fk_project, yqit.fk_taskuser) b "
					+ "on b.fk_project = yqit.fk_project "
					+ "and b.fk_taskuser = yqit.fk_taskuser "
				//代办任务
				+ "left join "
					+ "(select yqit.fk_taskuser, yqit.fk_project, "
					+ "IFNULL(COUNT(yqit.pk_task), 0) as wait_task_num "
					+ "from "
					+ "yjwy_quality_inspecttask yqit "
					+ whrSql
					+ "and yqit.task_state = '10' "
					+ "group by yqit.fk_project, yqit.fk_taskuser) c "
					+ "on c.fk_project = yqit.fk_project "
					+ "and c.fk_taskuser = yqit.fk_taskuser "	
				//完成完成任务
				+ "left join "
					+ "(select yqit.fk_taskuser, yqit.fk_project, "
					+ "IFNULL(COUNT(yqit.pk_task), 0) as finished_task_num "
					+ "from "
					+ "yjwy_quality_inspecttask yqit "
					+ whrSql
					+ "and yqit.task_state = '30' "
							+ "and yqit.is_rectify = '0' "
					+ "group by yqit.fk_project, yqit.fk_taskuser) d "
					+ "on d.fk_project = yqit.fk_project "
					+ "and d.fk_taskuser = yqit.fk_taskuser "
				//整改完成任务
				+ "left join "
					+ "(select yqit.fk_taskuser, yqit.fk_project, "
					+ "IFNULL(COUNT(yqit.pk_task), 0) as rec_finished_num "
					+ "from "
					+ "yjwy_quality_inspecttask yqit "
					+ whrSql
					+ "and yqit.task_state = '30' "
							+ "and yqit.is_rectify = '1' "
					+ "group by yqit.fk_project, yqit.fk_taskuser) e "
					+ "on e.fk_project = yqit.fk_project "
					+ "and e.fk_taskuser = yqit.fk_taskuser "
				//TODO 需求不确定待确认 。。。未完成任务
				+ "left join "
					+ "(select yqit.fk_taskuser, yqit.fk_project, "
					+ "IFNULL(COUNT(yqit.pk_task), 0) as unfinished_task_num "
					+ "from "
					+ "yjwy_quality_inspecttask yqit "
					+ whrSql
					+ "and yqit.task_state = '10' "
					+ "group by yqit.fk_project, yqit.fk_taskuser) f "
					+ "on f.fk_project = yqit.fk_project "
					+ "and f.fk_taskuser = yqit.fk_taskuser "
					+ whrSql 
				+ "group by yqit.fk_project, yqit.fk_taskuser "
				+ "limit " + queryVo.getStart() + "," + queryVo.getLimit();
		return sql;
	}
	
	/**
	 * 获取总条数的 sql
	 * 
	 * @return
	 */
	public static String getCountSql(PersonnelQueryVo queryVo) {
		String whrSql = "where 1=1 ";
		// 区域
		if (!StringUtils.isEmpty(queryVo.getArea_id())) {
			whrSql = whrSql + " and yqit.fk_area = '" + queryVo.getArea_id() + "' ";
		}
		// 项目id
		if (!StringUtils.isEmpty(queryVo.getProject_id())) {
			whrSql = whrSql + " and yqit.fk_project = '" + queryVo.getProject_id() + "' ";
		}
		// 岗位
		if (!StringUtils.isEmpty(queryVo.getStation_id())) {
			whrSql = whrSql + " and yqit.fk_job = '" + queryVo.getStation_id() + "' ";
		}
		// 提交时间
		if (!StringUtils.isEmpty(queryVo.getStart_time()) && !StringUtils.isEmpty(queryVo.getEnd_time())) {
			whrSql = whrSql + " and yqit.create_time >= '" + queryVo.getStart_time() + " 00:00:00' " 
					+ "and yqit.create_time <= '"+ queryVo.getEnd_time() + " 23:59:59' ";
		}
		
		whrSql = whrSql + " and yqit.pk_crop='"+UserUtil.getCurrentUser().getPk_crop()+"'";
		
		String sql = "select count(*) "
				+ "from "
				+ "(select "
				+ "yqit.fk_area as area_id, "
				+ "yqit.fk_project as project_id, "
				+ "yqit.fk_job as station_id, "
				+ "yqit.create_time "
				+ "from "
				+ "yjwy_quality_inspecttask yqit "
				+ whrSql 
				+ "group by yqit.fk_project,yqit.fk_taskuser) yqit ";
				
		return sql;

	}
}
