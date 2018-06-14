package com.shareworx.ezfm.worktask.details.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

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
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectBusinessService;
import com.shareworx.ezfm.baseinfo.resources.service.YJWYResourcesDomainService;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserDomainService;
import com.shareworx.ezfm.device.fmdata.model.YJWYEqModel;
import com.shareworx.ezfm.device.fmdata.model.YJWYRoomModel;
import com.shareworx.ezfm.device.fmdata.service.YJWYEqService;
import com.shareworx.ezfm.device.fmdata.service.YJWYRoomService;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.device.util.service.YJWYDeviceService;
import com.shareworx.ezfm.system.dict.model.YJWYDictModel;
import com.shareworx.ezfm.system.dict.service.YJWYDictDomainService;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.ezfm.worktask.details.dao.YJWYWorkTaskDetailsDao;
import com.shareworx.ezfm.worktask.details.model.YJWYWorkTaskDetailsModel;
import com.shareworx.ezfm.worktask.details.service.YJWYWorkTaskDetailsBusinessService;
import com.shareworx.ezfm.worktask.details.vo.WorkTaskDetailsVo;
import com.shareworx.ezfm.worktask.details.vo.WrokTaskSubmitDetailsVo;
import com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordModel;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.DateTimeUtil;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.platform.util.StringUtils;

/**
 * 工单详情表操作控制器
 * 
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/worktask/details")
public class YJWYWorkTaskDetailsAction {

	final static Logger log = Logger.getLogger(YJWYWorkTaskDetailsAction.class);

	/** 跳转页面 */
	public final static String PAGE_FORWARD = "ezfm/workTask/details/details_listcard";
	/** 地点页面 */
	public final static String SONPAGE_PLACE = "ezfm/workTask/details/add_repair_place";
	/** 派单页面 */
	public final static String SONPAGE_DELEGATE = "ezfm/workTask/details/delegate_son";
	/** 人员页面 */
	public final static String SONPAGE_ADDUSER = "ezfm/workTask/details/add_user";
	/** 接单页面 */
	public final static String SONPAGE_DRDERSORREDUSE = "ezfm/workTask/details/orders_reduse_cancel_son";
	/** 完成页面 */
	public final static String SONPAGE_COMPLETE = "ezfm/workTask/details/complete_son";
	/** 子详情界面 */
	public final static String SONPAGE_SONPAGE = "ezfm/workTask/details/show_son_page";
	/** 打印界面 */
	public final static String SONPAGE_PRINT = "ezfm/workTask/details/work_task_print";
	@Autowired
	@Qualifier(YJWYWorkTaskDetailsBusinessService.ID)
	private YJWYWorkTaskDetailsBusinessService service;

	public void setService(YJWYWorkTaskDetailsBusinessService service) {
		this.service = service;
	}

	@Autowired
	@Qualifier(YJWYEqService.ID)
	private YJWYEqService eqService;

	public void setEqService(YJWYEqService eqService) {
		this.eqService = eqService;
	}

	@Autowired
	@Qualifier(YJWYProjectBusinessService.ID)
	private YJWYProjectBusinessService projectService;

	public void setProjectService(YJWYProjectBusinessService projectService) {
		this.projectService = projectService;
	}

	@Autowired
	@Qualifier(YJWYRoomService.ID)
	private YJWYRoomService roomService;

	public void setRoomService(YJWYRoomService roomService) {
		this.roomService = roomService;
	}

	@Autowired
	@Qualifier(YJWYResourcesDomainService.ID)
	private YJWYResourcesDomainService resourcesService;

	public void setResourcesService(YJWYResourcesDomainService resourcesService) {
		this.resourcesService = resourcesService;
	}

	@Autowired
	@Qualifier(YJWYDictDomainService.ID)
	private YJWYDictDomainService dictService;

	public void setDictService(YJWYDictDomainService dictService) {
		this.dictService = dictService;
	}

	@Autowired
	@Qualifier(YJWYUserDomainService.ID)
	private YJWYUserDomainService userService;

	public void setUserService(YJWYUserDomainService userService) {
		this.userService = userService;
	}

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

	/**
	 * 转向地点
	 * 
	 * @return
	 */
	@RequestMapping(value = "place/{id}/{type}", method = RequestMethod.GET)
	public ModelAndView placeForward(HttpServletRequest request, @PathVariable String id, @PathVariable String type) {
		ModelAndView mv = new ModelAndView(SONPAGE_PLACE);
		mv.addObject("pk_project", id);
		mv.addObject("type", type);
		return mv;
	}

	/**
	 * 派单
	 * 
	 * @return
	 */
	@RequestMapping(value = "delegate/{id}", method = RequestMethod.GET)
	public ModelAndView delegateForward(HttpServletRequest request, @PathVariable String id) {
		JdbcTemplate read = dao.getReadTemplate();
		String sql = "select tab1.repair_details,tab1.repair_content,tab2.pk_project_,tab2.project_name_,tab2.pk_area_ from "
				+ "yjwy_worktask_details tab1 left join yjwy_pub_project tab2 "
				+ "on tab1.fk_project_id=tab2.pk_project_ where pk_details_id ='" + id + "'";
		Map<String, Object> modelMap = read.queryForMap(sql);
		ModelAndView mv = new ModelAndView(SONPAGE_DELEGATE);
		mv.addObject("pk_details_id", id);
		mv.addObject("repair_details", modelMap.get("repair_details"));
		mv.addObject("repair_content", modelMap.get("repair_content"));
		mv.addObject("pk_project", modelMap.get("pk_project_"));
		mv.addObject("project_name", modelMap.get("project_name_"));
		mv.addObject("pk_area", modelMap.get("pk_area_"));
		return mv;
	}

	/**
	 * 转发至接单、拒单、取消界面
	 * 
	 * @param request
	 * @param id
	 *            工单ID
	 * @param operation_type
	 *            工单操作类型：1:接单；2:拒单；
	 * @return
	 */
	@RequestMapping(value = "ordersOrReduseCancel/{id}/{operation_type}", method = RequestMethod.GET)
	public ModelAndView ordersOrReduseCancelForward(HttpServletRequest request, @PathVariable String id,
			@PathVariable String operation_type) {
		JdbcTemplate read = dao.getReadTemplate();
		String sql = "select tab1.repair_details,tab1.repair_content,tab2.pk_project_,tab2.project_name_ from "
				+ "yjwy_worktask_details tab1 left join yjwy_pub_project tab2 "
				+ "on tab1.fk_project_id=tab2.pk_project_ where pk_details_id ='" + id + "'";
		Map<String, Object> modelMap = read.queryForMap(sql);
		ModelAndView mv = new ModelAndView(SONPAGE_DRDERSORREDUSE);
		mv.addObject("pk_details_id", id);
		mv.addObject("operation_type", operation_type);
		mv.addObject("repair_details", modelMap.get("repair_details"));
		mv.addObject("repair_content", modelMap.get("repair_content"));
		mv.addObject("pk_project", modelMap.get("pk_project_"));
		mv.addObject("project_name", modelMap.get("project_name_"));
		return mv;
	}

	/**
	 * 完成
	 * 
	 * @return
	 */
	@RequestMapping(value = "complete/{id}", method = RequestMethod.GET)
	public ModelAndView completeForward(HttpServletRequest request, @PathVariable String id) {
		JdbcTemplate read = dao.getReadTemplate();
		String sql = "select tab1.repair_details,tab1.repair_content,tab1.repair_class_id,tab2.pk_project_,tab2.project_name_ from "
				+ "yjwy_worktask_details tab1 left join yjwy_pub_project tab2 "
				+ "on tab1.fk_project_id=tab2.pk_project_ where pk_details_id ='" + id + "'";
		Map<String, Object> modelMap = read.queryForMap(sql);
		ModelAndView mv = new ModelAndView(SONPAGE_COMPLETE);
		mv.addObject("pk_details_id", id);
		mv.addObject("repair_details", modelMap.get("repair_details"));
		mv.addObject("repair_content", modelMap.get("repair_content"));
		mv.addObject("repair_class_id", modelMap.get("repair_class_id"));
		mv.addObject("pk_project", modelMap.get("pk_project_"));
		mv.addObject("project_name", modelMap.get("project_name_"));
		return mv;
	}

	/**
	 * 详情界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "showSonPage/{id}", method = RequestMethod.GET)
	public ModelAndView showSonPageForward(HttpServletRequest request, @PathVariable String id) {
		JdbcTemplate read = dao.getReadTemplate();
		String sql = "select "
				+ " tab1.pk_details_id,tab1.datails_code,tab1.fk_repair_address,tab1.repair_details,tab1.repair_content,tab1.repair_user,tab1.contact_number,tab1.service_type,tab1.dispatch_type, "
				+ " tab1.worktask_type,tab1.task_state,tab1.update_time,tab2.project_name_,tab3.user_name_,tab4.class_name," +
				"(select count(tab5.file_id) AS file_id from yjwy_problem_file tab5 where tab5.record_id = tab1.pk_details_id)"
				+ " from (((yjwy_worktask_details tab1 left join yjwy_pub_project tab2 "
				+ " on tab1.fk_project_id=tab2.pk_project_) left join yjwy_pub_user "
				+ " tab3 on tab1.duty_user_id=tab3.pk_user_) left join yjwy_worktask_repair_class"
				+ " tab4 on tab1.repair_class_id=tab4.pk_class_id)"
				+ "where pk_details_id ='"
				+ id + "'";
		Map<String, Object> modelMap = read.queryForMap(sql);
		ModelAndView mv = new ModelAndView(SONPAGE_SONPAGE);
		// id
		mv.addObject("pk_details_id", id);
		// 编码
		mv.addObject("datails_code", modelMap.get("datails_code"));
		// 详细地址
		mv.addObject("repair_details", modelMap.get("repair_details"));
		// 维修内容
		mv.addObject("repair_content", modelMap.get("repair_content"));
		// 项目名称
		mv.addObject("project_name", modelMap.get("project_name_"));
		// 跟进人姓名
		mv.addObject("user_name", modelMap.get("user_name_"));
		// 预约时间
		mv.addObject("bookings_time", modelMap.get("bookings_time"));
		// 维修种类
		mv.addObject("class_name", modelMap.get("class_name"));
		// 报修人
		mv.addObject("repair_user", modelMap.get("repair_user"));
		// 联系电话
		mv.addObject("contact_number", modelMap.get("contact_number"));
		// 服务类型
		if (modelMap.get("service_type") != null && !StringUtils.isEmpty(modelMap.get("service_type").toString())) {
			YJWYDictModel dictModel = dictService.getDict(modelMap.get("service_type").toString());
			if (dictModel != null) {
				mv.addObject("service_type", dictModel.getDict_name());
			}
		}
		// 接单类型
		if (modelMap.get("dispatch_type") != null && !StringUtils.isEmpty(modelMap.get("dispatch_type").toString())) {
			if (modelMap.get("dispatch_type").equals(1)) {
				mv.addObject("dispatch_type", "抢单");
			} else if (modelMap.get("dispatch_type").equals(2)) {
				mv.addObject("dispatch_type", "派单");
			}
		}
		// 任务状态
		if (modelMap.get("task_state") != null && !StringUtils.isEmpty(modelMap.get("task_state").toString())) {
			if (modelMap.get("task_state").equals(0)) {
				mv.addObject("task_state", "未派单");
			} else if (modelMap.get("task_state").equals(1)) {
				mv.addObject("task_state", "待接单");
			} else if (modelMap.get("task_state").equals(2)) {
				mv.addObject("task_state", "维修中");
			} else if (modelMap.get("task_state").equals(3)) {
				mv.addObject("task_state", "完成");
			} else if (modelMap.get("task_state").equals(4)) {
				mv.addObject("task_state", "已拒单");
			} else if (modelMap.get("task_state").equals(5)) {
				mv.addObject("task_state", "已取消");
			}
		}
		// 报修来源
		if (modelMap.get("worktask_type") != null && !StringUtils.isEmpty(modelMap.get("worktask_type").toString())) {
			YJWYDictModel dictModel = dictService.getDict(modelMap.get("worktask_type").toString());
			if (dictModel != null) {
				mv.addObject("worktask_type", dictModel.getDict_name());
			}
		}
		// 地点
		if (modelMap.get("fk_repair_address") != null
				&& !StringUtils.isEmpty(modelMap.get("fk_repair_address").toString())) {
			String place = resourcesService.queryNameByResourcesId(modelMap.get("fk_repair_address").toString());
			mv.addObject("repair_place", place);
		}
		if (modelMap.get("file_id") != null && !StringUtils.isEmpty(modelMap.get("file_id").toString())) {
			mv.addObject("file_id", modelMap.get("file_id"));
		}
		return mv;
	}

	/**
	 * 转向人员
	 * 
	 * @return
	 */
	@RequestMapping(value = "addUser/{id}/{areaId}/{projectId}/{user_type}/{gridId}", method = RequestMethod.GET)
	public ModelAndView addUser(HttpServletRequest request, @PathVariable String id, @PathVariable String areaId,
			@PathVariable String projectId, @PathVariable String user_type, @PathVariable String gridId) {
		ModelAndView mv = new ModelAndView(SONPAGE_ADDUSER);
		mv.addObject("pk_details_id", id);
		mv.addObject("areaId", areaId);
		mv.addObject("projectId", projectId);
		mv.addObject("user_type", user_type);
		mv.addObject("gridId", gridId);
		return mv;
	}

	/**
	 * 打印
	 * 
	 * @return
	 */
	@RequestMapping(value = "print/{id}", method = RequestMethod.GET)
	public ModelAndView printForward(HttpServletRequest request, @PathVariable String id) {
		JdbcTemplate read = dao.getReadTemplate();
		String sql = "select tab1.datails_code,tab1.repair_user, "
				+ " tab1.contact_number,tab1.create_time,tab1.orders_time,tab1.repair_content, "
				+ " tab1.finish_time,tab1.repair_details,tab1.work_number,tab1.artificial_cost,tab1.material_cost,tab1.count_cost"
				+ " ,tab2.user_name_,tab3.class_name,tab4.project_name_,tab5.dict_name_ as service_type"
				+ " from (((yjwy_worktask_details tab1 left join yjwy_pub_user tab2 on tab1.repair_user_id=tab2.pk_user_)"
				+ " left join yjwy_worktask_repair_class tab3 on tab1.repair_class_id=tab3.pk_class_id) "
				+ " left join yjwy_pub_project tab4 on tab1.fk_project_id=tab4.pk_project_)"
				+ " left join yjwy_pub_dict tab5 on tab1.service_type=tab5.dict_code_";
		if (!StringUtils.isEmpty(id)) {
			sql += " where tab1.pk_details_id ='" + id + "'";
		} else {
			sql += " where 1!=1";
		}
		Map<String, Object> modelMap = read.queryForMap(sql);
		ModelAndView mv = new ModelAndView(SONPAGE_PRINT);
		// 服务类型
		mv.addObject("service_type", modelMap.get("service_type"));
		// 项目名称
		mv.addObject("project_name", modelMap.get("project_name_"));
		// 维修种类
		mv.addObject("class_name", modelMap.get("class_name"));
		// 编码
		mv.addObject("datails_code", modelMap.get("datails_code"));
		// 报修人
		mv.addObject("repair_user", modelMap.get("repair_user"));
		// 联系电话
		mv.addObject("contact_number", modelMap.get("contact_number"));
		// 报修时间
		mv.addObject("create_time", modelMap.get("create_time"));
		// 到达时间
		mv.addObject("orders_time", modelMap.get("orders_time"));
		// 维修内容
		mv.addObject("repair_content", modelMap.get("repair_content"));
		// 完成时间
		mv.addObject("finish_time", modelMap.get("finish_time"));
		// 签名图片
		String filePathSql = "select file_path from  yjwy_problem_file where record_id='" + id + "'";
		try {
			Map<String, Object> visitTimeMap = read.queryForMap(filePathSql);
			// 无法获取图片，跳过
			if (visitTimeMap != null) {
				// 回访时间
				mv.addObject("file_path", visitTimeMap.get("file_path"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			// 未查到异常错误，不做处理。跳过
		}
		// 回访时间/回访人
		String visitTimeSql = "select tab1.evaluate_type,tab1.attitude,tab1.quality,tab1.effect,tab1.record_time,tab3.user_name_ from (yjwy_problem_record tab1 "
				+ " left join yjwy_problem_details tab2 on tab1.fk_details_id=tab2.pk_details_id) "
				+ " left join yjwy_pub_user tab3 on tab1.record_user_id=tab3.pk_user_"
				+ " where tab1.operate_type='operationExpress7'and tab2.fk_details_id='" + id + "'";
		try {
			Map<String, Object> visitTimeMap = read.queryForMap(visitTimeSql);
			// 无法获取回访记录，跳过
			if (visitTimeMap != null) {
				// 回访时间
				mv.addObject("visit_time", visitTimeMap.get("record_time"));
				// 评价类型
				mv.addObject("evaluate_type", visitTimeMap.get("evaluate_type"));
				// 服务态度
				mv.addObject("attitude", visitTimeMap.get("attitude"));
				// 服务及时性
				mv.addObject("quality", visitTimeMap.get("quality"));
				// 服务机能
				mv.addObject("effect", visitTimeMap.get("effect"));
				// 回访人员
				mv.addObject("visit_user_name", visitTimeMap.get("user_name_"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			// 未查到异常错误，不做处理。跳过
		}
		// 维修地点
		mv.addObject("repair_details", modelMap.get("repair_details"));
		// 维修处理记录
		String remarksSql = "select operation_remarks from yjwy_worktask_details_record "
				+ " where operation_express='operationExpress6' and fk_details_id='" + id + "'";
		try {
			Map<String, Object> remarksMap = read.queryForMap(remarksSql);
			// 无法获取维修处理记录，跳过
			if (remarksMap != null) {
				mv.addObject("operation_remarks", remarksMap.get("operation_remarks"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			// 未查到异常错误，不做处理。跳过
		}
		// 维修类型
		mv.addObject("class_name", modelMap.get("class_name"));
		// 数量
		mv.addObject("work_number", modelMap.get("work_number"));
		// 人工费用
		mv.addObject("artificial_cost", modelMap.get("artificial_cost"));
		// 材料费用
		mv.addObject("material_cost", modelMap.get("material_cost"));
		// 费用合计
		mv.addObject("count_cost", modelMap.get("count_cost"));
		// 维修人
		mv.addObject("repair_user_name", modelMap.get("user_name_"));
		// 完成耗时
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (modelMap.get("orders_time") != null && !StringUtils.isEmpty(modelMap.get("orders_time").toString())
				&& modelMap.get("finish_time") != null
				&& !StringUtils.isEmpty(modelMap.get("finish_time").toString())) {
			String finishDate = modelMap.get("finish_time").toString();
			String ordersDate = modelMap.get("orders_time").toString();
			try {
				// 完成时间
				Date finish_time = sdf.parse(finishDate);
				// 接单时间
				Date orders_time = sdf.parse(ordersDate);
				long time = finish_time.getTime() - orders_time.getTime();
				int totalS = new Long(time / 1000 / 60).intValue();
				mv.addObject("complete_time_consuming", totalS);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 打单人
		YJWYUserModel userModel = userService.queryById(UserUtil.getCurrentUserPk());
		if (userModel != null && !StringUtils.isEmpty(userModel.getUser_name())) {
			mv.addObject("print_user_name", userModel.getUser_name());
		}
		// 打单时间
		mv.addObject("print_time", sdf.format(new Date()));
		return mv;
	}

	/**
	 * 转向列表页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView listForward(@RequestParam(value = "operation_flag", required = false) String operation_flag) {
		ModelAndView modelAndView = new ModelAndView(PAGE_FORWARD);
		modelAndView.addObject("operation_flag", operation_flag);
		return modelAndView;
	}

	/**
	 * 查询操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult query(@RequestParam(value = "param", required = false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		int falg = 0;
		for (Condition condition : query.getAndList()) {
			if (condition.getKey().equals("operation_flag")) {
				if (condition.getValue() != null) {
					falg = Integer.parseInt(String.valueOf(condition.getValue()));
				}
				query.getAndList().remove(condition);
				if (query.getAndList().size() <= 0) {
					break;
				}
			}
		}
		// 访问表示符：1：待办任务；2：全部任务；3：处理中；4：已完成待回访；5：已回访
		if (falg == 1) {
			query.or(Condition.create("task_state", 0));
			query.or(Condition.create("task_state", 4));
			query.or(Condition.create("task_state", 5));
		} else if (falg == 3) {
			query.and(Condition.create("task_state", 2));
		} else if (falg == 4) {
			query.and(Condition.create("task_state", 3));
		} else if (falg == 5) {
			query.and(Condition.create("task_state", 4));
		}
		YJWYWorkTaskDetailsModel[] models = service.query(query);
		YJWYWorkTaskDetailsDao domainDao = SpringUtils.getBean(YJWYWorkTaskDetailsDao.ID);
		long count = domainDao.countListByCondition(query);
		ModelAndResult mar = new ModelAndResult();
		mar.setTotal(count);
		mar.setAttribute("rows", models);
		mar.setTotal(count);
		return mar;
	}

	/**
	 * 查询操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "queryModels", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult queryModel(@RequestBody WorkTaskDetailsVo queryvo) {
		JdbcTemplate read = dao.getReadTemplate();
		String countSql = "select count(*) from " + "(yjwy_worktask_details tab1 left join yjwy_pub_project tab2 "
				+ "on tab1.fk_project_id=tab2.pk_project_) left join yjwy_pub_user "
				+ "tab3 on tab1.duty_user_id=tab3.pk_user_";
		String whrSql = " where 1=1 ";
		// 根据操作符判断任务状态
//		System.out.println("\n=====" + queryvo.getTask_state() + "=====\n");
		if (StringUtils.isEmpty(queryvo.getTask_state())) {
			// 判断当前操作是否是全部任务，非全部任务task_state设置失效
			if (queryvo.getOperation_flag().equals("1")) {
				whrSql += " and (tab1.task_state = 0 or task_state = 4 or task_state = 5)";
			} else if (queryvo.getOperation_flag().equals("3")) {
				whrSql += " and tab1.task_state = 1";
			} else if (queryvo.getOperation_flag().equals("4")) {
				whrSql += " and tab1.task_state = 2";
			} else if (queryvo.getOperation_flag().equals("5")) {
				whrSql += " and (tab1.task_state = 3 or tab1.task_state = 6)";
			}
		} else {
			whrSql += " and tab1.task_state = " + queryvo.getTask_state();
		}
		if (!StringUtils.isEmpty(queryvo.getArea_id()) && !queryvo.getArea_id().equals("default")) {
			if (!StringUtils.isEmpty(queryvo.getProject_id()) && !queryvo.getProject_id().equals("default")) {
				whrSql += " and tab1.fk_project_id = '" + queryvo.getProject_id() + "' ";
			} else {
				YJWYProjectModel[] projectModel = findProjectByAreaId(queryvo.getArea_id());
				if (projectModel != null && projectModel.length > 0) {
					whrSql += " and tab1.fk_project_id in(";
					for (int i = 0; i < projectModel.length; i++) {
						whrSql += "'" + projectModel[i].getPk_project() + "'";
						if (i < projectModel.length - 1) {
							whrSql += ",";
						}
					}
					whrSql += ") ";
				}

			}
		}
		if (!StringUtils.isEmpty(queryvo.getRepair_class_id())) {
			whrSql += " and tab1.repair_class_id = '" + queryvo.getRepair_class_id() + "' ";
		}
		if (!StringUtils.isEmpty(queryvo.getService_type())) {
			whrSql += " and tab1.service_type = '" + queryvo.getService_type() + "' ";
		}
		if (!StringUtils.isEmpty(queryvo.getCreate_time1())) {
			whrSql += " and tab1.create_time >='" + queryvo.getCreate_time1() + " 00:00:00'";
		}
		if (!StringUtils.isEmpty(queryvo.getCreate_time2())) {
			whrSql += " and tab1.create_time <='" + queryvo.getCreate_time2() + " 23:59:59'";
		}
		if (!StringUtils.isEmpty(queryvo.getWorktask_type())) {
			whrSql += " and tab1.worktask_type ='" + queryvo.getWorktask_type() + "'";
		}
		if (!StringUtils.isEmpty(queryvo.getContent())) {
			whrSql += " and (" + "datails_code = '" + queryvo.getContent() + "' or " + "repair_content = '"
					+ queryvo.getContent() + "' or " + "repair_details = '" + queryvo.getContent() + "'" + ")";
		}
		// 默认查询当前用户所有项目数据
		Set<String> projectIds = deviceService.queryProjectIdsByPkUser(UserUtil.getCurrentUserPk());
		// 2017-1-19 kim start:修复工单应该显示在对应公司
		whrSql += " and tab1.pk_crop = '" + UserUtil.getCurrentUser().getPk_crop() + "'";
		// 2017-1-19 kim end
		if (projectIds != null && projectIds.size() > 0) {
			whrSql += " and " + DeviceUtil.getInNotInSql("tab1.fk_project_id", QueryContents.TYPE_IN,
					projectIds.toArray(new String[] {}));
		} else {
			whrSql += " and tab1.fk_project_id = null ";
		}
		// 2017-1-19 kim end
		countSql += whrSql;
		int count = read.queryForObject(countSql, Integer.class);
		YJWYWorkTaskDetailsModel[] models = service.queryModels(queryvo, whrSql);
		ModelAndResult mar = new ModelAndResult(models);
		mar.setTotal(count);
		return mar;
	}

	/**
	 * 查询子页面记录流程操作
	 * 
	 * @param taskId
	 * @return
	 */
	@RequestMapping("query/record")
	public @ResponseBody ModelAndResult queryRecord(@RequestParam(value = "pk_details_id") String pk_details_id) {
		String sql = "select tab1.pk_record_id,tab1.operation_time,tab1.operation_remarks,tab1.task_state,"
				+ "tab2.user_name_,tab3.dict_name_,count(tab4.file_id) as file_id"
				+ " from ((yjwy_worktask_details_record tab1 left join yjwy_pub_user tab2 on tab1.operation_user_id=tab2.pk_user_) "
				+ " left join yjwy_pub_dict tab3 on tab1.operation_express= tab3.dict_code_)"
				+ " left join yjwy_problem_file tab4 on tab4.record_id=tab1.pk_record_id "
				+ " where tab1.fk_details_id ='" + pk_details_id + "' GROUP BY tab1.pk_record_id";
		JdbcTemplate read = dao.getReadTemplate();
		List<YJWYWorkTaskDetailsRecordModel> list = read.query(sql, new RowMapper<YJWYWorkTaskDetailsRecordModel>() {
			@Override
			public YJWYWorkTaskDetailsRecordModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				YJWYWorkTaskDetailsRecordModel vo = new YJWYWorkTaskDetailsRecordModel();
				// 记录ID
				vo.setPk_record_id(rs.getString("pk_record_id"));
				// 操作时间
				vo.setOperation_time(rs.getString("operation_time"));
				// 操作人姓名
				vo.setOperation_user_id(rs.getString("user_name_"));
				// 操作备注
				vo.setOperation_remarks(rs.getString("operation_remarks"));
				// 操作表示符
				vo.setOperation_express(rs.getString("dict_name_"));
				// 下级状态
				int task_state = rs.getInt("task_state");
				// 任务状态
				if (task_state == 0) {
					vo.put("task_name", "未派单");
				} else if (task_state == 1) {
					vo.put("task_name", "待接单");
				} else if (task_state == 2) {
					vo.put("task_name", "维修中");
				} else if (task_state == 3) {
					vo.put("task_name", "完成");
				} else if (task_state == 4) {
					vo.put("task_name", "已拒单");
				} else if (task_state == 5) {
					vo.put("task_name", "已取消");
				}
				// 附件
				if (rs.getInt("file_id") > 0) {
					vo.put("file_id", rs.getString("file_id"));
				}
				return vo;
			}
		});
		if (null == list || list.size() < 1) {
			return new ModelAndResult(false, "该工单没有记录。");
		}
		return new ModelAndResult(list);
	}

	/**
	 * 查询设备列表机房
	 * 
	 * @return
	 */
	@RequestMapping(value = "queryRoomByProject", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult queryRoomByProject(@RequestParam String projectId) {
		List<YJWYRoomModel> roomList = new ArrayList<YJWYRoomModel>();
		if (!StringUtils.isEmpty(projectId) && !("项目选择").equals(projectId)) {
			roomList = roomService.queryRoomModels(projectId);
		}
		return new ModelAndResult(roomList);
	}

	/**
	 * 查询设备列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "queryEqByRoom", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult queryEqByRoom(@RequestParam String roomId) {
		List<YJWYEqModel> eqList = eqService.queryEqModels(roomId);
		return new ModelAndResult(eqList);
	}

	/**
	 * 查询维修地点
	 * 
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value = "queryResourcesByProject", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult queryResourcesByProject(@RequestParam String projectId) {
		// List<YJWYResourcesModel> resourcesList =
		// resourcesService.queryByProject(projectId);
		// return new ModelAndResult(resourcesList);
		return null;
	}

	/**
	 * 定时任务，查询是否有超时工单
	 * 
	 * @param projectId
	 * @return
	 */
	@RequestMapping(value = "timing", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult timing() {
		// 当前时间的前5分钟
		long currentTime = System.currentTimeMillis() - 5 * 60 * 1000;
		Date date = new Date(currentTime);
		String sql = "select datails_code from yjwy_worktask_details where task_state=0 and create_time<'"
				+ DateTimeUtil.getTimestampString(date) + "' "
				+ " and fk_project_id in ( select project_id from yjwy_worktask_area_project_nexus where area_id in ("
				+ " select area_id from yjwy_worktask_area_user_nexus where user_id='" + UserUtil.getCurrentUserPk()
				+ "'))";
		JdbcTemplate read = dao.getReadTemplate();
		List<String> datailsCodeList = read.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				if (!StringUtils.isEmpty(rs.getString("datails_code"))) {
					return rs.getString("datails_code") + "<br>";
				} else {
					return "<br>";
				}
			}
		});
		StringBuffer sb = new StringBuffer();
		for (String item : datailsCodeList) {
			if (!StringUtils.isEmpty(item)) {
				sb.append(item + ",");
			}
		}
		ModelAndResult model = new ModelAndResult();
		if (datailsCodeList != null && datailsCodeList.size() > 0) {
			model.setSuccess(true);
			model.put("datailsCodeList", sb.toString());
		} else {
			model.setSuccess(false);
		}
		return model;
	}

	/**
	 * 新增保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody YJWYWorkTaskDetailsModel model) {
		YJWYWorkTaskDetailsModel[] rs = service.save(new YJWYWorkTaskDetailsModel[] { model });
		return new ModelAndResult(rs);
	}

	/**
	 * 新增保存派单操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "save/delegate", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult saveDelegate(WrokTaskSubmitDetailsVo vo) {
		YJWYWorkTaskDetailsModel[] rs = service.saveDelegate(vo);
		return new ModelAndResult(rs);
	}

	/**
	 * 新增保存拒单接单取消操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "save/ordersOrReduseCancel", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult saveOrdersOrReduseCancel(WrokTaskSubmitDetailsVo vo) {
		YJWYWorkTaskDetailsModel[] rs = service.saveOrdersOrRefuse(vo);
		return new ModelAndResult(rs);
	}

	/**
	 * 新增保存关闭操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "save/close", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult saveClose(
			@RequestParam(value = "detailsId", required = true) String detailsId) {
		YJWYWorkTaskDetailsModel[] rs = service.saveClose(detailsId);
		return new ModelAndResult(rs);
	}

	/**
	 * 新增保存完成操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "saveComplete", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult saveComplete(WrokTaskSubmitDetailsVo vo) {
		YJWYWorkTaskDetailsModel[] rs = service.saveComplete(vo);
		return new ModelAndResult(rs);
	}

	/**
	 * 修改保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYWorkTaskDetailsModel model) {
		YJWYWorkTaskDetailsModel[] rs = service.update(new YJWYWorkTaskDetailsModel[] { model });
		return new ModelAndResult(rs);
	}

	/**
	 * 删除操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYWorkTaskDetailsModel[] models) {
		YJWYWorkTaskDetailsModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}

	// 通过区域ID查询项目
	public YJWYProjectModel[] findProjectByAreaId(String areaId) {
		Query query = Query.from(YJWYProjectModel.META_ID);
		if (!StringUtils.isEmpty(areaId)) {
			query.and(Condition.create("pk_area_", areaId));
		}
		query.addSelect(new String[] { "pk_project_" });
		YJWYProjectModel[] models = projectService.query(query);
		return models;
	}
}
