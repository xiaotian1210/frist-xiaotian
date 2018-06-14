package com.shareworx.ezfm.device.siteproject.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.ezfm.device.siteproject.dao.YJWYSiteProjectDao;
import com.shareworx.ezfm.device.siteproject.model.YJWYSiteProjectModel;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.device.util.vo.ParamEntity;

/**
 * FM与YJWY项目关联表业务操作实现
 * 
 * @author jin.li
 *
 */
@Service(YJWYSiteProjectService.ID)
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class YJWYSiteProjectServiceImpl implements YJWYSiteProjectService {
	@Autowired
	@Qualifier(YJWYSiteProjectDao.ID)
	private YJWYSiteProjectDao siteProjectDao;

	public void setSiteProjectDao(YJWYSiteProjectDao siteProjectDao) {
		this.siteProjectDao = siteProjectDao;
	}

	/**
	 * 查询项目关联信息
	 */
	@Override
	public List<Map<String, Object>> queryMap(ParamEntity params) {
		Integer page = null;
		Integer rows = null;
		if (null != params) {
			page = params.getPage();
			rows = params.getRows();
		}
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("sp.site_id site_id,site.name name,sp.pk_crop pk_crop,");
		sql.append("sp.pk_project pk_project,pro.project_name_ project_name_ ");
		sql.append("FROM yjwy_fmdata_site_project sp ");
		sql.append("LEFT JOIN yjwy_fmdata_site site ");
		sql.append("ON sp.site_id = site.site_id ");
		sql.append("LEFT JOIN yjwy_pub_project pro ");
		sql.append("ON sp.pk_project = pro.pk_project_ ");
		sql.append("where sp.pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		sql.append("ORDER BY sp.site_id,sp.pk_project ");
		if (null != page && null != rows) {
			sql.append(" LIMIT " + (page - 1) * rows + "," + rows);
		}
		return siteProjectDao.queryMap(sql.toString());
	}

	/**
	 * 根据YJWY项目id查询FM项目id数组
	 */
	@Override
	public String[] queryIds(String[] pk_project) {
		String[] siteIds = new String[0];
		if (DeviceUtil.arrayIsNotEmpty(pk_project)) {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("site_id ");
			sql.append("from yjwy_fmdata_site_project ");
			sql.append("where " + DeviceUtil.getInNotInSql("pk_project", QueryContents.TYPE_IN, pk_project));
			List<String> siteIdList = siteProjectDao.queryIds(sql.toString());
			siteIds = new String[siteIdList.size()];
			for (int i = 0; i < siteIdList.size(); i++) {
				siteIds[i] = siteIdList.get(i);
			}
		}
		return siteIds;
	}

	/**
	 * 删除
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
	public YJWYSiteProjectModel[] delete(YJWYSiteProjectModel[] models) {
		for (YJWYSiteProjectModel yjwySiteProjectModel : models) {
			Delete delete = Delete.delete(YJWYSiteProjectModel.META_ID);
			delete.where(new Condition("site_id", QueryContents.TYPE_EQ, yjwySiteProjectModel.getSite_id()));
			delete.and(new Condition("pk_project", QueryContents.TYPE_EQ, yjwySiteProjectModel.getPk_project()));
			siteProjectDao.deleteByCondition(delete);
		}
		return models;
	}

}
