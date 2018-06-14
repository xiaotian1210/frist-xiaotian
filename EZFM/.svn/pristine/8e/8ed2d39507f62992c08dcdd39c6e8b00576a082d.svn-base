package com.shareworx.ezfm.app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 连接 sqlite 数据库
 * 
 * @author lingwei.li
 *
 */
public class AppSqliteDBHelperUtils {

	public static String url = "";
	public static String driver = "";
	public static String user = "";
	public static String password = "";
	
	static {
		try {
			url = SysConfigurationReadUtil.getInstance().getConfigItem(
					"url");
			driver = SysConfigurationReadUtil.getInstance().getConfigItem(
					"driver");
			user = SysConfigurationReadUtil.getInstance().getConfigItem(
					"user");
			password = SysConfigurationReadUtil.getInstance().getConfigItem(
					"password");
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	

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
			System.out.println("连接数据库成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 创建新的map 对象用来 重新组建 sql
	 * @param tableName
	 * @return
	 */
	public static Map<String, Object> createTableMap(String tableName) {
		// 新建 map 根据不同的表名创建不同的 map
		Map<String, Object> newMap = new HashMap<String, Object>();

		if ("yjwy_fmdata_room".equals(tableName)) {
			newMap.put("1", "rm_id");
			newMap.put("2", "fm_code");
			newMap.put("3", "name");
			newMap.put("4", "alias");
		} else if ("yjwy_fmdata_pmps".equals(tableName)) {
			newMap.put("1", "pmp_id");
			newMap.put("2", "pmps_id");
			newMap.put("3", "instructions");
			newMap.put("4", "detail_instructions");
		} else if ("yjwy_fmdata_eq".equals(tableName)) {
			newMap.put("1", "eq_id");
			newMap.put("2", "name");
			newMap.put("3", "fm_code");
			newMap.put("4", "eq_description");
			newMap.put("5", "long_description");
			newMap.put("6", "usual_name");
			newMap.put("7", "brand");
			newMap.put("8", "power");
			newMap.put("9", "factory");
			newMap.put("10", "model");
			newMap.put("11", "rm_id");
		}

		return newMap;

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
		stat.executeUpdate(deleteSql);
		
		try {
			pst = con.prepareStatement(insertSql.toString());
			for (Map<String, Object> map : list) {
				for (int i = 0; i < tableMap.size(); i++) {
					String x = String.valueOf(map.get(tableMap.get(String.valueOf(i+1))));
					if (AppEmptyUtils.isEmpty(x)) {
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
	 * 删除操作
	 * 
	 * @param sql
	 * @return
	 */
	public static int clearnDB() {
		int i = 0;
		Connection conn = getConnection();
		String arg[] = { "yjwy_fmdata_eq", "yjwy_fmdata_room",
				"yjwy_fmdata_pmps" };
		try {
			for (int j = 0; j < arg.length; j++) {
				String sql = "delete from " + arg[j];
				stmt = conn.createStatement();
				i += stmt.executeUpdate(sql);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return i;// 如果返回的是1，则执行成功;
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
	}
}
