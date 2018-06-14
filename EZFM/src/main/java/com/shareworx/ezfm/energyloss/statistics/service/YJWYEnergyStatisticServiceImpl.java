package com.shareworx.ezfm.energyloss.statistics.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.energyloss.statistics.dao.YJWYEnergyStatisticDao;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 能耗统计业务操作实现
 * 
 * @author xiaotian.luo
 *
 */
@Service(YJWYEnergyStatisticService.ID)
public class YJWYEnergyStatisticServiceImpl implements YJWYEnergyStatisticService {

	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;

	@Autowired
	@Qualifier(YJWYEnergyStatisticDao.ID)
	private YJWYEnergyStatisticDao statisticsDao;

	public void setStatisticsDao(YJWYEnergyStatisticDao statisticsDao) {
		this.statisticsDao = statisticsDao;
	}

	/**
	 * 查询数据集合
	 */
	public List<Map<String, Object>> queryList(String sql) {
		return statisticsDao.queryList(sql);
	}

	/**
	 * 查询总数
	 */
	public Long queryCount(String sql) {
		return statisticsDao.queryCount("select count(*) from (" + sql + ") a ");
	}

}
