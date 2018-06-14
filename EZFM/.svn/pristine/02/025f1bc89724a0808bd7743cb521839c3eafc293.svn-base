package com.shareworx.ezfm.problem.details.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.shareworx.ezfm.device.fmdata.model.YJWYEqModel;
import com.shareworx.ezfm.device.fmdata.model.YJWYRoomModel;
import com.shareworx.ezfm.device.fmdata.service.YJWYEqDomainService;
import com.shareworx.ezfm.device.fmdata.service.YJWYRoomDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.app.push.service.AppPushService;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectDomainService;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserDomainService;
import com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsModel;
import com.shareworx.ezfm.problem.details.vo.ProblemDetailsVo;
import com.shareworx.ezfm.problem.file.model.ProblemFileModel;
import com.shareworx.ezfm.problem.file.service.ProblemFileBusinessService;
import com.shareworx.ezfm.problem.record.model.YJWYProblemRecordModel;
import com.shareworx.ezfm.problem.record.service.YJWYProblemRecordDomainService;
import com.shareworx.ezfm.pub.dictionary.OperationExpress;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.ezfm.webservice.cloudHttpClient.inner.cloudHttpClient;
import com.shareworx.ezfm.webservice.cloudHttpClient.inner.cloudHttpService;
import com.shareworx.ezfm.worktask.areadetails.fileUpload.FilesUpload;
import com.shareworx.ezfm.worktask.details.model.YJWYWorkTaskDetailsModel;
import com.shareworx.ezfm.worktask.details.service.YJWYWorkTaskDetailsBusinessService;
import com.shareworx.ezfm.worktask.details.service.YJWYWorkTaskDetailsDomainService;
import com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordModel;
import com.shareworx.ezfm.worktask.record.service.YJWYWorkTaskDetailsRecordBusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 报事主类业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYProblemDetailsBusinessService.ID)
public class YJWYProblemDetailsBusinessServiceImpl implements YJWYProblemDetailsBusinessService {

	@Autowired
	@Qualifier(YJWYEqDomainService.ID)
	YJWYEqDomainService yjwyEqDomainService;

	@Autowired
	@Qualifier(YJWYProblemDetailsDomainService.ID)
	private YJWYProblemDetailsDomainService domainService;
	
	@Autowired
	@Qualifier(YJWYUserDomainService.ID)
	private YJWYUserDomainService userService;

	@Autowired
	@Qualifier(YJWYProblemRecordDomainService.ID)
	private YJWYProblemRecordDomainService recordService;
	
	@Autowired
	@Qualifier(YJWYWorkTaskDetailsRecordBusinessService.ID)
	private YJWYWorkTaskDetailsRecordBusinessService workTaskRecordService;
	

	@Autowired
	@Qualifier(YJWYProjectDomainService.ID)
	private YJWYProjectDomainService projectService;
	
	
	@Autowired
	@Qualifier(YJWYWorkTaskDetailsBusinessService.ID)
	private YJWYWorkTaskDetailsBusinessService workTaskService;
	
	@Autowired
	@Qualifier(YJWYWorkTaskDetailsDomainService.ID)
	private YJWYWorkTaskDetailsDomainService workTaskDomainService;
	
	@Autowired
	@Qualifier(ProblemFileBusinessService.ID)
	private ProblemFileBusinessService fileService;
    @Autowired
    @Qualifier(YJWYUserDomainService.ID)
    YJWYUserDomainService userDomainService;

    @Autowired
    @Qualifier(YJWYRoomDomainService.ID)
    YJWYRoomDomainService roomDomainService;

	public void setFileService(ProblemFileBusinessService fileService) {
		this.fileService = fileService;
	}
	public void setWorkTaskDomainService(YJWYWorkTaskDetailsDomainService workTaskDomainService) {
		this.workTaskDomainService = workTaskDomainService;
	}
	public void setWorkTaskRecordService(YJWYWorkTaskDetailsRecordBusinessService workTaskRecordService) {
		this.workTaskRecordService = workTaskRecordService;
	}
	
	@Autowired
	@Qualifier(cloudHttpService.ID)
	private cloudHttpService cloudhttpservice; 
	
	
	
	public void setCloudhttpservice(cloudHttpService cloudhttpservice) {
		this.cloudhttpservice = cloudhttpservice;
	}
	public void setDomainService(YJWYProblemDetailsDomainService domainService) {
		this.domainService = domainService;
	}
	public void setUserService(YJWYUserDomainService userService) {
		this.userService = userService;
	}
	
	public void setRecordService(YJWYProblemRecordDomainService recordService) {
		this.recordService = recordService;
	}
	
	
	public void setProjectService(YJWYProjectDomainService projectService) {
		this.projectService = projectService;
	}
	public void setWorkTaskService(YJWYWorkTaskDetailsBusinessService workTaskService) {
		this.workTaskService = workTaskService;
	}
	
	
	@Autowired
	@Qualifier(AppPushService.ID)
	private AppPushService appPushService;
	
	public void setAppPushService(AppPushService appPushService) {
		this.appPushService = appPushService;
	}
	
	

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.details.service.YJWYProblemDetailsBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYProblemDetailsModel[] query(Query query) throws ShareworxServiceException {
//		JdbcTemplate read = dao.getReadTemplate();
//		String sql = "select tab1.pk_details_id,tab1.datails_code,tab1.create_time,tab1.repair_user,"
//				+ "tab1.task_state,tab1.update_time,tab2.project_name_,tab3.user_name_ from "
//				+ "(yjwy_worktask_details tab1 left join yjwy_pub_project tab2 "
//				+ "on tab1.fk_project_id=tab2.pk_project_) left join yjwy_pub_user "
//				+ "tab3 on tab1.duty_user_id=tab3.pk_user_";
//		if (query.getOrList().size()>0) {
//			sql += " where ";
//			for (int i = 0; i < query.getOrList().size(); i++) {
//				if (i==0) {
//					sql += " "+query.getOrList().get(i).getKey()+" ="+query.getOrList().get(i).getValue();
//				}
//				sql += " or "+query.getOrList().get(i).getKey()+" ="+query.getOrList().get(i).getValue();
//			}
//		}
//		sql +=" limit "+query.getStart()+","+query.getLimit();
		
		
		
		
		List<YJWYProblemDetailsModel> list = domainService.queryListByCondition(query);
		//查询项目名称
		for (YJWYProblemDetailsModel model:list) {
			if (!StringUtils.isEmpty(model.getFk_project_id())) {
				YJWYProjectModel projectModels = projectService.queryById(model.getFk_project_id());
				model.setFk_project_id(projectModels.getProject_name());
			}
			if (model.getState()==1) {
				model.put("stateName", "未处理");
			}
		}
		if(ArrayUtils.isEmpty(list)){
			return new YJWYProblemDetailsModel[0];
		}
		return list.toArray(new YJWYProblemDetailsModel[0]);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.details.service.YJWYProblemDetailsBusinessService#load(com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsModel)
	 */
	@Override
	public YJWYProblemDetailsModel[] load(YJWYProblemDetailsModel model) throws ShareworxServiceException {
		List<YJWYProblemDetailsModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYProblemDetailsModel[0];
		}
		return list.toArray(new YJWYProblemDetailsModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.details.service.YJWYProblemDetailsBusinessService#save(com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsModel[])
	 */
	@Override
	// @Transactional(propagation=Propagation.REQUIRED,rollbackFor=java.lang.Exception.class)
	public YJWYProblemDetailsModel[] saveDetails(ProblemDetailsVo vo) throws ShareworxServiceException {
		//类公用方法。获取格式化时间；
		String time = this.obtainTime();
		//获取订单单号（项目编号前四位+年月日+四位流水号）
		String code;
		YJWYWorkTaskDetailsModel workTaskModel;
		String workTaskId ="";
		YJWYProblemDetailsModel model = new YJWYProblemDetailsModel();
		YJWYUserModel userModel = UserUtil.getCurrentUser();
		if (vo!=null) {
				code = domainService.getCodeByProjectId(vo.getFk_project_id());
				//注入单号
				model.setDetails_number(code);
				//来源
//				model.setSource_from(vo.getSource_from());
				//注入时间
				model.setUpdate_time(System.currentTimeMillis()+"");
				model.setCreate_time(time);
				//注入操作人
				model.setCreate_user_id(UserUtil.getCurrentUserPk());
				model.setUpdate_user_id(UserUtil.getCurrentUserPk());
				//问题来源
				model.setQuestion_source("taskSource1");
				//所属项目
				model.setFk_project_id(vo.getFk_project_id());
				//是否维修
				model.setWhether_repair(vo.getWhether_repair());
				//客户姓名
				model.setCustomer_name(vo.getCustomer_name());
				//客户电话
				model.setCustomer_number(vo.getCustomer_number());
				//内容
				model.setDetails_content(vo.getDetails_content());
				//最后修改时间
				vo.setUpdate_time(model.getUpdate_time());
				vo.setCreate_time(time);
				//所属公司
            model.setPk_crop(userModel.getPk_crop());


			//维修机房
			if (!StringUtils.isEmpty(vo.getWork_fk_repair_equipment_room())) {
				model.setFk_repair_equipment_room(vo.getWork_fk_repair_equipment_room());
			}
			//维修设备
			if (!StringUtils.isEmpty(vo.getWork_fk_repair_equipment())) {
				model.setFk_repair_equipment(vo.getWork_fk_repair_equipment());
			}







				//是否下单维修，等于1下维修单；
				if (vo.getWhether_repair()==1) {
					workTaskModel = this.fillWorkTask(vo);
					workTaskId = saveWorkTask(workTaskModel);
					//关联字段
					model.setFk_details_id(workTaskId);
					//报事状态
					model.setState(2);
					//跟进人
					model.setFk_duty_user_id(vo.getWork_duty_user_id());
					//地址
					model.setDetailed_address(vo.getWork_repair_details());
					//新增工单记录


					YJWYWorkTaskDetailsRecordModel recordModel = this.fillDetailsRecord(workTaskId, OperationExpress.ADD, workTaskModel.getTask_state(),"",time);
					YJWYWorkTaskDetailsRecordModel[]  recordModels = workTaskRecordService.save(new YJWYWorkTaskDetailsRecordModel[]{recordModel});
					//上传附件
					if (vo.getProblem_file()!=null&&!vo.getProblem_file().isEmpty()) {
						FilesUpload fileUpload = new FilesUpload();
						//保存到OSS
						ProblemFileModel file = fileUpload.upload(vo.getProblem_file(), YJWYWorkTaskDetailsRecordModel.META_ID, recordModels[0].getPk_record_id(), time, workTaskModel.getPk_crop());
						//保存图片信息
						fileService.save(new ProblemFileModel[]{file});
					}
					//工单推送
					appPushService.sendWorkTaskPush(workTaskModel);
				}//是否下单维修，等于2不下维修单
				else {
					//地址
					model.setDetailed_address(vo.getDetailed_address());
					//报事种类
					model.setFk_class_id(vo.getFk_class_id());
					//地点
					model.setFk_house_address_id(vo.getFk_house_address_id());
					//注入状态，不下维修单，未选择跟进人为“未处理”值为1，选择处理人为“处理中” 值为2；1未处理，2处理中，3处理完成，4已回访
					if (!StringUtils.isEmpty(vo.getFk_duty_user_id())) {
						//跟进人
						model.setFk_duty_user_id(vo.getFk_duty_user_id());
						model.setState(2);
					}else{
						model.setState(1);
					}
					//报事推送
					appPushService.sendProbelmPush(model);
				}
		}
		List<YJWYProblemDetailsModel> list = domainService.save(new YJWYProblemDetailsModel[]{model});
		//上传附件
		if (vo.getProblem_file()!=null&&!vo.getProblem_file().isEmpty()) {
			FilesUpload fileUpload = new FilesUpload();
			//保存到OSS
			ProblemFileModel file = fileUpload.upload(vo.getProblem_file(), YJWYProblemDetailsModel.META_ID, list.get(0).getPk_details_id(), time,UserUtil.getCurrentUser().getPk_crop());
			//保存图片信息
			fileService.save(new ProblemFileModel[]{file});
		}
		YJWYProblemRecordModel[] recordModels = new YJWYProblemRecordModel[list.size()];
		YJWYProblemRecordModel recordModel = null;
		/**
		 * 添加报事记录 
		 */
		for (int i = 0; i < list.size(); i++) {
			//实例化记录表
			recordModel= new YJWYProblemRecordModel();
			//操作人
			recordModel.setRecord_user_id(UserUtil.getCurrentUserPk());
			//记录时间
			recordModel.setRecord_time(time);
			//当前跟进人ID
			recordModel.setDuty_user_id(list.get(i).getFk_duty_user_id());
			//当前操作标记
			//1转发，2处理，3完成，4关闭，5拒单，6下单,7取消，8重派 9编辑 10 重派单 11追记
			recordModel.setOperate_type(OperationExpress.ADD);
			//操作时状态 1未处理，2处理中，3处理完成，4已关闭，5已拒单 ,6已取消,7已删除8待单中
			recordModel.setOperate_state(list.get(i).getState());
			recordModel.setFk_details_id(list.get(i).getPk_details_id());
			recordModels[i] = recordModel;
		}
		//提交记录
		recordService.save(recordModels);
		return list.toArray(new YJWYProblemDetailsModel[0]);
	}

    /**
     * vo.fk_repair_equipment    -- 设备
     * vo.create_user_id         -- 创建人
     * vo.question_source        -- 来源
     * vo.details_content        -- 内容
     * vo.whether_repair         --  是否下单 ，1--下单
     * vo.fk_class_id            -- 报事分类
     * @param vo
     * @return
     * @throws ShareworxServiceException
     */
	@Override
	// @Transactional(propagation=Propagation.REQUIRED,rollbackFor=java.lang.Exception.class)
	public YJWYProblemDetailsModel[] saveDetailsByEq(ProblemDetailsVo vo) throws ShareworxServiceException {
		//类公用方法。获取格式化时间；
		String time = this.obtainTime();
		//获取订单单号（项目编号前四位+年月日+四位流水号）
		String code;
		YJWYWorkTaskDetailsModel workTaskModel;
		String workTaskId ="";
		YJWYProblemDetailsModel model = new YJWYProblemDetailsModel();
//		YJWYUserModel userModel = UserUtil.getCurrentUser();


//		if (vo!=null) {
        YJWYEqModel yjwyEqModel = yjwyEqDomainService.queryById(vo.getWork_fk_repair_equipment());


        code = domainService.getCodeByProjectId(yjwyEqModel.getSite_id());
			//注入单号
			model.setDetails_number(code);
			//来源
			//model.setSource_from(vo.getSource_from());
			//注入时间
			model.setUpdate_time(System.currentTimeMillis()+"");
			model.setCreate_time(time);
			//注入操作人
			model.setCreate_user_id(vo.getCreate_user_id());
			model.setUpdate_user_id(vo.getCreate_user_id());
			//问题来源
			model.setQuestion_source(vo.getQuestion_source());
			//所属项目
			model.setFk_project_id(yjwyEqModel.getSite_id());
			//是否维修
			model.setWhether_repair(vo.getWhether_repair());
			//客户姓名
        YJWYUserModel yjwyUserModel = userDomainService.queryById(vo.getCreate_user_id());
        model.setCustomer_name(yjwyUserModel.getUser_name());
			//客户电话
			model.setCustomer_number(yjwyUserModel.getPhone());
			//内容
			model.setDetails_content(vo.getDetails_content());
			//最后修改时间
			vo.setUpdate_time(model.getUpdate_time());
			vo.setCreate_time(time);
			//所属公司
            model.setPk_crop(yjwyEqModel.getPk_crop());
			//维修机房
			if (!StringUtils.isEmpty(vo.getWork_fk_repair_equipment_room())) {
				model.setFk_repair_equipment_room(vo.getWork_fk_repair_equipment_room());
			}
			//维修设备

            model.setFk_repair_equipment(vo.getWork_fk_repair_equipment());






			//是否下单维修，等于1下维修单；
			if (vo.getWhether_repair() != null && vo.getWhether_repair()==1) {
			    vo.setFk_project_id(yjwyEqModel.getSite_id());

                vo.setPk_crop(yjwyEqModel.getPk_crop());
				workTaskModel = this.fillWorkTask(vo);
				workTaskId = saveWorkTask(workTaskModel);
//				//关联字段
				model.setFk_details_id(workTaskId);
//				//报事状态
				model.setState(2);
//				//跟进人
				model.setFk_duty_user_id(vo.getWork_duty_user_id());
//				//地址
                YJWYRoomModel yjwyRoomModel = roomDomainService.queryById(yjwyEqModel.getRm_id());
                model.setDetailed_address(yjwyRoomModel.getName());

//				//新增工单记录
                YJWYWorkTaskDetailsRecordModel recordModel = new YJWYWorkTaskDetailsRecordModel();
                recordModel.setFk_details_id(workTaskId);
                recordModel.setOperation_express( OperationExpress.ADD);
                recordModel.setTask_state(workTaskModel.getTask_state());
                recordModel.setOperation_time(time);
                recordModel.setOperation_user_id(yjwyUserModel.getPk_user());
                recordModel.setOperation_user_name(yjwyUserModel.getUser_name());
                recordModel.setOperation_remarks(vo.getDetails_content());
				//YJWYWorkTaskDetailsRecordModel recordModel = this.fillDetailsRecord(workTaskId, OperationExpress.ADD, workTaskModel.getTask_state(),"来自报事工单",time);
				YJWYWorkTaskDetailsRecordModel[]  recordModels = workTaskRecordService.save(new YJWYWorkTaskDetailsRecordModel[]{recordModel});
//				//上传附件
				if (vo.getProblem_files()!=null) {
					FilesUpload fileUpload = new FilesUpload();
//					//保存到OSS
                    MultipartFile[] problem_files = vo.getProblem_files();
                    List<ProblemFileModel> problemFileModels = new ArrayList<>();
                    for(int i = 0;i <problem_files.length;i++){
                        if( !StringUtils.isEmpty(problem_files[i].getOriginalFilename())){
                            ProblemFileModel file = fileUpload.upload(problem_files[i], YJWYWorkTaskDetailsRecordModel.META_ID, recordModels[0].getPk_record_id(), time, workTaskModel.getPk_crop(),yjwyUserModel.getPk_user());
                            problemFileModels.add(file);
                        }

                    }
//					//保存图片信息
					fileService.save(problemFileModels.toArray(new ProblemFileModel[problemFileModels.size()]));
				}
				//工单推送
				appPushService.sendWorkTaskPush(workTaskModel);
			}//是否下单维修，等于2不下维修单
			else {

                //详细地址
                YJWYRoomModel yjwyRoomModel = roomDomainService.queryById(yjwyEqModel.getRm_id());
                model.setDetailed_address(yjwyRoomModel.getName());
				//报事种类
				model.setFk_class_id(vo.getFk_class_id());
				//地点
				model.setFk_house_address_id(vo.getFk_house_address_id());
				//注入状态，不下维修单，未选择跟进人为“未处理”值为1，选择处理人为“处理中” 值为2；1未处理，2处理中，3处理完成，4已回访
                model.setState(1);
				//报事推送
				appPushService.sendProbelmPush(model);
			}
//		}
		List<YJWYProblemDetailsModel> list = domainService.save(new YJWYProblemDetailsModel[]{model});
        //上传附件
        if (vo.getProblem_files()!=null) {
            FilesUpload fileUpload = new FilesUpload();
//					//保存到OSS
            MultipartFile[] problem_files = vo.getProblem_files();
            List<ProblemFileModel> problemFileModels = new ArrayList<>();
            for(int i = 0;i <problem_files.length;i++){
                if( !StringUtils.isEmpty(problem_files[i].getOriginalFilename())){
                    ProblemFileModel file = fileUpload.upload(problem_files[i], YJWYProblemDetailsModel.META_ID,
                            list.get(0).getPk_details_id(), time,yjwyEqModel.getPk_crop(),yjwyUserModel.getPk_user());
                    problemFileModels.add(file);
                }

            }
//					//保存图片信息
            fileService.save(problemFileModels.toArray(new ProblemFileModel[problemFileModels.size()]));
        }
//		//上传附件
//		if (vo.getProblem_file()!=null&&!vo.getProblem_file().isEmpty()) {
//			FilesUpload fileUpload = new FilesUpload();
//			//保存到OSS
//			ProblemFileModel file = fileUpload.upload(vo.getProblem_file(), YJWYProblemDetailsModel.META_ID,
//                    list.get(0).getPk_details_id(), time,yjwyEqModel.getPk_crop(),yjwyUserModel.getPk_user());
//			//保存图片信息
//			fileService.save(new ProblemFileModel[]{file});
//		}
		YJWYProblemRecordModel[] recordModels = new YJWYProblemRecordModel[list.size()];
		YJWYProblemRecordModel recordModel = null;
		/**
		 * 添加报事记录
		 */
		for (int i = 0; i < list.size(); i++) {
			//实例化记录表
			recordModel= new YJWYProblemRecordModel();
			//操作人
			recordModel.setRecord_user_id(yjwyUserModel.getPk_user());
			//记录时间
			recordModel.setRecord_time(time);
			//当前跟进人ID
			recordModel.setDuty_user_id(list.get(i).getFk_duty_user_id());
			//当前操作标记
			//1转发，2处理，3完成，4关闭，5拒单，6下单,7取消，8重派 9编辑 10 重派单 11追记
			recordModel.setOperate_type(OperationExpress.ADD);
			//操作时状态 1未处理，2处理中，3处理完成，4已关闭，5已拒单 ,6已取消,7已删除8待单中
			recordModel.setOperate_state(list.get(i).getState());
			recordModel.setFk_details_id(list.get(i).getPk_details_id());
			recordModels[i] = recordModel;
		}
		//提交记录
		recordService.save(recordModels);
		return list.toArray(new YJWYProblemDetailsModel[0]);
	}


	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.details.service.YJWYProblemDetailsBusinessService#update(com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsModel[])
	 */
	@Override
	public YJWYProblemDetailsModel[] update(YJWYProblemDetailsModel[] models) throws ShareworxServiceException {
		List<YJWYProblemDetailsModel> list = domainService.update(models);
		return list.toArray(new YJWYProblemDetailsModel[0]);
	}
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.details.service.YJWYProblemDetailsBusinessService#update(com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsModel[])
	 */
	@Override
	public YJWYProblemDetailsModel[] saveRepair(ProblemDetailsVo vo) throws ShareworxServiceException {
		String createTime =  this.obtainTime();
		String updateTime = System.currentTimeMillis()+"";
		String workTaskId ="";
//		YJWYProblemDetailsModel model = new YJWYProblemDetailsModel();
		List<YJWYProblemDetailsModel> list = new ArrayList<YJWYProblemDetailsModel>();
		if (vo!=null) {
			YJWYProblemDetailsModel originalModel = new YJWYProblemDetailsModel();
			if (vo!=null&&!StringUtils.isEmpty(vo.getPk_details_id())) {
				originalModel = domainService.queryById(vo.getPk_details_id());
			}
			//为model填充时间
			vo.setUpdate_time(updateTime);
			vo.setCreate_time(createTime);
			YJWYWorkTaskDetailsModel workTaskModel = this.fillRepairWorkTask(vo);
			workTaskId = saveWorkTask(workTaskModel);
			//工单推送
			appPushService.sendWorkTaskPush(workTaskModel);
			// 2017-1-11 kim start
			// ycq_worktaskid 不为空,证明此记录是ycq后台上报的记录
			Query query = Query.from(YJWYProblemDetailsModel.META_ID);
			query.where(new Condition("pk_details_id", QueryContents.TYPE_EQ, vo.getPk_details_id()));
			YJWYProblemDetailsModel problemModel = domainService.queryOneByCondition(query);
			if(problemModel!=null){
				String worktask_id = problemModel.getYcq_worktaskid();
				if(!StringUtils.isEmpty(worktask_id)){
				    //推送"待分发状态"--“processed”
					String worktaskid = worktask_id;
					String ycq_status = "processed";
					String pem_id;
					if(StringUtils.isEmpty(workTaskModel.getDuty_user_id())){
						pem_id = workTaskModel.getDuty_user_id();
					}else{
						pem_id = UserUtil.getCurrentUserPk();
					}
					cloudhttpservice.sendCloudpost(ycq_status, worktaskid, pem_id);
				}
			}
			//2017-1-11 kim end 
			//新增工单记录
			YJWYWorkTaskDetailsRecordModel recordModel = this.fillDetailsRecord(workTaskId, OperationExpress.ADD, workTaskModel.getTask_state(),"",createTime);
			YJWYWorkTaskDetailsRecordModel[] recordModels = workTaskRecordService.save(new YJWYWorkTaskDetailsRecordModel[]{recordModel});
			//上传附件
			if (vo.getProblem_file()!=null&&!vo.getProblem_file().isEmpty()) {
				FilesUpload fileUpload = new FilesUpload();
				//保存到OSS
				ProblemFileModel file = fileUpload.upload(vo.getProblem_file(), YJWYWorkTaskDetailsRecordModel.META_ID, recordModels[0].getPk_record_id(), createTime, workTaskModel.getPk_crop());
				//保存图片信息
				fileService.save(new ProblemFileModel[]{file});
			}
			originalModel.setFk_details_id(workTaskId);
			originalModel.setState(2);
			originalModel.setUpdate_time(updateTime);
			originalModel.setUpdate_user_id(UserUtil.getCurrentUserPk());
			list = domainService.update(originalModel);
			//添加工单记录
			for (YJWYProblemDetailsModel problem:list) {
				YJWYProblemRecordModel problemRecordModel= new YJWYProblemRecordModel();
				//记录时间
				problemRecordModel.setRecord_time(createTime);
				//操作人
				problemRecordModel.setRecord_user_id(UserUtil.getCurrentUserPk());
				//当前跟进人ID
				problemRecordModel.setDuty_user_id(problem.getFk_duty_user_id());
				//当前操作标记
				problemRecordModel.setOperate_type(OperationExpress.REPAIR);
				//操作时状态 1未处理，2处理中，3处理完成，4已关闭，5已拒单 ,6已取消,7已删除8待单中
				problemRecordModel.setOperate_state(2);
				problemRecordModel.setFk_details_id(problem.getPk_details_id());
				recordService.save(problemRecordModel);
			}
		}
		
			
		return  list.toArray(new YJWYProblemDetailsModel[0]);
	}
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.details.service.YJWYProblemDetailsBusinessService#update(com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsModel[])
	 */
	@Override
	public YJWYProblemDetailsModel[] updateHandle(ProblemDetailsVo vo) throws ShareworxServiceException {
		String time = System.currentTimeMillis()+"";
		List<YJWYProblemDetailsModel> list = new ArrayList<YJWYProblemDetailsModel>();
		if (vo!=null&&!StringUtils.isEmpty(vo.getPk_details_id())) {
			YJWYProblemDetailsModel originalModel = domainService.queryById(vo.getPk_details_id());
			//跟进人
			originalModel.setFk_duty_user_id(vo.getFk_duty_user_id());
			//分类ID
			if (!StringUtils.isEmpty(vo.getFk_class_id())) {
				originalModel.setFk_class_id(vo.getFk_class_id());
			}
			originalModel.setUpdate_time(time);
			originalModel.setState(2);
			originalModel.setUpdate_user_id(UserUtil.getCurrentUserPk());
			list = domainService.update(originalModel);
			//报事推送
			appPushService.sendProbelmPush(originalModel);
			// 2017-1-11 kim start
			// ycq_worktaskid 不为空,证明此记录是ycq后台上报的记录
			Query query = Query.from(YJWYProblemDetailsModel.META_ID);
			query.where(new Condition("pk_details_id", QueryContents.TYPE_EQ, vo.getPk_details_id()));
			YJWYProblemDetailsModel problemModel = domainService.queryOneByCondition(query);
			if(problemModel!=null){
				String worktask_id = problemModel.getYcq_worktaskid();
				if(!StringUtils.isEmpty(worktask_id)){
					//推送"处理中状态"--“processing” 给易彩区后台
					String worktaskid = worktask_id;
					String ycq_status = "processing";
					String pem_id = UserUtil.getCurrentUserPk();
					cloudhttpservice.sendCloudpost(ycq_status, worktaskid, pem_id);
				}
			}
			//2017-1-11 kim end
			/**
			 * 添加报事记录 
			 */
			YJWYProblemRecordModel recordModel = null;
			//实例化记录表
			recordModel= new YJWYProblemRecordModel();
			//记录时间
			recordModel.setRecord_time(this.obtainTime());
			//当前操作人
			recordModel.setRecord_user_id(UserUtil.getCurrentUserPk());
			;
			//当前跟进人ID
			recordModel.setDuty_user_id(originalModel.getFk_duty_user_id());
			//当前跟进人姓名
//			recordModel.setDuty_user_name(userService.queryById(originalModel.getFk_duty_user_id()).getUser_name().toString());
			//当前操作标记
			recordModel.setOperate_type(OperationExpress.HANDLE);
			//操作时状态 1未处理，2处理中，3处理完成，4已关闭，5已拒单 ,6已取消,7已删除8待单中
			recordModel.setOperate_state(2);
			recordModel.setFk_details_id(originalModel.getPk_details_id());
			//详情记录
			if (!StringUtils.isEmpty(vo.getDetails_content())) {
				recordModel.setRecord_content(vo.getDetails_content());
			}
			List<YJWYProblemRecordModel> models = recordService.save(recordModel);
			//上传附件
			if (models!=null&&models.size()>0&&!vo.getProblem_file().isEmpty()) {
				FilesUpload fileUpload = new FilesUpload();
				//保存到OSS
				ProblemFileModel file = fileUpload.upload(vo.getProblem_file(), YJWYProblemRecordModel.META_ID, models.get(0).getPk_record_id(), time, UserUtil.getCurrentUser().getPk_crop());
				//保存图片信息
				fileService.save(new ProblemFileModel[]{file});
			}
		}
		return  list.toArray(new YJWYProblemDetailsModel[0]);
	}
	
	
	
	
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.details.service.YJWYProblemDetailsBusinessService#update(com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsModel[])
	 */
	@Override
	public YJWYProblemDetailsModel[] updateComplete(ProblemDetailsVo vo) throws ShareworxServiceException {
		String time = this.obtainTime();
		List<YJWYProblemDetailsModel> list = new ArrayList<YJWYProblemDetailsModel>();
		if (vo!=null&&!StringUtils.isEmpty(vo.getPk_details_id())) {
			YJWYProblemDetailsModel originalModel = domainService.queryById(vo.getPk_details_id());
			originalModel.setUpdate_time(System.currentTimeMillis()+"");
			originalModel.setState(3);
			originalModel.setUpdate_user_id(UserUtil.getCurrentUserPk());
			originalModel.setComplete_time(time);
			list = domainService.update(originalModel);
			/**
			 * 添加报事记录 
			 */
			YJWYProblemRecordModel recordModel = null;
			//实例化记录表
			recordModel= new YJWYProblemRecordModel();
			//记录时间
			recordModel.setRecord_time(time);
			//当前操作人
			recordModel.setRecord_user_id(UserUtil.getCurrentUserPk());
			//当前跟进人ID
			recordModel.setDuty_user_id(originalModel.getFk_duty_user_id());
			//当前跟进人姓名
//			recordModel.setDuty_user_name(userService.queryById(originalModel.getFk_duty_user_id()).getUser_name().toString());
			//当前操作标记
			//1转发，2处理，3完成，4关闭，5拒单，6下单,7取消，8重派 9编辑 10 重派单 11追记
			recordModel.setOperate_type(OperationExpress.COMPLETE);
			//操作时状态 1未处理，2处理中，3处理完成，4已关闭，5已拒单 ,6已取消,7已删除8待单中
			recordModel.setOperate_state(3);
			recordModel.setFk_details_id(originalModel.getPk_details_id());
			//详情记录
			if (!StringUtils.isEmpty(vo.getDetails_content())) {
				recordModel.setRecord_content(vo.getDetails_content());
			}
			//2017-1-11 kim start
			//ycq_worktaskid不为空表示是易彩区的报事记录 上送完成记录状态
			Query query = Query.from(YJWYProblemDetailsModel.META_ID);
			query.where(new Condition("pk_details_id", QueryContents.TYPE_EQ, vo.getPk_details_id()));
			YJWYProblemDetailsModel problemModel = domainService.queryOneByCondition(query);
			if(problemModel!=null){
				// ycq_worktaskid 不为空,证明此记录是ycq后台上报的记录
				String worktask_id = problemModel.getYcq_worktaskid();
				if(!StringUtils.isEmpty(worktask_id)){
					String worktaskid = worktask_id;
					String ycq_status = "completed";
					String pem_id = UserUtil.getCurrentUserPk();
					cloudhttpservice.sendCloudpost(ycq_status, worktaskid,pem_id); 
				}
			}
			//2017-1-11 kim end
			List<YJWYProblemRecordModel> models = recordService.save(recordModel);
			//上传附件
			if (models!=null&&models.size()>0&&!vo.getProblem_file().isEmpty()) {
				FilesUpload fileUpload = new FilesUpload();
				//保存到OSS
				ProblemFileModel file = fileUpload.upload(vo.getProblem_file(), YJWYProblemRecordModel.META_ID, models.get(0).getPk_record_id(), time, UserUtil.getCurrentUser().getPk_crop());
				//保存图片信息
				fileService.save(new ProblemFileModel[]{file});
			}
		}
		return  list.toArray(new YJWYProblemDetailsModel[0]);
	}

	
	
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.details.service.YJWYProblemDetailsBusinessService#update(com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsModel[])
	 */
	@Override
	public YJWYProblemDetailsModel[] updateVisit(ProblemDetailsVo vo) throws ShareworxServiceException {
		String time = System.currentTimeMillis()+"";
		List<YJWYProblemDetailsModel> list = new ArrayList<YJWYProblemDetailsModel>();
		if (vo!=null&&!StringUtils.isEmpty(vo.getPk_details_id())) {
			YJWYProblemDetailsModel originalModel = domainService.queryById(vo.getPk_details_id());
//			originalModel.setDetails_content(models.get("details_content").toString());
			originalModel.setUpdate_time(time);
			originalModel.setState(4);
			originalModel.setUpdate_user_id(UserUtil.getCurrentUserPk());
			
			list = domainService.update(originalModel);
			/**
			 * 添加报事记录 
			 */
			YJWYProblemRecordModel recordModel = null;
			//实例化记录表
			recordModel= new YJWYProblemRecordModel();
			//记录时间
			recordModel.setRecord_time(this.obtainTime());
			//当前操作人
			recordModel.setRecord_user_id(UserUtil.getCurrentUserPk());
			//当前跟进人ID
			recordModel.setDuty_user_id(originalModel.getFk_duty_user_id());
			//当前跟进人姓名
//			recordModel.setDuty_user_name(userService.queryById(originalModel.getFk_duty_user_id()).getUser_name().toString());
			//当前操作标记
			//1转发，2处理，3完成，4关闭，5拒单，6下单,7取消，8重派 9编辑 10 重派单 11追记
			recordModel.setOperate_type(OperationExpress.VISIT);
			//操作时状态 1未处理，2处理中，3处理完成，4已关闭，5已拒单 ,6已取消,7已删除8待单中
			recordModel.setOperate_state(4);
			recordModel.setFk_details_id(originalModel.getPk_details_id());
			if (!StringUtils.isEmpty(vo.getEvaluate_type())) {
				recordModel.setEvaluate_type(vo.getEvaluate_type());
			}
			if (!StringUtils.isEmpty(vo.getDetails_content())) {
				recordModel.setEvaluate_content(vo.getDetails_content());
				recordModel.setRecord_content(vo.getDetails_content());
			}
			if (!StringUtils.isEmpty(vo.getAttitude())) {
				recordModel.setAttitude(Integer.parseInt(vo.getAttitude()));
			}
			if (!StringUtils.isEmpty(vo.getQuality())) {
				recordModel.setQuality(Integer.parseInt(vo.getQuality()));
			}
			if (!StringUtils.isEmpty(vo.getEffect())) {
				recordModel.setEffect(Integer.parseInt(vo.getEffect()));
			}
			// 2017-1-11 kim start
			// ycq_worktaskid 不为空,证明此记录是ycq后台上报的记录
			Query query = Query.from(YJWYProblemDetailsModel.META_ID);
			query.where(new Condition("pk_details_id", QueryContents.TYPE_EQ, vo.getPk_details_id()));
			YJWYProblemDetailsModel problemModel = domainService.queryOneByCondition(query);
			if(problemModel!=null){
				// ycq_worktaskid 不为空,证明此记录是ycq后台上报的记录
				String worktask_id = problemModel.getYcq_worktaskid();
				if (!StringUtils.isEmpty(worktask_id)) {
					// 推送"工单完结"--“finished”
					String worktaskid = worktask_id;
					String ycq_status = "finished";
					String pem_id = UserUtil.getCurrentUserPk();
					cloudhttpservice.sendCloudpost(ycq_status, worktaskid,pem_id); 
				}
			}
			// 2017-1-11 kim end
			// 添加工单记录  
			List<YJWYProblemRecordModel> models = recordService.save(recordModel);
			//上传附件
			if (models!=null&&models.size()>0&&!vo.getProblem_file().isEmpty()) {
				FilesUpload fileUpload = new FilesUpload();
				//保存到OSS
				ProblemFileModel file = fileUpload.upload(vo.getProblem_file(), YJWYProblemRecordModel.META_ID, models.get(0).getPk_record_id(), time, UserUtil.getCurrentUser().getPk_crop());
				//保存图片信息
				fileService.save(new ProblemFileModel[]{file});
			}
		}
		return  list.toArray(new YJWYProblemDetailsModel[0]);
	}
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.details.service.YJWYProblemDetailsBusinessService#delete(com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsModel[])
	 */
	@Override
	public YJWYProblemDetailsModel[] delete(YJWYProblemDetailsModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

	
	//公共方法，获取当前时间
	public String obtainTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(new Date());
	}
	/**
	 * 填充工单信息
	 * @param model
	 * @return
	 */
	public YJWYWorkTaskDetailsModel fillWorkTask(ProblemDetailsVo vo){
		//填充工单信息
		YJWYWorkTaskDetailsModel workTaskModel = new YJWYWorkTaskDetailsModel();
		//服务类型
		if (!StringUtils.isEmpty(vo.getWork_service_type())) {
			workTaskModel.setService_type(vo.getWork_service_type());
		}
		//维修种类
		if (!StringUtils.isEmpty(vo.getWork_repair_class_id())) {
			workTaskModel.setRepair_class_id(vo.getWork_repair_class_id());
		}
		//维修楼址
		if (!StringUtils.isEmpty(vo.getWork_fk_repair_address())) {
			workTaskModel.setFk_repair_address(vo.getWork_fk_repair_address());
		}
		//维修地址
		if (!StringUtils.isEmpty(vo.getWork_repair_details())) {
			workTaskModel.setRepair_details(vo.getWork_repair_details());
		}
		//维修机房
		if (!StringUtils.isEmpty(vo.getWork_fk_repair_equipment_room())) {
			workTaskModel.setFk_repair_equipment_room(vo.getWork_fk_repair_equipment_room());
		}
		//维修设备
		if (!StringUtils.isEmpty(vo.getWork_fk_repair_equipment())) {
			workTaskModel.setFk_repair_equipment(vo.getWork_fk_repair_equipment());
		}
		//报修人姓名
		if (!StringUtils.isEmpty(vo.getCustomer_name())) {
			workTaskModel.setRepair_user(vo.getCustomer_name());
		}
		//联系电话
		if (!StringUtils.isEmpty(vo.getCustomer_number())) {
			workTaskModel.setContact_number(vo.getCustomer_number());
		}
		//预约时间
		if (!StringUtils.isEmpty(vo.getBookings_time())&&vo.getWhether_appointment()==1) {
			workTaskModel.setBookings_time(vo.getBookings_time());
		}
		//所属公司
		if (!StringUtils.isEmpty(vo.getPk_crop())) {
			workTaskModel.setPk_crop(vo.getPk_crop());
		}
		///创建人
        if (!StringUtils.isEmpty(vo.getCreate_user_id())) {
            workTaskModel.setCreate_user_id(vo.getCreate_user_id());
            //填充最后修改人
            workTaskModel.setUpdate_user_id(vo.getCreate_user_id());

        }else {
            workTaskModel.setCreate_time(vo.getCreate_time());
            //填充最后修改人
            workTaskModel.setUpdate_user_id(UserUtil.getCurrentUserPk());
        }
        //创建时间
        if (!StringUtils.isEmpty(vo.getCreate_time())) {
            workTaskModel.setCreate_time(vo.getCreate_time());
        }




		//生成工单单号
		String code =workTaskDomainService.getTaskCode(vo.getFk_project_id());
		//填充工单单号
		workTaskModel.setDatails_code(code);
		//填充最后修改时间戳
		workTaskModel.setUpdate_time(vo.getUpdate_time());

		//工单来源
		workTaskModel.setWorktask_type("taskSource11");
		//所属项目
		workTaskModel.setFk_project_id(vo.getFk_project_id());
		//维修内容
		workTaskModel.setRepair_content(vo.getDetails_content());
		//判断是否选跟进人

			//状态置为0：未派单
        workTaskModel.setTask_state(0);

		return workTaskModel;
	}
	
	/**
	 * 填充工单信息
	 * @param model
	 * @return
	 */
	public YJWYWorkTaskDetailsModel fillRepairWorkTask(ProblemDetailsVo vo){
		//填充工单信息
		YJWYWorkTaskDetailsModel workTaskModel = new YJWYWorkTaskDetailsModel();
		//服务类型
		if (!StringUtils.isEmpty(vo.getWork_service_type())) {
			workTaskModel.setService_type(vo.getWork_service_type());
		}
		//维修种类
		if (!StringUtils.isEmpty(vo.getWork_repair_class_id())) {
			workTaskModel.setRepair_class_id(vo.getWork_repair_class_id());
		}
		//维修楼址
		if (!StringUtils.isEmpty(vo.getWork_fk_repair_address())) {
			workTaskModel.setFk_repair_address(vo.getWork_fk_repair_address());
		}
		//维修地址
		if (!StringUtils.isEmpty(vo.getWork_repair_details())) {
			workTaskModel.setRepair_details(vo.getWork_repair_details());
		}
		//维修机房
		if (!StringUtils.isEmpty(vo.getWork_fk_repair_equipment_room())) {
			workTaskModel.setFk_repair_equipment_room(vo.getWork_fk_repair_equipment_room());
		}
		//维修设备
		if (!StringUtils.isEmpty(vo.getWork_fk_repair_equipment())) {
			workTaskModel.setFk_repair_equipment(vo.getWork_fk_repair_equipment());
		}
		//报修人姓名
		if (!StringUtils.isEmpty(vo.getWork_repair_user())) {
			workTaskModel.setRepair_user(vo.getWork_repair_user());
		}
		//联系电话
		if (!StringUtils.isEmpty(vo.getWork_contact_number())) {
			workTaskModel.setContact_number(vo.getWork_contact_number());
		}
		//预约时间
		if (!StringUtils.isEmpty(vo.getBookings_time())&&vo.getWhether_appointment_repair()==1) {
			workTaskModel.setBookings_time(vo.getBookings_time());
		}
		//所属公司
		if (!StringUtils.isEmpty(vo.getPk_crop())) {
			workTaskModel.setPk_crop(vo.getPk_crop());
		}
		//创建时间/创建人
		workTaskModel.setCreate_time(vo.getCreate_time());
		workTaskModel.setCreate_user_id(UserUtil.getCurrentUserPk());
		//生成工单单号
		String code =workTaskDomainService.getTaskCode(vo.getFk_project_id());
		//填充工单单号
		workTaskModel.setDatails_code(code);
		//填充最后修改时间戳
		workTaskModel.setUpdate_time(vo.getUpdate_time());
		//填充最后修改人
		workTaskModel.setUpdate_user_id(UserUtil.getCurrentUserPk());
		//工单来源
		workTaskModel.setWorktask_type("taskSource1");
		//所属项目
		workTaskModel.setFk_project_id(vo.getFk_project_id());
		//维修内容
		workTaskModel.setRepair_content(vo.getDetails_content());
		//判断是否选跟进人
		if (!StringUtils.isEmpty(vo.getWork_duty_user_id())) {
			workTaskModel.setDuty_user_id(vo.getWork_duty_user_id());
			//判断跟进人类型，duty_user_type值：1：接口人；2：维修人
			if (!StringUtils.isEmpty(vo.getWork_duty_user_type())&&vo.getWork_duty_user_type().equals("1")) {
				/**
				 * 选择接口人员，业务处理待确定
				 */
			}else{
				//状态置为1：待接单
				workTaskModel.setTask_state(1);
			}
		}else{
			//状态置为0：未派单
			workTaskModel.setTask_state(0);
		}
		return workTaskModel;
	}
	
	public String saveWorkTask(YJWYWorkTaskDetailsModel workTaskModel){
		String workTaskID = "";
		List<YJWYWorkTaskDetailsModel> workTaskModelList = workTaskDomainService.save(new YJWYWorkTaskDetailsModel[]{workTaskModel});
		if (workTaskModelList!=null&&workTaskModelList.size()>0) {
			workTaskID = workTaskModelList.get(0).getPk_details_id();
		}
		return workTaskID;
	}
	/**
	 * 
	 * 添加工单记录
	 * @param detailsId 工单ID
	 * @param operationExpress 操作表示符
	 * @param taskState 工单流转状态
	 * @param recordRemarks 备注详情
	 * @return
	 */
	public YJWYWorkTaskDetailsRecordModel fillDetailsRecord(String details_id,String operationExpress,int taskState,String recordRemarks,String time){
		YJWYWorkTaskDetailsRecordModel recordModel = new YJWYWorkTaskDetailsRecordModel();
		recordModel.setFk_details_id(details_id);
		recordModel.setOperation_express(operationExpress);
		recordModel.setTask_state(taskState);
		recordModel.setOperation_time(time);
		recordModel.setOperation_user_id(UserUtil.getCurrentUserPk());
		if (!StringUtils.isEmpty(recordRemarks)) {
			recordModel.setOperation_remarks(recordRemarks);
		}
		return recordModel;
	}
	@Override
	public YJWYProblemDetailsModel[] save(YJWYProblemDetailsModel[] models) throws ShareworxServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
//	public static void main(String[] args) {
//		FilesUpload fileUpload = new FilesUpload();
//		//验证是否为图片格式
//		System.out.println(fileUpload.isImage(new File("C:/Users/zhangjing.cheng/Desktop/房源信息/HouseAddrInfo-路址导入.java")));;
//		// 创建OSSClient实例
////		OSSClient ossClient = new OSSClient("http://oss-cn-beijing.aliyuncs.com",
////				"JCsbNdP7MnxyeoTW", "n1qb9iAji3u0ci94ZbyD2YXPa7ihuZ");
////		// 上传文件流
////		PutObjectResult result = ossClient.putObject("yjwy-test", "ezfm/files.jpg",new File("C:/Users/zhangjing.cheng/Desktop/头像/u=3441305868,2842560091&fm=11&gp=0.jpg"));
////		// 关闭client
////		ossClient.shutdown();
//	}
}
