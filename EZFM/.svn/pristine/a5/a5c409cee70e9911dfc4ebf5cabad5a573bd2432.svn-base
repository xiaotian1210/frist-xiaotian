package com.shareworx.ezfm.device.list.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.DatabaseConnections;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.device.fmdata.model.YJWYRoomModel;
import com.shareworx.ezfm.device.list.model.YJWYListMapper;
import com.shareworx.ezfm.device.list.model.YJWYListModel;
import com.shareworx.ezfm.device.siteproject.model.YJWYSiteProjectModel;

/**
 * 设备列表持久化操作实现
 * 
 * @author jin.li
 *
 */
@Repository(YJWYListDao.ID)
public class YJWYListDaoImpl implements YJWYListDao {
	private JdbcTemplate readJdbcTemplate = DatabaseConnections.getReadTemplate();
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;

	/**
	 * 设备列表查询
	 */
	public List<YJWYListModel> queryList(String sql) {
		return readJdbcTemplate.query(sql, new YJWYListMapper());
	}

	/**
	 * 查询总数
	 */
	public Long queryCount(String sql) {
		return readJdbcTemplate.queryForObject(sql, Long.class);
	}

	@Override
	public YJWYRoomModel queryRoom(String sql) {
		try {
			YJWYRoomModel room = readJdbcTemplate.queryForObject(sql, YJWYRoomModel.class);
			return room;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String querySite_id(String sql) {
		try {
			String site_id = readJdbcTemplate.queryForObject(sql, String.class);
			return site_id;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String queryPk_project(String sql) {
		try {
			String pk_project = readJdbcTemplate.queryForObject(sql, String.class);
			return pk_project;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int deleteByCondition(Delete delete) throws ShareworxServiceException {
		return dao.deleteByCondition(delete);
	}

	@Override
	public int deleteByCondition(String sql) {
		return readJdbcTemplate.update(sql);
	}

	@Override
	public String queryPk_area(Query query) {
		YJWYProjectModel project = dao.queryOneByCondition(query);
		try {
			String pk_area = project.getPk_area();
			return pk_area;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String querySite_id(Query query) {
		YJWYSiteProjectModel site_project = dao.queryOneByCondition(query);
		try {
			String site_id = site_project.getSite_id();
			return site_id;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String queryPk_project(Query query) {
		YJWYSiteProjectModel site_project = dao.queryOneByCondition(query);
		try {
			String pk_project = site_project.getPk_project();
			return pk_project;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public YJWYRoomModel queryRoom(Query query) {
		try {
			YJWYRoomModel room = dao.queryOneByCondition(query);
			return room;
		} catch (Exception e) {
			return null;
		}
	}

}
