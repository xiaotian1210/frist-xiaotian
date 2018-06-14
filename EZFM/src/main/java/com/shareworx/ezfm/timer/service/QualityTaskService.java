package com.shareworx.ezfm.timer.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.persist.service.BaseDomainService;
import com.shareworx.platform.util.DateTimeUtil;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.baseinfo.org.model.DefaultOrgModel;
import com.shareworx.ezfm.baseinfo.org.service.DefaultOrgBusinessService;
import com.shareworx.ezfm.baseinfo.pub.service.IBaseInfoQueryService;
import com.shareworx.ezfm.baseinfo.station.model.YJWYStationModel;
import com.shareworx.ezfm.baseinfo.station.service.YJWYStationBusinessService;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserRelationService;
import com.shareworx.ezfm.performance.leave.service.YJWYLeaveService;
import com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskModel;
import com.shareworx.ezfm.quality.proinspect.inspect.insptask.service.InspectTaskDomainService;
import com.shareworx.ezfm.quality.proinspect.inspect.standard.model.InspectStandardModel;
import com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardStationModel;
import com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardUserModel;
import com.shareworx.ezfm.quality.proinspect.inspect.standard.service.InspectStandardBusinessService;
import com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardStationBusinessService;
import com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardUserDomainService;

@Service(QualityTaskService.ID)
public class QualityTaskService {
	
	public static void main(String[] args) {
		long l = 1477141134187l;
		System.out.println(new Date(l).toLocaleString());
	}
	
	public static final String ID = "qualityTask";

	@Autowired
	@Qualifier(StandardStationBusinessService.ID)
	private StandardStationBusinessService service;

	public void setService(StandardStationBusinessService service) {
		this.service = service;
	}
	
	@Autowired
	@Qualifier(StandardUserDomainService.ID)
	private StandardUserDomainService suDomainService;
	
	public void setStandardUserDomainService(StandardUserDomainService suDomainService){
		this.suDomainService =suDomainService;
	}
	
	@Autowired
	@Qualifier(InspectStandardBusinessService.ID)
	private InspectStandardBusinessService standardService;

	public void setStandardService(InspectStandardBusinessService standardService) {
		this.standardService = standardService;
	}

	@Autowired
	@Qualifier(InspectTaskDomainService.ID)
	private InspectTaskDomainService taskService;

	public void setService(InspectTaskDomainService taskService) {
		this.taskService = taskService;
	}

	@Autowired
	@Qualifier(YJWYStationBusinessService.ID)
	private YJWYStationBusinessService stationService;

	public void setStationService(YJWYStationBusinessService stationService) {
		this.stationService = stationService;
	}

	@Autowired
	@Qualifier(DefaultOrgBusinessService.ID)
	private DefaultOrgBusinessService orgService;

	public void setOrgService(DefaultOrgBusinessService orgService) {
		this.orgService = orgService;
	}

	@Autowired
	@Qualifier(YJWYUserRelationService.ID)
	private YJWYUserRelationService urelationService;

	public void setOrgService(YJWYUserRelationService urelationService) {
		this.urelationService = urelationService;
	}

	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService baseService;

	public void setBaseService(BaseDomainService baseService) {
		this.baseService = baseService;
	}

	@Autowired
	@Qualifier(IBaseInfoQueryService.ID)
	IBaseInfoQueryService baseinfoQueryService;

	public void setBaseinfoQueryService(IBaseInfoQueryService baseinfoQueryService) {
		this.baseinfoQueryService = baseinfoQueryService;
	}

	@Autowired
	@Qualifier(YJWYLeaveService.ID)
	YJWYLeaveService leaveService;

	public void setLeaveService(YJWYLeaveService leaveService) {
		this.leaveService = leaveService;
	}

	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;

	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	
	@Autowired
	@Qualifier("transactionManager")
    private DataSourceTransactionManager txManager;
	
	public void setTxManager(DataSourceTransactionManager txManager) {
		this.txManager = txManager;
	}
	/**
	 * 执行定时器更新核查任务
	 */
	public void executeTimerUpdateTask() {
		Query query = Query.from(InspectTaskModel.META_ID);
		query.and(Condition.create("task_state", "10"));
		query.and(new Condition("task_deadline_date", QueryContents.TYPE_LT, DateTimeUtil.getDate()));
		List<InspectTaskModel> tasks = taskService.queryListByCondition(query);
		for (InspectTaskModel task : tasks) {
			task.setTask_state("40");
			task.setUpdate_time(System.currentTimeMillis() + "");
		}
		if (tasks.size() > 0) {
			taskService.update(tasks.toArray(new InspectTaskModel[] {}));
		}
	}
	/**
	 * 执行定时器生成核查任务
	 */
	public void executeTimerGeneratorTask() {
		StandardStationModel[] ssms = service.query(Query.from(StandardStationModel.META_ID));
		int len = 0;
		for (StandardStationModel ssm : ssms) {
			len++;
			System.out.println("=======" + len + "=======");
			YJWYStationModel station = baseService.queryById(YJWYStationModel.META_ID, ssm.getPk_station());
			InspectStandardModel standard = baseService.queryById(InspectStandardModel.META_ID, ssm.getPk_standard());
			String pcCode = getPcCode(station, standard);
			//如果生成频次为NULL 或者unSet(不固定) 侧不生成任务
			if ("unSet".equals(pcCode) || StringUtils.isEmpty(pcCode)) {
				continue;
			}
			//生成任务
			taskGenerator(station, standard, pcCode);
		}
	}


	/**
	 * 任务生成器
	 * @param station
	 * @param standard
	 * @param pcCode
	 * @return
	 */
	@Transactional
	public void taskGenerator(YJWYStationModel station, InspectStandardModel standard, String pcCode) {
		//查询该岗位下的人员
		List<YJWYUserModel> list = urelationService.queryUserByStation(station.getPk_station());
		//岗位下没人不生成任务
		if (null == list || list.size() < 1 || null==standard) {
			return;
		}
		
		//获取岗位所属组织信息
		Query query = Query.from(DefaultOrgModel.META_ID);
		query.and(Condition.create("pk_org_", station.getPk_org()));
		DefaultOrgModel org = orgService.query(query)[0];
		
		//获取频次信息 MAP
		Map<String, String> pcMap = baseinfoQueryService.queryDictionaryForMap("taskRate", 1);
		
		//标准和用户 插入对象
		List<StandardUserModel> addSuList = new ArrayList<>();
		//标准和用户 更新对象
		List<StandardUserModel> updateSuList = new ArrayList<>();
		//任务对象
		List<InspectTaskModel> tasks = new ArrayList<InspectTaskModel>();
		for (YJWYUserModel user : list) {
			if (leaveService.isLeaveByUserId(user.getPk_user())) {
				// 休假人员不生成任务，此方法相当影响程序运行，后期先从自身着手优化，再考虑与客户协调优化
				continue;
			}
			
			Date deadLine = null;
			//获取标准与用户的关联记录
			Query suQuery = Query.from(StandardUserModel.META_ID).and(Condition.create("pk_crop",station.getPk_crop())).and(Condition.create("pk_standard",standard.getPk_inspstan())).and(Condition.create("pk_user", user.getPk_user())).and(Condition.create("pk_station",station.getPk_station()));
			StandardUserModel suModel = suDomainService.queryOneByCondition(suQuery);
			//如果对象存在完成期限将期限赋值
			if(null!=suModel && !StringUtils.isEmpty(suModel.getLast_deadline_date())){
				deadLine = DateTimeUtil.formatDate(suModel.getLast_deadline_date());
			}
			
			//生成标准用户表的新完成时间
			Date newdeadLine = getDeadlineDate(DateTimeUtil.formatDate(DateTimeUtil.getDate()), pcCode);

			//如果用户未生成过  或者 当前日期已经大于完成期限 再次生成任务
			if(null==suModel || isGenerate(deadLine)){
				InspectTaskModel task = new InspectTaskModel();
				task.setFk_standard(standard.getPk_inspstan());
				task.setFk_project(org.getOrg_project());
				task.setFk_job(station.getPk_station());
				task.setFk_area(org.getOrg_area());
				task.setFk_dept(station.getPk_dept());
				task.setFk_taskuser(user.getPk_user());
				task.setTask_currentuser_pk(user.getPk_user());
				// task.settask_rectifyuser_pk();
				task.setTask_start_time(DateTimeUtil.getDate());
				// task.settask_end_time();
				task.setTask_generate_mode("0");
				task.setTask_score(standard.getInspstan_scorevalue());
				// task.setTask_subtract_score();
				task.setTask_deadline_date(DateTimeUtil.getDate(newdeadLine, "YYYY-MM-dd"));
				task.setCheck_state("2");
				task.setTask_type(1);
				// task.setTask_inspectresult();
				task.setTask_code(taskCodeGenerator());
				task.setSpecialty(standard.getSpecialty());
				// task.setTask_rectify_starttime(task_rectify_starttime);
				// task.setTask_rectify_finishtime(task_rectify_finishtime);
				// task.setTask_qualified_time(task_qualified_time);
				task.setTask_iscq("0");
				task.setTask_state("10");
				// task.setCreate_user(create_user);
				task.setCreate_time(DateTimeUtil.getTimestampStr());
				// task.setUpdate_user(update_user);
				task.setUpdate_time(System.currentTimeMillis() + "");
				task.setIs_valid("1");
				task.setTask_pc_name(pcMap.get(pcCode));
				// task.setRecord_finish_lat(record_finish_lat);
				// task.setRecord_finish_lon(record_finish_lon);
				// task.setTask_execute_site(task_execute_site);
				// task.setTask_rectify_deadline_time(task_rectify_deadline_time);
				task.setPk_crop(station.getPk_crop());
				// task.setis_sign
				// task.setCheck_content(check_content);
				// task.setCheck_user_pk(check_user_pk);
				// task.setCheck_time(check_time);
				task.setIs_rectify("0");
				tasks.add(task);
				if(null == suModel){
					StandardUserModel suNewModel = new StandardUserModel();
					suNewModel.setPk_crop(station.getPk_crop());
					suNewModel.setPk_station(station.getPk_station());
					suNewModel.setPk_standard(standard.getPk_inspstan());
					suNewModel.setPk_user(user.getPk_user());
					suNewModel.setLast_generate_date(DateTimeUtil.getDate());
					suNewModel.setLast_deadline_date(DateTimeUtil.getDate(newdeadLine, "YYYY-MM-dd"));
					suNewModel.setCreate_time(System.currentTimeMillis() + "");
					suNewModel.setUpdate_time(System.currentTimeMillis() + "");
					addSuList.add(suNewModel);
				}else{
					suModel.setLast_generate_date(DateTimeUtil.getDate());
					suModel.setLast_deadline_date(DateTimeUtil.getDate(newdeadLine, "YYYY-MM-dd"));
					suModel.setUpdate_time(System.currentTimeMillis() + "");
					updateSuList.add(suModel);
				}
			}
		}
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);// 事物隔离级别，开启新事务
		TransactionStatus status = txManager.getTransaction(def); // 获得事务状态
		try{
			//逻辑代码，可以写上你的逻辑处理代码
			if(tasks.size()>0){
				taskService.save(tasks.toArray(new InspectTaskModel[] {}));
			}
			if(updateSuList.size()>0){
				suDomainService.update(updateSuList.toArray(new StandardUserModel[] {}));
			}
			if(addSuList.size()>0){
				suDomainService.save(addSuList.toArray(new StandardUserModel[] {}));
			}
			txManager.commit(status);
		}catch(Exception e){
			txManager.rollback(status);
		}
	}

	/**
	 * 执行定时器生成核查任务
	 * 因为远洋要任务生成 根据用户的生成时间来计算 不根据岗位来生成任务，停用 备份
	 */
	/*public void executeTimerGeneratorTask() {
		StandardStationModel[] ssms = service.query(Query.from(StandardStationModel.META_ID));
		int len = 0;
		List<InspectTaskModel> taskList = new ArrayList<>();
		List<StandardStationModel> ssmList = new ArrayList<>();
		for (StandardStationModel ssm : ssms) {
			len++;
			System.out.println("=======" + len + "=======");
			YJWYStationModel station = baseService.queryById(YJWYStationModel.META_ID, ssm.getPk_station());
			InspectStandardModel standard = baseService.queryById(InspectStandardModel.META_ID, ssm.getPk_standard());
			String pcCode = getPcCode(station, standard);
			if ("unSet".equals(pcCode) || StringUtils.isEmpty(pcCode)) {
				continue;
			}
			Date deadLine = null;
			if (!StringUtils.isEmpty(ssm.getLast_deadline_date())) {
				// 如果岗位标准中间表存在完成期限，说明任务不是第一次生成
				deadLine = DateTimeUtil.formatDate(ssm.getLast_deadline_date());
			}
			// 如果任务没有完成期限，说明任务是第一次生成，生成时间选用当前时间
			Date newdeadLine = getDeadlineDate(DateTimeUtil.formatDate(DateTimeUtil.getDate()), pcCode);

			if (isGenerate(deadLine) || StringUtils.isEmpty(ssm.getLast_generate_date())) {
				taskList.addAll(taskGenerator(station, standard, newdeadLine, pcCode));
				ssm.setLast_generate_date(DateTimeUtil.getDate());
				ssm.setLast_deadline_date(DateTimeUtil.getDate(newdeadLine, "YYYY-MM-dd"));
				ssm.setUpdate_time(System.currentTimeMillis() + "");
				ssm.setPk_crop(station.getPk_crop());
				ssmList.add(ssm);
			} else {
				continue;
			}
		}
		taskService.save(taskList.toArray(new InspectTaskModel[] {}));
		service.update(ssmList.toArray(new StandardStationModel[] {}));
	}*/


	/**
	 * 任务生成器
	 * 因为远洋要任务生成 根据用户的生成时间来计算 不根据岗位来生成任务，停用 备份
	 * @param station
	 * @param standard
	 * @param pcCode
	 * @return
	 */
	/*public List<InspectTaskModel> taskGenerator(YJWYStationModel station, InspectStandardModel standard, Date newdeadlineDate, String pcCode) {
		List<YJWYUserModel> list = urelationService.queryUserByStation(station.getPk_station());
		if (null == list || list.size() < 1) {
			return new ArrayList<InspectTaskModel>();
		}
		Query query = Query.from(DefaultOrgModel.META_ID);
		query.and(Condition.create("pk_org_", station.getPk_org()));
		DefaultOrgModel org = orgService.query(query)[0];
		Map<String, String> pcMap = baseinfoQueryService.queryDictionaryForMap("taskRate", 1);
		List<InspectTaskModel> tasks = new ArrayList<InspectTaskModel>();
		for (YJWYUserModel user : list) {
			if (leaveService.isLeaveByUserId(user.getPk_user())) {
				// 休假人员不生成任务，此方法相当影响程序运行，后期先从自身着手优化，再考虑与客户协调优化
				continue;
			}
			InspectTaskModel task = new InspectTaskModel();
			task.setFk_standard(standard.getPk_inspstan());
			task.setFk_project(org.getOrg_project());
			task.setFk_job(station.getPk_station());
			task.setFk_area(org.getOrg_area());
			task.setFk_dept(station.getPk_dept());
			task.setFk_taskuser(user.getPk_user());
			task.setTask_currentuser_pk(user.getPk_user());
			// task.settask_rectifyuser_pk();
			task.setTask_start_time(DateTimeUtil.getDate());
			// task.settask_end_time();
			task.setTask_generate_mode("0");
			task.setTask_score(standard.getInspstan_scorevalue());
			// task.setTask_subtract_score();
			task.setTask_deadline_date(DateTimeUtil.getDate(newdeadlineDate, "YYYY-MM-dd"));
			task.setCheck_state("2");
			task.setTask_type(1);
			// task.setTask_inspectresult();
			task.setTask_code(taskCodeGenerator());
			task.setSpecialty(standard.getSpecialty());
			// task.setTask_rectify_starttime(task_rectify_starttime);
			// task.setTask_rectify_finishtime(task_rectify_finishtime);
			// task.setTask_qualified_time(task_qualified_time);
			task.setTask_iscq("0");
			task.setTask_state("10");
			// task.setCreate_user(create_user);
			task.setCreate_time(DateTimeUtil.getTimestampStr());
			// task.setUpdate_user(update_user);
			task.setUpdate_time(System.currentTimeMillis() + "");
			task.setIs_valid("1");
			task.setTask_pc_name(pcMap.get(pcCode));
			// task.setRecord_finish_lat(record_finish_lat);
			// task.setRecord_finish_lon(record_finish_lon);
			// task.setTask_execute_site(task_execute_site);
			// task.setTask_rectify_deadline_time(task_rectify_deadline_time);
			task.setPk_crop(station.getPk_crop());
			// task.setis_sign
			// task.setCheck_content(check_content);
			// task.setCheck_user_pk(check_user_pk);
			// task.setCheck_time(check_time);
			task.setIs_rectify("0");
			tasks.add(task);
		}
		// return taskService.save(tasks.toArray(new InspectTaskModel[] {}));
		return tasks;
	}*/

	/**
	 * 获取任务频次
	 * 
	 * @param station
	 * @param standard
	 * @return
	 */
	public String getPcCode(YJWYStationModel station, InspectStandardModel standard) {

		if ("competent".equalsIgnoreCase(station.getTask_level())) {
			// 对口主管频次
			return standard.getInspstan_dkzg_pc();
		} else if ("departmentLeader".equalsIgnoreCase(station.getTask_level())) {
			// 部门经理频次
			return standard.getInspstan_bmjl_pc();
		} else if ("projectManager".equalsIgnoreCase(station.getTask_level())) {
			// 项目经理频次
			return standard.getInspstan_xmjl_pc();
		} else if ("assistant".equalsIgnoreCase(station.getTask_level())) {
			// 区域总助频次
			return standard.getInspstan_qyzz_pc();
		} else if ("regionalManage".equalsIgnoreCase(station.getTask_level())) {
			// 区域总频次
			return standard.getInspstan_qyz_pc();
		} else if ("director".equalsIgnoreCase(station.getTask_level())) {
			// 总监频次
			return standard.getInspstan_zj_pc();
		} else {
			return null;
		}
	}

	/**
	 * 获取任务完成期限
	 * 
	 * @param last_generate_date
	 * @param pcCode
	 * @return
	 */
	public Date getDeadlineDate(Date date, String pcCode) {
		if ("unSet".equals(pcCode) || StringUtils.isEmpty(pcCode)) {
			return null;
		}
		int len = pcCode.length();
		String time = pcCode.charAt(2) + "";// 时间标示
		int ts = Integer.parseInt(pcCode.charAt(3) + "");// 时间倍数 = 0;
		if (len == 5) {
			String temp = "" + pcCode.charAt(3) + pcCode.charAt(4);
			ts = Integer.parseInt(temp);// 时间倍数
		}
		if ("Y".equalsIgnoreCase(time)) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(date.getTime());
			int currenYear = calendar.get(Calendar.YEAR);
			calendar.set(Calendar.YEAR, currenYear + ts);
			return calendar.getTime();
		} else if ("M".equalsIgnoreCase(time)) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(date.getTime());
			int currenMonth = calendar.get(Calendar.MONTH);
			calendar.set(Calendar.MONTH, currenMonth + ts);
			return calendar.getTime();
		} else if ("D".equalsIgnoreCase(time)) {
			ts--;
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(date.getTime());
			int currenDate = calendar.get(Calendar.DATE);
			calendar.set(Calendar.DATE, currenDate + ts);
			return calendar.getTime();
		} else {
			return null;
		}
	}

	/**
	 * 判断任务是否可以生成
	 * 
	 * @param nextDate
	 * @return
	 */
	public boolean isGenerate(Date deadline) {
		if (null == deadline) {
			return true;
		}
		long currentDate = DateTimeUtil.formatDate(DateTimeUtil.getDate()).getTime();
		long deadlineDate = deadline.getTime();
		if (currentDate > deadlineDate) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 任务编码生成器
	 * 
	 * @return
	 */
	public String taskCodeGenerator() {
		String date = DateTimeUtil.getDate().replaceAll("-", "");
		return date + DateTimeUtil.getTS().substring(8)+(int)(Math.random()*1000000);
		
	}
}