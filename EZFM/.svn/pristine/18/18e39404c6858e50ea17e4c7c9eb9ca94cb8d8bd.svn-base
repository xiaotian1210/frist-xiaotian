package com.shareworx.ezfm.bjyijiequ;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BjyijiequDMSHelper {

	//公司测试数据库
	/*public static String url = "jdbc:mysql://192.168.0.173:8121/yqwy_1107?useUnicode=true&characterEncoding=UTF8";
	public static String driver = "com.mysql.jdbc.Driver";
	public static String user = "root";
	public static String password = "dms@888"*/;
	
	//阿里云
	/*public static String url = "jdbc:mysql://rm-2ze8a65tey06qqfd0o.mysql.rds.aliyuncs.com:3306/yqwy?useUnicode=true&characterEncoding=UTF8";
	public static String driver = "com.mysql.jdbc.Driver";
	public static String user = "yqwy";
	public static String password = "yqwy@2016";*/
	
	//本地
	public static String url = "jdbc:mysql://127.0.0.1:3306/yqwy_1109?useUnicode=true&characterEncoding=UTF8";
	public static String driver = "com.mysql.jdbc.Driver";
	public static String user = "root";
	public static String password = "root";

	public static Connection conn = null;
	public static PreparedStatement pst = null; // 执行动态SQL语句
	public static Statement stmt = null; // 执行静态SQL语句
	public static ResultSet rs = null; // 执行查询数据库的SQL语句，返回一个结果集（ResultSet）对象。

	/**
	 * 连接数据库
	 * 
	 * @param sql
	 */
	public static Connection getConnection() {
		try {
			Class.forName(driver);// 指定连接类型
			conn = DriverManager.getConnection(url, user, password);// 获取连接
			System.out.println("连接-DMS-数据库成功");
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

		System.out.println("关闭-DMS-数据库连接！");
	}
	
	/**
	 * 插入操作
	 * 
	 * @param sql
	 * @return
	 * @throws SQLException 
	 */
	public static int insert(String tableName, List<Map<String, Object>> list) throws SQLException {

		int num = 0; // 新增计数

		Map<String, Object> tableMap = createTableMap(tableName);
		
		//因为一碑的分了三个 关系表存区域人员关系  dms用了一个表存储
		if ("yjwy_worktask_area_user_nexus_3".equals(tableName) 
				|| "yjwy_worktask_area_user_nexus_1".equals(tableName)
				|| "yjwy_worktask_area_user_nexus_2".equals(tableName)) {
			tableName = "yjwy_worktask_area_user_nexus";
		}
		
		//列数和列名称
		String value = "";
		String columnName = "";
		for(int i = 0; i < tableMap.size(); i++) {
			value += "?,";
			columnName += tableMap.get(String.valueOf(i+1))+",";
		}
		value = value.substring(0, value.length()-1);
		columnName = columnName.substring(0, columnName.length()-1);
		
		// 通用insert SQL
		StringBuffer insertSql = new StringBuffer();
		insertSql.append("insert into " + tableName);
		insertSql.append(" ("+columnName+")");
		insertSql.append(" values (" + value + ")");

		Connection con = getConnection();
		//在新增的时候 先 通过id删除 防止修改后的数据再次添加 id 重复
		String deleteIds = "";
		for (Map<String, Object> map : list) {
			deleteIds = deleteIds+"'"+map.get(tableMap.get(String.valueOf(1)))+"',";
		}
		deleteIds = deleteIds.substring(0, deleteIds.length()-1);
		Statement stat = con.createStatement();
		String deleteSql = "delete from "+tableName+" where "+tableMap.get(String.valueOf(1))+" in ("+deleteIds+")";
		//执行删除操作
		stat.executeUpdate(deleteSql);
		
		try {
			pst = con.prepareStatement(insertSql.toString());
			for (Map<String, Object> map : list) {
				for (int i = 0; i < tableMap.size(); i++) {
					String x = String.valueOf(map.get(tableMap.get(String.valueOf(i+1))));
					if ("null".equals(x) || x.length()==0) {
						x = " ";
					}
					pst.setString(i+1, x);
				}
				num += pst.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		System.out.println(tableName+"新增数："+num);
		return num;// 返回影响的行数，1为执行成功
	}
	
	/**
	 * 创建新的map 对象用来 重新组建 sql
	 * @param tableName
	 * @return
	 */
	public static Map<String, Object> createTableMap(String table_name) {
		// 新建 map 根据不同的表名创建不同的 map
		Map<String, Object> map = new HashMap<String, Object>();

		//区域
		if ("yjwy_pub_area".equals(table_name)) {
			map.put("1", "pk_area_");
			map.put("2", "area_name_");
			map.put("3", "area_code_");
			map.put("4", "area_memo_");
			map.put("5", "create_user_");
			map.put("6", "create_time_");
			map.put("7", "delete_flag_");
			
		} 
		//项目
		else if ("yjwy_pub_project".equals(table_name)) {
			map.put("1", "pk_project_");
			map.put("2", "pk_area_");
			map.put("3", "project_name_");
			map.put("4", "project_code_");
			map.put("5", "project_memo_");
			map.put("6", "project_remark_");
			map.put("7", "create_time_");
			map.put("8", "project_build_area_");
			map.put("9", "project_add_");
			map.put("10", "delete_flag_");
			map.put("11", "site_lat_");
			map.put("12", "site_lon_");
			
		} 
		//资源
		else if ("yjwy_pub_resources".equals(table_name)) {
			map.put("1", "pk_resources");
			map.put("2", "resources_name");
			map.put("3", "resources_type");
			map.put("4", "parent_id");
			map.put("5", "project_id");
		}
		//角色
		else if ("yjwy_pub_role".equals(table_name)) {
			map.put("1", "pk_role_");
			map.put("2", "create_time_");
			map.put("3", "create_user_");
			map.put("4", "last_modify_time_");
			map.put("5", "last_modify_user_");
			map.put("6", "role_code_");
			map.put("7", "memo_");
			map.put("8", "role_name_");
			map.put("9", "is_pre_");//一碑没有 dms有  设置默认值
		}
		//组织架构
		else if ("yjwy_pub_org".equals(table_name)) {
			map.put("1", "pk_org_");
			map.put("2", "org_code_");
			map.put("3", "memo_");
			map.put("4", "org_name_");
			map.put("5", "org_type_");
			map.put("6", "create_time_");
			map.put("7", "create_user_");
			map.put("8", "last_modify_time_");
			map.put("9", "last_modify_user_");
			map.put("10", "sort_");
			map.put("11", "delete_flag_");
			map.put("12", "org_area_");
			map.put("13", "pk_parent_");
			map.put("14", "org_project_");
		}
		//岗位架构
		else if ("yjwy_pub_station".equals(table_name)) {
			map.put("1", "pk_station_");
			map.put("2", "create_time_");
			map.put("3", "create_user_");
			map.put("4", "station_code_");
			map.put("5", "pk_dept_");
			map.put("6", "task_level_");
			map.put("7", "station_name_");
			map.put("8", "last_modify_time_");
			map.put("9", "last_modify_user_");
			map.put("10", "sort_");
			map.put("11", "delete_flag_");
			map.put("12", "pk_org_");
			map.put("13", "pk_parent_");
			map.put("14", "station_level_");
		}
		//用户信息
		else if ("yjwy_pub_user".equals(table_name)) {
			map.put("1", "pk_user_");
			map.put("2", "is_able_");
			map.put("3", "create_user_");
			map.put("4", "create_time_");
			map.put("5", "last_modify_time_");
			map.put("6", "last_modify_user_");
			map.put("7", "em_code_");
			map.put("8", "password_");
			map.put("9", "phone_");
			map.put("10", "delete_flag_");
			map.put("11", "user_code_");
			map.put("12", "user_name_");
			map.put("13", "update_time_");
			//系统预置
			map.put("14", "is_pre_");
			//是否签到
			map.put("15", "is_sign_");
		}
		//标准版本
		else if ("yjwy_quality_standardedition".equals(table_name)) {
			map.put("1", "pk_edition");
			map.put("2", "area_ids");
			map.put("3", "create_user");
			map.put("4", "create_time");
			map.put("5", "is_valid");
			map.put("6", "edition_name");
		}
		//核查标准
		else if ("yjwy_quality_inspectstandard".equals(table_name)) {
			map.put("1", "pk_inspstan");
			map.put("2", "inspstan_category");
			map.put("3", "inspstan_category_description");
			map.put("4", "inspstan_dkzg_pc");
			map.put("5", "inspstan_bmjl_pc");
			map.put("6", "inspstan_performance_norm");
			map.put("7", "inspstan_scorevalue");
			map.put("8", "inspstan_qyzz_pc");
			map.put("9", "inspstan_zj_pc");
			map.put("10", "inspstan_qyz_pc");
			map.put("11", "inspstan_inpectmethod");
			map.put("12", "inspstan_xmjl_pc");
			map.put("13", "specialty");
			map.put("14", "inspstan_code");
			map.put("15", "project_category");
			map.put("16", "inspstan_usingscope");
			map.put("17", "is_valid");
			map.put("18", "fk_standardedition");
			map.put("19", "update_time");
			map.put("20", "update_user");
			map.put("21", "create_time");
			map.put("22", "create_user");
			//是否强制拍照
			map.put("23", "inspstan_secretinquiries");
		}
		//问题类型
		else if ("yjwy_quality_problemtype".equals(table_name)) {
			map.put("1", "pk_problem");
			map.put("2", "problem_rectify_days");
			map.put("3", "create_user");
			map.put("4", "create_time");
			map.put("5", "update_time");
			map.put("6", "update_user");
			map.put("7", "is_valid");
			map.put("8", "problem_name");
		}
		//报事分类
		else if ("yjwy_problem_class".equals(table_name)) {
			map.put("1", "pk_class_id");
			map.put("2", "project_attribute");
			map.put("3", "class_code");
			map.put("4", "class_name");
			map.put("5", "sort");
			map.put("6", "state");
			map.put("7", "whether_repair");
			map.put("8", "parent_id");
			map.put("9", "time_limit");
			map.put("10", "whether_visit");
			map.put("11", "create_time");
			map.put("12", "create_user_id");
			map.put("13", "root");
		}
		//维修种类
		else if ("yjwy_worktask_repair_class".equals(table_name)) {
			map.put("1", "pk_class_id");
			map.put("2", "whether_broadcast");
			map.put("3", "class_code");
			map.put("4", "class_name");
			map.put("5", "repair_class");
			map.put("6", "whether_cross_specialty");
			map.put("7", "labor_cost");
			map.put("8", "rated_worktime");
			map.put("9", "material_cost");
			map.put("10", "service_major");
			map.put("11", "sort");
			map.put("12", "specifications");
			map.put("13", "support_category");
			map.put("14", "company");
			map.put("15", "parent_id");
			map.put("16", "create_time");
			map.put("17", "create_user_id");
			map.put("18", "update_time");
			map.put("19", "update_user_id");
		}
		//片区人员
		else if ("yjwy_worktask_area_personnel".equals(table_name)) {
			map.put("1", "pk_personnel_id");
			map.put("2", "certificate");
			map.put("3", "whether_apply_all");
			map.put("4", "remarks");
			map.put("5", "major_one");
			map.put("6", "major_five");
			map.put("7", "major_four");
			map.put("8", "major_three");
			map.put("9", "major_two");
			map.put("10", "user_id");
			map.put("11", "create_time");
			map.put("12", "create_user_id");
			map.put("13", "update_time");
			map.put("14", "update_user_id");
		}
		//片区信息
		else if ("yjwy_worktask_area_details".equals(table_name)) {
			map.put("1", "pk_area_id");
			map.put("2", "fk_region_id");
			map.put("3", "area_name");
			map.put("4", "create_time");
			map.put("5", "create_user_id");
			map.put("6", "update_time");
			map.put("7", "update_user_id");
		}
		
		//片区信息
		else if ("yjwy_pub_user_station".equals(table_name)) {
			map.put("1", "pk_station_");
			map.put("2", "pk_user_");
		}
		
		//用户角色关系
		else if ("yjwy_pub_role_user".equals(table_name)) {
			//---------dms-----------------一碑--------------
			map.put("1","pk_role_");
			map.put("2","pk_user_");
		}
		
		//人员定义
		else if ("yjwy_worktask_project_user".equals(table_name)) {
			//---------dms-----------------一碑--------------
			map.put("1", "pk_nexus_id");
			map.put("2", "create_time");
			map.put("3", "create_user_id");
			map.put("4", "user_id");
			map.put("5", "project_id");
			map.put("6", "nexus_type");
			
		}
		//片区信息
		else if ("yjwy_quality_standard_station".equals(table_name)) {
			map.put("1", "pk_station");
			map.put("2", "pk_standard");
		}
		
		//用户角色关系
		else if ("yjwy_worktask_area_project_nexus".equals(table_name)) {
			//---------dms-----------------一碑--------------
			map.put("1", "pk_nexus_id");
			map.put("2", "area_id" );
			map.put("3", "project_id");
		}
		
		//岗位和版本关系
		else if ("yjwy_quality_edition_station".equals(table_name)) {
			//---------dms-----------------一碑--------------
			map.put("1", "pk_station");
			map.put("2", "pk_edition");
		}
		
		//片区维修人员关系
		else if ("yjwy_worktask_area_user_nexus_2".equals(table_name)) {
			//---------dms-----------------一碑--------------
			map.put("1", "pk_nexus_id");
			map.put("2", "area_id");
			map.put("3", "personnel_id");
			map.put("4", "user_id");
			map.put("5", "user_type");
		}
		
		//片区主管人员关系
		else if ("yjwy_worktask_area_user_nexus_1".equals(table_name)) {
			//---------dms-----------------一碑--------------
			map.put("1", "pk_nexus_id");
			map.put("2", "area_id");
			map.put("3", "user_id");
			map.put("4", "user_type");
		}
		
		//片区调度人员关系
		else if ("yjwy_worktask_area_user_nexus_3".equals(table_name)) {
			//---------dms-----------------一碑--------------
			map.put("1", "pk_nexus_id");
			map.put("2", "area_id");
			map.put("3", "user_id");
			map.put("4", "user_type");
		}
		
		//维修种类和项目的关系
		else if ("yjwy_worktask_repair_class_project".equals(table_name)) {
			//---------dms-----------------一碑--------------
			map.put("1", "pk_nexus_id");
			map.put("2", "class_id");
			map.put("3", "create_time");
			map.put("4", "create_user_id");
			map.put("5", "update_time");
			map.put("6", "update_user_id");
			map.put("7", "project_id");
		}
		
		//项目报事关系
		else if ("yjwy_proinfo_class_nexus".equals(table_name)) {
			//---------dms-----------------一碑--------------
			map.put("1", "nexus_id");
			map.put("2", "project_id");
			map.put("3", "class_id");
			map.put("4", "create_time");
		}
		return map;

	}
	
}
