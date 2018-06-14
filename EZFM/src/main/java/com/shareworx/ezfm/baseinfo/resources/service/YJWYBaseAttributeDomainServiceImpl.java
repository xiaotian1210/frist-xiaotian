package com.shareworx.ezfm.baseinfo.resources.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.resources.model.YJWYBaseAttributeModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * yjwyBaseAttributeModel领域操作实现
 * @author Administrator
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYBaseAttributeDomainService.ID)
public class YJWYBaseAttributeDomainServiceImpl implements YJWYBaseAttributeDomainService {

	public final static String ID = "yjwyBaseAttributeDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.yjwyBaseAttributeDomainService#save(com.shareworx.ezfm.baseinfo.resources.model.yjwyBaseAttributeModel[])
	 */
	@Override
	public List<YJWYBaseAttributeModel> save(YJWYBaseAttributeModel... models) throws ShareworxServiceException {
		return service.save(YJWYBaseAttributeModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.yjwyBaseAttributeDomainService#update(com.shareworx.ezfm.baseinfo.resources.model.yjwyBaseAttributeModel[])
	 */
	@Override
	public List<YJWYBaseAttributeModel> update(YJWYBaseAttributeModel... models) throws ShareworxServiceException {
		return service.update(YJWYBaseAttributeModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.yjwyBaseAttributeDomainService#update(java.util.List, com.shareworx.ezfm.baseinfo.resources.model.yjwyBaseAttributeModel[])
	 */
	@Override
	public List<YJWYBaseAttributeModel> update(List<String> editFields, YJWYBaseAttributeModel... models) throws ShareworxServiceException {
		return service.update(YJWYBaseAttributeModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.yjwyBaseAttributeDomainService#delete(com.shareworx.ezfm.baseinfo.resources.model.yjwyBaseAttributeModel[])
	 */
	@Override
	public int delete(YJWYBaseAttributeModel... models) throws ShareworxServiceException {
		return service.delete(YJWYBaseAttributeModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.yjwyBaseAttributeDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYBaseAttributeModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.yjwyBaseAttributeDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.yjwyBaseAttributeDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYBaseAttributeModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYBaseAttributeModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.yjwyBaseAttributeDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYBaseAttributeModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.yjwyBaseAttributeDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYBaseAttributeModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.yjwyBaseAttributeDomainService#queryByExample(com.shareworx.ezfm.baseinfo.resources.model.yjwyBaseAttributeModel)
	 */
	@Override
	public List<YJWYBaseAttributeModel> queryByExample(YJWYBaseAttributeModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYBaseAttributeModel.META_ID, model);
	}

}
