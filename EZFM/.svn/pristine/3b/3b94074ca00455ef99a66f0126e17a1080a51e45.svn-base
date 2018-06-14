package com.shareworx.ezfm.quality.proinspect.datastatistics.projectdata.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
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
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.device.util.service.YJWYDeviceService;
import com.shareworx.ezfm.quality.proinspect.datastatistics.projectdata.vo.ProjectDataQueryVo;
import com.shareworx.ezfm.quality.proinspect.datastatistics.projectdata.vo.ProjectDataVo;
import com.shareworx.ezfm.quality.proinspect.datastatistics.rectification.action.RectificationRateAction;
import com.shareworx.ezfm.utils.ImpAndExpExcel;
import com.shareworx.ezfm.utils.MathUtils;
import com.shareworx.ezfm.utils.UserUtil;

/**
 * 项目数据统计表
 * @author yuting.wang
 *
 */
@Controller
@RequestMapping("ezfm/quality/datastatistics/projectdata")
public class ProjectDataAction {
	
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
	public final static String PAGE_FORWARD = "ezfm/quality/datastatistics/projectdata/projectdata_list";
	
	/**
	 * 转向列表页面
	 * @return
	 */
	@RequestMapping(value="index", method=RequestMethod.GET)
	public ModelAndView listForward(){
		return new ModelAndView(PAGE_FORWARD);
	}
	
	
	
	
	/**
	 * 拼接where条件
	 * @param queryVo
	 * @return
	 */
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
			whereSql.append(" and total.task_deadline_date >= '"+queryVo.getStart_time()+"'");
		}
		if(!StringUtils.isEmpty(queryVo.getEnd_time())){
			whereSql.append(" and total.task_deadline_date <= '"+queryVo.getEnd_time()+"'");
		}
		if(!StringUtils.isEmpty(queryVo.getDept_id())){
			whereSql.append(" and total.fk_dept='"+queryVo.getDept_id()+"'");
		}
		whereSql.append(" limit "+ queryVo.getStart() + "," + queryVo.getLimit());
		return whereSql.toString();
	}
	
	/**
	 * 获取条件查询语句
	 * @param queryVo
	 * @return
	 */
	public String getConditionQuerySql(ProjectDataQueryVo queryVo){
		YJWYUserModel user = UserUtil.getCurrentUser();
		StringBuffer whereSql =new StringBuffer();
		whereSql.append(" where 1=1");
		
		if(!StringUtils.isEmpty(queryVo.getArea_id())){
			whereSql.append(" and total.fk_area='"+queryVo.getArea_id()+"'");
		}
		if(!StringUtils.isEmpty(queryVo.getProject_id())){
			whereSql.append(" and total.fk_project='"+queryVo.getProject_id()+"'");
		}else{
			Set<String> projectIds = deviceService.queryProjectIdsByPkUser(user.getPk_user());
			String ids = "";
			for(String id : projectIds){
				ids = ids+",'"+id+"'";
			}
			//ids非空再substring(1)，不然报空指针
			if(!StringUtils.isEmpty(ids)){
//				whereSql = whrSql+" and fk_project in ("+ids.substring(1)+") ";
			    whereSql.append(" and total.fk_project in ("+ids.substring(1)+")");
			}else{
//				whereSql = whrSql+" and fk_project in ('') ";
				whereSql.append(" and fk_project in ('')");
			}
		}
		if(!StringUtils.isEmpty(queryVo.getStart_time())){
			whereSql.append(" and total.task_deadline_date >= '"+queryVo.getStart_time()+"'");
		}
		if(!StringUtils.isEmpty(queryVo.getEnd_time())){
			whereSql.append(" and total.task_deadline_date <= '"+queryVo.getEnd_time()+"'");
		}
		if(!StringUtils.isEmpty(queryVo.getDept_id())){
			whereSql.append(" and total.fk_dept='"+queryVo.getDept_id()+"'");
		}
		String cropSql=" and yjwy_quality_inspecttask.pk_crop='"+UserUtil.getCurrentUser().getPk_crop()+"' ";
		
		String querySql =" SELECT total.fk_area,total.fk_project,total.task_deadline_date,"
				+ " project.project_name_ as project_name,total.total_task,pending.pending_task,complete.complete_task,"
				+ " notComplete.not_complete_task,assessTotal.assess_total_task,rectifyComplete.rectify_complete_task"
				+ " FROM "
					+ "(SELECT count(total.pk_task) AS total_task,total.fk_project,total.fk_area,"
					+ "total.task_deadline_date,total.fk_dept"
					+ " FROM yjwy_quality_inspecttask total"
					+ whereSql +" and total.pk_crop='"+UserUtil.getCurrentUser().getPk_crop()+"'"
					+ " GROUP BY total.fk_project ) total "
				+ " LEFT JOIN (SELECT count(pk_task) AS pending_task,fk_project"
				+ " FROM yjwy_quality_inspecttask WHERE task_state='10' "+cropSql+ "GROUP BY fk_project) pending "
				+ " ON pending.fk_project = total.fk_project"
				+ " LEFT JOIN (SELECT count(pk_task) AS complete_task,fk_project"
				+ " FROM yjwy_quality_inspecttask WHERE task_state='30' "+cropSql+ "GROUP BY fk_project) complete"
				+ " ON complete.fk_project=total.fk_project"
				+ " LEFT JOIN (SELECT count(pk_task) AS not_complete_task,fk_project"
				+ " FROM yjwy_quality_inspecttask WHERE task_state IN('10','20') "+cropSql+ "GROUP BY fk_project) notComplete"
				+ " ON notComplete.fk_project=total.fk_project"
				+ " LEFT JOIN (SELECT count(pk_task) AS assess_total_task,fk_project"
				+ " FROM yjwy_quality_inspecttask WHERE task_generate_mode='0'  "+cropSql+ "GROUP BY fk_project) assessTotal"
				+ " ON assessTotal.fk_project = total.fk_project"
				+ " LEFT JOIN (SELECT count(pk_task) AS rectify_complete_task,fk_project"
				+ " FROM yjwy_quality_inspecttask WHERE task_state='30' and is_rectify='1' "+cropSql+ "GROUP BY fk_project) rectifyComplete"
				+ " ON rectifyComplete.fk_project = total.fk_project"
				+ " LEFT JOIN (SELECT *"
				+ " FROM yjwy_pub_project WHERE yjwy_pub_project.pk_crop_='"+UserUtil.getCurrentUser().getPk_crop()+"') project ON project.pk_project_ = total.fk_project "
				+ whereSql 
				+ " limit "+ queryVo.getStart() + "," + queryVo.getLimit();
		return querySql;
	}
	
	/**
	 * 获取查询的总条数
	 * @param queryVo
	 * @return
	 */
	public String getCountSql(ProjectDataQueryVo queryVo){
		StringBuffer whereSql =new StringBuffer();
		whereSql.append(" where 1=1");
		if(!StringUtils.isEmpty(queryVo.getArea_id())){
			whereSql.append(" and total.fk_area='"+queryVo.getArea_id()+"'");
		}
		if(!StringUtils.isEmpty(queryVo.getProject_id())){
			whereSql.append(" and total.fk_project='"+queryVo.getProject_id()+"'");
		}
		if(!StringUtils.isEmpty(queryVo.getStart_time())){
			whereSql.append(" and total.task_deadline_date >= '"+queryVo.getStart_time()+"'");
		}
		if(!StringUtils.isEmpty(queryVo.getEnd_time())){
			whereSql.append(" and total.task_deadline_date <= '"+queryVo.getEnd_time()+"'");
		}
		if(!StringUtils.isEmpty(queryVo.getDept_id())){
			whereSql.append(" and total.fk_dept='"+queryVo.getDept_id()+"'");
		}

		
		String cropSql=" and yjwy_quality_inspecttask.pk_crop='"+UserUtil.getCurrentUser().getPk_crop()+"' ";
		
		String countSql =" SELECT count(*)"
				+ " FROM "
				+ "(SELECT count(total.pk_task) AS total_task,total.fk_project,total.fk_area,"
				+ "total.task_deadline_date,total.fk_dept"
				+ " FROM yjwy_quality_inspecttask total"
				+ whereSql +" and total.pk_crop='"+UserUtil.getCurrentUser().getPk_crop()+"'"
				+ " GROUP BY total.fk_project) total "
				+ " LEFT JOIN (SELECT count(pk_task) AS pending_task,fk_project"
				+ " FROM yjwy_quality_inspecttask WHERE task_state='10' "+cropSql+ "GROUP BY fk_project) pending "
				+ " ON pending.fk_project = total.fk_project"
				+ " LEFT JOIN (SELECT count(pk_task) AS complete_task,fk_project"
				+ " FROM yjwy_quality_inspecttask WHERE task_state='30' "+cropSql+ "GROUP BY fk_project) complete"
				+ " ON complete.fk_project=total.fk_project"
				+ " LEFT JOIN (SELECT count(pk_task) AS not_complete_task,fk_project"
				+ " FROM yjwy_quality_inspecttask WHERE task_state IN('10','20') "+cropSql+ "GROUP BY fk_project) notComplete"
				+ " ON notComplete.fk_project=total.fk_project"
				+ " LEFT JOIN (SELECT count(pk_task) AS assess_total_task,fk_project"
				+ " FROM yjwy_quality_inspecttask WHERE task_generate_mode='0' "+cropSql+ "GROUP BY fk_project) assessTotal"
				+ " ON assessTotal.fk_project = total.fk_project"
				+ " LEFT JOIN (SELECT count(pk_task) AS rectify_complete_task,fk_project"
				+ " FROM yjwy_quality_inspecttask WHERE task_state='30' and is_rectify='1' "+cropSql+ "GROUP BY fk_project) rectifyComplete"
				+ " ON rectifyComplete.fk_project = total.fk_project "
				+ whereSql;
		
		
		return countSql;
	}
	
	/**
	 * 条件查询
	 * @param queryVo
	 * @return
	 */
	@RequestMapping(value="query", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult queryTaskForContion(@RequestBody ProjectDataQueryVo queryVo)  {
		JdbcTemplate read = dao.getReadTemplate();
		System.out.println(queryVo.toString());
		//String querySql = getConditionQuerySql()+getWhereQuerySql(queryVo);
		String querySql = getConditionQuerySql(queryVo);
		// 查询条数
		String countSql = getCountSql(queryVo);
		int count = read.queryForObject(countSql, Integer.class);
		
		List<ProjectDataVo> projectDataList = read.query(querySql,new RowMapper<ProjectDataVo>(){
			@Override
			public ProjectDataVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProjectDataVo projectDataVo = new ProjectDataVo();
				projectDataVo.setProject_name(rs.getString("project_name"));
				projectDataVo.setTotal_task(rs.getString("total_task"));
				projectDataVo.setPending_task(rs.getString("pending_task"));
				projectDataVo.setComplete_task(rs.getString("complete_task"));
				projectDataVo.setRectify_complete_task(rs.getString("rectify_complete_task"));
				projectDataVo.setNot_complete_task(rs.getString("not_complete_task"));
				projectDataVo.setAssess_total_task(rs.getString("assess_total_task"));
				projectDataVo.setFk_area(rs.getString("fk_area"));
				/**任务完成率*/
				String ctr = MathUtils.getPercent(projectDataVo.getComplete_task(), projectDataVo.getTotal_task());
				projectDataVo.setComplete_task_rate(ctr);
				return projectDataVo;
			}
		});
		ProjectDataVo[] projectData = projectDataList.toArray(new ProjectDataVo[] {});
		ModelAndResult mar = new ModelAndResult(projectData);
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
		List<ProjectDataVo> projectDataList = read.query(querySql,new RowMapper<ProjectDataVo>(){
			@Override
			public ProjectDataVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProjectDataVo projectDataVo = new ProjectDataVo();
				projectDataVo.setProject_name(rs.getString("project_name"));
				projectDataVo.setTotal_task(rs.getString("total_task"));
				projectDataVo.setPending_task(rs.getString("pending_task"));
				projectDataVo.setComplete_task(rs.getString("complete_task"));
				projectDataVo.setRectify_complete_task(rs.getString("rectify_complete_task"));
				projectDataVo.setNot_complete_task(rs.getString("not_complete_task"));
				projectDataVo.setAssess_total_task(rs.getString("assess_total_task"));
				projectDataVo.setFk_area(rs.getString("fk_area"));
				/**任务完成率*/
				String ctr = MathUtils.getPercent(projectDataVo.getComplete_task(), projectDataVo.getTotal_task());
				projectDataVo.setComplete_task_rate(ctr);
				return projectDataVo;
			}
		});
		
		return ImpAndExpExcel.doExpExcel(projectDataList, new String[]{"project_name","total_task",
				"pending_task","complete_task","rectify_complete_task",
				"not_complete_task","assess_total_task","complete_task_rate"}, 
				"templates/templates/quality/品质核查项目数据统计表.xls", 2);

	}
 }
