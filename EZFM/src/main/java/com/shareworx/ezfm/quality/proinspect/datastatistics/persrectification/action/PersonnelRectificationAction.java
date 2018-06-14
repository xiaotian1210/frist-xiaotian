package com.shareworx.ezfm.quality.proinspect.datastatistics.persrectification.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
import com.shareworx.ezfm.quality.proinspect.datastatistics.persrectification.vo.InspectTaskModelVo;
import com.shareworx.ezfm.quality.proinspect.datastatistics.persrectification.vo.PersonnelRectificationQueryVo;
import com.shareworx.ezfm.utils.ImpAndExpExcel;
import com.shareworx.ezfm.utils.UserUtil;
/**
 * 人员整改数据表
 * @author lingwei.li
 *
 */
@Controller
@RequestMapping("ezfm/quality/datastatistics/persrectification")
public class PersonnelRectificationAction {
	
	final static Logger log = Logger.getLogger(PersonnelRectificationAction.class);

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
	public final static String PAGE_FORWARD = "ezfm/quality/datastatistics/persrectification/persrectification_list";
	public final static String PAGE_UNFINISHED = "ezfm/quality/datastatistics/persrectification/persrectification_sonpage_list";
	
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
	public @ResponseBody ModelAndResult queryTaskForContion(@RequestBody PersonnelRectificationQueryVo queryVo) {
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
		PersonnelRectificationQueryVo queryVo = JSONObject.parseObject(param,PersonnelRectificationQueryVo.class);
		// 查询条数
		String countSql = getCountSql(queryVo);
		int count = read.queryForObject(countSql, Integer.class);
		queryVo.setStart(0);
		queryVo.setLimit(count);
		
		// 获取 查询条件的 sql
		String sql = getTaskForContionSql(queryVo);
		
		List<Map<String, Object>> list = getPageList(read.queryForList(sql));
		
		return ImpAndExpExcel.doExpExcel(list, new String[]{"project_name","rectify_user_name",
				"station_name","unfinished_num","finished_num","expire_finished_num",
				"total_task_num","check_task_num","finish_rate","timely_rate","should_rec_num"}, 
				"templates/templates/quality/品质核查人员整改数据表.xls", 2);
		
	}
	
	/**
	 * 子页面（未完成整改、已完成整改、过期完成整改、总任务、考核总任务）
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "sonpage", method = RequestMethod.GET)
	public @ResponseBody ModelAndView toUnfinishedListPage() {
		return new ModelAndView(PAGE_UNFINISHED);
	}
	
	/**
	 * 条件查询
	 * @param queryVo
	 * @return
	 */
	@RequestMapping(value = "sonpageQuery", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult sonpageQueryTaskForContion(@RequestBody InspectTaskModelVo queryVo) {
		
		Map<String, Object> sqlMap = getSonPageSql(queryVo);
		JdbcTemplate read = dao.getReadTemplate();
		
		// 获取 查询条件的 sql
		String sql = String.valueOf(sqlMap.get("sql"));

		// 查询条数
		String countSql = String.valueOf(sqlMap.get("countSql"));
		int count = read.queryForObject(countSql, Integer.class);
				
		List<InspectTaskModelVo> list = read.query(sql, new RowMapper<InspectTaskModelVo>() {

			@Override
			public InspectTaskModelVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				//TODO 
				InspectTaskModelVo taskModelVo = new InspectTaskModelVo();
				
				taskModelVo.setPk_task(rs.getString("pk_task"));
				taskModelVo.setFk_project(rs.getString("fk_project"));
				taskModelVo.setProject_name(rs.getString("project_name"));
				taskModelVo.setTask_generate_mode(rs.getString("task_generate_mode")); //生成方式
				taskModelVo.setTask_state(rs.getString("task_state")); //任务状态
				taskModelVo.setDept_name("--"); //目前先设空   后期问清楚了 做  ------核查人单位（部门）
				taskModelVo.setFk_taskuser(rs.getString("fk_taskuser")); //核查人id（所属人）
				taskModelVo.setCheck_user_name(rs.getString("check_user_name")); //核查人姓名（所属人）
				taskModelVo.setTask_inspectresult(rs.getString("task_inspectresult")); //核查结果
				taskModelVo.setTask_qualified_time(rs.getString("task_qualified_time")); //整改确认时间（也是核查提交时间，）
				taskModelVo.setTask_rectifyuser_pk(rs.getString("task_rectifyuser_pk")); //整改人id
				taskModelVo.setTask_rectifyuser_name(rs.getString("task_rectifyuser_name"));//整改人姓名
				taskModelVo.setTask_rectify_starttime(rs.getString("task_rectify_starttime")); //整改开始时间
				taskModelVo.setTask_rectify_finishtime(rs.getString("task_rectify_finishtime"));//整改提交时间（整改完成时间）
				taskModelVo.setCheck_state(rs.getString("check_state"));//审阅状态
				taskModelVo.setCheck_time(rs.getString("check_time"));//审阅时间
				
				
				return taskModelVo;
			}
			
		});
		
		InspectTaskModelVo[] taskModel = list.toArray(new InspectTaskModelVo[] {});
		ModelAndResult mar = new ModelAndResult(taskModel);
		mar.setTotal(count);
		
		return mar;
		
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
			
			int total_task_num = Integer.valueOf(String.valueOf(map.get("total_task_num")));
			
			int finished_num = Integer.valueOf(String.valueOf(map.get("finished_num")));
			
			int timely_num = finished_num - Integer.valueOf(String.valueOf(map.get("expire_finished_num")));
			
			int check_task_num = Integer.valueOf(String.valueOf(map.get("check_task_num")));
			
			//整改完成率（整改完成数/总任务）先判断除数是否为0
			if (total_task_num == 0 || finished_num == 0) {
				map.put("finish_rate", default_rate);
			}else{
				String finish_rate = getPercentage(finished_num, total_task_num);
				map.put("finish_rate", finish_rate);
			}
			
			//整改完成及时率 （整改及时数（整改完成数-过期完成整改数）/整改完成数）
			if (finished_num == 0 || timely_num == 0) {
				map.put("timely_rate", default_rate);
			}else{
				String timely_rate = getPercentage(timely_num, finished_num);
				map.put("timely_rate", timely_rate);
			}
			//应整改量 （考核任务*10%）
			if (check_task_num == 0) {
				map.put("should_rec_num", new DecimalFormat("0.00").format(0));
			}else{
				map.put("should_rec_num", new DecimalFormat("######0.00").format(check_task_num*0.1));
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
	public static String getTaskForContionSql(PersonnelRectificationQueryVo queryVo) {
		YJWYUserModel user = UserUtil.getCurrentUser();
		String whrSql = "";
		// 区域
		if (!StringUtils.isEmpty(queryVo.getArea_id())) {
			whrSql = whrSql + " and yqit.fk_area = '" + queryVo.getArea_id() + "' ";
		}
		// 项目id
		if (!StringUtils.isEmpty(queryVo.getProject_id())) {
			whrSql = whrSql + " and yqit.fk_project = '" + queryVo.getProject_id() + "' ";
		}else{
			YJWYDeviceService	deviceService = SpringUtils.getBean(YJWYDeviceService.ID);
			Set<String> projectIdsSet = deviceService.queryProjectIdsByPkUser(user.getPk_user());
			if (projectIdsSet.size() > 0 ) {
				
				String projectIdsStr = "";
				Iterator<String> it = projectIdsSet.iterator();
				while(it.hasNext()){
					projectIdsStr = projectIdsStr + "'"+it.next()+"',";
				}
				projectIdsStr = projectIdsStr.substring(0, projectIdsStr.length()-1);
				
				whrSql = whrSql + " and yqit.fk_project in ("+projectIdsStr+") ";
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
				+ "yqit.fk_area as area_id, "
				+ "yqit.task_rectifyuser_pk as rectify_user_id, "
				+ "ypu.user_name_ as rectify_user_name, "
				+ "yqit.fk_project as project_id, "
				+ "ypp.project_name_ as project_name, "
				+ "yqit.fk_job as station_id, "
				+ "yps.station_name_ as station_name, "
				+ "yqit.create_time, "
				+ "IFNULL(a.unfinished_task_num, 0) as unfinished_num, "
				+ "IFNULL(b.finished_task_num, 0) as finished_num, "
				+ "IFNULL(c.expire_finished_num, 0) as expire_finished_num, "
				+ "IFNULL(d.total_task_num, 0) as total_task_num, "
				+ "IFNULL(e.check_task_num, 0) as check_task_num "
				+ "from "
				+ "yjwy_quality_inspecttask yqit "
				+ "left join yjwy_pub_user ypu on yqit.task_rectifyuser_pk = ypu.pk_user_ "
				+ "left join yjwy_pub_project ypp on yqit.fk_project = ypp.pk_project_ "
				+ "left join yjwy_pub_station yps on yqit.fk_job = yps.pk_station_ "
				//未完成数
				+ "left join "
					+ "(select yqit.task_rectifyuser_pk, yqit.fk_project,"
					+ "IFNULL(COUNT(yqit.pk_task), 0) AS unfinished_task_num "
					+ "from "
					+ "yjwy_quality_inspecttask yqit "
					+ "where yqit.is_rectify = '1' "
					+ whrSql
					+ "and yqit.task_state = '20' "
					+ "group by yqit.fk_project, yqit.task_rectifyuser_pk) a "
				+ "on a.fk_project = yqit.fk_project "
				+ "and a.task_rectifyuser_pk = yqit.task_rectifyuser_pk "
				//完成数
				+ "left join "
					+ "(select yqit.task_rectifyuser_pk, yqit.fk_project,"
					+ "IFNULL(COUNT(yqit.pk_task), 0) AS finished_task_num "
					+ "from "
					+ "yjwy_quality_inspecttask yqit "
					+ "where yqit.is_rectify = '1' "
					+ whrSql
					+ "and yqit.task_state = '30' "
					+ "group by yqit.fk_project, yqit.task_rectifyuser_pk) b "
				+ "on b.fk_project = yqit.fk_project "
				+ "and b.task_rectifyuser_pk = yqit.task_rectifyuser_pk "
				//超期完成数
				+ "left join "
					+ "(select yqit.task_rectifyuser_pk, yqit.fk_project,"
					+ "IFNULL(COUNT(yqit.pk_task), 0) AS expire_finished_num "
					+ "from "
					+ "yjwy_quality_inspecttask yqit "
					+ "where yqit.is_rectify = '1' "
					+ whrSql
					+ "and yqit.task_state = '30' "
					+ "and yqit.task_iscq = '1'"
					+ "group by yqit.fk_project, yqit.task_rectifyuser_pk) c "
				+ "on c.fk_project = yqit.fk_project "
				+ "and c.task_rectifyuser_pk = yqit.task_rectifyuser_pk "
				//总任务数 (包括系统生成的任务 + 手机生成的任务)
				+ "left join "
					+ "(select yqit.task_rectifyuser_pk, yqit.fk_project,"
					+ "IFNULL(COUNT(yqit.pk_task), 0) AS total_task_num "
					+ "from "
					+ "yjwy_quality_inspecttask yqit "
					+ "where yqit.is_rectify = '1' "
					+ whrSql
					+ "group by yqit.fk_project, yqit.task_rectifyuser_pk) d "
				+ "on d.fk_project = yqit.fk_project "
				+ "and d.task_rectifyuser_pk = yqit.task_rectifyuser_pk "
				//核查总任务数（只有系统生成的任务）
				+ "left join "
					+ "(select yqit.task_rectifyuser_pk, yqit.fk_project,"
					+ "IFNULL(COUNT(yqit.pk_task), 0) AS check_task_num "
					+ "from "
					+ "yjwy_quality_inspecttask yqit "
					+ "where yqit.is_rectify = '1' "
					+ whrSql
					+ "and yqit.task_generate_mode = '0'"
					+ "group by yqit.fk_project, yqit.task_rectifyuser_pk) e "
				+ "on e.fk_project = yqit.fk_project "
				+ "and e.task_rectifyuser_pk = yqit.task_rectifyuser_pk "
				+ "where yqit.is_rectify = '1' "
				+ whrSql
				+ "group by yqit.fk_project, yqit.task_rectifyuser_pk "
				+ "limit " + queryVo.getStart() + "," + queryVo.getLimit();
		return sql;
	}

	/**
	 * 获取总条数的 sql
	 * 
	 * @return
	 */
	public static String getCountSql(PersonnelRectificationQueryVo queryVo) {
		String whrSql = "";
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
		
		whrSql = whrSql + " and yqit.pk_crop='"+UserUtil.getCurrentUser().getPk_crop()+"' ";
		
		String sql = "select count(*) "
				+ "from "
				+ "(select "
				+ "yqit.fk_area as area_id, "
				+ "yqit.fk_project as project_id, "
				+ "yqit.fk_job as station_id, "
				+ "yqit.create_time "
				+ "from "
				+ "yjwy_quality_inspecttask yqit "
				+ "where yqit.is_rectify = '1' "
				+ whrSql 
				+ "group by yqit.fk_project,yqit.task_rectifyuser_pk) yqit ";
				
		return sql;

	}
	
	/**
	 * 获取子页面的 列表信息
	 * @param queryVo
	 * @return
	 */
	public static Map<String, Object> getSonPageSql(InspectTaskModelVo queryVo) {
		Map<String, Object> backMap = new HashMap<String, Object>();
		String sql ="select "
				+ "yqit.pk_task, "
				+ "ypp.project_name_ as project_name, "
				+ "yqit.task_generate_mode, "
				+ "yqit.task_state, "
				+ "yqit.fk_dept, "
				+ "yqit.fk_taskuser, "
				+ "ypuf.user_name_ as check_user_name, "
				+ "yqit.task_currentuser_pk, "
				+ "yqit.task_inspectresult, "
				+ "yqit.task_qualified_time, "
				+ "yqit.check_content, "
				+ "ypu.user_name_ as task_rectifyuser_name, "
				+ "yqit.task_rectify_finishtime, "
				+ "yqit.task_rectify_starttime, "
				+ "yqit.check_state,"
				+ "yqit.check_time, "
				+ "yqit.task_rectifyuser_pk, "
				+ "yqit.fk_project "
				+ "from "
					+ "yjwy_quality_inspecttask yqit "
				+ "left join yjwy_pub_project ypp "
					+ "on yqit.fk_project = ypp.pk_project_ "
				+ "left join yjwy_pub_user ypu "
					+ "on yqit.task_rectifyuser_pk = ypu.pk_user_ "
				+ "left join yjwy_pub_user ypuf "
					+ "on yqit.fk_taskuser = ypuf.pk_user_ "
					+ "where yqit.fk_project = '"+queryVo.getFk_project()+"' "
							+ "and yqit.task_rectifyuser_pk = '"+queryVo.getTask_rectifyuser_pk()+"' "
									+ "and yqit.is_rectify = '1' "; 
		
		String countSql = "select count(*) "
				+ "from yjwy_quality_inspecttask yqit "
				+ "where yqit.fk_project = '"+queryVo.getFk_project()+"' "
						+ "and yqit.task_rectifyuser_pk = '"+queryVo.getTask_rectifyuser_pk()+"' "
								+ "and yqit.is_rectify = '1' ";
		String whrSql = "";
		switch (queryVo.getTask_type()) {
			//0:未完成整改条件；
			case "0":{
				whrSql = "and yqit.task_state = '20' ";
				break;
			}
			//1:完成整改条件;
			case "1":{
				whrSql = "and yqit.task_state = '30' ";
				break;
			}
			//2:过期完成整改条件;
			case "2":{
				whrSql = "and yqit.task_iscq = '1' "
						+ "and yqit.task_state = '30' ";
				break;
			}
			//3:总任务条件;
			case "3":{
				whrSql = "";
				break;
			}
			//4:核查总任务条件;
			case "4":{
				whrSql = "and task_generate_mode = '0' ";
				break;
			}
		
		}
		backMap.put("sql", sql+ whrSql);
		backMap.put("countSql", countSql+ whrSql);
		return backMap;
	}
	

}
