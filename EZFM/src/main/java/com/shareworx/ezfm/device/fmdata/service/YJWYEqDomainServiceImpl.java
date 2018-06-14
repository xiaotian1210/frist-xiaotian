package com.shareworx.ezfm.device.fmdata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.device.fmdata.model.YJWYEqModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYEqModel领域操作实现
 * @author zhi.zhang
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYEqDomainService.ID)
public class YJWYEqDomainServiceImpl implements YJWYEqDomainService {

	public final static String ID = "yJWYEqDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYEqDomainService#save(com.shareworx.ezfm.device.fmdata.model.YJWYEqModel[])
	 */
	@Override
	public List<YJWYEqModel> save(YJWYEqModel... models) throws ShareworxServiceException {
		return service.save(YJWYEqModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYEqDomainService#update(com.shareworx.ezfm.device.fmdata.model.YJWYEqModel[])
	 */
	@Override
	public List<YJWYEqModel> update(YJWYEqModel... models) throws ShareworxServiceException {
		return service.update(YJWYEqModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYEqDomainService#update(java.util.List, com.shareworx.ezfm.device.fmdata.model.YJWYEqModel[])
	 */
	@Override
	public List<YJWYEqModel> update(List<String> editFields, YJWYEqModel... models) throws ShareworxServiceException {
		return service.update(YJWYEqModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYEqDomainService#delete(com.shareworx.ezfm.device.fmdata.model.YJWYEqModel[])
	 */
	@Override
	public int delete(YJWYEqModel... models) throws ShareworxServiceException {
		return service.delete(YJWYEqModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYEqDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYEqModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYEqDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYEqDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYEqModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYEqModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYEqDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYEqModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYEqDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYEqModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYEqDomainService#queryByExample(com.shareworx.ezfm.device.fmdata.model.YJWYEqModel)
	 */
	@Override
	public List<YJWYEqModel> queryByExample(YJWYEqModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYEqModel.META_ID, model);
	}

}
