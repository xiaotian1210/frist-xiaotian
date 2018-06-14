package com.shareworx.ezfm.device.fmdata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.device.fmdata.model.YJWYPmpsModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYPmpsModel领域操作实现
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYPmpsDomainService.ID)
public class YJWYPmpsDomainServiceImpl implements YJWYPmpsDomainService {

	public final static String ID = "yJWYPmpsDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYPmpsDomainService#save(com.shareworx.ezfm.device.fmdata.model.YJWYPmpsModel[])
	 */
	@Override
	public List<YJWYPmpsModel> save(YJWYPmpsModel... models) throws ShareworxServiceException {
		return service.save(YJWYPmpsModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYPmpsDomainService#update(com.shareworx.ezfm.device.fmdata.model.YJWYPmpsModel[])
	 */
	@Override
	public List<YJWYPmpsModel> update(YJWYPmpsModel... models) throws ShareworxServiceException {
		return service.update(YJWYPmpsModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYPmpsDomainService#update(java.util.List, com.shareworx.ezfm.device.fmdata.model.YJWYPmpsModel[])
	 */
	@Override
	public List<YJWYPmpsModel> update(List<String> editFields, YJWYPmpsModel... models) throws ShareworxServiceException {
		return service.update(YJWYPmpsModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYPmpsDomainService#delete(com.shareworx.ezfm.device.fmdata.model.YJWYPmpsModel[])
	 */
	@Override
	public int delete(YJWYPmpsModel... models) throws ShareworxServiceException {
		return service.delete(YJWYPmpsModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYPmpsDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYPmpsModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYPmpsDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYPmpsDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYPmpsModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYPmpsModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYPmpsDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYPmpsModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYPmpsDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYPmpsModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYPmpsDomainService#queryByExample(com.shareworx.ezfm.device.fmdata.model.YJWYPmpsModel)
	 */
	@Override
	public List<YJWYPmpsModel> queryByExample(YJWYPmpsModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYPmpsModel.META_ID, model);
	}

}
