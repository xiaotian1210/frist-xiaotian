package com.shareworx.ezfm.baseinfo.resources.file.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.resources.file.model.YJWYResourceFileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYResourceFileModel领域操作实现
 * @author Administrator
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYResourceFileDomainService.ID)
public class YJWYResourceFileDomainServiceImpl implements YJWYResourceFileDomainService {

	public final static String ID = "yJWYResourceFileDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.file.service.YJWYResourceFileDomainService#save(com.shareworx.ezfm.baseinfo.resources.file.model.YJWYResourceFileModel[])
	 */
	@Override
	public List<YJWYResourceFileModel> save(YJWYResourceFileModel... models) throws ShareworxServiceException {
		return service.save(YJWYResourceFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.file.service.YJWYResourceFileDomainService#update(com.shareworx.ezfm.baseinfo.resources.file.model.YJWYResourceFileModel[])
	 */
	@Override
	public List<YJWYResourceFileModel> update(YJWYResourceFileModel... models) throws ShareworxServiceException {
		return service.update(YJWYResourceFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.file.service.YJWYResourceFileDomainService#update(java.util.List, com.shareworx.ezfm.baseinfo.resources.file.model.YJWYResourceFileModel[])
	 */
	@Override
	public List<YJWYResourceFileModel> update(List<String> editFields, YJWYResourceFileModel... models) throws ShareworxServiceException {
		return service.update(YJWYResourceFileModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.file.service.YJWYResourceFileDomainService#delete(com.shareworx.ezfm.baseinfo.resources.file.model.YJWYResourceFileModel[])
	 */
	@Override
	public int delete(YJWYResourceFileModel... models) throws ShareworxServiceException {
		return service.delete(YJWYResourceFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.file.service.YJWYResourceFileDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYResourceFileModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.file.service.YJWYResourceFileDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.file.service.YJWYResourceFileDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYResourceFileModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYResourceFileModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.file.service.YJWYResourceFileDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYResourceFileModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.file.service.YJWYResourceFileDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYResourceFileModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.file.service.YJWYResourceFileDomainService#queryByExample(com.shareworx.ezfm.baseinfo.resources.file.model.YJWYResourceFileModel)
	 */
	@Override
	public List<YJWYResourceFileModel> queryByExample(YJWYResourceFileModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYResourceFileModel.META_ID, model);
	}

}
