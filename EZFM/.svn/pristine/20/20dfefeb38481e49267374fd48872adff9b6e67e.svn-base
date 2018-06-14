package com.shareworx.ezfm.device.rough.dao;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shareworx.platform.persist.DatabaseConnections;

/**
 * 设备概况持久化操作实现
 * 
 * @author jin.li
 *
 */
@Repository(YJWYRoughDao.ID)
public class YJWYRoughDaoImpl implements YJWYRoughDao {
	private JdbcTemplate readJdbcTemplate = DatabaseConnections.getReadTemplate();

	/**
	 * 查询设备概况数值
	 * 
	 * @param sql
	 * @return
	 */
	@Override
	public Map<String, Object> queryRatio(String sql) {
		return readJdbcTemplate.queryForMap(sql);
	}

}
