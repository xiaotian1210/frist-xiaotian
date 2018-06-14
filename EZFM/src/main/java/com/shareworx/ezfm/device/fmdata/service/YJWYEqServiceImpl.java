package com.shareworx.ezfm.device.fmdata.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.ezfm.device.fmdata.dao.YJWYEqDao;
import com.shareworx.ezfm.device.fmdata.model.YJWYEqModel;
import com.shareworx.ezfm.device.util.DeviceUtil;

/**
 * 设备信息操作实现
 * 
 * @author jin.li
 *
 */
@Service(YJWYEqService.ID)
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class YJWYEqServiceImpl implements YJWYEqService {

	@Autowired
	@Qualifier(YJWYEqDao.ID)
	private YJWYEqDao eqDao;

	@Autowired
	@Qualifier(YJWYEqDomainService.ID)
	YJWYEqDomainService domainService;

	public void setEqDao(YJWYEqDao eqDao) {
		this.eqDao = eqDao;
	}

	@Override
	public List<YJWYEqModel> queryEqModels(String rm_id) {
		List<YJWYEqModel> list = new ArrayList<>();
		if (!DeviceUtil.stringIsEmpty(rm_id)) {
			Query query = Query.from(YJWYEqModel.META_ID);
			query.where(new Condition("rm_id", QueryContents.TYPE_EQ, rm_id));
			list = eqDao.queryListByCondition(query);
		}
		return list;
	}

	@Override
	public int[] saveModels(YJWYEqModel[] yjwyEqModels)throws ShareworxServiceException {

		return eqDao.saveModels(yjwyEqModels);
	}

    @Override
    public YJWYEqModel[] saveModelsByDv(YJWYEqModel[] yjwyEqModels) {
        List<YJWYEqModel> save = domainService.save(yjwyEqModels);
        return save.toArray(yjwyEqModels);
    }

    @Override
	public int[] updateModels(YJWYEqModel[] models) throws ShareworxServiceException {


		return eqDao.updateModels(models);
	}

    @Override
    public YJWYEqModel[] updateModelsByDv(YJWYEqModel[] models) throws ShareworxServiceException {


        return domainService.update(models).toArray(models);
    }
	// @Override
	// public List<YJWYEqModel> queryEqModels(String pk_project, String eq_name)
	// {
	// List<YJWYEqModel> list = null;
	// StringBuilder sql = null;
	// if (!util.stringIsEmpty(pk_project)) {
	// sql = new StringBuilder();
	// sql.append("SELECT
	// eq.eq_id,eq.long_description,eq.csi_id,eq.fm_code,eq.`name`,eq.power,");
	// sql.append("eq.brand,eq.model,eq.factory,eq.use_dept,eq.service_dept,eq.parameter1,");
	// sql.append("eq.parameter2,eq.site_id,eq.use_name,eq.`status`,eq.rm_id,eq.dms_update_time,");
	// sql.append("eq.flag,eq.active,eq.list_code,eq.usual_name,eq.eq_description,eq.pk_crop
	// ");
	// sql.append("FROM yjwy_fmdata_eq AS eq ");
	// String[] siteIds = siteProjectService.queryIds(new String[] { pk_project
	// });
	// if (util.arrayIsNotEmpty(siteIds)) {
	// sql.append(" WHERE "+util.getInNotInSql("eq.site_id",
	// QueryContents.TYPE_IN, siteIds));
	// if (!util.stringIsEmpty(eq_name)) {
	// sql.append(" AND eq.`name` LIKE '" + eq_name +"%' ");
	// }
	// }
	// list = eqDao.queryEqModels(sql.toString());
	// }
	// return list;
	// }

}
