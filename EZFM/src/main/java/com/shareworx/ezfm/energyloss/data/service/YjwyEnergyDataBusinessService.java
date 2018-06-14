package com.shareworx.ezfm.energyloss.data.service;

import com.shareworx.ezfm.energyloss.data.model.YjwyEnergyDataModel;
import com.shareworx.ezfm.energyloss.data.vo.EnergyVo;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

import java.util.List;
import java.util.Map;

/**
 * 水表电表抄表业务操作接口
 * @author zhi.zhang
 * @version since Shareworx platform 3.0
 *
 */
public interface YjwyEnergyDataBusinessService extends BusinessService<YjwyEnergyDataModel> {

	String ID = "yjwyEnergyDataBusinessService";
	
	/**
	 * 查询水表电表抄表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YjwyEnergyDataModel[] query(Query query) throws ShareworxServiceException;

	/**
	 * 按月查询项目用水用电
	 * @param vo
	 * @return
	 */
	List<Map<String,Object>> queryMonthData(EnergyVo vo);


	/**
	 * 按月查询每个水表用水用电
	 * @param vo
	 * @return
	 */
	List<Map<String,Object>> queryMonthDataEach(EnergyVo vo);

	/**
	 *查询项目
	 * @return
	 */
	Long countMonthData(EnergyVo vo);

	/**
	 * 查询每个表
	 * @param vo
	 * @return
	 */
	Long countMonthDataEach(EnergyVo vo);


	/**
	 * 查看项目每月份的用水用电
	 * @param pk_project 项目
	 * @param type 水表、电表
	 * @param year 年份
	 * @return
	 */
	List<Map<String,Object>> queryMonthDetails(String pk_project,String type,String year);

	/**
	 * 按月查看每个水表电表的用量
	 * @param eq_id
	 * @param year
	 * @return
	 */
	List<Map<String,Object>> queryMonthDetailsEach(String eq_id,String year);

	/**
	 * 加载水表电表抄表
	 */
	YjwyEnergyDataModel[] load(YjwyEnergyDataModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存水表电表抄表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YjwyEnergyDataModel[] save(YjwyEnergyDataModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存水表电表抄表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YjwyEnergyDataModel[] update(YjwyEnergyDataModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除水表电表抄表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YjwyEnergyDataModel[] delete(YjwyEnergyDataModel[] models) throws ShareworxServiceException;

	/**
	 * 修改设备最后一次度数
	 * @param eq_id
	 * @param string
	 */
	void updateLastEneryData(String eq_id,String string);
}
