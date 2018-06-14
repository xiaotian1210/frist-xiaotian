package com.shareworx.ezfm.bjyijiequ.action;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.DatabaseConnections;
import com.shareworx.ezfm.baseinfo.resources.action.YJWYResourcesAction;
import com.shareworx.ezfm.utils.ImpAndExpExcel;

@Controller
@RequestMapping("ezfm/bjyijiequ")
public class BjyijiequAction {
	final static Logger log = Logger.getLogger(YJWYResourcesAction.class);
	private JdbcTemplate readJdbcTemplate = DatabaseConnections.getReadTemplate();
	
	
	/**
	 * 查询操作
	 * @return
	 *//*
	@RequestMapping(value="insert", method=RequestMethod.GET)
	public @ResponseBody JSONObject query(@RequestParam(value="table_name", required=false) String table_name) {
		JSONObject jsonMap = new JSONObject();
		try {
			service.insertBjyijiequDb(table_name);
			jsonMap.put("code", 0);
			jsonMap.put("msg", "success");
		} catch (Exception e) {
			// TODO: handle exception
			jsonMap.put("code", 1);
			jsonMap.put("msg", "false");
		}
		
		return jsonMap;
	}*/
	
	/**
	 * 查询操作
	 * @return
	 */
	@RequestMapping(value="export", method=RequestMethod.GET)
	public @ResponseBody ModelAndResult export() {
		
		List<Map<String,Object>> listMap = readJdbcTemplate.queryForList(getSql());
		
		return ImpAndExpExcel.doExpExcel(listMap, new String[]{"pk_resources","project_name_",
				"e_name", "d_name", "c_name","b_name","a_name","resources_name"}, 
				"templates/templates/quality/资源导入模板.xls", 2);
		
	}
	
	public static String getSql(){
		
		String sql = "select pk_resources,"
				+ "resources_name, ypp.project_name_,"
				+ "a.a_name, b.b_name,c.c_name,d.d_name,"
				+ "e.e_name "
				+ "from yjwy_pub_resources ypr "
				+ "left join yjwy_pub_project ypp "
				+ "on ypp.pk_project_ = ypr.project_id "
				+ "left join "
					//楼层
					+ "(select pk_resources as a_resources,"
					+ "resources_name as a_name,"
					+ "parent_id AS a_parent_id "
					+ "from yjwy_pub_resources "
					+ "where resources_type = '4') a "
				+ "on a.a_resources = ypr.parent_id "
				+ "left join "
					//单元
					+ "(select pk_resources as b_resources,"
					+ "resources_name as b_name,"
					+ "parent_id AS b_parent_id "
					+ "from yjwy_pub_resources "
					+ "where resources_type = '3') b "
				+ "on b.b_resources = a.a_parent_id "
				+ "left join "
					//楼栋
					+ "(select pk_resources as c_resources,"
					+ "resources_name as c_name,"
					+ "parent_id AS c_parent_id "
					+ "from yjwy_pub_resources "
					+ "where resources_type = '2') c "
				+ "on c.c_resources = b.b_parent_id "
				+ "left join "
					//街道
					+ "(select pk_resources as d_resources,"
					+ "resources_name as d_name,"
					+ "parent_id AS d_parent_id "
					+ "from yjwy_pub_resources "
					+ "where resources_type = '6') d "
				+ "on d.d_resources = c.c_parent_id "
				+ "left join "
					//院区
					+ "(select pk_resources as e_resources,"
					+ "resources_name as e_name "
					+ "from yjwy_pub_resources "
					+ "where resources_type = '1') e "
				+ "on e.e_resources = d.d_parent_id "
				+ "where ypr.resources_type = '5' "
				+ "and ypr.project_id in('33','31','53','32','44','21','52','28')";
	
		
		return sql;
		
	}
}
