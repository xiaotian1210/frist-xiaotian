package com.shareworx.ezfm.timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.shareworx.platform.util.DateTimeUtil;
import com.shareworx.ezfm.timer.service.QualityTaskService;

@Component
@Configurable
@EnableScheduling
public class YJWYQualityTimer {
	@Autowired
	@Qualifier(QualityTaskService.ID)
	public QualityTaskService qualityService;
	
	//每3分钟执行一次
	//@Scheduled(cron = "0 */1 *  * * * ")
	public void executeTest(){
		System.out.println ("Scheduling Tasks Examples By Cron: The time is now " + DateTimeUtil.getTimestampStr());
		qualityService.executeTimerUpdateTask();
		qualityService.executeTimerGeneratorTask();
	}

	// 每日凌晨1点执行  	@Scheduled(cron = "0 21 15 * * ?")
	@Scheduled(cron = "0 0 1 * * ?")
	public void executeQualityTask() {
		System.out.println("核查任務過期开始");
		qualityService.executeTimerUpdateTask();
		System.out.println("核查任務過期结束");
		System.out.println("核查任務生成开始");
		qualityService.executeTimerGeneratorTask();
		System.out.println("核查任務生成结束");
	}

}
