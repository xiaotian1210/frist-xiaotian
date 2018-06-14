package com.shareworx.ezfm.device.warn.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.device.util.vo.ParamEntity;
import com.shareworx.ezfm.device.warn.dao.YJWYEbaWarningDao;
import com.shareworx.ezfm.device.warn.model.YJWYEbaWarningModel;

/**
 * YJWYEbaWarningModel领域操作实现
 * 
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYEbaWarningDomainService.ID)
public class YJWYEbaWarningDomainServiceImpl implements YJWYEbaWarningDomainService {

	public final static String ID = "yJWYEbaWarningDomainService";

	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;

	public void setService(BaseDomainService service) {
		this.service = service;
	}

	@Autowired
	@Qualifier(YJWYEbaWarningDao.ID)
	private YJWYEbaWarningDao ebaWarningDao;

	public void setEbaWarningDao(YJWYEbaWarningDao ebaWarningDao) {
		this.ebaWarningDao = ebaWarningDao;
	}

	@Override
	public List<Map<String, Object>> queryList(ParamEntity params) {
		return ebaWarningDao.queryList(this.getSqlString(params, true));
	}

	@Override
	public Long queryCount(ParamEntity params) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(w.report_id) from (" + this.getSqlString(params, false) + ") w");
		return ebaWarningDao.queryCount(sql.toString());
	}

	/**
	 * 获取sql语句
	 * 
	 * @return
	 */
	private String getSqlString(ParamEntity params, boolean flag) {
		Integer page = params.getPage();
		Integer rows = params.getRows();
		StringBuilder sql = new StringBuilder();
		sql.append("select warn.report_id report_id,warn.device_code device_code,");
		sql.append("warn.warning_content warning_content,warn.warning_value warning_value,");
		sql.append("warn.warning_time warning_time,warn.warn_state warn_state,");
		sql.append("warn.pk_crop pk_crop,warn.eq_id eq_id,eq.`name` eq_name,");
		sql.append("eq.rm_id rm_id,rm.`name` rm_name,eq.site_id site_id,");
		sql.append("site.`name` site_name,spa.project_name project_name,");
		sql.append("spa.pk_project pk_project,spa.pk_area pk_area,");
		sql.append("spa.area_name area_name from yjwy_eba_warning warn ");
		sql.append("LEFT JOIN yjwy_fmdata_eq eq ON warn.eq_id = eq.eq_id ");
		sql.append("LEFT JOIN yjwy_fmdata_room rm ON eq.rm_id = rm.rm_id ");
		sql.append("LEFT JOIN yjwy_fmdata_site site ON site.site_id = eq.site_id ");
		sql.append("LEFT JOIN ( select ");
		sql.append("sp.site_id site_id,sp.pk_project pk_project,site.`name` site_name,");
		sql.append("pro.project_name_ project_name,pro.pk_area_ pk_area,area.area_name_ area_name ");
		sql.append("from yjwy_fmdata_site_project sp ");
		sql.append("JOIN yjwy_fmdata_site site ON sp.site_id = site.site_id  ");
		sql.append("JOIN yjwy_pub_project pro ON sp.pk_project = pro.pk_project_  ");
		sql.append("JOIN yjwy_pub_area area ON pro.pk_area_ = area.pk_area_  ");
		sql.append(") spa ON spa.site_id = site.site_id ");
		sql.append(" where warn.pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		sql.append(" order by warn.warning_time desc ");
		if (flag && null != page && null != rows) {
			sql.append(" LIMIT " + (page - 1) * rows + "," + rows);
		}
		return sql.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.device.warn.service.YJWYEbaWarningDomainService#save(
	 * com.shareworx.ezfm.device.warn.model.YJWYEbaWarningModel[])
	 */
	@Override
	public List<YJWYEbaWarningModel> save(YJWYEbaWarningModel... models) throws ShareworxServiceException {
		return service.save(YJWYEbaWarningModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.device.warn.service.YJWYEbaWarningDomainService#update
	 * (com.shareworx.ezfm.device.warn.model.YJWYEbaWarningModel[])
	 */
	@Override
	public List<YJWYEbaWarningModel> update(YJWYEbaWarningModel... models) throws ShareworxServiceException {
		return service.update(YJWYEbaWarningModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.device.warn.service.YJWYEbaWarningDomainService#update
	 * (java.util.List,
	 * com.shareworx.ezfm.device.warn.model.YJWYEbaWarningModel[])
	 */
	@Override
	public List<YJWYEbaWarningModel> update(List<String> editFields, YJWYEbaWarningModel... models) throws ShareworxServiceException {
		return service.update(YJWYEbaWarningModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.device.warn.service.YJWYEbaWarningDomainService#delete
	 * (com.shareworx.ezfm.device.warn.model.YJWYEbaWarningModel[])
	 */
	@Override
	public int delete(YJWYEbaWarningModel... models) throws ShareworxServiceException {
		return service.delete(YJWYEbaWarningModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.device.warn.service.YJWYEbaWarningDomainService#
	 * deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYEbaWarningModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.device.warn.service.YJWYEbaWarningDomainService#
	 * deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.device.warn.service.YJWYEbaWarningDomainService#
	 * queryById(java.lang.String)
	 */
	@Override
	public YJWYEbaWarningModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYEbaWarningModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.device.warn.service.YJWYEbaWarningDomainService#
	 * queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYEbaWarningModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.device.warn.service.YJWYEbaWarningDomainService#
	 * queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYEbaWarningModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.device.warn.service.YJWYEbaWarningDomainService#
	 * queryByExample(com.shareworx.ezfm.device.warn.model.YJWYEbaWarningModel)
	 */
	@Override
	public List<YJWYEbaWarningModel> queryByExample(YJWYEbaWarningModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYEbaWarningModel.META_ID, model);
	}

}
