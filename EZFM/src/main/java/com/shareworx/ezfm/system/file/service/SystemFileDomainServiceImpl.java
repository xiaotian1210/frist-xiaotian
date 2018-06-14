package com.shareworx.ezfm.system.file.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.system.file.model.SystemFileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * SystemFileModel领域操作实现
 * @author ying.chen
 * @version since Shareworx platform 3.0
 *
 */
@Service(SystemFileDomainService.ID)
public class SystemFileDomainServiceImpl implements SystemFileDomainService {

	public final static String ID = "systemFileDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.file.service.SystemFileDomainService#save(com.shareworx.ezfm.system.file.model.SystemFileModel[])
	 */
	@Override
	public List<SystemFileModel> save(SystemFileModel... models) throws ShareworxServiceException {
		return service.save(SystemFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.file.service.SystemFileDomainService#update(com.shareworx.ezfm.system.file.model.SystemFileModel[])
	 */
	@Override
	public List<SystemFileModel> update(SystemFileModel... models) throws ShareworxServiceException {
		return service.update(SystemFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.file.service.SystemFileDomainService#update(java.util.List, com.shareworx.ezfm.system.file.model.SystemFileModel[])
	 */
	@Override
	public List<SystemFileModel> update(List<String> editFields, SystemFileModel... models) throws ShareworxServiceException {
		return service.update(SystemFileModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.file.service.SystemFileDomainService#delete(com.shareworx.ezfm.system.file.model.SystemFileModel[])
	 */
	@Override
	public int delete(SystemFileModel... models) throws ShareworxServiceException {
		return service.delete(SystemFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.file.service.SystemFileDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(SystemFileModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.file.service.SystemFileDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.file.service.SystemFileDomainService#queryById(java.lang.String)
	 */
	@Override
	public SystemFileModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(SystemFileModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.file.service.SystemFileDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public SystemFileModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.file.service.SystemFileDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<SystemFileModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.file.service.SystemFileDomainService#queryByExample(com.shareworx.ezfm.system.file.model.SystemFileModel)
	 */
	@Override
	public List<SystemFileModel> queryByExample(SystemFileModel model) throws ShareworxServiceException {
		return service.queryByExample(SystemFileModel.META_ID, model);
	}

}
