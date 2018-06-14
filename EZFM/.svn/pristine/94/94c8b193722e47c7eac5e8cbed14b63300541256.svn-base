package com.shareworx.ezfm.worktask.statistics.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shareworx.platform.persist.DatabaseConnections;

/**
 * 报修相关报表持久化操作实现
 * 
 * @author jin.li
 *
 */
@Repository(YJWYWorkTaskStatisticsDao.ID)
public class YJWYWorkTaskStatisticsDaoImpl implements YJWYWorkTaskStatisticsDao {
	private JdbcTemplate readJdbcTemplate = DatabaseConnections.getReadTemplate();

	/**
	 * 设备列表查询
	 */
	public List<Map<String, Object>> queryList(String sql) {
		return readJdbcTemplate.queryForList(sql);
	}

	/**
	 * 查询总数
	 */
	public Long queryCount(String sql) {
		return readJdbcTemplate.queryForObject(sql, Long.class);
	}

}
