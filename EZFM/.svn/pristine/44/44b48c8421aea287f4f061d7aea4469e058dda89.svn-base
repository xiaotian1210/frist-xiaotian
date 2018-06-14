package com.shareworx.ezfm.device.siteproject.service;

import java.util.List;
import java.util.Map;

import com.shareworx.ezfm.device.siteproject.model.YJWYSiteProjectModel;
import com.shareworx.ezfm.device.util.vo.ParamEntity;

/**
 * FM与YJWY项目关联表业务操作接口
 * 
 * @author jin.li
 *
 */
public interface YJWYSiteProjectService {
	String ID = "yJWYSiteProjectService";

	/**
	 * 查询FM与YJWY项目关联数据
	 * 
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> queryMap(ParamEntity params);

	/**
	 * 根据YJWY项目id查询FM项目id数组
	 * 
	 * @param pk_project
	 * @return
	 */
	String[] queryIds(String[] pk_project);

	/**
	 * 删除
	 * 
	 * @param models
	 * @return
	 */
	YJWYSiteProjectModel[] delete(YJWYSiteProjectModel[] models);

}
