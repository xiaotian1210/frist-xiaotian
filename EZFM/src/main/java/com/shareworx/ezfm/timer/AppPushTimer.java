package com.shareworx.ezfm.timer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.shareworx.ezfm.app.constant.AppConstant;
import com.shareworx.ezfm.app.worktask.service.AppWorkTaskPushService;

@Component
@Configurable
@EnableScheduling
public class AppPushTimer {

	@Autowired
	@Qualifier(AppWorkTaskPushService.ID)
	private AppWorkTaskPushService pushService;

	public void setPushService(AppWorkTaskPushService pushService) {
		this.pushService = pushService;
	}
	
	// 每天早上8:00 到 17：00 每15钟执行一次*/	
	@Scheduled(cron="0 0/15 8-16 * * ?") 
	//@Scheduled(cron = "0 */3 *  * * * ")
	public void appNotOrdersOverTimePush(){
		try {
			System.out.println("当前时间："+AppConstant.df_YMDHMS.format(new Date()));
			System.out.println("my implement not orders overtime push 8-17 15");
			pushService.notOrdersOverTimePush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// 每天早上8:00 到 17：00 每30钟执行一次*/	
	@Scheduled(cron="0 0/30 8-16 * * ?")
	public void appRepairOverTimePush(){
		try {
			System.out.println("当前时间："+AppConstant.df_YMDHMS.format(new Date()));
			System.out.println("my implement repair overtime push 8-17 30");
			pushService.repairOverTimePush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
