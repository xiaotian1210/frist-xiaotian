package com.shareworx.ezfm.timer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.shareworx.ezfm.app.constant.AppConstant;
import com.shareworx.ezfm.app.sqlite.service.AppCreateDBSqliteService;

@Component
@Configurable
@EnableScheduling
public class AppCreateDBSqliteTimer {

	@Autowired
	@Qualifier(AppCreateDBSqliteService.ID)
	private AppCreateDBSqliteService sqliteDBService;

	public void setSqliteDBService(AppCreateDBSqliteService sqliteDBService) {
		this.sqliteDBService = sqliteDBService;
	}
	
	// 每天凌晨 4点执行一次	
	@Scheduled(cron = "0 0 4 * * ?")
	public void appCreateDBSqlite(){
		try {
			System.out.println("当前时间："+AppConstant.df_YMDHMS.format(new Date()));
			System.out.println("my implement not orders overtime push 0 4 ");
			sqliteDBService.createSqliteDB();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/*@Scheduled(cron="0 0/15 9-16 * * ?")   //每5秒执行一次 
	public void appRepairOverTimePush(){
		try {
			System.out.println("当前时间："+AppConstant.df_YMDHMS.format(new Date()));
			System.out.println("my implement repair overtime push 0 1 9-17");
			//pushService.RepairOverTimePush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
