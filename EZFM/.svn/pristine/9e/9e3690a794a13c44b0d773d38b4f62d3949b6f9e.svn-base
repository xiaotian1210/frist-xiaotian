package com.shareworx.ezfm.device.fmdata.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shareworx.platform.persist.DatabaseConnections;

/**
 * FM数据同步持久化操作实现
 * @author jin.li
 *
 */
@Repository(YJWYFmDataDao.ID)
public class YJWYFmDataDaoImpl implements YJWYFmDataDao {
	private JdbcTemplate readJdbcTemplate = DatabaseConnections.getReadTemplate();
	
	/**
	 * 查询最大的更新时间
	 */
	public String queryLastUpdateTime(String sql) {
		return readJdbcTemplate.queryForObject(sql, String.class);
	}

	/**
	 * 查询表中数据总数
	 */
	public Long queryCount(String sql) {
		return readJdbcTemplate.queryForObject(sql, Long.class);
	}

}
