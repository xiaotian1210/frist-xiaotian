package com.shareworx.ezfm.system.dict.service;

import java.util.List;

import com.shareworx.ezfm.cache.annotation.DMSCacheEvict;
import com.shareworx.ezfm.cache.annotation.DMSCacheable;
import com.shareworx.ezfm.cache.constants.CacheConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.system.dict.model.YJWYDictModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYDictModel领域操作实现
 * @author ying.chen
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYDictDomainService.ID)
public class YJWYDictDomainServiceImpl implements YJWYDictDomainService {

	public final static String ID = "yJWYDictDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.dict.service.YJWYDictDomainService#save(com.shareworx.ezfm.system.dict.model.YJWYDictModel[])
	 */
	@Override
	@DMSCacheEvict(namespace = "'"+ CacheConstants.DICT_CACHA_NAMESPACE+"'", allEntries="true")
	public List<YJWYDictModel> save(YJWYDictModel... models) throws ShareworxServiceException {
		return service.save(YJWYDictModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.dict.service.YJWYDictDomainService#update(com.shareworx.ezfm.system.dict.model.YJWYDictModel[])
	 */
	@Override
	@DMSCacheEvict(namespace = "'"+ CacheConstants.DICT_CACHA_NAMESPACE+"'", allEntries="true")
	public List<YJWYDictModel> update(YJWYDictModel... models) throws ShareworxServiceException {
		return service.update(YJWYDictModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.dict.service.YJWYDictDomainService#update(java.util.List, com.shareworx.ezfm.system.dict.model.YJWYDictModel[])
	 */
	@Override
	@DMSCacheEvict(namespace = "'"+ CacheConstants.DICT_CACHA_NAMESPACE+"'", allEntries="true")
	public List<YJWYDictModel> update(List<String> editFields, YJWYDictModel... models) throws ShareworxServiceException {
		return service.update(YJWYDictModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.dict.service.YJWYDictDomainService#delete(com.shareworx.ezfm.system.dict.model.YJWYDictModel[])
	 */
	@Override
	@DMSCacheEvict(namespace = "'"+ CacheConstants.DICT_CACHA_NAMESPACE+"'", allEntries="true")
	public int delete(YJWYDictModel... models) throws ShareworxServiceException {
		return service.delete(YJWYDictModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.dict.service.YJWYDictDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	@DMSCacheEvict(namespace = "'"+ CacheConstants.DICT_CACHA_NAMESPACE+"'", allEntries="true")
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYDictModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.dict.service.YJWYDictDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	@DMSCacheEvict(namespace = "'"+ CacheConstants.DICT_CACHA_NAMESPACE+"'", allEntries="true")
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.dict.service.YJWYDictDomainService#queryById(java.lang.String)
	 */
	@Override
	@DMSCacheable(namespace = "'"+ CacheConstants.DICT_CACHA_NAMESPACE+"'" ,key = "#_methodSignature+','+#id")
	public YJWYDictModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYDictModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.dict.service.YJWYDictDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override

	public YJWYDictModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.dict.service.YJWYDictDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYDictModel> queryListByCondition(Query query) throws ShareworxServiceException {
		List<YJWYDictModel> list = service.queryListByCondition(query);
		return list;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.dict.service.YJWYDictDomainService#queryByExample(com.shareworx.ezfm.system.dict.model.YJWYDictModel)
	 */
	@Override
	public List<YJWYDictModel> queryByExample(YJWYDictModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYDictModel.META_ID, model);
	}

	@Override
	//根据code返回单个对象
	@DMSCacheable(namespace = "'"+ CacheConstants.DICT_CACHA_NAMESPACE+"'",key = "#_methodSignature+','+#code")
	public YJWYDictModel getDict(String code) throws ShareworxServiceException {
		Query query = Query.from(YJWYDictModel.META_ID);
		query.addSelect(new String[]{"pk_dict_","dict_code_","dict_name_"});
		query.and(Condition.create("dict_code_", code));
		List<YJWYDictModel> dict=this.queryListByCondition(query);
		if (dict!=null&&dict.size()>0) {
			return dict.get(0);
		}
		return null;
	}

}
