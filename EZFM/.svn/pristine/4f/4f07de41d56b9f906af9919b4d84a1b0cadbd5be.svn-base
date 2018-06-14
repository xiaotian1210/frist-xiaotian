package com.shareworx.ezfm.device.fmdata.service;

import com.shareworx.ezfm.device.fmdata_eq.model.YJWYEqSysModel;
import com.shareworx.platform.model.ModelAndResult;

import java.util.List;

/**
 * FM数据同步业务操作接口
 * 
 * @author jin.li
 *
 */
public interface YJWYFmDataService {
	String ID = "yJWYFmDataService";

	/**
	 * FM数据同步入口
	 * 
	 * @param address
	 * @param crop_code
	 * @return
	 * @throws Exception
	 */
	ModelAndResult synchro(String address, String crop_code) throws Exception;

	/**
	 * 查询最大更新时间
	 * 
	 * @param localTable
	 * @return
	 */
	String queryLastUpdateTime(String localTable);

	/**
	 * 查询表内数据总数
	 * 
	 * @param localTable
	 * @return
	 */
	Long queryCount(String localTable);


	Long queryCountBySysId(List<YJWYEqSysModel> list);
}
