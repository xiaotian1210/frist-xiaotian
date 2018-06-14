package com.shareworx.ezfm.app.quality.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.shareworx.platform.persist.service.BaseDomainService;
import com.shareworx.platform.util.DateTimeUtil;
import com.shareworx.ezfm.app.quality.service.AppInspectTaskService;
import com.shareworx.ezfm.app.util.AppJsonMessage;
import com.shareworx.ezfm.app.util.AppMessageFormatUtil;
import com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskModel;

/**
 * 核查管理 action
 * @author lingwei.li
 *
 */
@Controller
@RequestMapping("app/inspectTask")
public class AppInspectTaskAction {
	
	final static Logger logger = Logger.getLogger(AppInspectTaskAction.class);
	
	@Autowired
	@Qualifier(AppInspectTaskService.ID)
	private AppInspectTaskService service;
	
	/**
	 * 获取任务列表(包括：待办任务、待整改、已办任务、问题跟踪)
	 * @param reqjson
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "taskList", method = RequestMethod.GET)
	public @ResponseBody JSONObject taskList(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis(); //当前时间戳
		try {
			JSONObject json = service.getAllTask(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.error(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
	
	/**
	 * 完成任務
	 * @param reqjson
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "taskFinish", method = RequestMethod.POST)
	public @ResponseBody JSONObject taskFinish(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis(); //当前时间戳
		try {
			JSONObject json = service.saveTaskFinish(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.error(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}

	/**
	 * 根据任务id获取任务详情接口
	 * @param reqjson
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "taskDetail", method = RequestMethod.GET)
	public @ResponseBody JSONObject taskDetail(HttpServletRequest reqParam) throws Exception{
		
		long start = System.currentTimeMillis(); //当前时间戳
		try {
			JSONObject json = service.getInspectTaskDetail(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.error(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
		
	}
	
	/**
	 * 根据任务信息 整改任务信息
	 * @param reqjson
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "taskRectification", method = RequestMethod.POST)
	public @ResponseBody JSONObject taskRectification(HttpServletRequest reqParam) throws Exception{
		
		long start = System.currentTimeMillis(); //当前时间戳
		try {
			JSONObject json = service.saveTaskRectification(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.error(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
		
	}
	
	/**
	 * 根据任务信息 整改完成任务
	 * @param reqjson
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "taskRecFinish", method = RequestMethod.POST)
	public @ResponseBody JSONObject taskRecFinish(HttpServletRequest reqParam) throws Exception{
		
		long start = System.currentTimeMillis(); //当前时间戳
		try {
			JSONObject json = service.saveTaskRecFinish(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.error(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
		
	}
	
	/**
	 * 根据任务信息 整改确认
	 * @param reqjson
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "taskRecConfirm", method = RequestMethod.POST)
	public @ResponseBody JSONObject taskRecConfirm(HttpServletRequest reqParam) throws Exception{
		
		long start = System.currentTimeMillis(); //当前时间戳
		try {
			JSONObject json = service.saveTaskRecConfirm(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.error(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
		
	}
	
	/**
	 * 核查任务模块生成测试数据接口
	 * @return
	 * @throws Exception
	 */
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService domainService;
	
	@RequestMapping(value = "createTaskData", method = RequestMethod.GET)
	public @ResponseBody JSONObject createTaskData()throws Exception{
		
		InspectTaskModel[] models = new InspectTaskModel[100];
		int index1 = 0;
		int index2 = 0;
		for(int i=0;i<100;i++){
			InspectTaskModel task = new InspectTaskModel();
			String[] projects = {"0000002016080900019Y","0000002016080900019W"};
			String[] standards = {"000000201607250000AB","000000201607220000A9","000000201607220000A7","000000201607220000A3"};
			String[] users = {"00000020160713000048","00000020160713000049","0000002016071300004A","0000002016071300004B",
					"0000002016071300004C","0000002016071300004D","0000002016071300004E","0000002016071300004F",
					"0000002016071300004G","0000002016071300004H","0000002016071300004M"};
			if(index1>=3){
				index1=0;
			}
			if(index2>=11){
				index2=0;
			}
			task.setFk_project(projects[i%2]);
			task.setFk_standard(standards[index1]);
			task.setFk_taskuser(users[index2]);
			task.setTask_currentuser_pk(users[index2]);
			task.setTask_state("10");
			task.setCreate_time(DateTimeUtil.getTimestampString(new Date()));
			task.setUpdate_time(new Date().getTime()+"");
			task.setTask_start_time(DateTimeUtil.getTimestampString(new Date()));
			task.setTask_score(new BigDecimal(10));
			task.setTask_pc_name("1次/天");
			task.setIs_rectify("0");//是否整改，
			index1++;
			index2++;
			models[i]=task;
		}
		List<InspectTaskModel> taskList = domainService.save(InspectTaskModel.META_ID,models);
		
		return AppJsonMessage.toJsonMsgTrue(taskList);
	}
	
	
	
	
}
