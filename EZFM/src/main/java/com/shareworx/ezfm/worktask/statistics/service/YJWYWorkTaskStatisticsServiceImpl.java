package com.shareworx.ezfm.worktask.statistics.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shareworx.ezfm.worktask.statistics.dao.YJWYWorkTaskStatisticsDao;

/**
 * 报修相关报表业务操作实现
 * 
 * @author jin.li
 *
 */
@Service(YJWYWorkTaskStatisticsService.ID)
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class YJWYWorkTaskStatisticsServiceImpl implements YJWYWorkTaskStatisticsService {

	@Autowired
	@Qualifier(YJWYWorkTaskStatisticsDao.ID)
	private YJWYWorkTaskStatisticsDao workTaskStatisticsDao;

	public void setWorkTaskStatisticsDao(YJWYWorkTaskStatisticsDao workTaskStatisticsDao) {
		this.workTaskStatisticsDao = workTaskStatisticsDao;
	}

	/**
	 * 查询数据集合
	 */
	public List<Map<String, Object>> queryList(String sql) {
		return workTaskStatisticsDao.queryList(sql);
	}

	/**
	 * 查询总数
	 */
	public Long queryCount(String sql) {
		return workTaskStatisticsDao.queryCount("select count(*) from (" + sql + ") t ");
	}

}
