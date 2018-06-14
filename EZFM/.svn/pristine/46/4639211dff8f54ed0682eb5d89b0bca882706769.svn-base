package com.shareworx.ezfm.device.basic.executor.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.metadata.MetadataFactory;
import com.shareworx.platform.metadata.service.MetaCodeService;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.ezfm.device.basic.executor.dao.YJWYGroupDao;
import com.shareworx.ezfm.device.basic.executor.dao.YJWYGroupUserDao;
import com.shareworx.ezfm.device.basic.executor.model.YJWYGroupModel;
import com.shareworx.ezfm.device.basic.executor.model.YJWYGroupUserModel;
import com.shareworx.ezfm.device.util.DeviceUtil;

/**
 * 巡检维保计划业务操作实现
 * 
 * @author jin.li
 *
 */
@Service(YJWYGroupService.ID)
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class YJWYGroupServiceImpl implements YJWYGroupService {

	@Autowired
	@Qualifier(MetaCodeService.ID)
	private MetaCodeService metaCodeService;

	public void setMetaCodeService(MetaCodeService metaCodeService) {
		this.metaCodeService = metaCodeService;
	}

	private MetadataFactory factory;

	public MetadataFactory getFactory() {
		if (factory == null) {
			factory = SpringUtils.getBean("metadataFactory");
		}
		return factory;
	}

	@Autowired
	@Qualifier(YJWYGroupDao.ID)
	private YJWYGroupDao groupDao;

	public void setGroupDao(YJWYGroupDao groupDao) {
		this.groupDao = groupDao;
	}

	@Autowired
	@Qualifier(YJWYGroupUserDao.ID)
	private YJWYGroupUserDao groupUserDao;

	public void setGroupUserDao(YJWYGroupUserDao groupUserDao) {
		this.groupUserDao = groupUserDao;
	}

	public YJWYGroupModel queryOne(Serializable id) {
		return groupDao.queryOneByCondition(Query.from(YJWYGroupModel.META_ID).where(new Condition("pk_group", QueryContents.TYPE_EQ, id)));
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
	public YJWYGroupModel[] saveModels(YJWYGroupModel[] models) {
		YJWYGroupModel[] groupModels = new YJWYGroupModel[1];
		YJWYGroupModel yjwyGroupModel = models[0];
		// 生成主键
		List<MetaField> fieldList = getFactory().getMetaClassByName(YJWYGroupModel.META_ID).getFields();
		for (MetaField metaField : fieldList) {
			if ("pk_group".equals(metaField.getColumn())) {
				metaCodeService.execGenerateCode(metaField.getSerialcode(), metaField.getId(), true, yjwyGroupModel);
				break;
			}
		}
		// 主表持久化
		groupDao.saveModels(new YJWYGroupModel[] { yjwyGroupModel });
		// 获取子表数据
		YJWYGroupUserModel[] groupUserModels = this.saveGroupUserModels(yjwyGroupModel);
		if (DeviceUtil.arrayIsNotEmpty(groupUserModels)) {
			// 子表持久化
			groupUserDao.saveModels(groupUserModels);
		}
		// 查询持久化后的主表数据
		groupModels[0] = this.queryOne(yjwyGroupModel.getPk_group());
		return groupModels;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
	public YJWYGroupModel[] updateModels(YJWYGroupModel[] models) {
		YJWYGroupModel[] groupModels = new YJWYGroupModel[1];
		YJWYGroupModel yjwyGroupModel = models[0];
		// 修改主表数据
		groupDao.updateModels(new YJWYGroupModel[] { yjwyGroupModel });
		// 删除中间表数据
		groupUserDao.deleteByCondition(new Delete(YJWYGroupUserModel.META_ID).where(new Condition("pk_group", QueryContents.TYPE_EQ, yjwyGroupModel.getPk_group())));
		// 获取最新子表数据
		YJWYGroupUserModel[] groupUserModels = this.saveGroupUserModels(yjwyGroupModel);
		if (DeviceUtil.arrayIsNotEmpty(groupUserModels)) {
			// 子表数据持久化
			groupUserDao.saveModels(groupUserModels);
		}
		// 查询持久化后的主表数据
		groupModels[0] = this.queryOne(yjwyGroupModel.getPk_group());
		return groupModels;
	}

	/**
	 * 对中间表进行数据保存
	 * 
	 * @param groupModel
	 * @return
	 */
	private YJWYGroupUserModel[] saveGroupUserModels(YJWYGroupModel model) {
		String pk_group = model.getPk_group();
		// 获取人员数组
		JSONArray usersArray = JSONArray.parseArray(model.getUsers());
		YJWYGroupUserModel[] saveModels = null;
		// 遍历
		if (usersArray.size() > 0 && usersArray != null) {
			saveModels = new YJWYGroupUserModel[usersArray.size()];
			YJWYGroupUserModel groupUserModel = null;
			Map<String, String> map = null;
			for (int i = 0; i < usersArray.size(); i++) {
				map = (Map<String, String>) usersArray.get(i);
				groupUserModel = new YJWYGroupUserModel();
				new DeviceUtil().setPkCrop(groupUserModel);
				groupUserModel.setPk_group(pk_group);
				groupUserModel.setPk_user(map.get("pk_user"));
				groupUserModel.setPk_crop(model.getPk_crop());
				saveModels[i] = groupUserModel;
			}
		}
		return saveModels;
	}

	/**
	 * 根据分组主键id对关系表进行删除
	 * 
	 * @param pk_group
	 * @return
	 */
	private YJWYGroupUserModel[] deleteGroupUserModels(YJWYGroupModel model) {
		String pk_group = model.getPk_group();
		// 生成query
		Query query = Query.from(YJWYGroupUserModel.META_ID);
		query = query.where(new Condition("pk_group", QueryContents.TYPE_EQ, pk_group));
		// 查询数据
		List<YJWYGroupUserModel> models = groupUserDao.queryListByCondition(query);
		YJWYGroupUserModel[] deleteModels = null;
		// 判断是否有数据
		if (null != models && models.size() > 0) {
			deleteModels = new YJWYGroupUserModel[models.size()];
			for (int i = 0; i < models.size(); i++) {
				deleteModels[i] = models.get(i);

			}
		}
		return deleteModels;
	}

	/**
	 * 删除model
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
	public YJWYGroupModel[] deleteModels(YJWYGroupModel[] models) {
		YJWYGroupModel[] groupModels = new YJWYGroupModel[models.length];
		YJWYGroupUserModel[] groupUserMoldes = null;
		for (int i = 0; i < models.length; i++) {
			groupUserMoldes = this.deleteGroupUserModels(models[i]);
			if (null != groupUserMoldes && groupUserMoldes.length > 0) {
				groupUserDao.deleteByCondition(new Delete(YJWYGroupUserModel.META_ID).where(new Condition("pk_group", QueryContents.TYPE_EQ, models[i].getPk_group())));
			}
			groupModels[i] = this.queryOne(models[i].getPk_group());
		}
		groupDao.deleteModels(models);
		return groupModels;
	}
}
