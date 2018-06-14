package com.shareworx.ezfm.device.list.service;

import java.util.List;
import java.util.Map;

import com.shareworx.ezfm.device.list.model.YJWYListModel;
import com.shareworx.ezfm.device.util.vo.ParamEntity;

/**
 * 设备列表业务操作接口
 * @author jin.li
 *
 */
public interface YJWYListService {
	String ID = "yJWYListService";
	
	/**
	 * 查询
	 * @param params
	 * @return
	 */
	List<YJWYListModel> queryList(ParamEntity params);
	
	List<YJWYListModel> queryList2(ParamEntity params);
	
	/**
	 * 查询总数
	 * @return
	 */
	Long queryCount(ParamEntity params);
	/**
	 * 删除设备
	 * @param eq_id
	 * @return
	 */
	int deleteByCondition(String eq_id);
	/**
	 * 查询区域ID
	 * @param pk_project
	 * @return
	 */
	String queryPk_area(String pk_project);


	/**
	 *
	 * 在BIM中 查询设备的信息牌
	 * @param project_name
	 * @return
	 */
	List<Map<String,Object>> queryEqWorkTask(String project_name);

	/**
	 *
	 * 在BIM查询巡检、维保的信息牌

	 * @return
	 */
	List<Map<String, Object>> queryEqPatrolTask(String pk_project);


	/**
	 *
	 * 在BIM查询巡检、维保的记录

	 * @return
	 */
	List<Map<String, Object>> queryEqPatrolTaskByEqId(String eq_id);

}
