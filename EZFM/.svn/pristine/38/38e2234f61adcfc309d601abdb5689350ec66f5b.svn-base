package com.shareworx.ezfm.worktask.details.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.app.push.service.AppPushService;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectDomainService;
import com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsModel;
import com.shareworx.ezfm.problem.details.service.YJWYProblemDetailsDomainService;
import com.shareworx.ezfm.problem.file.model.ProblemFileModel;
import com.shareworx.ezfm.problem.file.service.ProblemFileBusinessService;
import com.shareworx.ezfm.problem.record.model.YJWYProblemRecordModel;
import com.shareworx.ezfm.problem.record.service.YJWYProblemRecordDomainService;
import com.shareworx.ezfm.pub.dictionary.OperationExpress;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.ezfm.webservice.cloudHttpClient.inner.cloudHttpService;
import com.shareworx.ezfm.webservice.eba.out.EbaWarningEliminateService;
import com.shareworx.ezfm.worktask.areadetails.fileUpload.FilesUpload;
import com.shareworx.ezfm.worktask.details.model.YJWYWorkTaskDetailsModel;
import com.shareworx.ezfm.worktask.details.vo.WorkTaskDetailsVo;
import com.shareworx.ezfm.worktask.details.vo.WrokTaskSubmitDetailsVo;
import com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordModel;
import com.shareworx.ezfm.worktask.record.service.YJWYWorkTaskDetailsRecordBusinessService;
import com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassModel;
import com.shareworx.ezfm.worktask.repairclass.service.YJWYWorkTaskRepairClassDomainService;
import com.shareworx.ezfm.worktask.sonclass.model.YJWYWorkTaskSonClassRecordModel;
import com.shareworx.ezfm.worktask.sonclass.service.YJWYWorkTaskSonClassRecordDomainService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.StringUtils;

/**
 * 工单详情表业务操作实现
 * 
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYWorkTaskDetailsBusinessService.ID)
public class YJWYWorkTaskDetailsBusinessServiceImpl implements YJWYWorkTaskDetailsBusinessService {

	@Autowired
	@Qualifier(YJWYWorkTaskDetailsDomainService.ID)
	private YJWYWorkTaskDetailsDomainService domainService;

	public void setDomainService(YJWYWorkTaskDetailsDomainService domainService) {
		this.domainService = domainService;
	}

	@Autowired
	@Qualifier(YJWYProjectDomainService.ID)
	private YJWYProjectDomainService projectService;

	public void setProjectService(YJWYProjectDomainService projectService) {
		this.projectService = projectService;
	}

	@Autowired
	@Qualifier(YJWYWorkTaskDetailsRecordBusinessService.ID)
	private YJWYWorkTaskDetailsRecordBusinessService detailsRecordService;

	public void setDetailsRecordService(YJWYWorkTaskDetailsRecordBusinessService detailsRecordService) {
		this.detailsRecordService = detailsRecordService;
	}

	@Autowired
	@Qualifier(YJWYProblemDetailsDomainService.ID)
	private YJWYProblemDetailsDomainService problemService;

	public void setProblemService(YJWYProblemDetailsDomainService problemService) {
		this.problemService = problemService;
	}

	@Autowired
	@Qualifier(AppPushService.ID)
	private AppPushService appPushService;

	public void setAppPushService(AppPushService appPushService) {
		this.appPushService = appPushService;
	}

	@Autowired
	@Qualifier(YJWYProblemRecordDomainService.ID)
	private YJWYProblemRecordDomainService problemRecordService;

	public void setProblemRecordService(YJWYProblemRecordDomainService problemRecordService) {
		this.problemRecordService = problemRecordService;
	}

	@Autowired
	@Qualifier(YJWYWorkTaskRepairClassDomainService.ID)
	private YJWYWorkTaskRepairClassDomainService repairClassService;

	public void setRepairClassService(YJWYWorkTaskRepairClassDomainService repairClassService) {
		this.repairClassService = repairClassService;
	}

	@Autowired
	@Qualifier(YJWYWorkTaskSonClassRecordDomainService.ID)
	private YJWYWorkTaskSonClassRecordDomainService sonClassService;

	public void setSonClassService(YJWYWorkTaskSonClassRecordDomainService sonClassService) {
		this.sonClassService = sonClassService;
	}

	@Autowired
	@Qualifier(ProblemFileBusinessService.ID)
	private ProblemFileBusinessService fileService;

	public void setFileService(ProblemFileBusinessService fileService) {
		this.fileService = fileService;
	}

	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;

	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	@Autowired
	@Qualifier(EbaWarningEliminateService.ID)
	private EbaWarningEliminateService ebaService;

	public void setEbaService(EbaWarningEliminateService ebaService) {
		this.ebaService = ebaService;
	}

	
	@Autowired
	@Qualifier(cloudHttpService.ID)
	private cloudHttpService cloudhttpservice; 
	
	
	
	public void setCloudhttpservice(cloudHttpService cloudhttpservice) {
		this.cloudhttpservice = cloudhttpservice;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.worktask.details.service.
	 * YJWYWorkTaskDetailsBusinessService#query(com.shareworx.platform.persist.
	 * Query)
	 */
	@Override
	public YJWYWorkTaskDetailsModel[] query(Query query) throws ShareworxServiceException {
		JdbcTemplate read = dao.getReadTemplate();
		String sql = "select tab1.pk_details_id,tab1.datails_code,tab1.create_time,tab1.repair_user,"
				+ "tab1.task_state,tab1.update_time,tab2.project_name_,tab3.user_name_ from "
				+ "(yjwy_worktask_details tab1 left join yjwy_pub_project tab2 "
				+ "on tab1.fk_project_id=tab2.pk_project_) left join yjwy_pub_user "
				+ "tab3 on tab1.duty_user_id=tab3.pk_user_";
		if (query.getOrList().size() > 0) {
			sql += " where ";
			for (int i = 0; i < query.getOrList().size(); i++) {
				if (i == 0) {
					sql += " " + query.getOrList().get(i).getKey() + " =" + query.getOrList().get(i).getValue();
				}
				sql += " or " + query.getOrList().get(i).getKey() + " =" + query.getOrList().get(i).getValue();
			}
		}
		sql += " limit " + query.getStart() + "," + query.getLimit();
		List<YJWYWorkTaskDetailsModel> list = read.query(sql, new RowMapper<YJWYWorkTaskDetailsModel>() {
			@Override
			public YJWYWorkTaskDetailsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				YJWYWorkTaskDetailsModel model = new YJWYWorkTaskDetailsModel();
				model.setPk_details_id(rs.getString("pk_details_id"));
				model.setDatails_code(rs.getString("datails_code"));
				model.setCreate_time(rs.getString("create_time"));
				model.setRepair_user(rs.getString("repair_user"));
				model.setTask_state(rs.getInt("task_state"));
				model.setUpdate_time(rs.getString("update_time"));
				model.put("project_name", rs.getString("project_name_"));
				model.put("user_name", rs.getString("user_name_"));
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
				return model;
			}
		});

		// List<YJWYWorkTaskDetailsModel> list =
		// domainService.queryListByCondition(query);
		// for (YJWYWorkTaskDetailsModel model:list) {
		//
		// }
		if (ArrayUtils.isEmpty(list)) {
			return new YJWYWorkTaskDetailsModel[0];
		}
		return list.toArray(new YJWYWorkTaskDetailsModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.worktask.details.service.
	 * YJWYWorkTaskDetailsBusinessService#query(com.shareworx.platform.persist.
	 * Query)
	 */
	@Override
	public YJWYWorkTaskDetailsModel[] queryModels(WorkTaskDetailsVo queryvo, String whrSql)
			throws ShareworxServiceException {
		JdbcTemplate read = dao.getReadTemplate();
		// 访问表示符：1：待办任务；2：全部任务；3：处理中；4：已完成待回访；5：已回访
		String sql = "select tab1.pk_details_id,tab1.datails_code,tab1.create_time,tab1.repair_user,"
				+ "tab1.task_state,tab1.update_time,tab2.project_name_,tab3.user_name_ from "
				+ "(yjwy_worktask_details tab1 left join yjwy_pub_project tab2 "
				+ "on tab1.fk_project_id=tab2.pk_project_) left join yjwy_pub_user "
				+ "tab3 on tab1.duty_user_id=tab3.pk_user_";
		sql += whrSql + " ORDER BY tab1.update_time DESC";
		sql += " limit " + queryvo.getStart() + "," + queryvo.getLimit();
		List<YJWYWorkTaskDetailsModel> list = read.query(sql, new RowMapper<YJWYWorkTaskDetailsModel>() {
			@Override
			public YJWYWorkTaskDetailsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				YJWYWorkTaskDetailsModel model = new YJWYWorkTaskDetailsModel();
				model.setPk_details_id(rs.getString("pk_details_id"));
				model.setDatails_code(rs.getString("datails_code"));
				model.setCreate_time(rs.getString("create_time"));
				model.setRepair_user(rs.getString("repair_user"));
				model.setTask_state(rs.getInt("task_state"));
				model.put("update_time_string",
						this.timeStamp2Date(rs.getString("update_time"), "yyyy-MM-dd HH:mm:ss"));
				model.setUpdate_time(rs.getString("update_time"));
				model.put("project_name", rs.getString("project_name_"));
				model.put("user_name", rs.getString("user_name_"));
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
				} else if (rs.getInt("task_state") == 6) {
					model.put("state_name", "已关闭");
				}
				return model;
			}

			/**
			 * 时间戳转换成日期格式字符串
			 * 
			 * @param seconds
			 *            精确到秒的字符串
			 * @param formatStr
			 * @return
			 */
			public String timeStamp2Date(String seconds, String format) {
				if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
					return "";
				}
				if (format == null || format.isEmpty())
					format = "yyyy-MM-dd HH:mm:ss";
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				return sdf.format(new Date(Long.valueOf(seconds)));
			}
		});
		if (ArrayUtils.isEmpty(list)) {
			return new YJWYWorkTaskDetailsModel[0];
		}
		return list.toArray(new YJWYWorkTaskDetailsModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.worktask.details.service.
	 * YJWYWorkTaskDetailsBusinessService#load(com.shareworx.ezfm.worktask.
	 * details.model.YJWYWorkTaskDetailsModel)
	 */
	@Override
	public YJWYWorkTaskDetailsModel[] load(YJWYWorkTaskDetailsModel model) throws ShareworxServiceException {
		List<YJWYWorkTaskDetailsModel> list = domainService.queryByExample(model);
		if (ArrayUtils.isEmpty(list)) {
			return new YJWYWorkTaskDetailsModel[0];
		}
		return list.toArray(new YJWYWorkTaskDetailsModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.worktask.details.service.
	 * YJWYWorkTaskDetailsBusinessService#save(com.shareworx.ezfm.worktask.
	 * details.model.YJWYWorkTaskDetailsModel[])
	 */
	@Override
	public YJWYWorkTaskDetailsModel[] saveCallPolice(YJWYWorkTaskDetailsModel[] models)
			throws ShareworxServiceException {
		// 获取当前时间
		String time = this.obtainTime("yyyy-MM-dd HH:mm:ss");
		String code = "";
		for (YJWYWorkTaskDetailsModel model : models) {
			// 根据维修种类编码，查询维修种类
			if (!StringUtils.isEmpty(model.getRepair_class_id())) {
				Query query = Query.from(YJWYWorkTaskRepairClassModel.META_ID);
				query.and(new Condition("class_code", QueryContents.TYPE_EQ, model.getRepair_class_id()));
				List<YJWYWorkTaskRepairClassModel> classModelList = repairClassService.queryListByCondition(query);
				if (classModelList != null && classModelList.size() != 0) {
					model.setRepair_class_id(classModelList.get(0).getPk_class_id());
				} else {
					throw new ShareworxServiceException("未查询到当前编码信息！");
				}
			} else {
				throw new ShareworxServiceException("维修种类编码不允许为空！");
			}
			// 生成工单单号
			code = domainService.getTaskCode(model.get("fk_project_id").toString());
			// 填充工单单号
			model.setDatails_code(code);
			// 填充创建时间
			model.setCreate_time(time);
			// 填充最后修改时间戳
			model.setUpdate_time(System.currentTimeMillis() + "");
			model.setWorktask_type("taskSource6");
			// 状态置为0：未派单
			model.setTask_state(0);
		}

		List<YJWYWorkTaskDetailsModel> list = domainService.save(models);
		// 添加工单记录
		for (YJWYWorkTaskDetailsModel model : list) {
			// 新增工单记录
			YJWYWorkTaskDetailsRecordModel recordModel = this.fillDetailsRecord(model.getPk_details_id(),
					OperationExpress.ADD, model.getTask_state(), "", time, "");
			detailsRecordService.save(new YJWYWorkTaskDetailsRecordModel[] { recordModel });
			// 新增报事详情
			YJWYProblemDetailsModel problemDetailsModel = this.fillProblemDetails(model);
			List<YJWYProblemDetailsModel> problemDetailsList = problemService
					.save(new YJWYProblemDetailsModel[] { problemDetailsModel });
			// 报事记录
			for (YJWYProblemDetailsModel problemModel : problemDetailsList) {
				YJWYProblemRecordModel problemRecordModel = fillProblemRecord(problemModel.getPk_details_id(),
						OperationExpress.ADD, 2, time, "");
				problemRecordService.save(new YJWYProblemRecordModel[] { problemRecordModel });
			}
		}
		return list.toArray(new YJWYWorkTaskDetailsModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.worktask.details.service.
	 * YJWYWorkTaskDetailsBusinessService#save(com.shareworx.ezfm.worktask.
	 * details.model.YJWYWorkTaskDetailsModel[])
	 */
	@Override
	public YJWYWorkTaskDetailsModel[] save(YJWYWorkTaskDetailsModel[] models) throws ShareworxServiceException {
		// 获取当前时间
		String time = this.obtainTime("yyyy-MM-dd HH:mm:ss");
		String code = "";
		for (YJWYWorkTaskDetailsModel model : models) {
			// 生成工单单号
			code = domainService.getTaskCode(model.get("fk_project_id").toString());
			// 填充工单单号
			model.setDatails_code(code);
			// 填充创建时间
			model.setCreate_time(time);
			// 填充创建人
			model.setCreate_user_id(UserUtil.getCurrentUserPk());
			// 填充最后修改时间戳
			model.setUpdate_time(System.currentTimeMillis() + "");
			// 填充最后修改人
			model.setUpdate_user_id(UserUtil.getCurrentUserPk());
			model.setWorktask_type("taskSource1");
			// 判断是否选跟进人
			if (model.get("duty_user_id") != null && !StringUtils.isEmpty(model.get("duty_user_id").toString())) {
				// 判断跟进人类型，duty_user_type值：1：接口人；2：维修人
				if (model.get("duty_user_type") != null && model.get("duty_user_type").equals("1")) {
					/**
					 * 选择接口人员，业务处理待确定
					 */
				} else {
					// 状态置为1：待接单
					model.setTask_state(1);
				}
			} else {
				// 状态置为0：未派单
				model.setTask_state(0);
			}
		}

		List<YJWYWorkTaskDetailsModel> list = domainService.save(models);
		// 添加工单记录
		for (YJWYWorkTaskDetailsModel model : list) {
			// 新增工单记录
			YJWYWorkTaskDetailsRecordModel recordModel = this.fillDetailsRecord(model.getPk_details_id(),
					OperationExpress.ADD, model.getTask_state(), "", time, UserUtil.getCurrentUserPk());
			detailsRecordService.save(new YJWYWorkTaskDetailsRecordModel[] { recordModel });
			// 新增报事详情
			YJWYProblemDetailsModel problemDetailsModel = this.fillProblemDetails(model);
			List<YJWYProblemDetailsModel> problemDetailsList = problemService
					.save(new YJWYProblemDetailsModel[] { problemDetailsModel });
			// 报事记录
			for (YJWYProblemDetailsModel problemModel : problemDetailsList) {
				YJWYProblemRecordModel problemRecordModel = fillProblemRecord(problemModel.getPk_details_id(),
						OperationExpress.ADD, 2, time, UserUtil.getCurrentUserPk());
				problemRecordService.save(new YJWYProblemRecordModel[] { problemRecordModel });
			}
		}
		return list.toArray(new YJWYWorkTaskDetailsModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.worktask.details.service.
	 * YJWYWorkTaskDetailsBusinessService#save(com.shareworx.ezfm.worktask.
	 * details.model.YJWYWorkTaskDetailsModel[])
	 */
	@Override
	public YJWYWorkTaskDetailsModel[] saveDelegate(WrokTaskSubmitDetailsVo vo) throws ShareworxServiceException {
		YJWYWorkTaskDetailsModel updateModel = new YJWYWorkTaskDetailsModel();
		String recordRemarks = "";
		String time = this.obtainTime("yyyy-MM-dd HH:mm:ss");
		if (null != vo) {
			updateModel = domainService.queryById(vo.getPk_details_id());
			// 填充最后修改时间戳
			updateModel.setUpdate_time(System.currentTimeMillis() + "");
			// 填充最后修改人
			updateModel.setUpdate_user_id(UserUtil.getCurrentUserPk());
			// 设置维修内容
			updateModel.setRepair_content(vo.getRepair_content());
			// 设置详细地址
			updateModel.setRepair_details(vo.getRepair_details());
			// 设置维修类型
			updateModel.setRepair_class_id(vo.getRepair_class_id());
			// 更改跟进人
			updateModel.setDuty_user_id(vo.getDuty_user_id());
			// 填充协助人
			updateModel.setHlop_user_id(vo.getHlop_user_id());
			// 设置派单时间
			updateModel.setDispatch_time(time);
			// 接单类型置为2：派单
			updateModel.setDispatch_type(2);
			// 工单状态
			updateModel.setTask_state(1);
			recordRemarks = vo.getRecord_remarks();
			// 判断是否选跟进人
			if (StringUtils.isEmpty(vo.getDuty_user_id())) {
				// 判断跟进人类型，duty_user_type值：1：接口人；2：维修人
				if (!StringUtils.isEmpty(vo.getDuty_user_type()) && vo.getDuty_user_type().equals("1")) {
					/**
					 * 选择接口人员，业务处理待确定
					 */
				}
			}
		}
		// 提交保存完成
		List<YJWYWorkTaskDetailsModel> list = domainService.update(updateModel);
		// 工单推送
		appPushService.sendWorkTaskPush(updateModel);
		// 2017-1-11 kim start
		// 根据工单id查找出problemdetails表的workstaskid
		Query query = Query.from(YJWYProblemDetailsModel.META_ID);
		query.where(new Condition("fk_details_id", QueryContents.TYPE_EQ, vo.getPk_details_id()));
		YJWYProblemDetailsModel problemModel = problemService.queryOneByCondition(query);
		if(problemModel!=null){
			// ycq_worktaskid 不为空,证明此记录是ycq后台上报的记录
			String worktask_id = problemModel.getYcq_worktaskid();
			if (!StringUtils.isEmpty(worktask_id)) {
				// 推送"已分发状态"--“assigned”
				String worktaskid = worktask_id;
				String ycq_status = "assigned";
				String pem_id = updateModel.getDuty_user_id();
				cloudhttpservice.sendCloudpost(ycq_status, worktaskid,pem_id);
			}
		}
		// 2017-1-11 kim end
		// 添加工单记录
		for (YJWYWorkTaskDetailsModel model : list) {
			// 新增工单记录
			YJWYWorkTaskDetailsRecordModel recordModel = this.fillDetailsRecord(model.getPk_details_id(),
					OperationExpress.SEND, model.getTask_state(), recordRemarks, time, UserUtil.getCurrentUserPk());
			YJWYWorkTaskDetailsRecordModel[] recordModels = detailsRecordService
					.save(new YJWYWorkTaskDetailsRecordModel[] { recordModel });
			// 上传附件
			if (recordModels != null && recordModels.length > 0 && !vo.getWroktask_file().isEmpty()) {
				FilesUpload fileUpload = new FilesUpload();
				// 保存到OSS
				ProblemFileModel file = fileUpload.upload(vo.getWroktask_file(), YJWYWorkTaskDetailsRecordModel.META_ID,
						recordModels[0].getPk_record_id(), time, UserUtil.getCurrentUser().getPk_crop());
				// 保存图片信息
				fileService.save(new ProblemFileModel[] { file });
			}
		}
		return list.toArray(new YJWYWorkTaskDetailsModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.worktask.details.service.
	 * YJWYWorkTaskDetailsBusinessService#save(com.shareworx.ezfm.worktask.
	 * details.model.YJWYWorkTaskDetailsModel[])
	 */
	@Override
	public YJWYWorkTaskDetailsModel[] saveOrdersOrRefuse(WrokTaskSubmitDetailsVo vo) throws ShareworxServiceException {
		YJWYWorkTaskDetailsModel updateModel = new YJWYWorkTaskDetailsModel();
		String recordRemarks = "";
		String operationExpress = "";
		String time = this.obtainTime("yyyy-MM-dd HH:mm:ss");
		if (null != vo) {
			updateModel = domainService.queryById(vo.getPk_details_id());
			// 填充最后修改时间戳
			updateModel.setUpdate_time(System.currentTimeMillis() + "");
			// 填充最后修改人
			updateModel.setUpdate_user_id(UserUtil.getCurrentUserPk());
			// 判断操作类型，1:接单；2:拒单;3:取消
			if (!StringUtils.isEmpty(vo.getOperation_type())) {
				if (vo.getOperation_type().equals("1")) {
					// 设置工单状态为2：维修中
					updateModel.setTask_state(2);
					// 设置维修人：将跟进人赋值给维修人；
					updateModel.setRepair_user_id(updateModel.getDuty_user_id());
					// 设置接单时间
					updateModel.setOrders_time(time);
					// 操作表示符【接单】
					operationExpress = OperationExpress.ACCEPT;
				} else if (vo.getOperation_type().equals("2")) {
					// 判断派单类型：1:抢单；2:派单；
					if (updateModel.getDispatch_type() != 0) {
						if (updateModel.getDispatch_type() == 1) {
							// 工单状态设置为0:未派单
							updateModel.setTask_state(0);
						} else if (updateModel.getDispatch_type() == 2) {
							// 工单状态设置为4:已拒单
							updateModel.setTask_state(4);
						}
					}
					// 操作表示符【拒单】
					operationExpress = OperationExpress.REFUSE;
				} else if (vo.getOperation_type().equals("3")) {
					// 工单状态设置为5:已取消
					updateModel.setTask_state(5);
					// 设置取消时间
					updateModel.setCancel_time(time);
					// 操作表示符【取消】
					operationExpress = OperationExpress.CANCEL;
					// 工单取消，报事不做任何处理
					/*
					 * //工单取消，将报事工单设置为1：未处理； Query query =
					 * Query.from(YJWYProblemDetailsModel.META_ID);
					 * query.and(Condition.create("fk_details_id",
					 * updateModel.getPk_details_id())); YJWYProblemDetailsModel
					 * problemModel = problemService.queryOneByCondition(query);
					 * //报事状态设置为1 if (problemModel!=null) {
					 * problemModel.setState(1);
					 * problemService.update(problemModel); } //添加报事记录
					 * YJWYProblemRecordModel problemRecordModel =
					 * fillProblemRecord(problemModel.getPk_details_id(),
					 * OperationExpress.CANCEL,
					 * problemModel.getState(),time,UserUtil.getCurrentUserPk())
					 * ; problemRecordService.save(new
					 * YJWYProblemRecordModel[]{problemRecordModel});
					 */
				}
			}
			recordRemarks = vo.getRecord_remarks();
		}
		// 2017-1-11 kim start
		// 根据工单id查找出problemdetails表的workstaskid
		Query query = Query.from(YJWYProblemDetailsModel.META_ID);
		query.where(new Condition("fk_details_id", QueryContents.TYPE_EQ, vo.getPk_details_id()));
		YJWYProblemDetailsModel problemModel = problemService.queryOneByCondition(query);
		if(problemModel!=null){
			// ycq_worktaskid 不为空,证明此记录是ycq后台上报的记录
			String worktask_id = problemModel.getYcq_worktaskid();
			if (!StringUtils.isEmpty(worktask_id)) {
				String worktaskid = "";
				String ycq_status = "";
				String pem_id= updateModel.getDuty_user_id();
				if(vo.getOperation_type().equals("1")){
					 //接单
					// 推送"处理中"--“processing”
					 worktaskid = worktask_id;
					 ycq_status = "answered";
				}else if(vo.getOperation_type().equals("2")){
					//拒单
					 worktaskid = worktask_id;
					 ycq_status = "backed";
				}else if(vo.getOperation_type().equals("3")){
					//取消 backed
					 worktaskid = worktask_id;
					 ycq_status = "backed";
				}
				cloudhttpservice.sendCloudpost(ycq_status, worktaskid, pem_id);
			}
		}
		// 2017-1-11 kim end
		// 添加工单记录
		List<YJWYWorkTaskDetailsModel> list = domainService.update(updateModel);
		// 添加工单记录
		for (YJWYWorkTaskDetailsModel model : list) {
			// 新增工单记录
			YJWYWorkTaskDetailsRecordModel recordModel = this.fillDetailsRecord(model.getPk_details_id(),
					operationExpress, model.getTask_state(), recordRemarks, time, UserUtil.getCurrentUserPk());
			YJWYWorkTaskDetailsRecordModel[] recordModels = detailsRecordService
					.save(new YJWYWorkTaskDetailsRecordModel[] { recordModel });
			// 上传附件
			if (recordModels != null && recordModels.length > 0 && !vo.getWroktask_file().isEmpty()) {
				FilesUpload fileUpload = new FilesUpload();
				// 保存到OSS
				ProblemFileModel file = fileUpload.upload(vo.getWroktask_file(), YJWYWorkTaskDetailsRecordModel.META_ID,
						recordModels[0].getPk_record_id(), time, UserUtil.getCurrentUser().getPk_crop());
				// 保存图片信息
				fileService.save(new ProblemFileModel[] { file });
			}
		}
		return list.toArray(new YJWYWorkTaskDetailsModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.worktask.details.service.
	 * YJWYWorkTaskDetailsBusinessService#save(com.shareworx.ezfm.worktask.
	 * details.model.YJWYWorkTaskDetailsModel[])
	 */
	@Override
	public YJWYWorkTaskDetailsModel[] saveClose(String detailsId) throws ShareworxServiceException {
		YJWYWorkTaskDetailsModel updateModel = new YJWYWorkTaskDetailsModel();
		// 详情
		String recordRemarks = "";
		// 操作表示符
		String operationExpress = "";
		String time = this.obtainTime("yyyy-MM-dd HH:mm:ss");
		List<YJWYWorkTaskDetailsModel> list = new ArrayList<YJWYWorkTaskDetailsModel>();
		if (!StringUtils.isEmpty(detailsId)) {
			updateModel = domainService.queryById(detailsId);
			// 填充最后修改时间戳
			updateModel.setUpdate_time(System.currentTimeMillis() + "");
			// 填充最后修改人
			updateModel.setUpdate_user_id(UserUtil.getCurrentUserPk());
			// 工单状态设置为6:已关闭
			updateModel.setTask_state(6);
			// 设置取消时间
			updateModel.setCancel_time(time);
			// 操作表示符【关闭】
			operationExpress = OperationExpress.CLOSE;
			// 工单取消，将报事工单设置为5：已关闭；
			Query query = Query.from(YJWYProblemDetailsModel.META_ID);
			query.and(Condition.create("fk_details_id", updateModel.getPk_details_id()));
			YJWYProblemDetailsModel problemModel = problemService.queryOneByCondition(query);
			// 添加工单记录 
			// 报事更改
			if (problemModel != null) {
				problemModel.setState(5);
				problemService.update(problemModel);
				// 添加报事记录
				YJWYProblemRecordModel problemRecordModel = fillProblemRecord(problemModel.getPk_details_id(),
						OperationExpress.CLOSE, problemModel.getState(), time, UserUtil.getCurrentUserPk());
				problemRecordService.save(new YJWYProblemRecordModel[] { problemRecordModel });
				
				// 2017-1-11 kim start
				// 根据工单id查找出problemdetails表的workstaskid
				String worktask_id = problemModel.getYcq_worktaskid();
				// ycq_worktaskid 不为空,证明此记录是ycq后台上报的记录
				if (!StringUtils.isEmpty(worktask_id)) {
					// 推送"已废弃状态"--“abandoned”
					String worktaskid = worktask_id;
					String ycq_status = "abandoned";
					String pem_id = updateModel.getDuty_user_id();
					cloudhttpservice.sendCloudpost(ycq_status, worktaskid, pem_id);
				}
				// 2017-1-11 kim end
			}
			list = domainService.update(updateModel);
			// 添加工单记录
			for (YJWYWorkTaskDetailsModel model : list) {
				// 新增工单记录
				YJWYWorkTaskDetailsRecordModel recordModel = this.fillDetailsRecord(model.getPk_details_id(),
						operationExpress, model.getTask_state(), recordRemarks, time, UserUtil.getCurrentUserPk());
				detailsRecordService.save(new YJWYWorkTaskDetailsRecordModel[] { recordModel });
			}
		}
		// EBA预警取消，判断是否为EBA预警工单。
		if (!StringUtils.isEmpty(updateModel.getEba_report_id())) {
			ebaService.EbaWarningEliminate(updateModel.getEba_report_id());
		}
		return list.toArray(new YJWYWorkTaskDetailsModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.worktask.details.service.
	 * YJWYWorkTaskDetailsBusinessService#save(com.shareworx.ezfm.worktask.
	 * details.model.YJWYWorkTaskDetailsModel[])
	 */
	@Override
	public YJWYWorkTaskDetailsModel[] saveComplete(WrokTaskSubmitDetailsVo vo) throws ShareworxServiceException {
		YJWYWorkTaskDetailsModel updateModel = new YJWYWorkTaskDetailsModel();
		String recordRemarks = "";
		String time = this.obtainTime("yyyy-MM-dd HH:mm:ss");
		BigDecimal cost = new BigDecimal(0.0);
		if (null != vo) {
			updateModel = domainService.queryById(vo.getPk_details_id());
			// 填充最后修改时间戳
			updateModel.setUpdate_time(System.currentTimeMillis() + "");
			// 填充最后修改人
			updateModel.setUpdate_user_id(UserUtil.getCurrentUserPk());
			// 设置工作数量
			if (vo.getWork_number() != null) {
				updateModel.setWork_number(vo.getWork_number());
			}
			// 设置材料费
			if (vo.getMaterial_cost() != null) {
				updateModel.setMaterial_cost(vo.getMaterial_cost());
			}
			// 设置人工费
			if (vo.getArtificial_cost() != null) {
				updateModel.setArtificial_cost(vo.getArtificial_cost());
			}
			// 求费用合计
			cost = updateModel.getMaterial_cost().add(updateModel.getArtificial_cost());
			// 设置费用合计
			updateModel.setCount_cost(cost);
			// 设置完成时间
			updateModel.setFinish_time(time);
			// 工单状态设置为3：已完成
			updateModel.setTask_state(3);
			recordRemarks = vo.getRecord_remarks();
			// 设置维修种类关联；
			// 查询详情，为设置二级分类，主要针对报表
			List<Object> classidLiset = new ArrayList<Object>();
			classidLiset = vo.getClassids();
			JdbcTemplate read = dao.getReadTemplate();
			String sql = "select pk_class_id,parent_id from yjwy_worktask_repair_class";
			if (classidLiset.size() > 0) {
				sql += " where pk_class_id in(";
				for (int i = 0; i < classidLiset.size(); i++) {
					if (i == classidLiset.size() - 1) {
						sql += "'" + classidLiset.get(i) + "'";
					} else {
						sql += "'" + classidLiset.get(i) + "',";
					}
				}
				sql += ")";
			}
			List<YJWYWorkTaskRepairClassModel> list = read.query(sql, new RowMapper<YJWYWorkTaskRepairClassModel>() {
				@Override
				public YJWYWorkTaskRepairClassModel mapRow(ResultSet rs, int rowNum) throws SQLException {
					YJWYWorkTaskRepairClassModel model = new YJWYWorkTaskRepairClassModel();
					model.setPk_class_id(rs.getString("pk_class_id"));
					model.setParent_id(rs.getString("parent_id"));
					return model;
				}
			});

			// 额定工时
			Integer ration_duration = new Integer(0);
			// 实际工时
			Integer finished_duration = new Integer(0);
			if (list.size() > 0) {
				YJWYWorkTaskSonClassRecordModel sonClassModel;
				YJWYWorkTaskSonClassRecordModel[] sonClassModels = new YJWYWorkTaskSonClassRecordModel[classidLiset
						.size()];
				for (int j = 0; j < list.size(); j++) {
					// 获取分类对象
					YJWYWorkTaskRepairClassModel repairClassmodel = repairClassService
							.queryById(list.get(j).getPk_class_id());
					// 循环加额定工时
					ration_duration = repairClassmodel.getRated_worktime() + ration_duration;
					// 循环加实际工时
					if (updateModel.getWork_number() == 0 || updateModel.getWork_number() == 1) {
						finished_duration = ration_duration;
					} else {
						finished_duration = finished_duration
								+ updateModel.getWork_number() * repairClassmodel.getRated_worktime();
					}

					sonClassModel = new YJWYWorkTaskSonClassRecordModel();
					// 设置工单ID
					sonClassModel.setFk_details_id(updateModel.getPk_details_id());
					// 设置分类ID
					sonClassModel.setFk_class_id(list.get(j).getPk_class_id());
					// 设置二级分类ID
					sonClassModel.setFk_two_levelclass_id(list.get(j).getParent_id());
					// 这种工作数量
					sonClassModel.setNumber(updateModel.getWork_number());
					// 设置记录时间
					sonClassModel.setRecord_time(time);
					// 设置记录人
					sonClassModel.setRecord_user_id(UserUtil.getCurrentUserPk());
					sonClassModels[j] = sonClassModel;
				}
				// 设置额定工时
				updateModel.setRation_duration(ration_duration + "");
				// 设置实际工时
				updateModel.setFinished_duration(finished_duration + "");
				sonClassService.save(sonClassModels);
			}

		}
		List<YJWYWorkTaskDetailsModel> list = domainService.update(updateModel);
		// 添加工单记录
		for (YJWYWorkTaskDetailsModel model : list) {
			// 新增工单记录
			YJWYWorkTaskDetailsRecordModel recordModel = this.fillDetailsRecord(model.getPk_details_id(),
					OperationExpress.COMPLETE, model.getTask_state(), recordRemarks, time, UserUtil.getCurrentUserPk());
			recordModel.setTask_cost(cost);
			recordModel.setTask_cost(cost);
			YJWYWorkTaskDetailsRecordModel[] recordModels = detailsRecordService
					.save(new YJWYWorkTaskDetailsRecordModel[] { recordModel });
			// 上传附件
			if (recordModels != null && recordModels.length > 0 && !vo.getWroktask_file().isEmpty()) {
				FilesUpload fileUpload = new FilesUpload();
				// 保存到OSS
				ProblemFileModel file = fileUpload.upload(vo.getWroktask_file(), YJWYWorkTaskDetailsRecordModel.META_ID,
						recordModels[0].getPk_record_id(), time, UserUtil.getCurrentUser().getPk_crop());
				// 保存图片信息
				fileService.save(new ProblemFileModel[] { file });
			}
			// 报事状态调整
			Query query = Query.from(YJWYProblemDetailsModel.META_ID);
			query.and(Condition.create("fk_details_id", model.getPk_details_id()));
			List<YJWYProblemDetailsModel> problemList = problemService.queryListByCondition(query);
			for (YJWYProblemDetailsModel problemModel : problemList) {
				// 2017-1-11 kim start
				if(problemModel!=null){
					// ycq_worktaskid 不为空,证明此记录是ycq后台上报的记录
					String worktask_id = problemModel.getYcq_worktaskid();
					if (!StringUtils.isEmpty(worktask_id)) {
						// 推送"完成状态"--“completed”
						String worktaskid = worktask_id;
						String ycq_status = "completed";
						String pem_id = updateModel.getDuty_user_id();
						cloudhttpservice.sendCloudpost(ycq_status, worktaskid, pem_id);
					}
				}
				// 2017-1-11 kim end
				// 将关联报事信息状态设置为3：完成待回访；
				problemModel.setState(3);
				problemService.update(problemModel);
				// 报事记录
				YJWYProblemRecordModel problemRecordModel = fillProblemRecord(problemModel.getPk_details_id(),
						OperationExpress.COMPLETE, problemModel.getState(), time, UserUtil.getCurrentUserPk());
				problemRecordService.save(new YJWYProblemRecordModel[] { problemRecordModel });
			}
		}
		// EBA预警取消，判断是否为EBA预警工单。
		if (!StringUtils.isEmpty(updateModel.getEba_report_id())) {
			ebaService.EbaWarningEliminate(updateModel.getEba_report_id());
		}
		return list.toArray(new YJWYWorkTaskDetailsModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.worktask.details.service.
	 * YJWYWorkTaskDetailsBusinessService#update(com.shareworx.ezfm.worktask.
	 * details.model.YJWYWorkTaskDetailsModel[])
	 */
	@Override
	public YJWYWorkTaskDetailsModel[] update(YJWYWorkTaskDetailsModel[] models) throws ShareworxServiceException {
		List<YJWYWorkTaskDetailsModel> list = domainService.update(models);
		return list.toArray(new YJWYWorkTaskDetailsModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.worktask.details.service.
	 * YJWYWorkTaskDetailsBusinessService#delete(com.shareworx.ezfm.worktask.
	 * details.model.YJWYWorkTaskDetailsModel[])
	 */
	@Override
	public YJWYWorkTaskDetailsModel[] delete(YJWYWorkTaskDetailsModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

	/**
	 * 
	 * 添加工单记录
	 * 
	 * @param detailsId
	 *            工单ID
	 * @param operationExpress
	 *            操作表示符
	 * @param taskState
	 *            工单流转状态
	 * @param recordRemarks
	 *            备注详情
	 * @return
	 */
	public YJWYWorkTaskDetailsRecordModel fillDetailsRecord(String details_id, String operationExpress, int taskState,
			String recordRemarks, String time, String user_id) {
		YJWYWorkTaskDetailsRecordModel recordModel = new YJWYWorkTaskDetailsRecordModel();
		recordModel.setFk_details_id(details_id);
		recordModel.setOperation_express(operationExpress);
		recordModel.setTask_state(taskState);
		recordModel.setOperation_time(time);
		recordModel.setOperation_user_id(user_id);
		if (!StringUtils.isEmpty(recordRemarks)) {
			recordModel.setOperation_remarks(recordRemarks);
		}
		return recordModel;
	}

	/**
	 * 根据工单添加报事详情
	 * 
	 * @param repairModel
	 *            工单详情
	 * @return 报事详情
	 */
	public YJWYProblemDetailsModel fillProblemDetails(YJWYWorkTaskDetailsModel repairModel) {
		YJWYProblemDetailsModel model = new YJWYProblemDetailsModel();
		model.setFk_details_id(repairModel.getPk_details_id());
		model.setCustomer_name(repairModel.getRepair_user());
		model.setCustomer_number(repairModel.getContact_number());
		model.setCreate_time(repairModel.getCreate_time());
		model.setCreate_user_id(repairModel.getCreate_user_id());
		model.setUpdate_time(repairModel.getUpdate_time());
		model.setUpdate_user_id(repairModel.getUpdate_user_id());
		model.setFk_duty_user_id(repairModel.getDuty_user_id());
		model.setFk_house_address_id(repairModel.getFk_repair_address());
		model.setFk_project_id(repairModel.getFk_project_id());
		model.setDetails_number(problemService.getCodeByProjectId(repairModel.getFk_project_id()));
		model.setQuestion_source(repairModel.getWorktask_type());
		model.setPk_crop(repairModel.getPk_crop());
		// 已生成工单，报事状态为2：处理中
		model.setState(2);
		return model;
	}

	/**
	 * 添加报事记录
	 * 
	 * @param detailsId
	 *            报事ID
	 * @param operationExpress
	 *            操作表示符
	 * @param taskState
	 *            报事流转状态
	 * @return
	 */
	public YJWYProblemRecordModel fillProblemRecord(String detailsId, String operationExpress, int taskState,
			String time, String user_id) {
		YJWYProblemRecordModel model = new YJWYProblemRecordModel();
		model.setFk_details_id(detailsId);
		model.setOperate_type(operationExpress);
		model.setOperate_state(taskState);
		model.setRecord_time(time);
		model.setRecord_user_id(user_id);
		return model;
	}

	// 公共方法，获取当前时间
	public String obtainTime(String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);// 设置日期格式
		return df.format(new Date());
	}
}
