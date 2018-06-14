package com.shareworx.ezfm.problem.statistics.dao;

import java.util.List;
import java.util.Map;

/**
 * 报事相关报表持久化操作接口
 * 
 * @author jin.li
 *
 */
public interface YJWYProblemStatisticsDao {
	String ID = "yJWYProblemStatisticsDao";

	/**
	 * 查询数据集合
	 * 
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> queryList(String sql);

	/**
	 * 查询总数
	 * 
	 * @param sql
	 * @return
	 */
	Long queryCount(String sql);

}
