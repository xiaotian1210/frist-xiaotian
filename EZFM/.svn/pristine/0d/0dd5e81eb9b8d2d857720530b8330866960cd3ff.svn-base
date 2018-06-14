package com.shareworx.ezfm.problem.classadmin.service;

import com.shareworx.ezfm.problem.classadmin.model.ProblemClassModel;
import com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassModel;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;

/**
 * 报事分类管理业务操作接口
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
public interface ProblemClassBusinessService extends BusinessService<ProblemClassModel> {

	String ID = "problemClassBusinessService";
	
	/**
	 * 查询报事分类管理
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	ProblemClassModel[] query(Query query) throws ShareworxServiceException;
	
	
	/**
	 * 查询报事分类管理
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	ProblemClassModel[] queryTree(String class_id) throws ShareworxServiceException;
	
	/**
	 * 项目定义查询操作
	 * @param query
	 * @return
	 * @throws ShareworxServiceException
	 */
	ProblemClassModel[] projectClassquery(String class_id,String project_id) throws ShareworxServiceException;
	
	/**
	 * 查询项目关联种类
	 * @param projectId
	 * @return
	 * @throws ShareworxServiceException
	 */
	YJWYWorkTaskRepairClassModel[] queryClassByProject(String projectId,String pk_details_id) throws ShareworxServiceException;
	/**
	 * 加载报事分类管理
	 */
	ProblemClassModel[] load(ProblemClassModel model) throws ShareworxServiceException;
	
	/**
	 * 新增保存报事分类管理
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	ProblemClassModel[] save(ProblemClassModel[] models) throws ShareworxServiceException;
	
	/**
	 * 修改保存报事分类管理
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	ProblemClassModel[] update(ProblemClassModel[] models) throws ShareworxServiceException;
	
	/**
	 * 删除报事分类管理
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	ProblemClassModel[] delete(ProblemClassModel[] models) throws ShareworxServiceException;
}
