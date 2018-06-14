package com.shareworx.ezfm.baseinfo.area.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYAreaModel领域操作实现
 * @author qiang.gu
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYAreaDomainService.ID)
public class YJWYAreaDomainServiceImpl implements YJWYAreaDomainService {

	public final static String ID = "yJWYAreaDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.area.service.YJWYAreaDomainService#save(com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel[])
	 */
	@Override
	public List<YJWYAreaModel> save(YJWYAreaModel... models) throws ShareworxServiceException {
		return service.save(YJWYAreaModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.area.service.YJWYAreaDomainService#update(com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel[])
	 */
	@Override
	public List<YJWYAreaModel> update(YJWYAreaModel... models) throws ShareworxServiceException {
		return service.update(YJWYAreaModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.area.service.YJWYAreaDomainService#update(java.util.List, com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel[])
	 */
	@Override
	public List<YJWYAreaModel> update(List<String> editFields, YJWYAreaModel... models) throws ShareworxServiceException {
		return service.update(YJWYAreaModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.area.service.YJWYAreaDomainService#delete(com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel[])
	 */
	@Override
	public int delete(YJWYAreaModel... models) throws ShareworxServiceException {
		return service.delete(YJWYAreaModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.area.service.YJWYAreaDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYAreaModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.area.service.YJWYAreaDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.area.service.YJWYAreaDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYAreaModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYAreaModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.area.service.YJWYAreaDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYAreaModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.area.service.YJWYAreaDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYAreaModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.area.service.YJWYAreaDomainService#queryByExample(com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel)
	 */
	@Override
	public List<YJWYAreaModel> queryByExample(YJWYAreaModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYAreaModel.META_ID, model);
	}

}
