package com.shareworx.ezfm.worktask.details.service;

import com.shareworx.ezfm.worktask.details.model.YJWYWorkTaskDetailsModel;
import com.shareworx.ezfm.worktask.details.vo.WorkTaskDetailsVo;
import com.shareworx.ezfm.worktask.details.vo.WrokTaskSubmitDetailsVo;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 工单详情表业务操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYWorkTaskDetailsBusinessService extends BusinessService<YJWYWorkTaskDetailsModel> {

	String ID = "yJWYWorkTaskDetailsBusinessService";
	
	/**
	 * 查询工单详情表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskDetailsModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 查询工单详情表
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskDetailsModel[] queryModels(WorkTaskDetailsVo queryvo,String whrSql) throws ShareworxServiceException;
	/**
	 * 加载工单详情表
	 */
	YJWYWorkTaskDetailsModel[] load(YJWYWorkTaskDetailsModel model) throws ShareworxServiceException;
	/**
	 * 新增保存工单详情表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskDetailsModel[] save(YJWYWorkTaskDetailsModel[] models) throws ShareworxServiceException;
	/**
	 * 设备设施预警新增保存工单详情表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskDetailsModel[] saveCallPolice(YJWYWorkTaskDetailsModel[] models) throws ShareworxServiceException;
	/**
	 * 派单保存工单详情表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskDetailsModel[] saveDelegate(WrokTaskSubmitDetailsVo vo) throws ShareworxServiceException;
	
	/**
	 * 接单、拒单、取消工单详情表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskDetailsModel[] saveOrdersOrRefuse(WrokTaskSubmitDetailsVo vo) throws ShareworxServiceException;
	
	/**
	 * 保存关闭工单
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskDetailsModel[] saveClose(String detailsId) throws ShareworxServiceException;
	/**
	 * 完成工单
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskDetailsModel[] saveComplete(WrokTaskSubmitDetailsVo vo) throws ShareworxServiceException;
	
	/**
	 * 修改保存工单详情表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskDetailsModel[] update(YJWYWorkTaskDetailsModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除工单详情表
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskDetailsModel[] delete(YJWYWorkTaskDetailsModel[] models) throws ShareworxServiceException;
}
