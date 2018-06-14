package com.shareworx.ezfm.baseinfo.userstation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.ezfm.baseinfo.station.service.YJWYStationDomainService;
import com.shareworx.ezfm.baseinfo.userstation.model.UserStationModel;

/**
 * 人员岗位关系业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(UserStationBusinessService.ID)
public class UserStationBusinessServiceImpl implements UserStationBusinessService {

	@Autowired
	@Qualifier(UserStationDomainService.ID)
	private UserStationDomainService domainService;

	
	public void setDomainService(UserStationDomainService domainService) {
		this.domainService = domainService;
	}

	@Autowired
	@Qualifier(YJWYStationDomainService.ID)
	private YJWYStationDomainService statService;
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.userstation.service.UserStationBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public UserStationModel[] query(Query query) throws ShareworxServiceException {
		List<UserStationModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new UserStationModel[0];
		}
		return list.toArray(new UserStationModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.userstation.service.UserStationBusinessService#load(com.shareworx.ezfm.baseinfo.userstation.model.UserStationModel)
	 */
	@Override
	public UserStationModel[] load(UserStationModel model) throws ShareworxServiceException {
		List<UserStationModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new UserStationModel[0];
		}
		return list.toArray(new UserStationModel[0]);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.userstation.service.UserStationBusinessService#save(com.shareworx.ezfm.baseinfo.userstation.model.UserStationModel[])
	 */
	@Override
	public UserStationModel[] save(UserStationModel[] models) throws ShareworxServiceException {
		List<UserStationModel> list = domainService.save(models);
		return list.toArray(new UserStationModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.userstation.service.UserStationBusinessService#update(com.shareworx.ezfm.baseinfo.userstation.model.UserStationModel[])
	 */
	@Override
	public UserStationModel[] update(UserStationModel[] models) throws ShareworxServiceException {
		List<UserStationModel> list = domainService.update(models);
		return list.toArray(new UserStationModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.userstation.service.UserStationBusinessService#delete(com.shareworx.ezfm.baseinfo.userstation.model.UserStationModel[])
	 */
	@Override
	public UserStationModel[] delete(UserStationModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

}
