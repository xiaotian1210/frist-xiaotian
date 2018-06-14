package com.shareworx.ezfm.bjyijiequ;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.druid.util.StringUtils;
import com.shareworx.ezfm.app.constant.AppConstant;
import com.shareworx.ezfm.app.util.AppIdBuilder;

public class BjyijiequUtilsDemo {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		//getBjyijiequAreaDate();
		//getBjyijiequProjectDate();
		getBjyijiequResourcesDate(200);//参数是 一共多少条数据 （参数*1000）
		/**
		 *	表名：
		 * 	yjwy_worktask_repair_class、
		 * 	yjwy_pub_resources、
		 * 	yjwy_quality_inspectstandard
		 * 以上表做过 增量 插入，如果需要导入全部数据
		 * 需要 修改 BjyijiequHelper 中 isIncrement 的 值 为 false
		 */
		String table_arr[] = {"yjwy_pub_role",
				"yjwy_pub_org",
				"yjwy_pub_area",
				"yjwy_pub_project",
				"yjwy_pub_station",
				"yjwy_pub_user",
				"yjwy_pub_resources",
				"yjwy_quality_standardedition",
				"yjwy_quality_inspectstandard",
				"yjwy_quality_problemtype",
				"yjwy_problem_class",
				"yjwy_worktask_repair_class",
				"yjwy_worktask_area_personnel",
				"yjwy_worktask_area_details",
				"yjwy_pub_user_station",
				"yjwy_pub_role_user",
				"yjwy_worktask_project_user",
				"yjwy_quality_standard_station",
				"yjwy_worktask_area_project_nexus",
				"yjwy_worktask_area_user_nexus_1",
				"yjwy_worktask_area_user_nexus_2",
				"yjwy_worktask_area_user_nexus_3",
				"yjwy_worktask_repair_class_project",
				"yjwy_proinfo_class_nexus"};
		
		String table_name = "yjwy_quality_inspectstandard";
		//inserDate(table_name);
	}
	
	public static void inserDate(String table_name){
		List<Map<String, Object>> listMap = BjyijiequHelper.getAll(table_name);
		
		//因为一碑和dms存的关系格式不同 
		if ("yjwy_proinfo_class_nexus".equals(table_name)) {
			
			List<Map<String, Object>> proList = new ArrayList<Map<String, Object>>();
			
			for (Map<String, Object> map : listMap) {
				
				//需要判断 class_ids 是否为空，如果为空  跳出本次循坏
				String class_ids = String.valueOf(map.get("class_id"));
				if (StringUtils.isEmpty(class_ids)) {
					continue;
				}
				
				//获取项目id
				String project_id = String.valueOf(map.get("project_id"));
				
				//循坏class_ids 重组map
				String[] strA=class_ids.split(",");
				for (int i = 0; i < strA.length; i++) {
					Map<String, Object> proMap = new HashMap<String, Object>();
					String class_id = strA[i];
					proMap.put("nexus_id", AppIdBuilder.createRandomId());
					proMap.put("project_id", project_id);
					proMap.put("class_id", class_id);
					proMap.put("create_time", AppConstant.df_YMDHMS.format(new Date()));
					proList.add(proMap);
				}
				
				
			}
			listMap = proList;
		}
		
		try {
			BjyijiequDMSHelper.insert(table_name, listMap);
		} catch (SQLException e) {
			System.out.println("导入表："+table_name+"报错！");
			e.printStackTrace();
		}
		
		
		
	}
	
	public static void getBjyijiequAreaDate(){
		String table_name = "yjwy_pub_area";
		List<Map<String, Object>> listMap = BjyijiequHelper.getAll(table_name);
		try {
			BjyijiequDMSHelper.insert(table_name, listMap);
		} catch (SQLException e) {
			System.out.println("导入表："+table_name+"报错！");
			e.printStackTrace();
		}
	}
	
	public static void getBjyijiequProjectDate(){
		String table_name = "yjwy_pub_project";
		List<Map<String, Object>> listMap = BjyijiequHelper.getAll(table_name);
		try {
			BjyijiequDMSHelper.insert(table_name, listMap);
		} catch (SQLException e) {
			System.out.println("导入表："+table_name+"报错！");
			e.printStackTrace();
		}
	}
	
	public static void getBjyijiequResourcesDate(int num){
		String table_name = "yjwy_pub_resources";
		String end = "1000";
		try {
			for (int i = 0; i < num; i++) {
				int startNum = 1000*i;
				String start = String.valueOf(startNum);
				List<Map<String, Object>> listMap = BjyijiequHelper.getResourcesAll(start,end,table_name);
				if (listMap.size() <= 0) {
					break;
				}
				BjyijiequDMSHelper.insert(table_name, listMap);
			}
			
		} catch (SQLException e) {
			System.out.println("导入表："+table_name+"报错！");
			e.printStackTrace();
		}
	}

}
