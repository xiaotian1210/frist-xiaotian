package com.shareworx.ezfm.baseinfo.userstation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.userstation.model.UserStationModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;

/**
 * UserStationModel领域操作实现
 * @author qiang.gu
 * @version since Shareworx platform 3.0
 *
 */
@Service(UserStationDomainService.ID)
public class UserStationDomainServiceImpl implements UserStationDomainService {

	public final static String ID = "userStationDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.userstation.service.UserStationDomainService#save(com.shareworx.ezfm.baseinfo.userstation.model.UserStationModel[])
	 */
	@Override
	public List<UserStationModel> save(UserStationModel... models) throws ShareworxServiceException {
		return service.save(UserStationModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.userstation.service.UserStationDomainService#update(com.shareworx.ezfm.baseinfo.userstation.model.UserStationModel[])
	 */
	@Override
	public List<UserStationModel> update(UserStationModel... models) throws ShareworxServiceException {
		return service.update(UserStationModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.userstation.service.UserStationDomainService#update(java.util.List, com.shareworx.ezfm.baseinfo.userstation.model.UserStationModel[])
	 */
	@Override
	public List<UserStationModel> update(List<String> editFields, UserStationModel... models) throws ShareworxServiceException {
		return service.update(UserStationModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.userstation.service.UserStationDomainService#delete(com.shareworx.ezfm.baseinfo.userstation.model.UserStationModel[])
	 */
	@Override
	public int delete(UserStationModel... models) throws ShareworxServiceException {
		return service.delete(UserStationModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.userstation.service.UserStationDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(UserStationModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.userstation.service.UserStationDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.userstation.service.UserStationDomainService#queryById(java.lang.String)
	 */
	@Override
	public UserStationModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(UserStationModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.userstation.service.UserStationDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public UserStationModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.userstation.service.UserStationDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<UserStationModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.userstation.service.UserStationDomainService#queryByExample(com.shareworx.ezfm.baseinfo.userstation.model.UserStationModel)
	 */
	@Override
	public List<UserStationModel> queryByExample(UserStationModel model) throws ShareworxServiceException {
		return service.queryByExample(UserStationModel.META_ID, model);
	}

}
