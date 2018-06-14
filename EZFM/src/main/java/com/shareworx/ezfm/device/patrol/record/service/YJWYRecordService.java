package com.shareworx.ezfm.device.patrol.record.service;

import java.util.List;
import java.util.Map;

import com.shareworx.ezfm.device.util.vo.ParamEntity;

/**
 * 巡检维保记录service
 * @author jin.li
 *
 */
public interface YJWYRecordService {
	String ID = "yJWYRecordService";
	
	/**
	 * 任务列表查询
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> queryRecordMap(ParamEntity params);
	
	List<Map<String, Object>> queryRecordMap2(ParamEntity params);


	/**
	 * 任务数量查询
	 * @param params
	 * @return
	 */
	Long queryRecordCount(ParamEntity params);

	

}
