package com.shareworx.ezfm.baseinfo.resources.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.resources.model.AttrResouorceModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.DatabaseConnections;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * attrResouorceModel领域操作实现
 * @author Administrator
 * @version since Shareworx platform 3.0
 *
 */
@Service(AttrResouorceDomainService.ID)
public class AttrResouorceDomainServiceImpl implements AttrResouorceDomainService {

	public final static String ID = "attrResouorceDomainService";
	private JdbcTemplate readJdbcTemplate = DatabaseConnections.getReadTemplate();
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.attrResouorceDomainService#save(com.shareworx.ezfm.baseinfo.resources.model.attrResouorceModel[])
	 */
	@Override
	public List<AttrResouorceModel> save(AttrResouorceModel... models) throws ShareworxServiceException {
		return service.save(AttrResouorceModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.attrResouorceDomainService#update(com.shareworx.ezfm.baseinfo.resources.model.attrResouorceModel[])
	 */
	@Override
	public List<AttrResouorceModel> update(AttrResouorceModel... models) throws ShareworxServiceException {
		return service.update(AttrResouorceModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.attrResouorceDomainService#update(java.util.List, com.shareworx.ezfm.baseinfo.resources.model.attrResouorceModel[])
	 */
	@Override
	public List<AttrResouorceModel> update(List<String> editFields, AttrResouorceModel... models) throws ShareworxServiceException {
		return service.update(AttrResouorceModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.attrResouorceDomainService#delete(com.shareworx.ezfm.baseinfo.resources.model.attrResouorceModel[])
	 */
	@Override
	public int delete(AttrResouorceModel... models) throws ShareworxServiceException {
		return service.delete(AttrResouorceModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.attrResouorceDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(AttrResouorceModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.attrResouorceDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.attrResouorceDomainService#queryById(java.lang.String)
	 */
	@Override
	public AttrResouorceModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(AttrResouorceModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.attrResouorceDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public AttrResouorceModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.attrResouorceDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<AttrResouorceModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.attrResouorceDomainService#queryByExample(com.shareworx.ezfm.baseinfo.resources.model.attrResouorceModel)
	 */
	@Override
	public List<AttrResouorceModel> queryByExample(AttrResouorceModel model) throws ShareworxServiceException {
		return service.queryByExample(AttrResouorceModel.META_ID, model);
	}

	@Override
	public int deleteByCondition(String sql) throws ShareworxServiceException {
		return readJdbcTemplate.update(sql);
	}

}
