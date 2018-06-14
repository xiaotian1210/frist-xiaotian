package com.shareworx.ezfm.worktask.statistics.service;

import java.util.List;
import java.util.Map;

/**
 * 报修相关报表业务操作接口
 * 
 * @author jin.li
 *
 */
public interface YJWYWorkTaskStatisticsService {
	String ID = "yJWYWorkTaskStatisticsService";

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
	 * @return
	 */
	Long queryCount(String sql);
}