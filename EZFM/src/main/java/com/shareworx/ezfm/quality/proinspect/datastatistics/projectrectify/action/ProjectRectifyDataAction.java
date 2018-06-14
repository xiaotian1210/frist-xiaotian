package com.shareworx.ezfm.quality.proinspect.datastatistics.projectrectify.action;

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
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.device.util.service.YJWYDeviceService;
import com.shareworx.ezfm.quality.proinspect.datastatistics.projectdata.vo.ProjectDataQueryVo;
import com.shareworx.ezfm.quality.proinspect.datastatistics.rectification.action.RectificationRateAction;
import com.shareworx.ezfm.utils.ImpAndExpExcel;
import com.shareworx.ezfm.utils.MathUtils;
import com.shareworx.ezfm.utils.UserUtil;

/**
 * 项目整改数据统计报表
 * @author yuting.wang
 *
 */
@Controller
@RequestMapping("ezfm/quality/datastatistics/projectrectify")
public class ProjectRectifyDataAction {

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
	public final static String PAGE_FORWARD = "ezfm/quality/datastatistics/projectrectify/projectdata_rectify_list";
	
	/**
	 * 转向列表页面
	 * @return
	 */
	@RequestMapping(value="index", method=RequestMethod.GET)
	public ModelAndView listForward(){
		return new ModelAndView(PAGE_FORWARD);
	}

	

	
	/**
	 * 获取条件查询语句
	 * @param queryVo
	 * @return
	 */
	public String getConditionQuerySql(ProjectDataQueryVo queryVo){
		YJWYUserModel user = UserUtil.getCurrentUser();
		String whr = "";
		
		if(!StringUtils.isEmpty(queryVo.getArea_id())){
			whr = whr + " and yqit.fk_area='"+queryVo.getArea_id()+"' ";
		}
		if(!StringUtils.isEmpty(queryVo.getProject_id())){
			whr = whr + " and yqit.fk_project='"+queryVo.getProject_id()+"' ";
		}else{
//			Set<String> projectIds = deviceService.queryProjectIdsByPkUser(user.getPk_user());
//			String ids = "";
//			for(String id : projectIds){
//				ids = ids+",'"+id+"'";
//			}
//			//ids非空再substring(1)，不然报空指针
//			if(!StringUtils.isEmpty(ids)){
//				whr = whr+" and yqit.fk_project in ("+ids.substring(1)+") ";
//			}else{
//				whr = whr+" and yqit.fk_project in ('') ";
//			}
		}
		if(!StringUtils.isEmpty(queryVo.getStart_time())){
			//whereSql.append(" and total.task_rectify_starttime>='"+queryVo.getStart_time()+"'");
			whr = whr + " and yqit.create_time >= '" + queryVo.getStart_time() + " 00:00:00' ";
		}
		if(!StringUtils.isEmpty(queryVo.getEnd_time())){
			//whereSql.append(" and total.task_rectify_starttime<'"+queryVo.getEnd_time()+"'");
			whr = whr + "and yqit.create_time <= '"+ queryVo.getEnd_time() + " 23:59:59' ";
		}
		// 任务开始时间  和  任务结束时间
		if (!StringUtils.isEmpty(queryVo.getTask_start_time()) && !StringUtils.isEmpty(queryVo.getTask_end_time())) {
			whr = whr + " and yqit.task_start_time >= '" + queryVo.getTask_start_time() + "' " 
					+ "and yqit.task_deadline_date <= '"+ queryVo.getTask_end_time() + "' ";
		}else{
			if (!StringUtils.isEmpty(queryVo.getTask_start_time())) {
				whr = whr + " and yqit.task_start_time = '" + queryVo.getTask_start_time() + "' ";
			}else if (!StringUtils.isEmpty(queryVo.getTask_end_time())) {
				whr = whr + " and yqit.task_deadline_date = '" + queryVo.getTask_end_time() + "' ";
			}
		}
		
		whr = whr + " and yqit.pk_crop='"+UserUtil.getCurrentUser().getPk_crop()+"' ";
		
		String querySql = " select ypp.project_name_ AS project_name, "
				+ "ypa.area_name_ AS area_name, "
				+ "IFNULL(t1.not_complete_rectify, 0) AS not_complete_rectify, "
				+ "IFNULL(t2.complete_rectify, 0) AS complete_rectify, "
				+ "IFNULL(t3.overdue_complete_rectify,0) AS overdue_complete_rectify, "
				+ "IFNULL(t4.assess_total_task, 0) AS assess_total_task, "
				+ "IFNULL(t5.total_task, 0) AS total_task "
				+ "from yjwy_quality_inspecttask yqit "
				+ "LEFT JOIN yjwy_pub_project ypp "
				+ "ON yqit.fk_project = ypp.pk_project_ "
				+ "LEFT JOIN yjwy_pub_area ypa "
				+ "ON ypa.pk_area_ = yqit.fk_area "
				//没有完成整改的任务
				+ "LEFT JOIN "
					+ "("
						+ "select count(yqit.pk_task) AS not_complete_rectify, "
						+ "yqit.fk_area, yqit.fk_project "
						+ "from yjwy_quality_inspecttask yqit "
						+ "where yqit.is_rectify = '1' "
						+ "and yqit.task_state = '20' "
						+ whr 
						+ "group by yqit.fk_project, yqit.fk_area "
					+ ") t1 "
				+ "ON t1.fk_project = yqit.fk_project "
				+ "AND t1.fk_area = yqit.fk_area "
				//完成整改的任务
				+ "LEFT JOIN "
					+ "("
						+ "select count(yqit.pk_task) AS complete_rectify, "
						+ "yqit.fk_area, yqit.fk_project "
						+ "from yjwy_quality_inspecttask yqit "
						+ "where yqit.is_rectify = '1' "
						+ "and yqit.task_state = '30' "
						+ whr 
						+ "group by yqit.fk_project, yqit.fk_area "
					+ ") t2 "
				+ "ON t2.fk_project = yqit.fk_project "
				+ "AND t2.fk_area = yqit.fk_area "
				//超期完成整改的任务
				+ "LEFT JOIN "
					+ "("
						+ "select count(yqit.pk_task) AS overdue_complete_rectify, "
						+ "yqit.fk_area, yqit.fk_project "
						+ "from yjwy_quality_inspecttask yqit "
						+ "where yqit.is_rectify = '1' "
						+ "and yqit.task_state = '30' "
						+ "and yqit.task_iscq = '1' "
						+ whr 
						+ "group by yqit.fk_project, yqit.fk_area "
					+ ") t3 "
				+ "ON t3.fk_project = yqit.fk_project "
				+ "AND t3.fk_area = yqit.fk_area "
				//考核 总任务
				+ "LEFT JOIN "
					+ "("
						+ "select count(yqit.pk_task) AS assess_total_task, "
						+ "yqit.fk_area, yqit.fk_project "
						+ "from yjwy_quality_inspecttask yqit "
						+ "where yqit.task_generate_mode = '0' "
						+ whr 
						+ "group by yqit.fk_project, yqit.fk_area "
					+ ") t4 "
				+ "ON t4.fk_project = yqit.fk_project "
				+ "AND t4.fk_area = yqit.fk_area "
				//总任务
				+ "LEFT JOIN "
					+ "("
						+ "select count(yqit.pk_task) AS total_task, "
						+ "yqit.fk_area, yqit.fk_project "
						+ "from yjwy_quality_inspecttask yqit "
						+ "where 1 = 1 "
						+ whr 
						+ "group by yqit.fk_project, yqit.fk_area "
					+ ") t5 "
				+ "ON t5.fk_project = yqit.fk_project "
				+ "AND t5.fk_area = yqit.fk_area "
				+ "where yqit.is_rectify = '1' "
				+  whr
				+ "GROUP BY yqit.fk_project, yqit.fk_area "
				+ " limit "+ queryVo.getStart() + "," + queryVo.getLimit();
		return querySql;
	}
	
	/**
	 * 获取查询的总条数
	 * @param queryVo
	 * @return
	 */
	public String getCountSql(ProjectDataQueryVo queryVo){
		String whr = "";
		
		if(!StringUtils.isEmpty(queryVo.getArea_id())){
			whr = whr + " and yqit.fk_area='"+queryVo.getArea_id()+"' ";
		}
		if(!StringUtils.isEmpty(queryVo.getProject_id())){
			whr = whr + " and yqit.fk_project='"+queryVo.getProject_id()+"' ";
		}
		if(!StringUtils.isEmpty(queryVo.getStart_time())){
			//whereSql.append(" and total.task_rectify_starttime>='"+queryVo.getStart_time()+"'");
			whr = whr + " and yqit.create_time >= '" + queryVo.getStart_time() + " 00:00:00' ";
		}
		if(!StringUtils.isEmpty(queryVo.getEnd_time())){
			//whereSql.append(" and total.task_rectify_starttime<'"+queryVo.getEnd_time()+"'");
			whr = whr + "and yqit.create_time <= '"+ queryVo.getEnd_time() + " 23:59:59' ";
		}
		
		whr = whr + " and yqit.pk_crop='"+UserUtil.getCurrentUser().getPk_crop()+"' ";
		
		String countSql = " select count(*) "
				+ "from "
				+ "( "
					+ "select yqit.fk_project "
					+ "from yjwy_quality_inspecttask yqit "
					+ "where yqit.is_rectify = '1' "
					+  whr 
					+ "GROUP BY yqit.fk_project, yqit.fk_area "
				+ ") m ";
				
				
		return countSql;
	}
	/**
	 * 拼接where条件
	 * @param queryVo
	 * @return
	 *//*
	public String getWhereQuerySql(ProjectDataQueryVo queryVo){
		StringBuffer whereSql =new StringBuffer();
		whereSql.append(" where 1=1");
		if(!StringUtils.isEmpty(queryVo.getArea_id())){
			whereSql.append(" and total.fk_area='"+queryVo.getArea_id()+"'");
		}
		if(!StringUtils.isEmpty(queryVo.getProject_id())){
			whereSql.append(" and total.fk_project='"+queryVo.getProject_id()+"'");
		}
		if(!StringUtils.isEmpty(queryVo.getStart_time())){
			whereSql.append(" and total.task_rectify_starttime>='"+queryVo.getStart_time()+"'");
		}
		if(!StringUtils.isEmpty(queryVo.getEnd_time())){
			whereSql.append(" and total.task_rectify_starttime<'"+queryVo.getEnd_time()+"'");
		}
		whereSql.append(" limit "+ queryVo.getStart() + "," + queryVo.getLimit());
		return whereSql.toString();
	}*/
	/**
	 * 条件查询
	 * @param queryVo
	 * @return
	 */
	@RequestMapping(value="query", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult queryTaskForContion(@RequestBody ProjectDataQueryVo queryVo)  {
		JdbcTemplate read = dao.getReadTemplate();
		System.out.println(queryVo.toString());
		String querySql = getConditionQuerySql(queryVo);
		// 查询条数
		String countSql = getCountSql(queryVo);
		int count = read.queryForObject(countSql, Integer.class);
		
		List<Map<String, Object>> list = getPageList(read.queryForList(querySql));
		
		
		
		/*List<ProjectRectifyDataVo> projectDataList = read.query(querySql,new RowMapper<ProjectRectifyDataVo>(){
			@Override
			public ProjectRectifyDataVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProjectRectifyDataVo rectifyVo = new ProjectRectifyDataVo();
				rectifyVo.setArea_name(rs.getString("area_name"));
				rectifyVo.setProject_name(rs.getString("project_name"));
				rectifyVo.setNot_complete_rectify(rs.getString("not_complete_Rectify"));
				rectifyVo.setComplete_rectify(rs.getString("complete_Rectify"));
				rectifyVo.setOverdue_complete_rectify(rs.getString("overdue_complete_Rectify"));
				rectifyVo.setTotal_task(rs.getString("total_task"));
				rectifyVo.setAssess_total_task(rs.getString("assess_total_task"));
				*//**整改完成率*//*
				String complete_rectify_rate = MathUtils.getPercent(rectifyVo.getComplete_rectify(), rectifyVo.getTotal_task());
				*//**整改及时率*//*
				int cr = Integer.valueOf(StringUtils.isEmpty(rectifyVo.getComplete_rectify())?"0":rectifyVo.getComplete_rectify());
				int ocr=Integer.valueOf(StringUtils.isEmpty(rectifyVo.getOverdue_complete_rectify())?"0":rectifyVo.getOverdue_complete_rectify()); 
				String timely_rectify = cr-ocr+"";
				String timely_rectify_rate = MathUtils.getPercent(timely_rectify, rectifyVo.getTotal_task());
				*//**应整改量*//*
				String should_rectify_number = MathUtils.forDivision(rectifyVo.getTotal_task(), "10");
				rectifyVo.setComplete_rectify_rate(complete_rectify_rate);
				rectifyVo.setTimely_rectify_rate(timely_rectify_rate);
				rectifyVo.setShould_rectify_number(should_rectify_number);
				return rectifyVo;
			}
		});
		ProjectRectifyDataVo[] rectifyData = projectDataList.toArray(new ProjectRectifyDataVo[] {});*/
		ModelAndResult mar = new ModelAndResult(list);
		mar.setTotal(count);
		return mar;
	}
	
	/**
	 * 导出报表
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "export", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult doExpDataStructureByTable(@RequestParam(value="param", required=false) String param) {
		JdbcTemplate read = dao.getReadTemplate();
		ProjectDataQueryVo queryVo = JSONObject.parseObject(param,ProjectDataQueryVo.class);
		// 查询条数
		String countSql = getCountSql(queryVo);
		int count = read.queryForObject(countSql, Integer.class);
		queryVo.setStart(0);
		queryVo.setLimit(count);
		//根据条件查询
		String querySql = getConditionQuerySql(queryVo);
		
		List<Map<String, Object>> list = getPageList(read.queryForList(querySql));
		
		/*List<ProjectRectifyDataVo> projectDataList = read.query(querySql,new RowMapper<ProjectRectifyDataVo>(){
			@Override
			public ProjectRectifyDataVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProjectRectifyDataVo rectifyVo = new ProjectRectifyDataVo();
				rectifyVo.setArea_name(rs.getString("area_name"));
				rectifyVo.setProject_name(rs.getString("project_name"));
				rectifyVo.setNot_complete_rectify(rs.getString("not_complete_Rectify"));
				rectifyVo.setComplete_rectify(rs.getString("complete_Rectify"));
				rectifyVo.setOverdue_complete_rectify(rs.getString("overdue_complete_Rectify"));
				rectifyVo.setTotal_task(rs.getString("total_task"));
				rectifyVo.setAssess_total_task(rs.getString("assess_total_task"));
				*//**整改完成率*//*
				String complete_rectify_rate = MathUtils.getPercent(rectifyVo.getComplete_rectify(), rectifyVo.getTotal_task());
				*//**整改及时率*//*
				int cr = Integer.valueOf(StringUtils.isEmpty(rectifyVo.getComplete_rectify())?"0":rectifyVo.getComplete_rectify());
				int ocr=Integer.valueOf(StringUtils.isEmpty(rectifyVo.getOverdue_complete_rectify())?"0":rectifyVo.getOverdue_complete_rectify()); 
				String timely_rectify = cr-ocr+"";
				String timely_rectify_rate = MathUtils.getPercent(timely_rectify, rectifyVo.getTotal_task());
				*//**应整改量*//*
				String should_rectify_number = MathUtils.forDivision(rectifyVo.getTotal_task(), "10");
				
				
				rectifyVo.setComplete_rectify_rate(complete_rectify_rate);
				rectifyVo.setTimely_rectify_rate(timely_rectify_rate);
				rectifyVo.setShould_rectify_number(should_rectify_number);
				
				return rectifyVo;
			}
		});*/
		
		return ImpAndExpExcel.doExpExcel(list, new String[]{"area_name","project_name",
				"not_complete_rectify","complete_rectify","overdue_complete_rectify",
				"total_task","assess_total_task","complete_rectify_rate",
				"timely_rectify_rate","should_rectify_number"}, 
				"templates/templates/quality/品质核查项目整改数据表.xls", 2);

	}
	
	
	
	/**
	 * 补充页面上 缺少的 值
	 * @param list
	 * @return
	 */
	public static List<Map<String, Object>> getPageList(List<Map<String, Object>> list) {
		
		for (Map<String, Object> map : list) {
			
			/**整改完成率*/
			//String complete_rectify_rate = MathUtils.getPercent(rectifyVo.getComplete_rectify(), rectifyVo.getTotal_task());
			String complete_rectify_rate = MathUtils.getPercent(String.valueOf(map.get("complete_rectify")), 
					String.valueOf(map.get("total_task")));
			map.put("complete_rectify_rate", complete_rectify_rate);
			
			/**整改及时率*/
			int cr = Integer.valueOf(String.valueOf(map.get("complete_rectify")));
			int ocr=Integer.valueOf(String.valueOf(map.get("overdue_complete_rectify"))); 
			String timely_rectify = cr-ocr+"";
			String timely_rectify_rate = MathUtils.getPercent(timely_rectify, String.valueOf(map.get("total_task")));
			map.put("timely_rectify_rate", timely_rectify_rate);
			
			/**应整改量   (考核总任务 的 百分之十)*/
			String should_rectify_number = MathUtils.forDivision(String.valueOf(map.get("assess_total_task")), "10");
			map.put("should_rectify_number", should_rectify_number);
			
		}
		
		return list;
	}
	
}
