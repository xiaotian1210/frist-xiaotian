package com.shareworx.ezfm.problem.statistics.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shareworx.ezfm.problem.statistics.dao.YJWYProblemStatisticsDao;

/**
 * 报修相关报表业务操作实现
 * 
 * @author jin.li
 *
 */
@Service(YJWYProblemStatisticsService.ID)
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class YJWYProblemStatisticsServiceImpl implements YJWYProblemStatisticsService {

	@Autowired
	@Qualifier(YJWYProblemStatisticsDao.ID)
	private YJWYProblemStatisticsDao statisticsDao;

	public void setStatisticsDao(YJWYProblemStatisticsDao statisticsDao) {
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
