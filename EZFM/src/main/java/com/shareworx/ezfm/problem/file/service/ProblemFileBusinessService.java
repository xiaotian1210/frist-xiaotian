package com.shareworx.ezfm.problem.file.service;

import com.shareworx.ezfm.problem.file.model.ProblemFileModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 报事报修文件存储业务操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface ProblemFileBusinessService extends BusinessService<ProblemFileModel> {

	String ID = "problemFileBusinessService";
	
	/**
	 * 查询报事报修文件存储
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	ProblemFileModel[] query(Query query) throws ShareworxServiceException;
	
	/**
	 * 加载报事报修文件存储
	 */
	ProblemFileModel[] load(ProblemFileModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存报事报修文件存储
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	ProblemFileModel[] save(ProblemFileModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存报事报修文件存储
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	ProblemFileModel[] update(ProblemFileModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除报事报修文件存储
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	ProblemFileModel[] delete(ProblemFileModel[] models) throws ShareworxServiceException;
}
