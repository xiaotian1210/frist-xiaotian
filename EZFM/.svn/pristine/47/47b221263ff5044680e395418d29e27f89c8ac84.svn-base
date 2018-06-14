package com.shareworx.ezfm.device.fmdata_eq.service;

import com.shareworx.ezfm.device.fmdata_eq.model.YJWYEqSysModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * YJWYEqSysModel领域操作实现
 * @author zhi.zhang
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYEqSysDomainService.ID)
public class YJWYEqSysDomainServiceImpl implements YJWYEqSysDomainService {

	public final static String ID = "yJWYEqSysDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata_eq.service.YJWYEqSysDomainService#save(com.shareworx.ezfm.device.fmdata_eq.model.YJWYEqSysModel[])
	 */
	@Override
	public List<YJWYEqSysModel> save(YJWYEqSysModel... models) throws ShareworxServiceException {
		return service.save(YJWYEqSysModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata_eq.service.YJWYEqSysDomainService#update(com.shareworx.ezfm.device.fmdata_eq.model.YJWYEqSysModel[])
	 */
	@Override
	public List<YJWYEqSysModel> update(YJWYEqSysModel... models) throws ShareworxServiceException {
		return service.update(YJWYEqSysModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata_eq.service.YJWYEqSysDomainService#update(java.util.List, com.shareworx.ezfm.device.fmdata_eq.model.YJWYEqSysModel[])
	 */
	@Override
	public List<YJWYEqSysModel> update(List<String> editFields, YJWYEqSysModel... models) throws ShareworxServiceException {
		return service.update(YJWYEqSysModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata_eq.service.YJWYEqSysDomainService#delete(com.shareworx.ezfm.device.fmdata_eq.model.YJWYEqSysModel[])
	 */
	@Override
	public int delete(YJWYEqSysModel... models) throws ShareworxServiceException {
		return service.delete(YJWYEqSysModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata_eq.service.YJWYEqSysDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYEqSysModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata_eq.service.YJWYEqSysDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata_eq.service.YJWYEqSysDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYEqSysModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYEqSysModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata_eq.service.YJWYEqSysDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYEqSysModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata_eq.service.YJWYEqSysDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYEqSysModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata_eq.service.YJWYEqSysDomainService#queryByExample(com.shareworx.ezfm.device.fmdata_eq.model.YJWYEqSysModel)
	 */
	@Override
	public List<YJWYEqSysModel> queryByExample(YJWYEqSysModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYEqSysModel.META_ID, model);
	}

}
