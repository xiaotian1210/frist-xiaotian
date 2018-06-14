package com.shareworx.ezfm.problem.details.service;

import com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsModel;
import com.shareworx.ezfm.problem.details.vo.ProblemDetailsVo;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 报事主类业务操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface YJWYProblemDetailsBusinessService extends BusinessService<YJWYProblemDetailsModel> {

	String ID = "yJWYProblemDetailsBusinessService";
	
	/**
	 * 查询报事主类
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProblemDetailsModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载报事主类
	 */
	YJWYProblemDetailsModel[] load(YJWYProblemDetailsModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存报事主类
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProblemDetailsModel[] save(YJWYProblemDetailsModel[] models) throws ShareworxServiceException;
	

	/**
	 * 新增保存报事主类
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProblemDetailsModel[] saveDetails(ProblemDetailsVo vo) throws ShareworxServiceException;
	/**
	 * 报修保存报事主类
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProblemDetailsModel[] saveRepair(ProblemDetailsVo vo) throws ShareworxServiceException;
	
	/**
	 * 修改保存报事主类
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProblemDetailsModel[] update(YJWYProblemDetailsModel[] models) throws ShareworxServiceException;
	
	
	
	/**
	 * 处理报事主类
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProblemDetailsModel[] updateHandle(ProblemDetailsVo vo) throws ShareworxServiceException;
	
	
	/**
	 * 完成报事主类
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProblemDetailsModel[] updateComplete(ProblemDetailsVo vo) throws ShareworxServiceException;
	
	/**
	 * 回访报事主类
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProblemDetailsModel[] updateVisit(ProblemDetailsVo vo) throws ShareworxServiceException;
	
	/**
	 * 删除报事主类
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProblemDetailsModel[] delete(YJWYProblemDetailsModel[] models) throws ShareworxServiceException;

	/**
	 * 新增保存报事主类
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYProblemDetailsModel[] saveDetailsByEq(ProblemDetailsVo vo) throws ShareworxServiceException;

}
