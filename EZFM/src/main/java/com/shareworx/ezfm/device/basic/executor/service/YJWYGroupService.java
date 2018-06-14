package com.shareworx.ezfm.device.basic.executor.service;

import java.io.Serializable;

import com.shareworx.ezfm.device.basic.executor.model.YJWYGroupModel;

/**
 * 巡检维保计划业务操作接口
 * @author jin.li
 *
 */
public interface YJWYGroupService {


	String ID = "yJWYGroupService";
	
	
	/**
	 * 删除多条数据
	 * @param models
	 * @return
	 */
	YJWYGroupModel[] deleteModels(YJWYGroupModel[] models);
	
	/**
	 * 根据主键查询单条数据
	 * @param id
	 * @return
	 */
	YJWYGroupModel queryOne(Serializable id);
	
	/**
	 * 插入多条数据
	 * @param models
	 * @return
	 */
	YJWYGroupModel[] saveModels(YJWYGroupModel[] models);
	
	/**
	 * 插入多条数据
	 * @param models
	 * @return
	 */
	YJWYGroupModel[] updateModels(YJWYGroupModel[] models);
	
}
