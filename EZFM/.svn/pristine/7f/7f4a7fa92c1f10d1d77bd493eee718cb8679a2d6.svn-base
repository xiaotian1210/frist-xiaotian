package com.shareworx.ezfm.pub.test.plms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.pub.test.plms.model.PlmsModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * PlmsModel领域操作实现
 * @author zhi.zhang
 * @version since Shareworx platform 3.0
 *
 */
@Service(PlmsDomainService.ID)
public class PlmsDomainServiceImpl implements PlmsDomainService {

	public final static String ID = "plmsDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.pub.test.plms.service.PlmsDomainService#save(com.shareworx.ezfm.pub.test.plms.model.PlmsModel[])
	 */
	@Override
	public List<PlmsModel> save(PlmsModel... models) throws ShareworxServiceException {
		return service.save(PlmsModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.pub.test.plms.service.PlmsDomainService#update(com.shareworx.ezfm.pub.test.plms.model.PlmsModel[])
	 */
	@Override
	public List<PlmsModel> update(PlmsModel... models) throws ShareworxServiceException {
		return service.update(PlmsModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.pub.test.plms.service.PlmsDomainService#update(java.util.List, com.shareworx.ezfm.pub.test.plms.model.PlmsModel[])
	 */
	@Override
	public List<PlmsModel> update(List<String> editFields, PlmsModel... models) throws ShareworxServiceException {
		return service.update(PlmsModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.pub.test.plms.service.PlmsDomainService#delete(com.shareworx.ezfm.pub.test.plms.model.PlmsModel[])
	 */
	@Override
	public int delete(PlmsModel... models) throws ShareworxServiceException {
		return service.delete(PlmsModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.pub.test.plms.service.PlmsDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(PlmsModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.pub.test.plms.service.PlmsDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.pub.test.plms.service.PlmsDomainService#queryById(java.lang.String)
	 */
	@Override
	public PlmsModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(PlmsModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.pub.test.plms.service.PlmsDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public PlmsModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.pub.test.plms.service.PlmsDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<PlmsModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.pub.test.plms.service.PlmsDomainService#queryByExample(com.shareworx.ezfm.pub.test.plms.model.PlmsModel)
	 */
	@Override
	public List<PlmsModel> queryByExample(PlmsModel model) throws ShareworxServiceException {
		return service.queryByExample(PlmsModel.META_ID, model);
	}

}
