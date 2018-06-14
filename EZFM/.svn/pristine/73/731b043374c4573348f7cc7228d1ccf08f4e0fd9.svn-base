package com.shareworx.ezfm.webservice.cloudHttpClient.inner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shareworx.ezfm.baseinfo.area.service.YJWYAreaBusinessService;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectBusinessService;
import com.shareworx.ezfm.baseinfo.resources.service.YJWYResourcesBusinessService;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserBusinessService;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserDomainService;
import com.shareworx.ezfm.problem.classadmin.model.ProblemClassModel;
import com.shareworx.ezfm.problem.classadmin.service.ProblemClassDomainService;
import com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsModel;
import com.shareworx.ezfm.problem.details.service.YJWYProblemDetailsDomainService;
import com.shareworx.ezfm.problem.file.service.ProblemFileBusinessService;
import com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusModel;
import com.shareworx.ezfm.problem.record.model.YJWYProblemRecordModel;
import com.shareworx.ezfm.problem.record.service.YJWYProblemRecordDomainService;
import com.shareworx.ezfm.pub.dictionary.OperationExpress;
import com.shareworx.ezfm.system.crop.model.CropModel;
import com.shareworx.ezfm.system.crop.service.ICropService;
import com.shareworx.ezfm.worktask.details.service.YJWYWorkTaskDetailsDomainService;
import com.shareworx.ezfm.worktask.record.service.YJWYWorkTaskDetailsRecordBusinessService;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.persist.service.BaseDomainService;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.ModelUtils;
import com.shareworx.platform.util.StringUtils;

@Service(cloudHttpService.ID)
public class cloudHttpServiceImpl extends cloudHttpClient implements cloudHttpService {

	@Autowired
	@Qualifier(YJWYUserBusinessService.ID)
	private YJWYUserBusinessService userBusinessService;

	public void setUserBusinessService(YJWYUserBusinessService userBusinessService) {
		this.userBusinessService = userBusinessService;
	}

	@Autowired
	@Qualifier(YJWYAreaBusinessService.ID)
	private YJWYAreaBusinessService areaBusinessService;

	public void setAreaBusinessService(YJWYAreaBusinessService areaBusinessService) {
		this.areaBusinessService = areaBusinessService;
	}

	@Autowired
	@Qualifier(YJWYProjectBusinessService.ID)
	private YJWYProjectBusinessService projectBusinessService;

	public void setProjectBusinessService(YJWYProjectBusinessService projectBusinessService) {
		this.projectBusinessService = projectBusinessService;
	}

	@Autowired
	@Qualifier(YJWYResourcesBusinessService.ID)
	private YJWYResourcesBusinessService resourcesBusinessService;

	public void setResourcesBusinessService(YJWYResourcesBusinessService resourcesBusinessService) {
		this.resourcesBusinessService = resourcesBusinessService;
	}

	@Autowired
	@Qualifier(YJWYProblemDetailsDomainService.ID)
	private YJWYProblemDetailsDomainService problemDetailsService;

	public void setProblemDetailsService(YJWYProblemDetailsDomainService problemDetailsService) {
		this.problemDetailsService = problemDetailsService;
	}

	@Autowired
	@Qualifier(YJWYProblemRecordDomainService.ID)
	private YJWYProblemRecordDomainService problemRecordService;

	public void setProblemRecordService(YJWYProblemRecordDomainService problemRecordService) {
		this.problemRecordService = problemRecordService;
	}

	@Autowired
	@Qualifier(ProblemFileBusinessService.ID)
	private ProblemFileBusinessService problemFileService;

	public void setProblemFileService(ProblemFileBusinessService problemFileService) {
		this.problemFileService = problemFileService;
	}

	@Autowired
	@Qualifier(YJWYUserDomainService.ID)
	private YJWYUserDomainService userDomainService;

	public void setUserDomainService(YJWYUserDomainService userDomainService) {
		this.userDomainService = userDomainService;
	}

	@Autowired
	@Qualifier(ProblemClassDomainService.ID)
	private ProblemClassDomainService service;

	public void setService(ProblemClassDomainService service) {
		this.service = service;
	}

	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService baseService;

	@Autowired
	@Qualifier(YJWYWorkTaskDetailsDomainService.ID)
	private YJWYWorkTaskDetailsDomainService worktaskDomainService;

	public void setWorktaskDomainService(YJWYWorkTaskDetailsDomainService worktaskDomainService) {
		this.worktaskDomainService = worktaskDomainService;
	}

	@Autowired
	@Qualifier(YJWYWorkTaskDetailsRecordBusinessService.ID)
	private YJWYWorkTaskDetailsRecordBusinessService detailsRecordService;

	public void setDetailsRecordService(YJWYWorkTaskDetailsRecordBusinessService detailsRecordService) {
		this.detailsRecordService = detailsRecordService;
	}

	@Autowired
	@Qualifier(ICropService.ID)
	private ICropService iCropService;

	public void setICropService(ICropService iCropService) {
		this.iCropService = iCropService;
	}

	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;

	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	final static Logger logger = Logger.getLogger(cloudHttpServiceImpl.class);

	@Override
	public String submitEvent(String pk_crop, String request_source, String user_id, String project_id,
			String service_id, String record_type, String remarks, String contack, String telephone, String location,String WorkTaskId,String yeZhuId) {
		JSONObject node = new JSONObject();
		JSONObject data = new JSONObject();
		try {
			YJWYProblemDetailsModel model = new YJWYProblemDetailsModel();
			// 所属公司
			if (StringUtils.isEmpty(pk_crop)) {
				throw new Exception("所属公司编码不允许为空！");
			} else {
				//CropModel cropModel = iCropService.queryForObject(pk_crop);
				CropModel cropModel = iCropService.queryForObjectById(pk_crop);
				if (cropModel == null) {
					throw new Exception("所属公司编码有误，请查证！");
				}
				model.setPk_crop(cropModel.getPk_crop());
			}
			// 报事来源
			if (StringUtils.isEmpty(request_source)) {
				throw new Exception("报事报修来源不允许为空！");
			} else {
				model.setQuestion_source(request_source);
			}
			// 所属项目
			if (!StringUtils.isEmpty(project_id)) {
				model.setFk_project_id(project_id);
			} else {
				throw new Exception("项目ID不允许为空！");
			}
			// 报事分类
			if (!StringUtils.isEmpty(service_id)) {
				model.setService_id(service_id);
			} else {
				throw new Exception("报事分类ID不允许为空！");
			}
			// 亿街区报事类型（1：报事报修， 2：表彰， 3：投诉）
			if (!StringUtils.isEmpty(record_type)) {
				// 当类型为表彰，默认工单关闭。并五星好评
				if (record_type.equals("2")) {
					// 已关闭，默认
					model.setState(5);
					model.setYjq_evaluate(1);
					model.setYjq_level("5");
				} else {
					// 未处理
					model.setState(1);
					model.setYjq_evaluate(0);
				}
				model.setRecord_type(record_type);
			} else {
				throw new Exception("报事报修类型不允许为空！");
			}
			// 详情
			if (!StringUtils.isEmpty(remarks)) {
				model.setDetails_content(remarks);
			}
			// 联系人
			if (!StringUtils.isEmpty(contack)) {
				model.setCustomer_name(contack);
			}
			// 联系电话
			if (!StringUtils.isEmpty(telephone)) {
				model.setCustomer_number(telephone);
			}
			// 详细地址
			if (!StringUtils.isEmpty(location)) {
				model.setDetailed_address(location);
			}
			// 类公用方法。获取格式化时间；
			String time = this.obtainTime();
			//
			// problemDetailsService.queryById(id)
			// 获取订单单号（项目编号前四位+年月日+四位流水号）
			String code = problemDetailsService.getCodeByProjectId(model.getFk_project_id());
			if (StringUtils.isEmpty(code)) {
				throw new Exception("为查询到项目编码，请查证！");
			}
			// 注入单号
			model.setDetails_number(code);
			// 注入修改
			model.setUpdate_time(System.currentTimeMillis() + "");
			// 创建
			model.setCreate_time(time);
			// 注入操作人
			if (!StringUtils.isEmpty(user_id)) {
				model.setCreate_user_id(user_id);
				model.setUpdate_user_id(user_id);
			} else {
				throw new Exception("操作用户ID不允许为空！");
			}
			//业主ID
			model.setYcq_yezhuid(yeZhuId);
			//save之前先判断易彩区之前有没有保存工单id
			if (!StringUtils.isEmpty(WorkTaskId)) {
				Query queryTaskId = Query.from(YJWYProblemDetailsModel.META_ID);
				queryTaskId.where(new Condition("ycq_worktaskid_", QueryContents.TYPE_EQ,WorkTaskId));
				List<YJWYProblemDetailsModel> problemdetailList = problemDetailsService.queryListByCondition(queryTaskId);
				if(problemdetailList.size() == 0){
					model.setYcq_worktaskid(WorkTaskId);
				}else{
					throw new Exception("'"+WorkTaskId+"'对应的报事id'"+problemdetailList.get(0).getPk_details_id()+"'已经存在于后台");
				}
			} else {
				throw new Exception("工单ID不允许为空！");
			}
            
			List<YJWYProblemDetailsModel> list = problemDetailsService.save(new YJWYProblemDetailsModel[] { model });
			YJWYProblemRecordModel[] recordModels = new YJWYProblemRecordModel[list.size()];
			YJWYProblemRecordModel recordModel = null;
			/**
			 * 添加报事记录
			 */
			for (int i = 0; i < list.size(); i++) {
				// 实例化记录表
				recordModel = new YJWYProblemRecordModel();
				// 记录时间
				recordModel.setRecord_time(list.get(i).getCreate_time());
				// 当前记录人ID
				recordModel.setRecord_user_id(user_id);
				// 当前操作标记
				// 1转发，2处理，3完成，4关闭，5拒单，6下单,7取消，8重派 9编辑 10 重派单 11追记
				recordModel.setOperate_type(OperationExpress.ADD);
				// 操作时状态 1未处理，2处理中，3处理完成，4已关闭，5已拒单 ,6已取消,7已删除8待单中
				recordModel.setOperate_state(list.get(i).getState());
				recordModel.setFk_details_id(list.get(i).getPk_details_id());
				recordModels[i] = recordModel;
				data.put("orderId", list.get(i).getPk_details_id());
				node.put("data", data);
			}
			// 提交记录
			problemRecordService.save(recordModels);
			//返回未处理状态
			
			node.put("st", System.currentTimeMillis());
			node.put("code", 0);
			node.put("msg", "success");
			return JSONArray.toJSONString(node);
		} catch (Exception e) {
			node.put("st", System.currentTimeMillis());
			node.put("code", 1);
			node.put("msg", e.getMessage());
			node.put("data", "");
			return JSONArray.toJSONString(node);
		}
	}

	@Override
	public String eventClassHistoryAddressInfo(String user_id, String project_id) {
		JSONObject node = new JSONObject();
		try {
			if (!StringUtils.isEmpty(project_id)) {
				JSONObject data = new JSONObject();
				JdbcTemplate read = dao.getReadTemplate();
				//如果用户不为空，查询该用户历史数据
				if (!StringUtils.isEmpty(user_id)) {
					String sql = "select tab1.create_time,tab1.customer_name,tab1.customer_number,tab1.detailed_address,tab1.fk_house_address_id"+
					" from yjwy_problem_details tab1 "+
					" where tab1.create_user_id ='"+user_id+"' and tab1.fk_project_id='"+project_id+"' ORDER BY create_time DESC LIMIT 1";
					Map<String, Object> historyMap = new HashMap<String, Object>();
					try {
						historyMap = read.queryForMap(sql);
					} catch (Exception e) {
						//由于spring jdbctemplate 查询机制read.queryForMap()方法，
						//如果没查询到数据，会抛异常，所以，在查询时，抛异常。忽略异常，程序继续运行。
						//异常不做处理。
					}
					//历史地址
					data.put("addressId", historyMap.get("fk_house_address_id"));
					//历史报事人
					data.put("contact", historyMap.get("customer_name"));
					//历史详细地址
					data.put("location", historyMap.get("detailed_address"));
					//历史电话
					data.put("telephone", historyMap.get("customer_number"));
				}
				//根据项目ID查询项目名称
				Map<String, Object> projectMap = new HashMap<String, Object>();
				String projectSql = "select tab1.project_name_"+
						" from yjwy_pub_project tab1 "+
						" where tab1.pk_project_='"+project_id+"'";
				try {
					projectMap = read.queryForMap(projectSql);
				} catch (Exception e) {
					//由于spring jdbctemplate 查询机制read.queryForMap()方法，
					//如果没查询到数据，会抛异常，所以，在查询时，抛异常。忽略异常，程序继续运行。
					//异常不做处理。
				}
				//项目名称
				data.put("projectName", projectMap.get("project_name_"));
				//通过项目ID查询该项目绑定的子类，及三级目录
				ProblemClassModel[] models = this.queryClassByProject(project_id);
				data.put("serviceTypeList", models);
				node.put("data", data);
			}else{
				throw new Exception("项目ID不允许为空！");
			}
			node.put("st", System.currentTimeMillis());
			node.put("code", 0);
			node.put("msg", "success");
			return JSONArray.toJSONString(node);
		} catch (Exception e) {
			node.put("st", System.currentTimeMillis());
			node.put("code", 1);
			node.put("msg",  e.getMessage());
			node.put("data", "");
			return JSONArray.toJSONString(node);
		}
	}

	@Override
	public String eventList(String user_id, String project_id, String order_type, String record_type, int page_num,
			int per_size) {
		JSONObject node = new JSONObject();
		try {
			JdbcTemplate read = dao.getReadTemplate();
			String whrSql = " where 1=1 ";
			//用户
			if (!StringUtils.isEmpty(user_id)) {
				whrSql += " and tab1.create_user_id = '"+user_id+"' ";
			}else{
				throw new Exception("用户ID不允许为空！");
			}
			//状态
			if (!StringUtils.isEmpty(order_type)) {
				//未完成
				if (order_type.equals("1")) {
					whrSql += " and (tab1.state = 1 or tab1.state=2) ";
				}//待评价
				else if(order_type.equals("2")){
					whrSql += " and (tab1.state = 3 or tab1.state = 4) and tab1.yjq_evaluate = 0 ";
				}//已完成
				else if(order_type.equals("3")){
					whrSql += " and tab1.state >= 3 and tab1.yjq_evaluate = 1 ";
				}//已关闭，返回达美盛资产云后台关闭的所有
				else if(order_type.equals("4")){
					whrSql += " and tab1.state = 5 and tab1.yjq_cancel != 1  and tab1.record_type != '2' ";
				}
			}
			//类型分类（1：报事报修， 2：表彰， 3：投诉，4：表彰+投诉，5：全部）
			if (!StringUtils.isEmpty(record_type)) {
				if (record_type.equals("1")) {
					whrSql += " and tab1.record_type = '1' ";
				}else if(record_type.equals("2")){
					whrSql += " and tab1.record_type = '2' ";
				}else if(record_type.equals("3")){
					whrSql += " and tab1.record_type = '3' ";
				}else if(record_type.equals("4")){
					whrSql += " and (tab1.record_type = '2' or tab1.record_type = '3') ";
				}
			}
			if (!StringUtils.isEmpty(project_id)) {
				whrSql += " and tab1.fk_project_id = '"+project_id+"' ";
			}
			String sql =  "select tab1.pk_details_id,tab1.details_number,tab1.fk_project_id,tab1.details_content,"
						+" tab1.customer_name,tab1.customer_number,tab1.service_id,tab1.fk_house_address_id,tab1.yjq_cancel,"
						+" tab1.detailed_address,tab1.fk_duty_user_id,tab1.state,tab1.create_time,tab1.create_user_id,tab1.yjq_evaluate,"
						+" tab2.project_name_,tab4.class_name,tab3.user_name_ as duty_user_name,tab3.phone_ as duty_user_phone,tab5.user_name_ as create_user_name,"
						+" tab6.duty_user_id as worktask_duty_user_id,tab6.repair_user_id,tab7.user_name_ as repair_user_name,tab7.phone_ as repair_user_phone,"
						+" tab8.user_name_ as worktask_duty_user_name,tab8.phone_ as worktask_duty_user_phone"
						+" from ((((((yjwy_problem_details tab1 left join yjwy_pub_project tab2 on tab1.fk_project_id=tab2.pk_project_)"
						+" left join yjwy_pub_user tab3 on tab1.fk_duty_user_id=tab3.pk_user_)"
						+" left join yjwy_problem_class tab4 on tab1.service_id=tab4.pk_class_id)"
						+" left join yjwy_pub_user tab5 on tab1.create_user_id=tab5.pk_user_)"
						+" left join yjwy_worktask_details tab6 on tab1.fk_details_id = tab6.pk_details_id)"
						+" left join yjwy_pub_user tab7 on tab6.repair_user_id=tab7.pk_user_)"
						+" left join yjwy_pub_user tab8 on tab6.duty_user_id = tab8.pk_user_"
						+  whrSql
						+" order by tab1.create_time desc";
						//起始位置
			if (page_num!=0&&per_size!=0) {
				int start=page_num*per_size-per_size;
				sql += " limit "+start+","+per_size;
			}
			List<YJWYProblemDetailsModel> list = read.query(sql, new RowMapper<YJWYProblemDetailsModel>(){
				@Override
				public YJWYProblemDetailsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
					YJWYProblemDetailsModel model = new YJWYProblemDetailsModel();
					//报事ID
					model.setPk_details_id(rs.getString("pk_details_id"));
					//编号
					model.setDetails_number(rs.getString("details_number"));
					//项目ID
					model.setFk_project_id(rs.getString("fk_project_id"));
					//项目名称
					model.put("fk_project_name", rs.getString("project_name_"));
					//详情
					model.setDetails_content(rs.getString("details_content"));
					//客户姓名
					model.setCustomer_name(rs.getString("customer_name"));
					//客户电话
					model.setCustomer_number(rs.getString("customer_number"));
					//分类ID
					model.setFk_class_id(rs.getString("service_id"));
					//分类名称
					model.put("class_name", rs.getString("class_name"));
					//楼址ID
					model.setFk_house_address_id(rs.getString("fk_house_address_id"));
					//详细地址
					model.setDetailed_address(rs.getString("detailed_address"));
					//报事跟进人Id
					model.setFk_duty_user_id(rs.getString("fk_duty_user_id"));
					//报事跟进人姓名
					model.setDuty_user_name(rs.getString("duty_user_name"));
					//报事跟进人电话
					model.put("duty_user_phone",rs.getString("duty_user_phone"));
					//工单跟进人Id
					model.put("worktask_duty_user_id",rs.getString("worktask_duty_user_id"));
					//工单跟进人姓名
					model.put("worktask_duty_user_name",rs.getString("worktask_duty_user_name"));
					//工单跟进人电话
					model.put("worktask_duty_user_phone",rs.getString("worktask_duty_user_phone"));
					//维修人员Id
					model.put("repair_user_id", rs.getString("repair_user_id"));
					//维修人姓名
					model.put("repair_user_name", rs.getString("repair_user_name"));
					//维修人电话
					model.put("repair_user_phone", rs.getString("repair_user_phone"));
					//报事状态
					int state = rs.getInt("state");
					String stateName = "";
					if (state==1) {
						stateName = "未处理";
					}else if(state==2){
						stateName = "处理中";
					}else if(state==3){
						stateName = "完成";
					}else if(state==4){
						stateName = "已回访";
					}else if(state==5){
						//取消状态
						int yjq_cancel = rs.getInt("yjq_cancel");
						if (yjq_cancel==0) {
							stateName = "已关闭";
						}else if(yjq_cancel==1){
							stateName = "已取消";
						}
					}
					model.setState(state);
					model.put("stateName", stateName);
					//创建时间
					model.setCreate_time(rs.getString("create_time"));
					//创建人ID
					model.setCreate_user_id(rs.getString("create_user_id"));
					//创建人姓名
					model.put("create_user_name", rs.getString("create_user_name"));
					
					//评价状态
					int yjq_evaluate = rs.getInt("yjq_evaluate");
					String yjq_evaluate_name = "";
					if (yjq_evaluate==0) {
						yjq_evaluate_name = "未评价";
					}else if(yjq_evaluate==1){
						yjq_evaluate_name = "已评价";
					}
					model.setYjq_evaluate(yjq_evaluate);
					model.put("yjq_evaluate_name", yjq_evaluate_name);
					return model;
				}
			});
			YJWYProblemDetailsModel[] models = list.toArray(new YJWYProblemDetailsModel[]{});
			node.put("st", System.currentTimeMillis());
			node.put("code", 0);
			node.put("msg", "success");
			node.put("data", models);
			return JSONArray.toJSONString(node);
		} catch (Exception e) {
			node.put("st", System.currentTimeMillis());
			node.put("code", 1);
			node.put("msg", e.getMessage());
			node.put("data", "");
			return JSONArray.toJSONString(node);
		}
	}

	// 公共方法，获取当前时间
	public String obtainTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		return df.format(new Date());
	}
	
	/**
	 * 根据项目ID查询亿街区关联类型
	 * @param project_id
	 * @return
	 */
	public ProblemClassModel[] queryClassByProject(String project_id) {
		//合并返回集合
		if (StringUtils.isEmpty(project_id)) {
			return new ProblemClassModel[]{};
		}
		Query query = Query.from(YJWYProjectInfoClassNexusModel.META_ID);
		query.where(new Condition("project_id", QueryContents.TYPE_EQ, project_id));
		List<YJWYProjectInfoClassNexusModel> list = baseService.queryListByCondition(query);
		if (ArrayUtils.isEmpty(list)) {
			return new ProblemClassModel[]{};
		}
		List<Object> class_ids = ModelUtils.getModelFieldValues(list, "class_id");
//		Query query2 = Query.from(ProblemClassModel.META_ID);
//		query2.addSelect("pk_class_id","class_name","whether_repair","whether_visit","project_attribute","time_limit",
//				"sort","parent_id","state","create_time","create_user_id","update_time","update_user_id","pk_crop","class_code");
//		List<ProblemClassModel> list2 = service.queryListByCondition(query2);
//		List<ProblemClassModel> list2 = baseService.queryListByCondition(query2);
			String sql = "select pk_class_id, class_name, whether_repair, whether_visit,"
					+ " project_attribute, time_limit, sort, parent_id, state, create_time, "
					+ "create_user_id, update_time, update_user_id, pk_crop, class_code "
					+ "from yjwy_problem_class";
			JdbcTemplate read = dao.getReadTemplate();
			List<ProblemClassModel> list2 = read.query(sql, new RowMapper<ProblemClassModel>(){
				@Override
				public ProblemClassModel mapRow(ResultSet rs, int rowNum) throws SQLException {
					ProblemClassModel vo = new ProblemClassModel();
					vo.setPk_class_id(rs.getString("pk_class_id"));
					vo.setClass_name(rs.getString("class_name"));
					vo.setWhether_repair(rs.getInt("whether_repair"));
					vo.setWhether_visit(rs.getInt("whether_visit"));
					vo.setProject_attribute(rs.getString("project_attribute"));
					vo.setTime_limit(rs.getInt("time_limit"));
					vo.setSort(rs.getInt("sort"));
					vo.setParent_id(rs.getString("parent_id"));
					vo.setState(rs.getInt("state"));
					vo.setCreate_time(rs.getString("create_time"));
					vo.setCreate_user_id(rs.getString("create_user_id"));
					vo.setUpdate_time(rs.getString("update_time"));
					vo.setUpdate_user_id(rs.getString("update_user_id"));
					vo.setPk_crop(rs.getString("pk_crop"));
					vo.setClass_code(rs.getString("class_code"));
					return vo;
				}			
			});
		if (ArrayUtils.isEmpty(list2)) {
			return new ProblemClassModel[]{};
		}
		Set<ProblemClassModel> set = new HashSet<>();
		Set<String> twoSet = new HashSet<>();
		Set<String> oneSet = new HashSet<>();
		Set<ProblemClassModel> removeSet = new HashSet<>();
		String temp = null;
		for (ProblemClassModel problemClassModel : list2) {
			temp = problemClassModel.getPk_class_id();
			for (Object class_id : class_ids) {
				if (temp.equals(class_id)) {
					set.add(problemClassModel);
					twoSet.add(problemClassModel.getParent_id());
					removeSet.add(problemClassModel);
					break;
				}
			}
		}
		list2.removeAll(removeSet);
		removeSet.clear();
		// 查二级
		for (ProblemClassModel problemClassModel : list2) {
			temp = problemClassModel.getPk_class_id();
			for (Object class_id : twoSet) {
				if (temp.equals(class_id)) {
					set.add(problemClassModel);
					oneSet.add(problemClassModel.getParent_id());
					removeSet.add(problemClassModel);
					break;
				}
			}
		}
		list2.removeAll(removeSet);
		// 查一级
		for (ProblemClassModel problemClassModel : list2) {
			temp = problemClassModel.getPk_class_id();
			for (Object class_id : oneSet) {
				if (temp.equals(class_id)) {
					set.add(problemClassModel);
					break;
				}
			}
		}
		return set.toArray(new ProblemClassModel[] {});
	}

	
    @Override
	public void sendCloudpost(String Status, String Property_order_id, String pem_id) {
		String JoggleName = "OwnerEvent/Event_Update";
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("Status", Status);
		map.put("Property_order_id",Property_order_id);
		map.put("pem_id", pem_id);
		this.clientSendPost(JoggleName, map);
	}
	
	
	

}
