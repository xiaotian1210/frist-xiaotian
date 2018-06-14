package com.shareworx.ezfm.table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.pool.PreparedStatementPool.MethodType;
import com.alibaba.fastjson.JSONObject;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.DatabaseConnections;
import com.shareworx.ezfm.utils.ImpAndExpExcel;

@Controller
@RequestMapping("ezfm/table/datastructure")
public class TableDataStructureAction {
	@RequestMapping(value="index", method=RequestMethod.GET)
	public ModelAndView forward(HttpServletRequest req){
		return new ModelAndView("ezfm/table/download_datastructure");
	}
	
	@RequestMapping(value="export/{table}",method=RequestMethod.GET)
	public @ResponseBody ModelAndResult doExpDataStructureByTable(HttpServletRequest req,@PathVariable String table){
		JdbcTemplate readTemplate = DatabaseConnections.getWriteTemplate();
		String tableSql = " select * from meta_class where table_ = '"+table+"' ";
		List<JSONObject> tablelist = readTemplate.query(tableSql, new RowMapper<JSONObject>(){
			@Override
			public JSONObject mapRow(ResultSet rs, int index) throws SQLException {
				JSONObject json = new JSONObject();
				json.put("id_", rs.getString("id_"));
				json.put("name_", rs.getString("name_"));
				json.put("table_", rs.getString("table_"));
				json.put("ormclass_", rs.getString("ormclass_"));
				json.put("pkg_", rs.getString("pkg_"));
				return json;
			}
		});
		if(null==tablelist || tablelist.size()<1){
			return new ModelAndResult(false,"您查寻的表结构不存，请重新输入表名称，谢谢！");
		}
		String filedSql = " select column_,define_,length_,name_ from meta_field where meta_ = '"+tablelist.get(0).getString("id_")+"' order by sort_ ";
		List<JSONObject> filedList = readTemplate.query(filedSql, new RowMapper<JSONObject>(){
			@Override
			public JSONObject mapRow(ResultSet rs, int index) throws SQLException {
				JSONObject json = new JSONObject();
				json.put("column_", rs.getString("column_"));
				json.put("define_", rs.getString("define_"));
				json.put("length_", rs.getString("length_"));
				json.put("name_", rs.getString("name_"));
				return json;
			}
		});
		//return ImpAndExpExcel.download("templates/templates/数据结构.xls");
		return ImpAndExpExcel.doExpExcel(filedList, new String[]{"column_","define_","length_","name_"}, "templates/templates/数据结构.xls", 2);
	}
}
