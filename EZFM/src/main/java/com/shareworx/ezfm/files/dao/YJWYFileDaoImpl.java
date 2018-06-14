package com.shareworx.ezfm.files.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shareworx.platform.persist.DatabaseConnections;

/**
 * 设备列表持久化操作实现
 * 
 * @author jin.li
 *
 */
@Repository(YJWYFileDao.ID)
public class YJWYFileDaoImpl implements YJWYFileDao {
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
