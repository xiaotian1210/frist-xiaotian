package com.shareworx.ezfm.problem.classadmin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.problem.classadmin.model.yjwyjwtest_csModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * yjwyjwtest_csModel领域操作实现
 * @author jiangwei.peng
 * @version since Shareworx platform 3.0
 *
 */
@Service(yjwyjwtest_csDomainService.ID)
public class yjwyjwtest_csDomainServiceImpl implements yjwyjwtest_csDomainService {

	public final static String ID = "yjwyjwtest_csDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.service.yjwyjwtest_csDomainService#save(com.shareworx.ezfm.problem.classadmin.model.yjwyjwtest_csModel[])
	 */
	@Override
	public List<yjwyjwtest_csModel> save(yjwyjwtest_csModel... models) throws ShareworxServiceException {
		return service.save(yjwyjwtest_csModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.service.yjwyjwtest_csDomainService#update(com.shareworx.ezfm.problem.classadmin.model.yjwyjwtest_csModel[])
	 */
	@Override
	public List<yjwyjwtest_csModel> update(yjwyjwtest_csModel... models) throws ShareworxServiceException {
		return service.update(yjwyjwtest_csModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.service.yjwyjwtest_csDomainService#update(java.util.List, com.shareworx.ezfm.problem.classadmin.model.yjwyjwtest_csModel[])
	 */
	@Override
	public List<yjwyjwtest_csModel> update(List<String> editFields, yjwyjwtest_csModel... models) throws ShareworxServiceException {
		return service.update(yjwyjwtest_csModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.service.yjwyjwtest_csDomainService#delete(com.shareworx.ezfm.problem.classadmin.model.yjwyjwtest_csModel[])
	 */
	@Override
	public int delete(yjwyjwtest_csModel... models) throws ShareworxServiceException {
		return service.delete(yjwyjwtest_csModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.service.yjwyjwtest_csDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(yjwyjwtest_csModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.service.yjwyjwtest_csDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.service.yjwyjwtest_csDomainService#queryById(java.lang.String)
	 */
	@Override
	public yjwyjwtest_csModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(yjwyjwtest_csModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.service.yjwyjwtest_csDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public yjwyjwtest_csModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.service.yjwyjwtest_csDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<yjwyjwtest_csModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.classadmin.service.yjwyjwtest_csDomainService#queryByExample(com.shareworx.ezfm.problem.classadmin.model.yjwyjwtest_csModel)
	 */
	@Override
	public List<yjwyjwtest_csModel> queryByExample(yjwyjwtest_csModel model) throws ShareworxServiceException {
		return service.queryByExample(yjwyjwtest_csModel.META_ID, model);
	}

}
