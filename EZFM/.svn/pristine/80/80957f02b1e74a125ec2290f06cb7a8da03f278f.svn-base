package com.shareworx.ezfm.quality.proinspect.inspect.insptask.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.DateTimeUtil;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.baseinfo.pub.service.IBaseInfoQueryService;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.device.util.service.YJWYDeviceService;
import com.shareworx.ezfm.quality.proinspect.inspect.insprecord.vo.RecordProcessVo;
import com.shareworx.ezfm.quality.proinspect.inspect.insptask.dao.InspectTaskDao;
import com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskModel;
import com.shareworx.ezfm.quality.proinspect.inspect.insptask.service.InspectTaskBusinessService;
import com.shareworx.ezfm.quality.proinspect.inspect.insptask.vo.InspectTaskVo;
import com.shareworx.ezfm.quality.proinspect.inspect.insptask.vo.TaskQueryVo;
import com.shareworx.ezfm.utils.ImpAndExpExcel;
import com.shareworx.ezfm.utils.UserUtil;

/**
 * 核查与整改任务操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/quality/proinspect/inspect/insptask")
public class InspectTaskAction {

	final static Logger log = Logger.getLogger(InspectTaskAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "ezfm/quality/proinspect/inspect/task/task_list";
	/** 跳转子页面 */
	public final static String SONPAGE_FORWARD = "ezfm/quality/proinspect/inspect/task/task_sonpage";
	
	@Autowired
	@Qualifier(InspectTaskBusinessService.ID)
	private InspectTaskBusinessService service;
	
	public void setService(InspectTaskBusinessService service) {
		this.service = service;
	}
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	
	@Autowired
	@Qualifier(IBaseInfoQueryService.ID)
	IBaseInfoQueryService baseinfoQueryService;
	
	public void setBaseinfoQueryService(IBaseInfoQueryService baseinfoQueryService) {
		this.baseinfoQueryService = baseinfoQueryService;
	}
	
	@Autowired
	@Qualifier(YJWYDeviceService.ID)
	YJWYDeviceService deviceService;
	
	public void setDeviceService(YJWYDeviceService deviceService) {
		this.deviceService = deviceService;
	}
	
	/**
	 * 转向列表页面
	 * @return
	 */
	@RequestMapping(value="index", method=RequestMethod.GET)
	public ModelAndView listForward(){
		return new ModelAndView(PAGE_FORWARD);
	}
	
	/**
	 * 转向子页面
	 * @return
	 */
	@RequestMapping(value="son/index/{id}", method=RequestMethod.GET)
	public ModelAndView sonListForward(@PathVariable(value="id") String id){
		String sql = " select "+
					 " t.pk_task,t.task_code,t.task_state,t.task_generate_mode,t.task_pc_name,t.specialty,t.task_score,t.task_start_time,t.task_end_time,t.task_deadline_date,t.check_state,t.check_time,t.check_content,"+
					 " p. project_name_ 'project_name',"+
					 " s.inspstan_category,s.inspstan_code,s.inspstan_category_description,s.inspstan_inpectmethod,s.inspstan_performance_norm"+
					 " from (select * from yjwy_quality_inspecttask where pk_task='"+id+"')t"+
					 " left join yjwy_pub_project p on t.fk_project = p.pk_project_"+
					 " left join yjwy_quality_inspectstandard s on t.fk_standard = s.pk_inspstan";
		JdbcTemplate read = dao.getReadTemplate();
		//JSONObject taskDetails = read.queryForObject(sql, JSONObject.class);
		List<JSONObject> list = read.query(sql, new RowMapper<JSONObject>(){
			Map<String,String> specialtyMap = baseinfoQueryService.queryDictionaryForMap("qualitySpecialty", 1);
			@Override
			public JSONObject mapRow(ResultSet rs, int rowNum) throws SQLException {
				JSONObject json = new JSONObject();
				json.put("pk_task", rs.getString("pk_task"));
				json.put("task_statename", queryTaskStateByKey(rs.getString("task_state")));
				json.put("task_state", rs.getString("task_state"));
				json.put("task_generate_mode", querygenerateModeByKey(rs.getString("task_generate_mode")));
				json.put("task_pc_name", rs.getString("task_pc_name"));
				json.put("specialty", specialtyMap.get(rs.getString("specialty")));
				json.put("task_score", rs.getString("task_score"));
				json.put("task_start_time", rs.getString("task_start_time"));
				json.put("task_end_time", rs.getString("task_end_time"));
				json.put("task_deadline_date", rs.getString("task_deadline_date"));
				json.put("inspstan_category", rs.getString("inspstan_category"));
				json.put("inspstan_code", rs.getString("inspstan_code"));
				json.put("inspstan_inpectmethod", rs.getString("inspstan_inpectmethod"));
				json.put("inspstan_performance_norm", rs.getString("inspstan_performance_norm"));
				json.put("inspstan_category_description", rs.getString("inspstan_category_description"));
				json.put("project_name", rs.getString("project_name"));
				json.put("task_code", rs.getString("task_code"));
				json.put("check_state", rs.getString("check_state"));
				json.put("check_time", rs.getString("check_time"));
				json.put("check_content", rs.getString("check_content"));
				return json;
			}
		});
		ModelAndView mar = new ModelAndView(SONPAGE_FORWARD);
		mar.addObject("mainmodel", list.get(0));
		return mar;
	}
	
	/**
	 * 查询操作
	 * @return
	 */
	@RequestMapping(value="query", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult query(@RequestParam(value="param", required=false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		InspectTaskModel[] models = service.query(query);
		InspectTaskDao domainDao = SpringUtils.getBean(InspectTaskDao.ID);
		long count = domainDao.countListByCondition(query);
		ModelAndResult mar = new ModelAndResult(models);
		mar.setTotal(count);
		return mar;
	}
	
	/**
	 * 查询操作
	 * @return
	 */
	@RequestMapping(value="conditon/query", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult queryTaskForContion(@RequestBody TaskQueryVo queryvo) {
		YJWYUserModel user = UserUtil.getCurrentUser();
		String whrSql = " where pk_crop = '"+user.getPk_crop()+"' ";
		if(!StringUtils.isEmpty(queryvo.getArea_id())){
			whrSql = whrSql+" and fk_area = '"+queryvo.getArea_id()+"' ";
		}
		if(!StringUtils.isEmpty(queryvo.getProject_id())){
			whrSql = whrSql+" and fk_project = '"+queryvo.getProject_id()+"' ";
		}else{
			Set<String> projectIds = deviceService.queryProjectIdsByPkUser(user.getPk_user());
			String ids = "";
			for(String id : projectIds){
				ids = ids+",'"+id+"'";
			}
			//ids非空再substring(1)，不然报空指针
			if(!StringUtils.isEmpty(ids)){
				whrSql = whrSql+" and fk_project in ("+ids.substring(1)+")";
			}else{
				whrSql = whrSql+" and fk_project in ('')";
			}
			
			
		}
		if(!StringUtils.isEmpty(queryvo.getDept_id())){
			whrSql = whrSql+" and fk_dept = '"+queryvo.getDept_id()+"' ";
		}
		if(!StringUtils.isEmpty(queryvo.getStation_id())){
			whrSql = whrSql+" and fk_job = '"+queryvo.getStation_id()+"' ";
		}
		if(!StringUtils.isEmpty(queryvo.getUser_id())){
			whrSql = whrSql+" and fk_taskuser = '"+queryvo.getUser_id()+"' ";
		}
		if(!StringUtils.isEmpty(queryvo.getTaskstate())){
			whrSql = whrSql+" and task_state = '"+queryvo.getTaskstate()+"' ";
		}
		if(!StringUtils.isEmpty(queryvo.getRectify_code())){
			/*if("1".equals(queryvo.getRectify_code())){
				whrSql = whrSql+" and task_rectifyuser_pk is not null and task_rectifyuser_pk <> '' ";
			}else{
				whrSql = whrSql+" and task_rectifyuser_pk is null or task_rectifyuser_pk = '' ";
			}*/
			whrSql = whrSql+" and is_rectify = '"+queryvo.getRectify_code()+"' ";;
		}
		if(!StringUtils.isEmpty(queryvo.getCheck_code())){
			whrSql = whrSql+" and check_state = '"+queryvo.getCheck_code()+"' ";
		}
		if(!StringUtils.isEmpty(queryvo.getGenmode_code())){
			whrSql = whrSql+" and task_generate_mode = '"+queryvo.getGenmode_code()+"' ";
		}
		if(!StringUtils.isEmpty(queryvo.getSpecialty_code())){
			whrSql = whrSql+" and specialty = '"+queryvo.getSpecialty_code()+"' ";;
		}
		String stanWhr = "";
		if(!StringUtils.isEmpty(queryvo.getStancode())){
			stanWhr = " where s.inspstan_code like '%"+queryvo.getStancode()+"%' ";
		}
		if(!StringUtils.isEmpty(queryvo.getTaskcode())){
			whrSql = whrSql+" and task_code like '%"+queryvo.getTaskcode()+"%' ";
		}
		if(!StringUtils.isEmpty(queryvo.getTaskcq())){
			if("1".equals(queryvo.getTaskcq())){//超期
				whrSql = whrSql+" and DATE_FORMAT(IFNULL(task_end_time,date_format(now(),'%Y-%m-%d')),'%Y-%m-%d') > IFNULL(task_deadline_date,'3000-1-1') ";
			}else{//未超期
				whrSql = whrSql+" and DATE_FORMAT(IFNULL(task_end_time,date_format(now(),'%Y-%m-%d')),'%Y-%m-%d') <= IFNULL(task_deadline_date,'3000-1-1')";
			}
		}
		if(!StringUtils.isEmpty(queryvo.getStart_time1())){
			whrSql = whrSql+" and task_start_time>= '"+queryvo.getStart_time1()+"' ";
		}
		if(!StringUtils.isEmpty(queryvo.getStart_time2())){
			whrSql = whrSql+" and task_start_time<= '"+queryvo.getStart_time2()+"' ";
		}
		if(!StringUtils.isEmpty(queryvo.getEnd_time1())){
			//whrSql = whrSql+" and task_end_time>= '"+queryvo.getEnd_time1()+"' ";
			//上面是结束日期下面是截止日期
			whrSql = whrSql+" and task_deadline_date>= '"+queryvo.getEnd_time1()+"' ";
		}
		if(!StringUtils.isEmpty(queryvo.getEnd_time2())){
			//whrSql = whrSql+" and task_end_time<= '"+queryvo.getEnd_time2()+"' ";
			//上面是结束日期下面是截止日期
			whrSql = whrSql+" and task_deadline_date<= '"+queryvo.getEnd_time2()+"' ";
		}
		JdbcTemplate read = dao.getReadTemplate();
		String sql = " SELECT"+
			    " t.pk_task,t.task_code," +
				"t.task_start_time," +
				"t.task_end_time," +
				"t.task_deadline_date," +
				"t.task_rectify_starttime," +
				"t.task_rectify_finishtime,t.task_qualified_time,t.task_score,t.task_iscq,t.task_state,t.check_state,s.inspstan_code,t.specialty,s.inspstan_category,p.project_name_  'project_name',u.user_name_ 'task_user_name',u2.user_name_ 'rectify_user_name'"+
			" FROM ( select * from yjwy_quality_inspecttask "+whrSql+")t"+
			" LEFT JOIN yjwy_quality_inspectstandard s"+
			     " ON t.fk_standard = s.pk_inspstan"+
			" LEFT JOIN yjwy_pub_project p"+
			     " ON t.fk_project = p.pk_project_"+
			" LEFT JOIN yjwy_pub_user u"+
			     " ON t.fk_taskuser = u.pk_user_ "+
			" LEFT JOIN yjwy_pub_user u2"+
			    " ON t.task_rectifyuser_pk = u2.pk_user_ "+stanWhr+
			" order by t.create_time desc"+
			" limit "+queryvo.getStart()+","+queryvo.getLimit();
		String countSql = " SELECT"+
			    " count(t.pk_task)"+
			" FROM ( select * from yjwy_quality_inspecttask "+whrSql+")t"+
			" LEFT JOIN yjwy_quality_inspectstandard s"+
			     " ON t.fk_standard = s.pk_inspstan"+
			" LEFT JOIN yjwy_pub_project p"+
			     " ON t.fk_project = p.pk_project_"+
			" LEFT JOIN yjwy_pub_user u"+
			     " ON t.fk_taskuser = u.pk_user_ "+
			" LEFT JOIN yjwy_pub_user u2"+
			    " ON t.task_rectifyuser_pk = u2.pk_user_ "+stanWhr;
		int count = read.queryForObject(countSql, Integer.class);
		
		List<InspectTaskVo> list = read.query(sql, new RowMapper<InspectTaskVo>(){
			Map<String,String> specialtyMap = baseinfoQueryService.queryDictionaryForMap("qualitySpecialty", 1);
			@Override
			public InspectTaskVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				InspectTaskVo task = new InspectTaskVo();
				task.setPk_task(rs.getString("pk_task"));
				task.setInspstan_category(rs.getString("inspstan_category"));
				task.setInspstan_code(rs.getString("inspstan_code"));
				task.setProject_name(rs.getString("project_name"));
				task.setRectify_user_name(rs.getString("rectify_user_name"));
				task.setSpecialty(specialtyMap.get(rs.getString("specialty")));
				task.setTask_code(rs.getString("task_code"));
				task.setTask_deadline_date(rs.getString("task_deadline_date"));
				task.setTask_end_time(rs.getString("task_end_time"));
				String caFlag = "否";

				String end_time = task.getTask_end_time();
				if(end_time == null){
                    end_time  = DateTimeUtil.getDate(new Date(),DateTimeUtil.SHORTFORMAT);
                }
                //结束日期
                Date date_end = DateTimeUtil.formatDate(end_time, DateTimeUtil.SHORTFORMAT);

				//截止日期
                String task_deadline_date = task.getTask_deadline_date();
                if(task_deadline_date == null){
                    task_deadline_date = "3000-1-1";
                }

                Date date_dead = DateTimeUtil.formatDate(task_deadline_date,DateTimeUtil.SHORTFORMAT);
                if(date_end.compareTo(date_dead)>0){
                    task.setTask_iscq("是");
                }else {
                    task.setTask_iscq("否");
                }
				task.setTask_qualified_time(rs.getString("task_qualified_time"));
				task.setTask_rectify_finishtime(rs.getString("task_rectify_finishtime"));
				task.setTask_rectify_starttime(rs.getString("task_rectify_starttime"));
				task.setTask_score(rs.getString("task_score"));
				task.setTask_start_time(rs.getString("task_start_time"));
				task.setTask_state_name(queryTaskStateByKey(rs.getString("task_state")));
				task.setTask_state(rs.getString("task_state"));
				task.setTask_user_name(rs.getString("task_user_name"));
				task.setCheck_state(queryCheckStateByKey(rs.getString("check_state")));
				return task;
			}
			
		});
		InspectTaskVo[] tasks = list.toArray(new InspectTaskVo[]{});
		ModelAndResult mar = new ModelAndResult(tasks);
		mar.setTotal(count);
		return mar;
	}
	
	/**
	 * 新增保存操作
	 * @return
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody InspectTaskModel model) {
		InspectTaskModel[] rs = service.save(new InspectTaskModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody InspectTaskModel model) {
		InspectTaskModel[] rs = service.update(new InspectTaskModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 检阅操作
	 * @return
	 */
	@RequestMapping(value="check/update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult checkUpdate(@RequestParam String task_id,@RequestParam String check_state,@RequestParam(required=false) String check_content) {
		InspectTaskModel model = service.query(Query.from(InspectTaskModel.META_ID).and(Condition.create("pk_task", task_id)))[0];
		model.setCheck_state(check_state);
		model.setCheck_content(check_content);
		model.setCheck_time(DateTimeUtil.getTimestampStr());
		model.setCheck_user_pk(UserUtil.getCurrentUserPk());
		InspectTaskModel[] rs = service.update(new InspectTaskModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody InspectTaskModel[] models) {
		InspectTaskModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
	
	/**
	 * 关闭操作
	 * @return
	 */
	@RequestMapping(value="close", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult close(@RequestBody InspectTaskModel[] models) {
		String inSql = "";
		for(InspectTaskModel model : models){
			inSql = inSql+","+model.getPk_task(); 
		}
		Query query = Query.from(InspectTaskModel.META_ID);
		query.and(Condition.in("pk_task", inSql.substring(1).split(",")));
		InspectTaskModel[] tasks = service.query(query);
		for(InspectTaskModel model : tasks){
			if("10".equalsIgnoreCase(model.getTask_state()) || "20".equalsIgnoreCase(model.getTask_state())){
				model.setTask_state("60");
			}
		}
		service.update(tasks);
		return new ModelAndResult(true,"任务已关闭");
	}
	/***
	 * 核查任务导出操作
	 * @param req
	 * @param table
	 * @return 
	 * @return
	 */
	@RequestMapping(value="export",method=RequestMethod.GET)
	public @ResponseBody ModelAndResult doExpDataStructureByTable(@RequestParam(value="queryparam", required=false) String queryparam){
		TaskQueryVo queryvo = JSON.parseObject(queryparam, TaskQueryVo.class);
		String whrSql = " where pk_crop = '"+UserUtil.getCurrentUser().getPk_crop()+"' ";
		if(!StringUtils.isEmpty(queryvo.getArea_id())){
			whrSql = whrSql+" and fk_area = '"+queryvo.getArea_id()+"' ";
		}
		if(!StringUtils.isEmpty(queryvo.getProject_id())){
			whrSql = whrSql+" and fk_project = '"+queryvo.getProject_id()+"' ";
		}
		if(!StringUtils.isEmpty(queryvo.getDept_id())){
			whrSql = whrSql+" and fk_dept = '"+queryvo.getDept_id()+"' ";
		}
		if(!StringUtils.isEmpty(queryvo.getStation_id())){
			whrSql = whrSql+" and fk_job = '"+queryvo.getStation_id()+"' ";
		}
		if(!StringUtils.isEmpty(queryvo.getUser_id())){
			whrSql = whrSql+" and fk_taskuser = '"+queryvo.getUser_id()+"' ";
		}
		if(!StringUtils.isEmpty(queryvo.getTaskstate())){
			whrSql = whrSql+" and task_state = '"+queryvo.getTaskstate()+"' ";
		}
		if(!StringUtils.isEmpty(queryvo.getRectify_code())){
			/*if("1".equals(queryvo.getRectify_code())){
				whrSql = whrSql+" and task_rectifyuser_pk is not null and task_rectifyuser_pk <> '' ";
			}else{
				whrSql = whrSql+" and task_rectifyuser_pk is null or task_rectifyuser_pk = '' ";
			}*/
			whrSql = whrSql+" and is_rectify = '"+queryvo.getRectify_code()+"' ";;
		}
		if(!StringUtils.isEmpty(queryvo.getCheck_code())){
			whrSql = whrSql+" and check_state = '"+queryvo.getCheck_code()+"' ";
		}
		if(!StringUtils.isEmpty(queryvo.getGenmode_code())){
			whrSql = whrSql+" and task_generate_mode = '"+queryvo.getGenmode_code()+"' ";
		}
		if(!StringUtils.isEmpty(queryvo.getSpecialty_code())){
			whrSql = whrSql+" and specialty = '"+queryvo.getSpecialty_code()+"' ";;
		}
		String stanWhr = "";
		if(!StringUtils.isEmpty(queryvo.getStancode())){
			stanWhr = " where s.inspstan_code like '%"+queryvo.getStancode()+"%' ";
		}
		if(!StringUtils.isEmpty(queryvo.getTaskcode())){
			whrSql = whrSql+" and task_code like '%"+queryvo.getTaskcode()+"%' ";
		}
		if(!StringUtils.isEmpty(queryvo.getTaskcq())){
			if("1".equals(queryvo.getTaskcq())){
				whrSql = whrSql+" and task_end_time>task_deadline_date ";
			}else{
				whrSql = whrSql+" and task_end_time<task_deadline_date ";
			}
		}
		if(!StringUtils.isEmpty(queryvo.getStart_time1())){
			whrSql = whrSql+" and task_start_time>= '"+queryvo.getStart_time1()+"' ";
		}
		if(!StringUtils.isEmpty(queryvo.getStart_time2())){
			whrSql = whrSql+" and task_start_time<= '"+queryvo.getStart_time2()+"' ";
		}
		if(!StringUtils.isEmpty(queryvo.getEnd_time1())){
			whrSql = whrSql+" and task_end_time>= '"+queryvo.getEnd_time1()+"' ";
		}
		if(!StringUtils.isEmpty(queryvo.getEnd_time2())){
			whrSql = whrSql+" and task_end_time<= '"+queryvo.getEnd_time2()+"' ";
		}
		JdbcTemplate read = dao.getReadTemplate();
		String sql = " SELECT"+
			    " t.pk_task,t.task_code,t.task_start_time,t.task_end_time,t.task_rectify_starttime,t.task_rectify_finishtime,t.task_qualified_time,t.task_score,t.task_iscq,t.task_state,t.check_state,s.inspstan_code,t.specialty,s.inspstan_category,p.project_name_  'project_name',u.user_name_ 'task_user_name',u2.user_name_ 'rectify_user_name'"+
			" FROM ( select * from yjwy_quality_inspecttask "+whrSql+")t"+
			" LEFT JOIN yjwy_quality_inspectstandard s"+
			     " ON t.fk_standard = s.pk_inspstan"+
			" LEFT JOIN yjwy_pub_project p"+
			     " ON t.fk_project = p.pk_project_"+
			" LEFT JOIN yjwy_pub_user u"+
			     " ON t.fk_taskuser = u.pk_user_ "+
			" LEFT JOIN yjwy_pub_user u2"+
			    " ON t.task_rectifyuser_pk = u2.pk_user_ "+stanWhr+
			" order by t.create_time desc";
		
		List<InspectTaskVo> list = read.query(sql, new RowMapper<InspectTaskVo>(){
			Map<String,String> specialtyMap = baseinfoQueryService.queryDictionaryForMap("qualitySpecialty", 1);
			@Override
			public InspectTaskVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				InspectTaskVo task = new InspectTaskVo();
				task.setPk_task(rs.getString("pk_task"));
				task.setInspstan_category(querygenerateModeByKey(rs.getString("inspstan_category")));
				task.setInspstan_code(rs.getString("inspstan_code"));
				task.setProject_name(rs.getString("project_name"));
				task.setRectify_user_name(rs.getString("rectify_user_name"));
				task.setSpecialty(specialtyMap.get(rs.getString("specialty")));
				task.setTask_code(rs.getString("task_code"));
				task.setTask_end_time(rs.getString("task_end_time"));
				String caFlag = "否";
				if(!("0".equals(rs.getString("task_iscq")))){
					caFlag = "是";
				}
				task.setTask_iscq(caFlag);
				task.setTask_qualified_time(rs.getString("task_qualified_time"));
				task.setTask_rectify_finishtime(rs.getString("task_rectify_finishtime"));
				task.setTask_rectify_starttime(rs.getString("task_rectify_starttime"));
				task.setTask_score(rs.getString("task_score"));
				task.setTask_start_time(rs.getString("task_start_time"));
				task.setTask_state_name(queryTaskStateByKey(rs.getString("task_state")));
				task.setTask_state(rs.getString("task_state"));
				task.setTask_user_name(rs.getString("task_user_name"));
				return task;
			}
			
		});
		return ImpAndExpExcel.doExpExcel(list, new String[]{"task_state_name","task_code","inspstan_code","project_name","specialty","task_user_name","task_start_time","task_end_time"}, "templates/templates/quality/品质核查任务.xls", 2);
	}
	/**
	 * 查询子页面记录流程操作
	 * @param taskId
	 * @return
	 */
	@RequestMapping("process/task/query")
	public @ResponseBody ModelAndResult queryRecordByTasiId(@RequestParam(value="taskId") String taskId){
		String sql = " select"+
		  " r.pk_record,r.create_time 'sumimi_time',r.record_status,r.record_sample_desc,r.record_inspect_result,"+
		  " t.task_deadline_date 'finish_deadline_time',t.task_rectify_deadline_time 'rectify_deadline_time',"+
		  " u.user_name_ 'record_inspect_user',u2.user_name_ 'follow_user',"+
		  " p.problem_name"+
		  " from (select * from yjwy_quality_inspectrecord where fk_task ='"+taskId+"' ) r"+
		  " left join yjwy_quality_inspecttask t on r.fk_task = t.pk_task"+
		  " left join yjwy_pub_user u on r.create_user = u.pk_user_"+
		  " left join yjwy_pub_user u2 on r.follow_user_pk = u2.pk_user_"+
		  " left join yjwy_quality_problemtype p on r.fk_problem_id = p.pk_problem";
		JdbcTemplate read = dao.getReadTemplate();
		List<RecordProcessVo> list = read.query(sql, new RowMapper<RecordProcessVo>(){
			@Override
			public RecordProcessVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				RecordProcessVo vo = new RecordProcessVo();
				vo.setSumimi_time(rs.getString("sumimi_time"));
				vo.setRectify_deadline_time(rs.getString("rectify_deadline_time"));
				vo.setFinish_deadline_time(rs.getString("finish_deadline_time"));
				vo.setRecord_inspect_user(rs.getString("record_inspect_user"));
				vo.setRecord_status(rs.getString("record_status"));
				vo.setRecord_sample_desc(rs.getString("record_sample_desc"));
				vo.setRecord_inspect_result(rs.getString("record_inspect_result"));
				vo.setProblem_type(rs.getString("problem_name"));
				vo.setFollow_user(rs.getString("follow_user"));
				return vo;
			}			
		});
		if(null==list || list.size()<1){
			return new ModelAndResult(false,"该任务暂时没有流程记录。");
		}
		return new ModelAndResult(list);
	}
	
	//获取状态
	public String queryTaskStateByKey(String code){
		if("10".equals(code)){
			return "待办";
		}else if("20".equals(code)){
			return "待整改";
		}else if("30".equals(code)){
			return "已办";
		}else if("40".equals(code)){
			return "正常过期";
		}else if("50".equals(code)){
			return "休假关闭";
		}else if("60".equals(code)){
			return "手动关闭";
		}else{
			return "";
		}
	}
	//获取生成方式
	public String querygenerateModeByKey(String code){
		if("1".equals(code)){
			return "手机终端核查任务";
		}else if("0".equals(code)){
			return "达美盛资产云后台系统生成";
		}else{
			return "";
		}
	}
	//获取生成方式
	public String queryCheckStateByKey(String code){
		if("1".equals(code)){
			return "合格";
		}else if("0".equals(code)){
			return "不合格";
		}else{
			return "未审阅";
		}
	}
}
