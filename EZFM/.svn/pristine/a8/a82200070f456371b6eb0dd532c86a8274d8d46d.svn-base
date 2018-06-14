package com.shareworx.ezfm.quality.proinspect.inspect.insprecord.action;

import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.baseinfo.pub.service.IBaseInfoQueryService;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.device.util.service.YJWYDeviceService;
import com.shareworx.ezfm.pub.commons.PubCommonsForward;
import com.shareworx.ezfm.quality.proinspect.inspect.insprecord.dao.InspectRecordDao;
import com.shareworx.ezfm.quality.proinspect.inspect.insprecord.model.InspectRecordModel;
import com.shareworx.ezfm.quality.proinspect.inspect.insprecord.service.InspectRecordBusinessService;
import com.shareworx.ezfm.quality.proinspect.inspect.insprecord.vo.InspectRecordVo;
import com.shareworx.ezfm.quality.proinspect.inspect.insprecord.vo.RecordProcessVo;
import com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskModel;
import com.shareworx.ezfm.quality.proinspect.inspect.insptask.service.InspectTaskDomainService;
import com.shareworx.ezfm.quality.proinspect.inspect.insptask.vo.TaskQueryVo;
import com.shareworx.ezfm.utils.ImpAndExpExcel;
import com.shareworx.ezfm.utils.UserUtil;

/**
 * 核查与整改记录操作控制器
 * 
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/quality/proinspect/inspect/insprecord")
public class InspectRecordAction {
	final static Logger log = Logger.getLogger(InspectRecordAction.class);

	/** 跳转页面 */
	public final static String FINISH_PAGE_FORWARD = "ezfm/quality/proinspect/inspect/record/record_list";
	/** 跳转子页面 */
	public final static String SONPAGE_FORWARD = "ezfm/quality/proinspect/inspect/record/record_sonpage";

	@Autowired
	@Qualifier(InspectRecordBusinessService.ID)
	private InspectRecordBusinessService service;

	public void setService(InspectRecordBusinessService service) {
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
	private YJWYDeviceService deviceService;

	public void setDeviceService(YJWYDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	@Autowired
	@Qualifier(InspectTaskDomainService.ID)
	private InspectTaskDomainService taskDomainService;

	public void setTaskDomainService(InspectTaskDomainService taskDomainService) {
		this.taskDomainService = taskDomainService;
	}

	/**
	 * 转向列表页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "index/{state}", method = RequestMethod.GET)
	public ModelAndView listForward(@PathVariable(value = "state") String state) {
		ModelAndView mav;
		if ("finish".equals(state)) {
			mav = new ModelAndView(FINISH_PAGE_FORWARD);
			mav.addObject("rectify_code", "0");
		} else {
			mav = new ModelAndView(FINISH_PAGE_FORWARD);
			mav.addObject("rectify_code", "1");
		}
		return mav;
	}

	/**
	 * 转向子页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "son/index/{taskId}/{recordId}", method = RequestMethod.GET)
	public ModelAndView sonListForward(@PathVariable(value = "taskId") String taskId, @PathVariable(value = "recordId") String recordId) {
		String sql = " select " + " t.pk_task,t.task_code,t.task_state,t.task_generate_mode,t.task_pc_name,t.specialty,t.task_score,t.task_start_time,t.task_end_time,t.task_deadline_date,t.check_state,t.check_time,t.check_content," + " p. project_name_ 'project_name'," + " s.inspstan_category,s.inspstan_code,s.inspstan_category_description,s.inspstan_inpectmethod,s.inspstan_performance_norm" + " from (select * from yjwy_quality_inspecttask where pk_task='" + taskId + "')t" + " left join yjwy_pub_project p on t.fk_project = p.pk_project_" + " left join yjwy_quality_inspectstandard s on t.fk_standard = s.pk_inspstan";
		JdbcTemplate read = dao.getReadTemplate();
		// JSONObject taskDetails = read.queryForObject(sql, JSONObject.class);
		List<JSONObject> list = read.query(sql, new RowMapper<JSONObject>() {
			Map<String, String> specialtyMap = baseinfoQueryService.queryDictionaryForMap("qualitySpecialty", 1);

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
		list.get(0).put("pk_record", recordId);
		ModelAndView mar = new ModelAndView(SONPAGE_FORWARD);
		mar.addObject("mainmodel", list.get(0));
		return mar;
	}

	/**
	 * 转向地图页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "map/index/{taskId}", method = RequestMethod.GET)
	public ModelAndView sonListForward(@PathVariable(value = "taskId") String taskId) {
		InspectTaskModel model = taskDomainService.queryById(taskId);
		String lon = model.getRecord_finish_lon();
		String lat = model.getRecord_finish_lat();
		ModelAndView mar = new ModelAndView(PubCommonsForward.BD_POINT_MAP_FORWARD);
		mar.addObject("lon", lon);
		mar.addObject("lat", lat);
		return mar;
	}

	/**
	 * 查询操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult query(@RequestParam(value = "param", required = false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		InspectRecordModel[] models = service.query(query);
		InspectRecordDao domainDao = SpringUtils.getBean(InspectRecordDao.ID);
		long count = domainDao.countListByCondition(query);
		ModelAndResult mar = new ModelAndResult(models);
		mar.setTotal(count);
		return mar;
	}

	/**
	 * 查询操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "conditon/query", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult queryTaskForContion(@RequestBody TaskQueryVo queryvo) {
		String whrSql = " where t.pk_crop = '" + UserUtil.getCurrentUser().getPk_crop() + "' ";
		if (!StringUtils.isEmpty(queryvo.getArea_id())) {
			whrSql = whrSql + " and t.fk_area = '" + queryvo.getArea_id() + "' ";
		}
		if (!StringUtils.isEmpty(queryvo.getProject_id())) {
			whrSql = whrSql + " and t.fk_project = '" + queryvo.getProject_id() + "' ";
		}
		if (StringUtils.isEmpty(queryvo.getArea_id()) && StringUtils.isEmpty(queryvo.getProject_id())) {
			// 默认查询当前人所属项目所有数据
			Set<String> projectIds = deviceService.queryProjectIdsByPkUser(UserUtil.getCurrentUserPk());
			if (projectIds.size() != 0) {
				whrSql = whrSql + " AND " + DeviceUtil.getInNotInSql("t.fk_project", QueryContents.TYPE_IN, projectIds.toArray(new String[] {}));
			} else {
				whrSql = whrSql + " AND 1=2 ";
			}
		}
		if (!StringUtils.isEmpty(queryvo.getDept_id())) {
			whrSql = whrSql + " and t.fk_dept = '" + queryvo.getDept_id() + "' ";
		}
		if (!StringUtils.isEmpty(queryvo.getStation_id())) {
			whrSql = whrSql + " and t.fk_job = '" + queryvo.getStation_id() + "' ";
		}
		if (!StringUtils.isEmpty(queryvo.getUser_id())) {
			whrSql = whrSql + " and r.create_user = '" + queryvo.getUser_id() + "' ";
		}
		if (!StringUtils.isEmpty(queryvo.getTaskstate())) {
			whrSql = whrSql + " and t.task_state = '" + queryvo.getTaskstate() + "' ";
		}
		if (!StringUtils.isEmpty(queryvo.getRectify_code())) {
			/*
			 * if("1".equals(queryvo.getRectify_code())){ whrSql = whrSql+
			 * " and t.task_rectifyuser_pk is not null and task_rectifyuser_pk <> '' "
			 * ; }else{ whrSql = whrSql+
			 * " and t.task_rectifyuser_pk is null or task_rectifyuser_pk = '' "
			 * ; }
			 */
			whrSql = whrSql + " and t.is_rectify = '" + queryvo.getRectify_code() + "' ";
			;
		}
		if (!StringUtils.isEmpty(queryvo.getSpecialty_code())) {
			whrSql = whrSql + " and t.specialty = '" + queryvo.getSpecialty_code() + "' ";
			;
		}
		if (!StringUtils.isEmpty(queryvo.getCheck_code())) {
			whrSql = whrSql + " and t.check_state = '" + queryvo.getCheck_code() + "' ";
		}
		if (!StringUtils.isEmpty(queryvo.getGenmode_code())) {
			whrSql = whrSql + " and t.task_generate_mode = '" + queryvo.getGenmode_code() + "' ";
		}
		if (!StringUtils.isEmpty(queryvo.getStancode())) {
			whrSql = whrSql + " and s.inspstan_code like '%" + queryvo.getStancode() + "%' ";
		}
		if (!StringUtils.isEmpty(queryvo.getTaskcode())) {
			whrSql = whrSql + " and t.task_code like '%" + queryvo.getTaskcode() + "%' ";
		}
		if (!StringUtils.isEmpty(queryvo.getTaskcq())) {
			if ("1".equals(queryvo.getTaskcq())) {
				whrSql = whrSql + " and t.task_end_time>t.task_deadline_date ";
			} else {
				whrSql = whrSql + " and t.task_end_time<t.task_deadline_date ";
			}
			// 这一步也可以走任务状态 正常超期
		}
		if (!StringUtils.isEmpty(queryvo.getStart_time1())) {
			whrSql = whrSql + " and t.task_start_time>= '" + queryvo.getStart_time1() + "' ";
		}
		if (!StringUtils.isEmpty(queryvo.getStart_time2())) {
			whrSql = whrSql + " and t.task_start_time<= '" + queryvo.getStart_time2() + "' ";
		}
		if (!StringUtils.isEmpty(queryvo.getEnd_time1())) {
			whrSql = whrSql + " and t.task_end_time>= '" + queryvo.getEnd_time1() + "' ";
		}
		if (!StringUtils.isEmpty(queryvo.getEnd_time2())) {
			whrSql = whrSql + " and t.task_end_time<= '" + queryvo.getEnd_time2() + "' ";
		}
		JdbcTemplate read = dao.getReadTemplate();

		String sql = " select" + " r.pk_record,r.fk_task,r.record_inspect_result,r.create_time,r.record_subtract_score,r.record_status," + " t.task_code,t.record_finish_lat,t.record_finish_lon,t.task_execute_site," + " s.inspstan_code," + " p.project_name_ 'project_name'," + " u.user_name_ 'inspect_user_name'," + " u2.user_name_ 'fllow_user_name'," + " u3.user_name_ 'rectify_user_name'," + " b.problem_name" + " from yjwy_quality_inspectrecord r" + " LEFT JOIN yjwy_quality_inspecttask t" + "   ON r.fk_task = t.pk_task" + " LEFT JOIN yjwy_quality_inspectstandard s" + "   ON s.pk_inspstan = t.fk_standard" + " LEFT JOIN yjwy_pub_project p" + "   ON p.pk_project_ = t.fk_project" + " LEFT JOIN yjwy_pub_user u" + "   ON r.create_user = u.pk_user_" + " LEFT JOIN yjwy_pub_user u2"
				+ "   ON r.create_user = u2.pk_user_" + " LEFT JOIN yjwy_pub_user u3" + "   ON t.task_rectifyuser_pk = u3.pk_user_" + " LEFT JOIN  yjwy_quality_problemtype b" + "   ON r.fk_problem_id = b.pk_problem" + whrSql + " order by create_time desc" + " limit " + queryvo.getStart() + "," + queryvo.getLimit();

		String countSql = " select" + " count(r.pk_record) " + " from yjwy_quality_inspectrecord r" + " LEFT JOIN yjwy_quality_inspecttask t" + "   ON r.fk_task = t.pk_task" + " LEFT JOIN yjwy_quality_inspectstandard s" + "   ON s.pk_inspstan = t.fk_standard" + " LEFT JOIN yjwy_pub_project p" + "   ON p.pk_project_ = t.fk_project" + " LEFT JOIN yjwy_pub_user u" + "   ON t.fk_taskuser = u.pk_user_" + " LEFT JOIN yjwy_pub_user u2" + "   ON r.follow_user_pk = u2.pk_user_" + " LEFT JOIN  yjwy_quality_problemtype b" + "   ON r.fk_problem_id = b.pk_problem" + whrSql;
		int count = read.queryForObject(countSql, Integer.class);

		List<InspectRecordVo> list = read.query(sql, new RowMapper<InspectRecordVo>() {
			@Override
			public InspectRecordVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				InspectRecordVo record = new InspectRecordVo();
				record.setPk_record(rs.getString("pk_record"));
				record.setFk_task(rs.getString("fk_task"));
				record.setRecord_status(queryProcessStateByKey(rs.getString("record_status")));
				record.setTask_code(rs.getString("task_code"));
				record.setInspstan_code(rs.getString("inspstan_code"));
				record.setProject_name(rs.getString("project_name"));
				record.setInspect_user_name(rs.getString("inspect_user_name"));
				record.setRecord_inspect_result(rs.getString("record_inspect_result"));
				record.setProblem_name(rs.getString("problem_name"));
				record.setFllow_user_name(rs.getString("fllow_user_name"));
				record.setRecord_subtract_score(rs.getString("record_subtract_score"));
				record.setRecord_finish_lon(rs.getString("record_finish_lon"));
				record.setRecord_finish_lat(rs.getString("record_finish_lat"));
				record.setCreate_time(rs.getString("create_time"));
				record.setTask_execute_site(rs.getString("task_execute_site"));
				record.setRectify_user_name(rs.getString("rectify_user_name"));
				// String checkState = "";
				// record.setRecord_check_state(checkState);
				return record;
			}

		});
		InspectRecordVo[] records = list.toArray(new InspectRecordVo[] {});
		ModelAndResult mar = new ModelAndResult(records);
		mar.setTotal(count);
		return mar;
	}

	/**
	 * 查询子页面记录流程操作
	 * 
	 * @param taskId
	 * @return
	 */
	@RequestMapping("process/query")
	public @ResponseBody ModelAndResult queryRecordByTasiId(@RequestParam(value = "recordId") String recordId) {
		String sql = " select" + " r.pk_record,r.create_time 'sumimi_time',r.record_status,r.record_sample_desc,r.record_inspect_result," + " t.task_deadline_date 'finish_deadline_time',t.task_rectify_deadline_time 'rectify_deadline_time'," + " u.user_name_ 'record_inspect_user',u2.user_name_ 'follow_user'," + " p.problem_name" + " from (select * from yjwy_quality_inspectrecord where pk_record ='" + recordId + "' ) r" + " left join yjwy_quality_inspecttask t on r.fk_task = t.pk_task" + " left join yjwy_pub_user u on r.create_user = u.pk_user_" + " left join yjwy_pub_user u2 on r.follow_user_pk = u2.pk_user_" + " left join yjwy_quality_problemtype p on r.fk_problem_id = p.pk_problem";
		JdbcTemplate read = dao.getReadTemplate();
		List<RecordProcessVo> list = read.query(sql, new RowMapper<RecordProcessVo>() {
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
		if (null == list || list.size() < 1) {
			return new ModelAndResult(false, "暂无信息显示");
		}
		return new ModelAndResult(new RecordProcessVo[] { list.get(0) });
	}

	/**
	 * 新增保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody InspectRecordModel model) {
		InspectRecordModel[] rs = service.save(new InspectRecordModel[] { model });
		return new ModelAndResult(rs);
	}

	/**
	 * 修改保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody InspectRecordModel model) {
		InspectRecordModel[] rs = service.update(new InspectRecordModel[] { model });
		return new ModelAndResult(rs);
	}

	/**
	 * 删除操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody InspectRecordModel[] models) {
		InspectRecordModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}

	/***
	 * 核查完成记录导出操作
	 * 
	 * @param req
	 * @param table
	 * @return
	 * @return
	 */
	@RequestMapping(value = "export", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult doExpDataStructureByTable(@RequestParam(value = "queryparam", required = false) String queryparam) {
		TaskQueryVo queryvo = JSON.parseObject(queryparam, TaskQueryVo.class);
		String whrSql = " where t.pk_crop = '" + UserUtil.getCurrentUser().getPk_crop() + "' ";
		if (!StringUtils.isEmpty(queryvo.getArea_id())) {
			whrSql = whrSql + " and t.fk_area = '" + queryvo.getArea_id() + "' ";
		}
		if (!StringUtils.isEmpty(queryvo.getProject_id())) {
			whrSql = whrSql + " and t.fk_project = '" + queryvo.getProject_id() + "' ";
		}
		if (!StringUtils.isEmpty(queryvo.getDept_id())) {
			whrSql = whrSql + " and t.fk_dept = '" + queryvo.getDept_id() + "' ";
		}
		if (!StringUtils.isEmpty(queryvo.getStation_id())) {
			whrSql = whrSql + " and t.fk_job = '" + queryvo.getStation_id() + "' ";
		}
		if (!StringUtils.isEmpty(queryvo.getUser_id())) {
			whrSql = whrSql + " and r.create_user = '" + queryvo.getUser_id() + "' ";
		}
		if (!StringUtils.isEmpty(queryvo.getTaskstate())) {
			whrSql = whrSql + " and t.task_state = '" + queryvo.getTaskstate() + "' ";
		}
		if (!StringUtils.isEmpty(queryvo.getRectify_code())) {
			/*
			 * if("1".equals(queryvo.getRectify_code())){ whrSql = whrSql+
			 * " and t.task_rectifyuser_pk is not null and task_rectifyuser_pk <> '' "
			 * ; }else{ whrSql = whrSql+
			 * " and t.task_rectifyuser_pk is null or task_rectifyuser_pk = '' "
			 * ; }
			 */
			whrSql = whrSql + " and t.is_rectify = '" + queryvo.getRectify_code() + "' ";
			;
		}
		if (!StringUtils.isEmpty(queryvo.getSpecialty_code())) {
			whrSql = whrSql + " and t.specialty = '" + queryvo.getSpecialty_code() + "' ";
			;
		}
		if (!StringUtils.isEmpty(queryvo.getCheck_code())) {
			whrSql = whrSql + " and t.check_state = '" + queryvo.getCheck_code() + "' ";
		}
		if (!StringUtils.isEmpty(queryvo.getGenmode_code())) {
			whrSql = whrSql + " and t.task_generate_mode = '" + queryvo.getGenmode_code() + "' ";
		}
		if (!StringUtils.isEmpty(queryvo.getStancode())) {
			whrSql = whrSql + " and s.inspstan_code like '%" + queryvo.getStancode() + "%' ";
		}
		if (!StringUtils.isEmpty(queryvo.getTaskcode())) {
			whrSql = whrSql + " and t.task_code like '%" + queryvo.getTaskcode() + "%' ";
		}
		if (!StringUtils.isEmpty(queryvo.getTaskcq())) {
			if ("1".equals(queryvo.getTaskcq())) {
				whrSql = whrSql + " and t.task_end_time>t.task_deadline_date ";
			} else {
				whrSql = whrSql + " and t.task_end_time<t.task_deadline_date ";
			}
			// 这一步也可以走任务状态 正常超期
		}
		if (!StringUtils.isEmpty(queryvo.getStart_time1())) {
			whrSql = whrSql + " and t.task_start_time>= '" + queryvo.getStart_time1() + "' ";
		}
		if (!StringUtils.isEmpty(queryvo.getStart_time2())) {
			whrSql = whrSql + " and t.task_start_time<= '" + queryvo.getStart_time2() + "' ";
		}
		if (!StringUtils.isEmpty(queryvo.getEnd_time1())) {
			whrSql = whrSql + " and t.task_end_time>= '" + queryvo.getEnd_time1() + "' ";
		}
		if (!StringUtils.isEmpty(queryvo.getEnd_time2())) {
			whrSql = whrSql + " and t.task_end_time<= '" + queryvo.getEnd_time2() + "' ";
		}

		String sql = " select" + " r.pk_record,r.fk_task,r.record_inspect_result,r.create_time,r.record_subtract_score,r.record_status," + " t.task_code,t.record_finish_lat,t.record_finish_lon,t.task_execute_site," + " s.inspstan_code," + " p.project_name_ 'project_name'," + " u.user_name_ 'inspect_user_name'," + " u2.user_name_ 'fllow_user_name'," + " u3.user_name_ 'rectify_user_name'," + " b.problem_name" + " from yjwy_quality_inspectrecord r" + " LEFT JOIN yjwy_quality_inspecttask t" + "   ON r.fk_task = t.pk_task" + " LEFT JOIN yjwy_quality_inspectstandard s" + "   ON s.pk_inspstan = t.fk_standard" + " LEFT JOIN yjwy_pub_project p" + "   ON p.pk_project_ = t.fk_project" + " LEFT JOIN yjwy_pub_user u" + "   ON r.create_user = u.pk_user_" + " LEFT JOIN yjwy_pub_user u2"
				+ "   ON r.create_user = u2.pk_user_" + " LEFT JOIN yjwy_pub_user u3" + "   ON t.task_rectifyuser_pk = u3.pk_user_" + " LEFT JOIN  yjwy_quality_problemtype b" + "   ON r.fk_problem_id = b.pk_problem" + whrSql + " order by create_time desc";
		JdbcTemplate read = dao.getReadTemplate();
		List<InspectRecordVo> list = read.query(sql, new RowMapper<InspectRecordVo>() {
			@Override
			public InspectRecordVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				InspectRecordVo record = new InspectRecordVo();
				record.setPk_record(rs.getString("pk_record"));
				record.setRecord_status(queryProcessStateByKey(rs.getString("record_status")));
				record.setTask_code(rs.getString("task_code"));
				record.setInspstan_code(rs.getString("inspstan_code"));
				record.setProject_name(rs.getString("project_name"));
				record.setInspect_user_name(rs.getString("inspect_user_name"));
				record.setRecord_inspect_result(rs.getString("record_inspect_result"));
				record.setProblem_name(rs.getString("problem_name"));
				record.setFllow_user_name(rs.getString("fllow_user_name"));
				record.setRecord_subtract_score(rs.getString("record_subtract_score"));
				record.setRecord_finish_lon(rs.getString("record_finish_lon"));
				record.setRecord_finish_lat(rs.getString("record_finish_lat"));
				record.setCreate_time(rs.getString("create_time"));
				String checkState = "";
				// 报错 导出的excel没有用到 被lilingwei 注释
				/*
				 * if("0".equals(rs.getString("record_check_state"))){
				 * checkState="不合格"; }else
				 * if("1".equals(rs.getString("record_check_state"))){
				 * checkState="合格"; }else{ checkState="未审阅"; }
				 */
				record.setRecord_check_state(checkState);
				return record;
			}

		});
		return ImpAndExpExcel.doExpExcel(list, new String[] { "record_status", "task_code", "inspstan_code", "project_name", "inspect_user_name", "record_inspect_result", "create_time" }, "templates/templates/quality/品质核查完成记录.xls", 2);
	}

	public String queryProcessStateByKey(String code) {
		// 记录状态(0完成，1整改，2整改完成，3整改确认)
		if ("0".equals(code)) {
			return "完成";
		} else if ("1".equals(code)) {
			return "整改";
		} else if ("2".equals(code)) {
			return "整改完成";
		} else if ("3".equals(code)) {
			return "整改确认";
		} else {
			return "";
		}
	}

	// 获取状态
	public String queryTaskStateByKey(String code) {
		if ("10".equals(code)) {
			return "待办";
		} else if ("20".equals(code)) {
			return "待整改";
		} else if ("30".equals(code)) {
			return "已办";
		} else if ("40".equals(code)) {
			return "正常过期";
		} else if ("50".equals(code)) {
			return "休假关闭";
		} else if ("60".equals(code)) {
			return "手动关闭";
		} else {
			return "";
		}
	}

	// 获取生成方式
	public String querygenerateModeByKey(String code) {
		if ("1".equals(code)) {
			return "手机终端核查任务";
		} else if ("0".equals(code)) {
			return "达美盛资产云后台系统生成";
		} else {
			return "";
		}
	}

	// 获取生成方式
	public String queryCheckStateByKey(String code) {
		if ("1".equals(code)) {
			return "合格";
		} else if ("0".equals(code)) {
			return "不合格";
		} else {
			return "未审阅";
		}
	}
}
