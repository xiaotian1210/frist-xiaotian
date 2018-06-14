package com.shareworx.ezfm.bjyijiequ;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesModel;

public class BjyijiequUtils {

	/**format 年月日时分秒*/
	public static SimpleDateFormat df_YMDHMS=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static void main(String[] args) throws Exception {
		//getBjyijiequAreaDate();
		//getBjyijiequProjectDate();
		//getBjyijiequResourcesDate();
	}

	/**
	 * 获取 区域 数组
	 * @param table_name
	 * @return
	 */
	public static YJWYAreaModel[] getBjyijiequAreaDate() {

		String table_name = "tbb_area";
		
		List<Map<String, Object>> listMap = BjyijiequHelper.getAll(table_name);

		List<YJWYAreaModel> areaModels = new ArrayList<YJWYAreaModel>();

		for (Map<String, Object> map : listMap) {

			YJWYAreaModel areaModel = new YJWYAreaModel();
			areaModel.setPk_area(String.valueOf(map.get("pk_area")));
			areaModel.setArea_code(String.valueOf(map.get("area_code")));
			areaModel.setArea_name(String.valueOf(map.get("area_name")));
			areaModel.setArea_memo(String.valueOf(map.get("area_memo")));
			areaModel.setCreate_user(String.valueOf(map.get("create_user")));
			//因为两个库的时间格式不一样
			String create_time = String.valueOf(map.get("create_time"));
			try {
				
				if ("null".equals(create_time)) {
					areaModel.setCreate_time("0000-00-00 00:00:00");
				}else{
					Date date = df_YMDHMS.parse(create_time);
					areaModel.setCreate_time(df_YMDHMS.format(date));
				}
				
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			//因为两个数据库的 删除标识是反的 
			String delete_flag_default = "0";  //默认未删除
			String state = String.valueOf(map.get("delete_flag"));
			if ("0".equals(state)) {
				delete_flag_default = "1";
			}
			areaModel.setDelete_flag(delete_flag_default);
			areaModels.add(areaModel);
		}
		int size = areaModels.size();

		YJWYAreaModel[] areaModelArrs = (YJWYAreaModel[]) areaModels.toArray(new YJWYAreaModel[size]);
		
		System.out.println("areaModelArrs:"+areaModelArrs.length+"条");
	
		return areaModelArrs;

		/*System.out.println(areaModels);*/
	}
	
	/**
	 * 项目
	 * @return
	 */
	public static YJWYProjectModel[] getBjyijiequProjectDate() {
		
		String table_name = "project_tab";

		List<Map<String, Object>> listMap = BjyijiequHelper.getAll(table_name);

		List<YJWYProjectModel> projectModels = new ArrayList<YJWYProjectModel>();
		
		for (Map<String, Object> map : listMap) {

			YJWYProjectModel projectModel = new YJWYProjectModel();
			projectModel.setPk_project(String.valueOf(map.get("pk_project")));
			projectModel.setPk_area(String.valueOf(map.get("project_id")));
			projectModel.setProject_name(String.valueOf(map.get("project_name")));
			projectModel.setProject_code(String.valueOf(map.get("project_code")));
			projectModel.setProject_memo(String.valueOf(map.get("project_memo")));
			projectModel.setProject_remark(String.valueOf(map.get("project_remark")));
			//因为两个库的时间格式不一样
			String create_time = String.valueOf(map.get("create_time"));
			try {
				if ("null".equals(create_time)) {
					projectModel.setCreate_time("0000-00-00 00:00:00");
				}else{
					Date date = df_YMDHMS.parse(create_time);
					projectModel.setCreate_time(df_YMDHMS.format(date));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			String build = String.valueOf(map.get("project_build_area"));
			if ("null".equals(build)) {
				build = "0";
			}
			projectModel.setProject_build_area(new BigDecimal(build));
			projectModel.setProject_add(String.valueOf(map.get("project_add")));
			projectModel.setDelete_flag(String.valueOf(map.get("delete_flag")));
			projectModel.setSite_lat(String.valueOf(map.get("site_lat")));
			projectModel.setSite_lon(String.valueOf(map.get("site_lon")));
			
			projectModels.add(projectModel);
		}
		
		int size = projectModels.size();

		YJWYProjectModel[] projectModelArrs = (YJWYProjectModel[]) projectModels.toArray(new YJWYProjectModel[size]);
		
		System.out.println("projectModelArrs:"+projectModelArrs.length+"条");
		
		return projectModelArrs;

		
	}
	
	/**
	 * 资源表
	 * @return
	 */
	public static YJWYResourcesModel[] getBjyijiequResourcesDate() {
		
		String table_name = "tbb_building_detail";

		List<Map<String, Object>> listMap = BjyijiequHelper.getAll(table_name);

		List<YJWYResourcesModel> resourceModels = new ArrayList<YJWYResourcesModel>();

		for (Map<String, Object> map : listMap) {

			YJWYResourcesModel resourceModel = new YJWYResourcesModel();
			resourceModel.setPk_resources(String.valueOf(map.get("pk_resources")));
			resourceModel.setResources_name(String.valueOf(map.get("resources_name")));
			resourceModel.setResources_type(String.valueOf(map.get("resources_type")));
			resourceModel.setParent_id(String.valueOf(map.get("parent_id")));
			resourceModel.setProject_id(String.valueOf(map.get("project_id")));
			resourceModels.add(resourceModel);
		}
		int size = resourceModels.size();

		YJWYResourcesModel[] resourceModelArrs = (YJWYResourcesModel[]) resourceModels.toArray(new YJWYResourcesModel[size]);
		
		System.out.println("resourceModelArrs:"+resourceModelArrs.length+"条");
		
		return resourceModelArrs;

	}
}
