package com.shareworx.ezfm.system.function.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.system.function.model.YJWYFunctionModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYFunctionModel领域操作实现
 * @author qiang.gu
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYFunctionDomainService.ID)
public class YJWYFunctionDomainServiceImpl implements YJWYFunctionDomainService {

	public final static String ID = "yJWYFunctionDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.function.service.YJWYFunctionDomainService#save(com.shareworx.ezfm.system.function.model.YJWYFunctionModel[])
	 */
	@Override
	public List<YJWYFunctionModel> save(YJWYFunctionModel... models) throws ShareworxServiceException {
		return service.save(YJWYFunctionModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.function.service.YJWYFunctionDomainService#update(com.shareworx.ezfm.system.function.model.YJWYFunctionModel[])
	 */
	@Override
	public List<YJWYFunctionModel> update(YJWYFunctionModel... models) throws ShareworxServiceException {
		return service.update(YJWYFunctionModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.function.service.YJWYFunctionDomainService#update(java.util.List, com.shareworx.ezfm.system.function.model.YJWYFunctionModel[])
	 */
	@Override
	public List<YJWYFunctionModel> update(List<String> editFields, YJWYFunctionModel... models) throws ShareworxServiceException {
		return service.update(YJWYFunctionModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.function.service.YJWYFunctionDomainService#delete(com.shareworx.ezfm.system.function.model.YJWYFunctionModel[])
	 */
	@Override
	public int delete(YJWYFunctionModel... models) throws ShareworxServiceException {
		return service.delete(YJWYFunctionModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.function.service.YJWYFunctionDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYFunctionModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.function.service.YJWYFunctionDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.function.service.YJWYFunctionDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYFunctionModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYFunctionModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.function.service.YJWYFunctionDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYFunctionModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.function.service.YJWYFunctionDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYFunctionModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.function.service.YJWYFunctionDomainService#queryByExample(com.shareworx.ezfm.system.function.model.YJWYFunctionModel)
	 */
	@Override
	public List<YJWYFunctionModel> queryByExample(YJWYFunctionModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYFunctionModel.META_ID, model);
	}

}
