package com.shareworx.ezfm.timer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.shareworx.ezfm.device.fmdata.service.YJWYFmDataService;
import com.shareworx.ezfm.device.patrol.task.service.YJWYTaskService;

/**
 * 设备管理定时器
 * 
 * @author jin.li
 *
 */
@Component
@Configurable
@EnableScheduling
public class YJWYDeviceTimer {

	@Autowired
	@Qualifier(YJWYTaskService.ID)
	private YJWYTaskService taskService;

	public void setTaskService(YJWYTaskService taskService) {
		this.taskService = taskService;
	}

	@Autowired
	@Qualifier(YJWYFmDataService.ID)
	private YJWYFmDataService fmDataService;

	public void setFmDataService(YJWYFmDataService fmDataService) {
		this.fmDataService = fmDataService;
	}

	final static Logger log = Logger.getLogger(YJWYDeviceTimer.class);

//	@Scheduled(cron = "0 1 0 * * ?") // 每天凌晨0点执行
//	public void deviceTask1() {
//		try {
//			System.out.println("=================FM数据同步【开始】！==================");
//			fmDataService.synchro("http://101.201.196.210:8081/archibus/cxf/BasisService", "YYJT");
//			System.out.println("=================FM数据同步【完成】！==================");
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("=================FM数据同步【失败】！==================");
//		}
//
//	}

	@Scheduled(cron = "0 1 1 * * ?") // 每天凌晨0点执行
	public void deviceTask() {
		taskService.getTaskModels();
		taskService.judgeExpired();
	}

}
