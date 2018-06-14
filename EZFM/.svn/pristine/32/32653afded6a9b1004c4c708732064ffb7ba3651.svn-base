package com.shareworx.ezfm.problem.details.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.shareworx.ezfm.device.fmdata.model.YJWYEqModel;
import com.shareworx.ezfm.device.fmdata.service.YJWYEqDomainService;
import com.shareworx.ezfm.files.service.YJWYFileService;
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

import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectBusinessService;
import com.shareworx.ezfm.baseinfo.resources.service.YJWYResourcesDomainService;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.device.util.service.YJWYDeviceService;
import com.shareworx.ezfm.problem.details.dao.YJWYProblemDetailsDao;
import com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsModel;
import com.shareworx.ezfm.problem.details.service.YJWYProblemDetailsBusinessService;
import com.shareworx.ezfm.problem.details.service.YJWYProblemDetailsDomainService;
import com.shareworx.ezfm.problem.details.vo.ProblemDetailsVo;
import com.shareworx.ezfm.problem.details.vo.TaskQueryVo;
import com.shareworx.ezfm.problem.record.model.YJWYProblemRecordModel;
import com.shareworx.ezfm.system.dict.model.YJWYDictModel;
import com.shareworx.ezfm.system.dict.service.YJWYDictDomainService;
import com.shareworx.ezfm.utils.UserUtil;
import com.alibaba.fastjson.JSON;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.platform.util.StringUtils;

/**
 * 报事主类操作控制器
 * 
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/problem/details")
public class YJWYProblemDetailsAction {

	final static Logger log = Logger.getLogger(YJWYProblemDetailsAction.class);

	/** 跳转页面 */
	public final static String PAGE_FORWARD = "ezfm/problem/problemmanage/problem_agency_listcard";
	/** 详情跳转页面 */
	public final static String SONPAGE_FORWARD = "ezfm/problem/problemmanage/problem_son";
	/** 处理跳转页面 */
	public final static String PROBLEM_HANDLE = "ezfm/problem/problemmanage/problem_handle";
	/** 维修跳转页面 */
	public final static String PROBLEM_REPAIR = "ezfm/problem/problemmanage/problem_repair";
	/** 完成跳转页面 */
	public final static String PROBLEM_COMPLETE = "ezfm/problem/problemmanage/problem_complete";
	/** 回访跳转页面 */
	public final static String PROBLEM_Visit = "ezfm/problem/problemmanage/problem_visit";

    /** 跳转图片 */
    public final static String SON_IMAGES = "ezfm/problem/problemmanage/problem_son_imgs";
	@Autowired
	@Qualifier(YJWYProblemDetailsBusinessService.ID)
	private YJWYProblemDetailsBusinessService service;

	@Autowired
	@Qualifier(YJWYProblemDetailsDomainService.ID)
	private YJWYProblemDetailsDomainService domainService;
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;

	@Autowired
    @Qualifier(YJWYEqDomainService.ID)
	YJWYEqDomainService yjwyEqDomainService;

    @Autowired
    @Qualifier(YJWYFileService.ID)
    private YJWYFileService fileService;

    public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	@Autowired
	@Qualifier(YJWYDictDomainService.ID)
	private YJWYDictDomainService dictService;

	public void setDictService(YJWYDictDomainService dictService) {
		this.dictService = dictService;
	}

	@Autowired
	@Qualifier(YJWYResourcesDomainService.ID)
	private YJWYResourcesDomainService resourcesService;

	public void setResourcesService(YJWYResourcesDomainService resourcesService) {
		this.resourcesService = resourcesService;
	}

	@Autowired
	@Qualifier(YJWYProjectBusinessService.ID)
	private YJWYProjectBusinessService projectService;

	public void setProjectService(YJWYProjectBusinessService projectService) {
		this.projectService = projectService;
	}

	public void setService(YJWYProblemDetailsBusinessService service) {
		this.service = service;
	}

	public void setDomainService(YJWYProblemDetailsDomainService domainService) {
		this.domainService = domainService;
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
	public ModelAndView listForward(@RequestParam(value = "operation_flag", required = false) String operation_flag) {
		ModelAndView modelAndView = new ModelAndView(PAGE_FORWARD);
		modelAndView.addObject("operation_flag", operation_flag);
		return modelAndView;
	}

	/**
	 * 转向处理
	 * 
	 * @return
	 */
	@RequestMapping(value = "handle/{id}", method = RequestMethod.GET)
	public ModelAndView handleForward(HttpServletRequest request, @PathVariable String id) {
		ModelAndView mv = new ModelAndView(PROBLEM_HANDLE);
		YJWYProblemDetailsModel model = domainService.queryById(id);
		mv.addObject("model", model);
		return mv;
	}

	/**
	 * 转向维修
	 * 
	 * @return
	 */
	@RequestMapping(value = "repair/{id}", method = RequestMethod.GET)
	public ModelAndView repairForward(HttpServletRequest request, @PathVariable String id) {
		JdbcTemplate read = dao.getReadTemplate();
		String sql = "select tab1.pk_details_id,tab1.fk_project_id,tab2.project_name_,tab2.pk_area_"
				+ " from yjwy_problem_details tab1 left join yjwy_pub_project tab2 on tab1.fk_project_id=tab2.pk_project_";
		if (!StringUtils.isEmpty(id)) {
			sql += " where pk_details_id ='" + id + "'";
		} else {
			sql += " where 1!=1";
		}
		Map<String, Object> modelMap = read.queryForMap(sql);
		ModelAndView mv = new ModelAndView(PROBLEM_REPAIR);
		YJWYProblemDetailsModel model = new YJWYProblemDetailsModel();
		if (modelMap.get("pk_details_id") != null) {
			model.setPk_details_id(modelMap.get("pk_details_id").toString());
		}
		if (modelMap.get("fk_project_id") != null) {
			model.setFk_project_id(modelMap.get("fk_project_id").toString());
		}
		if (modelMap.get("project_name_") != null) {
			model.put("project_name", modelMap.get("project_name_"));
		}
		if (modelMap.get("pk_area_") != null) {
			model.put("pk_area", modelMap.get("pk_area_"));
		}
		mv.addObject("model", model);
		return mv;
	}

    @RequestMapping(value = "imgs/{table_name}/{record_id}/{position}", method = RequestMethod.GET)
    public ModelAndView sonpageImges(HttpServletRequest request, @PathVariable String table_name,@PathVariable String record_id,@PathVariable Integer position) {



        ModelAndView mar = new ModelAndView(SON_IMAGES);
        List<Map<String, Object>> list = null;
        // 条件判断

            // 拼接附件查询sql
            StringBuilder sql = new StringBuilder();
            sql.append(  "select * from yjwy_problem_file ");
            sql.append("where table_name = '" + table_name + "' ");
            sql.append("and record_id = '" + record_id + "' ");

            list = fileService.queryList(sql.toString());
            mar.addObject("imgs",list);
            mar.addObject("position",position);
            return mar;

    }
	/**
	 * 转向详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "sonpage/{id}", method = RequestMethod.GET)
	public ModelAndView sonpageForward(HttpServletRequest request, @PathVariable String id) {
		JdbcTemplate read = dao.getReadTemplate();
		String sql = "select tab1.details_number,tab1.question_source,tab1.details_content,"
				+ " tab1.customer_name,tab1.customer_number,tab1.fk_house_address_id,"
				+ " tab1.detailed_address,tab1.state,tab1.create_time,"
                + "tab1.fk_repair_equipment,"
				+ " tab2.project_name_,tab3.class_name,tab3.whether_visit,tab3.time_limit,"
				+ " tab4.user_name_,count(tab5.file_id) as file_id "
				+ " from (((yjwy_problem_details tab1 left join yjwy_pub_project tab2 on tab1.fk_project_id=tab2.pk_project_)"
				+ " left join yjwy_problem_class tab3 on tab1.fk_class_id=tab3.pk_class_id)"
				+ " left join yjwy_pub_user tab4 on tab1.create_user_id=tab4.pk_user_)"
				+ " left join yjwy_problem_file tab5 on tab1.pk_details_id=tab5.record_id";
		if (!StringUtils.isEmpty(id)) {
			sql += " where tab1.pk_details_id ='" + id + "'";
		} else {
			sql += " where 1!=1";
		}
		sql += " GROUP BY  tab1.pk_details_id";
		Map<String, Object> modelMap = read.queryForMap(sql);
		ModelAndView mv = new ModelAndView(SONPAGE_FORWARD);
		// id
		mv.addObject("pk_details_id", id);
		// 编码
		mv.addObject("details_number", modelMap.get("details_number"));
		// 报修来源
		if (modelMap.get("question_source") != null
				&& !StringUtils.isEmpty(modelMap.get("question_source").toString())) {
			YJWYDictModel dictModel = dictService.getDict(modelMap.get("question_source").toString());
			mv.addObject("question_source", dictModel.getDict_name());
		}
		// 详情内容
		mv.addObject("details_content", modelMap.get("details_content"));
		// 联系人
		mv.addObject("customer_name", modelMap.get("customer_name"));
		// 联系方式
		mv.addObject("customer_number", modelMap.get("customer_number"));
		//设备名称
        if(modelMap.get("fk_repair_equipment") != null){
            String fk_repair_equipment = (String) modelMap.get("fk_repair_equipment");
            YJWYEqModel yjwyEqModel = yjwyEqDomainService.queryById(fk_repair_equipment);
            mv.addObject("eq_name",yjwyEqModel.getName());
        }

		// 房源地址
		if (modelMap.get("fk_house_address_id") != null
				&& !StringUtils.isEmpty(modelMap.get("fk_house_address_id").toString())) {
			String fk_house_address_id = resourcesService
					.queryNameByResourcesId(modelMap.get("fk_house_address_id").toString());
			mv.addObject("fk_house_address_id", fk_house_address_id);
		}
		// 详细地址
		mv.addObject("detailed_address", modelMap.get("detailed_address"));
		// 报事状态
		mv.addObject("detailed_address", modelMap.get("detailed_address"));
		// 任务状态
		if (modelMap.get("state") != null && !StringUtils.isEmpty(modelMap.get("state").toString())) {
			if (modelMap.get("state").equals(1)) {
				mv.addObject("state", "未处理");
			} else if (modelMap.get("state").equals(2)) {
				mv.addObject("state", "处理中");
			} else if (modelMap.get("state").equals(3)) {
				mv.addObject("state", "处理完成");
			} else if (modelMap.get("state").equals(4)) {
				mv.addObject("state", "已回访");
			}
		}
		// 创建时间
		mv.addObject("create_time", modelMap.get("create_time"));
		// 项目名称
		mv.addObject("project_name", modelMap.get("project_name_"));
		// 报事分类
		mv.addObject("class_name", modelMap.get("class_name"));
		// 是否回访
		if (modelMap.get("whether_visit") != null && !StringUtils.isEmpty(modelMap.get("whether_visit").toString())) {
			if (modelMap.get("whether_visit").equals(1)) {
				mv.addObject("whether_visit", "回访");
			} else if (modelMap.get("whether_visit").equals(2)) {
				mv.addObject("whether_visit", "不回访");
			}
		}
		// 处理时限
		if (modelMap.get("time_limit") != null && !StringUtils.isEmpty(modelMap.get("time_limit").toString())) {
			if (modelMap.get("time_limit").equals(1)) {
				mv.addObject("time_limit", "24小时");
			} else if (modelMap.get("time_limit").equals(2)) {
				mv.addObject("time_limit", "40分钟");
			} else if (modelMap.get("time_limit").equals(3)) {
				mv.addObject("time_limit", "30分钟");
			}
		}
		// 跟进人姓名
		mv.addObject("user_name", modelMap.get("user_name_"));
		// 附件
		mv.addObject("file_id", modelMap.get("file_id"));

        // 拼接附件查询sql
        StringBuilder sql2 = new StringBuilder();
        sql2.append(  "select * from yjwy_problem_file ");
        sql2.append("where table_name = 'yjwy_problem_details' ");
        sql2.append("and record_id = '" + id + "' ");

        List<Map<String, Object>> list = fileService.queryList(sql2.toString());
        mv.addObject("imgs",list);

		return mv;
	}

	/**
	 * 转向完成
	 * 
	 * @return
	 */
	@RequestMapping(value = "complete/{id}", method = RequestMethod.GET)
	public ModelAndView completeForward(HttpServletRequest request, @PathVariable String id) {
		ModelAndView mv = new ModelAndView(PROBLEM_COMPLETE);
		mv.addObject("id", id);
		return mv;
	}

	/**
	 * 转向回访
	 * 
	 * @return
	 */
	@RequestMapping(value = "visit/{id}", method = RequestMethod.GET)
	public ModelAndView visitForward(HttpServletRequest request, @PathVariable String id) {
		ModelAndView mv = new ModelAndView(PROBLEM_Visit);
		mv.addObject("id", id);
		return mv;
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
			query.and(Condition.create("state", 1));
		} else if (falg == 3) {
			query.and(Condition.create("state", 2));
		} else if (falg == 4) {
			query.and(Condition.create("state", 3));
		} else if (falg == 5) {
			query.and(Condition.create("state", 4));
		}
		YJWYProblemDetailsModel[] models = service.query(query);
		YJWYProblemDetailsDao domainDao = SpringUtils.getBean(YJWYProblemDetailsDao.ID);
		long count = domainDao.countListByCondition(query);
		ModelAndResult mar = new ModelAndResult();
		mar.setTotal(count);
		mar.setAttribute("rows", models);
		return mar;
	}

	/**
	 * 查询操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "conditon/query", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult queryTaskForContion(@RequestBody TaskQueryVo queryvo) {
		JdbcTemplate read = dao.getReadTemplate();
		// 访问表示符：1：待办任务；2：全部任务；3：处理中；4：已完成待回访；5：已回访
		String whrSql = " where 1=1 ";
		if (StringUtils.isEmpty(queryvo.getState())) {
			if (queryvo.getOperation_flag().equals("1")) {
//				whrSql += "and (tab1.state = 1 or (tab1.state = 2 and (tab4.task_state = 0 or tab4.task_state = 1 or tab4.task_state = 4 or tab4.task_state = 5)))";
				whrSql += "and tab1.state = 1 ";
			} else if (queryvo.getOperation_flag().equals("3")) {
				whrSql += "and tab1.state = 2 ";
			} else if (queryvo.getOperation_flag().equals("4")) {
				whrSql += "and tab1.state = 3 ";
			} else if (queryvo.getOperation_flag().equals("5")) {
				whrSql += "and tab1.state = 4 ";
			} else if (queryvo.getOperation_flag().equals("6")) {
				whrSql += "and tab1.state = 5 ";
			}
		} else {
			whrSql += " and tab1.state = " + queryvo.getState();
		}
		if (!StringUtils.isEmpty(queryvo.getTask_state())) {
			whrSql += " and tab4.task_state =" + queryvo.getTask_state();
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
		} else {
			if (!StringUtils.isEmpty(queryvo.getProject_id()) && !queryvo.getProject_id().equals("default")) {
				whrSql += " and tab1.fk_project_id = '" + queryvo.getProject_id() + "' ";
			}
		}
		if (!StringUtils.isEmpty(queryvo.getDispatch_type())) {
			whrSql += " and tab4.dispatch_type ='" + queryvo.getDispatch_type() + "'";
		}
		if (!StringUtils.isEmpty(queryvo.getQuestion_source())) {
			whrSql += " and tab1.question_source ='" + queryvo.getQuestion_source() + "'";
		}
		if (!StringUtils.isEmpty(queryvo.getDatails_code())) {
			whrSql += " and tab4.datails_code like '%" + queryvo.getDatails_code() + "%'";
		}
		if (!StringUtils.isEmpty(queryvo.getCreate_time1())) {
			whrSql += " and tab1.create_time >='" + queryvo.getCreate_time1() + " 00:00:00" + "'";
		}
		if (!StringUtils.isEmpty(queryvo.getCreate_time2())) {
			whrSql += " and tab1.create_time <='" + queryvo.getCreate_time2() + " 23:59:59" + "'";
		}
		// 2017-1-19 kim start:修改报事列表没有进行公司隔离Bug
		whrSql += " and tab1.pk_crop = '" + UserUtil.getCurrentUser().getPk_crop() + "'";
		// 2017-1-19 kim end
		// 默认查询当前用户所有项目数据
		Set<String> projectIds = deviceService.queryProjectIdsByPkUser(UserUtil.getCurrentUserPk());
		if (projectIds != null && projectIds.size() > 0) {
			whrSql += " and " + DeviceUtil.getInNotInSql("tab1.fk_project_id", QueryContents.TYPE_IN,
					projectIds.toArray(new String[] {}));
		} else {
			// 2017-1-19 kim start:修复报事中 关于project_id为空时，可以查询所有的报事的Bug
			whrSql += " and tab1.fk_project_id = null ";
			// 2017-1-19 kim end
		}
		String sql = "select tab1.pk_details_id,tab1.fk_details_id,tab1.fk_project_id,tab1.customer_name,tab1.customer_number,"
				+ "tab1.detailed_address,tab1.details_number,tab1.create_time,tab1.customer_name,"
				+ "tab1.state,tab1.update_time,tab2.project_name_,"
				+ "case when tab3.pk_user_ is null then "
			 	+ "	(select tab5.user_name_ from yjwy_pub_user tab5 where tab5.pk_user_ = tab4.duty_user_id) "
			 	+ "else tab3.user_name_ end user_name_,"
				+ "tab4.datails_code,tab4.task_state,tab4.repair_user"
				+ " from ((yjwy_problem_details tab1 left join yjwy_pub_project tab2 on tab1.fk_project_id=tab2.pk_project_) "
				+ "left join yjwy_pub_user tab3 on tab1.fk_duty_user_id=tab3.pk_user_) "
				+ "left join yjwy_worktask_details tab4 on tab1.fk_details_id= tab4.pk_details_id" + whrSql
				+ " order by tab1.update_time desc" + " limit " + queryvo.getStart() + "," + queryvo.getLimit();
		String countSql = "select count(tab1.pk_details_id) "
				+ "from ((yjwy_problem_details tab1 left join yjwy_pub_project tab2 on tab1.fk_project_id=tab2.pk_project_) "
				+ "left join yjwy_pub_user tab3 on tab1.fk_duty_user_id=tab3.pk_user_) "
				+ "left join yjwy_worktask_details tab4 on tab1.fk_details_id= tab4.pk_details_id" + whrSql;
		int count = read.queryForObject(countSql, Integer.class);
		List<YJWYProblemDetailsModel> list = read.query(sql, new RowMapper<YJWYProblemDetailsModel>() {
			@Override
			public YJWYProblemDetailsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				YJWYProblemDetailsModel model = new YJWYProblemDetailsModel();
				// 报事ID
				model.setPk_details_id(rs.getString("pk_details_id"));
				// 工单ID
				model.setFk_details_id(rs.getString("fk_details_id"));
				// 报事单号
				model.setDetails_number(rs.getString("details_number"));
				// 报事地址
				model.setDetailed_address(rs.getString("detailed_address"));
				// 客户姓名
				model.setCustomer_name(rs.getString("customer_name"));
				// 客户姓名
				model.setCustomer_number(rs.getString("customer_number"));
				// 创建时间
				model.setCreate_time(rs.getString("create_time"));
				// 报事状态
				int state = rs.getInt("state");
				String stateName = "";
				if (state == 1) {
					stateName = "未处理";
				} else if (state == 2) {
					stateName = "处理中";
				} else if (state == 3) {
					stateName = "处理完成";
				} else if (state == 4) {
					stateName = "已回访";
				} else if (state == 5) {
					stateName = "已关闭";
				}
				model.setState(state);
				model.put("stateName", stateName);
				// 当前跟进人
				model.put("duty_user_name", rs.getString("user_name_"));
				// 工单单号
				model.put("datails_code", rs.getString("datails_code"));
				if (!StringUtils.isEmpty(rs.getString("datails_code"))) {
					// 维修状态
					model.put("task_state", rs.getInt("task_state"));
					if (rs.getInt("task_state") == 0) {
						model.put("state_name", "未派单");
					} else if (rs.getInt("task_state") == 1) {
						model.put("state_name", "待接单");
					} else if (rs.getInt("task_state") == 2) {
						model.put("state_name", "维修中");
					} else if (rs.getInt("task_state") == 3) {
						model.put("state_name", "完成");
					} else if (rs.getInt("task_state") == 4) {
						model.put("state_name", "已拒单");
					} else if (rs.getInt("task_state") == 5) {
						model.put("state_name", "已取消");
					}
				}
				// 维修人员
				model.put("repair_user", rs.getString("repair_user"));
				// 项目ID
				model.setFk_project_id(rs.getString("fk_project_id"));
				// 项目名称
				model.put("fk_project_name", rs.getString("project_name_"));
				return model;
			}

		});
		YJWYProblemDetailsModel[] tasks = list.toArray(new YJWYProblemDetailsModel[] {});
		ModelAndResult mar = new ModelAndResult(tasks);
		mar.setTotal(count);
		return mar;
	}

	/**
	 * 新增保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult save(ProblemDetailsVo vo) {
		YJWYProblemDetailsModel[] rs = service.saveDetails(vo);
		return new ModelAndResult(rs);
	}

	/**
	 * 新增保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "saveTest", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult saveTest() {
		ProblemDetailsVo vo = new ProblemDetailsVo();
		vo.setWhether_repair(1);
		vo.setFk_project_id("0000002016092100002M");
		vo.setDetails_content("后台发布报修测试");
		vo.setCustomer_name("并发测试");
		vo.setCustomer_number("15827270120");
		vo.setWork_service_type("serviceCateTwo");
		vo.setWork_repair_class_id("00000020160921000035");
		vo.setWork_repair_details("上地南路");
		for (int i = 0; i < 200; i++) {
			service.saveDetails(vo);
		}
		YJWYProblemDetailsModel[] rs = service.saveDetails(vo);
		return new ModelAndResult(rs);
	}

	/**
	 * 报修操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "save/repair", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult saveRepair(ProblemDetailsVo vo) {
		YJWYProblemDetailsModel[] rs = service.saveRepair(vo);
		return new ModelAndResult(rs);
	}

	/**
	 * 处理操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "saveHandle", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult saveHandle(ProblemDetailsVo vo) {
		YJWYProblemDetailsModel[] rs = service.updateHandle(vo);
		return new ModelAndResult(rs);
	}

	/**
	 * 查询子页面记录流程操作
	 * 
	 * @param taskId
	 * @return
	 */
	@RequestMapping("query/record")
	public @ResponseBody ModelAndResult queryRecord(@RequestParam(value = "pk_details_id") String pk_details_id) {
		String sql = " select tab1.pk_record_id,tab1.record_time,tab1.record_content,tab1.operate_state,tab1.evaluate_content,tab1.attitude,tab1.quality,tab1.effect"
				+ " ,tab2.user_name_,tab3.dict_name_,tab4.dict_name_ as evaluate_type,count(tab5.file_id) as file_id  from "
				+ " (((yjwy_problem_record tab1 left join yjwy_pub_user tab2 on tab1.record_user_id=tab2.pk_user_)"
				+ " left join yjwy_pub_dict tab3 on tab1.operate_type= tab3.dict_code_)"
				+ " left join yjwy_pub_dict tab4 on tab1.evaluate_type= tab4.dict_code_)"
				+ " left join yjwy_problem_file tab5 on tab1.pk_record_id=tab5.record_id";
		if (!StringUtils.isEmpty(pk_details_id)) {
			sql += " where tab1.fk_details_id ='" + pk_details_id + "' GROUP BY tab1.pk_record_id";
		}
		JdbcTemplate read = dao.getReadTemplate();
		List<YJWYProblemRecordModel> list = read.query(sql, new RowMapper<YJWYProblemRecordModel>() {
			@Override
			public YJWYProblemRecordModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				YJWYProblemRecordModel vo = new YJWYProblemRecordModel();
				// 记录ID
				vo.setPk_record_id(rs.getString("pk_record_id"));
				// 操作时间
				vo.setRecord_time(rs.getString("record_time"));
				// 操作人姓名
				vo.setRecord_user_id(rs.getString("user_name_"));
				// 操作备注
				vo.setRecord_content(rs.getString("record_content"));
				// 操作表示符
				vo.setOperate_type(rs.getString("dict_name_"));
				// 下级状态
				int task_state = rs.getInt("operate_state");
				// 任务状态
				if (task_state == 1) {
					vo.put("task_name", "未处理");
				} else if (task_state == 2) {
					vo.put("task_name", "处理中");
				} else if (task_state == 3) {
					vo.put("task_name", "处理完成");
				} else if (task_state == 4) {
					vo.put("task_name", "已回访");
				}
				// 评价内容
				vo.setEvaluate_content(rs.getString("evaluate_content"));
				// 评价类型
				vo.setEvaluate_type(rs.getString("evaluate_type"));
				// 服务态度
				vo.put("attitude_name", rs.getInt("attitude") == 0 ? "" : "是");
				// 及时性
				vo.put("quality_name", rs.getInt("quality") == 0 ? "" : "是");
				// 服务技能
				vo.put("effect_name", rs.getInt("effect") == 0 ? "" : "是");
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
	 * 完成操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "saveComplete", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult saveComplete(ProblemDetailsVo vo) {
		YJWYProblemDetailsModel[] rs = service.updateComplete(vo);
		return new ModelAndResult(rs);
	}

	/**
	 * 回访操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "saveVisit", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult saveVisit(ProblemDetailsVo vo) {
		YJWYProblemDetailsModel[] rs = service.updateVisit(vo);
		return new ModelAndResult(rs);
	}

	/**
	 * 修改保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYProblemDetailsModel model) {
		YJWYProblemDetailsModel[] rs = service.update(new YJWYProblemDetailsModel[] { model });
		return new ModelAndResult(rs);
	}

	/**
	 * 删除操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYProblemDetailsModel[] models) {
		YJWYProblemDetailsModel[] rs = service.delete(models);
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
