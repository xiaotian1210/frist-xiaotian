package com.shareworx.ezfm.device.fmdata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.device.fmdata.model.YJWYPmpModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYPmpModel领域操作实现
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYPmpDomainService.ID)
public class YJWYPmpDomainServiceImpl implements YJWYPmpDomainService {

	public final static String ID = "yJWYPmpDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYPmpDomainService#save(com.shareworx.ezfm.device.fmdata.model.YJWYPmpModel[])
	 */
	@Override
	public List<YJWYPmpModel> save(YJWYPmpModel... models) throws ShareworxServiceException {
		return service.save(YJWYPmpModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYPmpDomainService#update(com.shareworx.ezfm.device.fmdata.model.YJWYPmpModel[])
	 */
	@Override
	public List<YJWYPmpModel> update(YJWYPmpModel... models) throws ShareworxServiceException {
		return service.update(YJWYPmpModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYPmpDomainService#update(java.util.List, com.shareworx.ezfm.device.fmdata.model.YJWYPmpModel[])
	 */
	@Override
	public List<YJWYPmpModel> update(List<String> editFields, YJWYPmpModel... models) throws ShareworxServiceException {
		return service.update(YJWYPmpModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYPmpDomainService#delete(com.shareworx.ezfm.device.fmdata.model.YJWYPmpModel[])
	 */
	@Override
	public int delete(YJWYPmpModel... models) throws ShareworxServiceException {
		return service.delete(YJWYPmpModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYPmpDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYPmpModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYPmpDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYPmpDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYPmpModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYPmpModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYPmpDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYPmpModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYPmpDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYPmpModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYPmpDomainService#queryByExample(com.shareworx.ezfm.device.fmdata.model.YJWYPmpModel)
	 */
	@Override
	public List<YJWYPmpModel> queryByExample(YJWYPmpModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYPmpModel.META_ID, model);
	}

}
