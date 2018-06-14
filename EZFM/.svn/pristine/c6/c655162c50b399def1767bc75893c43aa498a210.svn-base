package com.shareworx.ezfm.bjyijiequ;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.druid.util.StringUtils;
import com.shareworx.ezfm.app.util.AppIdBuilder;
/**
 * 
 * @author lingwei.li
 *
 */
public class BjyijiequHelper {

	/**format 年月日时分秒*/
	public static SimpleDateFormat df_YMDHMS=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static String url = "jdbc:mysql://t.bjyijiequ.com:3306/rds_sync_20161102?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String driver = "com.mysql.jdbc.Driver";
	public static String user = "root";
	public static String password = "rootyuanyang";

	public static Connection conn = null;
	public static PreparedStatement pst = null; // 执行动态SQL语句
	public static Statement stmt = null; // 执行静态SQL语句
	public static ResultSet rs = null; // 执行查询数据库的SQL语句，返回一个结果集（ResultSet）对象。

	//是否增量插入
	public static Boolean isIncrement =  false;
	
	/**
	 * 连接数据库
	 * 
	 * @param sql
	 */
	public static Connection getConnection() {
		try {
			Class.forName(driver);// 指定连接类型
			conn = DriverManager.getConnection(url, user, password);// 获取连接
			System.out.println("连接-YIBEI-数据库成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 关闭连接
	 */
	public static void close() {
		if (rs != null) { // 关闭记录集
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) { // 关闭声明
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (pst != null) { // 关闭声明
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null) { // 关闭连接对象
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		System.out.println("关闭-YIBEI-数据库连接！");
	}

	/**
	 * 获取区域表（tbb_area）中的数据
	 * 
	 * @return
	 */
	public static List<Map<String, Object>> getAll(String table_name) {
		// 需要返回的List<Map<String, Object>>
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		
		// 需要执行的sql语句
		String sql = getSql(table_name);
		
		// 创建连接
		Connection conn = getConnection();

		try {
			stmt = (Statement) conn.createStatement();

			ResultSet result = stmt.executeQuery(sql);

			while (result.next()) {
				Map<String, Object> map = getResultMap(table_name,result);
				listMap.add(map);
			}
			close();
			System.out.println(table_name+"查询数："+listMap.size());
			return listMap;
		} catch (SQLException e) {
			close();
			e.printStackTrace();
		}

		// 关闭连接
		close();
		return null;

	}
	
	
	/**
	 * 获取区域表（tbb_area）中的数据
	 * 
	 * @return
	 */
	public static List<Map<String, Object>> getResourcesAll(String start, String end, String table_name) {
		// 需要返回的List<Map<String, Object>>
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		
		// 需要执行的sql语句
		String sql = getResourcesSql(start,end,table_name);
		
		// 创建连接
		Connection conn = getConnection();

		try {
			stmt = (Statement) conn.createStatement();

			ResultSet result = stmt.executeQuery(sql);

			while (result.next()) {
				Map<String, Object> map = getResultMap(table_name,result);
				listMap.add(map);
			}
			close();
			System.out.println(table_name+"查询数："+listMap.size()+",从"+start+"开始,共查"+end+"条。");
			return listMap;
		} catch (SQLException e) {
			close();
			e.printStackTrace();
		}

		// 关闭连接
		close();
		return null;

	}
	
	
	/**
	 * 获取sql 语句 （数据不是很多的 直接查询所有的）
	 * @param table_name
	 * @return
	 */
	public static String getSql(String table_name){
		
		// 需要执行的sql语句
		String sql = "";
		
		//区域表
		if ("yjwy_pub_area".equals(table_name)) {
			sql = "select area_id,area_name," 
					+ "area_code,backup_info,operator," 
					+ "operate_date, state "
					+ "from tbb_area";
		}
		//项目表
		else if("yjwy_pub_project".equals(table_name)){
			sql = "select project_id,area_id,"
					+ "project_name, project_code, "
					+ "project_desc, remark, "
					+ "create_date, building_area, "
					+ "project_address, state, "
					+ "latitude, longitude "
					+ "from project_tab"; 
		}
		//角色信息
		else if("yjwy_pub_role".equals(table_name)){
			sql = "select role_id,create_date,"
					+ "creator, operate_date, "
					+ "operator, role_code, "
					+ "role_desc, role_name "
					+ "from ts_role where state = '1'"; 
		}
		//组织架构信息
		else if("yjwy_pub_org".equals(table_name)){
			sql = "select arch_id,arch_code,"
					+ "arch_desc, arch_name, "
					+ "arch_type, create_date, "
					+ "creator, operate_date,"
					+ "operator, sort, state, "
					+ "area_id, parent_id, project_id "
					+ "from ts_company_arch "; 
		}
		//岗位构信息
		else if("yjwy_pub_station".equals(table_name)){
			sql = "select job_id,create_date,"
					+ "creator, job_code, "
					+ "job_dept, job_level, "
					+ "job_name, operate_date,"
					+ "operator, sort, state, "
					+ "arch_id, parent_id, post_level "
					+ "from ts_job_arch "; 
		}
		//用户信息
		else if("yjwy_pub_user".equals(table_name)){
			sql = "select user_id,apply_state,"
					+ "create_date, creator, "
					+ "jobNumber, operate_date, "
					+ "operator, pwd,"
					+ "state, telephone, user_account, "
					+ "user_name, last_modify_date "
					+ "from user_tab "; 
		}
		//标准版本
		else if("yjwy_quality_standardedition".equals(table_name)){
			sql = "select standard_template_id,areaids,"
					+ "create_date, create_user_id, "
					+ "del_flag, template_name "
					+ "from ts_standard_template "; 
		}
		//核查标准
		else if("yjwy_quality_inspectstandard".equals(table_name)){
			String whr = " where 1 = 1 ";
			if (isIncrement) {
				whr = whr + " and uid >= '690' ";
			}
			sql = "select uid,category,"
					+ "category_subdivesion, competent, "
					+ "create_date, creator, "
					+ "department, qpi_desc,"
					+ "eval_score, joblevel_one, joblevel_three, "
					+ "joblevel_two, merification_mehtods, "
					+ "operate_date, operator, project,"
					+ "propessional, qpiCode, qpipro, "
					+ "scope_app, state, standard_template_id "
					+ "from qualityperind_tab "
					+ whr; 
		}
		//问题类型
		else if("yjwy_quality_problemtype".equals(table_name)){
			sql = "select ques_id,create_date,"
					+ "creator, limit_day, "
					+ "operate_date, operator, "
					+ "ques_name, state "
					+ "from record_ques_type "; 
		}
		//报事分类
		else if("yjwy_problem_class".equals(table_name)){
			sql = "select server_id,project_property,"
					+ "server_code, server_name, "
					+ "sort, state, "
					+ "submit_date, submitor,"
					+ "take_order, parent_id, "
					+ "process_limited, need_isit "
					+ "from crm_server_type "
					+ "where state = '1' "
					+ "and server_cate = '-2' "; 
		}
		//维修种类
		else if("yjwy_worktask_repair_class".equals(table_name)){
			String whr = " where 1 = 1 ";
			if (isIncrement) {
				whr = whr + " and cate_id >= '3739' ";
			}
			sql = "select cate_id,broadcast,"
					+ "cate_name, category, "
					+ "create_date, creator, "
					+ "cross_specialty, labour_cost,"
					+ "man_hour, material_cost, "
					+ "odd_number, operate_date, "
					+ "operator, professional, "
					+ "sort, standard, support_category, "
					+ "unit, parent_id "
					+ "from tbm_maintain_cate "
					+ whr
					+ "and state = '1' "; 
		}
		//片区人员
		else if("yjwy_worktask_area_personnel".equals(table_name)){
			sql = "select rs_id, certificate,"
					+ "create_date, creator, "
					+ "operate_date, operator, "
					+ "has_common_spec, rs_desc,"
					+ "specialty, specialty_five, "
					+ "specialty_four, specialty_three, "
					+ "specialty_two, user_id "
					+ "from tbm_maintain_repairuser "
					+ "where state = '1' "; 
		}
		//片区信息
		else if("yjwy_worktask_area_details".equals(table_name)){
			sql = "select team_id, area_id,"
					+ "create_date, creator, "
					+ "operate_date, operator, "
					+ "team_name "
					+ "from tbm_repair_team "
					+ "where state = '1' "; 
		}
		//用户岗位关系表
		else if("yjwy_pub_user_station".equals(table_name)){
			sql = "select job_id, user_id from tr_job_user "; 
		}
		//用户角色关系表
		else if("yjwy_pub_role_user".equals(table_name)){
			sql = "select role_id, user_id from tr_role_user "; 
		}
		//人员定义
		else if("yjwy_worktask_project_user".equals(table_name)){
			sql = "select tud.define_id, tuu.user_id, "
					+ "tud.create_date, tud.creator, "
					+ "tud.project_id "
					+ "from tbm_user_define tud "
					+ "LEFT JOIN tr_userdefine_user tuu "
					+ "ON tuu.define_id = tud.define_id "
					+ "WHERE tud.state = '1'"; 
		}
		//用户角色关系表
		else if("yjwy_quality_standard_station".equals(table_name)){
			sql = "select job_id, uid from tr_job_qpi "; 
		}
		
		//片区项目关系
		else if("yjwy_worktask_area_project_nexus".equals(table_name)){
			sql = "select team_id, project_id from tr_team_project "; 
		}
		
		//岗位和版本关系
		else if("yjwy_quality_edition_station".equals(table_name)){
			sql = "select job_id, standard_template_id from tzr_job_template "; 
		}
		
		//片区维修人员
		else if("yjwy_worktask_area_user_nexus_2".equals(table_name)){
			sql = "select ttr.team_id, ttr.rs_id, tmr.user_id "
					+ "from tr_team_repairuser ttr "
					+ "left join tbm_maintain_repairuser tmr "
					+ "on ttr.rs_id = tmr.rs_id ";
		}
		
		//片区主管人员
		else if("yjwy_worktask_area_user_nexus_1".equals(table_name)){
			sql = "select team_id, user_id "
					+ "from tr_team_user ";
		}
		
		//片区调度人员
		else if("yjwy_worktask_area_user_nexus_3".equals(table_name)){
			sql = "select team_id, user_id "
					+ "from tr_team_dispatch ";
		}
		
		//项目维修种类关系
		else if("yjwy_worktask_repair_class_project".equals(table_name)){
			sql = "select project_cate_id, cate_id, "
					+ "create_date, creator, "
					+ "operate_date, operator, "
					+ "project_id "
					+ "from tbm_maintain_project_cate "
					+ "where state = '1' ";
		}
		
		//项目报事种类关系
		else if("yjwy_proinfo_class_nexus".equals(table_name)){
			sql = "select project_id, server_types "
					+ "from crm_project_define "
					+ "where state = '1' and cate = '-2' ";
		}
	
		
		
		return sql;
		
	}
	
	/**
	 * 获取sql 语句  (数据多的 需要分批查询)
	 * @param table_name
	 * @return
	 */
	public static String getResourcesSql(String start, String end, String table_name){
		
		// 需要执行的sql语句
		String sql = "";
		
		//是否增量插入
		String whr = " where 1 = 1 ";
		if (isIncrement) {
			whr = whr + "and building_id >= '211119' ";
		}
		
		//资源表
		if("yjwy_pub_resources".equals(table_name)){
			sql = "select building_id, building_location, "
					+ "building_type, parent_id, project_id "
					+ "from tbb_building_detail "
					+ whr
					+ "order by building_id asc "
					+ "limit "+start+","+end;
		}
		return sql;
		
	}
	
	/**
	 * 查询出来的 结果需要 转化的 值
	 * @param table_name
	 * @param result
	 * @return
	 */
	public static Map<String, Object> getResultMap(String table_name, ResultSet result){
		Map<String, Object> map = new HashMap<String,Object>();
		
		try {
			//区域表
			if ("yjwy_pub_area".equals(table_name)) {
				//---------dms-----------------一碑--------------
				map.put("pk_area_", result.getString("area_id"));
				map.put("area_name_", result.getString("area_name"));
				map.put("area_code_", result.getString("area_code"));
				map.put("area_memo_", result.getString("backup_info"));
				map.put("create_user_", result.getString("operator"));
				//因为时间的 原因 需要转换
				map.put("create_time_", changeDate(result.getString("operate_date")));
				//因为 一碑和dms 删除标识相反
				map.put("delete_flag_", changeState(result.getString("state")));
			}
			//项目表
			else if ("yjwy_pub_project".equals(table_name)) {
				//---------dms-----------------一碑--------------
				map.put("pk_project_", result.getString("project_id"));
				map.put("pk_area_", result.getString("area_id"));
				map.put("project_name_", result.getString("project_name"));
				map.put("project_code_", result.getString("project_code"));
				map.put("project_memo_", result.getString("project_desc"));
				map.put("project_remark_", result.getString("remark"));
				//因为时间的 原因 需要转换
				map.put("create_time_", changeDate(result.getString("create_date")));
				map.put("project_build_area_", result.getString("building_area"));
				map.put("project_add_", result.getString("project_address"));
				//因为 一碑和dms 删除标识相反
				map.put("delete_flag_", changeState(result.getString("state")));
				map.put("site_lat_", result.getString("latitude"));
				map.put("site_lon_", result.getString("longitude"));
			}
			//资源表
			else if ("yjwy_pub_resources".equals(table_name)) {
				//---------dms-----------------一碑--------------
				map.put("pk_resources", result.getString("building_id"));
				map.put("resources_name", result.getString("building_location"));
				map.put("resources_type", result.getString("building_type"));
				map.put("parent_id", result.getString("parent_id"));
				map.put("project_id", result.getString("project_id"));
			}
			//角色
			else if ("yjwy_pub_role".equals(table_name)) {
				//---------dms-----------------一碑--------------
				map.put("pk_role_", result.getString("role_id"));
				map.put("create_time_", changeDate(result.getString("create_date")));
				map.put("create_user_", result.getString("creator"));
				map.put("last_modify_time_", changeDate(result.getString("operate_date")));
				map.put("last_modify_user_", result.getString("operator"));
				map.put("role_code_", result.getString("role_code"));
				map.put("memo_", result.getString("role_desc"));
				map.put("role_name_", result.getString("role_name"));
				map.put("is_pre_", "0");//一碑没有 dms有  设置默认值
			}
			//组织架构
			else if ("yjwy_pub_org".equals(table_name)) {
				//---------dms-----------------一碑--------------
				map.put("pk_org_", result.getString("arch_id"));
				map.put("org_code_", result.getString("arch_code"));
				map.put("memo_", result.getString("arch_desc"));
				map.put("org_name_", result.getString("arch_name"));
				map.put("org_type_", result.getString("arch_type"));
				map.put("create_time_", changeDate(result.getString("create_date")));
				map.put("create_user_", result.getString("creator"));
				map.put("last_modify_time_", changeDate(result.getString("operate_date")));
				map.put("last_modify_user_", result.getString("operator"));
				map.put("sort_", result.getString("sort"));
				map.put("delete_flag_", changeState(result.getString("state")));
				map.put("org_area_", result.getString("area_id"));
				//如果 一碑中的 parent_id = null 则说明为根目录 设置为 root
				String pk_parent = result.getString("parent_id");
				if (StringUtils.isEmpty(pk_parent)) {
					pk_parent = "root";
				}
				map.put("pk_parent_", pk_parent);
				map.put("org_project_", result.getString("project_id"));
			}
			//岗位架构
			else if ("yjwy_pub_station".equals(table_name)) {
				//---------dms-----------------一碑--------------
				map.put("pk_station_", result.getString("job_id"));
				map.put("create_time_", changeDate(result.getString("create_date")));
				map.put("create_user_", result.getString("creator"));
				map.put("station_code_", result.getString("job_code"));
				map.put("pk_dept_", result.getString("job_dept"));
				map.put("task_level_", result.getString("job_level"));
				map.put("station_name_", result.getString("job_name"));
				map.put("last_modify_time_", changeDate(result.getString("operate_date")));
				map.put("last_modify_user_", result.getString("operator"));
				map.put("sort_", result.getString("sort"));
				map.put("delete_flag_", changeState(result.getString("state")));
				map.put("pk_org_", result.getString("arch_id"));
				map.put("pk_parent_", result.getString("parent_id"));
				map.put("station_level_", result.getString("post_level"));
			}
			//用户信息
			else if ("yjwy_pub_user".equals(table_name)) {
				//---------dms-----------------一碑--------------
				map.put("pk_user_", result.getString("user_id"));
				//默认没有启用
				map.put("is_able_", "0");
				map.put("create_user_", result.getString("creator"));
				map.put("create_time_", changeDate(result.getString("create_date")));
				map.put("last_modify_time_", changeDate(result.getString("operate_date")));
				map.put("last_modify_user_", result.getString("operator"));
				map.put("em_code_", result.getString("jobNumber"));
				map.put("password_", result.getString("pwd"));
				map.put("phone_", result.getString("telephone"));
				map.put("delete_flag_", changeState(result.getString("state")));
				map.put("user_code_", result.getString("user_account"));
				map.put("user_name_", result.getString("user_name"));
				map.put("update_time_", changeDate(result.getString("last_modify_date")));
				//系统预置
				map.put("is_pre_", "0");
				//是否签到
				map.put("is_sign_", "0");
			}
			//标准版本
			else if ("yjwy_quality_standardedition".equals(table_name)) {
				//---------dms-----------------一碑--------------
				map.put("pk_edition", result.getString("standard_template_id"));
				map.put("area_ids", result.getString("areaids"));
				map.put("create_user", result.getString("create_user_id"));
				map.put("create_time", changeDate(result.getString("create_date")));
				map.put("is_valid", changeState(result.getString("del_flag")));
				map.put("edition_name", result.getString("template_name"));
			}
			//核查标准
			else if ("yjwy_quality_inspectstandard".equals(table_name)) {
				//---------dms-----------------一碑--------------
				map.put("pk_inspstan", result.getString("uid"));
				map.put("inspstan_category", result.getString("category"));
				map.put("inspstan_category_description", result.getString("category_subdivesion"));
				map.put("inspstan_dkzg_pc", result.getString("competent"));
				map.put("inspstan_bmjl_pc", result.getString("department"));
				map.put("inspstan_performance_norm", result.getString("qpi_desc"));
				String inspstan_scorevalue = "0";
				if (!StringUtils.isEmpty(result.getString("eval_score"))) {
					inspstan_scorevalue = result.getString("eval_score");
				}
				map.put("inspstan_scorevalue", inspstan_scorevalue);
				map.put("inspstan_qyzz_pc", result.getString("joblevel_one"));
				map.put("inspstan_zj_pc", result.getString("joblevel_three"));
				map.put("inspstan_qyz_pc", result.getString("joblevel_two"));
				map.put("inspstan_inpectmethod", result.getString("merification_mehtods"));
				map.put("inspstan_xmjl_pc", result.getString("project"));
				map.put("specialty", result.getString("propessional"));
				map.put("inspstan_code", result.getString("qpiCode"));
				map.put("project_category", result.getString("qpipro"));
				map.put("inspstan_usingscope", result.getString("scope_app"));
				map.put("is_valid", changeState(result.getString("state")));
				map.put("fk_standardedition", result.getString("standard_template_id"));
				//一碑没有用到
				map.put("update_time", String.valueOf(new Date().getTime()));
				map.put("update_user", result.getString("operator"));
				map.put("create_time", changeDate(result.getString("create_date")));
				map.put("create_user", result.getString("creator"));
				//是否强制拍照
				map.put("inspstan_secretinquiries", "0");
			}
			//问题类型
			else if ("yjwy_quality_problemtype".equals(table_name)) {
				//---------dms-----------------一碑--------------
				map.put("pk_problem", result.getString("ques_id"));
				map.put("problem_rectify_days", result.getString("limit_day"));
				map.put("create_user", result.getString("creator"));
				map.put("create_time", changeDate(result.getString("create_date")));
				String update_time = result.getString("operate_date");
				if (!StringUtils.isEmpty(update_time)) {
					update_time = changeDate(result.getString("operate_date"));
					try {
						Date update_date = df_YMDHMS.parse(update_time);
						
						map.put("update_time", update_date.getTime());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}else{
					map.put("update_time", "");
				}
				map.put("update_user", result.getString("operator"));
				map.put("is_valid", changeState(result.getString("state")));
				map.put("problem_name", result.getString("ques_name"));
			}
			//报事分类
			else if ("yjwy_problem_class".equals(table_name)) {
				//---------dms-----------------一碑--------------
				map.put("pk_class_id", result.getString("server_id"));
				map.put("project_attribute", result.getString("project_property"));
				map.put("class_code", result.getString("server_code"));
				map.put("class_name", result.getString("server_name"));
				//如果sort = "" 则默认设置为 0 
				String sort = result.getString("sort");
				if (StringUtils.isEmpty(sort)) {
					sort = "0";
				}
				map.put("sort", sort);
				map.put("state", changeState(result.getString("state")));
				//设置默认值 默认不支持
				String whether_repair = result.getString("take_order");
				if (StringUtils.isEmpty(whether_repair)) {
					whether_repair = "2";
				}		
				map.put("whether_repair", whether_repair);
				map.put("parent_id", result.getString("parent_id"));
				//设置默认值
				String time_limit = result.getString("process_limited");
				if (StringUtils.isEmpty(time_limit)) {
					time_limit = "0";
				}
				map.put("time_limit", time_limit);
				//设置默认值 默认不支持
				String whether_visit = result.getString("need_isit");
				if (StringUtils.isEmpty(whether_visit)) {
					whether_visit = "2";
				}
				map.put("whether_visit", whether_visit);
				map.put("create_time", changeDate(result.getString("submit_date")));
				map.put("create_user_id", result.getString("submitor"));
				//dms 需要设置 root 值
				String root = "0";
				if (StringUtils.isEmpty(result.getString("parent_id"))) {
					root = "1";
				}
				map.put("root", root);
			}
			//维修种类
			else if ("yjwy_worktask_repair_class".equals(table_name)) {
				//---------dms-----------------一碑--------------
				map.put("pk_class_id", result.getString("cate_id"));
				//设置默认值
				String whether_broadcast = result.getString("broadcast");
				if (StringUtils.isEmpty(whether_broadcast)) {
					whether_broadcast = "0";
				}
				map.put("whether_broadcast", whether_broadcast);
				map.put("class_code", result.getString("odd_number"));
				map.put("class_name", result.getString("cate_name"));
				map.put("repair_class", result.getString("category"));
				String whether_cross_specialty = result.getString("cross_specialty");
				if (StringUtils.isEmpty(whether_cross_specialty)) {
					whether_cross_specialty = "0";
				}
				map.put("whether_cross_specialty", whether_cross_specialty);
				//设置默认值 人工费用
				String labor_cost = result.getString("labour_cost");
				if (StringUtils.isEmpty(labor_cost)) {
					labor_cost = "0";
				}
				map.put("labor_cost", labor_cost);
				//设置默认值 （额定工时）
				String rated_worktime = result.getString("man_hour");
				if (StringUtils.isEmpty(rated_worktime)) {
					rated_worktime = "0";
				}
				map.put("rated_worktime", rated_worktime);
				//设置默认值 材料费用
				String material_cost = result.getString("material_cost");
				if (StringUtils.isEmpty(material_cost)) {
					material_cost = "0";
				}
				map.put("material_cost", material_cost);
				map.put("service_major", result.getString("professional"));
				//设置默认值 （排序）
				String sort = result.getString("sort");
				if (StringUtils.isEmpty(sort)) {
					sort = "0";
				}
				map.put("sort", sort);
				map.put("specifications", result.getString("standard"));
				map.put("support_category", result.getString("support_category"));
				map.put("company", result.getString("unit"));
				map.put("parent_id", result.getString("parent_id"));
				map.put("create_time", changeDate(result.getString("create_date")));
				map.put("create_user_id", result.getString("creator"));
				String update_time = result.getString("operate_date");
				if (!StringUtils.isEmpty(update_time)) {
					update_time = changeDate(result.getString("operate_date"));
					try {
						Date update_date = df_YMDHMS.parse(update_time);
						map.put("update_time", update_date.getTime());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					map.put("update_time", "");
				}
				
				map.put("update_user_id", result.getString("operator"));
			}
			//片区人员
			else if ("yjwy_worktask_area_personnel".equals(table_name)) {
				//---------dms-----------------一碑--------------
				map.put("pk_personnel_id", result.getString("rs_id"));
				map.put("certificate", result.getString("certificate"));
				map.put("whether_apply_all", result.getString("has_common_spec"));
				map.put("remarks", result.getString("rs_desc"));
				map.put("major_one", result.getString("specialty"));
				map.put("major_five", result.getString("specialty_five"));
				map.put("major_four", result.getString("specialty_four"));
				map.put("major_three", result.getString("specialty_three"));
				map.put("major_two", result.getString("specialty_two"));
				map.put("user_id", result.getString("user_id"));
				map.put("create_time", changeDate(result.getString("create_date")));
				map.put("create_user_id", result.getString("creator"));
				String update_time = result.getString("operate_date");
				if (!StringUtils.isEmpty(update_time)) {
					update_time = changeDate(result.getString("operate_date"));
					try {
						Date update_date = df_YMDHMS.parse(update_time);
						map.put("update_time", update_date.getTime());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					map.put("update_time", "");
				}
				map.put("update_user_id", result.getString("operator"));
			}
			//片区信息
			else if ("yjwy_worktask_area_details".equals(table_name)) {
				//---------dms-----------------一碑--------------
				map.put("pk_area_id", result.getString("team_id"));
				map.put("fk_region_id", result.getString("area_id"));
				map.put("area_name", result.getString("team_name"));
				map.put("create_time", changeDate(result.getString("create_date")));
				map.put("create_user_id", result.getString("creator"));
				map.put("update_time", changeDate(result.getString("operate_date")));
				map.put("update_user_id", result.getString("operator"));
			}
			
			//用户岗位关系
			else if ("yjwy_pub_user_station".equals(table_name)) {
				//---------dms-----------------一碑--------------
				map.put("pk_station_", result.getString("job_id"));
				map.put("pk_user_", result.getString("user_id"));
			}
			
			//用户角色关系
			else if ("yjwy_pub_role_user".equals(table_name)) {
				//---------dms-----------------一碑--------------
				map.put("pk_role_", result.getString("role_id"));
				map.put("pk_user_", result.getString("user_id"));
			}
			//人员定义
			else if ("yjwy_worktask_project_user".equals(table_name)) {
				//---------dms-----------------一碑--------------
				map.put("pk_nexus_id", AppIdBuilder.createRandomId());
				map.put("create_time", changeDate(result.getString("create_date")));
				map.put("create_user_id", result.getString("creator"));
				map.put("user_id", result.getString("user_id"));
				map.put("project_id", result.getString("project_id"));
				map.put("nexus_type", "1");
			}
			
			//片区信息
			else if ("yjwy_quality_standard_station".equals(table_name)) {
				map.put("pk_station", result.getString("job_id"));
				map.put("pk_standard", result.getString("uid"));
			}
			
			//用户角色关系
			else if ("yjwy_worktask_area_project_nexus".equals(table_name)) {
				//---------dms-----------------一碑--------------
				map.put("pk_nexus_id", AppIdBuilder.createRandomId());
				map.put("area_id", result.getString("team_id"));
				map.put("project_id", result.getString("project_id"));
			}
			
			//岗位和版本关系
			else if ("yjwy_quality_edition_station".equals(table_name)) {
				//---------dms-----------------一碑--------------
				map.put("pk_station", result.getString("job_id"));
				map.put("pk_edition", result.getString("standard_template_id"));
			}
			
			//片区维修人员关系
			else if ("yjwy_worktask_area_user_nexus_2".equals(table_name)) {
				//---------dms-----------------一碑--------------
				map.put("pk_nexus_id", AppIdBuilder.createRandomId());
				map.put("area_id", result.getString("team_id"));
				map.put("personnel_id", result.getString("rs_id"));
				map.put("user_id", result.getString("user_id"));
				map.put("user_type", "2");
			}
			
			//片区主管人员关系
			else if ("yjwy_worktask_area_user_nexus_1".equals(table_name)) {
				//---------dms-----------------一碑--------------
				map.put("pk_nexus_id", AppIdBuilder.createRandomId());
				map.put("area_id", result.getString("team_id"));
				map.put("user_id", result.getString("user_id"));
				map.put("user_type", "1");
			}
			
			//片区调度人员关系
			else if ("yjwy_worktask_area_user_nexus_3".equals(table_name)) {
				//---------dms-----------------一碑--------------
				map.put("pk_nexus_id", AppIdBuilder.createRandomId());
				map.put("area_id", result.getString("team_id"));
				map.put("user_id", result.getString("user_id"));
				map.put("user_type", "3");
			}
			//维修种类和项目的关系
			else if ("yjwy_worktask_repair_class_project".equals(table_name)) {
				//---------dms-----------------一碑--------------
				map.put("pk_nexus_id", result.getString("project_cate_id"));
				map.put("class_id", result.getString("cate_id"));
				map.put("create_time", changeDate(result.getString("create_date")));
				map.put("create_user_id", result.getString("creator"));
				
				String update_time = result.getString("operate_date");
				if (!StringUtils.isEmpty(update_time)) {
					update_time = changeDate(result.getString("operate_date"));
					try {
						Date update_date = df_YMDHMS.parse(update_time);
						map.put("update_time", update_date.getTime());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					map.put("update_time", "");
				}
				
				map.put("update_user_id", result.getString("operator"));
				map.put("project_id", result.getString("project_id"));
			}
			
			//项目报事关系
			else if ("yjwy_proinfo_class_nexus".equals(table_name)) {
				//---------dms-----------------一碑--------------
				map.put("project_id", result.getString("project_id"));
				map.put("class_id", result.getString("server_types"));
			}
			
			return map;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		
		
		return null;
		
	}
	
	/**
	 * 时间转换（因为 一碑和 dms 两个数据库设计的 时间格式 不一样）
	 * @param date
	 * @return
	 */
	public static String changeDate(String date){
		String default_date = "0000-00-00 00:00:00";
		if (StringUtils.isEmpty(date)) {
			return default_date;
		}else{
			try {
				Date newDate = df_YMDHMS.parse(date);
				default_date = df_YMDHMS.format(newDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return default_date;
		}
	}
	
	/**
	 * 转换 删除标识 （以为一碑 和 dms 做的删除标识 是反正的 故做此转换）
	 * @param state
	 * @return
	 */
	public static String changeState(String state){
		String delete_state = "0";  //默认未删除
		if ("0".equals(state)) {
			delete_state = "1";
		}
		return delete_state;
		
	}

}
