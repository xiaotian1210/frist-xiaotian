package com.shareworx.ezfm.files.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shareworx.ezfm.files.dao.YJWYFileDao;

/**
 * 设备列表业务操作实现
 * 
 * @author jin.li
 *
 */
@Service(YJWYFileService.ID)
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class YJWYFileServiceImpl implements YJWYFileService {

	@Autowired
	@Qualifier(YJWYFileDao.ID)
	private YJWYFileDao fileDao;

	public void setFileDao(YJWYFileDao fileDao) {
		this.fileDao = fileDao;
	}

	/**
	 * 设备列表查询
	 */
	public List<Map<String, Object>> queryList(String sql) {
		return fileDao.queryList(sql);
	}

	/**
	 * 查询总数
	 */
	public Long queryCount(String sql) {
		return fileDao.queryCount("select count(eq.eq_id) from (" + sql + ") eq");
	}
}
