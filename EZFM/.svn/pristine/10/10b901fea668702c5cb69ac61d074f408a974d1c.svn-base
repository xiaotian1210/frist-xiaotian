package com.shareworx.ezfm.system.dict.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.shareworx.ezfm.cache.annotation.DMSCacheEvict;
import com.shareworx.ezfm.cache.annotation.DMSCacheable;
import com.shareworx.ezfm.cache.constants.CacheConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.shareworx.ezfm.system.dict.model.YJWYDictModel;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryOrder;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.DateTimeUtil;

/**
 * 数据字典业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYDictBusinessService.ID)
public class YJWYDictBusinessServiceImpl implements YJWYDictBusinessService {

	@Autowired
	@Qualifier(YJWYDictDomainService.ID)
	private YJWYDictDomainService domainService;
	
	public void setDomainService(YJWYDictDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.dict.service.YJWYDictBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYDictModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYDictModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYDictModel[0];
		}
		return list.toArray(new YJWYDictModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.dict.service.YJWYDictBusinessService#load(com.shareworx.ezfm.system.dict.model.YJWYDictModel)
	 */
	@Override
	public YJWYDictModel[] load(YJWYDictModel model) throws ShareworxServiceException {
		List<YJWYDictModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYDictModel[0];
		}
		return list.toArray(new YJWYDictModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.dict.service.YJWYDictBusinessService#save(com.shareworx.ezfm.system.dict.model.YJWYDictModel[])
	 */

	@Override
	@DMSCacheEvict(namespace = "'"+ CacheConstants.DICT_CACHA_NAMESPACE+"'", allEntries="true")
	public YJWYDictModel[] save(YJWYDictModel[] models) throws ShareworxServiceException {

		//新增子级默认值 
		if (models.length>0) {
			for (YJWYDictModel model:models) {

				model.setDict_node(2);
				model.setPk_crop(UserUtil.getCurrentUser().getPk_crop());
				model.setDict_creator(UserUtil.getCurrentUserPk());
				model.setDict_creationTime(DateTimeUtil.getTimestampString(new Date()));
				model.setDict_modifier(UserUtil.getCurrentUserPk());
				model.setDict_modificationTime(System.currentTimeMillis()+"");
			}
		}
		List<YJWYDictModel> list = domainService.save(models);
		return list.toArray(new YJWYDictModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.dict.service.YJWYDictBusinessService#update(com.shareworx.ezfm.system.dict.model.YJWYDictModel[])
	 */
	@Override
	@DMSCacheEvict(namespace = "'"+ CacheConstants.DICT_CACHA_NAMESPACE+"'", allEntries="true")
	public YJWYDictModel[] update(YJWYDictModel[] models) throws ShareworxServiceException {
		if (models.length>0) {
			for (YJWYDictModel model:models) {
				model.setDict_modifier(UserUtil.getCurrentUserPk());
				model.setDict_modificationTime(System.currentTimeMillis()+"");
			}
		}
		List<YJWYDictModel> list = domainService.update(models);
		return list.toArray(new YJWYDictModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.dict.service.YJWYDictBusinessService#delete(com.shareworx.ezfm.system.dict.model.YJWYDictModel[])
	 */
	@Override
	@DMSCacheEvict(namespace = "'"+ CacheConstants.DICT_CACHA_NAMESPACE+"'", allEntries="true")
	public YJWYDictModel[] delete(YJWYDictModel[] models) throws ShareworxServiceException {
		//大于1说明有子级
		YJWYDictModel[] model=getDictByCode(models[0].getDict_code());
		if(model!=null && model.length>1){
			return null;
		}else{
			domainService.delete(models);
			return models;
		}	
	}
	/**
	 * 字典公用方法
	 * @param code
	 * @return
	 * @throws ShareworxServiceException
	 */
	@Override
	@DMSCacheable(namespace = "'"+ CacheConstants.DICT_CACHA_NAMESPACE+"'",key ="#_methodSignature+','+#code")
	public YJWYDictModel[] getDictByCode(String code) throws ShareworxServiceException {
		Query query = Query.from(YJWYDictModel.META_ID);
		//设置所需查询的列
		query.addSelect(new String[]{"pk_dict_","dict_code_","dict_name_","dict_parent_","dict_state_"});
		query.and(Condition.create("dict_parent_", code));
		QueryOrder order = new QueryOrder();
		order.setName("dict_sort_");
		order.setOrder(QueryOrder.ASC);
		query.addOrder(order);
		List<YJWYDictModel> modelList = domainService.queryListByCondition(query);	
		if(modelList==null || modelList.size()==0){
			return null;
		}
		YJWYDictModel[] yList=new YJWYDictModel[modelList.size()];
		YJWYDictModel[] models=modelList.toArray(yList);
		return models;
	}
	/**
	 * 字典公用方法
	 * @param code
	 * @return
	 * @throws ShareworxServiceException
	 */
	@Override
	public YJWYDictModel[] getDictAndNameByCode(String code,String dictName) throws ShareworxServiceException {
		Query query = Query.from(YJWYDictModel.META_ID);
		//设置所需查询的列
		query.addSelect(new String[]{"pk_dict_","dict_code_","dict_name_"});
		query.and(Condition.create("dict_parent_", code));
		QueryOrder order = new QueryOrder();
		order.setName("dict_sort_");
		order.setOrder(QueryOrder.ASC);
		query.addOrder(order);

		List<YJWYDictModel> modelList = new ArrayList<YJWYDictModel>();
		YJWYDictModel model = new YJWYDictModel();
		model.setDict_code("root");
		model.setDict_name(dictName);
		model.put("selected", true);
		modelList.add(model);
		modelList.addAll(domainService.queryListByCondition(query));
		if(modelList==null || modelList.size()==0){
			return null;
		}
		YJWYDictModel[] yList=new YJWYDictModel[modelList.size()];
		YJWYDictModel[] models=modelList.toArray(yList);
		return models;
	}


	/**
	 * 字典公用方法
	 * @param code
	 * @return
	 * @throws ShareworxServiceException
	 */
	@Override
	@DMSCacheable(namespace = "'"+ CacheConstants.DICT_CACHA_NAMESPACE+"'",key = "#_methodSignature+','+#code+','+#dictName")
	public YJWYDictModel[] getDictByNameAndParentCode(String code,String dictName) throws ShareworxServiceException {
		Query query = Query.from(YJWYDictModel.META_ID);
		//设置所需查询的列
		query.addSelect(new String[]{"pk_dict_","dict_code_","dict_name_"});
		query.and(Condition.create("dict_parent_", code));
		query.and(Condition.create("dict_name_",dictName));
		QueryOrder order = new QueryOrder();
		order.setName("dict_sort_");
		order.setOrder(QueryOrder.ASC);
		query.addOrder(order);
		List<YJWYDictModel> modelList = domainService.queryListByCondition(query);
		YJWYDictModel[] yList=new YJWYDictModel[modelList.size()];
		YJWYDictModel[] models=modelList.toArray(yList);
		return models;
	}
	
	@Override
	@DMSCacheEvict(namespace = "'"+ CacheConstants.DICT_CACHA_NAMESPACE+"'", allEntries="true")
	public YJWYDictModel[] saveParent(YJWYDictModel[] models) throws ShareworxServiceException {
		//新增父级默认值
		if (models.length>0) {
			for (YJWYDictModel model:models) {
				model.setDict_node(1);
				//获得当前用户
				model.setPk_crop(UserUtil.getCurrentUser().getPk_crop());
				model.setDict_creator(UserUtil.getCurrentUserPk());
				model.setDict_creationTime(DateTimeUtil.getTimestampString(new Date()));
				model.setDict_modifier(UserUtil.getCurrentUserPk());
				model.setDict_modificationTime(System.currentTimeMillis()+"");
			}
		}
		List<YJWYDictModel> list = domainService.save(models);
		return list.toArray(new YJWYDictModel[0]);
	}

	@Override
	//delete 子级
	@DMSCacheEvict(namespace = "'"+ CacheConstants.DICT_CACHA_NAMESPACE+"'", allEntries="true")
	public YJWYDictModel[] del(YJWYDictModel models) throws ShareworxServiceException {
		YJWYDictModel[] model={models};
		domainService.delete(model);
		return model;
	}




}
