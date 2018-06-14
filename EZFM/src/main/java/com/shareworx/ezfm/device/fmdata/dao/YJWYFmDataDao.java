package com.shareworx.ezfm.device.fmdata.dao;

/**
 * FM数据同步持久化操作接口
 * @author jin.li
 *
 */
public interface YJWYFmDataDao {
	String ID = "yJWYFmDataDao";
	
	/**
	 * 查询最大更新时间
	 * @param sql
	 * @param clazz
	 * @return
	 */
	String queryLastUpdateTime(String sql);
	
	/**
	 * 查询表中数据总数
	 * @param sql
	 * @return
	 */
	Long queryCount(String sql);
}
