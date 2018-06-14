package com.shareworx.ezfm.device.fmdata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.device.fmdata.model.YJWYCsiModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYCsiModel领域操作实现
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYCsiDomainService.ID)
public class YJWYCsiDomainServiceImpl implements YJWYCsiDomainService {

	public final static String ID = "yJWYCsiDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYCsiDomainService#save(com.shareworx.ezfm.device.fmdata.model.YJWYCsiModel[])
	 */
	@Override
	public List<YJWYCsiModel> save(YJWYCsiModel... models) throws ShareworxServiceException {
		return service.save(YJWYCsiModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYCsiDomainService#update(com.shareworx.ezfm.device.fmdata.model.YJWYCsiModel[])
	 */
	@Override
	public List<YJWYCsiModel> update(YJWYCsiModel... models) throws ShareworxServiceException {
		return service.update(YJWYCsiModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYCsiDomainService#update(java.util.List, com.shareworx.ezfm.device.fmdata.model.YJWYCsiModel[])
	 */
	@Override
	public List<YJWYCsiModel> update(List<String> editFields, YJWYCsiModel... models) throws ShareworxServiceException {
		return service.update(YJWYCsiModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYCsiDomainService#delete(com.shareworx.ezfm.device.fmdata.model.YJWYCsiModel[])
	 */
	@Override
	public int delete(YJWYCsiModel... models) throws ShareworxServiceException {
		return service.delete(YJWYCsiModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYCsiDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYCsiModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYCsiDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYCsiDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYCsiModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYCsiModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYCsiDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYCsiModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYCsiDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYCsiModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYCsiDomainService#queryByExample(com.shareworx.ezfm.device.fmdata.model.YJWYCsiModel)
	 */
	@Override
	public List<YJWYCsiModel> queryByExample(YJWYCsiModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYCsiModel.META_ID, model);
	}

}
