package com.shareworx.ezfm.device.fmdata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.device.fmdata.model.YJWYRoomModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * YJWYRoomModel领域操作实现
 * @author jin.li
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYRoomDomainService.ID)
public class YJWYRoomDomainServiceImpl implements YJWYRoomDomainService {

	public final static String ID = "yJWYRoomDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYRoomDomainService#save(com.shareworx.ezfm.device.fmdata.model.YJWYRoomModel[])
	 */
	@Override
	public List<YJWYRoomModel> save(YJWYRoomModel... models) throws ShareworxServiceException {
		return service.save(YJWYRoomModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYRoomDomainService#update(com.shareworx.ezfm.device.fmdata.model.YJWYRoomModel[])
	 */
	@Override
	public List<YJWYRoomModel> update(YJWYRoomModel... models) throws ShareworxServiceException {
		return service.update(YJWYRoomModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYRoomDomainService#update(java.util.List, com.shareworx.ezfm.device.fmdata.model.YJWYRoomModel[])
	 */
	@Override
	public List<YJWYRoomModel> update(List<String> editFields, YJWYRoomModel... models) throws ShareworxServiceException {
		return service.update(YJWYRoomModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYRoomDomainService#delete(com.shareworx.ezfm.device.fmdata.model.YJWYRoomModel[])
	 */
	@Override
	public int delete(YJWYRoomModel... models) throws ShareworxServiceException {
		return service.delete(YJWYRoomModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYRoomDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYRoomModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYRoomDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYRoomDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYRoomModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYRoomModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYRoomDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYRoomModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYRoomDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYRoomModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata.service.YJWYRoomDomainService#queryByExample(com.shareworx.ezfm.device.fmdata.model.YJWYRoomModel)
	 */
	@Override
	public List<YJWYRoomModel> queryByExample(YJWYRoomModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYRoomModel.META_ID, model);
	}

}
