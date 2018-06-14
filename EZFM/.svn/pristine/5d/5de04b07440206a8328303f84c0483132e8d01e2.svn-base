package com.shareworx.ezfm.baseinfo.resources.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.persist.service.BaseDomainService;
import com.shareworx.platform.util.StringUtils;

/**
 * YJWYResourcesModel领域操作实现
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYResourcesDomainService.ID)
public class YJWYResourcesDomainServiceImpl implements YJWYResourcesDomainService {

	public final static String ID = "yJWYResourcesDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.YJWYResourcesDomainService#save(com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesModel[])
	 */
	@Override
	public List<YJWYResourcesModel> save(YJWYResourcesModel... models) throws ShareworxServiceException {
		return service.save(YJWYResourcesModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.YJWYResourcesDomainService#update(com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesModel[])
	 */
	@Override
	public List<YJWYResourcesModel> update(YJWYResourcesModel... models) throws ShareworxServiceException {
		return service.update(YJWYResourcesModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.YJWYResourcesDomainService#update(java.util.List, com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesModel[])
	 */
	@Override
	public List<YJWYResourcesModel> update(List<String> editFields, YJWYResourcesModel... models) throws ShareworxServiceException {
		return service.update(YJWYResourcesModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.YJWYResourcesDomainService#delete(com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesModel[])
	 */
	@Override
	public int delete(YJWYResourcesModel... models) throws ShareworxServiceException {
		return service.delete(YJWYResourcesModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.YJWYResourcesDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYResourcesModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.YJWYResourcesDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.YJWYResourcesDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYResourcesModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYResourcesModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.YJWYResourcesDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYResourcesModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.YJWYResourcesDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYResourcesModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.YJWYResourcesDomainService#queryByExample(com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesModel)
	 */
	@Override
	public List<YJWYResourcesModel> queryByExample(YJWYResourcesModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYResourcesModel.META_ID, model);
	}
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.baseinfo.resources.service.YJWYResourcesDomainService#queryByExample(com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesModel)
	 */
	@Override
	public String queryNameByResourcesId(String pk_resources) throws ShareworxServiceException {
		JdbcTemplate read = dao.getReadTemplate();
		String place ="";
		Map<String, Object> resourcesMap;
		String parent_id = "";
		String resourcesSql = "";
		do{
			if (StringUtils.isEmpty(place)) {
				resourcesSql = "select resources_name,parent_id from yjwy_pub_resources "
						+ "where pk_resources='"+pk_resources+"'";
			}else{
				resourcesSql = "select resources_name,parent_id from yjwy_pub_resources "
						+ "where pk_resources='"+parent_id+"'";
			}
			
			try{  
				resourcesMap = read.queryForMap(resourcesSql); 
	        }catch (EmptyResultDataAccessException e) {  
	        	return place;
	        }  
			if (resourcesMap!=null) {
				place = resourcesMap.get("resources_name").toString()+place;
				parent_id = resourcesMap.get("parent_id").toString();
				if (resourcesMap.get("parent_id")==null||StringUtils.isEmpty(resourcesMap.get("parent_id").toString())) {
					return place;
				}
			}
		}while(resourcesMap!=null);
		return place;
	}
}
