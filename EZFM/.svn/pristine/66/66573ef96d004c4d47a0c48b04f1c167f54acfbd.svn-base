package com.shareworx.ezfm.baseinfo.resourceslog.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shareworx.ezfm.baseinfo.resources.dao.YJWYResourcesDao;
import com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesModel;
import com.shareworx.ezfm.baseinfo.resourceslog.dao.YJWYResourcesLogDao;
import com.shareworx.ezfm.baseinfo.resourceslog.model.YJWYResourcesLogModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.ModelUtils;

/**
 * 记录资源空间日志表业务操作实现
 * 
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYResourcesLogBusinessService.ID)
public class YJWYResourcesLogBusinessServiceImpl implements YJWYResourcesLogBusinessService {

	@Autowired
	@Qualifier(YJWYResourcesLogDomainService.ID)
	private YJWYResourcesLogDomainService domainService;

	public void setDomainService(YJWYResourcesLogDomainService domainService) {
		this.domainService = domainService;
	}

	@Autowired
	@Qualifier(YJWYResourcesLogDao.ID)
	private YJWYResourcesLogDao sourceLogDao;

	public void setSourceLogDao(YJWYResourcesLogDao sourceLogDao) {
		this.sourceLogDao = sourceLogDao;
	}
	
	@Autowired
	@Qualifier(YJWYResourcesDao.ID)
	private YJWYResourcesDao resourceDao;
	
	public void setResourceDao(YJWYResourcesDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.baseinfo.resourceslog.service.
	 * YJWYResourcesLogBusinessService#query(com.shareworx.platform.persist.
	 * Query)
	 */
	@Override
	public YJWYResourcesLogModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYResourcesLogModel> list = domainService.queryListByCondition(query);
		if (ArrayUtils.isEmpty(list)) {
			return new YJWYResourcesLogModel[0];
		}
		return list.toArray(new YJWYResourcesLogModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.baseinfo.resourceslog.service.
	 * YJWYResourcesLogBusinessService#load(com.shareworx.ezfm.baseinfo.
	 * resourceslog.model.YJWYResourcesLogModel)
	 */
	@Override
	public YJWYResourcesLogModel[] load(YJWYResourcesLogModel model) throws ShareworxServiceException {
		List<YJWYResourcesLogModel> list = domainService.queryByExample(model);
		if (ArrayUtils.isEmpty(list)) {
			return new YJWYResourcesLogModel[0];
		}
		return list.toArray(new YJWYResourcesLogModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.baseinfo.resourceslog.service.
	 * YJWYResourcesLogBusinessService#save(com.shareworx.ezfm.baseinfo.
	 * resourceslog.model.YJWYResourcesLogModel[])
	 */
	@Override
	public YJWYResourcesLogModel[] save(YJWYResourcesLogModel[] models) throws ShareworxServiceException {
		List<YJWYResourcesLogModel> list = domainService.save(models);
		return list.toArray(new YJWYResourcesLogModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.baseinfo.resourceslog.service.
	 * YJWYResourcesLogBusinessService#update(com.shareworx.ezfm.baseinfo.
	 * resourceslog.model.YJWYResourcesLogModel[])
	 */
	@Override
	public YJWYResourcesLogModel[] update(YJWYResourcesLogModel[] models) throws ShareworxServiceException {
		List<YJWYResourcesLogModel> list = domainService.update(models);
		return list.toArray(new YJWYResourcesLogModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.baseinfo.resourceslog.service.
	 * YJWYResourcesLogBusinessService#delete(com.shareworx.ezfm.baseinfo.
	 * resourceslog.model.YJWYResourcesLogModel[])
	 */
	@Override
	public YJWYResourcesLogModel[] delete(YJWYResourcesLogModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

	@Override
	public String SaUpRecords(String date) throws ShareworxServiceException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String CurrentTime = this.obtainTime().substring(0, 10);
		String nozzleTime = df.format(date);
		JSONObject node = new JSONObject();
		try {
			if (nozzleTime.equals(CurrentTime)) {
				Query query = Query.from(YJWYResourcesLogModel.META_ID);
				// 1代表新增 3代表更新 4代表保存之后的更新
				String[] typeRecords = { "1", "3", "4" };
				query.where(new Condition("operate_type", QueryContents.TYPE_IN, typeRecords));
				query.and(new Condition("operate_date", QueryContents.TYPE_EQ, nozzleTime));
				List<YJWYResourcesLogModel> logModels = this.sourceLogDao.queryListByCondition(query);
				List<YJWYResourcesModel> sourcesModels = new ArrayList<YJWYResourcesModel>();
				//把记录存入集合：sourcesModels
				for (YJWYResourcesLogModel yjwyResourcesLogModel : logModels) {
					String pk_resources = yjwyResourcesLogModel.getPk_resources_log();
					YJWYResourcesModel sourcesModel = this.resourceDao.queryById(pk_resources);
					sourcesModels.add(sourcesModel);
				}
				if (logModels.size() >= 0) {
					node.put("SaUpRecords", sourcesModels);
					node.put("st", System.currentTimeMillis());
					node.put("code", 0);
					node.put("msg", "success");
				}
				return JSONArray.toJSONString(node);
			} else {
				throw new Exception("查询的时间不是今天！");
			}
		} catch (Exception e) {
			node.put("st", System.currentTimeMillis());
			node.put("code", 1);
			node.put("msg", e.getMessage());
			node.put("data", "");
			return JSONArray.toJSONString(node);
		}
	}

	// 公共方法，获取当前时间
	public String obtainTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		return df.format(new Date());
	}

	@Override
	public String deleteRecords(String date) throws ShareworxServiceException {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String CurrentTime = this.obtainTime().substring(0, 10);
		String nozzleTime = df.format(date);
		JSONObject node = new JSONObject();
		try {
			if (nozzleTime.equals(CurrentTime)) {
				Query query = Query.from(YJWYResourcesLogModel.META_ID);
				// 2代表删除
				query.where(new Condition("operate_type", QueryContents.TYPE_EQ,"2"));
				query.and(new Condition("operate_date", QueryContents.TYPE_EQ, nozzleTime));
				List<YJWYResourcesLogModel> logModels = this.sourceLogDao.queryListByCondition(query);
				List<Object> recordsId = ModelUtils.getModelFieldValues(logModels, "pk_resources_log");
				if (recordsId.size() >= 0) {
					node.put("deleteRecordsId", recordsId);
					node.put("st", System.currentTimeMillis());
					node.put("code", 0);
					node.put("msg", "success");
				}
				return JSONArray.toJSONString(node);
			} else {
				throw new Exception("查询的时间不是今天！");
			}
		} catch (Exception e) {
			node.put("st", System.currentTimeMillis());
			node.put("code", 1);
			node.put("msg", e.getMessage());
			node.put("data", "");
			return JSONArray.toJSONString(node);
		}
	
	}

}
