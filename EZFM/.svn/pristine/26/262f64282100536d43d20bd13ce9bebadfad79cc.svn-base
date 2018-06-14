package com.shareworx.ezfm.device.patrol.record.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.device.patrol.record.model.YJWYTaskFileModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYTaskFileModel领域操作实现
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYTaskFileDomainService.ID)
public class YJWYTaskFileDomainServiceImpl implements YJWYTaskFileDomainService {

	public final static String ID = "yJWYTaskFileDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.record.service.YJWYTaskFileDomainService#save(com.shareworx.ezfm.device.patrol.record.model.YJWYTaskFileModel[])
	 */
	@Override
	public List<YJWYTaskFileModel> save(YJWYTaskFileModel... models) throws ShareworxServiceException {
		return service.save(YJWYTaskFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.record.service.YJWYTaskFileDomainService#update(com.shareworx.ezfm.device.patrol.record.model.YJWYTaskFileModel[])
	 */
	@Override
	public List<YJWYTaskFileModel> update(YJWYTaskFileModel... models) throws ShareworxServiceException {
		return service.update(YJWYTaskFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.record.service.YJWYTaskFileDomainService#update(java.util.List, com.shareworx.ezfm.device.patrol.record.model.YJWYTaskFileModel[])
	 */
	@Override
	public List<YJWYTaskFileModel> update(List<String> editFields, YJWYTaskFileModel... models) throws ShareworxServiceException {
		return service.update(YJWYTaskFileModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.record.service.YJWYTaskFileDomainService#delete(com.shareworx.ezfm.device.patrol.record.model.YJWYTaskFileModel[])
	 */
	@Override
	public int delete(YJWYTaskFileModel... models) throws ShareworxServiceException {
		return service.delete(YJWYTaskFileModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.record.service.YJWYTaskFileDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYTaskFileModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.record.service.YJWYTaskFileDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.record.service.YJWYTaskFileDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYTaskFileModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYTaskFileModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.record.service.YJWYTaskFileDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYTaskFileModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.record.service.YJWYTaskFileDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYTaskFileModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.patrol.record.service.YJWYTaskFileDomainService#queryByExample(com.shareworx.ezfm.device.patrol.record.model.YJWYTaskFileModel)
	 */
	@Override
	public List<YJWYTaskFileModel> queryByExample(YJWYTaskFileModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYTaskFileModel.META_ID, model);
	}

}
