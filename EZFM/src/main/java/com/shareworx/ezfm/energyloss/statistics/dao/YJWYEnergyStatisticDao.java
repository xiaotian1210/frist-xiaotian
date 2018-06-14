package com.shareworx.ezfm.energyloss.statistics.dao;
import java.util.List;
import java.util.Map;

/**
 * 能耗统计持久化操作接口
 * 
 * @author xiaotian.luo
 *
 */
public interface YJWYEnergyStatisticDao {
	String ID = "YJWYEnergyStatisticDao";

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
